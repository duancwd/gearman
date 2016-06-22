package Task;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfAppearance;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

public class PDF {
	
	
	public static  void mysqlConnect(byte[] data) throws DocumentException, IOException{
		
		
		 int i =data.length-1;
		 String index="" ;
		 for(int j =0; j <i; j++){
			index = index + (char)data[j];
        String dbDriver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://rdsravqqifyenyy.mysql.rds.aliyuncs.com:3306/dby40473rc47q3jg";
        String username = "ghp2";
        String password = "GHP2013";
        String re  = "";
        Statement mStatement = null;
        ResultSet mResultSet = null;
        Connection mConnection = null;
        String sql = "select device_token,  c.case_id,c.id from users u, case_updates c, user_case_updates uc,cases where u.id = uc.user_id and uc.case_update_id = c.id and uc.assignee=1 and c.deadline !='NULL'  and (c.resolved =0 or c.resolved is null) and deadline > DATE_SUB(NOW(), INTERVAL 8 HOUR) and cases.id = c.case_id and device_token !='NULL';";
        // sql =  new String(sql.getBytes("ISO-8859-1"),"utf-8");
        //   String sql = "select name, last_name from patients where id =6;";   
        try{
            Class.forName(dbDriver).newInstance();
            mConnection = DriverManager.getConnection(url, username, password);
            mStatement = mConnection.createStatement();
            mResultSet = mStatement.executeQuery(sql);

            try{
                while(mResultSet.next()){
//                    String tokene= mResultSet.getString(1);
//                 
//                    //re = new String(re.getBytes("ISO-8859-1"),"utf-8"); 
//                    
//                    Integer case_id=mResultSet.getInt(2);
//                    //re = new String(re.getBytes("ISO-8859-1"),"utf-8");
//                    System.out.println(" case_id= "+case_id);
//                    Integer update_id= mResultSet.getInt(3);
//                    System.out.println(" update_id= "+update_id);
//                    //Sentnote(case_id, update_id, token);
                    
                	String fullname = mResultSet.getString(1);;
            		String Paddress = mResultSet.getString(2);;
            		String CityCoutryPostalCode= mResultSet.getString(3);;
            		String PhoneCell =mResultSet.getString(4); ;
            		String Email =mResultSet.getString(5);
            		String Dofb =mResultSet.getString(6);
            		String age =mResultSet.getString(7);
            		String PoB =mResultSet.getString(7);
            		String sex = mResultSet.getString(8);
            		String Nationality = mResultSet.getString(9);
            		String Religion = mResultSet.getString(10);
            		String HoL  = mResultSet.getString(11);
            		String nHoL = mResultSet.getString(12);
            		String UnknownDeclined =mResultSet.getString(13);
            		String AIN =mResultSet.getString(14);
            		String Asian =mResultSet.getString(15);
            		String BoAA =mResultSet.getString(16);
            		String NHI =mResultSet.getString(17);
            		String Other =mResultSet.getString(18);
            		String WoC =mResultSet.getString(19);
            		String UnknowD_2 =mResultSet.getString(20);
            		String USSNia1  =mResultSet.getString(21);
            		String USSNia2 = mResultSet.getString(22);
            		String Single =mResultSet.getString(23);
            		String Married =mResultSet.getString(24);
            		String Divorced =mResultSet.getString(25);
            		String Widowed = mResultSet.getString(26);
            		String LS  =mResultSet.getString(27);
            		String RDP =mResultSet.getString(28);
            		String RDPDissolved= mResultSet.getString(29);
            		String RDPWidowed =mResultSet.getString(30);
            		String Test1 =mResultSet.getString(31);
            		String undefined =mResultSet.getString(32);
            		String Opim =mResultSet.getString(33);
            		String LoE1 = mResultSet.getString(34);
            		String LoE2 = mResultSet.getString(35);
            		String LoE3  = mResultSet.getString(36);
            		String CT1  =mResultSet.getString(37);
            		String CT2  = mResultSet.getString(38);
            		String CT3   =  mResultSet.getString(39);
            		String SoK1 = mResultSet.getString(40);
            		String SoK2 = mResultSet.getString(41);
            		String CSC1_2 =mResultSet.getString(42);
            		String CSC2_2 = mResultSet.getString(43);
            		String CSC3_2 = mResultSet.getString(44);
            		String LCI = mResultSet.getString(45);
            		String one =mResultSet.getString(46);
            		String two =mResultSet.getString(47);
            		String CSC1_3 = mResultSet.getString(48);
            		String CSC2_3 = mResultSet.getString(49);
            		String Tbspi  = mResultSet.getString(50);
            		String PSMD  =mResultSet.getString(51);
            		String cash =mResultSet.getString(52);
            		String CCdoUba =mResultSet.getString(53);
            		String WT =mResultSet.getString(54);
            		String IIraUta = mResultSet.getString(55);
            		String visa =mResultSet.getString(56);
            		String MC =mResultSet.getString(57);
            	    String AE  =mResultSet.getString(58);
            	    String Other_2 =mResultSet.getString(59);
            	    String undefined_2 =mResultSet.getString(60);
            	    String ICN =mResultSet.getString(61);
            	    String Sbtca =mResultSet.getString(62);
            	    String CityStateCountryZip =mResultSet.getString(63);
            	    String TCP =mResultSet.getString(64);
            	    String Group =mResultSet.getString(65);
            	    String Authorization =mResultSet.getString(66);
            	    String SubscriberPolicy_1 = mResultSet.getString(67);
            		String SubscriberPolicy_2 =mResultSet.getString(68);
            		String FriendFamily =mResultSet.getString(69);
            		String PR =mResultSet.getString(70);
            		String IsW =mResultSet.getString(71);
            		String Reputation =mResultSet.getString(72);
            		String other_3 =mResultSet.getString(73);
            		String undefined_3 =mResultSet.getString(74);
                    
            		pdf(fullname, Paddress, CityCoutryPostalCode,PhoneCell,  Email,  Dofb, age, PoB, sex, Nationality,  Religion, HoL, nHoL,  UnknownDeclined, AIN, Asian, BoAA,  NHI,
           				 Other,  WoC,  UnknowD_2,  USSNia1, USSNia2,  Single, Married,  Divorced, Widowed,LS, RDP, RDPDissolved, RDPWidowed,Test1,  undefined,Opim,LoE1,LoE2,  LoE3, CT1,CT2,  CT3,  SoK1,  SoK2, CSC1_2,
           				 CSC2_2, CSC3_2,  LCI, one, two,  CSC1_3 , CSC2_3, Tbspi,PSMD,  cash,CCdoUba,WT,  IIraUta,  visa,  MC,
           				AE, Other_2, undefined_2, ICN ,Sbtca,  CityStateCountryZip, TCP, Group,  Authorization, SubscriberPolicy_1,
           				 SubscriberPolicy_2,  FriendFamily,  PR,  IsW,  Reputation,  other_3 , undefined_3);
                    
                   // PDFrun(byte[] data)
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
        }}

                 
    }
	
	
	
	
	
	
	
	
	
	public static void   PDFrun(byte[] data) throws DocumentException, IOException{
		 int i =data.length-1;
		 String name="" ;
		 for(int j =0; j <i; j++){
			name = name + (char)data[j];
		 }
		 
		String fullname = name;
		String Paddress = "Paddress";
		String CityCoutryPostalCode= "CityCoutryPostalCode";
		String PhoneCell ="PhoneCell" ;
		String Email ="Email";
		String Dofb ="Dofb";
		String age ="age";
		String PoB ="PoB";
		String sex = "Female";
		String Nationality = "Nationality";
		String Religion = "Religion";
		String HoL  = "On";
		String nHoL = "On";
		String UnknownDeclined ="On";
		String AIN ="On";
		String Asian ="On";
		String BoAA ="On";
		String NHI ="On";
		String Other ="On";
		String WoC ="On";
		String UnknowD_2 ="On";
		String USSNia1  ="USSNia1";
		String USSNia2 = "USSNia2";
		String Single ="On";
		String Married ="On";
		String Divorced ="On";
		String Widowed = "On";
		String LS  ="On";
		String RDP ="On";
		String RDPDissolved= "On";
		String RDPWidowed ="On";
		String Test1 ="Test";
		String undefined ="Yes";
		String Opim ="Opim";
		String LoE1 = "LoE1";
		String LoE2 = "LoE2";
		String LoE3  = "LoE3";
		String CT1  ="CT1";
		String CT2  = "CT2";
		String CT3   =  "CT3";
		String SoK1 = "SoK1";
		String SoK2 = "SoK2";
		String CSC1_2 ="CSC1_2";
		String CSC2_2 = "CSC1_2";
		String CSC3_2 = "CSC3_2";
		String LCI = "LCI";
		String one ="one";
		String two ="two";
		String CSC1_3 = "CSC1_3";
		String CSC2_3 = "CSC2_3";
		String Tbspi  = "Tbspi";
		String PSMD  ="PSMD";
		String cash ="On";
		String CCdoUba ="On";
		String WT ="On";
		String IIraUta = "On";
		String visa ="On";
		String MC ="On";
	    String AE  ="On";
	    String Other_2 ="On";
	    String undefined_2 ="undefined_2";
	    String ICN ="ICN" ;
	    String Sbtca ="Sbtca";
	    String CityStateCountryZip ="CityStateCountryZip";
	    String TCP ="TCP";
	    String Group ="Group";
	    String Authorization ="Authorization";
	    String SubscriberPolicy_1 = "SubscriberPolicy_1";
		String SubscriberPolicy_2 ="SubscriberPolicy_2";
		String FriendFamily ="On";
		String PR ="On";
		String IsW ="On";
		String Reputation ="On";
		String other_3 ="On";
		String undefined_3 ="undefined_3";
		
		pdf(fullname, Paddress, CityCoutryPostalCode,PhoneCell,  Email,  Dofb, age, PoB, sex, Nationality,  Religion, HoL, nHoL,  UnknownDeclined, AIN, Asian, BoAA,  NHI,
				 Other,  WoC,  UnknowD_2,  USSNia1, USSNia2,  Single, Married,  Divorced, Widowed,LS, RDP, RDPDissolved, RDPWidowed,Test1,  undefined,Opim,LoE1,LoE2,  LoE3, CT1,CT2,  CT3,  SoK1,  SoK2, CSC1_2,
				 CSC2_2, CSC3_2,  LCI, one, two,  CSC1_3 , CSC2_3, Tbspi,PSMD,  cash,CCdoUba,WT,  IIraUta,  visa,  MC,
				AE, Other_2, undefined_2, ICN ,Sbtca,  CityStateCountryZip, TCP, Group,  Authorization, SubscriberPolicy_1,
				 SubscriberPolicy_2,  FriendFamily,  PR,  IsW,  Reputation,  other_3 , undefined_3);
	    
	    

	}
	
	
	
	public static void pdf(String fullname, String Paddress, String CityCoutryPostalCode, String PhoneCell, String Email, String Dofb,
			String age, String PoB, String sex,String Nationality, String Religion,String HoL,String nHoL, String UnknownDeclined, String AIN,String Asian,String BoAA, String NHI,
			 String Other, String WoC, String UnknowD_2, String USSNia1, String USSNia2, String Single,String Married, String Divorced, String Widowed,
			 String LS, String RDP, String RDPDissolved, String RDPWidowed,String Test1, String undefined,String Opim,String LoE1, String LoE2, String LoE3,String CT1,String CT2, String CT3, String SoK1, String SoK2, String CSC1_2,
			 String CSC2_2, String CSC3_2, String LCI, String one,String two, String CSC1_3 ,String CSC2_3, String Tbspi,String PSMD, String cash,String CCdoUba,  String WT,String IIraUta, String visa, String MC,
			 String AE, String Other_2, String undefined_2, String ICN ,String Sbtca, String CityStateCountryZip,String TCP, String Group, String Authorization, String SubscriberPolicy_1,
			 String SubscriberPolicy_2, String FriendFamily, String PR, String IsW, String Reputation, String other_3 ,String undefined_3) throws DocumentException, IOException{
    
		PdfReader reader = new PdfReader("/Users/chaoduan/Downloads/International Patient Registration Form 2014.pdf");
		PdfStamper stamper = new PdfStamper(reader, new FileOutputStream("/Users/chaoduan/Downloads/(A)International Patient Registration Form 2014.pdf"));
		AcroFields form = stamper.getAcroFields();
		form.removeXfa();
		stamper.setFormFlattening(true);
		form.setField("Full name", fullname);
		form.setField("Address", Paddress);
		form.setField("CityCountryPostal Code", CityCoutryPostalCode);
		
		form.setField("PhoneCell", PhoneCell);
		
		form.setField("Email", Email);
		
		form.setField("Date of Birth", Dofb);
		
		form.setField("Age", age);
		
		form.setField("Place of Birth", PoB);
		
		form.setField("Sex", sex);
		form.setField("Nationality",Nationality);
		form.setField("Religion", Religion);
	    
		
		form.setField("Hispanic or Latino", HoL);
		form.setField("Not Hispanic or Latino", nHoL);
		form.setField("UnknownDeclined",UnknownDeclined);
		
		
	    form.setField("American IndianAlaska Native",AIN);
	    form.setField("Asian",Asian);
	    form.setField("Black or African American", BoAA);
	    form.setField("Native HawaiianPacific Islander", NHI);
        form.setField("Other", Other);
        form.setField("White or Caucasian", WoC);
        form.setField("UnknownDeclined_2" , UnknowD_2);
        form.setField("US Social Security Number if applicable 1", USSNia1);
        form.setField("US Social Security Number if applicable 2", USSNia2);
        form.setField("Single",Single);
        form.setField("Married",Married);
        form.setField("Divorced",Divorced);
        form.setField("Widowed",Widowed);
        form.setField("Legally Separated",LS);
	    form.setField("Registered Domestic Partner RDP",RDP);
	    form.setField("RDPDissolved",RDPDissolved);
	    form.setField("RDPWidowed",RDPWidowed);
	    form.setField("Text1",Test1);
	    form.setField("undefined",undefined);
	
	    form.setField("Occupation parent if minor",Opim);
	    form.setField("Length of Employment 1",LoE1);
	    form.setField("Length of Employment 2",LoE2);
	    form.setField("Length of Employment 3",LoE3);
	    form.setField("CityState Country 1",CT1);
	    form.setField("CityState Country 2",CT2);
	    form.setField("CityState Country 3",CT3);
	    form.setField("SpouseNext of KinRelative 1", SoK1 );
	    form.setField("SpouseNext of KinRelative 2",SoK2);
	    form.setField("CityState Country 1_2", CSC1_2);
	    form.setField("CityState Country 2_2", CSC2_2);
	    form.setField("CityState Country 3_2",CSC3_2);
	    form.setField("Local Contact Information",LCI);
	    form.setField("1",one);
	    form.setField("2",two);
	    form.setField("CityState Country 1_3",CSC1_3);
	    form.setField("CityState Country 2_3",CSC2_3);
	    form.setField("Treatment being sought Please provide information", Tbspi);
	    form.setField("Preferred SpecialistMD",PSMD);
	    form.setField("Cash",cash);
	    form.setField("CashiersTravelers CheckCheck drawn on US bank account", CCdoUba);
        form.setField("Wire Transfer",WT);
    form.setField("International Insurance requires a USbased thirdparty administrator",IIraUta);
    form.setField("Visa",visa);
    form.setField("Master Card", MC);
    form.setField("American Express", AE);
    form.setField("Other_2", Other_2);
    form.setField("undefined_2",undefined_2);
	form.setField("Insurance Company Name",ICN);
	form.setField("Send bills to claims address", Sbtca);
	form.setField("CityStateCountryZip",CityStateCountryZip);
	form.setField("Telephone Contact Person",TCP);
	form.setField("Group",Group);
	form.setField("Authorization",Authorization);
	form.setField("SubscriberPolicy 1",SubscriberPolicy_1);
	form.setField("SubscriberPolicy 2",SubscriberPolicy_2);
	form.setField("FriendFamily",FriendFamily);
	form.setField("Physician Referral",PR);
	form.setField("Internet searchUCSF Website", IsW);
	form.setField("Reputation",Reputation);
	form.setField("Other_3",other_3);
	form.setField("undefined_3",undefined_3);
		
		//System.out.println("Check");
		stamper.setFormFlattening(true);
		stamper.close();
		reader.close();
	
	}

}
