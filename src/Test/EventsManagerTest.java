package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Emergency.ED;
import Events.Consultation;
import Events.Event;
import Events.EventsManager;
import Events.Time;
import Events.TimeStamp;
import Factory.FactoryCreator;
import Factory.PeopleFactory;
import Factory.RoomFactory;
import HR.Nurse;
import HR.Patient;
import HR.Physician;
import HR.Transporter;
import Rooms.BloodRoom;
import Rooms.BoxRoom;
import Rooms.RadioRoom;
import Rooms.ShockRoom;
import Rooms.WaitingRoom;

public class EventsManagerTest {

	@Test
	public void testInsertNewEvent() {

		// INITIALISATION D'UN ED
		ArrayList<ED> eds = new ArrayList<ED>();
		ED ed = new ED("ED1", "France");
		eds.add(ed);

		Time time = Time.getInstanceTime();
		EventsManager simulator = new EventsManager(eds);
		
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
		RoomFactory roomFactory = (RoomFactory) FactoryCreator.getFactory("ROOM");
		
		Patient patient = (Patient) peopleFactory.getPatient(ed, "L1" , new TimeStamp());
		Physician physician = (Physician) peopleFactory.getStaff("PHYSICIAN", ed);
		ShockRoom sRoom = (ShockRoom) roomFactory.getRoom("SHOCKROOM", ed);
		
		Consultation consultation1 = new Consultation(ed, patient, physician, sRoom);
		Consultation consultation2 = new Consultation(ed, patient, physician, sRoom);
		Consultation consultation3 = new Consultation(ed, patient, physician, sRoom);
		
		consultation1.setEndTime(new TimeStamp(15));
		consultation2.setEndTime(new TimeStamp(2));
		consultation3.setEndTime(new TimeStamp(10));
		ArrayList<Event> trylist = new ArrayList<Event>();
		trylist.add(consultation2);
		trylist.add(consultation3);
		trylist.add(consultation1);
		
		//PROCEED
		simulator.insertNewEvent(consultation1);
		simulator.insertNewEvent(consultation2);
		simulator.insertNewEvent(consultation3);
		
		assertTrue("1.0", simulator.getInProgress().equals(trylist));
		
	}
	
	@Test
	public void TestCheckNewRegistration(){
		System.out.println("\n test de check new registration");
		// INITIALISATION D'UN ED
		ArrayList<ED> eds = new ArrayList<ED>();
		ED ed = new ED("ED1", "France");
		eds.add(ed);

		Time time = Time.getInstanceTime();
		EventsManager simulator = new EventsManager(eds);
		
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
		RoomFactory roomFactory = (RoomFactory) FactoryCreator.getFactory("ROOM");
		
		Patient patient1 = (Patient) peopleFactory.getPatient(ed, "roger", "roger", "arriving", "gold", "L1", new TimeStamp());
		Patient patient2 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		Patient patient3 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		Patient patient4 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		Patient patient5 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		Patient patient6 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		


		Nurse nurse1 = (Nurse) peopleFactory.getStaff("NURSE", ed);
		Nurse nurse2 = (Nurse) peopleFactory.getStaff("NURSE", ed);
		Nurse nurse3 = (Nurse) peopleFactory.getStaff("NURSE", ed);
		
		//test
		simulator.checkNewRegistration(ed);
		
		
		assertTrue(patient1.getState().equalsIgnoreCase("Registered"));
		assertTrue(patient2.getState().equalsIgnoreCase("Registered"));
		assertTrue(patient3.getState().equalsIgnoreCase("Registered"));
			
	}
	
	@Test
	public void testCheckNewTransport_Nurse(){
		System.out.println("\n test de check transport nurse");
		// INITIALISATION D'UN ED
		ArrayList<ED> eds = new ArrayList<ED>();
		ED ed = new ED("ED1", "France");
		eds.add(ed);

		Time time = Time.getInstanceTime();
		EventsManager simulator = new EventsManager(eds);
		
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
		RoomFactory roomFactory = (RoomFactory) FactoryCreator.getFactory("ROOM");
		
		Patient patient1 = (Patient) peopleFactory.getPatient(ed, "roger", "roger", "registered", "gold", "L1", new TimeStamp());
		Patient patient2 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		Patient patient3 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		Patient patient4 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		Patient patient5 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		Patient patient6 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		


		Nurse nurse1 = (Nurse) peopleFactory.getStaff("NURSE", ed);
		Nurse nurse2 = (Nurse) peopleFactory.getStaff("NURSE", ed);
		Nurse nurse3 = (Nurse) peopleFactory.getStaff("NURSE", ed);
		
		WaitingRoom wroom1 = (WaitingRoom) roomFactory.getRoom("WAITINGROOM", ed);
		
		simulator.checkNewRegistration(ed);
		//Test
		simulator.checkNewTransport_Nurse(ed);
		assertTrue(ed.getDbPatient().get(2).size() == 3);
		
		
	}
	
