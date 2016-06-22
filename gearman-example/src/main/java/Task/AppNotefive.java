package Task;


import java.util.Date;
import java.util.Map;
import java.util.Date;
import java.util.Properties;
import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;
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

public class AppNotefive {
	public static  String mysqlConnect(){
        String dbDriver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://rdsravqqifyenyy.mysql.rds.aliyuncs.com:3306/dby40473rc47q3jg";
        String username = "ghp2";
        String password = "GHP2013";
        String re  = "";
        Statement mStatement = null;
        ResultSet mResultSet = null;
        Connection mConnection = null;
        //String sql = "select email, c.id, c.deadline, c.case_id, cases.user_id, u.name from users u, case_updates c, user_case_updates uc,cases where u.id = uc.user_id and uc.case_update_id = c.id and uc.assignee=1 and c.deadline !='NULL'  and (c.resolved =0 or c.resolved is null) and deadline < DATE_SUB(NOW(), INTERVAL 8 HOUR)and  deadline > DATE_SUB(NOW(), INTERVAL 13  HOUR)  and cases.id = c.case_id;";
        String sql = "select device_token,  c.case_id,c.id from users u, case_updates c, user_case_updates uc,cases where u.id = uc.user_id and uc.case_update_id = c.id and uc.assignee=1 and c.deadline !='NULL'  and (c.resolved =0 or c.resolved is null) and deadline < DATE_SUB(NOW(), INTERVAL 8 HOUR)and  deadline > DATE_SUB(NOW(), INTERVAL 13  HOUR) and cases.id = c.case_id and device_token !='NULL';";
        
        //String sql = "select email, c.id, c.deadline, c.case_id, cases.user_id, u.name from users u, case_updates c, user_case_updates uc,cases where u.id = uc.user_id and uc.case_update_id = c.id and uc.assignee=1 and c.deadline !='NULL'  and (c.resolved =0 or c.resolved is null) and deadline > DATE_SUB(NOW(), INTERVAL 8 HOUR)and deadline <= DATE_SUB(NOW(), INTERVAL 2  HOUR)  and cases.id = c.case_id;";
        
       // String sql = "select device_token,  c.case_id,c.id from users u, case_updates c, user_case_updates uc,cases where u.id = uc.user_id and uc.case_update_id = c.id and uc.assignee=1 and c.deadline !='NULL'  and (c.resolved =0 or c.resolved is null) and deadline > DATE_SUB(NOW(), INTERVAL 8 HOUR) and cases.id = c.case_id and device_token !='NULL';";
        // sql =  new String(sql.getBytes("ISO-8859-1"),"utf-8");
        //   String sql = "select name, last_name from patients where id =6;";   
        try{
            Class.forName(dbDriver).newInstance();
            mConnection = DriverManager.getConnection(url, username, password);
            mStatement = mConnection.createStatement();
            mResultSet = mStatement.executeQuery(sql);

            try{
                while(mResultSet.next()){
                    String tokene= mResultSet.getString(1);
                    String token =splt(tokene);
                    //re = new String(re.getBytes("ISO-8859-1"),"utf-8"); 
                    System.out.print("token="+token);
                    Integer case_id=mResultSet.getInt(2);
                    //re = new String(re.getBytes("ISO-8859-1"),"utf-8");
                    System.out.println(" case_id= "+case_id);
                    Integer update_id= mResultSet.getInt(3);
                    System.out.println(" update_id= "+update_id);
                    Sentnote(case_id, update_id, token);
                  // writeTxtFile(" email="+to_email +" update_id= "+update_id +" deadline= "+deadline + " caseID= "+ case_id+" userID= "+ user_id +" Name= "+name);
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
	
	
	 
	
	
	
	public static void Sentnote(Integer case_id, Integer update_id,String token){
	
	
		
	ApnsService service = APNS.newService()
		    //.withCert("/Users/chaoduan/Downloads/PushProdCertificates.p12", "GHP2016ghp2016")
			.withCert("/home/chaoduan/backup/project/backend/PushProdCertificates.p12", "GHP2016ghp2016")
		    .withSandboxDestination()
		    .build();
	String payload = APNS.newPayload()
            .badge(1)
            .customField("case_id", case_id)
            .customField("update_id", update_id)
            .alertBody("Case#"+case_id+" 您有未完成事件")
            .actionKey("play").build();


    //String payload = APNS.newPayload().alertBody("Case  您未完成").build();
	//String token = "15deb787 9b2360dc 5f9b4135 f94690a5 3a68bc26 7ee69058 907ba199 9f4cb109";
    //token = "7120be95 bfea6499 5fa77982 ff6e3b4e afcc592e 8f67740c 0643965a 8dd595dd";
	//token ="987fbaa6 6acbdbf9 b38c4e2e 1655aaf8 3e791717 d56d6c17 0e2a4cc6 715a9ba1";
	
	service.push(token, payload);
	
	  Map<String, Date> inactiveDevices = service.getInactiveDevices();
	  for (String deviceToken : inactiveDevices.keySet()) {
	      Date inactiveAsOf = inactiveDevices.get(deviceToken);
	  }
	      

}
	  public static String splt(String info){
          String f;
            String[] s1 = info.split("<");
            String[] s2 = s1[1].split(">");
         f= s2[0];
            return f;}
	
	
//	  public static void main(String argv[]){
//		 // Sentnote(null, null, null);
//		  mysqlConnect();
//  } 
}