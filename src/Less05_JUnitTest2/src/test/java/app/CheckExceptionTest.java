package app;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CheckExceptionTest {
	@Rule
	public ExpectedException exception = ExpectedException.none(); // Инициализация переменной exception
	@Test
	public void sumWithException() {
		//exception.expect(IntOverflowException.class);
		exception.expect(RuntimeException.class);
		exception.expectMessage("Переполнение диапазона int при сложении " + ((long)Integer.MAX_VALUE + 1));
		Calculator.sum(Integer.MAX_VALUE, 1);
	}
}



