package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Emergency.ED;
import Events.Time;
import Events.TimeStamp;
import Factory.FactoryCreator;
import Factory.PeopleFactory;
import HR.Patient;
import KPI.LOS;

public class LOSTest {

	@Test
	public void testCalculate() {
		//initialize the ed
		ED ed = new ED("ED1", "france");
		Time time = Time.getInstanceTime();
		
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
		
		Patient p1 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		Patient p2 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		Patient p3 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		Patient p4 = (Patient) peopleFactory.getPatient(ed, "L3", new TimeStamp());
		time.timeGoes(10);
		p1.setState("released");

		time.timeGoes(10);
		p2.setState("released");
		
		time.timeGoes(40);
		p3.setState("released");
		
		time.timeGoes(40);
		p4.setState("released");
		
		
		LOS losL1 = new LOS(ed,"L1");
		System.out.println(losL1.toString());
		assertTrue(losL1.calculate() == 30);
		
		LOS losL2 = new LOS(ed,"L2");
		assertTrue(losL2.calculate() == -1);
		
		LOS losL3 = new LOS(ed,"L3");
		assertTrue(losL3.calculate() == 100);
		
		
		
		
	}


}
