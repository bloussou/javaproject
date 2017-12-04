package KPI;

import java.util.ArrayList;

import Emergency.ED;
import HR.Patient;
/**
 * This class calculate the total of patient in the ED
 *
 */
public class NumberOfPatientInED extends Kpi {
	/**
	 * The ed where the NUmber of patient will be calculated
	 */
	private ED ed;
	
	
	public NumberOfPatientInED(ED ed) {
		this.setEd(ed);
	}
	
	
	/**
	 * 
	 * @return {@link NumberOfPatientInED#ed}
	 */
	public ED getEd() {
		return ed;
	}
	/**
	 * Set {@link NumberOfPatientInED#ed}
	 * @param ed
	 */
	public void setEd(ED ed) {
		this.ed = ed;
	}


	/**
	 * @return the number of patient in one ED
	 */
	@Override
	public double calculate() {
		int total = 0 ;
		ArrayList<ArrayList<Patient>> dbPatient = ed.getDbPatient();
		for (int i = 0 ; i<dbPatient.size(); i++){
			total += dbPatient.get(i).size();
		}
		
		return total;
	}



	@Override
	public String toString() {
		return "KPI :"+ " NumberOfPatientInED = " +this.calculate()+" for the ED : " +this.ed.getName();
	}
}
