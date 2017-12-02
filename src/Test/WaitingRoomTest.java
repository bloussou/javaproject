package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Emergency.ED;
import Events.Time;
import Events.TimeStamp;
import Factory.FactoryCreator;
import Factory.RoomFactory;
import HR.Patient;
import HR.Physician;
import Rooms.Room;
import Rooms.WaitingRoom;

public class WaitingRoomTest {

	
	@Test
	public void testSetState() {
		System.out.println("test setState !!!");
		// INITIALISATION D'UN ED
		
		ED ed = new ED("ED1", "France");
		
		RoomFactory roomFactory  = (RoomFactory) FactoryCreator.getFactory("ROOM");
				
		Room waitingRoom = (Room) roomFactory.getRoom("WAITINGROOM", ed);
		
		//test available
		waitingRoom.setState("available");
		assertTrue("available",waitingRoom.getState().equalsIgnoreCase("available"));
		assertTrue(ed.getDbWaitingRoom().get(0).contains(waitingRoom));
		assertFalse(ed.getDbWaitingRoom().get(1).contains(waitingRoom));
		
		//test occupied
		waitingRoom.setState("full");
		assertTrue("full",waitingRoom.getState().equalsIgnoreCase("full"));
		assertTrue(ed.getDbWaitingRoom().get(1).contains(waitingRoom));
		assertFalse(ed.getDbWaitingRoom().get(0).contains(waitingRoom));
		
		//test error
		waitingRoom.setState("lol");
		assertFalse("lol",waitingRoom.getState().equalsIgnoreCase("lol"));
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
	public void testWaitingRoomEDString() {
		fail("Not yet implemented");
	}

	@Test
	public void testWaitingRoomED() {
		fail("Not yet implemented");
	}



}
