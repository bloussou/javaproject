package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Emergency.ED;
import Events.Time;
import Events.TimeStamp;
import Factory.FactoryCreator;
import Factory.PeopleFactory;
import HR.Nurse;
import HR.Patient;

public class PatientTest {

	@Test
	public void testSetState() {
		System.out.println("test setState !!!");
		// INITIALISATION D'UN ED
		
		ED ed = new ED("ED1", "France");
		Time time = Time.getInstanceTime();
		
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
				
		Patient patient = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		
		//test arrived
		patient.setState("arrived");
		assertTrue("arrived",patient.getState().equalsIgnoreCase("arrived"));
		assertTrue(ed.getDbPatient().get(0).contains(patient));
		assertFalse(ed.getDbPatient().get(1).contains(patient));
		assertFalse(ed.getDbPatient().get(2).contains(patient));
		assertFalse(ed.getDbPatient().get(3).contains(patient));
		assertFalse(ed.getDbPatient().get(4).contains(patient));
		assertFalse(ed.getDbPatient().get(5).contains(patient));
		assertFalse(ed.getDbPatient().get(6).contains(patient));
		assertFalse(ed.getDbPatient().get(7).contains(patient));
		assertFalse(ed.getDbPatient().get(8).contains(patient));
		assertFalse(ed.getDbPatient().get(9).contains(patient));
		assertFalse(ed.getDbPatient().get(10).contains(patient));
		assertFalse(ed.getDbPatient().get(11).contains(patient));
		assertFalse(ed.getDbPatient().get(12).contains(patient));
		assertFalse(ed.getDbPatient().get(13).contains(patient));
		assertFalse(ed.getDbPatient().get(14).contains(patient));
		assertFalse(ed.getDbPatient().get(15).contains(patient));
		
		//test registered
				patient.setState("registered");
				assertTrue("registered",patient.getState().equalsIgnoreCase("registered"));
				assertTrue(ed.getDbPatient().get(1).contains(patient));
				assertFalse(ed.getDbPatient().get(0).contains(patient));
				assertFalse(ed.getDbPatient().get(2).contains(patient));
				assertFalse(ed.getDbPatient().get(3).contains(patient));
				assertFalse(ed.getDbPatient().get(4).contains(patient));
				assertFalse(ed.getDbPatient().get(5).contains(patient));
				assertFalse(ed.getDbPatient().get(6).contains(patient));
				assertFalse(ed.getDbPatient().get(7).contains(patient));
				assertFalse(ed.getDbPatient().get(8).contains(patient));
				assertFalse(ed.getDbPatient().get(9).contains(patient));
				assertFalse(ed.getDbPatient().get(10).contains(patient));
				assertFalse(ed.getDbPatient().get(11).contains(patient));
				assertFalse(ed.getDbPatient().get(12).contains(patient));
				assertFalse(ed.getDbPatient().get(13).contains(patient));
				assertFalse(ed.getDbPatient().get(14).contains(patient));
				assertFalse(ed.getDbPatient().get(15).contains(patient));

				//test arrived
				patient.setState("transporting");
				assertTrue("transporting",patient.getState().equalsIgnoreCase("transporting"));
				assertTrue(ed.getDbPatient().get(2).contains(patient));
				assertFalse(ed.getDbPatient().get(1).contains(patient));
				assertFalse(ed.getDbPatient().get(0).contains(patient));
				assertFalse(ed.getDbPatient().get(3).contains(patient));
				assertFalse(ed.getDbPatient().get(4).contains(patient));
				assertFalse(ed.getDbPatient().get(5).contains(patient));
				assertFalse(ed.getDbPatient().get(6).contains(patient));
				assertFalse(ed.getDbPatient().get(7).contains(patient));
				assertFalse(ed.getDbPatient().get(8).contains(patient));
				assertFalse(ed.getDbPatient().get(9).contains(patient));
				assertFalse(ed.getDbPatient().get(10).contains(patient));
				assertFalse(ed.getDbPatient().get(11).contains(patient));
				assertFalse(ed.getDbPatient().get(12).contains(patient));
				assertFalse(ed.getDbPatient().get(13).contains(patient));
				assertFalse(ed.getDbPatient().get(14).contains(patient));
				assertFalse(ed.getDbPatient().get(15).contains(patient));
				
				//test arrived
				patient.setState("waitingForConsultation");
				assertTrue("waitingForConsultation",patient.getState().equalsIgnoreCase("waitingForConsultation"));
				assertTrue(ed.getDbPatient().get(3).contains(patient));
				assertFalse(ed.getDbPatient().get(1).contains(patient));
				assertFalse(ed.getDbPatient().get(2).contains(patient));
				assertFalse(ed.getDbPatient().get(0).contains(patient));
				assertFalse(ed.getDbPatient().get(4).contains(patient));
				assertFalse(ed.getDbPatient().get(5).contains(patient));
				assertFalse(ed.getDbPatient().get(6).contains(patient));
				assertFalse(ed.getDbPatient().get(7).contains(patient));
				assertFalse(ed.getDbPatient().get(8).contains(patient));
				assertFalse(ed.getDbPatient().get(9).contains(patient));
				assertFalse(ed.getDbPatient().get(10).contains(patient));
				assertFalse(ed.getDbPatient().get(11).contains(patient));
				assertFalse(ed.getDbPatient().get(12).contains(patient));
				assertFalse(ed.getDbPatient().get(13).contains(patient));
				assertFalse(ed.getDbPatient().get(14).contains(patient));
				assertFalse(ed.getDbPatient().get(15).contains(patient));
				
				//test arrived
				patient.setState("inConsultation");
				assertTrue("inConsultation",patient.getState().equalsIgnoreCase("inConsultation"));
				assertTrue(ed.getDbPatient().get(4).contains(patient));
				assertFalse(ed.getDbPatient().get(1).contains(patient));
				assertFalse(ed.getDbPatient().get(2).contains(patient));
				assertFalse(ed.getDbPatient().get(3).contains(patient));
				assertFalse(ed.getDbPatient().get(0).contains(patient));
				assertFalse(ed.getDbPatient().get(5).contains(patient));
				assertFalse(ed.getDbPatient().get(6).contains(patient));
				assertFalse(ed.getDbPatient().get(7).contains(patient));
				assertFalse(ed.getDbPatient().get(8).contains(patient));
				assertFalse(ed.getDbPatient().get(9).contains(patient));
				assertFalse(ed.getDbPatient().get(10).contains(patient));
				assertFalse(ed.getDbPatient().get(11).contains(patient));
				assertFalse(ed.getDbPatient().get(12).contains(patient));
				assertFalse(ed.getDbPatient().get(13).contains(patient));
				assertFalse(ed.getDbPatient().get(14).contains(patient));
				assertFalse(ed.getDbPatient().get(15).contains(patient));
				
				//test arrived
				patient.setState("waitingForMRI");
				assertTrue("waitingForMRI",patient.getState().equalsIgnoreCase("waitingForMRI"));
				assertTrue(ed.getDbPatient().get(5).contains(patient));
				assertFalse(ed.getDbPatient().get(1).contains(patient));
				assertFalse(ed.getDbPatient().get(2).contains(patient));
				assertFalse(ed.getDbPatient().get(3).contains(patient));
				assertFalse(ed.getDbPatient().get(4).contains(patient));
				assertFalse(ed.getDbPatient().get(0).contains(patient));
				assertFalse(ed.getDbPatient().get(6).contains(patient));
				assertFalse(ed.getDbPatient().get(7).contains(patient));
				assertFalse(ed.getDbPatient().get(8).contains(patient));
				assertFalse(ed.getDbPatient().get(9).contains(patient));
				assertFalse(ed.getDbPatient().get(10).contains(patient));
				assertFalse(ed.getDbPatient().get(11).contains(patient));
				assertFalse(ed.getDbPatient().get(12).contains(patient));
				assertFalse(ed.getDbPatient().get(13).contains(patient));
				assertFalse(ed.getDbPatient().get(14).contains(patient));
				assertFalse(ed.getDbPatient().get(15).contains(patient));
				
				//test arrived
				patient.setState("waitingForBloodTest");
				assertTrue("waitingForBloodTest",patient.getState().equalsIgnoreCase("waitingForBloodTest"));
				assertTrue(ed.getDbPatient().get(6).contains(patient));
				assertFalse(ed.getDbPatient().get(1).contains(patient));
				assertFalse(ed.getDbPatient().get(2).contains(patient));
				assertFalse(ed.getDbPatient().get(3).contains(patient));
				assertFalse(ed.getDbPatient().get(4).contains(patient));
				assertFalse(ed.getDbPatient().get(5).contains(patient));
				assertFalse(ed.getDbPatient().get(0).contains(patient));
				assertFalse(ed.getDbPatient().get(7).contains(patient));
				assertFalse(ed.getDbPatient().get(8).contains(patient));
				assertFalse(ed.getDbPatient().get(9).contains(patient));
				assertFalse(ed.getDbPatient().get(10).contains(patient));
				assertFalse(ed.getDbPatient().get(11).contains(patient));
				assertFalse(ed.getDbPatient().get(12).contains(patient));
				assertFalse(ed.getDbPatient().get(13).contains(patient));
				assertFalse(ed.getDbPatient().get(14).contains(patient));
				assertFalse(ed.getDbPatient().get(15).contains(patient));
				
				//test arrived
				patient.setState("waitingForRadio");
				assertTrue("waitingForRadio",patient.getState().equalsIgnoreCase("waitingForRadio"));
				assertTrue(ed.getDbPatient().get(7).contains(patient));
				assertFalse(ed.getDbPatient().get(1).contains(patient));
				assertFalse(ed.getDbPatient().get(2).contains(patient));
				assertFalse(ed.getDbPatient().get(3).contains(patient));
				assertFalse(ed.getDbPatient().get(4).contains(patient));
				assertFalse(ed.getDbPatient().get(5).contains(patient));
				assertFalse(ed.getDbPatient().get(6).contains(patient));
				assertFalse(ed.getDbPatient().get(0).contains(patient));
				assertFalse(ed.getDbPatient().get(8).contains(patient));
				assertFalse(ed.getDbPatient().get(9).contains(patient));
				assertFalse(ed.getDbPatient().get(10).contains(patient));
				assertFalse(ed.getDbPatient().get(11).contains(patient));
				assertFalse(ed.getDbPatient().get(12).contains(patient));
				assertFalse(ed.getDbPatient().get(13).contains(patient));
				assertFalse(ed.getDbPatient().get(14).contains(patient));
				assertFalse(ed.getDbPatient().get(15).contains(patient));
				
				//test arrived
				patient.setState("transportation");
				assertTrue("transportation",patient.getState().equalsIgnoreCase("transportation"));
				assertTrue(ed.getDbPatient().get(8).contains(patient));
				assertFalse(ed.getDbPatient().get(1).contains(patient));
				assertFalse(ed.getDbPatient().get(2).contains(patient));
				assertFalse(ed.getDbPatient().get(3).contains(patient));
				assertFalse(ed.getDbPatient().get(4).contains(patient));
				assertFalse(ed.getDbPatient().get(5).contains(patient));
				assertFalse(ed.getDbPatient().get(6).contains(patient));
				assertFalse(ed.getDbPatient().get(7).contains(patient));
				assertFalse(ed.getDbPatient().get(0).contains(patient));
				assertFalse(ed.getDbPatient().get(9).contains(patient));
				assertFalse(ed.getDbPatient().get(10).contains(patient));
				assertFalse(ed.getDbPatient().get(11).contains(patient));
				assertFalse(ed.getDbPatient().get(12).contains(patient));
				assertFalse(ed.getDbPatient().get(13).contains(patient));
				assertFalse(ed.getDbPatient().get(14).contains(patient));
				assertFalse(ed.getDbPatient().get(15).contains(patient));
				
				//test arrived
				patient.setState("waitingForMRIT");
				assertTrue("waitingForMRIT",patient.getState().equalsIgnoreCase("waitingForMRIT"));
				assertTrue(ed.getDbPatient().get(9).contains(patient));
				assertFalse(ed.getDbPatient().get(1).contains(patient));
				assertFalse(ed.getDbPatient().get(2).contains(patient));
				assertFalse(ed.getDbPatient().get(3).contains(patient));
				assertFalse(ed.getDbPatient().get(4).contains(patient));
				assertFalse(ed.getDbPatient().get(5).contains(patient));
				assertFalse(ed.getDbPatient().get(6).contains(patient));
				assertFalse(ed.getDbPatient().get(7).contains(patient));
				assertFalse(ed.getDbPatient().get(8).contains(patient));
				assertFalse(ed.getDbPatient().get(0).contains(patient));
				assertFalse(ed.getDbPatient().get(10).contains(patient));
				assertFalse(ed.getDbPatient().get(11).contains(patient));
				assertFalse(ed.getDbPatient().get(12).contains(patient));
				assertFalse(ed.getDbPatient().get(13).contains(patient));
				assertFalse(ed.getDbPatient().get(14).contains(patient));
				assertFalse(ed.getDbPatient().get(15).contains(patient));
				
				//test arrived
				patient.setState("waitingForBloodTestT");
				assertTrue("waitingForBloodTestT",patient.getState().equalsIgnoreCase("waitingForBloodTestT"));
				assertTrue(ed.getDbPatient().get(10).contains(patient));
				assertFalse(ed.getDbPatient().get(1).contains(patient));
				assertFalse(ed.getDbPatient().get(2).contains(patient));
				assertFalse(ed.getDbPatient().get(3).contains(patient));
				assertFalse(ed.getDbPatient().get(4).contains(patient));
				assertFalse(ed.getDbPatient().get(5).contains(patient));
				assertFalse(ed.getDbPatient().get(6).contains(patient));
				assertFalse(ed.getDbPatient().get(7).contains(patient));
				assertFalse(ed.getDbPatient().get(8).contains(patient));
				assertFalse(ed.getDbPatient().get(9).contains(patient));
				assertFalse(ed.getDbPatient().get(0).contains(patient));
				assertFalse(ed.getDbPatient().get(11).contains(patient));
				assertFalse(ed.getDbPatient().get(12).contains(patient));
				assertFalse(ed.getDbPatient().get(13).contains(patient));
				assertFalse(ed.getDbPatient().get(14).contains(patient));
				assertFalse(ed.getDbPatient().get(15).contains(patient));
				
				//test arrived
				patient.setState("waitingForRadioT");
				assertTrue("waitingForRadioT",patient.getState().equalsIgnoreCase("waitingForRadioT"));
				assertTrue(ed.getDbPatient().get(11).contains(patient));
				assertFalse(ed.getDbPatient().get(1).contains(patient));
				assertFalse(ed.getDbPatient().get(2).contains(patient));
				assertFalse(ed.getDbPatient().get(3).contains(patient));
				assertFalse(ed.getDbPatient().get(4).contains(patient));
				assertFalse(ed.getDbPatient().get(5).contains(patient));
				assertFalse(ed.getDbPatient().get(6).contains(patient));
				assertFalse(ed.getDbPatient().get(7).contains(patient));
				assertFalse(ed.getDbPatient().get(8).contains(patient));
				assertFalse(ed.getDbPatient().get(9).contains(patient));
				assertFalse(ed.getDbPatient().get(10).contains(patient));
				assertFalse(ed.getDbPatient().get(0).contains(patient));
				assertFalse(ed.getDbPatient().get(12).contains(patient));
				assertFalse(ed.getDbPatient().get(13).contains(patient));
				assertFalse(ed.getDbPatient().get(14).contains(patient));
				assertFalse(ed.getDbPatient().get(15).contains(patient));
				
				//test arrived
				patient.setState("bloodTested");
				assertTrue("bloodTested",patient.getState().equalsIgnoreCase("bloodTested"));
				assertTrue(ed.getDbPatient().get(12).contains(patient));
				assertFalse(ed.getDbPatient().get(1).contains(patient));
				assertFalse(ed.getDbPatient().get(2).contains(patient));
				assertFalse(ed.getDbPatient().get(3).contains(patient));
				assertFalse(ed.getDbPatient().get(4).contains(patient));
				assertFalse(ed.getDbPatient().get(5).contains(patient));
				assertFalse(ed.getDbPatient().get(6).contains(patient));
				assertFalse(ed.getDbPatient().get(7).contains(patient));
				assertFalse(ed.getDbPatient().get(8).contains(patient));
				assertFalse(ed.getDbPatient().get(9).contains(patient));
				assertFalse(ed.getDbPatient().get(10).contains(patient));
				assertFalse(ed.getDbPatient().get(11).contains(patient));
				assertFalse(ed.getDbPatient().get(0).contains(patient));
				assertFalse(ed.getDbPatient().get(13).contains(patient));
				assertFalse(ed.getDbPatient().get(14).contains(patient));
				assertFalse(ed.getDbPatient().get(15).contains(patient));
				
				//test arrived
				patient.setState("mriTested");
				assertTrue("mriTested",patient.getState().equalsIgnoreCase("mriTested"));
				assertTrue(ed.getDbPatient().get(13).contains(patient));
				assertFalse(ed.getDbPatient().get(1).contains(patient));
				assertFalse(ed.getDbPatient().get(2).contains(patient));
				assertFalse(ed.getDbPatient().get(3).contains(patient));
				assertFalse(ed.getDbPatient().get(4).contains(patient));
				assertFalse(ed.getDbPatient().get(5).contains(patient));
				assertFalse(ed.getDbPatient().get(6).contains(patient));
				assertFalse(ed.getDbPatient().get(7).contains(patient));
				assertFalse(ed.getDbPatient().get(8).contains(patient));
				assertFalse(ed.getDbPatient().get(9).contains(patient));
				assertFalse(ed.getDbPatient().get(10).contains(patient));
				assertFalse(ed.getDbPatient().get(11).contains(patient));
				assertFalse(ed.getDbPatient().get(12).contains(patient));
				assertFalse(ed.getDbPatient().get(0).contains(patient));
				assertFalse(ed.getDbPatient().get(14).contains(patient));
				assertFalse(ed.getDbPatient().get(15).contains(patient));
				
				//test arrived
				patient.setState("radioTested");
				assertTrue("radioTested",patient.getState().equalsIgnoreCase("radioTested"));
				assertTrue(ed.getDbPatient().get(14).contains(patient));
				assertFalse(ed.getDbPatient().get(1).contains(patient));
				assertFalse(ed.getDbPatient().get(2).contains(patient));
				assertFalse(ed.getDbPatient().get(3).contains(patient));
				assertFalse(ed.getDbPatient().get(4).contains(patient));
				assertFalse(ed.getDbPatient().get(5).contains(patient));
				assertFalse(ed.getDbPatient().get(6).contains(patient));
				assertFalse(ed.getDbPatient().get(7).contains(patient));
				assertFalse(ed.getDbPatient().get(8).contains(patient));
				assertFalse(ed.getDbPatient().get(9).contains(patient));
				assertFalse(ed.getDbPatient().get(10).contains(patient));
				assertFalse(ed.getDbPatient().get(11).contains(patient));
				assertFalse(ed.getDbPatient().get(12).contains(patient));
				assertFalse(ed.getDbPatient().get(13).contains(patient));
				assertFalse(ed.getDbPatient().get(0).contains(patient));
				assertFalse(ed.getDbPatient().get(15).contains(patient));
				
				//test arrived
				patient.setState("released");
				assertTrue("released",patient.getState().equalsIgnoreCase("released"));
				assertTrue(ed.getDbPatient().get(15).contains(patient));
				assertFalse(ed.getDbPatient().get(1).contains(patient));
				assertFalse(ed.getDbPatient().get(2).contains(patient));
				assertFalse(ed.getDbPatient().get(3).contains(patient));
				assertFalse(ed.getDbPatient().get(4).contains(patient));
				assertFalse(ed.getDbPatient().get(5).contains(patient));
				assertFalse(ed.getDbPatient().get(6).contains(patient));
				assertFalse(ed.getDbPatient().get(7).contains(patient));
				assertFalse(ed.getDbPatient().get(8).contains(patient));
				assertFalse(ed.getDbPatient().get(9).contains(patient));
				assertFalse(ed.getDbPatient().get(10).contains(patient));
				assertFalse(ed.getDbPatient().get(11).contains(patient));
				assertFalse(ed.getDbPatient().get(12).contains(patient));
				assertFalse(ed.getDbPatient().get(13).contains(patient));
				assertFalse(ed.getDbPatient().get(14).contains(patient));
				assertFalse(ed.getDbPatient().get(0).contains(patient));
				
				
		//test error
		patient.setState("lol");
		assertFalse("lol",patient.getState().equalsIgnoreCase("lol"));
	}

}
