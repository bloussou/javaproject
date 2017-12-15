package UseCase;

import java.util.ArrayList;

import Emergency.ED;
import Emergency.EDDisplayer;
import Events.EventsManager;
import Events.Time;
import KPI.DTDT;
import KPI.LOS;
import SimErgyClient.EDGeneratorFromFile;

public class UseCase3 {

	/** Use Case generated from textFile UseCase2.txt
	 * @param args
	 */
	public static void main(String[] args) {
	
		Time time = Time.getInstanceTime();
		ArrayList<ED> eds = new ArrayList<ED>();

		
		//LOAD AD FROM FIL USECASE1.TXT
		EDGeneratorFromFile EDG = new EDGeneratorFromFile();
		eds = EDG.edsGenerating("UseCase3.txt");
		EventsManager simulator = new EventsManager(eds);

		while (!simulator.isSimulationEnd()){
			EDDisplayer.displayED(eds.get(0));
			simulator.nextStep();
		}
		
		EDDisplayer.displayED(eds.get(0));
		
		System.out.println(LOS.toString(eds.get(0), "L1"));
		System.out.println(LOS.toString(eds.get(0), "L2"));
		System.out.println(LOS.toString(eds.get(0), "L3"));
		System.out.println(LOS.toString(eds.get(0), "L5"));
		System.out.println(DTDT.toString(eds.get(0), "L1"));
		System.out.println(DTDT.toString(eds.get(0), "L2"));
		System.out.println(DTDT.toString(eds.get(0), "L3"));
		System.out.println(DTDT.toString(eds.get(0), "L5"));
	}
}
