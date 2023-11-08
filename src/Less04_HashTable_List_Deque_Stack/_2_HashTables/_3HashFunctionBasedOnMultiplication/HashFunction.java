package Less04_HashTable_List_Deque_Stack._2_HashTables._3HashFunctionBasedOnMultiplication;

public class HashFunction {
	private static final double A = 0.6180339887;
	private static int N = 13;
	
	static int hash(int k) {
		double d = A * k;
		System.out.println("k: " + k);
		System.out.println("A: " + A);
		System.out.println("d = A * k: " + d);
		System.out.println("Math.floor(d): " + Math.floor(d));
		System.out.println("d - Math.floor(d): " + (d - Math.floor(d)));
		System.out.println("N * (d - Math.floor(d)): " + (N * (d - Math.floor(d))));
		System.out.println("hash: " + (int)(N * (d - Math.floor(d))));
		return (int)(N * (d - Math.floor(d)));
	}
	
	public static void main(String[] args) {
		for(int i = 25; i < 28; i++) {
			hash(i);
			//System.out.println(hash(i));
		}
	}
}
