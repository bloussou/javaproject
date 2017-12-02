package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Emergency.ED;
import Events.TimeStamp;
import Factory.FactoryCreator;
import Factory.PeopleFactory;
import Factory.RoomFactory;
import HR.Patient;
import Rooms.Room;
import Rooms.WaitingRoom;

public class WaitingRoomTest {

	
	@Test
	public void testSetState() {
		System.out.println("\n test setState !!!");
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
		System.out.println("\n test AddOccupant !!!");
		// INITIALISATION D'UN ED
		
		ED ed = new ED("ED1", "France");
		
		RoomFactory roomFactory  = (RoomFactory) FactoryCreator.getFactory("ROOM");
				
		WaitingRoom waitingRoom = (WaitingRoom) roomFactory.getRoom("WAITINGROOM", ed);
		ArrayList<Patient> patientList = new ArrayList<Patient>() ;
		for (int i = 0; i<21; i++){
			patientList.add(new Patient(ed, "L1", new TimeStamp()));
		}
		
		//TEST
		waitingRoom.addOccupant(patientList.get(0));
		assertTrue(waitingRoom.getState().equalsIgnoreCase("available"));
		assertTrue(waitingRoom.getOccupants().contains(patientList.get(0)));
		assertTrue(ed.getDbWaitingRoom().get(0).contains(waitingRoom));
		
		//looking for the full state
		for (int i=1; i<20; i++){
			waitingRoom.addOccupant(patientList.get(i));
		}
		assertTrue(waitingRoom.getState().equalsIgnoreCase("full"));
		assertTrue(ed.getDbWaitingRoom().get(1).contains(waitingRoom));
	}

	@Test
	public void testRemoveOccupant() {
		System.out.println("\n test AddOccupant !!!");
		// INITIALISATION D'UN ED
		
		ED ed = new ED("ED1", "France");
		
		RoomFactory roomFactory  = (RoomFactory) FactoryCreator.getFactory("ROOM");
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
				
		WaitingRoom waitingRoom = (WaitingRoom) roomFactory.getRoom("WAITINGROOM", ed);
		ArrayList<Patient> patientList = new ArrayList<Patient>() ;
		for (int i = 0; i<21; i++){
			patientList.add((Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp()));
		}
		
		//TEST
		for (int i=0; i<20; i++){
			waitingRoom.addOccupant(patientList.get(i));
		}
		waitingRoom.removeOccupant(patientList.get(20));
		assertTrue(waitingRoom.getState().equalsIgnoreCase("available"));
		
		waitingRoom.removeOccupant(patientList.get(19));
		assertTrue(waitingRoom.getState().equalsIgnoreCase("available"));
		
	}







}
