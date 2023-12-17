package HomeWork.basetypes;

import java.util.*;

public class Test2 {
	public static String remainder(int key, int size, int num) {
		String f1 = "func1: " + key + " % " + size + " = " + (key % size) + "\n";
		String f2 = "func2: " + num + " - " + key + " % " + num +  " = " + (num - key % num) + "\n";
		return f1 + f2;
	}
	public static String hash1(int key, int N) {
		double A = 0.6180339887;
		double d = A * key;
		int result = (int) (N * (d - Math.floor(d)));
		String s1 = "d = A * key:\n";
		String s2 = A + " * " + key + " = " + d + "\n";
		String s3 = "d - Math.floor(d):\n";
		String s4 = d + " - " + Math.floor(d) + " = " + (d - (Math.floor(d))) + "\n";
		String s5 = "N * (d - Math.floor(d)):\n";
		String s6 = N + " * " + (d - Math.floor(d)) + " = " + result + "\n";
		return s1 + s2 + s3 + s4 + s5 + s6;
	}
	public static String hash2(int key, int N) {
		double A = 0.6180339887;
		double d = A * key;
		int result = (int) (N * (d - Math.floor(d)));
		String s1 = "key: " + key + ", N: " + N + "\n" ;
		String s2 = "hash: " + result + "\n";
		return s1 + s2;
	}
	public static int hash(int key, int N) {
			double A = 0.6180339887;
			double d = A * key;
			int result = (int) (N * (d - Math.floor(d)));
			return result;
		}
	
	public static void main(String[] args) {
		int size = 11;
		int num  = 5;
		for(int key = 0; key < 33; key++) {
			System.out.println(remainder(key, size, num));
		}
		List<Integer> list = new ArrayList<>();
		for(int key = 0; key < 11; key++) {
			//System.out.println(hash1(key, size));
			//System.out.println(hash2(key, size));
			list.add(hash(key, size));
		}
		Collections.sort(list);
		System.out.println(list);
	}
}
/* Prime numbers
2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101,
 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199,
 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307
 */
