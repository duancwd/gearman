package Task;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;








public class SendMailtxt {


	static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  
	   private static String path = "/home/chaoduan/backup/log/log"+df.format(new Date())+".txt";
	   private static File filename = new File(path);


        public static  String mysqlConnect(){
            String dbDriver = "com.mysql.jdbc.Driver";

            String url = "jdbc:mysql://rdsravqqifyenyy.mysql.rds.aliyuncs.com:3306/dby40473rc47q3jg";

            String username = "ghp2";
            String password = "GHP2013";
            String re  = "";
            Statement mStatement = null;
            ResultSet mResultSet = null;
            Connection mConnection = null;
            String sql = "select email, c.id, c.deadline, c.case_id, cases.user_id, u.name from users u, case_updates c, user_case_updates uc,cases where u.id = uc.user_id and uc.case_update_id = c.id and uc.assignee=1 and c.deadline !='NULL'  and (c.resolved =0 or c.resolved is null) and TO_DAYS(NOW())-TO_DAYS(c.deadline)<=0 and cases.id = c.case_id;";
            // sql =  new String(sql.getBytes("ISO-8859-1"),"utf-8");
            //   String sql = "select name, last_name from patients where id =6;";   
            try{
                Class.forName(dbDriver).newInstance();
                mConnection = DriverManager.getConnection(url, username, password);
                mStatement = mConnection.createStatement();
                mResultSet = mStatement.executeQuery(sql);

                try{
                    while(mResultSet.next()){
                        String to_email= mResultSet.getString(1);
                        //re = new String(re.getBytes("ISO-8859-1"),"utf-8"); 
                        System.out.print("email="+to_email);
                        
                        Integer update_id=mResultSet.getInt(2);
                        //re = new String(re.getBytes("ISO-8859-1"),"utf-8");
                        System.out.println(" update_id= "+update_id);
                        String deadline= mResultSet.getString(3);
                        System.out.println(" deadline= "+deadline);
                        Integer case_id=mResultSet.getInt(4);
                        System.out.println(" caseID= "+ case_id);
                         Integer user_id=mResultSet.getInt(5);
                        System.out.println(" userID= "+ user_id);
                         String name= mResultSet.getString(6);
                        //re = new String(re.getBytes("ISO-8859-1"),"utf-8"); 
                        System.out.println(" Name= "+name);
                       send(to_email, case_id, update_id,deadline,user_id,name);
                       writeTxtFile(" email="+to_email +" update_id= "+update_id +" deadline= "+deadline + " caseID= "+ case_id+" userID= "+ user_id +" Name= "+name);
                    }
                }catch (Exception e){
                    System.out.println("connection  error! \n" + e.getMessage());
                }
             }catch (SQLException e){
                System.out.println("connection  error: \n" + url + "\n" + e.getMessage());
             }catch (Exception e){
                e.printStackTrace();
             }finally{
            //      mStatement.close();   
            //      mConnection.close();   
            }

                        return re;
        }



        public static void send(String to_email, Integer case_id, Integer update_id, String deadline,Integer user_id, String name  )throws IOException {

                //final String username = "duancwd@gmail.com";
                final String username = "info@globalhealthpass.com";
                final String password = "GHP2013ghp2013";

                Properties props = new Properties();
                props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
           props.put("mail.smtp.host", "smtp.partner.outlook.cn");
         //   props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.port", "587");

                Session session = Session.getInstance(props,
                  new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(username, password);
                        }
                  });

                try {
                	  Message message = new MimeMessage(session);
                      message.setFrom(new InternetAddress("info@globalhealthpass.com"));
                      message.setRecipients(Message.RecipientType.TO,
                              InternetAddress.parse(to_email));
                      message.setSubject("No-reply ["+name+"] Case#"+case_id+"Incomplete ["+to_email+"]");
                      message.setText(" [case # "+ case_id +"] [update # " +update_id + "]Incomplete. " + "\n\n More Details:\n  http://admin.globalhealthpass.com/users/"+user_id+"/cases/"+case_id+"/case_updates/"+update_id +"\n\n \n Confidential, please don't share outside the recipients." );

                      Transport.send(message);

                      System.out.println("Done");
                      writeTxtFile("\n Done \n");

              } catch (MessagingException e) {
                      throw new RuntimeException(e);
              }
      }

        
        
        
        

        
        
        public static void creatTxtFile() throws IOException {
            if (!filename.exists()) {
                filename.createNewFile();
                System.err.println(filename + "new");
            }
        }

 public static String readTxtFile() throws IOException {
            String strs = "";
  
        
        try {
            FileReader read = new FileReader(filename);
            StringBuffer sb = new StringBuffer();
            char ch[] = new char[1024];
            int d = read.read(ch);
            while (d != -1) {
                String str = new String(ch, 0, d);
                sb.append(str);
                d = read.read(ch);
            }

            strs = sb.toString();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block  
            e.printStackTrace();
        }
        System.out.println("context:" + "\r\n" + strs);
        return strs;
    }
public static void writeTxtFile(String newStr) throws IOException {


        creatTxtFile();

        String str = readTxtFile();


        FileWriter fw = new FileWriter(path);
        if (str.length() < 1) {
            fw.write(newStr);
        } else {
            fw.write(str + "\r\n" + newStr);
        }
        fw.close();
    }
//      public static void main(String[] args) {
//              mysqlConnect();
//      }

}




