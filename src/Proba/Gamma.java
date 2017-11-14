package Proba;

import org.apache.commons.math3.distribution.GammaDistribution; 



public class Gamma  {
	
	public Gamma(){}

	
	public double randSample(double k, double theta) {
		return new GammaDistribution(k, theta).sample();
	}
	
	
}
