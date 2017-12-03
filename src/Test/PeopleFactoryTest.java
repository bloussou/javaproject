package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Emergency.ED;
import Events.TimeStamp;
import Factory.FactoryCreator;
import Factory.PeopleFactory;
import HR.Nurse;
import HR.Patient;
import HR.Physician;
import HR.Transporter;

public class PeopleFactoryTest {

	@Test
	public void testGetStaffStringED() {
	// INITIALISATION D'UN ED
		
	ED ed = new ED("ED1", "France");
		
	PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
	
	//TEST
	
	//nurse
	Nurse nurse = (Nurse) peopleFactory.getStaff("NURSE", ed);
	assertTrue(ed.getDbNurse().get(0).contains(nurse));
	
	//physician
	Physician physician = (Physician) peopleFactory.getStaff("PHYSICIAN", ed);
	assertTrue(ed.getDbPhysician().get(0).contains(physician));
	
	//transporter
	Transporter transporter = (Transporter) peopleFactory.getStaff("TRANSPORTER", ed);
	assertTrue(ed.getDbTransporter().get(0).contains(transporter));
	}

	@Test
	public void testGetPatientEDStringTimeStamp() {
		// INITIALISATION D'UN ED
		
		ED ed = new ED("ED1", "France");
			
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
		
		//TEST
		Patient patient = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		assertTrue(ed.getDbPatient().get(0).contains(patient));
		assertTrue(patient.getSeverityLevel().equalsIgnoreCase("L1"));
	}

}
