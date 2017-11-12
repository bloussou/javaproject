package Factory;

public abstract class Room {

		private int id;
		private String name;
		private Distribution dist;
		private float cost;
		private String state;
		private int capacity;
		
		abstract void construct();
		abstract void updatePatientCharge();
		
}