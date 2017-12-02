package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Emergency.ED;
import Factory.FactoryCreator;
import Factory.RoomFactory;
import Rooms.Room;

public class BoxRoomTest {

	@Test
	public void testSetState() {
		System.out.println("test setState !!!");
		// INITIALISATION D'UN ED
		
		ED ed = new ED("ED1", "France");
		
		RoomFactory roomFactory  = (RoomFactory) FactoryCreator.getFactory("ROOM");
				
		Room boxRoom = (Room) roomFactory.getRoom("BOXROOM", ed);
		
		//test free
		boxRoom.setState("free");
		assertTrue("free",boxRoom.getState().equalsIgnoreCase("free"));
		assertTrue(ed.getDbBoxRoom().get(0).contains(boxRoom));
		assertFalse(ed.getDbBoxRoom().get(1).contains(boxRoom));
		
		//test occupied
		boxRoom.setState("occupied");
		assertTrue("occupied",boxRoom.getState().equalsIgnoreCase("occupied"));
		assertTrue(ed.getDbBoxRoom().get(1).contains(boxRoom));
		assertFalse(ed.getDbBoxRoom().get(0).contains(boxRoom));
		
		//test error
		boxRoom.setState("lol");
		assertFalse("lol",boxRoom.getState().equalsIgnoreCase("lol"));
	}




	@Test
	public void testUpdatePatientCharge() {
		fail("Not yet implemented");
	}

}
