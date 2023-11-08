package HomeWork.recursion.AsNumberSumDrafts;

import java.util.Arrays;

public class AsNumbersSum2 {
	static int num = 0;
	public static String asNumbersSum(int number) {
		num = number;
		int[] nums = new int[number];
		Arrays.fill(nums,  1);
		int len = nums.length;
//		int rem = (number % (number - 1));
//		int num = number/2;
		//String str = "=" + (number - 1) +   "+" + ((number % (number - 1)));
		String str1 = "";
		String str2 = "";
		String str3 = "";
		String str4 = "";
		int a = number - 1;
		int b = number - a;
		if(a == b)
			return str3;
		str3 = asNumbersSum(a);
		if(b == a)
			return str4;
		str4 = asNumbersSum(b);
		int s = 0;
		
		str2 = str2.concat("" + nums[len - 2]).repeat(a);
		int sl = str2.length();
		if(b < number) {
			//b += Integer.parseInt(Character.toString(str2.charAt(sl - 1)));
			//a -= 1;
			str1 = "=" + str4 + "+" +  str3;// + "=" + a + ("").concat(str3).repeat(b);
		} else {
			return str1;
		}
		
		System.out.println("number1: " + number);
		System.out.println("nums1: " + Arrays.toString(nums));
		System.out.println("str1-1: " + str1);
		System.out.println("str1-2: " + str2);
		System.out.println("str1-3: " + str3);
		System.out.println("str1-4: " + str4);
		str1 = asNumbersSum(number - 1);
		//str2 =  asNumbersSum(number - 2);//.substring(1);/*(number % (number - 1))*///.concat(str);// + Arrays.toString(nums);
		//str = asNumbersSum((number - 1) + )
		System.out.println("number2: " + number);
		System.out.println("nums2: " + Arrays.toString(nums));
		System.out.println("str2-1: " + str1);
		System.out.println("str2-2: " + str2);
		System.out.println("str3-2: " + str3);
		return str1;// + str2;
	}
	
	public static void main(String[] args) {
//		String a = "11111";
//		int b = 0;
//		int s = 0;
//		String str = "";
//		for(int i = 0; i < a.length(); i++) {
//			s += Integer.parseInt(Character.toString(a.charAt(a.length() - 1)));
//			str += Character.toString(a.charAt(a.length() - 1));
//			System.out.println(s);
//			System.out.println(str);
//		}
		
		System.out.println(asNumbersSum(5));
		//asNumbersSum(5);
	}
}
