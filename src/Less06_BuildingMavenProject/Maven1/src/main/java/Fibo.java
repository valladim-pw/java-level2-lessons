import com.google.gson.Gson;
import java.math.BigInteger;

public class Fibo {
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
		System.out.println("55-е число Фибоначчи равно: " + fibonacci(55));
		
		Gson gson = new Gson();
		gson.toJson(123);
	}
}
