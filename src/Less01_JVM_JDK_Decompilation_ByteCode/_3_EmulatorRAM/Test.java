package Less01_JVM_JDK_Decompilation_ByteCode._3_EmulatorRAM;

public class Test {
	public Test() {};
//	public static int factorial(int num) {
//
//	}
	public static void main(String[] args) {
//		for(int i = 0; i < 1000; i++) {
//			System.out.println( i + ": " + (i ^ 2));
//		}
		//int x = 0b00000000_00000000_00000000_00000101;
		//int res = x & 1;
		//System.out.println(Integer.toBinaryString(13));
		//System.out.println(String.format("%32s", Integer.toBinaryString(x).replace("", "0")));
		int f = 1;
		for(int i = 1; i <= 8; i++) {
			f *= i;
		}
		System.out.println(f);
	}
}
