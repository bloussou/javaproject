package Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Emergency.*;
import Factory.*;
import HR.*;
import Rooms.*;
import Events.*;
 
public class PhysicianTest {

	@Test
	public void testGetPatientOverseeing() {
		
		// INITIALISATION D'UN ED
		ED ed = new ED("ED1", "France");
		Time time = Time.getInstanceTime();
		
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
		RoomFactory roomFactory = (RoomFactory) FactoryCreator.getFactory("ROOM");
		FacilityFactory facilityFactory = (FacilityFactory) FactoryCreator.getFactory("FACILITY");
		
		
		Patient patient1 = (Patient) peopleFactory.getPatient(ed, "L1" , new TimeStamp());
		Patient patient2 = (Patient) peopleFactory.getPatient(ed, "L3", new TimeStamp());
		
		Physician physician1 = (Physician) peopleFactory.getStaff("PHYSICIAN", ed);
		Physician physician2 = (Physician) peopleFactory.getStaff("PHYSICIAN", ed);
		
		BoxRoom boxRoom1 = (BoxRoom) roomFactory.getRoom("BOXROOM", ed);
		ShockRoom ShockRoom1 = (ShockRoom) roomFactory.getRoom("SHOCKROOM", ed);
		
		// TEST

		physician1.handleNewPatient(patient1, boxRoom1);
		
		//good answer PatientOverseeing list
		ArrayList<Patient> tryList = new ArrayList<Patient>();
		tryList.add(patient1);

		assertTrue(physician1.getPatientOverseeing().equals(tryList));
	}


	@Test
	public void testHandleNewPatient() {
		// INITIALISATION D'UN ED
		ED ed = new ED("ED1", "France");
		Time time = Time.getInstanceTime();
		time.timeGoes(10);
			
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
		RoomFactory roomFactory = (RoomFactory) FactoryCreator.getFactory("ROOM");
		FacilityFactory facilityFactory = (FacilityFactory) FactoryCreator.getFactory("FACILITY");
				
				
		Patient patient1 = (Patient) peopleFactory.getPatient(ed, "L1" , new TimeStamp());
		Patient patient2 = (Patient) peopleFactory.getPatient(ed, "L3", new TimeStamp());
				
		Physician physician1 = (Physician) peopleFactory.getStaff("PHYSICIAN", ed);
		Physician physician2 = (Physician) peopleFactory.getStaff("PHYSICIAN", ed);
				
		BoxRoom boxRoom1 = (BoxRoom) roomFactory.getRoom("BOXROOM", ed);
		ShockRoom ShockRoom1 = (ShockRoom) roomFactory.getRoom("SHOCKROOM", ed);
		
		
		
		
		
		//TEST
		physician1.handleNewPatient(patient1, boxRoom1);
		
		//test physician state
		String tryState = "visiting";
		assertTrue(physician1.getState().equals(tryState));
		assertTrue(ed.getDbPhysician().get(1).indexOf(physician1) != -1);
		
		//test patient state
		assertTrue(patient1.getState().equalsIgnoreCase("inConsultation"));
		
		//test room state
		assertTrue(boxRoom1.getState().equalsIgnoreCase("occupied"));
		
		//test physician of the patient
		assertTrue(patient1.getPhysician().equals(physician1));
		
		//test patient history
		assertTrue(patient1.getHistory().equalsIgnoreCase("(visited, 10), "));
		
	}

