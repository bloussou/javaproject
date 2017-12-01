package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Emergency.*;
import Events.*;
import HR.*;
import Rooms.*;
import Factory.*;

public class NurseTest {

	@Test
	public void testRegister() {
		
		// INITIALISATION D'UN ED
		
		ED ed = new ED("ED1", "France");
		Time time = Time.getInstanceTime();
		
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
		RoomFactory roomFactory = (RoomFactory) FactoryCreator.getFactory("ROOM");
		FacilityFactory facilityFactory = (FacilityFactory) FactoryCreator.getFactory("FACILITY");
		
		
		Patient patient1 = (Patient) peopleFactory.getPatient(ed, "L1" , new TimeStamp());
		Patient patient2 = (Patient) peopleFactory.getPatient(ed, "L3", new TimeStamp());
		
		Nurse nurse = (Nurse) peopleFactory.getStaff("NURSE", ed);

				
		// PROCEED
		nurse.register(patient1);
		nurse.register(patient2);
		
		
		// CHECK 
		assertTrue("1.1", ed.getEdRegister().contains(patient1));
		assertTrue("1.2", ed.getEdRegister().contains(patient2));
		assertTrue("1.3", nurse.getPatientRegistered().contains(patient1));
		assertTrue("1.4", nurse.getPatientRegistered().contains(patient2));
		assertFalse("1.5", ed.getDbPatient().get(0).contains(patient1));
		assertFalse("1.6", ed.getDbPatient().get(0).contains(patient2));
		assertTrue("1.7", ed.getDbPatient().get(1).contains(patient1));
		assertTrue("1.8", ed.getDbPatient().get(1).contains(patient2));
	}
	
	
	@Test
	public void testTransport(){
		
		// INITIALISATION D'UN ED
		
		ED ed = new ED("ED1", "France");
		Time time = Time.getInstanceTime();
		
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
		RoomFactory roomFactory = (RoomFactory) FactoryCreator.getFactory("ROOM");
		FacilityFactory facilityFactory = (FacilityFactory) FactoryCreator.getFactory("FACILITY");		
		
		Patient patient1 = (Patient) peopleFactory.getPatient(ed, "L1" , new TimeStamp());
		Nurse nurse = (Nurse) peopleFactory.getStaff("NURSE", ed);
		WaitingRoom wRoom1 = (WaitingRoom) roomFactory.getRoom("WAITINGROOM", ed);
		
		//PROCEED
		nurse.register(patient1);
		nurse.transport(patient1, wRoom1);
		
		//Check
		assertTrue("2.1", nurse.getPatientTransported().contains(patient1));
		assertFalse("2.3", ed.getDbPatient().get(1).contains(patient1));
		assertTrue("2.5", ed.getDbPatient().get(2).contains(patient1));
		assertFalse("2.7", ed.getDbNurse().get(0).contains(nurse));
		assertTrue("2.8", ed.getDbNurse().get(1).contains(nurse));
		assertTrue("2.9.0", patient1.getState().equalsIgnoreCase("Transporting"));
		assertTrue("2.9", nurse.getTargetRoom().equals(wRoom1));
	}
	
	@Test
	public void testSetState(){
		System.out.println("test setState !!!");
		// INITIALISATION D'UN ED
		
		ED ed = new ED("ED1", "France");
		Time time = Time.getInstanceTime();
		
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
				
		Nurse nurse = (Nurse) peopleFactory.getStaff("NURSE", ed);
		
		//test idle
		nurse.setState("idle");
		assertTrue("idle",nurse.getState().equalsIgnoreCase("idle"));
		assertTrue(ed.getDbNurse().get(0).contains(nurse));
		assertFalse(ed.getDbNurse().get(1).contains(nurse));
		assertFalse(ed.getDbNurse().get(2).contains(nurse));
		//test transporting
		nurse.setState("transporting");
		assertTrue("transporting",nurse.getState().equalsIgnoreCase("transporting"));
		assertTrue(ed.getDbNurse().get(1).contains(nurse));
		assertFalse(ed.getDbNurse().get(2).contains(nurse));
		assertFalse(ed.getDbNurse().get(0).contains(nurse));
		
		//test ofduty
		nurse.setState("ofDuty");
		assertTrue("ofDuty",nurse.getState().equalsIgnoreCase("ofDuty"));
		assertTrue(ed.getDbNurse().get(2).contains(nurse));
		assertFalse(ed.getDbNurse().get(1).contains(nurse));
		assertFalse(ed.getDbNurse().get(0).contains(nurse));
		
		//test error
		nurse.setState("lol");
		assertFalse("lol",nurse.getState().equalsIgnoreCase("lol"));
	}
	
	
	@Test
	public void testDropPatient(){
		
		// INITIALISATION D'UN ED
		
		ED ed = new ED("ED1", "France");
		Time time = Time.getInstanceTime();
		
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
		RoomFactory roomFactory = (RoomFactory) FactoryCreator.getFactory("ROOM");
		FacilityFactory facilityFactory = (FacilityFactory) FactoryCreator.getFactory("FACILITY");		
		
		Patient patient1 = (Patient) peopleFactory.getPatient(ed, "L1" , new TimeStamp());
		Nurse nurse = (Nurse) peopleFactory.getStaff("NURSE", ed);
		WaitingRoom wRoom1 = (WaitingRoom) roomFactory.getRoom("WAITINGROOM", ed);
		
		//PROCEED
		nurse.register(patient1);
		nurse.transport(patient1, wRoom1);
		nurse.dropPatient(patient1);
		
		//CHECK
		assertTrue("3.1.0", nurse.getState().equalsIgnoreCase("Idle"));
		assertTrue("3.1.1", patient1.getState().equalsIgnoreCase("waitingForConsultation"));
		assertFalse("3.2.0", ed.getDbPatient().get(2).contains(patient1));
		assertTrue("3.2.1", ed.getDbPatient().get(3).contains(patient1));
		assertFalse("3.3.0", ed.getDbNurse().get(1).contains(nurse));
		assertTrue ("3.3.1", ed.getDbNurse().get(0).contains(nurse));
		assertTrue("3.4", wRoom1.getOccupants().contains(patient1));
	}

}
