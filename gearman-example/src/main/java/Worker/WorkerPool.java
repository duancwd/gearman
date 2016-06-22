package Worker;

import net.johnewart.gearman.client.NetworkGearmanWorkerPool;
import net.johnewart.gearman.common.events.WorkEvent;
import net.johnewart.gearman.common.interfaces.GearmanFunction;
import net.johnewart.gearman.net.Connection;
import thread.ThreadCreator;

import java.io.IOException;

import org.apache.commons.lang3.ArrayUtils;
import net.johnewart.gearman.common.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.DocumentException;

import Task.AppNote;
import Task.AppNoteDay;
import Task.AppNotefive;
import Task.AppNotesix;
import Task.BackupTask;
import Task.PDF;
import Task.SendMailTLS;
import Task.SendMailfive;
import Task.SendMailsix;
import Task.SendMailtxt;



public class WorkerPool {
   private static Logger LOG = LoggerFactory.getLogger(WorkerPool.class);
//
//    static class ReverseFunction implements GearmanFunction
//    {
//        @Override
//        public byte[] process(WorkEvent workEvent) {
//            Job job = workEvent.job;
//            
//            byte[] data = job.getData();
//            String function = job.getFunctionName();
//           // String function = job.appn.Sentnote(null, null, null);
//            LOG.debug("Got data for function " + function);
//           
//            ArrayUtils.reverse(data);
//            return data;
//        }
//    }
   
   

   static class TimeSendEmailG implements GearmanFunction
   {
       
       public byte[] process(WorkEvent workEvent) {
    	   Job job = workEvent.job;
    	   byte[] data = job.getData();
           String function = job.getFunctionName();
          // String function = job.appn.Sentnote(null, null, null);
           LOG.debug("Got data for function " + function);
           //ArrayUtils.reverse(data);
           //TimeSendEmail.showTimer();
           SendMailTLS.send(null, null, null, null, null, null);
           return data;
       }
      
   
   } 
       
   static class TimeAppnG implements GearmanFunction
   {
	 
       public byte[] process(WorkEvent workEvent) {
    	   Job job = workEvent.job;
    	   byte[] data = job.getData();
           String function = job.getFunctionName();
          // String function = job.appn.Sentnote(null, null, null);
           LOG.debug("Got data for function " + function);
           //ArrayUtils.reverse(data);
           AppNote.Sentnote(null, null, null);
           return data;
       }

	
   }
   static class BackupTaskG implements GearmanFunction
   {
	 
       public byte[] process(WorkEvent workEvent) {
    	   Job job = workEvent.job;
    	   byte[] data = job.getData();
           String function = job.getFunctionName();
          // String function = job.appn.Sentnote(null, null, null);
           LOG.debug("Got data for function " + function);
           //ArrayUtils.reverse(data);
          BackupTask.appbackup();
           return data;
       }

	
   }
   

   
//=====================real==============
   static class TimeEmailtxtG implements GearmanFunction
   {
	 
       public byte[] process(WorkEvent workEvent) {
    	   Job job = workEvent.job;
    	   byte[] data = job.getData();
           String function = job.getFunctionName();
          // String function = job.appn.Sentnote(null, null, null);
           LOG.debug("Got data for function " + function);
           //ArrayUtils.reverse(data);
           SendMailtxt.mysqlConnect();
           return data;
       }

	
   }   
   
   
   static class TimeEmailfiveG implements GearmanFunction
   {
	 
       public byte[] process(WorkEvent workEvent) {
    	   Job job = workEvent.job;
    	   byte[] data = job.getData();
           String function = job.getFunctionName();
          // String function = job.appn.Sentnote(null, null, null);
           LOG.debug("Got data for function " + function);
           //ArrayUtils.reverse(data);
           SendMailfive.mysqlConnect();
           return data;
       }

	
   }  
   
   
   
   static class TimeEmailsixG implements GearmanFunction
   {
	 
       public byte[] process(WorkEvent workEvent) {
    	   Job job = workEvent.job;
    	   byte[] data = job.getData();
           String function = job.getFunctionName();
          // String function = job.appn.Sentnote(null, null, null);
           LOG.debug("Got data for function " + function);
           //ArrayUtils.reverse(data);
           SendMailsix.mysqlConnect();
           return data;
       }

	
   }  
   
   
   
   static class TimeAppnDayG implements GearmanFunction
   {
	 
       public byte[] process(WorkEvent workEvent) {
    	   Job job = workEvent.job;
    	   byte[] data = job.getData();
           String function = job.getFunctionName();
          // String function = job.appn.Sentnote(null, null, null);
           LOG.debug("Got data for function " + function);
           //ArrayUtils.reverse(data);
           AppNoteDay.mysqlConnect();
           return data;
       }

	
   }
   
   
   static class TimeAppnfiveG implements GearmanFunction
   {
	 
       public byte[] process(WorkEvent workEvent) {
    	   Job job = workEvent.job;
    	   byte[] data = job.getData();
           String function = job.getFunctionName();
          // String function = job.appn.Sentnote(null, null, null);
           LOG.debug("Got data for function " + function);
           //ArrayUtils.reverse(data);
           AppNotefive.mysqlConnect();
           return data;
       }

	
   }
   static class TimeAppnsixG implements GearmanFunction
   {
	 
       public byte[] process(WorkEvent workEvent) {
    	   Job job = workEvent.job;
    	   byte[] data = job.getData();
           String function = job.getFunctionName();
          // String function = job.appn.Sentnote(null, null, null);
           LOG.debug("Got data for function " + function);
           //ArrayUtils.reverse(data);
           AppNotesix.mysqlConnect();
           return data;
       }

	
   }
//==========
	

   static class PDFrunG implements GearmanFunction
   {
	 
       public byte[] process(WorkEvent workEvent) {
    	   Job job = workEvent.job;
    	   byte[] data = job.getData();
           String function = job.getFunctionName();
          // String function = job.appn.Sentnote(null, null, null);
           LOG.debug("Got data for function " + function);
           //ArrayUtils.reverse(data);
          
			try {
				PDF.PDFrun(data);
			} catch (DocumentException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
           return data;
       }
       
       
       
       
       
       
   }

    public static void main(String... args)
    {
        try {
            byte data[] = "This is a test".getBytes();
           // byte data1[] = "This is a test".getBytes();
            NetworkGearmanWorkerPool workerPool = new NetworkGearmanWorkerPool.Builder()
                                        .threads(3)
                                        .withConnection(new Connection("127.0.0.1", 4730))
                                        .build();
           // workerPool.registerCallback("AllProject",  new All());
            workerPool.registerCallback("TimeSendEmailG",  new TimeSendEmailG());
            workerPool.registerCallback("TimeAppnG",  new TimeAppnG());
            workerPool.registerCallback("TimeEmailtxtG",  new TimeEmailtxtG());
            workerPool.registerCallback("TimeEmailfiveG",  new TimeEmailfiveG());
            workerPool.registerCallback("TimeEmailsixG",  new TimeEmailsixG());
            workerPool.registerCallback("TimeAppnDayG",  new TimeAppnDayG());
            workerPool.registerCallback("TimeAppnfiveG",  new TimeAppnfiveG());
            workerPool.registerCallback("TimeAppnsix",  new TimeAppnsixG());
            workerPool.registerCallback("BackupTaskG",  new BackupTaskG());
            workerPool.registerCallback("PDFrunG",  new PDFrunG());
            

            workerPool.doWork();
        } catch (Exception e) {
            LOG.error("Error: ", e);
        }
    }

	
}
