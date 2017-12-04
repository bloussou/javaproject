package Proba;
import java.lang.Math;

/**
 * 
 * @author brieuc
 * This class has the sample method for a uniform distribution of probability between [l,h]
 * Uniform.sample(l,h)
 */

public class Uniform {
	
	public Uniform(){}
	

	
	/**
	 * 
	 * @param l : lower limit of the interval
	 * @param h : higher limit of the interval
	 * @return random double in [l,h]
	 */

	public static double randSample(double l , double h) {
		double x = Math.random();
		
		return (x*(h-l)+l);
	}
	
}


