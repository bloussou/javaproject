package Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Emergency.ED;
import Events.Time;
import Events.TimeStamp;
import HR.Patient;
import HR.Physician;
 
public class PhysicianTest {

	@Test
	public void testGetPatientOverseeing() {
		ED ed = new ED("ed1","france");
		Time time = Time.getInstanceTime();
		time.timeGoes(10);
		Patient patient = new Patient(ed,"L1",new TimeStamp());
		Physician physician = new Physician(ed);
		physician.handleNewPatient(patient);
		ArrayList<Patient> tryList = new ArrayList<Patient>();
		tryList.add(patient);
		
		assertTrue(physician.getPatientOverseeing().equals(tryList));
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
