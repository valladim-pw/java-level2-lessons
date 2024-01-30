package app;

public class Calculator {
	public static int sum(int a, int b) {
		long longRes = (long)a + b;
		if(longRes > Integer.MAX_VALUE || longRes < Integer.MIN_VALUE)
			throw new IntOverflowException(a, b, "Переполнение диапазона int при сложении " + longRes);
		  //throw new ArithmeticException("Переполнение диапазона int при сложении " + longRes);
		return a + b;
	}
	public static int diff(int a, int b) {
		long longRes = (long)a - b;
		if(longRes > Integer.MAX_VALUE || longRes < Integer.MIN_VALUE)
			throw new IntOverflowException(a, b, "Переполнение диапазона int при вычитании " + longRes);
		  //throw new ArithmeticException("Переполнение диапазона int при вычитании " + longRes);
		return a - b;
	}
	
	public static int mult(int a, int b) {
			long longRes = (long)a * b;
			if(longRes > Integer.MAX_VALUE || longRes < Integer.MIN_VALUE)
				throw new IntOverflowException(a, b, "Переполнение диапазона int при умножении " + longRes);
			  //throw new ArithmeticException("Переполнение диапазона int при умножении " + longRes);
			return a * b;
		}
	
	
	
	public static int div(int a, int b) {
		return a / b;
	}
	
	public static void main(String[] args) {
		Calculator calc = new Calculator();
		sum(Integer.MAX_VALUE, Integer.MAX_VALUE);
		diff(Integer.MIN_VALUE,  1);
		mult(2, Integer.MAX_VALUE);
	}
}
