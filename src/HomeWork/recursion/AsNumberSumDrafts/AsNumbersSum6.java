package HomeWork.recursion.AsNumberSumDrafts;

import java.util.*;

//public class AsNumbersSum6 implements Replace{
//	public static LinkedList<Integer> nums = new LinkedList<>();
//	public static LinkedList<Integer> diffs = new LinkedList<>();
//	public static LinkedList<String> strings = new LinkedList<>();
//	public static int interNum = 0;
//
//	public static String asNumbersSum(int num) {
//
//		String res = "";
//		String[] arrStrings;
//		String subStrFirst = "";
//		int len = 0;
//		int size = 0;
//		int numsFirst = 0;
//		int numsLast = 0;
//		int diffsLast = 0;
//		int diffsFirst = 0;
//
//
//		if(nums.isEmpty())
//			nums.add(num);
//		if(nums.getLast() == 1){
//			if (nums.getFirst() == 3)
//				res = "=" + 1 + ("").concat("+1").repeat(nums.getFirst() - 1);
//			return res;
//		}
//		if(diffs.isEmpty()) {
//			num = nums.getLast();
//			numsLast = num - 1;
//			nums.add(numsLast);
//			len = nums.size();
//			numsFirst = nums.getFirst();
//			diffsLast = numsFirst - numsLast;
//			if (len == 2 && interNum == 0)
//				res = num + "=" + numsLast + "+" + diffsLast;
//			else
//				diffs.add(diffsLast);
//		} else {
//			numsFirst = nums.getFirst();
//			numsLast = nums.getLast();
//			diffsFirst = diffs.getFirst();
//			diffsLast = diffs.getLast();
//
//
//			if(diffs.size() == 1 && strings.isEmpty()) {
//				if(diffsLast < numsLast)
//					strings.add("=" + numsLast + "+" +  diffsLast);
//				else if(diffsLast == numsLast && numsLast != 2)
//					strings.add("=" + numsLast + "+" + numsLast);
//				else if(diffsLast > numsLast && numsLast != 2)
//					strings.add("=" + numsLast + "+" + numsLast + "+" + (diffsLast - numsLast));
//				else if(numsLast == 2) {
//					if(numsFirst % 2 == 0)
//						strings.add("=" + 2 + ("").concat("+2").repeat(numsFirst/2 - 1));
//					else
//						strings.add("=" + 2 + ("").concat("+2").repeat(numsFirst/2 - 1) + "+" + 1);
//				}
//				if(diffsLast >= numsLast && numsLast != 2)
//					interNum = diffsLast;
//				System.out.println("interNum-0: " + interNum);
//			}
//			diffs.add(diffsLast - 1);
//
//			System.out.println("diffs-1a: " + diffs);
//			System.out.println("strings-1a: " + strings);
//			if(diffs.getLast() == 1) {
//				if((numsFirst == 4 && diffsLast == numsLast) || diffsLast < numsLast) {
//					if(interNum != 0)
//						strings.add("="  + numsLast + "+" + 1  + ("").concat("+1").repeat(interNum - 1));
//					else
//						strings.add("=" + numsLast + "+" + 1  + ("").concat("+1").repeat(diffsFirst - 1));
//				}
//				subStrFirst =  strings.getFirst().substring(strings.getFirst().lastIndexOf("="));
//				arrStrings = subStrFirst.split("");
//				len = subStrFirst.length();
//				if(!subStrFirst.equals(strings.getLast()))
//					strings.set(0, strings.getFirst() + strings.getLast());
//				if(arrStrings[1].equals("2") && len == numsFirst*2 - 2) {
//					strings.add("=" + 1 + ("").concat("+1").repeat(numsFirst - 1));
//					strings.set(0, strings.getFirst() + strings.getLast());
//				}
//				res = strings.getFirst();
//				System.out.println("diffs-1a: " + diffs);
//				System.out.println("strings:" + strings);
//				System.out.println("res:" + res);
//				if(res.substring(res.lastIndexOf("=")).length() == numsFirst*2 ) {
//					diffs.clear();
//					strings.clear();
//					return res;
//				}	else {
//					//interNum = 0;
//					diffs.clear();
//					strings.clear();
//				}
//			} else  if(diffsFirst < numsLast ) {
//				if(interNum != 0)
//					strings.add("="+ numsLast + "+" + diffs.getLast() + "+" + (numsLast - diffs.getLast()) +
//									(interNum > numsLast ? "+" + (interNum - numsLast) : "" ));
//				else
//					strings.add("=" + numsLast + "+" + (diffsLast - 1) + "+" + (diffsFirst - (diffsLast - 1)));
//				strings.set(0, strings.getFirst() + strings.getLast());
//				Replace.checkAndReplaceLastNums(strings);
//
//			} else if(diffsFirst > numsLast && strings.getFirst().substring(1, 2).equals("2")) {
//				if(strings.size() == 1)
//					strings.add(strings.getFirst());
//				Replace.replaceQueue(strings, numsFirst);
//			} else if(diffsFirst >= numsLast) {
//				//interNum = diffsFirst;
//				System.out.println("interNum: " + interNum);
//				System.out.println("numsLast: " + numsLast);
//				System.out.println("diffsLast: " + diffsLast);
//				if(diffsFirst == numsLast) {
//					strings.add("=" + numsLast + "+" + diffs.getLast() + "+" + (interNum - diffs.getLast()));
//					diffs.removeFirst();
//					strings.set(0, strings.getFirst() + strings.getLast());
//				}
//				Replace.checkAndReplaceLastNums(strings);
//				if(diffsFirst > numsLast) {
//					diffs.clear();
//					diffs.add(numsLast);
//				}
//				System.out.println("nums-3: " + nums);
//				System.out.println("diffs-3: " + diffs);
//				System.out.println("strings-3: " + strings);
//
//			}
//			res = res + asNumbersSum(numsLast);
//		}
//
//		System.out.println("num-1: " + num);
//		System.out.println("numsLast-1: " + numsLast);
//		System.out.println("diffsLast-1: " + diffsLast);
//		System.out.println("res-1: " + res);
//		System.out.println("nums-1: " + nums);
//		System.out.println("diffs-1: " + diffs);
//		System.out.println("strings-1: " + strings);
//
//		res = res + asNumbersSum(numsLast);
//
//		System.out.println("num-2: " + num);
//		System.out.println("numsLast-2: " + numsLast);
//		System.out.println("diffsLast-2: " + diffsLast);
//		System.out.println("res-2: " + res);
//		System.out.println("list-2: " + nums);
//		System.out.println("diffs-2: " + diffs);
//		System.out.println("strings-2: " + strings);
//
//		return res;
//	}
//
//	public static void main(String[] args) {
//	System.out.println(asNumbersSum(5));
//		LinkedList<String> list = new LinkedList<>();
//		String s1 = "";
//		String s2 = "";
//		String s3 = "";
//		String b = "=2+2+2";
//		int num = 6;
//		int fn = 8;
//		int idx = 2;
//		s1 = "3=2+1";
//		s2 = "=2+2=2+1+1+1=2+1+1";
//		list.add(s2);
//		list.add(s1);
//		//System.out.println(s2.substring(1, 2));
//		LinkedList<Integer> nums = new LinkedList<>();
//		LinkedList<Integer> diffs = new LinkedList<>();
//
//		for(int i = 9; i >= 5; i--) {
//			nums.add(i);
//		}
//		diffs.add(5);
//		diffs.add(4);
////		System.out.println(nums);
////		System.out.println(diffs);
////		Object cloneNums = nums.clone();
////		nums.clear();
////		nums.addAll(diffs);
////		System.out.println(nums);
////		System.out.println(diffs);
//
////		nums.clear();
////		nums.addAll((Collection<? extends Integer>) cloneNums);
////		System.out.println(nums);
////		//s1.substring(s1.lastIndexOf("=")).split("");
////		//System.out.println(list.getFirst().replace(list.getFirst().substring(list.getFirst().lastIndexOf("="), list.getFirst().length() - 1), ""));
////		int len = list.getFirst().length();
////		String first = list.getFirst();
////		//System.out.println(list);
////		first = first.substring(0, first.lastIndexOf("="));
////		//System.out.println(first);
////		list.set(0, first);
//		//System.out.println(list);
//		//System.out.println(list.getFirst().replace(list.getFirst().substring(list.getFirst().lastIndexOf("="), len), ""));
//		//System.out.println(s1.substring(s1.lastIndexOf("=")).length());
////		for(int i = 0; i < 3; i++) {
////			StringBuilder sb = new StringBuilder();
////			sb.append(list.getLast());
////			sb.delete(0, 2);
////			list.set(1, sb.toString());
////			sb.append(("").concat("+1").repeat(fn - list.getLast().length()));
////			sb.delete(0, 1);
////			sb.insert(0, "=");
////			list.set(0, list.getFirst() + sb.toString());
////			///idx += 2;
////			System.out.println(list);
////		}
////		for(int i = 0; i < 2; i++) {
////			StringBuilder sb = new StringBuilder();
////			sb.append(list.getLast());
////			sb.delete(0, 2);
////			list.set(1, sb.toString());
////			sb.append(("").concat("+1").repeat(fn - list.getLast().length()));
////			//System.out.println(sb.toString());
////			sb.delete(0, 1);
////			sb.insert(0, "=");
////			list.set(0, list.getFirst() + sb.toString());
////			///idx += 2;
////			//System.out.println(list);
////		}
////
////
////		//System.out.println(list);
////
////		strings.add(s1);
////		strings.add(s2);
////		//strings.add(s3);
////		String l = strings.getLast();
////		System.out.println();
////		boolean test = strings.getFirst().substring(strings.getFirst().length()).equals(strings.getLast());
//		//if(test)
//			//System.out.println("ok");
//		//System.out.println(strings.getFirst().substring(strings.getFirst().length()));
//		//System.out.println(strings);
////		if(strings.getLast().substring(1, 2).equals("2") && (num*2 - strings.getLast().length()) == 2) {
////			strings.add("=" + 1 + ("").concat("+1").repeat(num - 1));
////			strings.set(0, strings.getFirst() + strings.getLast());
////			//System.out.println("ok");
////		}
//		//System.out.println(strings);
//		//System.out.println(b.length());
//	}
//}
