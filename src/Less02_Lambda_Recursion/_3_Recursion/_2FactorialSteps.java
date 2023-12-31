package Less02_Lambda_Recursion._3_Recursion;

public class _2FactorialSteps {
	public static int factorial(int val) {
		System.out.println("Прямой ход, val = " + val);
		int result = val <= 1 ? 1 : factorial(val - 1) * val;
		System.out.println("Обратный ход, val = " + val + ", result = " + result);
		return result;
	}
	
	public static void main(String[] args) {
		factorial(5);
	}
}
