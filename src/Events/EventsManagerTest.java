package Events;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Emergency.ED;
import Factory.FactoryCreator;
import Factory.PeopleFactory;
import Factory.RoomFactory;
import HR.Patient;
import HR.Physician;
import Rooms.ShockRoom;

public class EventsManagerTest {

	@Test
	public void testInsertNewEvent() {

		// INITIALISATION D'UN ED
		ED ed = new ED("ED1", "France");
		Time time = Time.getInstanceTime();
		EventsManager simulator = new EventsManager();
		
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
		RoomFactory roomFactory = (RoomFactory) FactoryCreator.getFactory("ROOM");
		
		Patient patient = (Patient) peopleFactory.getPatient(ed, "L1" , new TimeStamp());
		Physician physician = (Physician) peopleFactory.getStaff("PHYSICIAN", ed);
		ShockRoom sRoom = (ShockRoom) roomFactory.getRoom("SHOCKROOM", ed);
		
		Consultation consultation1 = new Consultation(ed, patient, physician, sRoom);
		Consultation consultation2 = new Consultation(ed, patient, physician, sRoom);
		Consultation consultation3 = new Consultation(ed, patient, physician, sRoom);
		
		consultation1.setEndTime(new TimeStamp(15));
		consultation2.setEndTime(new TimeStamp(2));
		consultation3.setEndTime(new TimeStamp(10));
		ArrayList<Event> trylist = new ArrayList<Event>();
		trylist.add(consultation2);
		trylist.add(consultation3);
		trylist.add(consultation1);
		
		//PROCEED
		simulator.insertNewEvent(consultation1);
		simulator.insertNewEvent(consultation2);
		simulator.insertNewEvent(consultation3);
		
		assertTrue("1.0", simulator.getInProgress().equals(trylist));
		
	}

}