	@Test
	public void TestcheckNewConsultation(){
		System.out.println("\n test de check new consultation");
		// INITIALISATION D'UN ED
		ArrayList<ED> eds = new ArrayList<ED>();
		ED ed = new ED("ED1", "France");
		eds.add(ed);

		Time time = Time.getInstanceTime();
		EventsManager simulator = new EventsManager(eds);
		
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
		RoomFactory roomFactory = (RoomFactory) FactoryCreator.getFactory("ROOM");
		
		Patient patient1 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		Patient patient2 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		Patient patient3 = (Patient) peopleFactory.getPatient(ed, "L2", new TimeStamp());
		Patient patient4 = (Patient) peopleFactory.getPatient(ed, "L2", new TimeStamp());
		Patient patient5 = (Patient) peopleFactory.getPatient(ed, "L3", new TimeStamp());
		Patient patient6 = (Patient) peopleFactory.getPatient(ed, "L5", new TimeStamp());
		


		Nurse nurse1 = (Nurse) peopleFactory.getStaff("NURSE", ed);
		Nurse nurse2 = (Nurse) peopleFactory.getStaff("NURSE", ed);
		Nurse nurse3 = (Nurse) peopleFactory.getStaff("NURSE", ed);
		Nurse nurse4 = (Nurse) peopleFactory.getStaff("NURSE", ed);
		Nurse nurse5 = (Nurse) peopleFactory.getStaff("NURSE", ed);
		Nurse nurse6 = (Nurse) peopleFactory.getStaff("NURSE", ed);
		
		WaitingRoom wroom1 = (WaitingRoom) roomFactory.getRoom("WAITINGROOM", ed);
		ShockRoom schockroom1 = (ShockRoom) roomFactory.getRoom("SHOCKROOM", ed);
		ShockRoom schockroom2 = (ShockRoom) roomFactory.getRoom("SHOCKROOM", ed);
		ShockRoom schockroom3 = (ShockRoom) roomFactory.getRoom("SHOCKROOM", ed);
		ShockRoom schockroom4 = (ShockRoom) roomFactory.getRoom("SHOCKROOM", ed);
		ShockRoom schockroom5 = (ShockRoom) roomFactory.getRoom("SHOCKROOM", ed);
		BoxRoom boxRoom = (BoxRoom) roomFactory.getRoom("BOXROOM", ed);
		
		
		
		Physician physician1 = (Physician) peopleFactory.getStaff("PHYSICIAN", ed);
		Physician physician2 = (Physician) peopleFactory.getStaff("PHYSICIAN", ed);
		Physician physician3 = (Physician) peopleFactory.getStaff("PHYSICIAN", ed);
		Physician physician4 = (Physician) peopleFactory.getStaff("PHYSICIAN", ed);
		Physician physician5 = (Physician) peopleFactory.getStaff("PHYSICIAN", ed);
		
		simulator.checkNewRegistration(ed);
		//Test
		
		simulator.checkNewTransport_Nurse(ed);
		time.timeGoes(2);
		simulator.dequeueEvents();
		
		simulator.checkNewConsultation(ed);
		
		assertTrue(ed.getDbPatient().get(4).contains(patient1));
		assertTrue(ed.getDbPatient().get(4).contains(patient2));
		assertTrue(ed.getDbPatient().get(4).contains(patient3));
		assertTrue(ed.getDbPatient().get(4).contains(patient4));
		assertTrue(ed.getDbPatient().get(4).contains(patient5));
		assertFalse(ed.getDbPatient().get(4).contains(patient6));
		
	}
	
	@Test
	public void testCheckTransportTransporter(){
		System.out.println("\n test de check new consultation");
		// INITIALISATION D'UN ED
		ArrayList<ED> eds = new ArrayList<ED>();
		ED ed = new ED("ED1", "France");
		eds.add(ed);

		Time time = Time.getInstanceTime();
		EventsManager simulator = new EventsManager(eds);
		
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
		RoomFactory roomFactory = (RoomFactory) FactoryCreator.getFactory("ROOM");
		
		Patient patient1 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		Patient patient2 = (Patient) peopleFactory.getPatient(ed, "L5", new TimeStamp());
		

		patient1.setState("WAITINGFORBLOODTEST");
		patient2.setState("WAITINGFORRADIO");
		
		BloodRoom bloodRoom = (BloodRoom) roomFactory.getRoom("BLOODROOM", ed);
		RadioRoom radioRoom = (RadioRoom) roomFactory.getRoom("RADIOROOM", ed);
		
		Transporter transporter1 = (Transporter) peopleFactory.getStaff("TRANSPORTER", ed);
		Transporter transporter2 = (Transporter) peopleFactory.getStaff("TRANSPORTER", ed);
		
		
		
		//Test
		
		
		
		simulator.checkTransport_transporter(ed);
		
		System.out.println(simulator.getInProgress());
		System.out.println(patient1.getState());
		System.out.println(patient2.getState());
	}



}
