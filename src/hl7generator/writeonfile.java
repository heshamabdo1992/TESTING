package hl7generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;


public class writeonfile {
	
	protected static Properties Prop=null;
	protected static FileInputStream LoadFile=null;
	
	public static void ReadingPropertiesFile (String FilePath) throws IOException{
		Prop=new Properties();
		LoadFile= new FileInputStream(FilePath);
		Prop.load(LoadFile);
	}
	
	 public static void main(String [] args) throws IOException {
			String fileName = "C:\\Users\\Hesham.Gharib\\Desktop\\cfg\\mph.cfg";

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
					bufferedWriter.append("Entity_"+i+"_Name=Rule"+i);
					bufferedWriter.newLine();
					bufferedWriter.append("Entity_"+i+"_ActionWhen=0");
					bufferedWriter.newLine();
					bufferedWriter.append("Entity_"+i+"_ActionOn=1");
					bufferedWriter.newLine();
					bufferedWriter.append("Entity_"+i+"_ConditonsCount=0");
					bufferedWriter.newLine();
					bufferedWriter.append("Entity_"+i+"_ActionsCount=1");
					bufferedWriter.newLine();
					bufferedWriter.append("Action_"+i+"_0_Count=3");
					bufferedWriter.newLine();
					bufferedWriter.append("Action_"+i+"_0_0=ActionType=3");
					bufferedWriter.newLine();
					bufferedWriter.append("Action_"+i+"_0_1=TagName=1048592");
					bufferedWriter.newLine();
					bufferedWriter.append("Action_"+i+"_0_2=Add");
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

