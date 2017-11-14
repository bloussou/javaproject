package Proba;
import java.lang.Math;

public class Exp {
	
	public Exp(){}
	/**
	 * 
	 * @author brieuc
	 * This class has the sample method for an exponential distribution of probability with a lambda parameter 
	 * Exp.sample(lambda)
	 * 
	 */
	
	public double RandSample(double lambda) {
		/*
		 * The sample method of Exp gives random numbers exponentially distributed if you put it in a for loop
		 * Warning ! lambda must be >0
		 */
		if (lambda>0){
			double x = Math.random();	
			return Math.log(1/(1-x))*1/lambda;
		}	
		else{
			return(lambda);
		}
	}

}
