package Less02_Lambda_Recursion._3_Recursion;

public class _5EuclidGreatestCommonDivisorSteps {
	static int nod(int x, int y) {
		System.out.println("Прямой ход, x = " + x + ", y = " + y);
		int result = 0;
		if(x == y)
			result = x;
		else
			result = x > y ? nod(x - y, y) : nod(x, y - x);
		System.out.println("Обратный ход, x = " + x + ", y = " + y + ", result = " + result);
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(nod(15,25));
	}
}
