package SimErgyClient;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Emergency.ED;
import Events.Time;
import Factory.FactoryCreator;

public class EDGeneratorFromFile {
	
	private ArrayList<ED> edsGenerated;
	private int numED;
	public Factory.AbstractFactory humanFactory;
	public Factory.AbstractFactory roomFactory;
	public Factory.AbstractFactory facilityFactory;
	public Time time;
	
	public EDGeneratorFromFile(){
		this.time = Time.getInstanceTime();
		this.edsGenerated = new ArrayList<ED>();
		this.numED = -1;
		this.humanFactory = FactoryCreator.getFactory("HUMAN");
		this.roomFactory = FactoryCreator.getFactory("ROOM");
		this.facilityFactory = FactoryCreator.getFactory("FACILITY");
	}
	
	/**
	 * Extract all the numbers in the String str from the index setOff, and return them in an ArrayList<StringBuilder> 
	 * @param str
	 * @param setOff
	 * @return
	 */
	public ArrayList<String> getNumbersFromLine(String str, int setOff){
		ArrayList<String> values = new ArrayList<String>();
		String myNumbers = "";
		boolean digitDetected = false;
	    
	    for (int i = setOff; i < str.length(); i++) {
	        if (Character.isDigit(str.charAt(i))) {
	        	digitDetected = true;
	            myNumbers += str.charAt(i);
	        } else if (digitDetected) {
	            digitDetected = false;
	            values.add(myNumbers);
	            myNumbers = "";
	        }
	    }
		if(digitDetected){values.add(myNumbers);}
	    
		return values;
	}
		
	/**
	 * Extract the next word in the string from the index setOff
	 * @param str
	 * @param setOff
	 * @return
	 */
	public String getWordFromLine(String str, int setOff){
		String returnValue = "";
		int i = setOff;
		boolean wordFound = false;
		
		while(i< str.length() && !(str.charAt(i)==' ' && wordFound) && !(str.charAt(i)=='\n')){
			if(!(str.charAt(i)==' ') && !(str.charAt(i)==':')){
				wordFound = true;
				returnValue+=str.charAt(i);
			}
			i++;
		}
		
		return returnValue;
	}
	
