package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Emergency.ED;
import Events.Consultation;
import Events.Registration;
import Events.Time;
import Events.TimeStamp;
import Factory.FactoryCreator;
import Factory.PeopleFactory;
import Factory.RoomFactory;
import HR.Nurse;
import HR.Patient;
import HR.Physician;
import Rooms.ShockRoom;

public class ConsultationTest {

	@Test
	public void testConsultationCreation() {
		// INITIALISATION D'UN ED
		ED ed = new ED("ED1", "France");
		Time time = Time.getInstanceTime();
		
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
		RoomFactory roomFactory = (RoomFactory) FactoryCreator.getFactory("ROOM");
		
		Patient patient = (Patient) peopleFactory.getPatient(ed, "L1" , new TimeStamp());
		Physician physician = (Physician) peopleFactory.getStaff(ed, "PHYSICIAN");
		ShockRoom sRoom = (ShockRoom) roomFactory.getRoom(ed, "SHOCKROOM");

				
		// PROCEED
		time.timeGoes(12);
		Consultation consultation = new Consultation(ed, patient, physician, sRoom);		
		
		// CHECK 
		assertTrue("1.1", patient.getPhysician().equals(physician));
		assertTrue("2.1", physician.getPatientOverseeing().contains(patient));
		assertTrue("3.1", patient.getState().equalsIgnoreCase("inConsultation"));
		assertTrue("3.2", physician.getState().equalsIgnoreCase("visiting"));
		assertTrue("3.3", sRoom.getState().equalsIgnoreCase("occupied"));
		assertTrue("4.1", consultation.getStartTime().toString().equals(time.toString()));
		assertTrue("4.2", consultation.getDuration()>5 && consultation.getDuration()<20);
		TimeStamp ts = new TimeStamp(consultation.getDuration());
		assertTrue("4.3", consultation.getEndTime().toString().equals(ts.toString()));
	}

	public void testConsultationEnd() {
		// INITIALISATION D'UN ED
		ED ed = new ED("ED1", "France");
		Time time = Time.getInstanceTime();
		
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
		RoomFactory roomFactory = (RoomFactory) FactoryCreator.getFactory("ROOM");
		
		Patient patient = (Patient) peopleFactory.getPatient(ed, "L1" , new TimeStamp());
		Physician physician = (Physician) peopleFactory.getStaff(ed, "PHYSICIAN");
		ShockRoom sRoom = (ShockRoom) roomFactory.getRoom(ed, "SHOCKROOM");

				
		// PROCEED
		time.timeGoes(12);
		Consultation consultation = new Consultation(ed, patient, physician, sRoom);		
		consultation.endEvent();
		
		// CHECK 
		assertTrue("1.1", patient.getState() == "released" || patient.getState() == "waitingForRadio" || patient.getState() == "waitingForBloodTest" || patient.getState() == "waitingForMRI");
		assertTrue("1.2", physician.getState().equalsIgnoreCase("Idle"));
		assertTrue("1.3", sRoom.getState().equalsIgnoreCase("free"));
	}
}
