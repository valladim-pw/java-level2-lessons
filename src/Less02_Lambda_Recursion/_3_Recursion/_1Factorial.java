package Less02_Lambda_Recursion._3_Recursion;

public class _1Factorial {
	public static int factorial(int val) {
		if(val <= 1)
			return 1;
		//System.out.println(factorial(val - 1) * val);
		return factorial(val - 1) * val;
	}
	
	public static void main(String[] args) {
		System.out.println(factorial(5));
	}
}
