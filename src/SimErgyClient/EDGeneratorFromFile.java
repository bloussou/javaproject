package SimErgyClient;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Emergency.ED;
import Events.Time;
import Events.TimeStamp;
import Factory.FactoryCreator;
import Proba.Exp;
import Proba.Gamma;
import Proba.LogNorm;
import Proba.Uniform;

public class EDGeneratorFromFile {
	
	private ArrayList<ED> edsGenerated;
	private int numED;
	public Factory.AbstractFactory humanFactory;
	public Factory.AbstractFactory roomFactory;
	public Factory.AbstractFactory facilityFactory;
	public Time time;
	
	/**
	 * Instanciate :
	 * <li> Time </li>
	 * <li> generated EDs's list</li>
	 * <li> all the factories which are used to instanciate new pieces of the SimErgy system</li>
	 */
	public EDGeneratorFromFile(){
		this.time = Time.getInstanceTime();
		this.edsGenerated = new ArrayList<ED>();
		this.numED = -1;
		this.humanFactory = FactoryCreator.getFactory("HUMAN");
		this.roomFactory = FactoryCreator.getFactory("ROOM");
		this.facilityFactory = FactoryCreator.getFactory("FACILITY");
	}
	
	/**
	 * Extract all the numbers in the String str from the index setOff, and return them in an ArrayList<Double> 
	 * @param str
	 * @param setOff
	 * @return
	 */
	public static ArrayList<Double> getNumbersFromLine(String str, int setOff){
		ArrayList<Double> values = new ArrayList<Double>();
		String myNumbers = "";
		boolean digitDetected = false;
	    
	    for (int i = setOff; i < str.length(); i++) {
	        if (Character.isDigit(str.charAt(i)) || str.charAt(i)=='.') {
	        	digitDetected = true;
	            myNumbers += str.charAt(i);
	        } else if (digitDetected) {
	            digitDetected = false;
	            values.add(Double.parseDouble(myNumbers));
	            myNumbers = "";
	        }
	    }
		if(digitDetected){values.add(Double.parseDouble(myNumbers));}
	    
		return values;
	}
		
	/**
	 * Extract the next word in the string from the index setOff
	 * @param str
	 * @param setOff
	 * @return
	 */
	public static String getWordFromLine(String str, int setOff){
		String returnValue = "";
		int i = setOff;
		boolean wordFound = false;
		
		while(i< str.length() && !(str.charAt(i)==' ' && wordFound) && !(str.charAt(i)=='(')&& !(str.charAt(i)=='\n')){
			if(!(str.charAt(i)==' ') && !(str.charAt(i)==':')){
				wordFound = true;
				returnValue+=str.charAt(i);
			}
			i++;
		}
		
		return returnValue;
	}
	
