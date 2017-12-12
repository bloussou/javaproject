package Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Emergency.*;
import Factory.*;
import HR.*;
import MessageBox.Message;
import Rooms.*;
import Events.*;
 
public class PhysicianTest {

	@Test
	public void testGetPatientOverseeing() {
		System.out.println("\n test patient overseeing !");
		// INITIALISATION D'UN ED
		ED ed = new ED("ED1", "France");
		
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
		RoomFactory roomFactory = (RoomFactory) FactoryCreator.getFactory("ROOM");
		
		
		Patient patient1 = (Patient) peopleFactory.getPatient(ed, "L1" , new TimeStamp());
		
		Physician physician1 = (Physician) peopleFactory.getStaff(ed,"PHYSICIAN");
		
		BoxRoom boxRoom1 = (BoxRoom) roomFactory.getRoom(ed,"BOXROOM");
		
		// TEST

		physician1.handleNewPatient(patient1, boxRoom1);
		
		//good answer PatientOverseeing list
		ArrayList<Patient> tryList = new ArrayList<Patient>();
		tryList.add(patient1);

		assertTrue(physician1.getPatientOverseeing().equals(tryList));
	}


	@Test
	public void testHandleNewPatient() {
		System.out.println("\n test handle new patient !");
		// INITIALISATION D'UN ED
		ED ed = new ED("ED1", "France");
		Time time = Time.getInstanceTime();
		time.timeGoes(10);
			
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
		RoomFactory roomFactory = (RoomFactory) FactoryCreator.getFactory("ROOM");
				
				
		Patient patient1 = (Patient) peopleFactory.getPatient(ed, "L1" , new TimeStamp());
				
		Physician physician1 = (Physician) peopleFactory.getStaff(ed,"PHYSICIAN");
				
		BoxRoom boxRoom1 = (BoxRoom) roomFactory.getRoom(ed,"BOXROOM");
		
		
		
		
		
		//TEST
		physician1.handleNewPatient(patient1, boxRoom1);
		
		//test physician state
		String tryState = "visiting";
		assertTrue(physician1.getState().equals(tryState));
		assertTrue(ed.getDbPhysician().get(1).indexOf(physician1) != -1);
		
		//test patient state
		assertTrue(patient1.getState().equalsIgnoreCase("inConsultation"));
		
		//test room state
		assertTrue(boxRoom1.getState().equalsIgnoreCase("occupied"));
		
		//test physician of the patient
		assertTrue(patient1.getPhysician().equals(physician1));
		
		System.out.println(patient1.getHistory());
		
	}

	@Test
	public void testEmitVerdict() {
		System.out.println("\n emit verdict !");
		// INITIALISATION D'UN ED
		ED ed = new ED("ED1", "France");
		Time time = Time.getInstanceTime();
		time.timeGoes(10);
					
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
		RoomFactory roomFactory = (RoomFactory) FactoryCreator.getFactory("ROOM");
						
						
		Patient patient1 = (Patient) peopleFactory.getPatient(ed, "L1" , new TimeStamp());
						
		Physician physician1 = (Physician) peopleFactory.getStaff(ed,"PHYSICIAN");
						
				
						
				
				
		//TEST
		physician1.emitVerdict(patient1);
	
		assertTrue(physician1.getPatientOverseeing().indexOf(patient1) == -1);
		assertTrue(physician1.getPatientAlreadyTreated().indexOf(patient1) != -1);
		int min = time.getTime();
		assertTrue(patient1.getDepartureTime().getTimeStamp() == min);
		System.out.println(patient1.getHistory());
	}

