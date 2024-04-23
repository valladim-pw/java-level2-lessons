import org.junit.Test;
import static org.junit.Assert.*;
import java.math.BigInteger;

public class FiboTest {
	@Test
	public void test1() { test(1, BigInteger.ONE);}
	@Test
	public void test2() { test(2, BigInteger.ONE);}
	@Test
	public void test3() { test(55, BigInteger.valueOf(139583862445L));}
	
	private void test(int num, BigInteger val) {
		BigInteger res = Fibo.fibonacci(num);
		assertEquals(
						"ќшибка. fibonacci(" + num + ") = " + val + ", а не " + res,
						res,
						val
		);
	}
}