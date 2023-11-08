package HomeWork.generics;

public class Test3 {
	enum CompareResult{
		LESS,
		GREATER,
		EQUAL
	}
	
	static<T extends Comparable> CompareResult compare(T a, T b) {
		if(a.compareTo(b) < 0)
			return CompareResult.LESS;
		else if(a.compareTo(b) > 0)
			return CompareResult.GREATER;
		else
			return CompareResult.EQUAL;

	}
	
	public static void main(String[] args) {
		System.out.println(compare("Петя", "Вася"));
		String s1 = "A";
		String s2 = "B";
		String s3 = "Вася";
		//System.out.println(s1.compareTo(s2));
		
	}
	
}
