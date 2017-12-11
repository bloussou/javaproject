package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Emergency.ED;
import Events.BloodExamination;
import Events.Time;
import Events.TimeStamp;
import Factory.FactoryCreator;
import Factory.PeopleFactory;
import Factory.RoomFactory;
import HR.Patient;
import Rooms.BloodRoom;

public class BloodExaminationTest {

	@Test
	public void testEndEvent() {
		// INITIALISATION D'UN ED
		
		ED ed = new ED("ED1", "France");
		Time time = Time.getInstanceTime();
		time.timeGoes(10);
		
		RoomFactory roomFactory  = (RoomFactory) FactoryCreator.getFactory("ROOM");
		PeopleFactory peoplefactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
				
		BloodRoom bloodRoom = (BloodRoom) roomFactory.getRoom(ed, "BLOODROOM");
		Patient patient = (Patient) peoplefactory.getPatient(ed, "L1", new TimeStamp());
		bloodRoom.addOccupant(patient);
		
		
		//TEST
		BloodExamination bloodExamination = new BloodExamination(patient, ed, bloodRoom);
		bloodExamination.endEvent();
		
		
		
		
		assertTrue(bloodRoom.getState().equalsIgnoreCase("free"));
		assertTrue(patient.getState().equalsIgnoreCase("bloodtested"));
	}

	@Test
	public void testBloodExamination() {
		// INITIALISATION D'UN ED
		
		ED ed = new ED("ED1", "France");
		Time time = Time.getInstanceTime();
		time.timeGoes(10);
		
		RoomFactory roomFactory  = (RoomFactory) FactoryCreator.getFactory("ROOM");
		PeopleFactory peoplefactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
				
		BloodRoom bloodRoom = (BloodRoom) roomFactory.getRoom(ed, "BLOODROOM");
		Patient patient = (Patient) peoplefactory.getPatient(ed, "L1", new TimeStamp());
		bloodRoom.addOccupant(patient);
		
		
		
		//TEST
		BloodExamination bloodExamination = new BloodExamination(patient, ed, bloodRoom);
		
		assertTrue(patient.getCharges() == bloodRoom.getCost());
		assertTrue(bloodRoom.getStartTime().getTimeStamp() == 20);
		assertTrue(bloodRoom.getEndTime().getTimeStamp() == 20+(int)bloodRoom.getDuration());
		assertTrue(bloodRoom.getDuration()>=30);
		assertTrue(bloodRoom.getState().equalsIgnoreCase("occupied"));
		assertTrue(bloodRoom.getStartTime().equals(bloodExamination.getStartTime()));
		assertTrue(bloodRoom.getEndTime().equals(bloodRoom.getEndTime()));
	}

}
