package HomeWork.recursion;

import com.sun.jdi.IntegerValue;

import java.util.*;
import java.util.stream.Collectors;

public class Test {
	public static void main(String[] args) {
//		int num = 5;
//		int i = 0;
//		int a = num - 1;
//		int b = num / 2;
//		int c = num % 2;
		//int x = 1;
		//int y = x * 2;
		//int z = x * 3;
//		String s1 = "" + 1;
//		String s2 = "" + 2;
//		String s3 = "" + 1 + "" + 1;
//		String s4 = "" + 3;
//		String s5 = "" + 2 + "" + 1;
//		String s6 = "" + 1 + "" + 1 + "" + 1;
//		StringBuilder sb1 = new StringBuilder(num);
//		//StringBuilder sb2 = sb1.reverse();
//		while( i < num) {
//			if(b != 0) {
//				sb1.append("=" + a + "+" + x);
//				if(a > (a - x))
//					sb1.append("=" + z + "+" + y);
//				if(a == b )
//					sb1.append("=" + a + "+" + b);
//			}
//			num--;
//		}
//		int res = 2 ^ 5 - 1;
//		//System.out.println(res);
		
		//sb.append("<" + ring + "> ");
		//ring = sb.toString();
		//System.out.println(ring);
		//sb.append(rod + rod + rod);
		//System.out.println(sb.toString());
		//sb.replace(0, ring.length(), ring);
		//System.out.println(sb.toString());
		//sb.replace(0, rod.length(), rod);
		//sb.replace(ring.length(), ring.length() * 2, ring);
		//System.out.println(sb.toString());
		//sb.replace(ring.length() * 2, ring.length() * 3, ring);
		//System.out.println(sb.toString());
		//System.out.println("0:" + 0 + " 1:" + ring.length() + " 2:" + ring.length()* 2);
//		char ch1 = sb.charAt(0);
//		char ch2 = sb.charAt(6);
//		char ch3 = sb.charAt(12);
		//System.out.println("0:" + ch1 + " 1:" + ch2 + " 2:" + ch3);
		
//		TreeSet<String> ts = new TreeSet<>();
//		ts.add(s1);
//		ts.add(s2);
//		ts.add(s3);
//		ts.add(s4);
		//System.out.println(ts);
		//List<String> f = ts.stream().filter(x -> x.substring(12, 13).equals("-")).collect(Collectors.toList());
		//System.out.println(f.get(f.size() - 1));
		int n = 75;
		int size = 3;
		String rod = "--I-- ";
		String ring = "<" + String.valueOf((double)n / 1000).substring(2) + "> ";
		System.out.println(ring);
		System.out.println(rod);
		String s1 = rod + rod + rod;
		String s2 = ring + rod + rod;
		String s3 = ring + rod + ring;
		String s4 = ring + ring + ring;
		//StringBuilder sb = new StringBuilder();
		LinkedList<String> list = new LinkedList<>();
		for(int i = 0; i < size; i++) {
			list.add(s1);
		}
		System.out.println(list);
		list.forEach(System.out::println);
		for(int i = 0; i < list.size(); i++) {
			StringBuilder sb = new StringBuilder();
			String str = list.get(i);
			if(str.substring(0, 1).equals("-")) {
				sb.append(str);
				sb.replace(0, ring.length(), ring);
				list.set(i, sb.toString());
			}
		}
		System.out.println(list);
		list.forEach(System.out::println);
	}
}
