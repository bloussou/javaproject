package KPI;

import java.util.ArrayList;

import Emergency.ED;
import HR.Patient;

/**
 * A class to calculate the avg length of stay in each ed for a category : L1, L2, L3, L4 or L5
 */
public class LOS extends Kpi{
	
	/**
	 * The severity level of the patient
	 * <li>L1</li>
	 * <li>L2</li>
	 * <li>L3</li>
	 * <li>L4</li>
	 * <li>L5</li>
	 */
	private String severityLevel;
	/**
	 * The ed where the los has to be calculate
	 */
	private ED ed;
	/**
	 * the value of the los
	 */
	private double los;
	
	
	
	
	/**
	 * Build a LOS and set its ed and severity level
	 * @param ed
	 * @see LOS#ed
	 * @see LOS#severityLevel
	 */
	public LOS(ED ed, String severityLevel) {
		this.ed = ed;
		this.setSeverityLevel(severityLevel);
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
				if( this.getSeverityLevel().equalsIgnoreCase(dbPatient.get(i).getSeverityLevel())){
					int arrivalTime = dbPatient.get(i).getArrivalTime().getTimeStamp();
					int departureTime = dbPatient.get(i).getDepartureTime().getTimeStamp();
					int length = departureTime-arrivalTime;
					sum += length;
					k+=1;
				}
				else {
					
				}

			}
			if (k!=0) {
				this.setLos(sum/k);
				return this.getLos();
			}
			else {
				return -1;
			}
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
	public String getSeverityLevel() {
		return severityLevel;
	}
	public void setSeverityLevel(String severityLevel) {
		this.severityLevel = severityLevel;
	}


	


}
