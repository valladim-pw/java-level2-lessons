package Less03_Generics._1_GenericClasses;

import java.util.Arrays;
import java.util.List;

public class _8Test<T> {
	
	public static<T> void reverse(T[] arr, int value1, int value2) {
		T tmp = arr[value1];
		arr[value1] = arr[value2];
		arr[value2] = tmp;
	}
	
	public static<T> List<T> arrToList(T...args) {
		return Arrays.asList(args);
	}
	
	public static void main(String[] args) {
		Integer[] arr1 = new Integer[]{1, 2, 4, 3};
		System.out.println(Arrays.toString(arr1));
		reverse(arr1,arr1.length - 1, arr1.length - 2);
		System.out.println(Arrays.toString(arr1));
		String[] arr2 = new String[]{"Hello", "My", "Darling", "!"};
		System.out.println(Arrays.toString(arr2));
		reverse(arr2,arr2.length - 1, 0);
		System.out.println(Arrays.toString(arr2));
		List<Integer> ints1 = arrToList(2, 3, 4, 5);
		System.out.println(ints1);
		List<Integer> ints2 = _8Test.<Integer>arrToList(2, 3, 4, 5);
		System.out.println(ints2);
		List<Double> dbls1 = arrToList(2.0, 3.0, 4.0, 5.0);
		System.out.println(dbls1);
		List<Double> dbls2 = arrToList(2.0, 3.0, 4.0, 5.0);
		System.out.println(dbls2);
		List<String> strs1 = arrToList("Hello", "My", "Darling", "!");
		System.out.println(strs1);
		List<String> strs2 = _8Test.<String>arrToList("Hello", "My", "Darling", "!");
		System.out.println(strs2);
	}
}
