package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Factory.FacilityFactory;
import Factory.FactoryCreator;
import Factory.PeopleFactory;
import Factory.RoomFactory;

public class FactoryCreatorTest {

	@Test
	public void testGetFactory() {
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("Human");
		RoomFactory roomFactory = (RoomFactory) FactoryCreator.getFactory("ROOM");
		FacilityFactory facilityFactory = (FacilityFactory) FactoryCreator.getFactory("FACILITY");
		
		assertTrue(peopleFactory instanceof PeopleFactory);
		assertTrue(roomFactory instanceof RoomFactory);
		assertTrue(facilityFactory instanceof FacilityFactory);
	}

}
