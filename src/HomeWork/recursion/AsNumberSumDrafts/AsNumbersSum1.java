package HomeWork.recursion.AsNumberSumDrafts;

import java.util.Arrays;

public class AsNumbersSum1 {
	
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
		String str3 = "+1";
		int b = 2;
		int a = number - b;
		str2 = str2.concat("+" + nums[len - 1]).repeat(number - 1);
		if(a >= b) {
			if(number % 2 != 0) {
				if(a % 2 != 0)
					str1 = "=" + a + "+" +  b + "=" + a + ("").concat(str3).repeat(b);
			  else
				  str1 = "=" + a + "+" + b + "+" + 1 + "=" + a + ("").concat(str3).repeat(b + 1);
			}
		} else {
			return str1;
		}
		
		System.out.println("number1: " + number);
		System.out.println("nums1: " + Arrays.toString(nums));
		System.out.println("str1-1: " + str1);
		System.out.println("str1-2: " + str2);
		System.out.println("str1-3: " + str3);
		str1 = str1 + asNumbersSum(number - 1);
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
		
		System.out.println(asNumbersSum(5));
		//asNumbersSum(5);
	}
}
