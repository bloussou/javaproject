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
import KPI.NumberOfPatientReleased;

public class NumberOfPatientReleasedTest {

	@Test
	public void testCalculate() {
		//initialize the ed
		ED ed = new ED("ED1", "france");
		Time time = Time.getInstanceTime();
		NumberOfPatientReleased nopr = new NumberOfPatientReleased(ed);
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
		
		Patient p1 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		Patient p2 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		Patient p3 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		Patient p4 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		
		
		assertTrue(nopr.calculate() == 0);
		
		time.timeGoes(10);
		p1.setState("released");
		assertTrue(nopr.calculate() == 1);

		time.timeGoes(10);
		p2.setState("released");
		assertTrue(nopr.calculate() == 2);
		
		time.timeGoes(40);
		p3.setState("released");

		
		assertTrue(nopr.calculate() == 3);
		
		
		
	}
}