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
import Rooms.MRIRoom;
import Rooms.Room;

public class MRIRoomTest {

	@Test
	public void testSetState() {
		System.out.println("\n test setState !!!");
		// INITIALISATION D'UN ED
		
		ED ed = new ED("ED1", "France");
		
		RoomFactory roomFactory  = (RoomFactory) FactoryCreator.getFactory("ROOM");
				
		Room mriRoom = (Room) roomFactory.getRoom(ed, "MRIROOM");
		
		//test free
		mriRoom.setState("free");
		assertTrue("free",mriRoom.getState().equalsIgnoreCase("free"));
		assertTrue(ed.getDbMRIRoom().get(0).contains(mriRoom));
		assertFalse(ed.getDbMRIRoom().get(1).contains(mriRoom));
		
		//test occupied
		mriRoom.setState("occupied");
		assertTrue("occupied",mriRoom.getState().equalsIgnoreCase("occupied"));
		assertTrue(ed.getDbMRIRoom().get(1).contains(mriRoom));
		assertFalse(ed.getDbMRIRoom().get(0).contains(mriRoom));
		
		//test error
		mriRoom.setState("lol");
		assertFalse("lol",mriRoom.getState().equalsIgnoreCase("lol"));
	}

	@Test
	public void testAddOccupant() {
		System.out.println("\n test add Occupant !!!");
		// INITIALISATION D'UN ED
		
		ED ed = new ED("ED1", "France");
		
		RoomFactory roomFactory  = (RoomFactory) FactoryCreator.getFactory("ROOM");
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
		
				
		Room mriRoom = (Room) roomFactory.getRoom(ed, "MRIROOM");
		Patient patient = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		
		
		//TEST
		mriRoom.addOccupant(patient);
		
		assertTrue(patient.getLocation().equals(mriRoom));
		assertTrue(mriRoom.getState().equalsIgnoreCase("occupied"));
	}

	@Test
	public void testRemoveOccupant() {
		System.out.println("\n test Remove Occupant !!!");
		// INITIALISATION D'UN ED
		
		ED ed = new ED("ED1", "France");
		
		RoomFactory roomFactory  = (RoomFactory) FactoryCreator.getFactory("ROOM");
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
		
				
		MRIRoom mriRoom = (MRIRoom) roomFactory.getRoom(ed, "MRIROOM");
		Patient patient = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		
		
		//TEST
		mriRoom.addOccupant(patient);
		mriRoom.removeOccupant(patient);
		
		
		assertTrue(mriRoom.getState().equalsIgnoreCase("free"));
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
				
		MRIRoom mriRoom = (MRIRoom) roomFactory.getRoom(ed, "MRIROOM");
		Patient patient = (Patient) peoplefactory.getPatient(ed, "L1", new TimeStamp());
		
		
		//TEST
		mriRoom.updatePatientCharge(patient);
		
		assertTrue(patient.getCharges() == mriRoom.getCost());
	}

	@Test
	public void testMriTesting() {
		System.out.println("\n test MRI testing");
		// INITIALISATION D'UN ED
		
		ED ed = new ED("ED1", "France");
		Time time = Time.getInstanceTime();
		time.timeGoes(10);
		
		
		RoomFactory roomFactory  = (RoomFactory) FactoryCreator.getFactory("ROOM");
		PeopleFactory peoplefactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
				
		MRIRoom mriRoom = (MRIRoom) roomFactory.getRoom(ed, "MRIROOM");
		Patient patient = (Patient) peoplefactory.getPatient(ed, "L1", new TimeStamp());
		
		
		//TEST
		mriRoom.addOccupant(patient);
		mriRoom.mriTesting();
		assertTrue(mriRoom.getStartTime().getTimeStamp() == 10);
		time.timeGoes((int)mriRoom.getDuration());
		assertTrue(mriRoom.getEndTime().getTimeStamp() == 10+(int)mriRoom.getDuration());
		assertTrue(mriRoom.getDuration()>=30);
	
		
	}

	@Test
	public void testEndMRITesting() {
		System.out.println("\n test End mri testing !!!");
		// INITIALISATION D'UN ED
		
		ED ed = new ED("ED1", "France");
		
		RoomFactory roomFactory  = (RoomFactory) FactoryCreator.getFactory("ROOM");
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
		
				
		MRIRoom mriRoom = (MRIRoom) roomFactory.getRoom(ed, "MRIROOM");
		Patient patient = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		
		
		//TEST
		mriRoom.addOccupant(patient);
		mriRoom.endMRITesting();
		
		System.out.println(patient.getHistory());
		assertTrue(mriRoom.getState().equalsIgnoreCase("free"));
		assertTrue(patient.getState().equalsIgnoreCase("mriTested"));
	}

}
