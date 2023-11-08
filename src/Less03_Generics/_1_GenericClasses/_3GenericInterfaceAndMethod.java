package Less03_Generics._1_GenericClasses;

import java.util.*;

//Пример generic интерфейса для работы с базой данных
//Также пример generic метода myMath

public class _3GenericInterfaceAndMethod<K, E> {
	
	K index;
	E item;
	
	public _3GenericInterfaceAndMethod(){}
	
	public _3GenericInterfaceAndMethod(K key, E item) {
		index = key;
		this.item = item;
	}
	
	public void add(K index, E item){}
	
	public void delete(K index){}
	
	public E get(K index){
		throw new UnsupportedOperationException();
	}
	
	@Override
	public String toString() {
		return index.getClass().getSimpleName() + " " + item.getClass().getSimpleName();
	}
	
	public static void main(String[] args) {
		_3GenericInterfaceAndMethod<String, String> gtm = new _3GenericInterfaceAndMethod("1L", "Hello");
		System.out.println(gtm);
		
		List list = new ArrayList();
		list.add(new String("dsadsa"));
		list.add(10);
		
		int a = MyMath.<Integer>getMiddle(12, 3, 6, 8, 11, 9);
		System.out.println(a);
		String s = MyMath.<String>getMiddle("12w", "3f", "6y", "h8", "n11");
		System.out.println(s);
	}
}

class MyMath {
	public static <T> T getMiddle(T... args) {
		return args[args.length / 2];
	}
}
