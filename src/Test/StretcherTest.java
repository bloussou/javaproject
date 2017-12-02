package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Emergency.ED;
import Facilities.Stretcher;
import Factory.FacilityFactory;
import Factory.FactoryCreator;
import Factory.RoomFactory;
import Rooms.Room;

public class StretcherTest {

	@Test
	public void testSetState() {
		System.out.println("test setState !!!");
		// INITIALISATION D'UN ED
		
		ED ed = new ED("ED1", "France");
		
		FacilityFactory facilityFactory  = (FacilityFactory) FactoryCreator.getFactory("FACILITY");
				
		Stretcher stretcher = (Stretcher) facilityFactory.getFacility("STRETCHER", ed);
		
		//test free
		stretcher.setState("free");
		assertTrue("free",stretcher.getState().equalsIgnoreCase("free"));
		assertTrue(ed.getDbStretcher().get(0).contains(stretcher));
		assertFalse(ed.getDbStretcher().get(1).contains(stretcher));
		
		//test occupied
		stretcher.setState("occupied");
		assertTrue("occupied",stretcher.getState().equalsIgnoreCase("occupied"));
		assertTrue(ed.getDbStretcher().get(1).contains(stretcher));
		assertFalse(ed.getDbStretcher().get(0).contains(stretcher));
		
		//test error
		stretcher.setState("lol");
		assertFalse("lol",stretcher.getState().equalsIgnoreCase("lol"));
	}

	@Test
	public void testStretcherEDString() {
		System.out.println("test setState !!!");
		// INITIALISATION D'UN ED
		
		ED ed = new ED("ED1", "France");
		
		FacilityFactory facilityFactory  = (FacilityFactory) FactoryCreator.getFactory("FACILITY");
				
		Stretcher stretcher = (Stretcher) facilityFactory.getFacility("STRETCHER", ed, "1");
		
		//TEST
		assertTrue(stretcher.getEd().equals(ed));
		assertTrue(stretcher.getName().equals("1"));
		assertTrue(ed.getDbStretcher().get(0).contains(stretcher));
		
	}

	@Test
	public void testStretcherED() {
		System.out.println("test setState !!!");
		// INITIALISATION D'UN ED
		
		ED ed = new ED("ED1", "France");
		
		FacilityFactory facilityFactory  = (FacilityFactory) FactoryCreator.getFactory("FACILITY");
				
		Stretcher stretcher = (Stretcher) facilityFactory.getFacility("STRETCHER", ed);
		
		//TEST
		assertTrue(stretcher.getEd().equals(ed));
		assertTrue(ed.getDbStretcher().get(0).contains(stretcher));
		
	}

}
