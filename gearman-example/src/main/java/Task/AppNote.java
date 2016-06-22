package Task;

import java.util.Date;
import java.util.Map;

import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;

import net.johnewart.gearman.common.interfaces.GearmanFunction;

public  class AppNote {

public static GearmanFunction Sentnote  (Integer case_id, Integer update_id,String token){
	
	
	
	ApnsService service = APNS.newService()
		    .withCert("/Users/chaoduan/Downloads/PushProdCertificates.p12", "GHP2016ghp2016")
			//.withCert("/home/chaoduan/backup/project/backend/PushProdCertificates.p12", "GHP2016ghp2016")
		    .withSandboxDestination()
		    .build();
	String payload = APNS.newPayload()
            .badge(1)
            .customField("case_id", case_id)
            .customField("update_id", update_id)
            .alertBody("Case#"+case_id+"你有未完成的任务")
            .actionKey("play").build();


    //String payload = APNS.newPayload().alertBody("Case  您未完成").build();
	//String token = "15deb787 9b2360dc 5f9b4135 f94690a5 3a68bc26 7ee69058 907ba199 9f4cb109";
    //token = "7120be95 bfea6499 5fa77982 ff6e3b4e afcc592e 8f67740c 0643965a 8dd595dd";// dc
	//token = "a48e3440 7672b1d1 cfd4666c 4bdfd0d6 0fe23742 74f4af62 b7a70aab 1926bb63";
     token ="987fbaa6 6acbdbf9 b38c4e2e 1655aaf8 3e791717 d56d6c17 0e2a4cc6 715a9ba1";
	//token = "1062f3af a2f6a6e2 77ef2b75 99ca1ff0 75ad4965 18757a84 dad98432 3ad9b09a";
	//token = "a4cf647e 32fe3057 bd90ce77 26bdf9ff d99773d1 6f1a6b0e 76f6f01b ab42112d";
	
	service.push(token, payload);
	
	  Map<String, Date> inactiveDevices = service.getInactiveDevices();
	  for (String deviceToken : inactiveDevices.keySet()) {
	      Date inactiveAsOf = inactiveDevices.get(deviceToken);
	  }
	return null;
	      

}
	  public static String splt(String info){
          String f;
            String[] s1 = info.split("<");
            String[] s2 = s1[1].split(">");
         f= s2[0];
            return f;}
	
	

}

