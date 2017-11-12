package HR;

import static org.junit.Assert.*;

import org.junit.Test;

import Factory.Human;

public class NurseTest {

	@Test
	public void test() {
		Human.setCompteurHumanId(0);
		Nurse nurse0 = new Nurse("Nathalie", "Jowns", "libre");
		Nurse nurse1 = new Nurse("Joe", "Dalton", "occupé");
		Nurse nurse2 = new Nurse("Elisabeth", "LeBottier", "libre");
		
		nurse0.toString();
		nurse1.toString();
		nurse2.toString();
	}

}
