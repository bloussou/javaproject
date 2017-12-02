package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.sun.jmx.snmp.Timestamp;

import Emergency.ED;
import Events.Time;
import Events.TimeStamp;
import Factory.FactoryCreator;
import Factory.PeopleFactory;
import Factory.RoomFactory;
import HR.Nurse;
import HR.Patient;
import Rooms.BloodRoom;
import Rooms.Room;

public class BloodRoomTest {

	@Test
	public void testSetState() {
		System.out.println("test setState !!!");
		// INITIALISATION D'UN ED
		
		ED ed = new ED("ED1", "France");
		
		RoomFactory roomFactory  = (RoomFactory) FactoryCreator.getFactory("ROOM");
				
		Room bloodRoom = (Room) roomFactory.getRoom("BLOODROOM", ed);
		
		//test free
		bloodRoom.setState("free");
		assertTrue("free",bloodRoom.getState().equalsIgnoreCase("free"));
		assertTrue(ed.getDbBloodRoom().get(0).contains(bloodRoom));
		assertFalse(ed.getDbBloodRoom().get(1).contains(bloodRoom));
		
		//test occupied
		bloodRoom.setState("occupied");
		assertTrue("occupied",bloodRoom.getState().equalsIgnoreCase("occupied"));
		assertTrue(ed.getDbBloodRoom().get(1).contains(bloodRoom));
		assertFalse(ed.getDbBloodRoom().get(0).contains(bloodRoom));
		
		//test error
		bloodRoom.setState("lol");
		assertFalse("lol",bloodRoom.getState().equalsIgnoreCase("lol"));
	}

	@Test
	public void testAddOccupant() {
		// INITIALISATION D'UN ED
		
		ED ed = new ED("ED1", "France");
		Time time = Time.getInstanceTime();
		time.timeGoes(10);
		
		RoomFactory roomFactory  = (RoomFactory) FactoryCreator.getFactory("ROOM");
		PeopleFactory peoplefactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
				
		Room bloodRoom = (Room) roomFactory.getRoom("BLOODROOM", ed);
		Patient patient = (Patient) peoplefactory.getPatient(ed, "L1", new TimeStamp());
		
		
		//TEST
		bloodRoom.addOccupant(patient);
		
		assertTrue(patient.getLocation().equals(bloodRoom));
	}

	@Test
	public void testRemoveOccupant() {
		// INITIALISATION D'UN ED
		
		ED ed = new ED("ED1", "France");
		Time time = Time.getInstanceTime();
		time.timeGoes(10);
		
		RoomFactory roomFactory  = (RoomFactory) FactoryCreator.getFactory("ROOM");
		PeopleFactory peoplefactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
				
		Room bloodRoom = (Room) roomFactory.getRoom("BLOODROOM", ed);
		Patient patient = (Patient) peoplefactory.getPatient(ed, "L1", new TimeStamp());
		
		
		//TEST
		bloodRoom.addOccupant(patient);
		bloodRoom.removeOccupant(patient);
		
		assertTrue(ed.getDbPatient().get(3).contains(patient));
	}

	@Test
	public void testUpdatePatientCharge() {
		// INITIALISATION D'UN ED
		
		ED ed = new ED("ED1", "France");
		Time time = Time.getInstanceTime();
		time.timeGoes(10);
		
		RoomFactory roomFactory  = (RoomFactory) FactoryCreator.getFactory("ROOM");
		PeopleFactory peoplefactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
				
		Room bloodRoom = (Room) roomFactory.getRoom("BLOODROOM", ed);
		Patient patient = (Patient) peoplefactory.getPatient(ed, "L1", new TimeStamp());
		
		
		//TEST
		bloodRoom.updatePatientCharge(patient);
		
		assertTrue(patient.getCharges() == bloodRoom.getCost());
	}

	@Test
	public void testBloodTesting() {
		// INITIALISATION D'UN ED
		
		ED ed = new ED("ED1", "France");
		Time time = Time.getInstanceTime();
		time.timeGoes(10);
		
		
		RoomFactory roomFactory  = (RoomFactory) FactoryCreator.getFactory("ROOM");
		PeopleFactory peoplefactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
				
		BloodRoom bloodRoom = (BloodRoom) roomFactory.getRoom("BLOODROOM", ed);
		Patient patient = (Patient) peoplefactory.getPatient(ed, "L1", new TimeStamp());
		
		
		//TEST
		bloodRoom.addOccupant(patient);
		bloodRoom.bloodTesting();
		assertTrue(bloodRoom.getStartTime().getTimeStamp() == 30);
		time.timeGoes((int)bloodRoom.getDuration());
		assertTrue(bloodRoom.getEndTime().getTimeStamp() == 30+(int)bloodRoom.getDuration());
	
		
	}


}
