package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Emergency.ED;
import Events.Time;
import Events.TimeStamp;
import Factory.FactoryCreator;
import Factory.PeopleFactory;
import Factory.RoomFactory;
import HR.Patient;
import HR.Transporter;
import Rooms.BloodRoom;
import Rooms.MRIRoom;
import Rooms.RadioRoom;
import Rooms.WaitingRoom;

public class TransporterTest {

	@Test
	public void testTransport() {
		System.out.println("\n test transport !!!");
		// INITIALISATION D'UN ED
		
		ED ed = new ED("ED1", "France");
		Time time = Time.getInstanceTime();
		time.timeGoes(10);
		
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");	
		RoomFactory roomFactory = (RoomFactory) FactoryCreator.getFactory("ROOM");
				
		Transporter transporter = (Transporter) peopleFactory.getStaff(ed,"TRANSPORTER");
		Patient patient1 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		BloodRoom bloodRoom = (BloodRoom) roomFactory.getRoom(ed,"BLOODROOM");
		
		//TEST
		transporter.transport(patient1, bloodRoom);
		ArrayList<Patient> trylist = new ArrayList<Patient>();
		trylist.add(patient1);
		
		assertTrue(transporter.getPatientTransported().equals(trylist));
		assertTrue(transporter.getStartTime().getTimeStamp() == 10);
		assertTrue(transporter.getEndTime().getTimeStamp() == 14);
		
		
	}

//	@Test
//	public void testBackToPhysician() {
//		System.out.println("\n test BackToPhysician !!!");
//		// INITIALISATION D'UN ED
//		
//		ED ed = new ED("ED1", "France");
//		Time time = Time.getInstanceTime();
//		time.timeGoes(10);
//		
//		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");	
//		RoomFactory roomFactory = (RoomFactory) FactoryCreator.getFactory("ROOM");
//				
//		Transporter transporter = (Transporter) peopleFactory.getStaff("TRANSPORTER", ed);
//		Patient patient1 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
//		WaitingRoom waitingRoom = (WaitingRoom) roomFactory.getRoom("WAITINGROOM", ed);
//		
//		//TEST
//		patient1.setState("bloodtested");
//		
//		transporter.backToPhysician(patient1, waitingRoom);
//		ArrayList<Patient> trylist = new ArrayList<Patient>();
//		trylist.add(patient1);
//		
//		assertTrue(patient1.getState().equalsIgnoreCase("transportation"));
//		assertTrue(transporter.getState().equalsIgnoreCase("transportation"));
//		
//	}

