package Less02_Lambda_Recursion._1_IntroToLambdaExpressions;

import java.util.List;
import java.util.function.Predicate;

public class _5LambdaWithPredicate {
	public static void main(String[] args) {
		Predicate<Integer> isEven = x -> x % 2 == 0;
		System.out.println(isEven.test(5));
		System.out.println(isEven.test(1000));
	}
}