	public void generateFromLine(String line){
		
//--ED---------------------------------------------------------------------------------------------- 		
		if (line.startsWith("NEW ED")){
				this.edsGenerated.add(new ED("ED"+this.numED,"UnknownCountry"));
				this.numED ++;
				}
		else if (line.startsWith("ED - name") && !this.edsGenerated.isEmpty()) {
				this.edsGenerated.get(this.numED).setName(this.getWordFromLine(line, 11));
		}
		else if (line.startsWith("ED - country") && !this.edsGenerated.isEmpty()){
			
				this.edsGenerated.get(this.numED).setCountry(this.getWordFromLine(line, 14));
		}
//--RH---------------------------------------------------------------------------------------------- 
		else if (line.startsWith("RH - number of physicians") && !this.edsGenerated.isEmpty()){
				int numPhysicians = Integer.parseInt(this.getNumbersFromLine(line, 0).get(0));
				if (numPhysicians>0){
					for (int i = 0; i < numPhysicians ; i++) {
						this.humanFactory.getStaff("PHYSICIAN", this.edsGenerated.get(this.numED));
					}
				}
				else {System.out.println("Probl�me avec le nb de physicians de " + this.edsGenerated.get(this.numED).getName());}
		}
		else if (line.startsWith("RH - number of nurses") && !this.edsGenerated.isEmpty()){
				int numNurses = Integer.parseInt(this.getNumbersFromLine(line, 0).get(0));
				if (numNurses>0){
					for (int i = 0; i < numNurses ; i++) {
						this.humanFactory.getStaff("NURSE", this.edsGenerated.get(this.numED));
					}
				}
				else {System.out.println("Probl�me avec le nb de nurses de " + this.edsGenerated.get(this.numED).getName());}
		}
		else if (line.startsWith("RH - number of transporters") && !this.edsGenerated.isEmpty()){
				int numTransporters = Integer.parseInt(this.getNumbersFromLine(line, 0).get(0));
				if (numTransporters>0){
					for (int i = 0; i < numTransporters ; i++) {
						this.humanFactory.getStaff("TRANSPORTER", this.edsGenerated.get(this.numED));
					}
				}
				else {System.out.println("Probl�me avec le nb de transporters de " + this.edsGenerated.get(this.numED).getName());}
		}
//--FACILITIES---------------------------------------------------------------------------------------------- 
		else if (line.startsWith("Facilities - number of stretchers") && !this.edsGenerated.isEmpty()){
				int numStretchers = Integer.parseInt(this.getNumbersFromLine(line, 0).get(0));
				if (numStretchers>0){
					for (int i = 0; i < numStretchers ; i++) {
						this.facilityFactory.getFacility("FACILITY", this.edsGenerated.get(this.numED));
					}
				}
				else {System.out.println("Probl�me avec le nb de stretchers de " + this.edsGenerated.get(this.numED).getName());}
		}
//--WAITING AND CONSULTATION ROOMS---------------------------------------------------------------------------------------------- 
		else if (line.startsWith("Rooms - number of WaitingRooms") && !this.edsGenerated.isEmpty()){
				int numWR = Integer.parseInt(this.getNumbersFromLine(line, 0).get(0));
				if (numWR>0){
					for (int i = 0; i < numWR ; i++) {
						this.roomFactory.getRoom("WAITINGROOM", this.edsGenerated.get(this.numED));
					}
				}
				else {System.out.println("Probl�me avec le nb de waitingRooms de " + this.edsGenerated.get(this.numED).getName());}
		}
		else if (line.startsWith("Rooms - number of ShockRooms") && !this.edsGenerated.isEmpty()){
				int numSR = Integer.parseInt(this.getNumbersFromLine(line, 0).get(0));
				if (numSR>0){
					for (int i = 0; i < numSR ; i++) {
						this.roomFactory.getRoom("SHOCKROOM", this.edsGenerated.get(this.numED));
					}
				}
				else {System.out.println("Probl�me avec le nb de shockRooms de " + this.edsGenerated.get(this.numED).getName());}
		}
		else if (line.startsWith("Rooms - number of BoxRooms") && !this.edsGenerated.isEmpty()){
				int numBR = Integer.parseInt(this.getNumbersFromLine(line, 0).get(0));
				if (numBR>0){
					for (int i = 0; i < numBR ; i++) {
						this.roomFactory.getRoom("BOXROOM", this.edsGenerated.get(this.numED));
					}
				}
				else {System.out.println("Probl�me avec le nb de boxRooms de " + this.edsGenerated.get(this.numED).getName());}
		}
//--TESTROOMS---------------------------------------------------------------------------------------------- 
		else if (line.startsWith("Rooms - number of BloodTestRooms") && !this.edsGenerated.isEmpty()){
				int numBloodR = Integer.parseInt(this.getNumbersFromLine(line, 0).get(0));
				if (numBloodR>0){
					for (int i = 0; i < numBloodR ; i++) {
						this.roomFactory.getRoom("BLOODROOM", this.edsGenerated.get(this.numED));
					}
				}
				else {System.out.println("Probl�me avec le nb de bloodRooms de " + this.edsGenerated.get(this.numED).getName());}
		}
		else if (line.startsWith("Rooms - number of MRITestRooms") && !this.edsGenerated.isEmpty()){
				int numMRIR = Integer.parseInt(this.getNumbersFromLine(line, 0).get(0));
				if (numMRIR>0){
					for (int i = 0; i < numMRIR ; i++) {
						this.roomFactory.getRoom("MRIROOM", this.edsGenerated.get(this.numED));
					}
				}
				else {System.out.println("Probl�me avec le nb de mriRooms de " + this.edsGenerated.get(this.numED).getName());}
		}		
		else if (line.startsWith("Rooms - number of RadioTestRooms") && !this.edsGenerated.isEmpty()){
				int numRadioR = Integer.parseInt(this.getNumbersFromLine(line, 0).get(0));
				if (numRadioR>0){
					for (int i = 0; i < numRadioR ; i++) {
						this.roomFactory.getRoom("RADIOROOM", this.edsGenerated.get(this.numED));
					}
				}
				else {System.out.println("Probl�me avec le nb de radioRooms de " + this.edsGenerated.get(this.numED).getName());}
		}
//--PATIENTS---------------------------------------------------------------------------------------------- 
		else if (line.startsWith("Patients") && !this.edsGenerated.isEmpty()){
				ArrayList<String> dataNum = this.getNumbersFromLine(line, 12);
				int numPatientsL1 = Integer.parseInt(this.getNumbersFromLine(line, 0).get(0));
				int startMinute = Integer.parseInt(this.getNumbersFromLine(line, 0).get(1));
				int endMinute = Integer.parseInt(this.getNumbersFromLine(line, 0).get(2));
				String severityLevel = this.getWordFromLine(line, 8);
				String distribution = this.getWordFromLine(line, line.indexOf("distribution")+12);
				
				if (numPatientsL1>0){
					for (int i = 0; i < numPatientsL1 ; i++) {
						this.humanFactory.getPatient(this.getEdsGenerated().get(this.numED), severityLevel, arrivalTime)("RADIOROOM", this.edsGenerated.get(this.numED));
					}
				}
				else {System.out.println("Probl�me avec le nb de radioRooms de " + this.edsGenerated.get(this.numED).getName());}
		}	
	
	
	
	

	
	
	}
	
	
	public ArrayList<ED> edsGenerating(String fileName){
		
		FileReader file = null; 
		BufferedReader reader = null; 
		
		try { 
			file = new FileReader(fileName);
			// a FileReader for reading byte by byte 
			reader = new BufferedReader(file); 
			// wrapping a FileReader into a BufferedReader for reading line by line 
			String line = ""; 
			while ((line = reader.readLine()) != null) {
			}
		} 
		catch (Exception e) {throw new RuntimeException(e);} 
		finally {
			if (reader != null) {
				try {reader.close();} 
				catch (IOException e) {} 
			} 
			if (file != null) {
				try {file.close();} 
				catch (IOException e) {} 
				}			
		return edsGenerated;
		}
		
}

	
	
	public ArrayList<ED> getEdsGenerated() {
		return edsGenerated;
	}

	public void setEdsGenerated(ArrayList<ED> edsGenerated) {
		this.edsGenerated = edsGenerated;
	}
	
	
	


}
			
			
