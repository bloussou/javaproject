package Proba;
import java.lang.Math;

/**
 * 
 * @author brieuc
 * This class has the sample method for a uniform distribution of probability between [l,h]
 * Uniform.sample(l,h)
 * 
 */

public class Uniform {
	
	public Uniform(){}
	

	
	/*
	 * The sample method of uniform gives a random number between l and h
	 * Uniform.sample(l,h)
	 */
	public double randSample(double l , double h) {
		double x = Math.random();
		
		return (x*(h-l)+l);
	}
	
}


