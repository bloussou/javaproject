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
import Rooms.ShockRoom;

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

}
