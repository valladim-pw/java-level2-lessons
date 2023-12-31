package Less02_Lambda_Recursion._3_Recursion;

public class _4EuclidGreatestCommonDivisor {
	static int nod(int x, int y) {
		if(x == y)
			return x;
		return x > y ? nod(x - y, y) : nod(x, y - x);
	}
	
	public static void main(String[] args) {
		System.out.println(nod(15,25));
		System.out.println(nod(55,121));
		System.out.println(nod(1024,4096));
		System.out.println(nod(555,111));
		System.out.println(nod(54321,12345));
	}
}
