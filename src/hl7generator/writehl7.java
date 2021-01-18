package hl7generator;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;


public class writehl7 {
	
	protected static Properties Prop=null;
	protected static FileInputStream LoadFile=null;
	
	public static void ReadingPropertiesFile (String FilePath) throws IOException{
		Prop=new Properties();
		LoadFile= new FileInputStream(FilePath);
		Prop.load(LoadFile);
	}
	
	 public static void main(String [] args) throws IOException {
		 
		 ReadingPropertiesFile("./ReadFrom/Broker.properties");
		 String Receiveing_Facility=Prop.getProperty("Receiveing_Facility");
		 String	 Receiveing_Application=Prop.getProperty("Receiveing_Application");
		 String	 Sender_Facility=Prop.getProperty("Sender_Facility");
		 String	 Sender_Application=Prop.getProperty("Sender_Application");
	        for (int i = 1; i <=2; i++) {
				// The name of the file to open.
	        	
				String fileName = "//192.168.5.183/c$/In_HL7"+String.valueOf(i)+".hl7";
				try {
					// Assume default encoding.
					FileWriter fileWriter = new FileWriter(fileName);

					// Always wrap FileWriter in BufferedWriter.
					BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

					// Note that write() does not automatically
					// append a newline character.
					bufferedWriter.write(
							"MSH|^~\\&|"+Receiveing_Facility+"|"+Receiveing_Application+"|"+Sender_Facility+"|"+Sender_Application+"|||ORM^O01|ControlID0"+String.valueOf(i)+"|P|2.3.1||||||||");
					bufferedWriter.newLine();
					bufferedWriter.write(
							"PID|||PatientID"+String.valueOf(i)+"^^^ADT1||PatientName"+String.valueOf(i)+"||19450804|M||WH|820 JORIE BLVD^^CHICAGO^IL^60523|||||||20-98-4000|||||||||||||||||||||");
					bufferedWriter.newLine();
					bufferedWriter.write(
							"PV1||E|ED||||1234^WEAVER^TIMOTHY^P^^DR|5101^NELL^FREDERICK^P^^DR|||||||||||V100^^^ADT1|||||||||||||||||||||||||200008201100|||||||V|");
					bufferedWriter.newLine();
					bufferedWriter.write(
							"ORC|NW|A100Z^MESA_ORDPLC|||||1^once^^^^S||200082011000|^ROSEWOOD^RANDOLPH||7101^ESTRADA^JAIME^P^^DR||(314)555-1212|200082011000||922229-10^IHE-RAD^IHE-CODE-231||");
					bufferedWriter.newLine();
					bufferedWriter.write(
							"OBR|1|A100Z^MESA_ORDPLC||P1^Procedure 1^ERL_MESA|||||||||xxx||Radiology^^^^R|7101^ESTRADA^JAIME^P^^DR|||||||||||1^once^^^^S|||WALK|Test Case 100||||||||||A|||");

					//Always close files.
					bufferedWriter.close();
				} catch (IOException ex) {
					System.out.println("Error writing to file '" + fileName + "'");
					// Or we could just do this:
					// ex.printStackTrace();
				} 
			}
	    }
}

