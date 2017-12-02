package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Emergency.ED;
import Factory.FactoryCreator;
import Factory.RoomFactory;
import Rooms.Room;

public class MRIRoomTest {

	@Test
	public void testSetState() {
		System.out.println("test setState !!!");
		// INITIALISATION D'UN ED
		
		ED ed = new ED("ED1", "France");
		
		RoomFactory roomFactory  = (RoomFactory) FactoryCreator.getFactory("ROOM");
				
		Room mriRoom = (Room) roomFactory.getRoom("MRIROOM", ed);
		
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
	public void testMriTesting() {
		fail("Not yet implemented");
	}

	@Test
	public void testEndMRITesting() {
		fail("Not yet implemented");
	}

}
