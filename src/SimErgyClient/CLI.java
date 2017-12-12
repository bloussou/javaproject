package SimErgyClient;

import java.util.ArrayList;
import java.util.Scanner;

import Emergency.ED;
import Events.EventsManager;
import Events.Time;
import Events.TimeStamp;
import Factory.FactoryCreator;
import Factory.PeopleFactory;
import HR.Patient;
import Proba.Exp;
import Proba.Gamma;
import Proba.LogNorm;
import Proba.Uniform;

public class CLI {

	private ArrayList<ED> eds;
	private ArrayList<String> commandLine;
	
	public Time time;
	public Factory.AbstractFactory humanFactory;
	public Factory.AbstractFactory roomFactory;
	public Factory.AbstractFactory facilityFactory;
	public EventsManager simulator;
	public EDGeneratorFromFile EDGenerator;
	
	public CLI(){
		this.commandLine = new ArrayList<String>();
		this.eds = new ArrayList<ED>();
		
		this.time = Time.getInstanceTime();
		this.humanFactory = FactoryCreator.getFactory("HUMAN");
		this.roomFactory = FactoryCreator.getFactory("ROOM");
		this.facilityFactory = FactoryCreator.getFactory("FACILITY");
		this.simulator = new EventsManager(this.eds);
		this.EDGenerator = new EDGeneratorFromFile();
		}
	
	public CLI(ArrayList<ED> eds){
		this.commandLine = new ArrayList<String>();
		this.eds = eds;
		this.time = Time.getInstanceTime();
		this.humanFactory = FactoryCreator.getFactory("HUMAN");
		this.roomFactory = FactoryCreator.getFactory("ROOM");
		this.facilityFactory = FactoryCreator.getFactory("FACILITY");
		this.simulator = new EventsManager(this.eds);
		this.EDGenerator = new EDGeneratorFromFile();
	}
	
	
	/**
	 * Get a command line prompted
	 * @return
	 */
	public void promptCommandLine(){
		this.commandLine = new ArrayList<String>();
		Scanner scanner = new Scanner(System.in);
		System.out.print("Next command-->\t"); 
		String line = scanner.nextLine();	
		scanner.close();
		String[] temp = {};
		String word = "";
		boolean spaceDetected = false;
		for (int i = 0; i < line.length(); i++) {
			if (!(Character.isWhitespace(line.charAt(i)))){
				spaceDetected = false;
				word += line.charAt(i);
			}
			else if(!spaceDetected){
				this.commandLine.add(word);
				word = "";
				spaceDetected = true;	
			}
		}
		if(!spaceDetected){this.commandLine.add(word);}
	}

	/**
	 * Analyses weither the command entered is correct, and process it
	 */
	public void analyseCommand(){
		if(!(this.commandLine.isEmpty())){
			
		// ------ COMMAND : HELP
			if(this.commandLine.get(0).equalsIgnoreCase("help")){ this.commandHelp(); }
		
		// ------ COMMAND : LOAD EDs FROM FILE	
			else if(this.commandLine.get(0).equalsIgnoreCase("loadFromFile")){ this.loadFromFile(); }
			
		// ------ COMMAND : CREATION OF A NEW ED	
			else if(this.commandLine.get(0).equalsIgnoreCase("createED")){ this.commandCreateED(); }
					
		// ------ COMMAND : CREATION OF A NEW WAITING OR CONSULTATION ROOM
			else if(this.commandLine.get(0).equalsIgnoreCase("addRoom")){ this.commandAddRoom(); }			
		
		// ------ COMMAND : CREATION OF A NEW TESTROOM
			else if(this.commandLine.get(0).equalsIgnoreCase("addTestRoom")){ this.commandAddTestRoom(); }			
			
		// ------ COMMAND : CREATION OF A NEW NURSE
			else if(this.commandLine.get(0).equalsIgnoreCase("addNurse")){ this.commandAddNurse(); }	
			
		// ------ COMMAND : CREATION OF A NEW PHYSICIAN
			else if(this.commandLine.get(0).equalsIgnoreCase("addPhysician")){ this.commandAddPhysician(); }			
			
		// ------ COMMAND : CREATION OF A NEW TRANSPORTER
			else if(this.commandLine.get(0).equalsIgnoreCase("addTransporter")){ this.commandAddTransporter(); }			
			
		// ------ COMMAND : CREATION OF A NEW ARRIVING PATIENT
			else if(this.commandLine.get(0).equalsIgnoreCase("addPatient")){ this.commandAddPatient(); }
			
		// ------ COMMAND : CREATION OF A NEW PATIENTS FLOW
			else if(this.commandLine.get(0).equalsIgnoreCase("setNewPatientFlow")){ this.commandSetNewPatientFlow(); }
			
		// ------ COMMAND : SET PATIENT INSURANCE
			else if(this.commandLine.get(0).equalsIgnoreCase("setPatientInsurance")){ this.commandSetPatientInsurance(); }
						
		// ------ COMMAND : DISPLAY ED 
			else if(this.commandLine.get(0).equalsIgnoreCase("display")){ }
			
		// ------ COMMAND : RUN SIMULATION UNTIL T=?
			else if(this.commandLine.get(0).equalsIgnoreCase("runSimulation")){ }
			
		// ------ COMMAND : RUN NEXT STEP OF SIMULATION
			else if(this.commandLine.get(0).equalsIgnoreCase("runNextStep")){ }
			
		// ------ COMMAND : RESET SIMULATION
			else if(this.commandLine.get(0).equalsIgnoreCase("resetAll")){ }	
			
		// ------ COMMAND : SHUT DOWN
			else if(this.commandLine.get(0).equalsIgnoreCase("exit")){ }
		}
		else{
			System.out.println("Unexpected command, please enter 'help' command to see the command list");
		}
	}
	
	
	
