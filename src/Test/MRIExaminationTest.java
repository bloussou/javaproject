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

public class MRIExaminationTest {

	@Test
	public void testEndEvent() {
		// INITIALISATION D'UN ED
		
		ED ed = new ED("ED1", "France");
		Time time = Time.getInstanceTime();
		time.timeGoes(10);
		
		RoomFactory roomFactory  = (RoomFactory) FactoryCreator.getFactory("ROOM");
		PeopleFactory peoplefactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
				
		RadioRoom radioRoom = (RadioRoom) roomFactory.getRoom(ed, "RADIOROOM");
		Patient patient = (Patient) peoplefactory.getPatient(ed, "L1", new TimeStamp());
		radioRoom.addOccupant(patient);
		
		RadioExamination radioExamination = new RadioExamination(patient, ed, radioRoom);
		
		//TEST
		radioExamination.endEvent();
		
		
		
		
		assertTrue(radioRoom.getState().equalsIgnoreCase("free"));
		assertTrue(patient.getState().equalsIgnoreCase("radiotested"));
	}

	@Test
	public void testMRIExamination() {
		// INITIALISATION D'UN ED
		
		ED ed = new ED("ED1", "France");
		Time time = Time.getInstanceTime();
		time.timeGoes(10);
		
		RoomFactory roomFactory  = (RoomFactory) FactoryCreator.getFactory("ROOM");
		PeopleFactory peoplefactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
				
		MRIRoom mriRoom = (MRIRoom) roomFactory.getRoom(ed, "MRIROOM");
		Patient patient = (Patient) peoplefactory.getPatient(ed, "L1", new TimeStamp());
		mriRoom.addOccupant(patient);
		
		
		
		//TEST
		MRIExamination mriExamination = new MRIExamination(patient, ed, mriRoom);
		
		assertTrue(patient.getCharges() == mriRoom.getCost());
		assertTrue(mriRoom.getStartTime().getTimeStamp() == 20);
		assertTrue(mriRoom.getEndTime().getTimeStamp() == 20+(int)mriRoom.getDuration());
		assertTrue(mriRoom.getDuration()>=30);
		assertTrue(mriRoom.getState().equalsIgnoreCase("occupied"));
		assertTrue(mriRoom.getStartTime().equals(mriExamination.getStartTime()));
		assertTrue(mriRoom.getEndTime().equals(mriRoom.getEndTime()));
	}

}
