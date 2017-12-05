package SimErgyClient;

import java.util.ArrayList;

import Emergency.ED;
import Events.EventsManager;
import Events.Time;
import Events.TimeStamp;
import Events.Transport_Nurse;
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

public class SimErgy {

	public static void main(String[] args) {
		
		// INITIALISATION D'UN ED
		ArrayList<ED> eds = new ArrayList<ED>();
		ED ed = new ED("ED1", "France");
		eds.add(ed);

		Time time = Time.getInstanceTime();
		EventsManager simulator = new EventsManager(eds);
				
		PeopleFactory peopleFactory = (PeopleFactory) FactoryCreator.getFactory("HUMAN");
		RoomFactory roomFactory = (RoomFactory) FactoryCreator.getFactory("ROOM");
//		
//		
		Patient patient0 = (Patient) peopleFactory.getPatient(ed, "L1" , new TimeStamp(5));
//		Patient patient1 = (Patient) peopleFactory.getPatient(ed, "L1" , new TimeStamp(15));
//		Patient patient2 = (Patient) peopleFactory.getPatient(ed, "L2" , new TimeStamp(4));
//		Patient patient3 = (Patient) peopleFactory.getPatient(ed, "L3" , new TimeStamp(18));
//		Patient patient4 = (Patient) peopleFactory.getPatient(ed, "L4" , new TimeStamp(20));
//		Patient patient5 = (Patient) peopleFactory.getPatient(ed, "L5" , new TimeStamp(19));
//		Patient patient6 = (Patient) peopleFactory.getPatient(ed, "L1" , new TimeStamp(25));
//		Patient patient7 = (Patient) peopleFactory.getPatient(ed, "L2" , new TimeStamp(24));
//		
		Nurse nurse0 = (Nurse) peopleFactory.getStaff("NURSE", ed);
//		Nurse nurse1 = (Nurse) peopleFactory.getStaff("NURSE", ed);
//		
		Transporter transporter0 = (Transporter) peopleFactory.getStaff("TRANSPORTER", ed);
		Transporter transporter1 = (Transporter) peopleFactory.getStaff("TRANSPORTER", ed);
//		
		Physician physician0 = (Physician) peopleFactory.getStaff("PHYSICIAN", ed);
//		Physician physician1 = (Physician) peopleFactory.getStaff("PHYSICIAN", ed);
//		
//	
		WaitingRoom wRoom0 = (WaitingRoom) roomFactory.getRoom("WAITINGROOM", ed);
//		BoxRoom bRoom = (BoxRoom) roomFactory.getRoom("BOXROOM", ed);
		ShockRoom sRoom = (ShockRoom) roomFactory.getRoom("SHOCKROOM", ed);
//		
		MRIRoom MRIRoom0 = (MRIRoom) roomFactory.getRoom("MRIROOOM", ed);
		BloodRoom bloddRoom = (BloodRoom) roomFactory.getRoom("BLOODROOM", ed);
		RadioRoom radioRoom = (RadioRoom) roomFactory.getRoom("RADIOROOM", ed);
		
		
		
		while (time.getTime()<6000){
			simulator.nextStep();
		}
		System.out.println("\n"+patient0.getHistory());
		System.out.println(patient0.getState());
		
		
	}
}