	/**
	 * Interprete a line and creates the corresponding elements in the system
	 * @param line
	 */
	public void generateFromLine(String line){
		
//--ED---------------------------------------------------------------------------------------------- 		
		if (line.startsWith("NEW ED")){
				System.out.println("EDGGENERATORFROMFILE --- creating New Ed");
				this.edsGenerated.add(new ED("ED"+this.numED,"UnknownCountry"));
				this.numED ++;
				}
		else if (line.startsWith("ED - name") && !this.edsGenerated.isEmpty()) {
				System.out.println("EDGGENERATORFROMFILE --- New ed name");
				this.edsGenerated.get(this.numED).setName(EDGeneratorFromFile.getWordFromLine(line, 11));
		}
		else if (line.startsWith("ED - country") && !this.edsGenerated.isEmpty()){
				System.out.println("EDGGENERATORFROMFILE --- New ed country");
				this.edsGenerated.get(this.numED).setCountry(EDGeneratorFromFile.getWordFromLine(line, 14));
		}
//--RH---------------------------------------------------------------------------------------------- 
		else if (line.startsWith("RH - number of physicians") && !this.edsGenerated.isEmpty()){
				int numPhysicians = Integer.parseInt(Double.toString(EDGeneratorFromFile.getNumbersFromLine(line, 0).get(0)));
				System.out.println("EDGGENERATORFROMFILE --- creating " + numPhysicians + " physicians");
				if (numPhysicians>0){
					for (int i = 0; i < numPhysicians ; i++) {
						this.humanFactory.getStaff( this.edsGenerated.get(this.numED),"PHYSICIAN");
					}
				}
				else {System.out.println("Problème avec le nb de physicians de " + this.edsGenerated.get(this.numED).getName());}
		}
		else if (line.startsWith("RH - number of nurses") && !this.edsGenerated.isEmpty()){
				int numNurses = Integer.parseInt(Double.toString(EDGeneratorFromFile.getNumbersFromLine(line, 0).get(0)));
				System.out.println("EDGGENERATORFROMFILE --- creating " + numNurses + " nurses");
				if (numNurses>0){
					for (int i = 0; i < numNurses ; i++) {
						this.humanFactory.getStaff( this.edsGenerated.get(this.numED), "NURSE");
					}
				}
				else {System.out.println("Problème avec le nb de nurses de " + this.edsGenerated.get(this.numED).getName());}
		}
		else if (line.startsWith("RH - number of transporters") && !this.edsGenerated.isEmpty()){
				int numTransporters = Integer.parseInt(Double.toString(EDGeneratorFromFile.getNumbersFromLine(line, 0).get(0)));
				System.out.println("EDGGENERATORFROMFILE --- creating " + numTransporters + " transporters");
				if (numTransporters>0){
					for (int i = 0; i < numTransporters ; i++) {
						this.humanFactory.getStaff( this.edsGenerated.get(this.numED), "TRANSPORTER");
					}
				}
				else {System.out.println("Problème avec le nb de transporters de " + this.edsGenerated.get(this.numED).getName());}
		}
//--FACILITIES---------------------------------------------------------------------------------------------- 
		else if (line.startsWith("Facilities - number of stretchers") && !this.edsGenerated.isEmpty()){
				int numStretchers = Integer.parseInt(Double.toString(EDGeneratorFromFile.getNumbersFromLine(line, 0).get(0)));
				System.out.println("EDGGENERATORFROMFILE --- creating " + numStretchers + " stretchers");
				if (numStretchers>0){
					for (int i = 0; i < numStretchers ; i++) {
						this.facilityFactory.getFacility(this.edsGenerated.get(this.numED), "FACILITY");
					}
				}
				else {System.out.println("Problème avec le nb de stretchers de " + this.edsGenerated.get(this.numED).getName());}
		}
//--WAITING AND CONSULTATION ROOMS---------------------------------------------------------------------------------------------- 
		else if (line.startsWith("Rooms - number of WaitingRooms") && !this.edsGenerated.isEmpty()){
				int numWR = Integer.parseInt(Double.toString(EDGeneratorFromFile.getNumbersFromLine(line, 0).get(0)));
				if (numWR>0){
					for (int i = 0; i < numWR ; i++) {
						this.roomFactory.getRoom(this.edsGenerated.get(this.numED), "WAITINGROOM");
					}
				}
				else {System.out.println("Problème avec le nb de waitingRooms de " + this.edsGenerated.get(this.numED).getName());}
		}
		else if (line.startsWith("Rooms - number of ShockRooms") && !this.edsGenerated.isEmpty()){
				int numSR = Integer.parseInt(Double.toString(EDGeneratorFromFile.getNumbersFromLine(line, 0).get(0)));
				if (numSR>0){
					for (int i = 0; i < numSR ; i++) {
						this.roomFactory.getRoom( this.edsGenerated.get(this.numED), "SHOCKROOM");
					}
				}
				else {System.out.println("Problème avec le nb de shockRooms de " + this.edsGenerated.get(this.numED).getName());}
		}
		else if (line.startsWith("Rooms - number of BoxRooms") && !this.edsGenerated.isEmpty()){
				int numBR = Integer.parseInt(Double.toString(EDGeneratorFromFile.getNumbersFromLine(line, 0).get(0)));
				if (numBR>0){
					for (int i = 0; i < numBR ; i++) {
						this.roomFactory.getRoom(this.edsGenerated.get(this.numED), "BOXROOM");
					}
				}
				else {System.out.println("Problème avec le nb de boxRooms de " + this.edsGenerated.get(this.numED).getName());}
		}
//--TESTROOMS---------------------------------------------------------------------------------------------- 
		else if (line.startsWith("Rooms - number of BloodTestRooms") && !this.edsGenerated.isEmpty()){
				int numBloodR = Integer.parseInt(Double.toString(EDGeneratorFromFile.getNumbersFromLine(line, 0).get(0)));
				if (numBloodR>0){
					for (int i = 0; i < numBloodR ; i++) {
						this.roomFactory.getRoom(this.edsGenerated.get(this.numED), "BLOODROOM");
					}
				}
				else {System.out.println("Problème avec le nb de bloodRooms de " + this.edsGenerated.get(this.numED).getName());}
		}
		else if (line.startsWith("Rooms - number of MRITestRooms") && !this.edsGenerated.isEmpty()){
				int numMRIR = Integer.parseInt(Double.toString(EDGeneratorFromFile.getNumbersFromLine(line, 0).get(0)));
				if (numMRIR>0){
					for (int i = 0; i < numMRIR ; i++) {
						this.roomFactory.getRoom( this.edsGenerated.get(this.numED), "MRIROOM");
					}
				}
				else {System.out.println("Problème avec le nb de mriRooms de " + this.edsGenerated.get(this.numED).getName());}
		}		
		else if (line.startsWith("Rooms - number of RadioTestRooms") && !this.edsGenerated.isEmpty()){
				int numRadioR = Integer.parseInt(Double.toString(EDGeneratorFromFile.getNumbersFromLine(line, 0).get(0)));
				if (numRadioR>0){
					for (int i = 0; i < numRadioR ; i++) {
						this.roomFactory.getRoom( this.edsGenerated.get(this.numED), "RADIOROOM");
					}
				}
				else {System.out.println("Problème avec le nb de radioRooms de " + this.edsGenerated.get(this.numED).getName());}
		}
//--PATIENTS---------------------------------------------------------------------------------------------- 
		else if (line.startsWith("Patients") && !this.edsGenerated.isEmpty()){
			
				ArrayList<Double> dataNum = EDGeneratorFromFile.getNumbersFromLine(line, 12);
				String sevLevel = EDGeneratorFromFile.getWordFromLine(line, 8);
				int endTime = Integer.parseInt(Double.toString(dataNum.get(0)));

				String distribution = EDGeneratorFromFile.getWordFromLine(line, line.indexOf("distribution")+12);
				TimeStamp nextArrivalTime;
				
				
				if (sevLevel.equalsIgnoreCase("L1") || sevLevel.equalsIgnoreCase("L2") || sevLevel.equalsIgnoreCase("L3") || sevLevel.equalsIgnoreCase("L4") || sevLevel.equalsIgnoreCase("L5")){
					if(distribution.equalsIgnoreCase("UNIFORM") && dataNum.size()==3){
						int minLap = Integer.parseInt(Double.toString(dataNum.get(1)));
						int maxLap = Integer.parseInt(Double.toString(dataNum.get(2)));
						int durationBeforeNextArrival = (int) Uniform.randSample(minLap, maxLap);
						nextArrivalTime = new TimeStamp(durationBeforeNextArrival);
						while(nextArrivalTime.getTimeStamp()<=endTime){
							this.humanFactory.getPatient(this.getEdsGenerated().get(this.numED), sevLevel, nextArrivalTime);
							durationBeforeNextArrival = (int) Uniform.randSample(minLap, maxLap);
							nextArrivalTime = new TimeStamp(nextArrivalTime.getTimeStamp() + durationBeforeNextArrival);
						}
					}
					else if (distribution.equalsIgnoreCase("EXP") && dataNum.size()==2){
						int lambda = Integer.parseInt(Double.toString(dataNum.get(1)));
						int durationBeforeNextArrival = (int) Exp.randSample(lambda);
						nextArrivalTime = new TimeStamp(durationBeforeNextArrival);
						while(nextArrivalTime.getTimeStamp()<=endTime){
							this.humanFactory.getPatient(this.getEdsGenerated().get(this.numED), sevLevel, nextArrivalTime);
							durationBeforeNextArrival = (int) Exp.randSample(lambda);
							nextArrivalTime = new TimeStamp(nextArrivalTime.getTimeStamp() + durationBeforeNextArrival);
						}
					}
					else if (distribution.equalsIgnoreCase("GAMMA") && dataNum.size()==3){
						int K = Integer.parseInt(Double.toString(dataNum.get(1)));
						int T = Integer.parseInt(Double.toString(dataNum.get(2)));
						int durationBeforeNextArrival = (int) Gamma.randSample(K, T);
						nextArrivalTime = new TimeStamp(durationBeforeNextArrival);
						while(nextArrivalTime.getTimeStamp()<=endTime){
							this.humanFactory.getPatient(this.getEdsGenerated().get(this.numED), sevLevel, nextArrivalTime);
							durationBeforeNextArrival = (int) Gamma.randSample(K, T);
							nextArrivalTime = new TimeStamp(nextArrivalTime.getTimeStamp() + durationBeforeNextArrival);
						}
					}
					else if (distribution.equalsIgnoreCase("LOGNORM") && dataNum.size()==3){
						int E = Integer.parseInt(Double.toString(dataNum.get(1)));
						int S = Integer.parseInt(Double.toString(dataNum.get(2)));
						int durationBeforeNextArrival = (int) LogNorm.randSample(E, S);
						nextArrivalTime = new TimeStamp(durationBeforeNextArrival);
						while(nextArrivalTime.getTimeStamp()<=endTime){
							this.humanFactory.getPatient(this.getEdsGenerated().get(this.numED), sevLevel, nextArrivalTime);
							durationBeforeNextArrival = (int) LogNorm.randSample(E, S);
							nextArrivalTime = new TimeStamp(nextArrivalTime.getTimeStamp() + durationBeforeNextArrival);
						}
					}
					else{
						System.out.println("La loi de distribution ou son paramétrage n'est pas valide");
					}
				}
				else {System.out.println("Problème avec le degré de sévérité des patients pour l'ed " + this.edsGenerated.get(this.numED).getName());}
		}	
	

	}
	
	/**
	 * Read the initialisation file (filename) and treat it line by line until the end
	 * @param fileName
	 * @return
	 */
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
				this.generateFromLine(line);
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
		return this.edsGenerated;
		}
		
}

	
	public ArrayList<ED> getEdsGenerated() {
		return edsGenerated;
	}
	public void setEdsGenerated(ArrayList<ED> edsGenerated) {
		this.edsGenerated = edsGenerated;
	}
	
	


}
			
			
