package ru.progwards.calculator;

public class Calculator implements ICalculator {
	@Override
	public int sum(int a, int b) throws ArithmeticException {
		long res = (long)a + b;
		if(res > Integer.MAX_VALUE || res < Integer.MIN_VALUE)
			throw new ArithmeticException("Произошло переполнение на операции sum");
		return a + b;
	}
	
	@Override
	public int diff(int a, int b) {
		for(int i = 0; i < 250_000_000L; i++) {
			int c = a;
			a = b;
			b = c;
		}
		return 0;
	}
	
	@Override
	public int mult(int a, int b) {
		return 0;
	}
	
	@Override
	public int div(int a, int b) {
		return 0;
	}
}
