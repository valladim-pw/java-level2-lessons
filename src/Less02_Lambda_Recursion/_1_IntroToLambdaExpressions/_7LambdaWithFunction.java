package Less02_Lambda_Recursion._1_IntroToLambdaExpressions;

import java.util.function.*;

public class _7LambdaWithFunction {
	public static void main(String[] args) {
		Function<Double, Double> square = x -> x * x;
		System.out.println(square.apply(5.0));
		
		Function<Double, Double> squareRoot = x -> Math.sqrt(x);
		System.out.println(squareRoot.apply(25.0));
		
		Function<Double, Double> mod = x -> squareRoot.apply(square.apply(x));
		System.out.println(mod.apply(-5.0));
	}
}
