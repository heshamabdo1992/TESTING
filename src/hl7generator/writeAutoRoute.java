package hl7generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;


public class writeAutoRoute {
	
	protected static Properties Prop=null;
	protected static FileInputStream LoadFile=null;
	
	public static void ReadingPropertiesFile (String FilePath) throws IOException{
		Prop=new Properties();
		LoadFile= new FileInputStream(FilePath);
		Prop.load(LoadFile);
	}
	
	 public static void main(String [] args) throws IOException {
			String fileName = "C:\\Users\\Hesham.Gharib\\Desktop\\cfg\\lcm1.cfg";
			File file = new File(fileName);
		// ReadingPropertiesFile("./ReadFrom/Broker.properties");
			// Assume default encoding.
			FileWriter fileWriter = new FileWriter(file,true);

			// Always wrap FileWriter in BufferedWriter.
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
//			bufferedWriter.newLine();

	        for (int i = 2; i <=1000; i++) {
				// The name of the file to open.
	        	
				try {
					// Note that write() does not automatically
					// append a newline character.
					bufferedWriter.append(
							"[Policy_"+i+"]\r\n" + 
							"Active=True\r\n" + 
							"CustomerName=Diagnostics Elite\r\n" + 
							"BranchName=Diagnostics Elite\r\n" + 
							"Name=LCMP"+i+"\r\n" + 
							"Description=\r\n" + 
							"Conditions=LCMCond"+i+"\r\n" + 
							"Action=0\r\n" + 
							"Delete_KeepDB=False\r\n" + 
							"Directroy=C:\\Patient"+i+"BK\r\n" + 
							"RemotePACS=\r\n" + 
							"DeleteAfterSend=False\r\n" + 
							"Send_DeleteFromDB=False\r\n" + 
							"StartTime=0\r\n" + 
							"EndTime=0.99998842592322\r\n" + 
							"ActionOn=0\r\n" + 
							"StudyLimit=500\r\n"+ 
							"[Condition_"+i+"]\r\n" + 
							"Name=LCMCond"+i+"\r\n" + 
							"Description=\r\n" + 
							"Field=2\r\n" + 
							"Operator=Before or Equal\r\n" + 
							"Date=\r\n" + 
							"Period=180\r\n" + 
							"Modality=All Modalities\r\n" + 
							"SizeLimit=\r\n" + 
							"PatientDir=\r\n" + 
							"DiskLimitPeriod=\r\n");
					//bufferedWriter.newLine();
					
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

