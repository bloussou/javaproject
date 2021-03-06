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
import HR.Physician;
import KPI.DTDT;
import Rooms.BoxRoom;

public class DTDTTest {

	@Test
	public void testCalculate() {
		//initialize the ed
		ED ed = new ED("ED1", "france");
		Time time = Time.getInstanceTime();
		
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
		RoomFactory roomFactory = (RoomFactory) FactoryCreator.getFactory("ROOM");
		
		Patient p1 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		Patient p2 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		Patient p3 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		Patient p4 = (Patient) peopleFactory.getPatient(ed, "L3", new TimeStamp());
		
		Physician phys1 = (Physician) peopleFactory.getStaff(ed, "PHYSICIAN");
		
		BoxRoom box1 = (BoxRoom) roomFactory.getRoom(ed, "BOXROOM");
		
		time.timeGoes(10);
		phys1.handleNewPatient(p1, box1);

		time.timeGoes(10);
		phys1.handleNewPatient(p2, box1);
		p2.setState("released");
		
		time.timeGoes(40);
		phys1.handleNewPatient(p3, box1);
		p3.setState("waitingForBloodTest");
		
		time.timeGoes(40);
		phys1.handleNewPatient(p4, box1);

		assertTrue(DTDT.calculate(ed, "L1") == 30);
		
		System.out.println(DTDT.toString(ed, "L1"));
		assertTrue(DTDT.calculate(ed, "L2") == -1);
		
		assertTrue(DTDT.calculate(ed, "L3") == 100);
	}

}
