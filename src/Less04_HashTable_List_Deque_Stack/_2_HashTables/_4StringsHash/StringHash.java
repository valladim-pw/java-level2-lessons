package Less04_HashTable_List_Deque_Stack._2_HashTables._4StringsHash;

import java.util.Objects;

public class StringHash {
	public static int customHashFunction(String str) {
		int hashCode = 0;
		for (int i = 0; i < str.length(); i++) {
//хэш-код получается суммированием значений символов строки
			hashCode += str.charAt(i);
		}
		return hashCode;
	}

	public static void main(String[] args) {
		String[] values = {"Hello","Hello", "My", "Beautiful", "But", "Mad", "mad", "World"};
		
		System.out.println("----String.hashCode()----");
		for(String s : values)
			System.out.println("str: " + s + "-> hash: " + s.hashCode());
		
		System.out.println("----Objects.hash(Object...values)----");
		for(String s : values)
			System.out.println("str: " + s + "-> hash: " + Objects.hash(s));
		
		System.out.println("----customHashFunction(String str)----");
		for(String s : values)
			System.out.println("str: " + s + "-> hash: " + customHashFunction(s));
		
	}
}