	/**
	 * ---- PRINT CLI HELP
	 */
	public void commandHelp(){
		for (int i = 0; i < 10; i++) {System.out.println("\n");}
		System.out.println("<<<<<<<<<<<<<<<<HELP>>>>>>>>>>>>>>>>");
		System.out.println("Command lines should start with one of the command words");
		System.out.println("Command lines parameters should be separated by whiteblancspaces\n");
		System.out.println("--------- COMMAND WORDS ---------");
		System.out.println("help");
		System.out.println("loadFromFile\t\t<fileName>");
		System.out.println("createED\t\t<EDname>");
		
		System.out.println("addRoom\t\t\t<EDname> <RoomType> <RoomName>");
		System.out.println("addTestRoom\t\t<EDname> <RoomType> <DistributionType> <DistributionParameters>");
		System.out.println("addNurse\t\t<EDname> <NurseName> <NurseSurname>");
		System.out.println("addPhysician\t\t<EDname> <PhysicianName> <PhysicianeSurname>");
		System.out.println("addTransporter\t\t<EDname> <TransporterName> <TransporterSurname>");
		System.out.println("addPatient\t\t<EDname> <severityLevel> <arrivalTime>");
		System.out.println("setNewPatientFlow\t<EDname> <severityLevel> <DistributionType> <Distribution parameters> <startTime> <endTime>");
		System.out.println("setPatientInsurance\t<EDname> <PatientID> <HealthInsurance>");
		System.out.println("display\t\t\t<EDname>");
		System.out.println("runSimulation\t\t<endTime>");
		System.out.println("runNextStep");
		System.out.println("resetAll");
		System.out.println("exit");
		for (int i = 0; i < 3; i++) {System.out.println("\n");}
	}
	
	/**
	 * ---- LOAD EDs FROM TEXTFILE
	 */
	public void loadFromFile(){
		if(this.commandLine.size()>1){
			String fileName = this.commandLine.get(1);
			this.EDGenerator.edsGenerating(fileName);
		}
		else {
			System.out.println("This command requires at least 1 argument : filename");;
		}
	}
	
	/**
	 * ---- CREATE A NEW ED
	 */
	public void commandCreateED(){
		if(this.commandLine.size()==1){
			String edName = "ED" + this.eds.size();
			this.eds.add(new ED(edName,"Unknown"));
		}
		else if(this.commandLine.size()==2){
			this.eds.add(new ED(this.commandLine.get(1),"Unknown"));
		}
		else {
			this.eds.add(new ED(this.commandLine.get(1),this.getCommandLine().get(2)));
		}
	}
	
