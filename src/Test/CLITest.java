package Test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

import HR.Patient;
import SimErgyClient.CLI;

public class CLITest {

	@Test
	public void testPromptCommandLine() {
		CLI cli = new CLI();
		cli.promptCommandLine();
		
		System.out.println("Prompted command line : " + cli.getCommandLine());
	}

	@Test
	public void testCommandHelp(){
		CLI cli = new CLI();
		ArrayList<String> command = new ArrayList<String>();
		command.add("HELP");
		cli.setCommandLine(command);
		cli.analyseCommand();
	}
	
	@Test
	public void testCommandCreateED(){
		CLI cli = new CLI();
		ArrayList<String> command = new ArrayList<String>();
		
		//'createED'
		command = new ArrayList<String>();
		command.add("createED");
		cli.setCommandLine(command);
		cli.analyseCommand();
		assertTrue("1.0", cli.getEds().get(0).getName().equalsIgnoreCase("ED0"));
		assertTrue("1.0", cli.getEds().get(0).getCountry().equalsIgnoreCase("Unknown"));
		
		//'createED EDTEST'
		command = new ArrayList<String>();
		command.add("createED");
		command.add("EDTEST");
		cli.setCommandLine(command);
		cli.analyseCommand();
		assertTrue("2.0", cli.getEds().get(1).getName().equalsIgnoreCase("EDTEST"));
		assertTrue("2.0", cli.getEds().get(1).getCountry().equalsIgnoreCase("Unknown"));
		
		//'createED EDTEST France'
		command = new ArrayList<String>();
		command.add("createED");
		command.add("EDTEST");
		command.add("France");
		cli.setCommandLine(command);
		cli.analyseCommand();
		assertTrue("3.0", cli.getEds().get(2).getName().equalsIgnoreCase("EDTEST"));
		assertTrue("3.0", cli.getEds().get(2).getCountry().equalsIgnoreCase("France"));
	}
		
	@Test 
	public void testAddRoom(){
		CLI cli = new CLI();
		ArrayList<String> command = new ArrayList<String>();
		
		// Create an ED
		command = new ArrayList<String>();
		command.add("createED");
		cli.setCommandLine(command);
		cli.analyseCommand();

		//'addRoom Pas assez d'arguments
		System.out.println("addRoom Pas assez d'arguments");
		command = new ArrayList<String>();
		command.add("addRoom");
		cli.setCommandLine(command);
		cli.analyseCommand();
		
		//'addRoom EDINCONNU 
		System.out.println("addRoom EDINCONNU");
		command = new ArrayList<String>();
		command.add("addRoom");
		command.add("EDINCONNU");
		cli.setCommandLine(command);
		cli.analyseCommand();
		
		//'addRoom ED0 WAITINGROOM
		System.out.println("'addRoom ED0 WAITINGROOM");
		command = new ArrayList<String>();
		command.add("addRoom");
		command.add("ED0");
		command.add("waitingroom");
		cli.setCommandLine(command);
		System.out.println("cli.getCommandLine() : " + cli.getCommandLine());
		cli.analyseCommand();
		assertTrue("1.0", cli.getEds().get(0).getDbWaitingRoom().get(0).size()==1);
	}
	
	@Test
	public void testAddTestRoom(){
		CLI cli = new CLI();
		ArrayList<String> command = new ArrayList<String>();
		
		// Create an ED
		command = new ArrayList<String>();
		command.add("createED");
		cli.setCommandLine(command);
		cli.analyseCommand();

		//addTestRoom Pas assez d'arguments
		System.out.println("addTestRoom Pas assez d'arguments");
		command = new ArrayList<String>();
		command.add("addTestRoom");
		cli.setCommandLine(command);
		cli.analyseCommand();
		
		//addTestRoom EDINCONNU 
		System.out.println("addTestRoom EDINCONNU ");
		command = new ArrayList<String>();
		command.add("addTestRoom");
		command.add("EDINCONNU");
		command.add("Dist");
		cli.setCommandLine(command);
		cli.analyseCommand();
		
		//addTestRoom ED0 BLOODROOM DISTRIB	(1.5,6)
		System.out.println("addTestRoom ED0 BLOODROOM DISTRIB  (1.5,6)");
		command = new ArrayList<String>();
		command.add("addTestRoom");
		command.add("ED0");
		command.add("BloodRoom");
		command.add("Distrib");
		command.add("(1.5,6)");
		cli.setCommandLine(command);
		cli.analyseCommand();
		assertTrue("1.0", cli.getEds().get(0).getDbBloodRoom().get(0).size()==1);
	}

