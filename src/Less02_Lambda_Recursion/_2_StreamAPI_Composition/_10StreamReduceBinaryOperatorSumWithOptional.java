package Less02_Lambda_Recursion._2_StreamAPI_Composition;

import java.util.List;
import java.util.Optional;

public class _10StreamReduceBinaryOperatorSumWithOptional {
	public static void main(String[] args) {
		List<Integer> list = List.of(5, 2, 1, 8, 3, 21);
		Optional<Integer> oSum = list.stream().reduce((a, x) -> a + x);
		int sum = oSum.orElse(0);
		
		System.out.println("sum: " + sum);
	}
}
