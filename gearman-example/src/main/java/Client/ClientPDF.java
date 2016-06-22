package Client;

import net.johnewart.gearman.client.NetworkGearmanClient;
import net.johnewart.gearman.common.JobStatus;
import net.johnewart.gearman.common.events.GearmanClientEventListener;
import net.johnewart.gearman.constants.JobPriority;
import net.johnewart.gearman.exceptions.NoServersAvailableException;
import net.johnewart.gearman.exceptions.WorkException;
import net.johnewart.gearman.exceptions.WorkExceptionException;
import net.johnewart.gearman.exceptions.WorkFailException;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import Task.SendMailTLS;



public class ClientPDF {
    private ClientPDF() { }

 
 static int count = 0;
 static int flag = 1;
 
 
 
	
	
    
    
    
    
    
 
    public static void main(String... args) throws IOException
    {
   
    	submission("wahaha"); 
      
    
    	 
    	    
    	
    }
    	
    	
    public static Runnable submission(String input) throws IOException{
        GearmanClientEventListener eventListener = new GearmanClientEventListener() {
            @Override
            public void handleWorkData(String jobHandle, byte[] data) {
                System.err.println("data update for job " + jobHandle);
            }

            @Override
            public void handleWorkWarning(String jobHandle, byte[] warning) {
                System.err.println(" warning for job " + jobHandle);
            }

            @Override
            public void handleWorkStatus(String jobHandle, JobStatus jobStatus) {
                System.err.println("status update for job " + jobHandle);
                System.err.println("Status: " + jobStatus.getNumerator() + " / " + jobStatus.getDenominator());
            }
        };

        try {
            byte data[] = input.getBytes();
            NetworkGearmanClient client = new NetworkGearmanClient("127.0.0.1", 4730);
            client.registerEventListener(eventListener);
            while(true){
            	//System.out.println("true = flag = " +flag);
            	
            while(flag ==1)
            {
           
                try {
                    //byte[] result = client.submitJob("reverse", data, JobPriority.NORMAL);
                    //System.err.println("Result: " + new String(result));
                	byte[] App = client.submitJob("PDFrunG", data, JobPriority.NORMAL);
                	 flag =2;
                	 System.err.println("PDF: " + new String(App));
                 	
                } 
              
                catch (WorkException e) {
                    if(e instanceof WorkFailException)
                        System.err.println("Job " + e.getJobHandle() + " failed.");
                    else
                        System.err.println("Job " + e.getJobHandle() + " exception: " + ((WorkExceptionException) e).getMessage());

                    e.printStackTrace();
                }
               

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Thread.sleep(1000);
            }
        }  catch (NoServersAvailableException nsae) {
            System.err.println("Can't connect to any servers.");
        } catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
    }
}
