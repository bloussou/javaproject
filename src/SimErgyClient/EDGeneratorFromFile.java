package SimErgyClient;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Emergency.ED;

public class EDGeneratorFromFile {
	
	private String readLine = "";
	private int compteurEmptyLines = 0;
	private ArrayList<ED> edsGenerated = new ArrayList<ED>();
	
	public String readTextFileLineByLine(String fileName) {
		String returnValue = "";
		FileReader file = null; 
		BufferedReader reader = null; 
		
		try { 
			file = new FileReader(fileName);
			// a FileReader for reading byte by byte 
			reader = new BufferedReader(file); 
			// wrapping a FileReader into a BufferedReader for reading line by line 
			String line = ""; 
			while ((line = reader.readLine()) != null) {
				// read the file line by line 
				returnValue += line + "\n"; 
			}
		} catch (Exception e) {
			throw new RuntimeException(e); 
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// Ignore issues 
				} 
			} 
			if (file != null) {
				try {
					file.close();
				} catch (IOException e) {
					// Ignore issues during closing 
				} 
			}
		}
		return returnValue;
	}
	
	/**
	 * Extract all the numbers in the String str from the index setOff, and return them in an ArrayList<StringBuilder> 
	 * @param str
	 * @param setOff
	 * @return
	 */
	public ArrayList<StringBuilder> getValuesFromLine(String str, int setOff){
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
			
	public ArrayList<ED> EDSGenerating(String fileName){
		
		while (this.compteurEmptyLines < 10){
			this.readLine = this.readTextFileLineByLine(fileName);
			
			if(this.readLine.startsWith("NEW ED")){
				this.edsGenerated.add(new ED("EDSUIVANT", "CountryEDSUIVANT"));
			}
			
			if( !edsGenerated.isEmpty() && readLine.startsWith("ED - name :") ){
				
			}
			
			
		}
		
		
		return edsGenerated;
	}
	
	
	
	
	public static void main(String[] args) {
	    String str = "abc d 1234567890pqr 54897";
	    StringBuilder myNumbers = new StringBuilder();
	    for (int i = 0; i < str.length(); i++) {
	        if (Character.isDigit(str.charAt(i))) {
	            myNumbers.append(str.charAt(i));
	            System.out.println(str.charAt(i) + " is a digit.");
	        } else {
	            System.out.println(str.charAt(i) + " not a digit.");
	        }
	    }
	    System.out.println("Your numbers: " + myNumbers.toString());
	    
	    EDGeneratorFromFile EDG = new EDGeneratorFromFile();
	    System.out.println(EDG.getValuesFromLine(str, 0));
	}


}
			
			
