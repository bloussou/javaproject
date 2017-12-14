package SimErgyClient;

import java.io.*;

import Emergency.ED;

public class EDwriter {
	private ED ed;
	File file;
	FileWriter writer;
	
	
	public EDwriter(ED ed) throws IOException{
		this.ed = ed;
		this.file = new File("SimErgy_"+ed.getName()+".txt");
		// creates the file
		file.createNewFile();
		// create a file 
		this.writer = new FileWriter(file);
		this.writer.write("Récapitulatif du fonctionnement de l'ED nommé "+ed.getName()+" situé en "+ed.getCountry()+"\n \n");
	}
	
	
	
	public void writeInfo(String info) throws IOException{

			this.writer.write(info+" \n");
			this.writer.flush();

	}
	
	public void closeInfo() throws IOException{
		this.writer.close();
	}
	
	public static void main(String[] args) throws IOException{
		ED ed = new ED("ED1", "France");
		EDwriter edwriter = new  EDwriter(ed);
		edwriter.writeInfo("prou");
		edwriter.writeInfo("lol");
		edwriter.writeInfo("fait");
		edwriter.closeInfo();
	}

}
