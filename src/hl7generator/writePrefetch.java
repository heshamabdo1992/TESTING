package hl7generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;


public class writePrefetch {
	
	protected static Properties Prop=null;
	protected static FileInputStream LoadFile=null;
	
	public static void ReadingPropertiesFile (String FilePath) throws IOException{
		Prop=new Properties();
		LoadFile= new FileInputStream(FilePath);
		Prop.load(LoadFile);
	}
	
	 public static void main(String [] args) throws IOException {
			String fileName = "C:\\Users\\Hesham.Gharib\\Desktop\\cfg\\pf.cfg";
			File file = new File(fileName);
		// ReadingPropertiesFile("./ReadFrom/Broker.properties");
			// Assume default encoding.
			FileWriter fileWriter = new FileWriter(file,true);

			// Always wrap FileWriter in BufferedWriter.
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	        for (int i = 2; i <=1000; i++) {
				// The name of the file to open.
	        	
				try {
					// Note that write() does not automatically
					// append a newline character.
					bufferedWriter.append("[Rule_"+i+"]\r\n" + 
							"RuleName=PreFetchRule"+i+"\r\n" + 
							"PrefetchDate=1\r\n" + 
							"Trigger=1\r\n" + 
							"IncOrig=True\r\n" + 
							"CheckExt=True\r\n" + 
							"Condition=1\r\n" + 
							"MaxPriors=50\r\n" + 
							"PriorRange=111 Years\r\n" + 
							"HostName=Sender183_1#=$Sender183_1\r\n" + 
							"ToPACS=Sender183_2#=$192.168.5.80\r\n" + 
							"FromPACS=Sender183_3#=$192.168.5.80\r\n" + 
							"PrefetchBy=1048608\r\n" + 
							"otherTagChecked=True\r\n" + 
							"FromTime=00:00\r\n" + 
							"ToTime=23:30");
					bufferedWriter.newLine();
					
					//Always close files.
					
				} catch (IOException ex) {
					System.out.println("Error writing to file '" + fileName + "'");
					// Or we could just do this:
					// ex.printStackTrace();
				} 
				
				
			}
	        bufferedWriter.close();
	        fileWriter.close();
	    }
}

