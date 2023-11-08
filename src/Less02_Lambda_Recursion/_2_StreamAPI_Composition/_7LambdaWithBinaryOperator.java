package Less02_Lambda_Recursion._2_StreamAPI_Composition;

import java.util.function.*;

public class _7LambdaWithBinaryOperator {
	public static void main(String[] args) {
		BinaryOperator<Integer> mod = (x1, x2) -> x1 % x2;
		mod.apply(5, 16);
		System.out.println(mod.apply(5, 16));
		System.out.println(mod.apply(16, 5));
	}
}
