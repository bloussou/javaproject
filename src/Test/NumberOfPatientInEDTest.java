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
import KPI.NumberOfPatientInED;

public class NumberOfPatientInEDTest {

	@Test
	public void testCalculate() {
		//initialize the ed
		ED ed = new ED("ED1", "france");
		Time time = Time.getInstanceTime();
		
		NumberOfPatientInED nopied = new NumberOfPatientInED(ed);
		
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
		assertTrue(nopied.calculate() == 0);
		
		Patient p1 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		assertTrue(nopied.calculate() == 1);
		
		Patient p2 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		assertTrue(nopied.calculate() == 2);
		Patient p3 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		Patient p4 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		
		
		time.timeGoes(10);
		p1.setState("waitingforbloodTest");
		assertTrue(nopied.calculate() == 4);
		time.timeGoes(10);
		p2.setState("released");
		assertTrue(nopied.calculate() == 4);
		
		time.timeGoes(40);
		p3.setState("inconsultation");

		assertTrue(nopied.calculate() == 4);
		
		
	
	}

}
