package ru.progwards.calculator;
import org.junit.*;
import static junit.framework.TestCase.*;

public class CalculatorTest {
	
	static Calculator calc;
	
	@BeforeClass
	public static void init() {
		calc = new Calculator();
	}
	
	@Test
	public void sum() {
		assertEquals(8, calc.sum(3, 5));
		assertEquals(1, calc.sum(-1, 1));
		assertEquals(calc.sum(Integer.MAX_VALUE, -1), Integer.MAX_VALUE - 1);
	}
	
	@Test(expected = ArithmeticException.class)
	public void sumException() {
		assertTrue(calc.sum(Integer.MAX_VALUE, 1) == Integer.MAX_VALUE + 1);
	}
	
	@Test(timeout = 1000)
	public void diff() {
		calc.diff(0, 0);
	}
	
	@AfterClass
	public static void Destroy() {
		calc = null;
	}
}
