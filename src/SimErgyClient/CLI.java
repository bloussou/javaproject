package SimErgyClient;

import java.util.ArrayList;
import java.util.Scanner;

import Emergency.ED;
import Events.Time;
import Events.TimeStamp;
import Factory.FactoryCreator;
import Factory.PeopleFactory;
import Proba.Exp;
import Proba.Gamma;
import Proba.LogNorm;
import Proba.Uniform;

public class CLI {

	private ArrayList<ED> eds;
	private ArrayList<String> commandLine;
	Time time;
	public Factory.AbstractFactory humanFactory;
	public Factory.AbstractFactory roomFactory;
	public Factory.AbstractFactory facilityFactory;
	
	
	public CLI(){
		this.commandLine = new ArrayList<String>();
		this.time = Time.getInstanceTime();
		this.eds = new ArrayList<ED>();
		this.humanFactory = FactoryCreator.getFactory("HUMAN");
		this.roomFactory = FactoryCreator.getFactory("ROOM");
		this.facilityFactory = FactoryCreator.getFactory("FACILITY");
		}
	public CLI(ArrayList<ED> eds){
		this.commandLine = new ArrayList<String>();
		this.time = Time.getInstanceTime();
		this.eds = eds;
		this.humanFactory = FactoryCreator.getFactory("HUMAN");
		this.roomFactory = FactoryCreator.getFactory("ROOM");
		this.facilityFactory = FactoryCreator.getFactory("FACILITY");
	}
	
	
	/**
	 * Get a command line prompted
	 * @return
	 */
	public void promptCommandLine(){
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
			
			
		// ----- CREATION OF A NEW ED	
			 
			if(this.commandLine.get(0).equalsIgnoreCase("createED")){
				if(this.commandLine.size()==1){
					String edName = "EmergDep" + this.eds.size();
					this.eds.add(new ED(edName,"Unknown"));
				}
				else if(this.commandLine.size()==2){
					this.eds.add(new ED(this.commandLine.get(1),"Unknown"));
				}
				else {
					this.eds.add(new ED(this.commandLine.get(1),this.getCommandLine().get(2)));
				}
			}
			
			
		// ----- CREATION OF A NEW WAITING OR CONSULTATION ROOM
			
			else if(this.commandLine.get(0).equalsIgnoreCase("addRoom")){
				if(this.commandLine.size()==3){
					String edName = this.commandLine.get(1);
					String roomType = this.commandLine.get(2);
					int edIndex = -1;
					for (int i = 0; i < this.eds.size(); i++) {
						if(this.eds.get(i).getName().equalsIgnoreCase(edName)){
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
							this.roomFactory.getRoom(this.eds.get(i), roomType, roomName);
						}
					}
					if (edIndex == -1){System.out.println("ED introuvable");}
				}
				else {
					System.out.println("This command requires at least 1 arguments");
				}
			}
			
		
		// ----- CREATION OF A NEW TESTROOM
			
			else if(this.commandLine.get(0).equalsIgnoreCase("addTestRoom")){
				if(this.commandLine.size()>4){
					String edName = this.commandLine.get(1);
					String testRoomType = this.commandLine.get(2);
					String distribution = this.commandLine.get(3);
					String parameters = this.commandLine.get(4);
					int edIndex = -1;
					for (int i = 0; i < this.eds.size(); i++) {
						if(this.eds.get(i).getName().equalsIgnoreCase(edName)){
							this.roomFactory.getRoom(this.eds.get(i), testRoomType);
						}
					}
					if (edIndex == -1){System.out.println("ED introuvable");}
				}
				else {
					System.out.println("This command requires at least 4 arguments : edName testRoomType distribution distParameters(between parenthesis");
				}
			}
			
			
			// ------ CREATION OF A NEW NURSE
			
			else if(this.commandLine.get(0).equalsIgnoreCase("addNurse")){
				if(this.commandLine.size()==2){
					String edName = this.commandLine.get(1);
					int edIndex = -1;
					for (int i = 0; i < this.eds.size(); i++) {
						if(this.eds.get(i).getName().equalsIgnoreCase(edName)){
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
							this.humanFactory.getStaff(this.eds.get(i), "NURSE", nurseName, nurseSurname, "idle");
						}
					}
					if (edIndex == -1){System.out.println("ED introuvable");}
				}
				else {
					System.out.println("This command requires only edName or at least 3 arguments : edName nurseName nurseSurname");
				}
			}
			
			
		// ------ CREATION OF A NEW PHYSICIAN
			
			else if(this.commandLine.get(0).equalsIgnoreCase("addPhysician")){
				if(this.commandLine.size()==2){
					String edName = this.commandLine.get(1);
					int edIndex = -1;
					for (int i = 0; i < this.eds.size(); i++) {
						if(this.eds.get(i).getName().equalsIgnoreCase(edName)){
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
							this.humanFactory.getStaff(this.eds.get(i), "PHYSICIAN", physicianName, physicianSurname, "idle");
						}
					}
					if (edIndex == -1){System.out.println("ED introuvable");}
				}
				else {
					System.out.println("This command requires only edName or at least 3 arguments : edName physicianName physicianSurname");
				}
			}
			
			
		// ------ CREATION OF A NEW TRANSPORTER
			
			else if(this.commandLine.get(0).equalsIgnoreCase("addTransporter")){
				if(this.commandLine.size()==2){
					String edName = this.commandLine.get(1);
					int edIndex = -1;
					for (int i = 0; i < this.eds.size(); i++) {
						if(this.eds.get(i).getName().equalsIgnoreCase(edName)){
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
							this.humanFactory.getStaff(this.eds.get(i), "TRANSPORTER", transpName, transpSurname, "idle");
						}
					}
					if (edIndex == -1){System.out.println("ED introuvable");}
				}
				else {
					System.out.println("This command requires only edName or at least 3 arguments : edName transporterName tranporterSurname");
				}
			}
			
			
		// ------- CREATION OF A NEW PATIENTS FLOW
			
			else if(this.commandLine.get(0).equalsIgnoreCase("setNewPatientFlow")){
				if(this.commandLine.size()>7){
					String edName = this.commandLine.get(1);
					String sevLevel = this.commandLine.get(2);
					String distribution = this.commandLine.get(3);
					ArrayList<String> distParam = EDGeneratorFromFile.getNumbersFromLine(this.commandLine.get(4), 0);
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
								int minLap = Integer.parseInt(distParam.get(0));
								int maxLap = Integer.parseInt(distParam.get(1));
								int durationBeforeNextArrival = (int) Uniform.randSample(minLap, maxLap);
								nextArrivalTime = new TimeStamp(startTime + durationBeforeNextArrival);
								while(nextArrivalTime.getTimeStamp()<=endTime){
									this.humanFactory.getPatient(this.eds.get(edIndex), sevLevel, nextArrivalTime);
									durationBeforeNextArrival = (int) Uniform.randSample(minLap, maxLap);
									nextArrivalTime = new TimeStamp(nextArrivalTime.getTimeStamp() + durationBeforeNextArrival);
								}
							}
							else if (distribution.equalsIgnoreCase("EXP") && distParam.size()==1){
								int lambda = Integer.parseInt(distParam.get(0));
								int durationBeforeNextArrival = (int) Exp.RandSample(lambda);
								nextArrivalTime = new TimeStamp(startTime + durationBeforeNextArrival);
								while(nextArrivalTime.getTimeStamp()<=endTime){
									this.humanFactory.getPatient(this.eds.get(edIndex), sevLevel, nextArrivalTime);
									durationBeforeNextArrival = (int) Exp.RandSample(lambda);
									nextArrivalTime = new TimeStamp(nextArrivalTime.getTimeStamp() + durationBeforeNextArrival);
								}
							}
							else if (distribution.equalsIgnoreCase("GAMMA") && distParam.size()==2){
								int K = Integer.parseInt(distParam.get(0));
								int T = Integer.parseInt(distParam.get(1));
								int durationBeforeNextArrival = (int) Gamma.randSample(K, T);
								nextArrivalTime = new TimeStamp(startTime + durationBeforeNextArrival);
								while(nextArrivalTime.getTimeStamp()<=endTime){
									this.humanFactory.getPatient(this.eds.get(edIndex), sevLevel, nextArrivalTime);
									durationBeforeNextArrival = (int) Gamma.randSample(K, T);
									nextArrivalTime = new TimeStamp(nextArrivalTime.getTimeStamp() + durationBeforeNextArrival);
								}
							}
							else if (distribution.equalsIgnoreCase("LOGNORM") && distParam.size()==2){
								int E = Integer.parseInt(distParam.get(0));
								int S = Integer.parseInt(distParam.get(1));
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
		
			
		// ------
			
			
			
		}
		else{
			System.out.println("Unexpected command, please enter 'help' command to see the command list");
		}
	}
	
	
	
	public void setCommandLine(ArrayList<String> commandLine) {
		this.commandLine = commandLine;
	}
	public ArrayList<String> getCommandLine() {
		return commandLine;
	}
	
	public static void main(String[] args) {
		System.out.println("classe CLI");
		
		CLI cli = new CLI();
		cli.promptCommandLine();
		System.out.println(cli.getCommandLine());
	}

}
