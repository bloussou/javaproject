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
	
	public ArrayList<String> getValueFromLine(String line){
		ArrayList<String> value = new ArrayList<String>();
		String buf = "";
		int indexBegin;
		int indexEnd;
		int index = 0;
		
		while (line.indexOf(":",index)!=-1){
			indexBegin = line.indexOf(":",index);
			indexEnd = Math.min(line.indexOf(" ",indexBegin), line.indexOf(" ",indexBegin));
			value.add(line.substring(indexBegin, indexEnd));
					
		}
			
		return value;
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
		EDGeneratorFromFile EDG = new EDGeneratorFromFile();
		String ligne = "Rooms - number of WaitingRooms : 10";
		ArrayList<String> values = new ArrayList<String>();
		values = EDG.getValueFromLine(ligne);
		System.out.println("Hello");
		System.out.println(values);
	}



}
			
			
