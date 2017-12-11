package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Emergency.ED;
import Events.Consultation;
import Events.Event;
import Events.EventsManager;
import Events.MRIExamination;
import Events.RadioExamination;
import Events.Time;
import Events.TimeStamp;
import Factory.FactoryCreator;
import Factory.PeopleFactory;
import Factory.RoomFactory;
import HR.Nurse;
import HR.Patient;
import HR.Physician;
import HR.Transporter;
import Rooms.BloodRoom;
import Rooms.BoxRoom;
import Rooms.MRIRoom;
import Rooms.RadioRoom;
import Rooms.ShockRoom;
import Rooms.WaitingRoom;

public class EventsManagerTest {

	@Test
	public void testInsertNewEvent() {

		// INITIALISATION D'UN ED
		ArrayList<ED> eds = new ArrayList<ED>();
		ED ed = new ED("ED1", "France");
		eds.add(ed);

		Time time = Time.getInstanceTime();
		EventsManager simulator = new EventsManager(eds);
		
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
		RoomFactory roomFactory = (RoomFactory) FactoryCreator.getFactory("ROOM");
		
		Patient patient = (Patient) peopleFactory.getPatient(ed, "L1" , new TimeStamp());
		Physician physician = (Physician) peopleFactory.getStaff(ed, "PHYSICIAN");
		ShockRoom sRoom = (ShockRoom) roomFactory.getRoom(ed, "SHOCKROOM");
		
		Consultation consultation1 = new Consultation(ed, patient, physician, sRoom);
		Consultation consultation2 = new Consultation(ed, patient, physician, sRoom);
		Consultation consultation3 = new Consultation(ed, patient, physician, sRoom);
		
		consultation1.setEndTime(new TimeStamp(15));
		consultation2.setEndTime(new TimeStamp(2));
		consultation3.setEndTime(new TimeStamp(10));
		ArrayList<Event> trylist = new ArrayList<Event>();
		trylist.add(consultation2);
		trylist.add(consultation3);
		trylist.add(consultation1);
		
		//PROCEED
		simulator.insertNewEvent(consultation1);
		simulator.insertNewEvent(consultation2);
		simulator.insertNewEvent(consultation3);
		
		assertTrue("1.0", simulator.getInProgress().equals(trylist));
		
	}
	
	@Test
	public void TestCheckNewRegistration(){
		System.out.println("\n test de check new registration");
		// INITIALISATION D'UN ED
		ArrayList<ED> eds = new ArrayList<ED>();
		ED ed = new ED("ED1", "France");
		eds.add(ed);

		Time time = Time.getInstanceTime();
		EventsManager simulator = new EventsManager(eds);
		
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
		RoomFactory roomFactory = (RoomFactory) FactoryCreator.getFactory("ROOM");
		
		Patient patient1 = (Patient) peopleFactory.getPatient(ed, "roger", "roger", "arriving", "gold", "L1", new TimeStamp());
		Patient patient2 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		Patient patient3 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		Patient patient4 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		Patient patient5 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		Patient patient6 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		


		Nurse nurse1 = (Nurse) peopleFactory.getStaff(ed, "NURSE");
		Nurse nurse2 = (Nurse) peopleFactory.getStaff(ed, "NURSE");
		Nurse nurse3 = (Nurse) peopleFactory.getStaff(ed, "NURSE");
		
		//test
		simulator.checkNewRegistration(ed);
		
		
		assertTrue(patient1.getState().equalsIgnoreCase("Registered"));
		assertTrue(patient2.getState().equalsIgnoreCase("Registered"));
		assertTrue(patient3.getState().equalsIgnoreCase("Registered"));
			
	}
	
	@Test
	public void testCheckNewTransport_Nurse(){
		System.out.println("\n test de check transport nurse");
		// INITIALISATION D'UN ED
		ArrayList<ED> eds = new ArrayList<ED>();
		ED ed = new ED("ED1", "France");
		eds.add(ed);

		Time time = Time.getInstanceTime();
		EventsManager simulator = new EventsManager(eds);
		
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
		RoomFactory roomFactory = (RoomFactory) FactoryCreator.getFactory("ROOM");
		
		Patient patient1 = (Patient) peopleFactory.getPatient(ed, "roger", "roger", "registered", "gold", "L1", new TimeStamp());
		Patient patient2 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		Patient patient3 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		Patient patient4 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		Patient patient5 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		Patient patient6 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		


		Nurse nurse1 = (Nurse) peopleFactory.getStaff(ed, "NURSE");
		Nurse nurse2 = (Nurse) peopleFactory.getStaff(ed, "NURSE");
		Nurse nurse3 = (Nurse) peopleFactory.getStaff(ed, "NURSE");
		
		WaitingRoom wroom1 = (WaitingRoom) roomFactory.getRoom(ed, "WAITINGROOM");
		
		simulator.checkNewRegistration(ed);
		//Test
		simulator.checkNewTransport_Nurse(ed);
		assertTrue(ed.getDbPatient().get(2).size() == 3);
		
		
	}
	
