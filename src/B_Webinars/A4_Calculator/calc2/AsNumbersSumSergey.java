package B_Webinars.A4_Calculator.calc2;

public class AsNumbersSumSergey {
	static String Recursion(int n, String str) {
	   System.out.println(str + n);
	   for (int i=1; i<=n/2; i++){
	       Recursion(n-i, str + i + "+");
	   }
	   return "";
	}
	
	public static void main(String[] args) {
		System.out.println(Recursion(5, ""));
	}
}
