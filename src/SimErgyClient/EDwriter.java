package SimErgyClient;

import java.io.*;

import Emergency.ED;

public class EDwriter {
	private ED ed;
	File file;
	FileWriter writer;
	
	
	public EDwriter(ED ed) throws IOException{
		this.ed = ed;
		this.file = new File("SimErgy_Save_"+ed.getName()+"_"+ed.getCountry() + ".txt");
		// creates the file
		file.createNewFile();
		// create a file 
		this.writer = new FileWriter(file);
		this.writer.write("Récapitulatif du fonctionnement de l'ED nommé "+ed.getName()+" situé en "+ed.getCountry()+"\n \n");
	}
	
	public void writeInfo(String info) throws IOException{

			this.writer.write(info);
			this.writer.flush();
	}
	
	public void closeWriting() throws IOException{
		this.writer.close();
	}
	

}
