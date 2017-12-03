package Proba;
import java.lang.Math;

public class Exp {
	
	public Exp(){}
	/**
	 * 

	 * This class has the sample method for an exponential distribution of probability with a lambda parameter 
	 * Exp.sample(lambda).
	 * <li>Warning ! lambda must be >0</li>
	 * @param lambda
	 */
	public double RandSample(double lambda) {
		if (lambda>0){
			double x = Math.random();	
			return Math.log(1/(1-x))*1/lambda;
		}	
		else{
			return(lambda);
		}
	}

}
