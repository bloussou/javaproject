package UseCase;

import java.util.ArrayList;

import Emergency.ED;
import Emergency.EDDisplayer;
import Events.EventsManager;
import Events.Time;
import SimErgyClient.EDGeneratorFromFile;

public class UseCase1 {

	
	/**
	 * Use Case generated from textFile UseCase1.txt
	 * @param args
	 */
	public static void main(String[] args) {
	
		Time time = Time.getInstanceTime();
		ArrayList<ED> eds = new ArrayList<ED>();

		
		//LOAD AD FROM FIL USECASE1.TXT
		EDGeneratorFromFile EDG = new EDGeneratorFromFile();
		eds = EDG.edsGenerating("UseCase1.txt");
		EventsManager simulator = new EventsManager(eds);

		while (!simulator.isSimulationEnd()){
			EDDisplayer.displayED(eds.get(0));
			simulator.nextStep();
		}
		
		EDDisplayer.displayED(eds.get(0));
		
		
	}
	
	
	
	
}
