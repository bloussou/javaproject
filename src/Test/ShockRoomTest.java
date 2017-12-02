package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Emergency.ED;
import Factory.FactoryCreator;
import Factory.RoomFactory;
import Rooms.Room;

public class ShockRoomTest {

	@Test
	public void testSetState() {
		System.out.println("test setState !!!");
		// INITIALISATION D'UN ED
		
		ED ed = new ED("ED1", "France");
		
		RoomFactory roomFactory  = (RoomFactory) FactoryCreator.getFactory("ROOM");
				
		Room shockRoom = (Room) roomFactory.getRoom("SHOCKROOM", ed);
		
		//test free
		shockRoom.setState("free");
		assertTrue("free",shockRoom.getState().equalsIgnoreCase("free"));
		assertTrue(ed.getDbShockRoom().get(0).contains(shockRoom));
		assertFalse(ed.getDbShockRoom().get(1).contains(shockRoom));
		
		//test occupied
		shockRoom.setState("occupied");
		assertTrue("occupied",shockRoom.getState().equalsIgnoreCase("occupied"));
		assertTrue(ed.getDbShockRoom().get(1).contains(shockRoom));
		assertFalse(ed.getDbShockRoom().get(0).contains(shockRoom));
		
		//test error
		shockRoom.setState("lol");
		assertFalse("lol",shockRoom.getState().equalsIgnoreCase("lol"));
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

}
