package Proba;

import org.apache.commons.math3.distribution.GammaDistribution; 



public class Gamma  {
	
	public Gamma(){}

	/**
	 * Return a sample which follows a gamma distribution with the parameters k and theta
	 * @param k
	 * @param theta
	 * @return {@link GammaDistribution#sample()}
	 */
	public double randSample(double k, double theta) {
		return new GammaDistribution(k, theta).sample();
	}
	
	
}
