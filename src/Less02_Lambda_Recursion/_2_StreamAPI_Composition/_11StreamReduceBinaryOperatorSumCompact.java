package Less02_Lambda_Recursion._2_StreamAPI_Composition;

import java.util.List;
import java.util.Optional;

public class _11StreamReduceBinaryOperatorSumCompact {
	public static void main(String[] args) {
		List<Integer> list = List.of(5, 2, 1, 8, 3, 21);
		int sum = list.stream().reduce((a, x) -> a + x).orElse(0);
		
		System.out.println("sum: " + sum);
	}
}
