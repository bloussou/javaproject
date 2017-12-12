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
import KPI.DTDT;
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
		
	
		Patient patient0 = (Patient) peopleFactory.getPatient(ed, "L1" , new TimeStamp(5));
		Patient patient1 = (Patient) peopleFactory.getPatient(ed, "L1" , new TimeStamp(15));
		Patient patient2 = (Patient) peopleFactory.getPatient(ed, "L2" , new TimeStamp(4));
//		Patient patient3 = (Patient) peopleFactory.getPatient(ed, "L3" , new TimeStamp(18));
//		Patient patient4 = (Patient) peopleFactory.getPatient(ed, "L4" , new TimeStamp(20));
//		Patient patient5 = (Patient) peopleFactory.getPatient(ed, "L5" , new TimeStamp(19));
//		Patient patient6 = (Patient) peopleFactory.getPatient(ed, "L1" , new TimeStamp(25));
//		Patient patient7 = (Patient) peopleFactory.getPatient(ed, "L2" , new TimeStamp(24));
	
		Nurse nurse0 = (Nurse) peopleFactory.getStaff(ed, "NURSE");
		Nurse nurse1 = (Nurse) peopleFactory.getStaff(ed,"NURSE");
	
		Transporter transporter0 = (Transporter) peopleFactory.getStaff(ed,"TRANSPORTER");
		Transporter transporter1 = (Transporter) peopleFactory.getStaff(ed,"TRANSPORTER");
		Transporter transporter2 = (Transporter) peopleFactory.getStaff(ed,"TRANSPORTER");
		Transporter transporter3 = (Transporter) peopleFactory.getStaff(ed,"TRANSPORTER");
		
		Physician physician0 = (Physician) peopleFactory.getStaff(ed,"PHYSICIAN");
		Physician physician1 = (Physician) peopleFactory.getStaff(ed,"PHYSICIAN");
		

		WaitingRoom wRoom0 = (WaitingRoom) roomFactory.getRoom(ed,"WAITINGROOM");
		BoxRoom bRoom = (BoxRoom) roomFactory.getRoom(ed,"BOXROOM");
		ShockRoom sRoom = (ShockRoom) roomFactory.getRoom(ed,"SHOCKROOM");
		
		MRIRoom mriRoom = (MRIRoom) roomFactory.getRoom(ed,"MRIROOM");
		BloodRoom bloodRoom = (BloodRoom) roomFactory.getRoom(ed,"BLOODROOM");
		RadioRoom radioRoom = (RadioRoom) roomFactory.getRoom(ed,"RADIOROOM");
		
		

		while (time.getTime()<600000){
			simulator.nextStep();
		}
		System.out.println("simulation end");
		System.out.println(new DTDT(ed,"L1").calculate());
		System.out.println(patient0.getState());
		System.out.println(patient1.getState());
		System.out.println(patient2.getState());
		System.out.println(time.getTime());
		
		
		
	}
}
