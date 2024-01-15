package app;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import static org.junit.Assert.*;

@RunWith(Parameterized.class) // Запуск раннера Parametrized
public class CalculatorTest {
	  /*
		-----------------------------
		Вариант без конструктора
		-----------------------------
		 */
	  /*
		@Parameterized.Parameter(0) // В скобках индексы в массиве
			public int val1; // Передаваемое значение 1
		@Parameterized.Parameter(1)
			public int val2; // Передаваемое значение 2
		@Parameterized.Parameter(2)
			public int expected; // Ожидаемый результат
		*/
  public int val1;
  public int val2;
  public int expected;
	
	public CalculatorTest(int val1, int val2, int expected) {
		this.val1 = val1;
		this.val2 = val2;
		this.expected = expected;
	}
	
	@Parameterized.Parameters(name = "Тест{index} : {0} + {1} = {2}")
	public static Iterable<Object[]> dataForTest() {
		// Статический метод, возвращает обязательно Iterable
		return Arrays.asList(new Object[][] {
						// Преобразуем массив в список, т.к. массив не является Iterable
						{0, 0, 0}, // 5 + 0 = 5
						{5, 0, 5}, // 5 + 0 = 5
						{-5, -5, -10}, // -5 + (-5) = -10
						{34, 55, 89}, // 34 + 55 = 89
						{-34, -55, -89} // -34 + (-55) = -89
		});
	}
	
	@Test
	public void testWithParams() {
		assertEquals(expected, Calculator.sum(val1, val2));
	}
}
