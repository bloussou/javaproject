package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Emergency.ED;
import Events.Time;
import Factory.FacilityFactory;
import Factory.FactoryCreator;
import Factory.PeopleFactory;
import Factory.RoomFactory;
import HR.Nurse;
import HR.Transporter;

public class TransporterTest {

	@Test
	public void testTransport() {
		fail("Not yet implemented");
	}

	@Test
	public void testBackToPhysician() {
		fail("Not yet implemented");
	}

	@Test
	public void testDropPatient() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLastPatientState() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetLastPatientState() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testSetState(){
		System.out.println("test setState !!!");
		// INITIALISATION D'UN ED
		
		ED ed = new ED("ED1", "France");
		Time time = Time.getInstanceTime();
		
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");		
				
		Transporter transporter = (Transporter) peopleFactory.getStaff("TRANSPORTER", ed);
		
		//test idle
		transporter.setState("idle");
		assertTrue("idle",transporter.getState().equalsIgnoreCase("idle"));
		assertTrue(ed.getDbTransporter().get(0).contains(transporter));
		assertFalse(ed.getDbTransporter().get(1).contains(transporter));
		assertFalse(ed.getDbTransporter().get(2).contains(transporter));
		
		//test transportation
		transporter.setState("transportation");
		assertTrue("transportation",transporter.getState().equalsIgnoreCase("transportation"));
		assertTrue(ed.getDbTransporter().get(1).contains(transporter));
		assertFalse(ed.getDbTransporter().get(0).contains(transporter));
		assertFalse(ed.getDbTransporter().get(2).contains(transporter));
		
		//test ofduty
		transporter.setState("ofDuty");
		assertTrue("ofDuty",transporter.getState().equalsIgnoreCase("ofDuty"));
		assertTrue(ed.getDbTransporter().get(2).contains(transporter));
		assertFalse(ed.getDbTransporter().get(1).contains(transporter));
		assertFalse(ed.getDbTransporter().get(0).contains(transporter));
		
		//test error
		transporter.setState("lol");
		assertFalse("lol",transporter.getState().equalsIgnoreCase("lol"));		
	}

}
