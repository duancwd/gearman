package thread;

import java.io.IOException;
import java.lang.Thread;

import Client.ClientAppNote;
import Client.ClientEmail;
import net.johnewart.gearman.common.interfaces.GearmanFunction;  

public class ThreadCreator {
	
	
	
	 public static void main(String... args) throws IOException
	 { 
		 
		 
		 Thread threadAppTime = new Thread(ClientAppNote.showTimer());  
		 Thread threadEmailTime = new Thread(ClientEmail.showTimer()); 
		 
    	 Thread threadAppSub = new Thread(ClientAppNote.submission()); 
    	 Thread threadEmailSub = new Thread(ClientEmail.submission()); 
    	 
    	 
    	 threadAppTime.start();
    	 threadAppSub.start();
    	 threadEmailTime.start();
    	 threadEmailSub.start();
		 
		 
	
		 
		 
	    
	    //Thread thread3 = new Thread( TimeRemoteTask.showTimer() ); 
	  
		
	    
	    // thread3.start();
		
	}
	

}
	
	 
