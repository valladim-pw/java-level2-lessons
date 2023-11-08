package HomeWork.recursion.AsNumberSumDrafts;

import java.util.*;
//import java.util.List;

public class AsNumbersSum5 {
	public static LinkedList<Integer> list = new LinkedList<>();
	public static LinkedList<Integer> rec = new LinkedList<>();
	public static List<Integer> sum() {
		return list;
	}
	public static String asNumbersSum(int num) {

		String res = "";
		String res2 = "";
		int len = 0;
		//int b = num/2;
		//int c = num % 2;
		list.add(num);
		len = list.size();
		int a = num - 1;
		int f = list.getFirst();
		int l = list.getLast();
		int b = f - a;
		int b1 = b - 1;
		int c = f - l;
//		if(list.size() > 1)
//			rec.add(c);
		if(l == 1 ) {
			//res = list.get(len - 1) + ("").concat("+1").repeat(list.get(0) - 1);
			return res;
		} else {
			System.out.println("num-1: " + num);
			System.out.println("a-1: " + a);
			System.out.println("b-1: " + b);
			System.out.println("res-1: " + res);
			System.out.println("list-1: " + list);
			System.out.println("rec-1: " + rec);
			if(len == 1)
				res = num + "=";
			else if(len == 2)
				res += a + "+" + c;
			else if(a == 1)
				res = 1 + ("").concat("+1").repeat(f - 1);
			else {
				//if(b )
				rec.add(b);
					if(b <= a) {
						res2 = String.valueOf(b);
						res = a + "+" + res2;
						switch(b) {
							case 2 : res += "=" + a + "+" + b1 + ("").concat("+1").repeat(b - 1);
								break;
							case 3: res += "=" + a + "+" + b1 + "+" + 1;
								break;
							default:
								//res = a + "+" + b/2;
								return res;
						}
					}	else {
						res = a + "+" +  (f % 2 == 0 ? (b - 2) + "+" + 2 : (b - 1) + "+" + 1 );
						switch(b) {
							case 3: res += "=" + a + "+" + (b - 2) + ("").concat("+1").repeat(b - 1);;
								break;
							default:
								//res += a + "+" + res +  asNumbersSum(b - 1);
								return res;
						}
					}
				
			}
//			System.out.println("num-1: " + num);
//			System.out.println("a-1: " + a);
//			System.out.println("b-1: " + b);
//			System.out.println("res-1: " + res);
//			System.out.println("list-1: " + list);
//			System.out.println("rec-1: " + rec);
			
		}
		res = res + "=" + asNumbersSum(a) + asNumbersSum(b - 1);// + asNumbersSum(b - 1);
		System.out.println("num-2: " + num);
		System.out.println("a-2: " + a);
		System.out.println("b-2: " + b);
		//System.out.println("c-2: " + c);
		System.out.println("res-2: " + res);
		System.out.println("list-2: " + list);
		
		return res;
	}
	
	public static void main(String[] args) {
		System.out.println(asNumbersSum(6));
		String a = "222";
		//join​(CharSequence delimiter, CharSequence... elements)
		Integer[] arr = {1, 2, 3};
		LinkedList<Integer> list = new LinkedList<>();
		list.addAll(List.of(arr));
		list.removeLast();
		System.out.println(list);
		a = a.join("+", a.split(""));
		//System.out.println(a);
//		String b = "";
//		String c = "";
//		int s = 0;
//		String str = "";
//		for(int i = 0; i < a.length(); i++) {
//			s += Integer.parseInt(Character.toString(a.charAt(a.length() - 1)));
//			str += Character.toString(a.charAt(a.length() - 1));
//			System.out.println(s);
//			System.out.println(str);
//		}
//		b = a.substring(0, 4);
//		c = a.substring(4);
		//System.out.println(a.substring(a.length() - 1));
		//System.out.println(1/2 + "\n" + 1%2);
	
		//asNumbersSum(5);
	}
}
