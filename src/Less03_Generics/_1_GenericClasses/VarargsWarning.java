package Less03_Generics._1_GenericClasses;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class VarargsWarning {
	public static <T> void addAll(Collection<T> coll, T... args) {
		for(T t : args) {
			coll.add(t);
		}
	}
	
	public static void main(String[] args) {
		List<String> listA = new ArrayList<String>();
		List<Integer> listB = new ArrayList<Integer>();
		addAll(listA, "Hello", "World");
		System.out.println(listA);
		addAll(listB, 23, 35);
		System.out.println(listB);
		List<List> listC = new ArrayList<>();
		addAll(listC, listA, listB);
		System.out.println(listC);
	}
}
