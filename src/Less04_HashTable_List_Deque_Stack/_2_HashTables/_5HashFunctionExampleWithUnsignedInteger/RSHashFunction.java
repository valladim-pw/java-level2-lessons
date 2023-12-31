package Less04_HashTable_List_Deque_Stack._2_HashTables._5HashFunctionExampleWithUnsignedInteger;

public class RSHashFunction {
	static final long UINT_MAX = 4294967295L;
	static long unsignedInt(long num) {
		return num % UINT_MAX;
	}
	
	public long RSHash(String str) {
		long b = 378551;
		long a = 63689;
		long hash = 0;
		
		for(int i = 0; i < str.length(); i++)	{
			System.out.println("i: " + i + "--------------------");
			System.out.println("hash: " + hash);
			System.out.println("a: " + a);
			System.out.println("b: " + b);
			System.out.println("hash * a: " + (hash*a));
			System.out.println("str.charAt(i): " + str.charAt(i) + "->" + (int)str.charAt(i));
			hash = unsignedInt(hash * a + str.charAt(i));
			System.out.println("hash = unsignedInt(hash * a + str.charAt(i)): " + hash);
			a = unsignedInt(a * b);
			System.out.println("a * b: " + a*b);
			System.out.println("a = unsignedInt(a * b): " + a);
		}
		
		return hash;
	}
	public static void main(String[] args) {
		String key = "abc235";
		RSHashFunction rshf = new RSHashFunction();
		System.out.println("-------RSHash Function Value-------\n"
						+ rshf.RSHash(key));
	}
}