package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Emergency.ED;
import Events.MRIExamination;
import Events.Time;
import Events.TimeStamp;
import Factory.FactoryCreator;
import Factory.PeopleFactory;
import Factory.RoomFactory;
import HR.Patient;
import Rooms.MRIRoom;
import Rooms.Room;

public class MRIExaminationTest {

	@Test
	public void testEndEvent() {

	}

	@Test
	public void testMRIExamination() {
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
		assertTrue(patient.getCharges() == mriRoom.getCost());
		assertTrue(mriRoom.getStartTime().getTimeStamp() == 10);
		assertTrue(mriRoom.getEndTime().getTimeStamp() == 10+(int)mriRoom.getDuration());
		assertTrue(mriRoom.getDuration()>=30);
		assertTrue(mriRoom.getState().equalsIgnoreCase("occupied"));
	}

}
