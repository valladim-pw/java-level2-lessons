package app;

public class IntOverflowException extends RuntimeException {
	int a;
	int b;
	public IntOverflowException(int a, int b, String msg) {
		super(msg);
		this.a = a;
		this.b = b;
	}
}
