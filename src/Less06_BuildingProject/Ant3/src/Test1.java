import org.junit.Test;
import static org.junit.Assert.*;
import java.math.BigInteger;

public class Test1 {
	@Test
	public void test1() { test(1, BigInteger.ONE);}
	@Test
	public void test2() { test(2, BigInteger.ONE);}
	@Test
	public void test3() { test(55, BigInteger.valueOf(139583862445L));}
	
	private void test(int num, BigInteger val) {
		BigInteger res = Main4Ant.fibonacci(num);
		assertEquals(
						"Ошибка. fibonacci(" + num + ") = " + val + ", а не " + res,
						res,
						val
		);
	}
}
