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



public class ClientEmailtxt {
    private ClientEmailtxt() { }

 
 static int count = 0;
 static int flag = 0;
 
 
 public static Runnable showTimer() {
        TimerTask task1 = new TimerTask() {
            @Override
            public void run() {
            System.out.println("flag=2 = " + flag);
            	flag = 1;
            System.out.println("flag=1 = " + flag);
            	//SendMailTLS.send(null, null, null, null, null, null);
                ++count;
                System.out.println("Send email 时间=" + new Date() + " 执行了" + count + "次"); // 1次
            }
            
            
        };

        //设置执行时间
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);//每天
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        
        //定制每天的21:09:00执行，
        calendar.set(year, month, day, 0, 0, 00);
        Date date1 = calendar.getTime();
        Timer timer = new Timer();
        System.out.println(date1);
        
        int period1 = 24 * 60 * 60 * 1000;
        //每天的date时刻执行task，每隔2秒重复执行
        timer.schedule(task1, date1, period1);
        //每天的date时刻执行task, 仅执行一次
        //timer.schedule(task, date);
		return null;
    }

	
	
    
    
    
    
    
 
    public static void main(String... args) throws IOException
    {
    	Thread thread1 = new Thread(showTimer());  
    	 Thread thread2 = new Thread(submission()); 
      
    	 thread1.start();
    	 thread2.start();
    	    
    	
    }
    	
    	
    public static Runnable submission() throws IOException{
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
            byte data[] = "TimeEmailsix".getBytes();
            NetworkGearmanClient client = new NetworkGearmanClient("127.0.0.1", 4730);
            client.registerEventListener(eventListener);
            while(true){
            	//System.out.println("true = flag = " +flag);
            	
            while(flag ==1)
            {
           
                try {
                    //byte[] result = client.submitJob("reverse", data, JobPriority.NORMAL);
                    //System.err.println("Result: " + new String(result));
                	byte[] email = client.submitJob("TimeEmailtxtG", data, JobPriority.NORMAL);
                	 flag =2;
                 	
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