	@Test
	public void testAddNurse(){
		CLI cli = new CLI();
		ArrayList<String> command = new ArrayList<String>();
		
		// Create an ED
		command = new ArrayList<String>();
		command.add("createED");
		cli.setCommandLine(command);
		cli.analyseCommand();

		//addNurse
		System.out.println("addNurse Pas assez d'arguments");
		command = new ArrayList<String>();
		command.add("addNurse");
		cli.setCommandLine(command);
		cli.analyseCommand();
		
		//addNurse EDINCONNU 
		System.out.println("addNurse EDINCONNU ");
		command = new ArrayList<String>();
		command.add("addNurse");
		command.add("EDINCONNU");
		cli.setCommandLine(command);
		cli.analyseCommand();
		
		//addNurse ED0
		System.out.println("addNurse ED0");
		command = new ArrayList<String>();
		command.add("addNurse");
		command.add("ED0");
		cli.setCommandLine(command);
		cli.analyseCommand();
		assertTrue("1.0", cli.getEds().get(0).getDbNurse().get(0).size()==1);
		
		//addNurse ED0 Simone Chantere
		System.out.println("addNurse ED0 Simone Chantere");
		command = new ArrayList<String>();
		command.add("addNurse");
		command.add("ED0");
		command.add("Simone");
		command.add("Chantere");
		cli.setCommandLine(command);
		cli.analyseCommand();
		assertTrue("2.0", cli.getEds().get(0).getDbNurse().get(0).size()==2);
	}

	@Test
	public void testAddPhysician(){
		CLI cli = new CLI();
		ArrayList<String> command = new ArrayList<String>();
		
		// Create an ED
		command = new ArrayList<String>();
		command.add("createED");
		cli.setCommandLine(command);
		cli.analyseCommand();

		//addPhysician
		System.out.println("addPhysician Pas assez d'arguments");
		command = new ArrayList<String>();
		command.add("addPhysician");
		cli.setCommandLine(command);
		cli.analyseCommand();
		
		//addPhysician EDINCONNU 
		System.out.println("addPhysician EDINCONNU ");
		command = new ArrayList<String>();
		command.add("addPhysician");
		command.add("EDINCONNU");
		cli.setCommandLine(command);
		cli.analyseCommand();
		
		//addPhysician ED0
		System.out.println("addPhysician ED0");
		command = new ArrayList<String>();
		command.add("addPhysician");
		command.add("ED0");
		cli.setCommandLine(command);
		cli.analyseCommand();
		assertTrue("1.0", cli.getEds().get(0).getDbPhysician().get(0).size()==1);
		
		//addPhysician ED0 Simone Chantere
		System.out.println("addPhysician ED0 Simone Chantere");
		command = new ArrayList<String>();
		command.add("addPhysician");
		command.add("ED0");
		command.add("Simone");
		command.add("Chantere");
		cli.setCommandLine(command);
		cli.analyseCommand();
		assertTrue("2.0", cli.getEds().get(0).getDbPhysician().get(0).size()==2);
	}
	
	@Test
	public void testAddTransporter(){
		CLI cli = new CLI();
		ArrayList<String> command = new ArrayList<String>();
		
		// Create an ED
		command = new ArrayList<String>();
		command.add("createED");
		cli.setCommandLine(command);
		cli.analyseCommand();

		//addTransporter
		System.out.println("addTransporter Pas assez d'arguments");
		command = new ArrayList<String>();
		command.add("addTransporter");
		cli.setCommandLine(command);
		cli.analyseCommand();
		
		//addTransporter EDINCONNU 
		System.out.println("addTransporter EDINCONNU ");
		command = new ArrayList<String>();
		command.add("addTransporter");
		command.add("EDINCONNU");
		cli.setCommandLine(command);
		cli.analyseCommand();
		
		//addTransporter ED0
		System.out.println("addTransporter ED0");
		command = new ArrayList<String>();
		command.add("addTransporter");
		command.add("ED0");
		cli.setCommandLine(command);
		cli.analyseCommand();
		assertTrue("1.0", cli.getEds().get(0).getDbTransporter().get(0).size()==1);
		
		//addTransporter ED0 Simone Chantere
		System.out.println("addTransporter ED0 Simone Chantere");
		command = new ArrayList<String>();
		command.add("addTransporter");
		command.add("ED0");
		command.add("Simone");
		command.add("Chantere");
		cli.setCommandLine(command);
		cli.analyseCommand();
		assertTrue("2.0", cli.getEds().get(0).getDbTransporter().get(0).size()==2);
	}

