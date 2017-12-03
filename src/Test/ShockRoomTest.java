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
import Rooms.Room;
import Rooms.ShockRoom;

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
	public void testUpdatePatientCharge() {
		System.out.println("\n test update patient charges");
		// INITIALISATION D'UN ED
		
		ED ed = new ED("ED1", "France");
		Time time = Time.getInstanceTime();
		time.timeGoes(10);
		
		RoomFactory roomFactory  = (RoomFactory) FactoryCreator.getFactory("ROOM");
		PeopleFactory peoplefactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
				
		ShockRoom shockRoom = (ShockRoom) roomFactory.getRoom("SHOCKROOM", ed);
		Patient patient = (Patient) peoplefactory.getPatient(ed, "L1", new TimeStamp());
		
		
		//TEST
		shockRoom.updatePatientCharge(patient);
		
		assertTrue(patient.getCharges() == shockRoom.getCost());
	}

}
