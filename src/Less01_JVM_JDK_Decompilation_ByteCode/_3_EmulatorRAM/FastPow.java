package Less01_JVM_JDK_Decompilation_ByteCode._3_EmulatorRAM;

import java.math.BigDecimal;
import java.math.BigInteger;

public class FastPow {
	public static BigDecimal fastPow(BigDecimal num, int pow) {
		BigDecimal z = num;
		System.out.println("z = num: " + z);
		BigDecimal res = BigDecimal.ONE;
		System.out.println("BigDecima.ONE: " + res);
		int compare;
		String str = Integer.toBinaryString(pow);
		System.out.println("toBinaryString(pow): " + str);
		for(int i = 0; i < str.length(); i++){
			compare = pow & 1;
			if(compare == 1) {
				res = res.multiply(z);
			}
			z = z.pow(2);
			pow >>= 1;
			System.out.println("i: " + i );
			System.out.println("res.multiply(z): " + res );
			System.out.println("z.pow(2): " + z );
			System.out.println("pow >>= 1: " + Integer.toBinaryString(pow) );
		}
		return res;
	}
	//Variant 2
//public static BigDecimal fastPow(BigDecimal num,int pow){
//	BigDecimal z = num;
//	BigDecimal res = BigDecimal.ONE;
//	while(pow > 0){
//		int remainder = pow % 2;
//		if(remainder == 1){
//			res = res.multiply(z);
//			z = z.pow(2);
//		} else {
//			z = z.pow(2);
//		}
//		pow /= 2;
//	}
//	return res;
//}
	public static void main(String[] args) {
		System.out.println(fastPow(new BigDecimal("21"), 13));
		//21^13 : 154 472 377 739 119 461
		//21^15 : 68 122 318 582 951 682 301
		//15^10 : 576 650 390 625
		//7^20 : 79 792 266 297 612 001
		//54^8 : 72 301 961 339 136
		//8.23^9 : 173 220 192,505318905457564663
		
	}
}
