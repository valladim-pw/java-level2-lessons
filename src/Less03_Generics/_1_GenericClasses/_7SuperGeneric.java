package Less03_Generics._1_GenericClasses;

import java.util.ArrayList;
import java.util.List;

public class _7SuperGeneric<T> {
	public void get(List<? extends Male> items) {
		System.out.println("Метод get(List<? extends Male>) c " + items.get(0).getClass().getSimpleName() + " работает");
	}
	
	public void getSuper(List<? super Male> items) {
		System.out.println("Метод get(List<? super Male>) c " + items.get(0).getClass().getSimpleName() + " работает");
	}
	
	public static void main(String[] args) {
		_7SuperGeneric<String> sg = new _7SuperGeneric<>();
		List<Human> h1 = new ArrayList<>();
		List<Male> m1 = new ArrayList<>();
		List<Employee> e1 = new ArrayList<>();
		h1.add(new Human());
		m1.add(new Male());
		e1.add(new Employee());
		//sg.get(h1);
		// нельзя, потому что в методе стоит ограничение по Male, а Human в иерархии выше
		sg.get(m1);
		sg.get(e1);
		System.out.println("Метод get(List<? extends Male>) c Human не работает, потому что он в иерархии выше Male");
		System.out.println("--------------------------");
		sg.getSuper(h1);
		sg.getSuper(m1);
		System.out.println("Метод get(List<? super Male>) c Employee не работает, потому что он в иерархии ниже Male");
		//sg.getSuper(e1);
		// нельзя, потому что в методе стоит ограничение по Male super,
		// т.е. можно сам класс и все что выше в иерархии
	}
}

class Human {
}

class Male extends Human {
}

class Employee extends Male {
}