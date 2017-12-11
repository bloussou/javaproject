package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Emergency.ED;
import Facilities.Stretcher;
import Factory.FacilityFactory;
import Factory.FactoryCreator;

public class FacilityFactoryTest {

	@Test
	public void testGetFacilityStringED() {
		ED ed = new ED("ED1", "France");
		
		FacilityFactory facilityFactory = (FacilityFactory) FactoryCreator.getFactory("FACILITY");
		
		Stretcher stretcher = (Stretcher) facilityFactory.getFacility(ed, "STRETCHER");
		
		assertTrue(ed.getDbStretcher().get(0).contains(stretcher));
		assertTrue(stretcher.getEd().equals(ed));
	}

	@Test
	public void testGetFacilityStringEDString() {
		ED ed = new ED("ED1", "France");
		
		FacilityFactory facilityFactory = (FacilityFactory) FactoryCreator.getFactory("FACILITY");
		
		Stretcher stretcher = (Stretcher) facilityFactory.getFacility(ed, "STRETCHER", "1");
		
		assertTrue(ed.getDbStretcher().get(0).contains(stretcher));
		assertTrue(stretcher.getName().equals("1"));
		assertTrue(stretcher.getEd().equals(ed));
	}

}
