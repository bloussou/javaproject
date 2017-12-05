package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Emergency.ED;
import Events.Transport_Transporter;
import Factory.FactoryCreator;
import Factory.PeopleFactory;
import HR.Transporter;

public class Transport_TransporterTest {

	@Test
	public void testEndEvent() {
		fail("Not yet implemented");
	}

	@Test
	public void testTransport_Transporter() {
		System.out.println("\n test setState !!!");
		// INITIALISATION D'UN ED
		
		ED ed = new ED("ED1", "France");
		
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");		
				
		Transporter transporter = (Transporter) peopleFactory.getStaff("TRANSPORTER", ed);
		
		Transport_Transporter transport_Transporter = new Transport_Transporter(ed, patient, transporter, targetRoom);
		
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

}
