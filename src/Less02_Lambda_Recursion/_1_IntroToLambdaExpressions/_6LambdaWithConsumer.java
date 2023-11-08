package Less02_Lambda_Recursion._1_IntroToLambdaExpressions;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class _6LambdaWithConsumer {
	public static void main(String[] args) {
		Consumer<Integer> doIt = x -> System.out.println(x);
		doIt.accept(1966);
		Consumer doIt2 = x -> System.out.println(x);
		doIt2.accept("Any String");
		doIt2.accept(new Book("Заповедник", "Довлатов", 635));
	}
}
