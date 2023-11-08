package HomeWork.recursion.AsNumberSumDrafts;

import java.util.*;

//public class AsNumbersSum8{
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
//					res = num + "=" + numsLast + "+" + diffsLast;
//			else
//					diffs.add(diffsLast);
//		} else {
//			numsFirst = nums.getFirst();
//			numsLast = nums.getLast();
//			diffsFirst = diffs.getFirst();
//			diffsLast = diffs.getLast();
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
//					strings.add("=" + 2 + ("").concat("+2").repeat(numsFirst/2 - 1) + "+" + 1);
//				}
//				if(diffsLast >= numsLast && numsLast != 2)
//					interNum = diffsLast;
//			}
//			diffs.add(diffsLast - 1);
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
//						strings.add("=" + 1 + ("").concat("+1").repeat(numsFirst - 1));
//						strings.set(0, strings.getFirst() + strings.getLast());
//				}
//				res = strings.getFirst();
//
//				if(res.substring(res.lastIndexOf("=")).length() == numsFirst*2 ) {
//					diffs.clear();
//					strings.clear();
//					return res;
//				}	else {
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
//				if(diffsFirst == numsLast) {
//					strings.add("=" + numsLast + "+" + diffs.getLast() +
//									(interNum % 2 == 0 ? "+" + (interNum - diffs.getLast()) : "+" + (interNum - numsLast) + "+" + 1));
//					diffs.removeFirst();
//					strings.set(0, strings.getFirst() + strings.getLast());
//				}
//				Replace.checkAndReplaceLastNums(strings);
//				if(diffsFirst > numsLast) {
//					diffs.clear();
//					diffs.add(numsLast);
//				}
//			}
//			res = res + asNumbersSum(numsLast);
//		}
//
//		res = res + asNumbersSum(numsLast);
//
//		return res;
//	}
//
//	public static void main(String[] args) {
//		System.out.println(asNumbersSum(9));
//	}
//}
