package Less07_TypeOfLinks_GarbageCollector;

import java.text.DecimalFormat;

public class Test {
	public static void main(String[] args) {
		int sum = 0;
		int number = 1;
		while (number <= 100) {
			if (number % 3 == 0) {
				number++;
				continue;
			}
			sum += number;
			number++;
			System.out.println(sum);
		}
	}
}
