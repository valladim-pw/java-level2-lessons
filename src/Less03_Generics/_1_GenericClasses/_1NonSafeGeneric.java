package Less03_Generics._1_GenericClasses;

import java.util.Arrays;

public class _1NonSafeGeneric {
	private Object[] items;
	private int count;
	
	public _1NonSafeGeneric(){
		items = new Object[10];
		count = 0;
	}
	
	static class A {
		public A() {}
		
		@Override
		public String toString() {
			return 	"class A";
		}
	}
	
	public void add(Object item) {
		System.out.println(count);
		items[count++] = item;
	}
	
	public  Object get(int idx) {
		System.out.println(items[idx]);
		return items[idx];
	}
	
	@Override
	public String toString() {
		return 	"items=" + Arrays.toString(items);
	}
	
	public static void main(String[] args) {
		_1NonSafeGeneric nsg = new _1NonSafeGeneric();
		String first = "Hello";
		nsg.add(first);
		Integer val = 10;
		nsg.add(val);
		A a = new A();
		nsg.add(a);
		//ClassCastException это Runtime Exception
		//String result = (String) nsg.get(1);
		if(nsg.get(1) instanceof String) {
			String result = (String) nsg.get(1);
		}
		if(nsg.get(2) instanceof A) {
			A result = (A) nsg.get(2);
		}
		
		System.out.println(nsg.toString());
	}
}
