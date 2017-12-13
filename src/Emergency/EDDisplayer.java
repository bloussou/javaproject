package Emergency;

import java.util.ArrayList;

import HR.Patient;

public class EDDisplayer {

	/**
	 * Display the total state of an ED
	 * @param ed
	 */
	public static void displayED(ED ed){
		EDDisplayer.displayEdHeader(ed);
		EDDisplayer.displayEdStaff(ed);
		EDDisplayer.displayEdFacilities(ed);
		EDDisplayer.displayEdRooms(ed);
		EDDisplayer.displayEdTestRooms(ed);
		EDDisplayer.displayEdPatientsLx(ed, "L1");
		EDDisplayer.displayEdPatientsLx(ed, "L2");
		EDDisplayer.displayEdPatientsLx(ed, "L3");
		EDDisplayer.displayEdPatientsLx(ed, "L4");
		EDDisplayer.displayEdPatientsLx(ed, "L5");
	}
	
	/**
	 * Display the Name and country of an ed (if the country is not unknown)
	 * @param ed
	 */
 	public static void displayEdHeader(ED ed){
		if(ed.getCountry().equalsIgnoreCase("Unknown")){
			System.out.println("\n\n-------- " + ed.getName() + " --------");
		}
		else {
			System.out.println("-------- " + ed.getName() + " in " + ed.getCountry() + " --------");
		}
	}
	/**
	 * Display the state of the staff of an ed
	 * @param ed
	 */
	public static void displayEdStaff(ED ed){
		System.out.println("\nSTAFF :");
		
		//NURSES
		int nurseIdle = ed.getDbNurse().get(0).size();
		int nurseTransporting = ed.getDbNurse().get(1).size();
		int nurseOffDuty = ed.getDbNurse().get(2).size();
		int nurseTotal = nurseIdle + nurseTransporting + nurseOffDuty;
		System.out.println(nurseTotal + "Nurses -->\t\t" + nurseIdle + "Idle,\t\t" + nurseTransporting + "Transporting,\t" + nurseOffDuty + "OffDuty");
	
		//PHYSICIANS
		int physIdle = ed.getDbPhysician().get(0).size();
		int physTransporting = ed.getDbPhysician().get(1).size();
		int physOffDuty = ed.getDbPhysician().get(2).size();
		int physTotal = physIdle + physTransporting + physOffDuty;
		System.out.println(physTotal + "Physicians -->\t\t" + physIdle + "Idle,\t\t" + physTransporting + "Transporting,\t" + physOffDuty + "OffDuty");
	
		//TRANSPORTERS
		int transIdle = ed.getDbTransporter().get(0).size();
		int transTransporting = ed.getDbTransporter().get(1).size();
		int transOffDuty = ed.getDbTransporter().get(2).size();
		int transTotal = transIdle + transTransporting + transOffDuty;
		System.out.println(transTotal + "Transporters -->\t\t" + transIdle + "Idle,\t\t" + transTransporting + "Transporting,\t" + transOffDuty + "OffDuty");
	}
	/**
	 * Display the state of the facilities of an ed
	 * @param ed
	 */
	public static void displayEdFacilities(ED ed){
		System.out.println("\nFACILITIES :");
		
		//STRETCHERS
		int stretchFree = ed.getDbStretcher().get(0).size();
		int stretchOccupied = ed.getDbStretcher().get(1).size();
		int stretchTotal = stretchFree + stretchOccupied;
		System.out.println(stretchTotal + "Stretchers -->\t\t" + stretchFree + "Free,\t\t" + stretchOccupied + "Occupied");
	}
	/**
	 * Display the state of the waiting and consultation rooms of an ed
	 * @param ed
	 */
	public static void displayEdRooms(ED ed){
		System.out.println("\nROOMS :");
		
		//WAITINGROOMS
		int wRAvailable = ed.getDbWaitingRoom().get(0).size();
		int wRFull = ed.getDbWaitingRoom().get(1).size();
		int wRTotal = wRAvailable + wRFull;
		System.out.println(wRTotal + "WaitingRooms -->\t\t" + wRAvailable + "Available,\t\t" + wRFull + "Full");
		
		//BOXROOMS
		int bRFree = ed.getDbBoxRoom().get(0).size();
		int bROccupied = ed.getDbBoxRoom().get(1).size();
		int bRTotal = bRFree + bROccupied;
		System.out.println(bRTotal + "BoxRooms -->\t\t" + bRFree + "Free,\t\t" + bROccupied + "Occupied");		
		
		//SHOCKROOMS
		int sRFree = ed.getDbShockRoom().get(0).size();
		int sROccupied = ed.getDbShockRoom().get(1).size();
		int sRTotal = sRFree + sROccupied;
		System.out.println(sRTotal + "ShockRooms -->\t\t" + sRFree + "Free,\t\t" + sROccupied + "Occupied");
	}
	/**
	 * Display the state of the test rooms of an ed
	 * @param ed
	 */
	public static void displayEdTestRooms(ED ed){
		System.out.println("\nTESTROOMS :");
		
		//BLOODROOMS
		int bloodRFree = ed.getDbBloodRoom().get(0).size();
		int bloodROccupied = ed.getDbBloodRoom().get(1).size();
		int bloodRTotal = bloodRFree + bloodROccupied;
		System.out.println(bloodRTotal + "BloodRooms -->\t\t" + bloodRFree + "Free,\t\t" + bloodROccupied + "Occupied");
		
		//MRIROOMS
		int mriRFree = ed.getDbMRIRoom().get(0).size();
		int mriROccupied = ed.getDbMRIRoom().get(1).size();
		int mriRTotal = mriRFree + mriROccupied;
		System.out.println(mriRTotal + "MriRooms -->\t\t" + mriRFree + "Free,\t\t" + mriROccupied + "Occupied");		
		
		//RADIOROOMS
		int radioRFree = ed.getDbRadioRoom().get(0).size();
		int radioROccupied = ed.getDbRadioRoom().get(1).size();
		int radioRTotal = radioRFree + radioROccupied;
		System.out.println(radioRTotal + "RadioRooms -->\t\t" + radioRFree + "Free,\t\t" + radioROccupied + "Occupied");				
	}
	/**
	 * Display the state of the patients of an ed, given a precise severitylevel
	 * @param ed
	 * @param severityLevel
	 */
	public static void displayEdPatientsLx(ED ed, String severityLevel){
		
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
		
		System.out.println("\nPATIENTS " + severityLevel + "          Total : " + total);
		System.out.println(stateNumberOfPatient.get(0) + "Arriving,\t\t" + stateNumberOfPatient.get(1) + "Registered,\t\t" + stateNumberOfPatient.get(2) + "Transporting,\t\t" + stateNumberOfPatient.get(3) + "WaitingForConsultation,");
		System.out.println(stateNumberOfPatient.get(4) + "inConsultation,\t\t" + stateNumberOfPatient.get(5) + "WaitingForMRI,\t\t" + stateNumberOfPatient.get(6) + "WaitingForBloodTest,\t\t" + stateNumberOfPatient.get(7) + "WaintingForRadioTest");
		System.out.println(stateNumberOfPatient.get(8) + "Transporting,\t\t" + stateNumberOfPatient.get(9) + "WaitingForMRIStart,\t\t" + stateNumberOfPatient.get(10) + "WaitingForBloodTestStart,\t\t" + stateNumberOfPatient.get(11) + "WaitingForRadioStart");
		System.out.println(stateNumberOfPatient.get(16) + "Testing,\t\t" + stateNumberOfPatient.get(12) + "BloodTested,\t\t" + stateNumberOfPatient.get(13) + "MRITested,\t\t" + stateNumberOfPatient.get(14) + "RadioTested");
		System.out.println(stateNumberOfPatient.get(15) + "Released");
		
	}
}
