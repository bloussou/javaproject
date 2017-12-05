package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Emergency.ED;
import Events.TimeStamp;
import Events.Transport_Nurse;
import Factory.FactoryCreator;
import Factory.PeopleFactory;
import Factory.RoomFactory;
import HR.Nurse;
import HR.Patient;
import Rooms.WaitingRoom;

public class Transport_NurseTest {

	@Test
	public void testEndEvent() {
		// INITIALISATION D'UN ED
		
		ED ed = new ED("ED1", "France");
		
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
		RoomFactory roomFactory = (RoomFactory) FactoryCreator.getFactory("ROOM");
		
		Patient patient = (Patient) peopleFactory.getPatient(ed, "L1" , new TimeStamp());
		Nurse nurse = (Nurse) peopleFactory.getStaff("NURSE", ed);
		WaitingRoom wRoom1 = (WaitingRoom) roomFactory.getRoom("WAITINGROOM", ed);
		
		//PROCEED
		nurse.register(patient);
		nurse.transport(patient, wRoom1);
		Transport_Nurse transport_Nurse = new Transport_Nurse(ed, patient, nurse, wRoom1);
		
		//TEST
		transport_Nurse.endEvent();
		assertTrue("3.1.0", nurse.getState().equalsIgnoreCase("Idle"));
		assertTrue("3.1.1", patient.getState().equalsIgnoreCase("waitingForConsultation"));
		assertTrue(patient.getLocation().equals(wRoom1));
		assertTrue("3.4", wRoom1.getOccupants().contains(patient));
	}

	@Test
	public void testTransport_Nurse() {
		// INITIALISATION D'UN ED
		
		ED ed = new ED("ED1", "France");
		
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
		RoomFactory roomFactory = (RoomFactory) FactoryCreator.getFactory("ROOM");
		
		Patient patient = (Patient) peopleFactory.getPatient(ed, "L1" , new TimeStamp());
		Nurse nurse = (Nurse) peopleFactory.getStaff("NURSE", ed);
		WaitingRoom wRoom1 = (WaitingRoom) roomFactory.getRoom("WAITINGROOM", ed);
		
		//PROCEED
		nurse.register(patient);
		nurse.transport(patient, wRoom1);
		Transport_Nurse transport_Nurse = new Transport_Nurse(ed, patient, nurse, wRoom1);
		
		//Check
		assertTrue("2.1", nurse.getPatientTransported().contains(patient));
		assertTrue("2.9.0", patient.getState().equalsIgnoreCase("Transporting"));
		assertTrue("2.9", nurse.getTargetRoom().equals(wRoom1));
		assertTrue(transport_Nurse.getStartTime().equals(nurse.getStartTime()));
		assertTrue(transport_Nurse.getEndTime().equals(nurse.getEndTime()));
		assertTrue(transport_Nurse.getEndTime().getTimeStamp() == transport_Nurse.getStartTime().getTimeStamp()+2);
	}

}