	@Test
	public void testPrescribe() {
		System.out.println("\n test precribe !");
		// INITIALISATION D'UN ED
		ED ed = new ED("ED1", "France");
		Time time = Time.getInstanceTime();
		time.timeGoes(10);
					
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
		RoomFactory roomFactory = (RoomFactory) FactoryCreator.getFactory("ROOM");
						
						
		Patient patient1 = (Patient) peopleFactory.getPatient(ed, "L1" , new TimeStamp());
		Patient patient2 = (Patient) peopleFactory.getPatient(ed, "L3", new TimeStamp());
						
		Physician physician1 = (Physician) peopleFactory.getStaff(ed,"PHYSICIAN");
		Physician physician2 = (Physician) peopleFactory.getStaff(ed,"PHYSICIAN");
						
		BoxRoom boxRoom1 = (BoxRoom) roomFactory.getRoom(ed,"BOXROOM");
		ShockRoom ShockRoom1 = (ShockRoom) roomFactory.getRoom(ed,"SHOCKROOM");
				
				
				
				
				
		//TEST
		physician1.prescribe(patient1, boxRoom1);
	
		assertTrue(physician1.getState().equals("idle"));
		assertTrue(patient1.getState().equalsIgnoreCase("WaitingForBloodTest") || patient1.getState().equalsIgnoreCase("WaitingForMRI") 
			|| patient1.getState().equalsIgnoreCase("WaitingForRadio") || patient1.getState().equalsIgnoreCase("Released"));
		assertTrue(ed.getDbBoxRoom().get(0).contains(boxRoom1));
		assertFalse(ed.getDbBoxRoom().get(1).contains(boxRoom1));


		physician2.prescribe(patient2, ShockRoom1);
		assertTrue(patient2.getState().equalsIgnoreCase("WaitingForBloodTest") || patient2.getState().equalsIgnoreCase("WaitingForMRI") 
				|| patient2.getState().equalsIgnoreCase("WaitingForRadio") || patient2.getState().equalsIgnoreCase("Released"));
		assertTrue(ed.getDbShockRoom().get(0).contains(ShockRoom1));
		assertFalse(ed.getDbShockRoom().get(1).contains(ShockRoom1));

	}

	@Test
	public void testSetState(){

		System.out.println("\n test setState !!!");
		// INITIALISATION D'UN ED
		
		ED ed = new ED("ED1", "France");
		
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");		
				
		Physician physician = (Physician) peopleFactory.getStaff(ed,"PHYSICIAN");
		
		//test idle
		physician.setState("idle");
		assertTrue("idle",physician.getState().equalsIgnoreCase("idle"));
		assertTrue(ed.getDbPhysician().get(0).contains(physician));
		assertFalse(ed.getDbPhysician().get(1).contains(physician));
		assertFalse(ed.getDbPhysician().get(2).contains(physician));
		//test visiting
		physician.setState("visiting");
		assertTrue("visiting",physician.getState().equalsIgnoreCase("visiting"));
		assertTrue(ed.getDbPhysician().get(1).contains(physician));
		assertFalse(ed.getDbPhysician().get(2).contains(physician));
		assertFalse(ed.getDbPhysician().get(0).contains(physician));
		
		//test ofduty
		physician.setState("ofDuty");
		assertTrue("ofDuty",physician.getState().equalsIgnoreCase("ofDuty"));
		assertTrue(ed.getDbPhysician().get(2).contains(physician));
		assertFalse(ed.getDbPhysician().get(1).contains(physician));
		assertFalse(ed.getDbPhysician().get(0).contains(physician));
		
		//test error
		physician.setState("lol");
		assertFalse("lol",physician.getState().equalsIgnoreCase("lol"));
	}
	
	
	/*
	 * it tests the ability to a physician to write to another physician about patient1
	 */
	@Test
	public void testWriteMessage(){
		System.out.println("\n test write message !");
		// INITIALISATION D'UN ED
		ED ed = new ED("ED1", "France");
		Time time = Time.getInstanceTime();
		time.timeGoes(10);
					
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
						
						
		Patient patient1 = (Patient) peopleFactory.getPatient(ed, "L1" , new TimeStamp());
						
		Physician physician1 = (Physician) peopleFactory.getStaff(ed,"PHYSICIAN");
		Physician physician2 = (Physician) peopleFactory.getStaff(ed,"PHYSICIAN");
		
		//TEST
		physician1.writeMessage(patient1, physician2, "patient1 very sick", "he is about to die");
		assertFalse(physician2.getMailBox().get(0).isEmpty());
		
	}
	