	/**
	 * ---- CREATE A NEW WAITING OR CONSULTATION ROOM
	 */
	public void commandAddRoom(){
		if(this.commandLine.size()==3){
			String edName = this.commandLine.get(1);
			String roomType = this.commandLine.get(2);
			int edIndex = -1;
			for (int i = 0; i < this.eds.size(); i++) {
				if(this.eds.get(i).getName().equalsIgnoreCase(edName)){
					edIndex = i;
					this.roomFactory.getRoom(this.eds.get(i), roomType);
				}
			}
			if (edIndex == -1){System.out.println("ED introuvable");}
		}
		else if(this.commandLine.size()>3){
			String edName = this.commandLine.get(1);
			String roomType = this.commandLine.get(2);
			String roomName = this.commandLine.get(3);
			int edIndex = -1;
			for (int i = 0; i < this.eds.size(); i++) {
				if(this.eds.get(i).getName().equalsIgnoreCase(edName)){
					edIndex = i;
					this.roomFactory.getRoom(this.eds.get(i), roomType, roomName);
				}
			}
			if (edIndex == -1){System.out.println("ED introuvable");}
		}
		else {
			System.out.println("This command requires at least 2 arguments : edName roomType");
		}
	}
	
	/**
	 * ---- CREATE A NEW TEST ROOM
	 */
	public void commandAddTestRoom(){
		if(this.commandLine.size()>4){
			String edName = this.commandLine.get(1);
			String testRoomType = this.commandLine.get(2);
			String distribution = this.commandLine.get(3);
			ArrayList<Double> data = EDGeneratorFromFile.getNumbersFromLine(this.commandLine.get(4),0);
			if(data.size()==1){data.add(0.0);}
			float[] parameters = {data.get(0).floatValue(), data.get(1).floatValue()};
			int edIndex = -1;
			for (int i = 0; i < this.eds.size(); i++) {
				if(this.eds.get(i).getName().equalsIgnoreCase(edName)){
					edIndex = i;
					this.roomFactory.getTestRoom(this.eds.get(i), testRoomType, distribution, parameters);
				}
			}
			if (edIndex == -1){System.out.println("ED introuvable");}
		}
		else {
			System.out.println("This command requires at least 4 arguments : edName testRoomType distribution distParameters(between parenthesis");
		}
	}
	
	/**
	 * ---- CREATE A NEW NURSE
	 */
	public void commandAddNurse(){
		if(this.commandLine.size()==2){
			String edName = this.commandLine.get(1);
			int edIndex = -1;
			for (int i = 0; i < this.eds.size(); i++) {
				if(this.eds.get(i).getName().equalsIgnoreCase(edName)){
					edIndex = i;
					this.humanFactory.getStaff(this.eds.get(i), "NURSE");
				}
			}
			if (edIndex == -1){System.out.println("ED introuvable");}
		}
		else if(this.commandLine.size()>3){
			String edName = this.commandLine.get(1);
			String nurseName = this.commandLine.get(2);
			String nurseSurname = this.commandLine.get(3);
			int edIndex = -1;
			for (int i = 0; i < this.eds.size(); i++) {
				if(this.eds.get(i).getName().equalsIgnoreCase(edName)){
					edIndex = i;
					this.humanFactory.getStaff(this.eds.get(i), "NURSE", nurseName, nurseSurname, "idle");
				}
			}
			if (edIndex == -1){System.out.println("ED introuvable");}
		}
		else {
			System.out.println("This command requires only edName or at least 3 arguments : edName nurseName nurseSurname");
		}
	}
	
	/**
	 * ---- CREATE A NEW PHYSICIAN
	 */
	public void commandAddPhysician(){
		if(this.commandLine.size()==2){
			String edName = this.commandLine.get(1);
			int edIndex = -1;
			for (int i = 0; i < this.eds.size(); i++) {
				if(this.eds.get(i).getName().equalsIgnoreCase(edName)){
					edIndex = i;
					this.humanFactory.getStaff(this.eds.get(i), "PHYSICIAN");
				}
			}
			if (edIndex == -1){System.out.println("ED introuvable");}
		}
		else if(this.commandLine.size()>3){
			String edName = this.commandLine.get(1);
			String physicianName = this.commandLine.get(2);
			String physicianSurname = this.commandLine.get(3);
			int edIndex = -1;
			for (int i = 0; i < this.eds.size(); i++) {
				if(this.eds.get(i).getName().equalsIgnoreCase(edName)){
					edIndex = i;
					this.humanFactory.getStaff(this.eds.get(i), "PHYSICIAN", physicianName, physicianSurname, "idle");
				}
			}
			if (edIndex == -1){System.out.println("ED introuvable");}
		}
		else {
			System.out.println("This command requires only edName or at least 3 arguments : edName physicianName physicianSurname");
		}
	}
	
