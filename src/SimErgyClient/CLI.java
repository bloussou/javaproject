package SimErgyClient;

import java.util.ArrayList;
import java.util.Scanner;

import Emergency.ED;
import Events.Time;
import Factory.FactoryCreator;
import Factory.PeopleFactory;

public class CLI {

	private ArrayList<ED> eds;
	private ArrayList<String> commandLine;
	Time time;
	public Factory.AbstractFactory humanFactory;
	public Factory.AbstractFactory roomFactory;
	public Factory.AbstractFactory facilityFactory;
	
	
	public void CLI(){
		this.commandLine = new ArrayList<String>();
		this.time = Time.getInstanceTime();
		this.eds = new ArrayList<ED>();
		this.humanFactory = FactoryCreator.getFactory("HUMAN");
		this.roomFactory = FactoryCreator.getFactory("ROOM");
		this.facilityFactory = FactoryCreator.getFactory("FACILITY");
		}
	public void CLI(ArrayList<ED> eds){
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
		String[] temp = line.split(" ");
		
		for (int i = 0; i < temp.length; i++) {
			this.commandLine.add(temp[i]);
		}

	}

	/**
	 * Analyses weither the command entered is correct, and process it
	 */
	public void analyseCommand(){
		if(!(this.commandLine.isEmpty())){
			
			/**
			 * Creation of a new ED
			 */
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
			
			/**
			 * Creation of a new waiting or consultation room
			 */
			else if(this.commandLine.get(0).equalsIgnoreCase("addRoom")){
				if(this.commandLine.size()==3){
					String edName = this.commandLine.get(1);
					String roomType = this.commandLine.get(2);
					int edIndex = -1;
					for (int i = 0; i < this.eds.size(); i++) {
						if(this.eds.get(i).getName().equalsIgnoreCase(edName)){
							humanFactory.getRoom(this.eds.get(i), roomType);
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
							humanFactory.getRoom(this.eds.get(i), roomType, roomName);
						}
					}
					if (edIndex == -1){System.out.println("ED introuvable");}
				}
				else {
					System.out.println("This command requires at least 1 arguments");
				}
			}
			
			/**
			 * Creation of a new test Room
			 */
				
			
			
			
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
