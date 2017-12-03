package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Emergency.ED;
import Events.Time;
import Events.TimeStamp;
import Factory.FactoryCreator;
import Factory.PeopleFactory;
import Factory.RoomFactory;
import HR.Patient;
import Rooms.RadioRoom;
import Rooms.Room;

public class RadioRoomTest {

	@Test
	public void testSetState() {
		System.out.println("\n test setState !!!");
		// INITIALISATION D'UN ED
		
		ED ed = new ED("ED1", "France");
		
		RoomFactory roomFactory  = (RoomFactory) FactoryCreator.getFactory("ROOM");
				
		Room radioRoom = (Room) roomFactory.getRoom("RADIOROOM", ed);
		
		//test free
		radioRoom.setState("free");
		assertTrue("free",radioRoom.getState().equalsIgnoreCase("free"));
		assertTrue(ed.getDbRadioRoom().get(0).contains(radioRoom));
		assertFalse(ed.getDbRadioRoom().get(1).contains(radioRoom));
		
		//test occupied
		radioRoom.setState("occupied");
		assertTrue("occupied",radioRoom.getState().equalsIgnoreCase("occupied"));
		assertTrue(ed.getDbRadioRoom().get(1).contains(radioRoom));
		assertFalse(ed.getDbRadioRoom().get(0).contains(radioRoom));
		
		//test error
		radioRoom.setState("lol");
		assertFalse("lol",radioRoom.getState().equalsIgnoreCase("lol"));
	}

	@Test
	public void testAddOccupant() {
		System.out.println("\n test add Occupant !!!");
		// INITIALISATION D'UN ED
		
		ED ed = new ED("ED1", "France");
		
		RoomFactory roomFactory  = (RoomFactory) FactoryCreator.getFactory("ROOM");
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
		
				
		RadioRoom radioRoom = (RadioRoom) roomFactory.getRoom("RadioROOM", ed);
		Patient patient = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		
		
		//TEST
		radioRoom.addOccupant(patient);
		
		assertTrue(patient.getLocation().equals(radioRoom));
		assertTrue(radioRoom.getState().equalsIgnoreCase("occupied"));
	}

	@Test
	public void testRemoveOccupant() {
		System.out.println("\n test Remove Occupant !!!");
		// INITIALISATION D'UN ED
		
		ED ed = new ED("ED1", "France");
		
		RoomFactory roomFactory  = (RoomFactory) FactoryCreator.getFactory("ROOM");
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
		
				
		RadioRoom radioRoom = (RadioRoom) roomFactory.getRoom("RADIOROOM", ed);
		Patient patient = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		
		
		//TEST
		radioRoom.addOccupant(patient);
		radioRoom.removeOccupant(patient);
		
		
		assertTrue(radioRoom.getState().equalsIgnoreCase("free"));
	}

	@Test
	public void testUpdatePatientCharge() {
		System.out.println("\n test update patient charges");
		// INITIALISATION D'UN ED
		
		ED ed = new ED("ED1", "France");
		Time time = Time.getInstanceTime();
		time.timeGoes(10);
		
		RoomFactory roomFactory  = (RoomFactory) FactoryCreator.getFactory("ROOM");
		PeopleFactory peoplefactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
				
		RadioRoom radioRoom = (RadioRoom) roomFactory.getRoom("RADIOROOM", ed);
		Patient patient = (Patient) peoplefactory.getPatient(ed, "L1", new TimeStamp());
		
		
		//TEST
		radioRoom.updatePatientCharge(patient);
		
		assertTrue(patient.getCharges() == radioRoom.getCost());
	}

	@Test
	public void testRadioTesting() {
		System.out.println("\n test MRI testing");
		// INITIALISATION D'UN ED
		
		ED ed = new ED("ED1", "France");
		Time time = Time.getInstanceTime();
		time.timeGoes(10);
		
		
		RoomFactory roomFactory  = (RoomFactory) FactoryCreator.getFactory("ROOM");
		PeopleFactory peoplefactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
				
		RadioRoom radioRoom = (RadioRoom) roomFactory.getRoom("RADIOROOM", ed);
		Patient patient = (Patient) peoplefactory.getPatient(ed, "L1", new TimeStamp());
		
		
		//TEST
		radioRoom.addOccupant(patient);
		radioRoom.radioTesting();
		assertTrue(radioRoom.getStartTime().getTimeStamp() == 20);
		time.timeGoes((int)radioRoom.getDuration());
		assertTrue(radioRoom.getEndTime().getTimeStamp() == 20+(int)radioRoom.getDuration());
		assertTrue(radioRoom.getDuration()>=10 && radioRoom.getDuration()<=20);
	}

	@Test
	public void testEndRadioTesting() {
		System.out.println("\n test End mri testing !!!");
		// INITIALISATION D'UN ED
		
		ED ed = new ED("ED1", "France");
		
		RoomFactory roomFactory  = (RoomFactory) FactoryCreator.getFactory("ROOM");
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
		
				
		RadioRoom radioRoom = (RadioRoom) roomFactory.getRoom("RADIOROOM", ed);
		Patient patient = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		
		
		//TEST
		radioRoom.addOccupant(patient);
		radioRoom.endRadioTesting();
		
		System.out.println(patient.getHistory());
		assertTrue(radioRoom.getState().equalsIgnoreCase("free"));
		assertTrue(patient.getState().equalsIgnoreCase("radioTested"));
	}

}
