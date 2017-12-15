package KPI;

import java.util.ArrayList;

import Emergency.ED;
import Events.TimeStamp;
import HR.Patient;

/**
 * a class to calculate the door to door time, it's the time between the arrival and the first consultation with a physician
 */
public class DTDT {

	/**
	 * It calculates the door to door time on all the patient who have been handle by a physician !
	 * @see ED#getDbPatient()
	 * @return {@link DTDT#dtdt} or -1 if there is no patient with who have been handle by a physician with the good severity level!
	 */
	public static double calculate(ED ed, String severityLevel) {
		ArrayList<Patient> dbPatient = new ArrayList<Patient>();
		//initialize dbpatient which contains all the patient with an arrivalTime and a dtdTime
		for (int i = 3;i<ed.getDbPatient().size();i++){
			ArrayList<Patient> eddbPatient = ed.getDbPatient().get(i);
			if (!eddbPatient.isEmpty()){
				for (int j=0; j<eddbPatient.size(); j++){
					if (severityLevel.equalsIgnoreCase(eddbPatient.get(j).getSeverityLevel()) && eddbPatient.get(j).getPhysician()!=null){
						dbPatient.add(eddbPatient.get(j));
					}
					else{	
					}
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
				int consultationTime = dbPatient.get(i).getFirstConsultationTime().getTimeStamp();
				int length = consultationTime-arrivalTime;
				sum += length;
				k+=1;
			}
			return sum/k;
		}
	}

	/**
	 * Return the following string :
	 * "KPI :"+ "DTDT = " +this.calculate()+"for the ED : " +this.ed.getName()
	 */
	public static String toString(ED ed, String severityLevel) {
		TimeStamp dtdt = new TimeStamp((int)DTDT.calculate(ed, severityLevel));
		return severityLevel + " DTDT = (" + dtdt.toString() + ")" ;
		
	}
}
