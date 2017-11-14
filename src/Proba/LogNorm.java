package Proba;
import java.lang.Math;


import org.apache.commons.math3.distribution.LogNormalDistribution; 

public class LogNorm {
	
	public LogNorm(){}
	
	/*
	 * f is the inner function in the integral
	 */
	private static double f(double t){
		return Math.exp(-t*t);
	}
	
	
	/*
	 * integral( min, max, precision) is calculating the integral of the function f define in this class
	 * it uses the simpson method
	 */
	private static double integral(double a, double b, int n){
		int i;
		int z;
		double h;
		double s;
		n=n+n;
	    s = LogNorm.f(a)*LogNorm.f(b);
	    h = (b-a)/n;                                        
	    z = 4;

	    for(i = 1; i<n; i++){
	        s = s + z * LogNorm.f(a+i*h);
	        z = 6 - z;
	    }
	    return (s * h)/3;
	    } 
	
	
	/*
	 * erf method implements the erf function
	 */
	private static double erf(double z){
		return (2 / Math.sqrt(Math.PI) * LogNorm.integral(0, z, 100 ));
	}
	
	/*
	 * logNormRepar is the repartition function of the LogNorm distribution
	 */
	private static double logNormRepar(double x, double mu, double sigma){
		return 0.5+0.5*LogNorm.erf(  (Math.log(x)-mu)  / (sigma*Math.sqrt(2))  );
	}
	
	/*
	 * logNormReparderivate is the derived of the repartition function
	 * 
	 */
	private static double logNormReparDerivate(double x, double mu, double sigma){
		return 1/(x*sigma*Math.sqrt(2*Math.PI))*Math.exp((-1)*(Math.log(x)-mu)*(Math.log(x)-mu)/(2*sigma*sigma));
	}

	
	public double RandSample(double mu, double sigma) {
		double u = Math.random();
		double x = 0.5;
		int n = 0;
		int p = 10;
		
	
		
		while (Math.abs((LogNorm.logNormRepar(x, mu, sigma)-u)/LogNorm.logNormReparDerivate(x, mu, sigma))>=Math.pow(10, -p) && n<=10){
			x = x - (LogNorm.logNormRepar(x, mu, sigma)-u)/LogNorm.logNormReparDerivate(x, mu, sigma);
			n = n + 1;
		}
		
		return x;	
	}
	
//	public static double Rand2sample(double mu, double sigma){
//		return new LogNormalDistribution(mu, sigma).sample();
//	}
	

}