	@Test
	public void TestcheckNewConsultation(){
		System.out.println("\n test de check new consultation");
		// INITIALISATION D'UN ED
		ArrayList<ED> eds = new ArrayList<ED>();
		ED ed = new ED("ED1", "France");
		eds.add(ed);

		Time time = Time.getInstanceTime();
		EventsManager simulator = new EventsManager(eds);
		
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
		RoomFactory roomFactory = (RoomFactory) FactoryCreator.getFactory("ROOM");
		
		Patient patient1 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		Patient patient2 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		Patient patient3 = (Patient) peopleFactory.getPatient(ed, "L2", new TimeStamp());
		Patient patient4 = (Patient) peopleFactory.getPatient(ed, "L2", new TimeStamp());
		Patient patient5 = (Patient) peopleFactory.getPatient(ed, "L3", new TimeStamp());
		Patient patient6 = (Patient) peopleFactory.getPatient(ed, "L5", new TimeStamp());
		


		Nurse nurse1 = (Nurse) peopleFactory.getStaff(ed, "NURSE");
		Nurse nurse2 = (Nurse) peopleFactory.getStaff(ed, "NURSE");
		Nurse nurse3 = (Nurse) peopleFactory.getStaff(ed, "NURSE");
		Nurse nurse4 = (Nurse) peopleFactory.getStaff(ed, "NURSE");
		Nurse nurse5 = (Nurse) peopleFactory.getStaff(ed, "NURSE");
		Nurse nurse6 = (Nurse) peopleFactory.getStaff(ed, "NURSE");
		
		WaitingRoom wroom1 = (WaitingRoom) roomFactory.getRoom(ed, "WAITINGROOM");
		ShockRoom schockroom1 = (ShockRoom) roomFactory.getRoom(ed, "SHOCKROOM");
		ShockRoom schockroom2 = (ShockRoom) roomFactory.getRoom(ed, "SHOCKROOM");
		ShockRoom schockroom3 = (ShockRoom) roomFactory.getRoom(ed, "SHOCKROOM");
		ShockRoom schockroom4 = (ShockRoom) roomFactory.getRoom(ed, "SHOCKROOM");
		ShockRoom schockroom5 = (ShockRoom) roomFactory.getRoom(ed, "SHOCKROOM");
		BoxRoom boxRoom = (BoxRoom) roomFactory.getRoom(ed, "BOXROOM");
		
		
		
		Physician physician1 = (Physician) peopleFactory.getStaff(ed, "PHYSICIAN");
		Physician physician2 = (Physician) peopleFactory.getStaff(ed, "PHYSICIAN");
		Physician physician3 = (Physician) peopleFactory.getStaff(ed, "PHYSICIAN");
		Physician physician4 = (Physician) peopleFactory.getStaff(ed, "PHYSICIAN");
		Physician physician5 = (Physician) peopleFactory.getStaff(ed, "PHYSICIAN");
		
		simulator.checkNewRegistration(ed);
		//Test
		
		simulator.checkNewTransport_Nurse(ed);
		time.timeGoes(2);
		simulator.dequeueEvents();
		
		simulator.checkNewConsultation(ed);
		
		assertTrue(ed.getDbPatient().get(4).contains(patient1));
		assertTrue(ed.getDbPatient().get(4).contains(patient2));
		assertTrue(ed.getDbPatient().get(4).contains(patient3));
		assertTrue(ed.getDbPatient().get(4).contains(patient4));
		assertTrue(ed.getDbPatient().get(4).contains(patient5));
		assertFalse(ed.getDbPatient().get(4).contains(patient6));
		
	}
	
