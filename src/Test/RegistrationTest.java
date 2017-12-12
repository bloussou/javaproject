package Test;

import static org.junit.Assert.*;

import org.junit.Test;
import Emergency.ED;
import Events.Registration;
import Events.Time;
import Events.TimeStamp;
import Factory.FactoryCreator;
import Factory.PeopleFactory;
import HR.Nurse;
import HR.Patient;

public class RegistrationTest {

	@Test
	public void testRegistration() {
		
		// INITIALISATION D'UN ED
		ED ed = new ED("ED1", "France");
		Time time = Time.getInstanceTime();
		
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
		
		Patient patient = (Patient) peopleFactory.getPatient(ed, "L1" , new TimeStamp());
		
		Nurse nurse = (Nurse) peopleFactory.getStaff(ed,"NURSE");

				
		// PROCEED
		time.timeGoes(11);
		Registration registration = new Registration(ed, patient, nurse);		
		
		// CHECK 
		assertTrue("1.1", ed.getEdRegister().contains(patient));
		assertTrue("2.1", registration.getStartTime().toString().equals(time.toString()));
		assertTrue("2.2", registration.getEndTime().toString().equals(time.toString()));
	}

}
