package app;

import org.junit.Test;

public class CheckExceptionTest {
	@Test(expected = IntOverflowException.class)
	
	public void sumWithException() {
		Calculator.sum(Integer.MAX_VALUE, 1);
	}
}
