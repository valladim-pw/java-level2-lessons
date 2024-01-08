package B_Webinars.A4_Calculator.calc2;

public class AsNumbersSumWeb {
	public static String asNumbersSum(int number) {
		return number
		+ divideIntoTerms(number, 1, "");
	}

	public static String divideIntoTerms(int number, int secondTerm, String lastTerm) {
		if (number <= 0){
			return "";
		} else {
			if (secondTerm > number)
				return divideIntoTerms(number, secondTerm - number, lastTerm + number + "+")
		       + divideIntoTerms(number - 1, secondTerm + 1, lastTerm);
			else
				return "=" + lastTerm + number + "+" + secondTerm
		       + divideIntoTerms(secondTerm - 1, 1, lastTerm + number + "+")
		       + divideIntoTerms(number - 1, secondTerm + 1, lastTerm);
		}
	}
	public static void main(String[] args) {
		System.out.println(asNumbersSum(4));
	}
}
