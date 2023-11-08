package Less02_Lambda_Recursion._1_IntroToLambdaExpressions;

import java.util.function.*;

public class _8LambdaWithSupplier {
	public static void main(String[] args) {
		Supplier<Integer> randomFactory = () -> (int) (Math.random() * 10 + 1);
		System.out.println("Random numbers from 1 to 10:");
		for(int i = 0; i < 10; i++) {
			System.out.println(randomFactory.get());
		}
	}
}
