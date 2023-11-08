package Less02_Lambda_Recursion._1_IntroToLambdaExpressions;

import java.util.function.*;

public class _9LambdaWithUnaryOperator {
	public static void main(String[] args) {
		UnaryOperator<Integer> square = x -> x * x;
		System.out.println(square.apply(5));
		System.out.println(square.apply(-5));
	}
}
