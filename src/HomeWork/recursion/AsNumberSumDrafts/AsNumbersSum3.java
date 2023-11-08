package HomeWork.recursion.AsNumberSumDrafts;

import java.util.Arrays;

public class AsNumbersSum3 {
	public static String asNumbersSum(int num) {
//		num = number;
//		int[] nums = new int[number];
//		Arrays.fill(nums,  1);
		//String result = "";
		String res = "";
		
		int a = num - 1;
		int b = num/2;
		int c = num % 2;
		int d = 0;
		switch(num) {
			//case 0 : return "" + 4;
			case 1 : return "" + num * 2;
			case 2 : return "" + num;
			default :
				if(a % 2 == 0)
					res = a + "+" + asNumbersSum(b);
				else
					res = a  + "+" +  (Integer.valueOf(asNumbersSum(b)) - 1);
				
				System.out.println("num-1: " + num);
				System.out.println("a-1: " + a);
				System.out.println("b-1: " + b);
				System.out.println("c-1: " + c);
				System.out.println("res-1: " + res);
				//System.out.println("res2-1: " + res);
				//System.out.println("sb-1: " + sb.toString());
		}
		if(a == 1) {
			return res;
		}
		res = res + "=" + asNumbersSum(num - 1).concat("+1").repeat(a / 2);
		StringBuilder sb = new StringBuilder();
		sb.append(res);
		sb.insert(0, num + "=");
		String res2 = ("").concat("1").repeat(num);
		res2 = res2.join("+", res2.split(""));
		sb.append("=" + res2);
		
		System.out.println("num-2: " + num);
		System.out.println("a-2: " + a);
		System.out.println("b-2: " + b);
		System.out.println("c-2: " + c);
		System.out.println("res-2: " + res);
		System.out.println("sb2-2: " + sb.toString());
		res = sb.toString();
		sb.delete(sb.length() - (res2.length() + 1), sb.length());
		res = res.replaceAll("=" + a + "=", "=");
		return res;// + str2;
	}
	
	public static void main(String[] args) {
		String a = "2=1+1";
		String b = "";
		int d = 1;
		b = d + ("").concat("+1").repeat(d * 4 - 1);
		//System.out.println(b);
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
		//System.out.println(1%2);
		System.out.println(asNumbersSum(4));
		//asNumbersSum(5);
	}
}
