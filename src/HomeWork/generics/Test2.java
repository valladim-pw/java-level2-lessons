package HomeWork.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test2 {
	static<T> ArrayList<T> from(T[] arr) {
		ArrayList<T> list = new ArrayList<>();
		for(T t : arr) {
			list.add(t);
		}
		return list;
	}
	
	public static void main(String[] args) {
		Integer[] ints = {2, 3, 4, 5};
		System.out.println(from(ints));
	}
}
