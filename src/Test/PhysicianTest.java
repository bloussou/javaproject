package Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Emergency.*;
import Factory.*;
import HR.*;
import Rooms.*;
import Events.*;
 
public class PhysicianTest {

	@Test
	public void testGetPatientOverseeing() {
		
		// INITIALISATION D'UN ED
		ED ed = new ED("ED1", "France");
		Time time = Time.getInstanceTime();
		
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
		RoomFactory roomFactory = (RoomFactory) FactoryCreator.getFactory("ROOM");
		FacilityFactory facilityFactory = (FacilityFactory) FactoryCreator.getFactory("FACILITY");
		
		
		Patient patient1 = (Patient) peopleFactory.getPatient(ed, "L1" , new TimeStamp());
		Patient patient2 = (Patient) peopleFactory.getPatient(ed, "L3", new TimeStamp());
		
		Physician physician1 = (Physician) peopleFactory.getStaff("PHYSICIAN", ed);
		Physician physician2 = (Physician) peopleFactory.getStaff("PHYSICIAN", ed);
		
		BoxRoom boxRoom1 = (BoxRoom) roomFactory.getRoom("BOXROOM", ed);
		ShockRoom ShockRoom1 = (ShockRoom) roomFactory.getRoom("SHOCKROOM", ed);
		
		// TEST

		physician1.handleNewPatient(patient1, boxRoom1);
		
		//good answer PatientOverseeing list
		ArrayList<Patient> tryList = new ArrayList<Patient>();
		tryList.add(patient1);

		assertTrue(physician1.getPatientOverseeing().equals(tryList));
	}


	@Test
	public void testHandleNewPatient() {
		ED ed = new ED("ed1","france");
		Time time = Time.getInstanceTime();
		time.timeGoes(10);
		Patient patient = new Patient(ed,"L1",new TimeStamp());
		Physician physician = new Physician(ed);
		physician.handleNewPatient(patient);
	
		
		
		assertTrue(physician.getState().equals("visiting"));
		assertTrue(patient.getState().equals("inConsultation"));
		assertTrue(physician.getStartTime().getTimeStamp() == time.getTime());
		assertTrue(patient.getPhysician().equals(physician));
	}

	@Test
	public void testEmitVerdict() {
	ED ed = new ED("ed1","france");
	Time time = Time.getInstanceTime();
	time.timeGoes(10);
	Patient patient = new Patient(ed,"L1",new TimeStamp());
	Physician physician = new Physician(ed);
	physician.handleNewPatient(patient);
	physician.emitVerdict(patient);
	
	assertTrue(physician.getPatientOverseeing().indexOf(patient) == -1);
	assertTrue(physician.getPatientAlreadyTreated().indexOf(patient) != -1);
	}

	@Test
	public void testPrescribe() {
	ED ed = new ED("ed1","france");
	Time time = Time.getInstanceTime();
	time.timeGoes(10);
	Patient patient = new Patient(ed,"L1",new TimeStamp());
	Physician physician = new Physician(ed);
	physician.handleNewPatient(patient);
	physician.prescribe(patient);
	
	assertTrue(physician.getState().equals("idle"));
	assertTrue(patient.getState().equalsIgnoreCase("WaitingForBloodTest") || patient.getState().equalsIgnoreCase("WaitingForMRI") 
			|| patient.getState().equalsIgnoreCase("WaitingForRadio") || patient.getState().equalsIgnoreCase("Released"));
	}

}
