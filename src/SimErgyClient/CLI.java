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
	 * Analyses weither the command entered id correct
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
				if(this.commandLine.size()==2){
					String edName = this.commandLine.get(1);
					int edIndex = -1;
					for (int i = 0; i < this.eds.size(); i++) {
						if(this.eds.get(i).getName().equalsIgnoreCase(edName)){
							this.roomFactory.getRoom();
						}
					}
					
				}
				
				
			}
				
			
			
			
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
