package HR;

import java.util.ArrayList;

public class MsgBox implements Observable{

	private int id;
	private String status; //a voir si c'est utile
	private ArrayList<String> storedMsgs;
	private ArrayList<String> unreadMsgs;
	private ArrayList<String> removedMsgs;
	
	public void msgPopUp(){
		
	}

	@Override
	public void addObserver() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeObserver() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyObserver() {
		// TODO Auto-generated method stub
		
	}
	
	
}
