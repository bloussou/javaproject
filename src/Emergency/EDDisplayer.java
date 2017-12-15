package Emergency;

import java.util.ArrayList;
import HR.*;
import KPI.DTDT;
import KPI.LOS;

public class EDDisplayer {

	/**
	 * Display the total state of an ED
	 * @param ed
	 */
	public static String displayED(ED ed){
		String display = "";
		
		display += EDDisplayer.displayEdHeader(ed);
		display += EDDisplayer.displayEdStaff(ed);
		display += EDDisplayer.displayEdFacilities(ed);
		display += EDDisplayer.displayEdRooms(ed);
		display += EDDisplayer.displayEdTestRooms(ed);
		display += EDDisplayer.displayEdPatients(ed, "L1");
		display += EDDisplayer.displayEdPatients(ed, "L2");
		display += EDDisplayer.displayEdPatients(ed, "L3");
		display += EDDisplayer.displayEdPatients(ed, "L4");
		display += EDDisplayer.displayEdPatients(ed, "L5");
		display += EDDisplayer.displayEdKpis(ed);
		
		return display;
		
	}
	
	/**
	 * Display the Name and country of an ed (if the country is not unknown)
	 * @param ed
	 */
 	public static String displayEdHeader(ED ed){
 		String returnValue = "";
		if(ed.getCountry().equalsIgnoreCase("Unknown")){
			returnValue += "\n\n-------- " + ed.getName() + "  TIME = " + ed.time.toString() + " --------\n";
		}
		else {
			returnValue += "\n\n-------- " + ed.getName() + " in " + ed.getCountry() +  "  TIME = " + ed.time.toString() + " --------\n";
		}
		
		return returnValue;
	}
	/**
	 * Display the state of the staff of an ed
	 * @param ed
	 */
	public static String displayEdStaff(ED ed){
		String returnValue = "";
		returnValue += "\nSTAFF :\n";
		
		//NURSES
		int nurseIdle = ed.getDbNurse().get(0).size();
		int nurseTransporting = ed.getDbNurse().get(1).size();
		int nurseOffDuty = ed.getDbNurse().get(2).size();
		int nurseTotal = nurseIdle + nurseTransporting + nurseOffDuty;
		returnValue += "\t\t" + nurseTotal + " Nurses -->\t\t" + nurseIdle + " Idle,\t" + nurseTransporting + " Transporting,\t" + nurseOffDuty + " OffDuty\n";
	
		//PHYSICIANS
		int physIdle = ed.getDbPhysician().get(0).size();
		int physTransporting = ed.getDbPhysician().get(1).size();
		int physOffDuty = ed.getDbPhysician().get(2).size();
		int physTotal = physIdle + physTransporting + physOffDuty;
		returnValue += "\t\t" + physTotal + " Physicians -->\t" + physIdle + " Idle,\t" + physTransporting + " Visiting,\t" + physOffDuty + " OffDuty\n";
	
		//TRANSPORTERS
		int transIdle = ed.getDbTransporter().get(0).size();
		int transTransporting = ed.getDbTransporter().get(1).size();
		int transOffDuty = ed.getDbTransporter().get(2).size();
		int transTotal = transIdle + transTransporting + transOffDuty;
		returnValue += "\t\t" + transTotal + " Transporters -->\t" + transIdle + " Idle,\t" + transTransporting + " Transporting,\t" + transOffDuty + " OffDuty\n";
	
		return returnValue;
	}
	/**
	 * Display the state of the facilities of an ed
	 * @param ed
	 */
	public static String displayEdFacilities(ED ed){
		String returnValue = "";
		returnValue += "\nFACILITIES :\n";
		
		//STRETCHERS
		int stretchFree = ed.getDbStretcher().get(0).size();
		int stretchOccupied = ed.getDbStretcher().get(1).size();
		int stretchTotal = stretchFree + stretchOccupied;
		returnValue += "\t\t" + stretchTotal + " Stretchers -->\t" + stretchFree + " Free,\t" + stretchOccupied + " Occupied\n";
	
		return returnValue;
	}
	/**
	 * Display the state of the waiting and consultation rooms of an ed
	 * @param ed
	 */
	public static String displayEdRooms(ED ed){
		String returnValue = "";
		returnValue += "\nROOMS :\n";
		
		//WAITINGROOMS
		int wRAvailable = ed.getDbWaitingRoom().get(0).size();
		int wRFull = ed.getDbWaitingRoom().get(1).size();
		int wRTotal = wRAvailable + wRFull;
		returnValue += "\t\t" + wRTotal + " WaitingRooms -->\t" + wRAvailable + " Available,\t" + wRFull + " Full\n";
		
		//BOXROOMS
		int bRFree = ed.getDbBoxRoom().get(0).size();
		int bROccupied = ed.getDbBoxRoom().get(1).size();
		int bRTotal = bRFree + bROccupied;
		returnValue += "\t\t" + bRTotal + " BoxRooms -->\t\t" + bRFree + " Free,\t\t" + bROccupied + " Occupied\n";		
		
		//SHOCKROOMS
		int sRFree = ed.getDbShockRoom().get(0).size();
		int sROccupied = ed.getDbShockRoom().get(1).size();
		int sRTotal = sRFree + sROccupied;
		returnValue += "\t\t" + sRTotal + " ShockRooms -->\t" + sRFree + " Free,\t\t" + sROccupied + " Occupied\n";
		
		return returnValue;
	}
	/**
	 * Display the state of the test rooms of an ed
	 * @param ed
	 */
	public static String displayEdTestRooms(ED ed){
		String returnValue = "";
		returnValue += "\nTESTROOMS :\n";
		
		//BLOODROOMS
		int bloodRFree = ed.getDbBloodRoom().get(0).size();
		int bloodROccupied = ed.getDbBloodRoom().get(1).size();
		int bloodRTotal = bloodRFree + bloodROccupied;
		returnValue += "\t\t" + bloodRTotal + " BloodRooms -->\t" + bloodRFree + " Free,\t\t" + bloodROccupied + " Occupied\n";
		
		//MRIROOMS
		int mriRFree = ed.getDbMRIRoom().get(0).size();
		int mriROccupied = ed.getDbMRIRoom().get(1).size();
		int mriRTotal = mriRFree + mriROccupied;
		returnValue += "\t\t" + mriRTotal + " MriRooms -->\t\t" + mriRFree + " Free,\t\t" + mriROccupied + " Occupied\n";		
		
		//RADIOROOMS
		int radioRFree = ed.getDbRadioRoom().get(0).size();
		int radioROccupied = ed.getDbRadioRoom().get(1).size();
		int radioRTotal = radioRFree + radioROccupied;
		returnValue += "\t\t" + radioRTotal + " RadioRooms -->\t" + radioRFree + " Free,\t\t" + radioROccupied + " Occupied\n";	
		
		return returnValue;
	}
	/**
	 * Display the state of the patients of an ed, given a precise severitylevel
	 * @param ed
	 * @param severityLevel
	 */
	public static String displayEdPatients(ED ed, String severityLevel){
		
		String returnValue = "";
		
		ArrayList<Integer> stateNumberOfPatient = new ArrayList<Integer>();
		int total = 0;
		
		for (int k = 0; k < 17; k++) {
			int compteur = 0;
			for (Patient patient : ed.getDbPatient().get(k)){
				if (patient.getSeverityLevel().equalsIgnoreCase(severityLevel)){compteur ++;}
			}
			stateNumberOfPatient.add(compteur);
			total+=compteur;
		}
		
		if(total>0){
			returnValue += "\nPATIENTS " + severityLevel + "   (Total : " + total + ")\n";
			returnValue += "\t" + stateNumberOfPatient.get(0) + " Arriving,\t\t" + stateNumberOfPatient.get(1) + " Registered,\t\t" + stateNumberOfPatient.get(2) + " Transporting,\t\t\t" + stateNumberOfPatient.get(3) + " WaitingForConsultation,\n";
			returnValue += "\t" + stateNumberOfPatient.get(4) + " inConsultation,\t" + stateNumberOfPatient.get(5) + " WaitingForMRI,\t" + stateNumberOfPatient.get(6) + " WaitingForBloodTest,\t\t" + stateNumberOfPatient.get(7) + " WaitingForRadioTest\n";
			returnValue += "\t" + stateNumberOfPatient.get(8) + " Transporting,\t\t" + stateNumberOfPatient.get(9) + " WaitingForMRIStart,\t" + stateNumberOfPatient.get(10) + " WaitingForBloodTestStart,\t" + stateNumberOfPatient.get(11) + " WaitingForRadioStart\n";
			returnValue += "\t" + stateNumberOfPatient.get(16) + " Testing,\t\t" + stateNumberOfPatient.get(12) + " BloodTested,\t\t" + stateNumberOfPatient.get(13) + " MRITested,\t\t\t" + stateNumberOfPatient.get(14) + " RadioTested\n";
			returnValue += "\t" + stateNumberOfPatient.get(15) + " Released\n";
		}
		
		return returnValue;
	}

