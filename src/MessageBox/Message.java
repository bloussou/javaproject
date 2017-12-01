package MessageBox;

import Events.TimeStamp;
import HR.Patient;
import HR.Physician;

public class Message {
	private Patient patient;
	private Physician physician;
	private String obj;
	private String content;
	private TimeStamp time;
	private static int messageId;
	private int Id;
	
	
	public Message(Patient patient, Physician physician, String obj, String content) {
		super();
		this.patient = patient;
		this.physician = physician;
		this.obj = obj;
		this.content = content;
		
		Message.messageId += 1;
		this.setId(this.messageId);
		
		//the new message is stored in the unread directory
		physician.getMailBox().get(0).add(this);
	}
	
	public void read(){
		System.out.println("objet : "+this.obj+" heure : "+this.time.toString()+" message : "+  this.content);
		this.physician.getMailBox().get(1).add(this);
		this.physician.getMailBox().get(0).remove(this);
	}
	
	public void unRead(){
		this.physician.getMailBox().get(0).add(this);
		this.physician.getMailBox().get(1).remove(this);
	}
	
	public void removeMessage(){
		this.physician.getMailBox().get(0).remove(this);
		this.physician.getMailBox().get(1).remove(this);
	}
	
	
	


	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	
	
	
	

}
