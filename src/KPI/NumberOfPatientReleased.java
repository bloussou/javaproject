package KPI;

import java.util.ArrayList;

import Emergency.ED;
import HR.Patient;

/**
 * A class to calculate the number of patient released in an ed
 */
public class NumberOfPatientReleased extends Kpi{
	/**
	 * The ed where the NUmber of patient released will be calculated
	 */
	private ED ed;
	
	
	public NumberOfPatientReleased(ED ed) {
		this.setEd(ed);
	}
	
	
	/**
	 * 
	 * @return {@link NumberOfPatientReleased#ed}
	 */
	public ED getEd() {
		return ed;
	}
	/**
	 * Set {@link NumberOfPatientReleased#ed}
	 * @param ed
	 */
	public void setEd(ED ed) {
		this.ed = ed;
	}


	/**
	 * @return the number of patient released in one ED
	 */
	@Override
	public double calculate() {
		int total ;
		ArrayList<Patient> dbPatientReleased = ed.getDbPatient().get(15);
		total = dbPatientReleased.size();
		return total;
	}



	@Override
	public String toString() {
		return "KPI :"+ " NumberOfPatientReleased = " +this.calculate()+" for the ED : " +this.ed.getName();
	}
	

}
