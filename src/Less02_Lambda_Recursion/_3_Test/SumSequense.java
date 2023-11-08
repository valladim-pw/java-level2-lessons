package Less02_Lambda_Recursion._3_Test;

public class SumSequense {
	static int sumSequence(int n) {
		if (n == 1)
			return n;
		return sumSequence(n-2)+n;
	}
	
	public static void main(String[] args) {
		System.out.println(sumSequence(3));
		//System.out.println(sumSequence(2));
		System.out.println(sumSequence(1));
	}
}
