package Less02_Lambda_Recursion._1_IntroToLambdaExpressions;

import java.util.List;

public class _2LambdaExample {
	public static void main(String[] args) {
		List<Integer> list = List.of(1, 2, 3, 4, 5);
		
		list.forEach(x -> System.out.println("element = " + x));
	}
}