	public static String displayEdKpis(ED ed){
		String returnValue = "";
		returnValue += "\nKPIS :\n";
		
		//DTDT
		returnValue += "Average time before 1st consultation (DTDT) : " + DTDT.toString(ed, "L1") +"\n";
		returnValue += "Average time before 1st consultation (DTDT) : " + DTDT.toString(ed, "L2") +"\n";
		returnValue += "Average time before 1st consultation (DTDT) : " + DTDT.toString(ed, "L3") +"\n";
		returnValue += "Average time before 1st consultation (DTDT) : " + DTDT.toString(ed, "L4") +"\n";
		returnValue += "Average time before 1st consultation (DTDT) : " + DTDT.toString(ed, "L5") +"\n";
		
		//LOS
		returnValue += "Average length of stay : " + LOS.toString(ed, "L1") +"\n";
		returnValue += "Average length of stay : " + LOS.toString(ed, "L2") +"\n";
		returnValue += "Average length of stay : " + LOS.toString(ed, "L3") +"\n";
		returnValue += "Average length of stay : " + LOS.toString(ed, "L4") +"\n";
		returnValue += "Average length of stay : " + LOS.toString(ed, "L5") +"\n";
		
		return returnValue;
	}

	
	/**
	 * Display a patient
	 * @param patient
	 */
	public static void displayPatient(Patient patient){
		System.out.println(patient.toString());
	}
	/**
	 * Display a nurse
	 * @param nurse
	 */
	public static void displayNurse(Nurse nurse){
		System.out.println(nurse.toString());
	}
	/**
	 * Display a physician
	 * @param physician
	 */
	public static void displayPhysician(Physician physician){
		System.out.println(physician.toString());
	}
	/**
	 * Display a transporter
	 * @param transporter
	 */
	public static void displayTransporter(Transporter transporter){
		System.out.println(transporter.toString());
	}

}