	/**
	 * ---- CREATE A NEW TRANSPORTER
	 */
	public void commandAddTransporter(){
		if(this.commandLine.size()==2){
			String edName = this.commandLine.get(1);
			int edIndex = -1;
			for (int i = 0; i < this.eds.size(); i++) {
				if(this.eds.get(i).getName().equalsIgnoreCase(edName)){
					edIndex = i;
					this.humanFactory.getStaff(this.eds.get(i), "TRANSPORTER");
				}
			}
			if (edIndex == -1){System.out.println("ED introuvable");}
		}
		else if(this.commandLine.size()>3){
			String edName = this.commandLine.get(1);
			String transpName = this.commandLine.get(2);
			String transpSurname = this.commandLine.get(3);
			int edIndex = -1;
			for (int i = 0; i < this.eds.size(); i++) {
				if(this.eds.get(i).getName().equalsIgnoreCase(edName)){
					edIndex =i;
					this.humanFactory.getStaff(this.eds.get(i), "TRANSPORTER", transpName, transpSurname, "idle");
				}
			}
			if (edIndex == -1){System.out.println("ED introuvable");}
		}
		else {
			System.out.println("This command requires only edName or at least 3 arguments : edName transporterName tranporterSurname");
		}
	}
	
	/**
	 * ---- CREATE A NEW FLOW OF PATIENTS
	 */
	public void commandSetNewPatientFlow(){

		if(this.commandLine.size()>6){
			String edName = this.commandLine.get(1);
			String sevLevel = this.commandLine.get(2);
			String distribution = this.commandLine.get(3);
			ArrayList<Double> distParam = EDGeneratorFromFile.getNumbersFromLine(this.commandLine.get(4), 0);
			int startTime = Integer.parseInt(this.commandLine.get(5));
			int endTime = Integer.parseInt(this.commandLine.get(6));
			
			int edIndex = -1;
			for (int i = 0; i < this.eds.size(); i++) {
				if(this.eds.get(i).getName().equalsIgnoreCase(edName)){edIndex = i;}
			}
			if (edIndex == -1){System.out.println("ED introuvable");}
			else {

				TimeStamp nextArrivalTime;

				if (sevLevel.equalsIgnoreCase("L1") || sevLevel.equalsIgnoreCase("L2") || sevLevel.equalsIgnoreCase("L3") || sevLevel.equalsIgnoreCase("L4") || sevLevel.equalsIgnoreCase("L5")){

					if(distribution.equalsIgnoreCase("UNIFORM") && distParam.size()==2){
						int minLap = distParam.get(0).intValue();
						int maxLap = distParam.get(1).intValue();
						int durationBeforeNextArrival = (int) Uniform.randSample(minLap, maxLap);
						nextArrivalTime = new TimeStamp(startTime + durationBeforeNextArrival);
						while(nextArrivalTime.getTimeStamp()<=endTime){
							this.humanFactory.getPatient(this.eds.get(edIndex), sevLevel, nextArrivalTime);
							durationBeforeNextArrival = (int) Uniform.randSample(minLap, maxLap);
							nextArrivalTime = new TimeStamp(nextArrivalTime.getTimeStamp() + durationBeforeNextArrival);
						}
					}
					else if (distribution.equalsIgnoreCase("EXP") && distParam.size()==1){
						int lambda = distParam.get(0).intValue();
						int durationBeforeNextArrival = (int) Exp.randSample(lambda);
						nextArrivalTime = new TimeStamp(startTime + durationBeforeNextArrival);
						while(nextArrivalTime.getTimeStamp()<=endTime){
							this.humanFactory.getPatient(this.eds.get(edIndex), sevLevel, nextArrivalTime);
							durationBeforeNextArrival = (int) Exp.randSample(lambda);
							nextArrivalTime = new TimeStamp(nextArrivalTime.getTimeStamp() + durationBeforeNextArrival);
						}
					}
					else if (distribution.equalsIgnoreCase("GAMMA") && distParam.size()==2){
						int K = distParam.get(0).intValue();
						int T = distParam.get(1).intValue();
						int durationBeforeNextArrival = (int) Gamma.randSample(K, T);
						nextArrivalTime = new TimeStamp(startTime + durationBeforeNextArrival);
						while(nextArrivalTime.getTimeStamp()<=endTime){
							this.humanFactory.getPatient(this.eds.get(edIndex), sevLevel, nextArrivalTime);
							durationBeforeNextArrival = (int) Gamma.randSample(K, T);
							nextArrivalTime = new TimeStamp(nextArrivalTime.getTimeStamp() + durationBeforeNextArrival);
						}
					}
					else if (distribution.equalsIgnoreCase("LOGNORM") && distParam.size()==2){
						int E = distParam.get(0).intValue();
						int S = distParam.get(1).intValue();
						int durationBeforeNextArrival = (int) LogNorm.randSample(E, S);
						nextArrivalTime = new TimeStamp(startTime + durationBeforeNextArrival);
						while(nextArrivalTime.getTimeStamp()<=endTime){
							this.humanFactory.getPatient(this.eds.get(edIndex), sevLevel, nextArrivalTime);
							durationBeforeNextArrival = (int) LogNorm.randSample(E, S);
							nextArrivalTime = new TimeStamp(nextArrivalTime.getTimeStamp() + durationBeforeNextArrival);
						}
					}
					else{
						System.out.println("La loi de distribution ou son paramétrage n'est pas valide");
					}
				}
				else {System.out.println("Problème avec le degré de sévérité des patients pour l'ed " + this.eds.get(edIndex).getName());}
	
			}
		}
		else {
			System.out.println("This command requires 6 arguments : edName severityLevel distribution distributionParameters startTime endTime");
		}
	}
	
