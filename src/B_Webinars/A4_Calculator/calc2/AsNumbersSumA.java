package B_Webinars.A4_Calculator.calc2;

public class AsNumbersSumA {
	public static String asNumbersSum(int number) {
    return numbersSum(number, number,"");
  }

  private static String numbersSum(int usedNum, int sum, String prefix) {
    if (sum == 0)
    	return prefix;
    int d;
    if (usedNum < sum) {
      d = usedNum;
    } else d = sum;
    String s = numbersSum(d,sum - d, (prefix.isEmpty())? ""+ d : prefix +"+"+ d);
	  //System.out.println(s);
    if (d > 1) {
	    System.out.println(s);
      s = s +"="+ numbersSum(d - 1, sum, prefix);
    }
    return s;
  }
	public static void main(String[] args) {
		System.out.println(asNumbersSum(5));
	}
}
