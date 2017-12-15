package KPI;

import java.util.ArrayList;


import Emergency.ED;
import Events.TimeStamp;
import HR.Patient;

/**
 * A class to calculate the avg length of stay in each ed for a category : L1, L2, L3, L4 or L5
 */
public class LOS{
	

	/**
	 * It calculates the avg length of stay in the ED, return -1 if it couldn't be calculate because no patient have been released
	 * @return The LOS of {@link LOS#ed}
	 */
	
	public static double calculate(ED ed, String severityLevel) {
		ArrayList<Patient> dbPatient = ed.getDbPatient().get(15);
		if (dbPatient.isEmpty()){
			return -1;
		}
		else {
			int sum = 0;
			int k = 0;
			for (int i=0; i<dbPatient.size();i++){
				if( severityLevel.equalsIgnoreCase(dbPatient.get(i).getSeverityLevel())){
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
				return sum/k;
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
	
	public static String toString(ED ed, String severityLevel) {
		TimeStamp los = new TimeStamp((int) LOS.calculate(ed, severityLevel));
		
		return severityLevel + " LOS = (" + los.toString() + ")";
	}



}
