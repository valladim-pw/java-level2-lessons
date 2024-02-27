package Less02_Lambda_Recursion._2_StreamAPI_Composition;

import java.util.List;

public class _09StreamReduceBinaryOperatorSum {
	public static void main(String[] args) {
		List<Integer> list = List.of(5, 2, 1, 8, 3, 21);
		int sum = list.stream().reduce(0, (a, x) -> a + x);
		
		System.out.println("sum: " + sum);
	}
}
