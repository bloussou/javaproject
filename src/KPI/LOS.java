package KPI;

import java.util.ArrayList;

import Emergency.ED;
import HR.Patient;

/**
 * A class to calculate the avg length of stay in each ed
 */
public class LOS extends Kpi{
	/**
	 * The ed where the los has to be calculate
	 */
	private ED ed;
	/**
	 * the value of the los
	 */
	private double los;
	
	
	
	
	/**
	 * Build a LOS and set its ed
	 * @param ed
	 * @see LOS#ed
	 */
	public LOS(ED ed) {
		this.ed = ed;
	}
	/**
	 * 
	 * @return {@link LOS#ed}
	 */
	public ED getEd() {
		return ed;
	}
	/**
	 * It calculates the avg length of stay in the ED, return -1 if it couldn't be calculate because no patient have been released
	 * @return The LOS of {@link LOS#ed}
	 */
	@Override
	public double calculate() {
		ArrayList<Patient> dbPatient = ed.getDbPatient().get(15);
		if (dbPatient.isEmpty()){
			return -1;
		}
		else {
			int sum = 0;
			int k = 0;
			for (int i=0; i<dbPatient.size();i++){
				int arrivalTime = dbPatient.get(i).getArrivalTime().getTimeStamp();
				int departureTime = dbPatient.get(i).getDepartureTime().getTimeStamp();
				int length = departureTime-arrivalTime;
				sum += length;
				k+=1;
			}
			this.setLos(sum/k);
			return this.getLos();
		}	
	}
	/**
	 * return a string like :
	 * "KPI :"+ "LOS = " +this.calculate()+"for the ED : " +this.ed.getName()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "KPI :"+ "LOS = " +this.calculate()+"for the ED : " +this.ed.getName();
	}
	/**
	 * 
	 * @return {@link LOS#los}
	 */
	public double getLos() {
		return los;
	}
	/**
	 * set the {@link LOS#los}
	 * @param los
	 */
	public void setLos(double los) {
		this.los = los;
	}


	


}
