package Test;

import java.util.ArrayList;

import Emergency.ED;
import Emergency.EDDisplayer;
import Events.EventsManager;
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

public class EDDisplayerTest {

	
	public static void main(String[] args) {
		// INITIALISATION D'UN ED
		ArrayList<ED> eds = new ArrayList<ED>();
		ED ed = new ED("ED1", "France");
		eds.add(ed);

		Time time = Time.getInstanceTime();
		EventsManager simulator = new EventsManager(eds);
				
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
		RoomFactory roomFactory = (RoomFactory) FactoryCreator.getFactory("ROOM");
		
	
		Patient patient0 = (Patient) peopleFactory.getPatient(ed, "L1" , new TimeStamp(5));
		Patient patient1 = (Patient) peopleFactory.getPatient(ed, "L1" , new TimeStamp(15));
		Patient patient2 = (Patient) peopleFactory.getPatient(ed, "L2" , new TimeStamp(4));
		Patient patient3 = (Patient) peopleFactory.getPatient(ed, "L3" , new TimeStamp(18));
		Patient patient4 = (Patient) peopleFactory.getPatient(ed, "L4" , new TimeStamp(20));
		Patient patient5 = (Patient) peopleFactory.getPatient(ed, "L5" , new TimeStamp(19));
		Patient patient6 = (Patient) peopleFactory.getPatient(ed, "L1" , new TimeStamp(25));
		Patient patient7 = (Patient) peopleFactory.getPatient(ed, "L2" , new TimeStamp(24));
	
		Nurse nurse0 = (Nurse) peopleFactory.getStaff(ed, "NURSE");
		Nurse nurse1 = (Nurse) peopleFactory.getStaff(ed,"NURSE");
	
		Transporter transporter0 = (Transporter) peopleFactory.getStaff(ed,"TRANSPORTER");
		Transporter transporter1 = (Transporter) peopleFactory.getStaff(ed,"TRANSPORTER");
		Transporter transporter2 = (Transporter) peopleFactory.getStaff(ed,"TRANSPORTER");
		Transporter transporter3 = (Transporter) peopleFactory.getStaff(ed,"TRANSPORTER");
		
		Physician physician0 = (Physician) peopleFactory.getStaff(ed,"PHYSICIAN");
		Physician physician1 = (Physician) peopleFactory.getStaff(ed,"PHYSICIAN");
		
		float[] distParam = {10, 20};
		WaitingRoom wRoom0 = (WaitingRoom) roomFactory.getRoom(ed, "WAITINGROOM");
		BoxRoom bRoom = (BoxRoom) roomFactory.getRoom(ed,"BOXROOM");
		ShockRoom sRoom = (ShockRoom) roomFactory.getRoom(ed,"SHOCKROOM");
		
		MRIRoom mriRoom = (MRIRoom) roomFactory.getTestRoom(ed,"MRIROOM", "UNIFORM", distParam);
		BloodRoom bloodRoom = (BloodRoom) roomFactory.getTestRoom(ed,"BLOODROOM",  "UNIFORM", distParam);
		RadioRoom radioRoom = (RadioRoom) roomFactory.getTestRoom(ed,"RADIOROOM",  "UNIFORM", distParam);
		
		EDDisplayer.displayED(ed);
				
	}
}
