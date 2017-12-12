package Proba;

public class MainTest {
	public static void main(String[] args){
		System.out.println(new Uniform().sample(5, 10));
		System.out.println(new Uniform().sample(8, 18));
		System.out.println(new Exp().sample(1));
	}

}
