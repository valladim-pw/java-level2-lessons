package Less03_Generics._1_GenericClasses;

public class _5GenericApp<T> {
	private T[] elementData;
	
	public T get(int i) {
		throw new UnsupportedOperationException();
	}
	
	public void add(T t) {
	
	}
	
	public static void main(String[] args) {
		_5GenericApp<People> g1 = new _5GenericApp<>();
		_5GenericApp<String> g2 = new _5GenericApp<>();
		System.out.println(g1.getClass().getName());
		System.out.println(g2.getClass().getName());
		
		System.out.println(g1.getClass().equals(g2.getClass()));
	}
}
