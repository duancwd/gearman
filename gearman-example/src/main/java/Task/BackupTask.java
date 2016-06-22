package Task;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;


public class BackupTask {
    
    public static void appbackup() {
    	String shpath="/home/chaoduan/backup/doc/appdatarun.sh"; 
    	Process process =null;
    	String command = "chmod 777 " + shpath;
        try {
             process = Runtime.getRuntime().exec("command");
             process.waitFor();                // Wait for the process to finish.
             System.out.println("Script executed successfully");
             InputStreamReader ir = new InputStreamReader(process.getInputStream());
             LineNumberReader input = new LineNumberReader(ir);
             String line;
            while((line = input.readLine()) != null)
                System.out.println(line);
            input.close();
            ir.close();
        } catch (IOException | InterruptedException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}