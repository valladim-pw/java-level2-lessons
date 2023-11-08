package Less00_DinamicArrays._3Compare_D_BArrayTimeTest;

import Less00_DinamicArrays._1DinamicIntArray.DIntArray;
import Less00_DinamicArrays._2BlockIntArray.BIntArray;

public class TimeTest {
	static final int count = 100_000;
	
	public static void main(String[] args) {
		DIntArray a1 = new DIntArray();
		BIntArray a2 = new BIntArray(1000, 1000);
		
		long start = System.currentTimeMillis();
		for(int i = 0; i < count; i++)
			a1.add(i);
		long middle = System.currentTimeMillis();
		for(int i = 0; i < count; i++)
			a2.add(i);
		long stop = System.currentTimeMillis();
		
		System.out.println("DIntArray add time: " + (middle - start));
		System.out.println("BIntArray add time: " + (stop - middle));
	}
}
