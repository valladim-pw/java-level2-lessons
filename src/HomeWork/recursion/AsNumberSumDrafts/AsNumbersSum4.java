package HomeWork.recursion.AsNumberSumDrafts;

import java.util.*;

public class AsNumbersSum4 {
	public static List<Integer> list = new ArrayList<>();
	public static List<Integer> sum() {
		return list;
	}
	public static String asNumbersSum(int num) {

		String res = "";
		String res2 = "";
		int len = 0;
		
		
		//int b = num/2;
		int c = num % 2;
		list.add(num);
		len = list.size();
		int a = num - 1;
		int b = list.get(0) - a;
		switch(num) {
			case 1 : return "1+1";
			case 2 : res = ("" + num + "+" + b) + ("").concat("+" + b).repeat(b) + "=";
			return res;
			
			default :
				if(a % 2 == 0)
					res += a + "+" + (b + c);
				else
					res += a  + "+" + ( b - c);
				
				System.out.println("num-1: " + num);
				System.out.println("a-1: " + a);
				System.out.println("b-1: " + b);
				System.out.println("c-1: " + c);
				System.out.println("res-1: " + res);
				System.out.println("res2-1: " + res2);
				System.out.println("resLen-1: " + res.length());
		}
		if(a == 0)
			return res;
		res = res + "=" + asNumbersSum(num - 1) + "+" + asNumbersSum(num/2);//.concat("+1").repeat((a / 2) * 2);
		res = res.replaceAll("=" + a + "=", "=");
		res = res.replace("=+", "=");
//		int s = Integer.valueOf(num);
//		res2 = ("").concat("1").repeat(num - 1);
//		res2 = res2.join("+", res2.split(""));
//		res = res.concat(res2);
		//StringBuilder sb = new StringBuilder(res);
		//sb.append(res2);
		System.out.println("num-2: " + num);
		System.out.println("a-2: " + a);
		System.out.println("b-2: " + b);
		System.out.println("c-2: " + c);
		System.out.println("res-2: " + res);
		System.out.println("res2-2: " + res2);
		System.out.println("resLen-2: " + res.length());
		//System.out.println("sb2-2: " + sb.toString());
		//System.out.println("sbLen-2: " + sb.length());
		
		return res;
	}
	
	public static void main(String[] args) {
		String a = "" + 1 + "+" + 1;
		//join​(CharSequence delimiter, CharSequence... elements)
		
		a = a.join("+", a.split(""));
		byte[] b = a.getBytes();
		System.out.println(Arrays.toString(b));
		Integer i = 1 << 1;
		System.out.println(Integer.toBinaryString(i));
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
		System.out.println(asNumbersSum(5));
		//asNumbersSum(5);
	}
}
