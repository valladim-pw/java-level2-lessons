package Less03_Generics._1_GenericClasses;

import java.util.ArrayList;
import java.util.List;

public class _7SuperGeneric<T> {
	public void get(List<? extends Male> items) {}
	
	public void getSuper(List<? super Male> items) {}
	
	public static void main(String[] args) {
		_7SuperGeneric<String> sg = new _7SuperGeneric<>();
		List<Human> h1 = new ArrayList<>();
		List<Male> m1 = new ArrayList<>();
		List<Employee> e1 = new ArrayList<>();
		//sg.get(h1);
		// нельзя, потому что в методе стоит ограничение по Male, а Human в иерархии выше
		sg.get(m1);
		sg.get(e1);
		
		sg.getSuper(h1);
		sg.getSuper(m1);
		//sg.getSuper(e1);
		// нельзя, потому что в методе стоит ограничение по Male super,
		// т.е. можно сам класс и все что выше в иерархии
	}
}

class Human {}

class Male extends Human {}

class Employee extends Male {}