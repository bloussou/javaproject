package Factory;

import Emergency.ED;
import Events.TimeStamp;
import Facilities.Stretcher;
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

public class FactoryTest {

	public static void main(String[] args) {
		
				//get 2 different ED
				ED ED1 = new ED("ED1", "Suède");
				ED ED2 = new ED("ED2", "Nord");
			
				//get Abstract Factory
				Factory.AbstractFactory humanFactory = FactoryCreator.getFactory("HUMAN");
				Factory.AbstractFactory roomFactory = FactoryCreator.getFactory("ROOM");
				Factory.AbstractFactory facilityFactory = FactoryCreator.getFactory("FACILITY");
				
				//create different Human objects
				Nurse nurse1 = (Nurse) humanFactory.getStaff( ED1,"NURSE");
				System.out.println(nurse1.toString());
				Nurse nurse2 = (Nurse) humanFactory.getStaff( ED1,"NURSE", "Janet", "Blues", "OccupéeAFaireUnLit");
				System.out.println(nurse2.toString());
				Nurse nurse3 = (Nurse) humanFactory.getStaff( ED2, "NURSE");
				System.out.println(nurse3.toString());
				
				Patient patient1 = (Patient) humanFactory.getPatient(ED1, "L5", new TimeStamp());
				System.out.println(patient1.toString());
				Patient patient2 = (Patient) humanFactory.getPatient(ED1, "Jean", "Bonbeurre", "attendDeRentrer", "PASASSURE", "L1", new TimeStamp());
				System.out.println(patient2.toString());
				Patient patient3 = (Patient) humanFactory.getPatient(ED2, "L2", new TimeStamp());
				System.out.println(patient3.toString());
				Patient patient4 = (Patient) humanFactory.getPatient(ED1, "L5", new TimeStamp());
				System.out.println(patient4.toString());
				
				Physician phys1 = (Physician) humanFactory.getStaff(ED1, "PHYSICIAN");
				System.out.println(phys1.toString());
				Physician phys2 = (Physician) humanFactory.getStaff(ED2, "PHYSICIAN");
				System.out.println(phys2.toString());
				
				Transporter trans1 = (Transporter) humanFactory.getStaff(ED2, "TRANSPORTER");
				System.out.println(trans1.toString());
				Transporter trans2 = (Transporter) humanFactory.getStaff(ED1, "TRANSPORTER");
				System.out.println(trans2.toString());
				Transporter trans3 = (Transporter) humanFactory.getStaff(ED2, "TRANSPORTER","Bob", "Lebricoleur", "Dispo");
				System.out.println(trans3.toString());
				
				
				//create different Room objects
				WaitingRoom WR1 = (WaitingRoom) roomFactory.getRoom(ED1, "WAITINGROOM", "SC.46");
				System.out.println(WR1.toString());
				WaitingRoom WR2 = (WaitingRoom) roomFactory.getRoom(ED2, "WAITINGROOM");
				System.out.println(WR2.toString());
				
				BoxRoom BoxR1 = (BoxRoom) roomFactory.getRoom(ED1, "BOXROOM");
				System.out.println(BoxR1.toString());
				BoxRoom BoxR2 = (BoxRoom) roomFactory.getRoom(ED1, "BOXROOM", "BE150");
				System.out.println(BoxR2.toString());
				
				ShockRoom SR1 = (ShockRoom) roomFactory.getRoom(ED1, "SHOCKROOM");
				System.out.println(SR1.toString());
				ShockRoom SR2 = (ShockRoom) roomFactory.getRoom(ED2, "SHOCKROOM", "H204");
				System.out.println(SR2.toString());
				
				BloodRoom BloodR1 = (BloodRoom) roomFactory.getRoom(ED1, "BLOODROOM", "E.190");
				System.out.println(BloodR1.toString());
				BloodRoom BloodR2 = (BloodRoom) roomFactory.getRoom(ED2, "BLOODROOM");
				System.out.println(BloodR2.toString());
				
				MRIRoom MR1 = (MRIRoom) roomFactory.getRoom(ED1, "MRIROOM");
				System.out.println(MR1.toString());
				MRIRoom MR2 = (MRIRoom) roomFactory.getRoom(ED2, "MRIROOM", "e.15");
				System.out.println(MR2.toString());
				
				RadioRoom RR1 = (RadioRoom) roomFactory.getRoom(ED1, "RADIOROOM", "LV.415");
				System.out.println(RR1.toString());
				RadioRoom RR2 = (RadioRoom) roomFactory.getRoom(ED2, "RADIOROOM");
				System.out.println(RR2.toString());
				
				//create different facility objects
				Stretcher S1 = (Stretcher) facilityFactory.getFacility(ED1, "STRETCHER");
				System.out.println(S1.toString());
				Stretcher S2 = (Stretcher) facilityFactory.getFacility(ED2, "STRETCHER", "BranquardDeJoey");
				System.out.println(S2.toString());
				Stretcher S3 = (Stretcher) facilityFactory.getFacility(ED2, "STRETCHER", "BranquardDeTritus");
				System.out.println(S3.toString());
	}
}
