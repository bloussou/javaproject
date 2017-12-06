package SimErgyClient;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Emergency.ED;

public class EDGeneratorFromFile {
	
	private ArrayList<ED> edsGenerated;
	
	public EDGeneratorFromFile(){
		
		this.edsGenerated = new ArrayList<ED>();
	}
	
	/**
	 * Extract all the numbers in the String str from the index setOff, and return them in an ArrayList<StringBuilder> 
	 * @param str
	 * @param setOff
	 * @return
	 */
	public ArrayList<StringBuilder> getNumbersFromLine(String str, int setOff){
		ArrayList<StringBuilder> values = new ArrayList<StringBuilder>();
		StringBuilder myNumbers = new StringBuilder();
		boolean digitDetected = false;
	    
	    for (int i = setOff; i < str.length(); i++) {
	        if (Character.isDigit(str.charAt(i))) {
	        	digitDetected = true;
	            myNumbers.append(str.charAt(i));
	            System.out.println("myNuber : " + myNumbers);
	        } else if (digitDetected) {
	            digitDetected = false;
	            values.add(myNumbers);
	            myNumbers = new StringBuilder();
	        }
	    }
		if(digitDetected){values.add(myNumbers);}
	    
		return values;
	}
		
	public String getWordFromLine(String str, int setOff){
		String returnValue = "";
		int i = setOff;
		boolean wordFound = false;
		
		while(i< str.length() && !(str.charAt(i)==' ' && wordFound) && !(str.charAt(i)=='\n')){
			if(!(str.charAt(i)==' ')){
				wordFound = true;
				returnValue+=str.charAt(i);
			}
			i++;
		}
		
		return returnValue;
	}
	
	public void generateFromLine(String line){
		
		if (line.startsWith("NEW ED")){
			this.edsGenerated.add(new ED("ED","UnknownCountry"));
		}
		else if (line.startsWith("ED - name") && !this.edsGenerated.isEmpty()) {
		}
		else if (line.startsWith("ED - country") && !this.edsGenerated.isEmpty()){
			
		}
		else if (line.startsWith("RH - number of physicians") && !this.edsGenerated.isEmpty()){
			
		}
		else if (line.startsWith("RH - number of nurses") && !this.edsGenerated.isEmpty()){
			
		}
		else if (line.startsWith("RH - number of transporters") && !this.edsGenerated.isEmpty()){
			
		}
		else if (line.startsWith("Facilities - number of stretchers") && !this.edsGenerated.isEmpty()){
			
		}
		else if (line.startsWith("Rooms - number of WaitingRooms") && !this.edsGenerated.isEmpty()){
			
		}
		else if (line.startsWith("Rooms - number of ShockRooms") && !this.edsGenerated.isEmpty()){
			
		}
		else if (line.startsWith("Rooms - number of BoxRooms") && !this.edsGenerated.isEmpty()){
			
		}
		else if (line.startsWith("Rooms - number of BloodTestRooms") && !this.edsGenerated.isEmpty()){
			
		}
		else if (line.startsWith("Rooms - number of MRITestRooms") && !this.edsGenerated.isEmpty()){
			
		}		
		else if (line.startsWith("Rooms - number of RadioTestRooms") && !this.edsGenerated.isEmpty()){
			
		}
		else if (line.startsWith("Rooms - number of WaitingRooms") && !this.edsGenerated.isEmpty()){
			
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
	
	
	
	public static void main(String[] args) {
	    String str = "abc d 1234567890pqr 54897";

	    EDGeneratorFromFile EDG = new EDGeneratorFromFile();
	    System.out.println(EDG.getNumbersFromLine(str, 0));
	    
	    String str2 = "ED - name : Régis";
	    System.out.println(EDG.getWordFromLine(str2, 11));
	}
	
	


}
			
			
