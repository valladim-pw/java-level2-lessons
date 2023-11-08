package Less02_Lambda_Recursion._2_StreamAPI_Composition;

import java.util.List;
import java.util.stream.Collectors;

public class _16StreamDropWhile {
	public static void main(String[] args) {
		List<Integer> list = List.of(5, 144, 2, 1, 233, 8, 13, 21, 34, 55, 3, 1, 89);
		List<Integer> filtered = list.stream().sorted().dropWhile(x ->  x < 100).collect(Collectors.toList());
		
		System.out.println(filtered);
	}
}
