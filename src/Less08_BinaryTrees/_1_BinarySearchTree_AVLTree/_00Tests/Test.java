package Less08_BinaryTrees._1_BinarySearchTree_AVLTree._00Tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Test {
	
	static void printRow(int space, List<String> list) {
		LinkedList<String> valueList = new LinkedList<>(list);
		int doubleSpace = space * 2;
		while (!valueList.isEmpty()) {
			String value = valueList.poll();
			int valueLength = value.length();
			int spaceFirst = space - (valueLength > 1 ? valueLength / 2 : valueLength);
			
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < doubleSpace; i++) {
				if (i == spaceFirst)
					sb.append(value.equals("n") ? "_" : value);
				else
					sb.append(" ");
			}
			System.out.print(sb.toString());
		}
		System.out.println();
	}
	
	static List<String> adjustValues(int maxValueLength, List<String> values) {
		int startSpace = 0;
		int endSpace = 0;
		String start = "";
		String end = "";
		int lengthDiff = 0;
		String value = "";
		String newValue = "";
		List<String> changeValues = new ArrayList<>();
		for (int i = 0; i < values.size(); i++) {
			value = values.get(i);
			lengthDiff = maxValueLength - value.length();
			startSpace = (int) Math.floor(((lengthDiff) * 1.0) / 2);
			endSpace = (int) Math.ceil(((lengthDiff) * 1.0) / 2);
			start = ("*").repeat(startSpace);
			end = ("*").repeat(endSpace);
			if (lengthDiff > 0) {
				newValue = start + value + end;
				changeValues.add(newValue);
			} else
				changeValues.add(value);
		}
		return changeValues;
	}
	
	public static void main(String[] args) {
		List<String> list = List.of(new String[]{"15", "8", "18", "6", "9", "16", "19", "2", "7", "_", "10", "_", "17", "_", "20", "1", "4", "_", "_", "_", "_", "_", "13", "_", "_", "_", "_", "_", "_", "_", "_", "-2100", "_", "3", "_", "_", "_", "_", "_", "_", "_", "_", "_", "_", "_", "11", "14", "_", "_", "_", "_", "_", "_", "_", "_", "_", "_", "_", "_", "_", "_", "_", "_"});
		//System.out.println(list);
		String val = "-2100";
		List<String> list2 = adjustValues(val.length(), list);
		//System.out.println(list2);
		String str = "--_----_----3----_----_----_----_----_----_----_----_----_----_----_---11---14----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_----_--";
		String str2 = "||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||";
		//System.out.println(str2.length());
		List<String> list3 = new ArrayList<>(list);
		int level = 32;
		int index = list.size() - level;
		//System.out.println(list3);
		//System.out.println(index);
		list3.add(index, "&");
		//System.out.println(list3);
		list3.remove(index);
		//System.out.println(list3);
		//System.out.println(list3.indexOf("-2100"));
		//System.out.println("\t\t\t* Здесь показан класс <i>TreeTest</i>, который я использую для тестирования работы методов класса <i>Bin".length());
		String ch = "❯";
		int i = ch.codePointAt(0);
		System.out.println(i);
		char[] arr = ch.toCharArray();
		System.out.println(Arrays.toString(arr));
		int maxValueLength = 5;
		int startValueHalf = (int) Math.floor(((maxValueLength) * 1.0) / 2);
		int endValueHalf = (int) Math.ceil(((maxValueLength) * 1.0) / 2);
		System.out.println("floor: " + startValueHalf);
		System.out.println("ceil: " + endValueHalf);
	}
}
//5, 2, 8, 1, 4, 6, 9, n, n, 3, n, n, 7, n, 10, n, n, n, n, n, n, n, n, n, n, n, n, n, n, n, 22
//[15, 8, 18, 6, 9, 16, 19, 2, 7, _, 10, _, 17, _, 20, 1, 4, _, _, _, _, _, 13, _, _, _, _, _, _, _, _, -2100, _, 3, _, _, _, _, _, _, _, _, _, _, _, 11, 14, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]

//		for (int i = 1; i < 20; i++) {
//			int tail = 20 % i;
//			System.out.println(tail + "");
//		}
//		for (double i = 0; i < 10; i++) {
//			int pow  = (int)Math.pow(2, i);
//			System.out.println(2 + "^" + (int)i + " = " + pow);
//		}
//int space = 16;
//		List<String> list1 = List.of("5");
//		printRow(32, list1);
//		//System.out.println();
//		List<String> list2 = List.of("2", "8");
//		printRow(16, list2);
//		//System.out.println();
//		List<String> list3 = List.of("1", "4", "6", "9");
//		printRow(8, list3);
//		//System.out.println();
//		List<String> list4 = List.of("n", "n", "3", "n","n", "7", "n", "10");
//		printRow(4, list4);
//		//System.out.println();
//		List<String> list5 = List.of("n", "n", "n", "n","n", "n", "n", "n","n", "n", "n", "n","n", "n", "n", "22");
//		printRow(2, list5);
//String newValue;
//		String max = "-210000";
//		int maxValueLength = max.length();
//		int valueLength = value.length();
//		int lengthDiff = maxValueLength - valueLength;
//		int startSpace = (int) Math.floor(((lengthDiff) * 1.0) / 2);
//		int endSpace = (int) Math.ceil(((lengthDiff) * 1.0) / 2);
//		System.out.println("f: " + startSpace + ", s: " + endSpace);
//		String start = ("*").repeat(startSpace);
//		String end = ("*").repeat(endSpace);
//		if (lengthDiff > 0) {
//			newValue = start + value + end;
//			System.out.println(newValue);
//		} else
//			System.out.println(value);