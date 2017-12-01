package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Emergency.ED;
import Events.Time;
import Events.TimeStamp;
import HR.Patient;
import HR.Physician;
import Rooms.WaitingRoom;

public class WaitingRoomTest {

	@Test
	public void testAddOccupant() {
		ED ed = new ED("ed1","france");
		Time time = Time.getInstanceTime();
		time.timeGoes(10);
		WaitingRoom waiting1 = new WaitingRoom(ed);
		Patient patient1 = new Patient(ed,"L1",new TimeStamp());
		Patient patient2 = new Patient(ed,"L2", new TimeStamp());
		waiting1.addOccupant(patient);
		assertTrue(waiting1.getOccupants().equals([patient]));
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