	@Test
	public void testEmitVerdict() {
		// INITIALISATION D'UN ED
		ED ed = new ED("ED1", "France");
		Time time = Time.getInstanceTime();
		time.timeGoes(10);
					
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
		RoomFactory roomFactory = (RoomFactory) FactoryCreator.getFactory("ROOM");
		FacilityFactory facilityFactory = (FacilityFactory) FactoryCreator.getFactory("FACILITY");
						
						
		Patient patient1 = (Patient) peopleFactory.getPatient(ed, "L1" , new TimeStamp());
		Patient patient2 = (Patient) peopleFactory.getPatient(ed, "L3", new TimeStamp());
		Patient patient3 = (Patient) peopleFactory.getPatient(ed, "L2", new TimeStamp());
		Patient patient4 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		Patient patient5 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
						
		Physician physician1 = (Physician) peopleFactory.getStaff("PHYSICIAN", ed);
						
		BoxRoom boxRoom1 = (BoxRoom) roomFactory.getRoom("BOXROOM", ed);
		ShockRoom ShockRoom1 = (ShockRoom) roomFactory.getRoom("SHOCKROOM", ed);
				
				
		// A REPRENDRE CI DESSOUS !!		
				
				
		//TEST
		physician1.handleNewPatient(patient1, boxRoom1);
	
		assertTrue(physician.getPatientOverseeing().indexOf(patient) == -1);
		assertTrue(physician.getPatientAlreadyTreated().indexOf(patient) != -1);
	}

	@Test
	public void testPrescribe() {
		// INITIALISATION D'UN ED
		ED ed = new ED("ED1", "France");
		Time time = Time.getInstanceTime();
		time.timeGoes(10);
					
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
		RoomFactory roomFactory = (RoomFactory) FactoryCreator.getFactory("ROOM");
		FacilityFactory facilityFactory = (FacilityFactory) FactoryCreator.getFactory("FACILITY");
						
						
		Patient patient1 = (Patient) peopleFactory.getPatient(ed, "L1" , new TimeStamp());
		Patient patient2 = (Patient) peopleFactory.getPatient(ed, "L3", new TimeStamp());
						
		Physician physician1 = (Physician) peopleFactory.getStaff("PHYSICIAN", ed);
		Physician physician2 = (Physician) peopleFactory.getStaff("PHYSICIAN", ed);
						
		BoxRoom boxRoom1 = (BoxRoom) roomFactory.getRoom("BOXROOM", ed);
		ShockRoom ShockRoom1 = (ShockRoom) roomFactory.getRoom("SHOCKROOM", ed);
				
				
				
				
				
		//TEST
		physician1.handleNewPatient(patient1, boxRoom1);
	
		assertTrue(physician.getState().equals("idle"));
	assertTrue(patient.getState().equalsIgnoreCase("WaitingForBloodTest") || patient.getState().equalsIgnoreCase("WaitingForMRI") 
			|| patient.getState().equalsIgnoreCase("WaitingForRadio") || patient.getState().equalsIgnoreCase("Released"));

	}
	
	
	@Test
	public void testSetState(){
		System.out.println("test setState !!!");
		// INITIALISATION D'UN ED
		
		ED ed = new ED("ED1", "France");
		Time time = Time.getInstanceTime();
		
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");		
				
		Physician physician = (Physician) peopleFactory.getStaff("PHYSICIAN", ed);
		
		//test idle
		physician.setState("idle");
		assertTrue("idle",physician.getState().equalsIgnoreCase("idle"));
		assertTrue(ed.getDbPhysician().get(0).contains(physician));
		assertFalse(ed.getDbPhysician().get(1).contains(physician));
		assertFalse(ed.getDbPhysician().get(2).contains(physician));
		//test visiting
		physician.setState("visiting");
		assertTrue("visiting",physician.getState().equalsIgnoreCase("visiting"));
		assertTrue(ed.getDbPhysician().get(1).contains(physician));
		assertFalse(ed.getDbPhysician().get(2).contains(physician));
		assertFalse(ed.getDbPhysician().get(0).contains(physician));
		
		//test ofduty
		physician.setState("ofDuty");
		assertTrue("ofDuty",physician.getState().equalsIgnoreCase("ofDuty"));
		assertTrue(ed.getDbPhysician().get(2).contains(physician));
		assertFalse(ed.getDbPhysician().get(1).contains(physician));
		assertFalse(ed.getDbPhysician().get(0).contains(physician));
		
		//test error
		physician.setState("lol");
		assertFalse("lol",physician.getState().equalsIgnoreCase("lol"));
	}
}

	
