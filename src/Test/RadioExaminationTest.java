package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Emergency.ED;
import Events.MRIExamination;
import Events.RadioExamination;
import Events.Time;
import Events.TimeStamp;
import Factory.FactoryCreator;
import Factory.PeopleFactory;
import Factory.RoomFactory;
import HR.Patient;
import Rooms.MRIRoom;
import Rooms.RadioRoom;

public class RadioExaminationTest {

	@Test
	public void testEndEvent() {
		// INITIALISATION D'UN ED
		
		ED ed = new ED("ED1", "France");
		Time time = Time.getInstanceTime();
		time.timeGoes(10);
		
		RoomFactory roomFactory  = (RoomFactory) FactoryCreator.getFactory("ROOM");
		PeopleFactory peoplefactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
				
		MRIRoom mriRoom = (MRIRoom) roomFactory.getRoom("MRIROOM", ed);
		Patient patient = (Patient) peoplefactory.getPatient(ed, "L1", new TimeStamp());
		mriRoom.addOccupant(patient);
		
		MRIExamination mriExamination = new MRIExamination(patient, ed, mriRoom);
		
		//TEST
		mriExamination.endEvent();
		
		
		
		
		assertTrue(mriRoom.getState().equalsIgnoreCase("free"));
		assertTrue(patient.getState().equalsIgnoreCase("mritested"));
	}

	@Test
	public void testRadioExamination() {
		// INITIALISATION D'UN ED
		
		ED ed = new ED("ED1", "France");
		Time time = Time.getInstanceTime();
		time.timeGoes(10);
		
		RoomFactory roomFactory  = (RoomFactory) FactoryCreator.getFactory("ROOM");
		PeopleFactory peoplefactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
				
		RadioRoom radioRoom = (RadioRoom) roomFactory.getRoom("RADIOROOM", ed);
		Patient patient = (Patient) peoplefactory.getPatient(ed, "L1", new TimeStamp());
		radioRoom.addOccupant(patient);
		
		
		
		//TEST
		RadioExamination radioExamination = new RadioExamination(patient, ed, radioRoom);
		
		assertTrue(patient.getCharges() == radioRoom.getCost());
		assertTrue(radioRoom.getStartTime().getTimeStamp() == 20);
		assertTrue(radioRoom.getEndTime().getTimeStamp() == 20+(int)radioRoom.getDuration());
		assertTrue(radioRoom.getDuration()>=10);
		assertTrue(radioRoom.getState().equalsIgnoreCase("occupied"));
		assertTrue(radioRoom.getStartTime().equals(radioExamination.getStartTime()));
		assertTrue(radioRoom.getEndTime().equals(radioExamination.getEndTime()));
	}

}