	@Test
	public void testCheckTransportTransporter(){
		System.out.println("\n test de check new transport transporter");
		// INITIALISATION D'UN ED
		ArrayList<ED> eds = new ArrayList<ED>();
		ED ed = new ED("ED1", "France");
		eds.add(ed);

		Time time = Time.getInstanceTime();
		EventsManager simulator = new EventsManager(eds);
		
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
		RoomFactory roomFactory = (RoomFactory) FactoryCreator.getFactory("ROOM");
		
		Patient patient1 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		Patient patient2 = (Patient) peopleFactory.getPatient(ed, "L5", new TimeStamp());
		Patient patient3 = (Patient) peopleFactory.getPatient(ed, "L5", new TimeStamp());
		Patient patient4 = (Patient) peopleFactory.getPatient(ed, "L5", new TimeStamp());
		Patient patient5 = (Patient) peopleFactory.getPatient(ed, "L5", new TimeStamp());
		Patient patient6 = (Patient) peopleFactory.getPatient(ed, "L5", new TimeStamp());
		

		patient1.setState("WAITINGFORBLOODTEST");
		patient2.setState("WAITINGFORRADIO");
		patient3.setState("waitingForMRI");
		patient4.setState("bloodTested");
		patient5.setState("mriTested");
		patient6.setState("radioTested");
		
		BloodRoom bloodRoom = (BloodRoom) roomFactory.getRoom(ed, "BLOODROOM");
		RadioRoom radioRoom = (RadioRoom) roomFactory.getRoom(ed, "RADIOROOM");
		MRIRoom mriRoom = (MRIRoom) roomFactory.getRoom(ed, "MRIROOM");
		WaitingRoom waitingRoom = (WaitingRoom) roomFactory.getRoom(ed, "WAITINGROOM");
		BoxRoom boxRoom1 = (BoxRoom) roomFactory.getRoom(ed, "BOXROOM");
		BoxRoom boxRoom2 = (BoxRoom) roomFactory.getRoom(ed, "BOXROOM");
		BoxRoom boxRoom3 = (BoxRoom) roomFactory.getRoom(ed, "BOXROOM");
		
		Transporter transporter1 = (Transporter) peopleFactory.getStaff(ed, "TRANSPORTER");
		Transporter transporter2 = (Transporter) peopleFactory.getStaff(ed, "TRANSPORTER");
		Transporter transporter3 = (Transporter) peopleFactory.getStaff(ed, "TRANSPORTER");
		Transporter transporter4 = (Transporter) peopleFactory.getStaff(ed, "TRANSPORTER");
		Transporter transporter5 = (Transporter) peopleFactory.getStaff(ed, "TRANSPORTER");
		Transporter transporter6 = (Transporter) peopleFactory.getStaff(ed, "TRANSPORTER");
		
		Physician physician1 = (Physician) peopleFactory.getStaff(ed, "PHYSICIAN");
		Physician physician2 = (Physician) peopleFactory.getStaff(ed, "PHYSICIAN");
		Physician physician3 = (Physician) peopleFactory.getStaff(ed, "PHYSICIAN");
		
		
		patient4.setPhysician(physician1);
		patient5.setPhysician(physician2);
		patient6.setPhysician(physician3);
		//Test
		ArrayList<Patient> patientTested = new ArrayList<Patient>();
		for (Patient patient : ed.getDbPatient().get(12)){
			patientTested.add(patient);
		}
		for (Patient patient : ed.getDbPatient().get(13)){
			patientTested.add(patient);
		}
		for (Patient patient : ed.getDbPatient().get(14)){
			patientTested.add(patient);
		}

		assertFalse(patientTested.isEmpty());
		assertFalse(ed.getDbPhysician().get(0).isEmpty());
		assertFalse(ed.getDbBoxRoom().isEmpty());
		
		simulator.checkTransport_transporter(ed);
		
		System.out.println(simulator.getInProgress());
		assertTrue(patient1.getState().equalsIgnoreCase("transportation"));
		assertTrue(patient2.getState().equalsIgnoreCase("transportation"));
		assertTrue(patient3.getState().equalsIgnoreCase("transportation"));
		assertTrue(patient4.getState().equalsIgnoreCase("transportation"));
		assertTrue(patient5.getState().equalsIgnoreCase("transportation"));
		assertTrue(patient6.getState().equalsIgnoreCase("transportation"));
		assertTrue(simulator.getInProgress().size() == 6);
		System.out.println(simulator.getInProgress().get(0).getEndTime());
		
	}
	