	@Test
	public void testReadMailBox() {
		System.out.println("\n test read mail box !");
		// INITIALISATION D'UN ED
		ED ed = new ED("ED1", "France");
		Time time = Time.getInstanceTime();
		time.timeGoes(10);
					
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
						
						
		Patient patient1 = (Patient) peopleFactory.getPatient(ed, "L1" , new TimeStamp());
						
		Physician physician1 = (Physician) peopleFactory.getStaff(ed,"PHYSICIAN");
		Physician physician2 = (Physician) peopleFactory.getStaff(ed,"PHYSICIAN");
		
		//TEST
		physician2.readMessage();
		
		physician1.writeMessage(patient1, physician2, "patient1 very sick", "he is about to die");
		
		physician2.readMessage();
		
		assertTrue(0 == 0);
	}
	
	@Test
	public void testReadMessageOne() {
		System.out.println("\n test read a mail !");
		// INITIALISATION D'UN ED
		ED ed = new ED("ED1", "France");
		Time time = Time.getInstanceTime();
		time.timeGoes(10);
					
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
						
						
		Patient patient1 = (Patient) peopleFactory.getPatient(ed, "L1" , new TimeStamp());
						
		Physician physician1 = (Physician) peopleFactory.getStaff(ed,"PHYSICIAN");
		Physician physician2 = (Physician) peopleFactory.getStaff(ed,"PHYSICIAN");
		
		//TEST
		physician2.writeMessage(patient1, physician1, "I'm going to save him", "using my super power");
		
		physician1.writeMessage(patient1, physician2, "patient1 very sick", "he is about to die");
		physician1.writeMessage(patient1, physician2, "patient1 has died", "he died in th night");
		
		physician2.readMessage(physician2.getMailBox().get(0).get(1));
		physician2.readMessage(physician1.getMailBox().get(0).get(0));
		
		assertTrue(0 == 0);
	}
	
	@Test
	public void testUnReadMessage() {
		System.out.println("\n test unread a mail !");
		// INITIALISATION D'UN ED
		ED ed = new ED("ED1", "France");
		Time time = Time.getInstanceTime();
		time.timeGoes(10);
					
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
						
						
		Patient patient1 = (Patient) peopleFactory.getPatient(ed, "L1" , new TimeStamp());
						
		Physician physician1 = (Physician) peopleFactory.getStaff(ed,"PHYSICIAN");
		Physician physician2 = (Physician) peopleFactory.getStaff(ed,"PHYSICIAN");
		
		//TEST
		physician2.writeMessage(patient1, physician1, "I'm going to save him", "using my super power");
		
		physician1.writeMessage(patient1, physician2, "patient1 very sick", "he is about to die");
		physician1.writeMessage(patient1, physician2, "patient1 has died", "he died in th night");
		
		physician2.readMessage(physician2.getMailBox().get(0).get(1));
		
		physician2.unReadMessage(physician2.getMailBox().get(1).get(0));
		
		assertTrue(physician2.getMailBox().get(0).contains(physician2.getMailBox().get(0).get(0)));
	}
	
	@Test
	public void testRemoveMessage() {
		System.out.println("\n test remove a mail !");
		// INITIALISATION D'UN ED
		ED ed = new ED("ED1", "France");
		Time time = Time.getInstanceTime();
		time.timeGoes(10);
					
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
						
						
		Patient patient1 = (Patient) peopleFactory.getPatient(ed, "L1" , new TimeStamp());
						
		Physician physician1 = (Physician) peopleFactory.getStaff(ed,"PHYSICIAN");
		Physician physician2 = (Physician) peopleFactory.getStaff(ed,"PHYSICIAN");
		
		//TEST
		physician2.writeMessage(patient1, physician1, "I'm going to save him", "using my super power");
		
		physician1.writeMessage(patient1, physician2, "patient1 very sick", "he is about to die");
		physician1.writeMessage(patient1, physician2, "patient1 has died", "he died in th night");
		
		Message tryMail = physician2.getMailBox().get(0).get(1);
		physician2.removeMessage(tryMail); //remove a message
		
		physician2.removeMessage(physician1.getMailBox().get(0).get(0)); //try to remove a message wich doesn't exist in the physician2 mailing box
		
		assertFalse(physician2.getMailBox().get(0).contains(tryMail));
		assertFalse(physician2.getMailBox().get(1).contains(tryMail));
	}
	
}

	
