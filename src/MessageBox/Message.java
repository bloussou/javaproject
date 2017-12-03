package MessageBox;

import com.sun.jmx.snmp.Timestamp;

import Events.TimeStamp;
import HR.Patient;
import HR.Physician;

public class Message {
	/**
	 * The patient why the mail has been writing
	 */
	private Patient patient;
	/**
	 * the receiver of the message, a physician
	 */
	private Physician physician;
	/**
	 * the object of the message
	 */
	private String obj;
	/**
	 * the content of the message
	 */
	private String content;
	/**
	 * the date of the message writing
	 */
	private TimeStamp time;
	/**
	 * a int to give a unique id to each message
	 */
	private static int messageId;
	/**
	 * the unique id of the message
	 */
	private int Id;
	
	/**
	 * The message builder, it gives the id to the message and put it in the mailBox a the physician
	 * @param patient
	 * @param physician
	 * @param obj
	 * @param content
	 * @see Physician#getMailBox()
	 * @see Message#patient
	 * @see Message#physician
	 * @see Message#obj
	 * @see Message#content
	 * @see Message#time
	 * @see Message#messageId
	 */
	public Message(Patient patient, Physician physician, String obj, String content) {
		super();
		this.setPatient(patient);
		this.physician = physician;
		this.obj = obj;
		this.content = content;
		this.time = new TimeStamp();
		
		Message.messageId += 1;
		this.setId(messageId);
		
		//the new message is stored in the unread directory
		physician.getMailBox().get(0).add(this);
	}
	/**
	 * it display all the unread message and change the message to already read
	 * @see Physician#getMailBox()
	 * @see Timestamp#toString()
	 * @see Message#obj
	 * @see Message#content
	 * @see Message#time
	 */
	public void read(){
		System.out.println("[OBJECT] : "+this.obj+"\n [TIME] : "+this.time.toString()+"\n [MESSAGE] : "+  this.content);
		this.physician.getMailBox().get(1).add(this);
		this.physician.getMailBox().get(0).remove(this);
	}
	/**
	 * it change the message state from already read to unread
	 * @see Physician#getMailBox()
	 */
	public void unRead(){
		this.physician.getMailBox().get(0).add(this);
		this.physician.getMailBox().get(1).remove(this);
	}
	/**
	 * it removes this message from the physician mailBox
	 * @see Physician#getMailBox()
	 */
	public void removeMessage(){
		this.physician.getMailBox().get(0).remove(this);
		this.physician.getMailBox().get(1).remove(this);
	}
	
	
	

	/**
	 * 
	 * @return {@link Message#Id}
	 */
	public int getId() {
		return Id;
	}
	/**
	 * Set the {@link Message#Id}
	 * @param id
	 */
	public void setId(int id) {
		Id = id;
	}
	/**
	 * 
	 * @return {@link Message#patient}
	 */
	public Patient getPatient() {
		return patient;
	}
	/**
	 * Set the message {@link Message#patient}
	 * @param patient
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	
	
	

}