	@Test
	public void testCheckNewBloodExamination(){
		System.out.println("\n test de check new blood examination");
		// INITIALISATION D'UN ED
		ArrayList<ED> eds = new ArrayList<ED>();
		ED ed = new ED("ED1", "France");
		eds.add(ed);

		Time time = Time.getInstanceTime();
		EventsManager simulator = new EventsManager(eds);
		
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
		RoomFactory roomFactory = (RoomFactory) FactoryCreator.getFactory("ROOM");
		
		Patient patient1 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		Patient patient2 = (Patient) peopleFactory.getPatient(ed, "L5", new TimeStamp());
		

		patient1.setState("WAITINGFORBLOODTESTt");
		patient2.setState("waitingforbloodtestT");
		
		BloodRoom bloodRoom = (BloodRoom) roomFactory.getRoom(ed, "BLOODROOM");
		

		System.out.println(patient1.getState());
		System.out.println(patient2.getState());
		//Test
		
		assertTrue(ed.getDbPatient().get(10).size() == 2);
		
		simulator.checkNewBloodExamination(ed);
		System.out.println(simulator.getInProgress());
		System.out.println(simulator.getInProgress().get(0).getEndTime().toString());
		assertTrue(simulator.getInProgress().size() ==1);
		assertTrue(bloodRoom.getState().contentEquals("occupied"));
		
		


	}
	
	@Test
	public void testNewMRIExamination() {
		System.out.println("\n test de check new mri examination");
		// INITIALISATION D'UN ED
		ArrayList<ED> eds = new ArrayList<ED>();
		ED ed = new ED("ED1", "France");
		eds.add(ed);

		Time time = Time.getInstanceTime();
		EventsManager simulator = new EventsManager(eds);
		
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
		RoomFactory roomFactory = (RoomFactory) FactoryCreator.getFactory("ROOM");
		
		Patient patient1 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		Patient patient2 = (Patient) peopleFactory.getPatient(ed, "L5", new TimeStamp());
		

		patient1.setState("WaitingFormriT");
		patient2.setState("WaitingFormriT");
		
		MRIRoom mriRoom = (MRIRoom) roomFactory.getRoom(ed, "MRIROOM");
		
		patient1.setLocation(mriRoom);
		System.out.println(patient1.getState());
		System.out.println(patient2.getState());
		//Test
		
		assertTrue(ed.getDbPatient().get(9).size() == 2);
		
		simulator.checkNewMRIExamination(ed);

		System.out.println(simulator.getInProgress());
		System.out.println(simulator.getInProgress().get(0).getEndTime().toString());
		MRIExamination mriexam = (MRIExamination) simulator.getInProgress().get(0);
		assertTrue(mriexam.getPatient().equals(patient2) || mriexam.getPatient().equals(patient1));
		assertTrue(simulator.getInProgress().size() ==1);
		assertTrue(mriRoom.getState().contentEquals("occupied"));





	}
	
	@Test
	public void testCheckNewRadioExamination(){
		System.out.println("\n test de check new radio examination");
		// INITIALISATION D'UN ED
		ArrayList<ED> eds = new ArrayList<ED>();
		ED ed = new ED("ED1", "France");
		eds.add(ed);

		Time time = Time.getInstanceTime();
		EventsManager simulator = new EventsManager(eds);
		
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
		RoomFactory roomFactory = (RoomFactory) FactoryCreator.getFactory("ROOM");
		
		Patient patient1 = (Patient) peopleFactory.getPatient(ed, "L1", new TimeStamp());
		Patient patient2 = (Patient) peopleFactory.getPatient(ed, "L5", new TimeStamp());
		

		patient1.setState("waitingforradioT");
		patient2.setState("waitingforradioT");
		
		RadioRoom radioRoom = (RadioRoom) roomFactory.getRoom(ed, "RADIOROOM");
		

		System.out.println(patient1.getState());
		System.out.println(patient2.getState());
		//Test
		
		assertTrue(ed.getDbPatient().get(11).size() == 2);
		
		simulator.checkNewRadioExamination(ed);

		System.out.println(simulator.getInProgress());
		System.out.println(simulator.getInProgress().get(0).getEndTime().toString());
		RadioExamination radioexam = (RadioExamination) simulator.getInProgress().get(0);
		assertTrue(radioexam.getPatient().equals(patient2) || radioexam.getPatient().equals(patient1));
		assertTrue(simulator.getInProgress().size() ==1);
		assertTrue(radioRoom.getState().contentEquals("occupied"));


	}



}
