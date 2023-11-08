package Less02_Lambda_Recursion._2_StreamAPI_Composition;

import java.util.List;
import java.util.stream.Collectors;

public class _17StreamMap {
	public static void main(String[] args) {
		List<String> list = List.of("1", "1", "2", "3", "5", "8", "13", "21", "34", "55", "89");
		List<Integer> filtered = list.stream().map(Integer::valueOf).collect(Collectors.toList());
		
		System.out.println(filtered);
	}
}
