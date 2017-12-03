package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Emergency.ED;
import Factory.FactoryCreator;
import Factory.RoomFactory;
import Rooms.BloodRoom;
import Rooms.BoxRoom;
import Rooms.MRIRoom;
import Rooms.RadioRoom;
import Rooms.ShockRoom;
import Rooms.WaitingRoom;

public class RoomFactoryTest {

	@Test
	public void testGetRoomStringED() {
		ED ed = new ED("ED1", "France");
		
		RoomFactory roomFactory = (RoomFactory) FactoryCreator.getFactory("ROOM");
		
		BoxRoom boxRoom = (BoxRoom) roomFactory.getRoom("BOXROOM", ed);
		RadioRoom radioRoom = (RadioRoom) roomFactory.getRoom("RADIOROOM", ed);
		MRIRoom mriRoom = (MRIRoom) roomFactory.getRoom("MRIROOM", ed);
		BloodRoom bloodRoom = (BloodRoom) roomFactory.getRoom("BLOODROOM", ed);
		ShockRoom shockRoom = (ShockRoom) roomFactory.getRoom("SHOCKROOM", ed);
		WaitingRoom waitingRoom = (WaitingRoom) roomFactory.getRoom("WAITINGROOM", ed);
		
		//TEST
		assertTrue(ed.getDbBoxRoom().get(0).contains(boxRoom));
		assertTrue(boxRoom.getEd().equals(ed));
		
		assertTrue(ed.getDbMRIRoom().get(0).contains(mriRoom));
		assertTrue(mriRoom.getEd().equals(ed));
		
		assertTrue(ed.getDbRadioRoom().get(0).contains(radioRoom));
		assertTrue(radioRoom.getEd().equals(ed));
		
		assertTrue(ed.getDbBloodRoom().get(0).contains(bloodRoom));
		assertTrue(bloodRoom.getEd().equals(ed));
		
		assertTrue(ed.getDbShockRoom().get(0).contains(shockRoom));
		assertTrue(shockRoom.getEd().equals(ed));
		
		assertTrue(ed.getDbWaitingRoom().get(0).contains(waitingRoom));
		assertTrue(waitingRoom.getEd().equals(ed));	
		
	}

	@Test
	public void testGetRoomStringEDString() {
		ED ed = new ED("ED1", "France");
		
		RoomFactory roomFactory = (RoomFactory) FactoryCreator.getFactory("ROOM");
		
		BoxRoom boxRoom = (BoxRoom) roomFactory.getRoom("BOXROOM", ed, "1");
		RadioRoom radioRoom = (RadioRoom) roomFactory.getRoom("RADIOROOM", ed, "1");
		MRIRoom mriRoom = (MRIRoom) roomFactory.getRoom("MRIROOM", ed, "1");
		BloodRoom bloodRoom = (BloodRoom) roomFactory.getRoom("BLOODROOM", ed, "1");
		ShockRoom shockRoom = (ShockRoom) roomFactory.getRoom("SHOCKROOM", ed, "1");
		WaitingRoom waitingRoom = (WaitingRoom) roomFactory.getRoom("WAITINGROOM", ed, "1");
		
		//TEST
		assertTrue(ed.getDbBoxRoom().get(0).contains(boxRoom));
		assertTrue(boxRoom.getName().equals("1"));
		assertTrue(boxRoom.getEd().equals(ed));
		
		assertTrue(ed.getDbMRIRoom().get(0).contains(mriRoom));
		assertTrue(mriRoom.getName().equals("1"));
		assertTrue(mriRoom.getEd().equals(ed));
		
		assertTrue(ed.getDbRadioRoom().get(0).contains(radioRoom));
		assertTrue(radioRoom.getName().equals("1"));
		assertTrue(radioRoom.getEd().equals(ed));
		
		assertTrue(ed.getDbBloodRoom().get(0).contains(bloodRoom));
		assertTrue(bloodRoom.getName().equals("1"));
		assertTrue(bloodRoom.getEd().equals(ed));
		
		assertTrue(ed.getDbShockRoom().get(0).contains(shockRoom));
		assertTrue(shockRoom.getName().equals("1"));
		assertTrue(shockRoom.getEd().equals(ed));
		
		assertTrue(ed.getDbWaitingRoom().get(0).contains(waitingRoom));
		assertTrue(waitingRoom.getEd().equals(ed));
		assertTrue(waitingRoom.getEd().equals(ed));	
	}

}
