package Less07_TypeOfLinks_GarbageCollector;

import java.text.DecimalFormat;

public class Test {
	public static void main(String[] args) {
//		double gb = 1024 * 1024 * 1024;
//		double mem = 125829120;
//		double res = mem / gb;
//		System.out.println(res);
//		DecimalFormat df = new DecimalFormat("###.###");
//		System.out.println(df.format(res));
		
		for (int i = 0; i < 10_000; i++) {
			System.out.println(i + ": " + (i % 5_000));
		}
	}
}
