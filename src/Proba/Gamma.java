package Proba;

import org.apache.commons.math3.distribution.GammaDistribution; 



public class Gamma  {

	
	public static double randSample(double k, double theta) {
		return new GammaDistribution(k, theta).sample();
	}
	public static void main(String[] args) {
		System.out.println(Gamma.randSample(9, 0.5));
	}
	
}
