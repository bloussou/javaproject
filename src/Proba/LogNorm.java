package Proba;
import java.lang.Math;


public class LogNorm {
	
	public LogNorm() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * f is the inner function in the integral
	 */
	private double f(double t){
		return Math.exp(-t*t);
	}
	
	
	/*
	 * integral( min, max, precision) is calculating the integral of the function f define in this class
	 * it uses the simpson method
	 */
	private double integral(double a, double b, int n){
		int i;
		int z;
		double h;
		double s;
		n=n+n;
	    s = f(a)*f(b);
	    h = (b-a)/n;                                        
	    z = 4;

	    for(i = 1; i<n; i++){
	        s = s + z * f(a+i*h);
	        z = 6 - z;
	    }
	    return (s * h)/3;
	    } 
	
	
	/*
	 * erf method implements the erf function
	 */
	private double erf(double z){
		return (2 / Math.sqrt(Math.PI) * this.integral(0, z, 100 ));
	}
	
	/*
	 * logNormRepar is the repartition function of the LogNorm distribution
	 */
	private double logNormRepar(double x, double mu, double sigma){
		return 1/2+1/2*this.erf((Math.log(x)-mu)/(sigma*Math.sqrt(2)));
		
	}
	
	/*
	 * logNormReparderivate is the derived of the repartition function
	 * 
	 */
	private double logNormReparDerivate(double x, double mu, double sigma){
		return 1/(x*sigma*Math.sqrt(2*Math.PI))*Math.exp((-1)*(Math.log(x)-mu)*(Math.log(x)-mu)/(2*sigma*sigma));
	}

	
	public double sample(double mu, double sigma) {
		double u = Math.random();
		double x = 1.7;
		int n = 0;
		int p = 6;
		while (Math.abs((this.logNormRepar(x, mu, sigma)-u)/this.logNormReparDerivate(x, mu, sigma))>=Math.pow(10, -p) && n<=10){
			x = x - (this.logNormRepar(x, mu, sigma)-u)/this.logNormReparDerivate(x, mu, sigma);
			n +=1;
			
		}
		return x;
		
	}
	
	public static void main(String[] args) {
		System.out.println(new LogNorm().sample(5,10));
		//System.out.println(new LogNorm().integral(0, 1, 100)); la fonction intégrale fonctionne !
	}
	

}
