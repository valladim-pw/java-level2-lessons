package Less04_HashTable_List_Deque_Stack._1_List_Deque_Stack._4FactorialFunction;

public class Factorial {
	static int factorial(int n) {
		if(n == 1)
			return n;
		else
			return n * factorial(n - 1);
	}
	
	public static void main(String[] args) {
		System.out.println(Factorial.factorial(3));
	}
}
