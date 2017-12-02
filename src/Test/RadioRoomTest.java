package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Emergency.ED;
import Factory.FactoryCreator;
import Factory.RoomFactory;
import Rooms.Room;

public class RadioRoomTest {

	@Test
	public void testSetState() {
		System.out.println("test setState !!!");
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
	public void testRadioTesting() {
		fail("Not yet implemented");
	}

	@Test
	public void testEndRadioTesting() {
		fail("Not yet implemented");
	}

}
