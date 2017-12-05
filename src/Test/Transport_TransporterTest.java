package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Emergency.ED;
import Events.Time;
import Events.TimeStamp;
import Events.Transport_Transporter;
import Factory.FactoryCreator;
import Factory.PeopleFactory;
import Factory.RoomFactory;
import HR.Patient;
import HR.Transporter;
import Rooms.BloodRoom;
import Rooms.BoxRoom;
import Rooms.RadioRoom;

public class Transport_TransporterTest {

	@Test
	public void testTransport_Transporter() {
		
		// INITIALISATION D'UN ED
		ED ed = new ED("ED1", "France");
		Time time = Time.getInstanceTime();
		
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
		RoomFactory roomFactory = (RoomFactory) FactoryCreator.getFactory("ROOM");
				
		Patient patient1 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		patient1.setState("waitingForMRI");
		Transporter transporter1 = (Transporter) peopleFactory.getStaff("TRANSPORTER", ed);
		Patient patient2 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		patient2.setState("radioTested");
		Transporter transporter2 = (Transporter) peopleFactory.getStaff("TRANSPORTER", ed);
		
		BloodRoom bRoom = (BloodRoom) roomFactory.getRoom("BLOODROOM", ed);
		BoxRoom boxRoom = (BoxRoom) roomFactory.getRoom("BOXROOM", ed);
		
		//PROCEED
		Transport_Transporter transport_Transporter1 = new Transport_Transporter(ed, patient1, transporter1, bRoom);
		Transport_Transporter transport_Transporter2 = new Transport_Transporter(ed, patient2, transporter2, boxRoom);	
		
		//CHECK
		assertTrue("1.1",transporter1.getState().equalsIgnoreCase("transportation"));
		assertTrue("1.2",transporter2.getState().equalsIgnoreCase("transportation"));
		assertTrue("2.1", patient1.getState().equalsIgnoreCase("transportation"));
		assertTrue("2.2", patient2.getState().equalsIgnoreCase("transportation"));
		assertTrue("3.1", boxRoom.getState().equalsIgnoreCase("occupied"));
		assertTrue("3.2", bRoom.getState().equalsIgnoreCase("occupied"));
		assertTrue("4.1", transporter1.getLastPatientState().equalsIgnoreCase("waitingForMRI"));
		assertTrue("4.2", transporter2.getLastPatientState().equalsIgnoreCase("radioTested"));
	}

	@Test
	public void testEndEvent() {
		// INITIALISATION D'UN ED
		ED ed = new ED("ED1", "France");
		Time time = Time.getInstanceTime();
		
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
		RoomFactory roomFactory = (RoomFactory) FactoryCreator.getFactory("ROOM");
				
		Patient patient1 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		patient1.setState("waitingForMRI");
		Transporter transporter1 = (Transporter) peopleFactory.getStaff("TRANSPORTER", ed);
		Patient patient2 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		patient2.setState("radioTested");
		Transporter transporter2 = (Transporter) peopleFactory.getStaff("TRANSPORTER", ed);
		
		BloodRoom bRoom = (BloodRoom) roomFactory.getRoom("BLOODROOM", ed);
		BoxRoom boxRoom = (BoxRoom) roomFactory.getRoom("BOXROOM", ed);
		
		//PROCEED
		Transport_Transporter transport_Transporter1 = new Transport_Transporter(ed, patient1, transporter1, bRoom);
		Transport_Transporter transport_Transporter2 = new Transport_Transporter(ed, patient2, transporter2, boxRoom);	
		transport_Transporter1.endEvent();
		transport_Transporter2.endEvent();
		
		//CHECK
		assertTrue("1.1",transporter1.getState().equalsIgnoreCase("idle"));
		assertTrue("1.2",transporter2.getState().equalsIgnoreCase("idle"));
		assertTrue("2.1", patient1.getState().equalsIgnoreCase("waitingForMRIT"));
		assertTrue("2.2", patient2.getState().equalsIgnoreCase("waitingForFinalConsultation"));
		assertTrue("3.1", boxRoom.getState().equalsIgnoreCase("occupied"));
		assertTrue("3.2", bRoom.getState().equalsIgnoreCase("occupied"));
		assertTrue("4.1", transporter1.getLastPatientState().equalsIgnoreCase("waitingForMRI"));
		assertTrue("4.2", transporter2.getLastPatientState().equalsIgnoreCase("radioTested"));
	}
	
}
