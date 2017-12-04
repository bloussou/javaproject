package KPI;

import java.util.ArrayList;

import Emergency.ED;
import HR.Patient;

/**
 * a class to calculate the door to door time, it's the time between the arrival and the first consultation with a physician
 */
public class DTDT extends Kpi{
	/**
	 * The ed where the los has to be calculate
	 */
	private ED ed;
	
	/**
	 * the value of the dtdt
	 */
	private double dtdt;
	
	/**
	 * Build a dtdt and set its ed
	 * @param ed
	 * @see DTDT#ed
	 */
	public DTDT(ED ed) {
		this.setEd(ed);
	}

	/**
	 * It calculates the door to door time on all the patient who have been handle by a physician !
	 * @see ED#getDbPatient()
	 * @return {@link DTDT#dtdt} or -1 if there is no patient with who have been handle by a physician !
	 */
	@Override
	public double calculate() {
		ArrayList<Patient> dbPatient = new ArrayList<Patient>();
		//initialize dbpatient which contains all the patient with an arrivalTime and a dtdTime
		for (int i = 4;i<16;i++){
			ArrayList<Patient> eddbPatient = ed.getDbPatient().get(i);
			if (!eddbPatient.isEmpty()){
				for (int j=0; j<eddbPatient.size(); j++){
					dbPatient.add(eddbPatient.get(j));
				}
			}
			else {
			}
		}
		

		if (dbPatient.isEmpty()){
			return -1;
		}
		else {
			int sum = 0;
			int k = 0;
			for (int i=0; i<dbPatient.size();i++){
				int arrivalTime = dbPatient.get(i).getArrivalTime().getTimeStamp();
				int dtdtime = dbPatient.get(i).getDtdtime().getTimeStamp();
				int length = dtdtime-arrivalTime;
				sum += length;
				k+=1;
			}
			this.setDtdt(sum/k);
			return this.getDtdt();
		}
	}

	/**
	 * Return the following string :
	 * "KPI :"+ "DTDT = " +this.calculate()+"for the ED : " +this.ed.getName()
	 */
	@Override
	public String toString() {
		return "KPI :"+ "DTDT = " +this.calculate()+"for the ED : " +this.ed.getName();
		
	}
	/**
	 * 
	 * @return {@link DTDT#ed}
	 */
	public ED getEd() {
		return ed;
	}
	/**
	 * Set the {@link DTDT#ed}
	 * @param ed
	 */
	public void setEd(ED ed) {
		this.ed = ed;
	}

	/**
	 * 
	 * @return {@link DTDT#dtdt}
	 */
	public double getDtdt() {
		return dtdt;
	}

	/**
	 * set the {@link DTDT#dtdt}
	 * @param dtdt
	 */
	public void setDtdt(double dtdt) {
		this.dtdt = dtdt;
	}

}
