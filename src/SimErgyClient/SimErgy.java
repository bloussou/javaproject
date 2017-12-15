package SimErgyClient;

import java.io.IOException;
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

	public static void main(String[] args) throws IOException {
		
		System.out.println("\n\n\n\n\n\n ----------------- Bienvenue sur SimErgy !!! -----------------");
		System.out.println("\n Pour obtenir la liste des commandes entrez 'HELP'\n\n");
		
		CLI cli = new CLI();
		
		while (!cli.exit){
			cli.promptCommandLine();
			cli.analyseCommand();
		}
		
		
		
	}
}