	@Test
	public void testDropPatient() {
		System.out.println("\n test Drop Patient !!!");
		// INITIALISATION D'UN ED
		
		ED ed = new ED("ED1", "France");
		
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");	
		RoomFactory roomFactory = (RoomFactory) FactoryCreator.getFactory("ROOM");
				
		Transporter transporter = (Transporter) peopleFactory.getStaff(ed,"TRANSPORTER");
		Patient patient1 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		Patient patient2 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		Patient patient3 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		Patient patient4 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		Patient patient5 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		Patient patient6 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		
		WaitingRoom waitingRoom = (WaitingRoom) roomFactory.getRoom(ed,"WAITINGROOM");
		BloodRoom bloodRoom = (BloodRoom) roomFactory.getRoom(ed,"BLOODROOM");
		MRIRoom mriRoom = (MRIRoom) roomFactory.getRoom(ed,"MRIROOM");
		RadioRoom radioRoom = (RadioRoom) roomFactory.getRoom(ed,"RADIOROOM");
		//Initialization of the patient state
		patient1.setState("waitingForMRI");
		patient2.setState("waitingForBloodTest");
		patient3.setState("waitingForRadio");
		patient4.setState("bloodTested");
		patient5.setState("mriTested");
		patient6.setState("radioTested");		
		
		//TEST Drop patient
		transporter.transport(patient1, mriRoom);
		assertTrue(transporter.getTargetRoom().equals(mriRoom));
		transporter.dropPatient(patient1);
		assertTrue(transporter.getState().equalsIgnoreCase("idle"));
		assertTrue(patient1.getState().equalsIgnoreCase("waitingForMRIT"));
		assertTrue(mriRoom.getState().equalsIgnoreCase("occupied"));
		assertTrue(mriRoom.getPatient().equals(patient1));
		
		transporter.transport(patient2, bloodRoom);
		assertTrue(transporter.getTargetRoom().equals(bloodRoom));
		transporter.dropPatient(patient2);
		assertTrue(transporter.getState().equalsIgnoreCase("idle"));
		assertTrue(patient2.getState().equalsIgnoreCase("waitingForBloodTestT"));
		assertTrue(bloodRoom.getState().equalsIgnoreCase("occupied"));
		assertTrue(bloodRoom.getPatient().equals(patient2));
		
		transporter.transport(patient3, radioRoom);
		assertTrue(transporter.getTargetRoom().equals(radioRoom));
		transporter.dropPatient(patient3);
		assertTrue(transporter.getState().equalsIgnoreCase("idle"));
		assertTrue(patient3.getState().equalsIgnoreCase("waitingForRadioT"));
		assertTrue(radioRoom.getState().equalsIgnoreCase("occupied"));
		assertTrue(radioRoom.getPatient().equals(patient3));
		
//		transporter.backToPhysician(patient4, waitingRoom);
//		assertTrue(transporter.getTargetRoom().equals(waitingRoom));
//		transporter.dropPatient(patient4);
//		assertTrue(transporter.getState().equalsIgnoreCase("idle"));
//		assertTrue(patient4.getState().equalsIgnoreCase("waitingForConsultation"));
//		assertTrue(waitingRoom.getState().equalsIgnoreCase("available"));
//		assertTrue(waitingRoom.getOccupants().contains(patient4));
//		
//		transporter.backToPhysician(patient5, waitingRoom);
//		transporter.dropPatient(patient5);
//		assertTrue(transporter.getState().equalsIgnoreCase("idle"));
//		assertTrue(patient5.getState().equalsIgnoreCase("waitingForConsultation"));
//		assertTrue(waitingRoom.getState().equalsIgnoreCase("available"));
//		assertTrue(waitingRoom.getOccupants().contains(patient5));
//		
//		transporter.backToPhysician(patient6, waitingRoom);
//		transporter.dropPatient(patient6);
//		assertTrue(transporter.getState().equalsIgnoreCase("idle"));
//		assertTrue(patient6.getState().equalsIgnoreCase("waitingForConsultation"));
//		assertTrue(waitingRoom.getState().equalsIgnoreCase("available"));
//		assertTrue(waitingRoom.getOccupants().contains(patient6));
		

		
	}
	
	@Test
	public void testSetState(){
		System.out.println("\n test setState !!!");
		// INITIALISATION D'UN ED
		
		ED ed = new ED("ED1", "France");
		
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");		
				
		Transporter transporter = (Transporter) peopleFactory.getStaff(ed,"TRANSPORTER");
		
		//test idle
		transporter.setState("idle");
		assertTrue("idle",transporter.getState().equalsIgnoreCase("idle"));
		assertTrue(ed.getDbTransporter().get(0).contains(transporter));
		assertFalse(ed.getDbTransporter().get(1).contains(transporter));
		assertFalse(ed.getDbTransporter().get(2).contains(transporter));
		
		//test transportation
		transporter.setState("transportation");
		assertTrue("transportation",transporter.getState().equalsIgnoreCase("transportation"));
		assertTrue(ed.getDbTransporter().get(1).contains(transporter));
		assertFalse(ed.getDbTransporter().get(0).contains(transporter));
		assertFalse(ed.getDbTransporter().get(2).contains(transporter));
		
		//test ofduty
		transporter.setState("ofDuty");
		assertTrue("ofDuty",transporter.getState().equalsIgnoreCase("ofDuty"));
		assertTrue(ed.getDbTransporter().get(2).contains(transporter));
		assertFalse(ed.getDbTransporter().get(1).contains(transporter));
		assertFalse(ed.getDbTransporter().get(0).contains(transporter));
		
		//test error
		transporter.setState("lol");
		assertFalse("lol",transporter.getState().equalsIgnoreCase("lol"));		
	}

}