	public void testAddPatient(){
		CLI cli = new CLI();
		ArrayList<String> command = new ArrayList<String>();
		
		// Create an ED
		command = new ArrayList<String>();
		command.add("createED");
		cli.setCommandLine(command);
		cli.analyseCommand();

		//addPatient
		System.out.println("addPatient Pas assez d'arguments");
		command = new ArrayList<String>();
		command.add("addPatient");
		cli.setCommandLine(command);
		cli.analyseCommand();
		
		//addPatient ED0 L2 15
		System.out.println("addPatient ED0 L2 15");
		command = new ArrayList<String>();
		command.add("addPatient");
		command.add("ED0");
		command.add("L2");
		command.add("15");
		cli.setCommandLine(command);
		cli.analyseCommand();
		assertTrue("1.0", cli.getEds().get(0).getDbPatient().get(0).size()==1);
		assertTrue("2.0", cli.getEds().get(0).getDbPatient().get(0).get(0).getSeverityLevel().equalsIgnoreCase("L2"));
		assertTrue("3.0", cli.getEds().get(0).getDbPatient().get(0).get(0).getArrivalTime().getTimeStamp()==15);
	}
	
	@Test 
	public void testSetNewPatientFlow(){
		CLI cli = new CLI();
		ArrayList<String> command = new ArrayList<String>();
		
		// Create an ED
		command = new ArrayList<String>();
		command.add("createED");
		cli.setCommandLine(command);
		cli.analyseCommand();
		
		
		//setNewPatientFlow ED0 L2 UNIFORM (2,5) 5 153
		System.out.println("\n\nsetNewPatientFlow ED0 L2 25people 5min UNIFORM (5,15.5)");
		command = new ArrayList<String>();
		command.add("setNewPatientFlow");
		command.add("ED0");
		command.add("L2");
		command.add("25people");
		command.add("(5min)");
		command.add("UNIFORM");
		command.add("(5,15.5)");
		cli.setCommandLine(command);
		cli.analyseCommand();
		assertFalse(cli.getEds().get(0).getDbPatient().get(0).isEmpty());
		for (Patient patient : cli.getEds().get(0).getDbPatient().get(0)){
			System.out.println("patient.getArrivalTime().toString() : " + patient.getArrivalTime().toString());
		}
		for (Patient patient : cli.getEds().get(0).getDbPatient().get(0)){
			assertTrue(patient.getArrivalTime().getTimeStamp()>4 && patient.getArrivalTime().getTimeStamp()<16);
		}
		System.out.println("END ----- setNewPatientFlow ED0 L2 25people 5min UNIFORM (5,15.5)\n\n");
		
	}

	@Test 
	public void testSetInsurance(){
		CLI cli = new CLI();
		ArrayList<String> command = new ArrayList<String>();
		
		System.out.println("\n------Test SetHealthInsurance");
		// Create an ED
		command = new ArrayList<String>();
		command.add("createED");
		cli.setCommandLine(command);
		cli.analyseCommand();
		
		//addPatient ED0 L2 15
		System.out.println("addPatient ED0 L2 15");
		command = new ArrayList<String>();
		command.add("addPatient");
		command.add("ED0");
		command.add("L2");
		command.add("15");
		cli.setCommandLine(command);
		cli.analyseCommand();
		
		//setPatientInsurance ED0 [PatientID] SILVER
		System.out.println("setPatientInsurance ED0 [PatientID] SILVER");
		command = new ArrayList<String>();
		command.add("setPatientInsurance");
		command.add("ED0");
		String patientID = "" + cli.getEds().get(0).getDbPatient().get(0).get(0).getId();
		command.add(patientID);
		command.add("SILVER");
		cli.setCommandLine(command);
		cli.analyseCommand();
		System.out.println("cli.getEds().get(0).getDbPatient().get(0) :" + cli.getEds().get(0).getDbPatient().get(0) );
		System.out.println("cli.getEds().get(0).getDbPatient().get(0).get(0).getHealthInsurance() : " + cli.getEds().get(0).getDbPatient().get(0).get(0).getHealthInsurance());
		assertTrue("1.0", cli.getEds().get(0).getDbPatient().get(0).size()==1);
		assertTrue("2.0", cli.getEds().get(0).getDbPatient().get(0).get(0).getHealthInsurance().equalsIgnoreCase("SILVER"));
		
		System.out.println("END------Test SetHealthInsurance\n");
	}
		
	

}
