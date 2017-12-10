package SimErgyClient;

import java.util.ArrayList;
import java.util.Scanner;

public class CommandLineInterpretor {

	private ArrayList<String> commandLine;
	
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
		String[] temp = line.split(" ");
		
		for (int i = 0; i < temp.length; i++) {
			this.commandLine.add(temp[i]);
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
	}
}
