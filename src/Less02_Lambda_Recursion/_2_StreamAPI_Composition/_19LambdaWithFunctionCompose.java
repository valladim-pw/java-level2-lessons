package Less02_Lambda_Recursion._2_StreamAPI_Composition;

import java.util.function.Function;

public class _19LambdaWithFunctionCompose {
	public static void main(String[] args) {
		Function<Double, Double> square = x -> x * x;
		System.out.println(square.apply(5.0));
		
		Function<Double, Double> squareRoot = x -> Math.sqrt(x);
		System.out.println(squareRoot.apply(25.0));
		
		Function<Double, Double> mod = x -> squareRoot.apply(square.apply(x));
		System.out.println(mod.apply(-5.0));
		System.out.println("-----------------------------");
		
		Function<Double, Double> mod2 = x -> square.andThen(squareRoot).apply(x);
		System.out.println(mod2.apply(-3.0));
		
		Function<Double, Double> mod3 = x -> squareRoot.compose(square).apply(x);
		System.out.println(mod2.apply(-22.0));
	}
}
