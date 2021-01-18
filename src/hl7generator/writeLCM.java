package hl7generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;


public class writeLCM {
	
	protected static Properties Prop=null;
	protected static FileInputStream LoadFile=null;
	
	public static void ReadingPropertiesFile (String FilePath) throws IOException{
		Prop=new Properties();
		LoadFile= new FileInputStream(FilePath);
		Prop.load(LoadFile);
	}
	
	 public static void main(String [] args) throws IOException {
			String fileName = "C:\\Users\\Hesham.Gharib\\Desktop\\cfg\\route.cfg";
			File file = new File(fileName);
		// ReadingPropertiesFile("./ReadFrom/Broker.properties");
			// Assume default encoding.
			FileWriter fileWriter = new FileWriter(file,true);

			// Always wrap FileWriter in BufferedWriter.
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	        for (int i = 1; i <=1000; i++) {
				// The name of the file to open.
	        	
				try {
					// Note that write() does not automatically
					// append a newline character.
					bufferedWriter.append(
							"Entity_"+i+"_Name=AutoRule"+i+"\r\n" + 
							"Entity_"+i+"_ActionWhen=1\r\n" + 
							"Entity_"+i+"_IncludePriors=1\r\n" + 
							"Entity_"+i+"_StudiesCount=50\r\n" + 
							"Entity_"+i+"_PriorRange=30 Years\r\n" + 
							"Entity_"+i+"_RouteOrg=1\r\n" + 
							"Entity_"+i+"_PurgeStudy=1\r\n" + 
							"Entity_"+i+"_ConditonsCount=1\r\n" + 
							"Condition_"+i+"_0_Key=Others\r\n" + 
							"Condition_"+i+"_0_Value=x\r\n" + 
							"Condition_"+i+"_0_Compare=1\r\n" + 
							"Condition_"+i+"_0_Tag=1048608");
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

