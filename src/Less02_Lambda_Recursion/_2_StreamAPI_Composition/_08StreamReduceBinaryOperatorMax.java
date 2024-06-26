package Less02_Lambda_Recursion._2_StreamAPI_Composition;

import java.util.*;
import java.util.function.*;

public class _08StreamReduceBinaryOperatorMax {
	public static void main(String[] args) {
		List<Integer> list = List.of(5, 2, 1, 8, 3, 21, 34, 55, 3, 1);
		int max = list.stream().reduce(0,
						(a, x) -> {
							System.out.println("a: " + a + ", x: " + x);
							return a < x ? x : a;
						}
		);
		
		System.out.println("max: " + max);
	}
}
