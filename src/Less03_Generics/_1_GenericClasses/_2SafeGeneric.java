package Less03_Generics._1_GenericClasses;

import java.util.Arrays;

public class _2SafeGeneric<T> {
	private T[] items;
	private int count;
	
	public _2SafeGeneric(){
		items = (T[])new Object[10];
		count = 0;
	}
	
	static class A {
		public A() {}
		
		@Override
		public String toString() {
			return 	"class A";
		}
	}
	
	public void add(T item) {
		System.out.println(count);
		items[count++] = item;
	}
	
	public T get(int idx) {
		System.out.println(items[idx]);
		return items[idx];
	}
	
	@Override
	public String toString() {
		return 	"items=" + Arrays.toString(items);
	}
	
	public static void main(String[] args) {
		_2SafeGeneric<A> nsg = new _2SafeGeneric();
		//Если мы укажем тип дженерика, например <A>,
		//то при попытке добавить объекты другого типа будет ошибка компиляции!!!
		//Этим мы достигаем безопасности класса!!!
		String first = "Hello";
		//nsg.add(first);
		Integer val = 10;
		//nsg.add(val);
		A a = new A();
		nsg.add(a);
		//ClassCastException
		//String result = (String) nsg.get(1);
		A result2 = (A) nsg.get(2);
//		if(nsg.get(1) instanceof String) {
//			String result = (String) nsg.get(1);
//		}
//		if(nsg.get(2) instanceof A) {
//			A result = (A) nsg.get(2);
//		}
		
		System.out.println(nsg.toString());
	}
}
