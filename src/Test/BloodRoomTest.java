package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Emergency.ED;
import Events.Time;
import Factory.FactoryCreator;
import Factory.PeopleFactory;
import Factory.RoomFactory;
import HR.Nurse;
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
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveOccupant() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdatePatientCharge() {
		fail("Not yet implemented");
	}

	@Test
	public void testBloodTesting() {
		fail("Not yet implemented");
	}

	@Test
	public void testEndBloodTesting() {
		fail("Not yet implemented");
	}

}
