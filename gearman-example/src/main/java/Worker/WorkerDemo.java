package Worker;

import net.johnewart.gearman.client.NetworkGearmanWorker;
import net.johnewart.gearman.common.events.WorkEvent;
import org.apache.commons.lang3.ArrayUtils;
import net.johnewart.gearman.common.interfaces.GearmanFunction;
import net.johnewart.gearman.common.Job;
import net.johnewart.gearman.net.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Task.SendMailTLS;



public class WorkerDemo {
    private static Logger LOG = LoggerFactory.getLogger(WorkerDemo.class);

    static class TimeAppnG implements GearmanFunction
    {
        @Override
        public byte[] process(WorkEvent workEvent) {
            Job job = workEvent.job;
            byte[] data = job.getData();
            String function = job.getFunctionName();
            LOG.debug("Got data for function " + function);
            //ArrayUtils.reverse(data);
            //TimeAppn.showTimer();
            return data;
        }
    }
    
    
    static class TimeSendEmailG implements GearmanFunction
    {
        
        public byte[] process(WorkEvent workEvent) {
     	   Job job = workEvent.job;
     	  byte[] data = job.getData();
            String function = job.getFunctionName();
           // String function = job.appn.Sentnote(null, null, null);
            LOG.debug("Got data for function " + function);
            ArrayUtils.reverse(data);
            //TimeSendEmail.showTimer();
            SendMailTLS.send(null, null, null, null, null, null);
            return data;
        }
       
    
    } 


    public static void main(String... args)
    {
        try {
            byte data[] = "This is a test".getBytes();
            NetworkGearmanWorker worker = new NetworkGearmanWorker.Builder()
                                        .withConnection(new Connection("localhost", 4730))
                                        .build();

           // worker.registerCallback("reverse", new ReverseFunction());
            worker.registerCallback("TimeSendEmailG",  new TimeSendEmailG());
            worker.doWork();
        } catch (Exception e) {
            LOG.error("oops!");
        }
    }
}