	/**
	 * ----CREATE A NEW PATIENT AddPatient ED severityLevel arrivalTime
	 */
	public void commandAddPatient(){
		if(this.commandLine.size()>3){
			String edName = this.commandLine.get(1);
			String sevLevel = this.commandLine.get(2);
			int arrivalTime = Integer.parseInt(this.commandLine.get(3));
			
			int edIndex = -1;
			for (int i = 0; i < this.eds.size(); i++) {
				if(this.eds.get(i).getName().equalsIgnoreCase(edName)){
					edIndex = i;
					this.humanFactory.getPatient(this.eds.get(edIndex), sevLevel, new TimeStamp(arrivalTime));
				}
			}
			if (edIndex == -1){System.out.println("ED introuvable");}
		}
		else {
			System.out.println("This command requires at least 3 arguments : edName severityLevel arrivalTime");
		}
	}
	
	/**
	 * ---- SET PATIENT HEALTH INSURANCE
	 */
	public void commandSetPatientInsurance(){
		if(this.commandLine.size()>3){
			String edName = this.commandLine.get(1);
			int patientId = Integer.parseInt(this.commandLine.get(2));
			String healthInsurance = this.commandLine.get(3);
			int edIndex = -1;
			boolean patientFound = false;
			for (int i = 0; i < this.eds.size(); i++) {
				if(this.eds.get(i).getName().equalsIgnoreCase(edName)){
					edIndex = i;
				}
			}
			if (edIndex == -1){System.out.println("ED introuvable");}
			else if (!(this.eds.get(edIndex).getDbPatient().get(0).isEmpty())){
				for (Patient patient : this.eds.get(edIndex).getDbPatient().get(0) ) {
					if(patient.getId()==patientId){ patient.setHealthInsurance(healthInsurance); patientFound = true; }
				}
				if(!patientFound){System.out.println("Patient non trouvé parmi les patients arrivés et non encore entrés dans le Département d'Urgence " + this.eds.get(edIndex).getName()); }
			}
			else { System.out.println("Il n'y a pas de patients qui viennent d'arriver"); }
		}
		else {
			System.out.println("This command requires only edName or at least 3 arguments : edName patientId healthInsurance");
		}
	}
	
	
	public void setCommandLine(ArrayList<String> commandLine) {
		this.commandLine = commandLine;
	}
	public ArrayList<String> getCommandLine() {
		return commandLine;
	}
	public ArrayList<ED> getEds() {
		return eds;
	}
	public void setEds(ArrayList<ED> eds) {
		this.eds = eds;
	}
	

	
}
