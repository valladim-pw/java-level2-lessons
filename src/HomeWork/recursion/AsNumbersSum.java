package HomeWork.recursion;

import java.util.Arrays;

public class AsNumbersSum {
	public static String asNumbersSum(int number) {
		return number + findCombination(number - 1, 1, "");
	}
	public static String findCombination(int N, int i, String str) {
			String res = "";
//добрались до конца
			if (N <= 0) {
				return "";
			}
		
//чтобы без повторов, половина возможных переборов
			if (i > N) {
				res = findCombination(N, i - N, str + N + "+") +
								findCombination(N - 1, i + 1, str);
				System.out.println("i: " + i);
				System.out.println("N: " + N);
				System.out.println("res: " + res);
				return res;
			}	else {
				res = "=" + str + N + "+" + i +
								findCombination(i - 1, 1, str + N + "+") +
								findCombination(N - 1, i + 1, str);
				System.out.println("i: " + i);
				System.out.println("N: " + N);
				System.out.println("res: " + res);
				return res;
			}
			
				
	}
	public static void main(String[] args) {
		
		System.out.println(asNumbersSum(5));
		int[] a = new int[] {1, 2, 3, 4};
		System.out.println(Arrays.toString(a));
	}
}
