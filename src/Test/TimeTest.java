package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Events.Time;

public class TimeTest {

	@Test
	public void testGetInstanceTime() {
		Time time = Time.getInstanceTime();
		Time time2 = Time.getInstanceTime();
		
		//test
		assertTrue(time instanceof Time);
		assertTrue(time2 instanceof Time);
		
		//test de l'unicité
		
		time.timeGoes(10);
		time2.timeGoes(10);
		assertTrue(time.getTime() == 91);
		assertTrue(time2.getTime() == 91);
		
	}

	@Test
	public void testTimeGoes() {
		Time time = Time.getInstanceTime();
		time.timeGoes(10);
		assertTrue(time.getTime() == 10);
	}



	@Test
	public void testToString() {
		Time time = Time.getInstanceTime();
		time.timeGoes(10);
		assertTrue(time.toString().equalsIgnoreCase("day:0  hour:0  min:20"));
		
		time.timeGoes(51);
		System.out.println(time.toString());
		assertTrue(time.toString().equalsIgnoreCase("day:0  hour:1  min:11"));
	}

}
