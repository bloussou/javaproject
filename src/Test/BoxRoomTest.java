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
import Rooms.BoxRoom;
import Rooms.Room;

public class BoxRoomTest {

	@Test
	public void testSetState() {
		System.out.println("test setState !!!");
		// INITIALISATION D'UN ED
		
		ED ed = new ED("ED1", "France");
		
		RoomFactory roomFactory  = (RoomFactory) FactoryCreator.getFactory("ROOM");
				
		Room boxRoom = (Room) roomFactory.getRoom(ed, "BOXROOM");
		
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
		System.out.println("\n test update patient charges");
		// INITIALISATION D'UN ED
		
		ED ed = new ED("ED1", "France");
		Time time = Time.getInstanceTime();
		time.timeGoes(10);
		
		RoomFactory roomFactory  = (RoomFactory) FactoryCreator.getFactory("ROOM");
		PeopleFactory peoplefactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
				
		BoxRoom boxRoom = (BoxRoom) roomFactory.getRoom(ed, "BOXROOM");
		Patient patient = (Patient) peoplefactory.getPatient(ed, "L1", new TimeStamp());
		
		
		//TEST
		boxRoom.updatePatientCharge(patient);
		
		assertTrue(patient.getCharges() == boxRoom.getCost());
	}

}
