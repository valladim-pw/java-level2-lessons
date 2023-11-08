package HomeWork.generics;

import java.util.ArrayList;
import java.util.List;

public class Test1 {
	public static <T> void swap(List<T> list, int id1, int id2) {
		T t = list.get(id1);
		list.set(id1, list.get(id2));
		list.set(id2, t);
	}
	
	public static void main(String[] args) {
		List<Integer> ints = new ArrayList<>();
		ints.add(3);
		ints.add(2);
		ints.add(1);
		System.out.println(ints);
		swap(ints, 0, 1);
		System.out.println(ints);
	}

}
