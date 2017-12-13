package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import HR.Patient;
import SimErgyClient.EDGeneratorFromFile;

public class EDGeneratorFromFileTest {

	@Test
	public void testgetNumbersFromLine() {
		String str = "Patients L1 - arriving until Time=10min, arrivals probability distribution : UNIFORM(2.5,5)\n";

	    EDGeneratorFromFile EDG = new EDGeneratorFromFile();
	    
	    assertTrue(EDG.getNumbersFromLine(str, 12).get(0)==10);
	    assertTrue(EDG.getNumbersFromLine(str, 12).get(1)==2.5);
	    assertTrue(EDG.getNumbersFromLine(str, 12).get(2)==5);
	}
	
	
	
	@Test
	public void testgetWordFromLine() {
		
	    EDGeneratorFromFile EDG = new EDGeneratorFromFile();
	    String str2 = "ED - country : France";
	    assertTrue(EDG.getWordFromLine(str2, 14).equalsIgnoreCase("France"));
	}

	
	
	@Test
	public void testgenerateFromLineED() {
		
	    EDGeneratorFromFile EDG = new EDGeneratorFromFile();

	    String line0 = "NEW ED";
	    EDG.generateFromLine(line0);
	    EDG.generateFromLine(line0);
	    assertTrue(EDG.getEdsGenerated().size()==2);
	    
	    String line1 ="ED - name : Régis";
	    String line2 = "ED - country : France";
	    EDG.generateFromLine(line2);
	    EDG.generateFromLine(line1);
	    assertTrue(EDG.getEdsGenerated().get(1).getName().equalsIgnoreCase("Régis"));
	    assertTrue(EDG.getEdsGenerated().get(1).getCountry().equalsIgnoreCase("France"));
	    
	}

	@Test
	public void testgenerateFromLineRH() {
		
	    EDGeneratorFromFile EDG = new EDGeneratorFromFile(); 
	    String line0 = "NEW ED";
	    EDG.generateFromLine(line0);
	    
	    String line1 = "RH - number of physicians 2";
	    EDG.generateFromLine(line1);	    
	    assertTrue(EDG.getEdsGenerated().get(0).getDbPhysician().get(0).size()==2);
	    assertTrue(EDG.getEdsGenerated().get(0).getDbPhysician().get(1).size()==0);
	    assertTrue(EDG.getEdsGenerated().get(0).getDbPhysician().get(2).size()==0);
	    
	    String line2 = "RH - number of nurses :2";
	    EDG.generateFromLine(line2);  
	    assertTrue(EDG.getEdsGenerated().get(0).getDbNurse().get(0).size()==2);
	    assertTrue(EDG.getEdsGenerated().get(0).getDbNurse().get(1).size()==0);
	    assertTrue(EDG.getEdsGenerated().get(0).getDbNurse().get(2).size()==0);
	    
	    String line3 = "RH - number of transporters : 3";
	    EDG.generateFromLine(line3);
	    assertTrue(EDG.getEdsGenerated().get(0).getDbTransporter().get(0).size()==3);
	    assertTrue(EDG.getEdsGenerated().get(0).getDbTransporter().get(1).size()==0);
	    assertTrue(EDG.getEdsGenerated().get(0).getDbTransporter().get(2).size()==0);

	}
	
	@Test
	public void testgenerateFromLineRooms() {
		
	    EDGeneratorFromFile EDG = new EDGeneratorFromFile(); 
	    String line0 = "NEW ED";
	    EDG.generateFromLine(line0);
	    
	    String line1 = "Rooms - number of WaitingRooms : 2";
	    EDG.generateFromLine(line1); 
	    assertTrue(EDG.getEdsGenerated().get(0).getDbWaitingRoom().get(0).size()==2);
	    assertTrue(EDG.getEdsGenerated().get(0).getDbWaitingRoom().get(1).size()==0);
	    
	    String line2 = "Rooms - number of ShockRooms : 3";
	    EDG.generateFromLine(line2);
	    assertTrue(EDG.getEdsGenerated().get(0).getDbShockRoom().get(0).size()==3);
	    assertTrue(EDG.getEdsGenerated().get(0).getDbShockRoom().get(1).size()==0);
	    
	    String line3 = "Rooms - number of BoxRooms : 2";
	    EDG.generateFromLine(line3);
	    assertTrue(EDG.getEdsGenerated().get(0).getDbBoxRoom().get(0).size()==2);
	    assertTrue(EDG.getEdsGenerated().get(0).getDbBoxRoom().get(1).size()==0);
	    
	    String line4 = "TestRooms - Blood test duration distribution : UNIFORM(2,5), number of BloodTestRooms : 3";
	    EDG.generateFromLine(line4); 
	    assertTrue(EDG.getEdsGenerated().get(0).getDbBloodRoom().get(0).size()==3);
	    assertTrue(EDG.getEdsGenerated().get(0).getDbBloodRoom().get(1).size()==0);
	    
	    String line5 = "TestRooms - MRI test duration distribution : GAMMA(1,2),  number of MRITestRooms : 10 ";
	    EDG.generateFromLine(line5);
	    assertTrue(EDG.getEdsGenerated().get(0).getDbMRIRoom().get(0).size()==10);
	    assertTrue(EDG.getEdsGenerated().get(0).getDbMRIRoom().get(1).size()==0);
	    
	    String line6 = "TestRooms - Radio test duration distribution : EXP(2),number of RadioTestRooms : 11";
	    EDG.generateFromLine(line6);
	    assertTrue(EDG.getEdsGenerated().get(0).getDbRadioRoom().get(0).size()==11);
	    assertTrue(EDG.getEdsGenerated().get(0).getDbRadioRoom().get(1).size()==0);
	    
	}
	
	
	@Test
	public void testgenerateFromLinePatients() {
		
		
		System.out.println("\n--------testgenerateFromLinePatients-------");
	    EDGeneratorFromFile EDG = new EDGeneratorFromFile(); 
	    String line0 = "NEW ED";
	    EDG.generateFromLine(line0);
	    
	    String line1 = "Patients L1 - 15 people arriving from Time=5min, arrivals distribution : UNIFORM(5,10)";
	    EDG.generateFromLine(line1);
	    
	    String line2 = "Patients L2 - 15 people arriving from Time=5min, arrivals distribution : EXP(0.2)";
	    EDG.generateFromLine(line2);
	    
	    String line3 = "Patients L3 - 15 people arriving from Time=5min, arrivals distribution : LOGNORM(2,1.5)";
	    EDG.generateFromLine(line3);
	    
	    String line4 = "Patients L4 - 15 people arriving from Time=5min, arrivals distribution : GAMMA(25,0.2)";
	    EDG.generateFromLine(line4);
	    
	    System.out.println(EDG.getEdsGenerated().get(0).getDbPatient());
	    for (Patient patient : EDG.getEdsGenerated().get(0).getDbPatient().get(0)) {
			System.out.println("Patient de degré d'urgence " + patient.getSeverityLevel() + " " + patient.getHistory());
		}
	    
	    System.out.println("--------testgenerateFromLinePatients----END---\n");

	}

	@Test
	public void testEdsGenerating(){
		
	}
	
	
}
