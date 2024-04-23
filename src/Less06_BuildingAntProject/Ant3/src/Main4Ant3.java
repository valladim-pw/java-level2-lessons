import java.math.BigInteger;

public class Main4Ant3 {
	public static BigInteger fibonacci(int n) {
		BigInteger prev = BigInteger.ONE;
		BigInteger fibo = BigInteger.ZERO;
		for(int i = 0; i < n; i++) {
			BigInteger tmp = prev;
			prev = fibo;
			fibo = fibo.add(tmp);
		}
		return fibo;
	}
	
	public static void main(String[] args) {
		int n = 55;
		System.out.println(n + "-ое число Фибоначчи: " + fibonacci(n));
	}
}