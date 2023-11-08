package Less00_DinamicArrays._5Comare_B_PArrayTimeTest;

import Less00_DinamicArrays._1DinamicIntArray.DIntArray;
import Less00_DinamicArrays._2BlockIntArray.BIntArray;
import Less00_DinamicArrays._4PageIntArray.PIntArray;

public class TimeTest {
	static final int count = 1000_000;
	
	public static void main(String[] args) {
		PIntArray a1 = new PIntArray(1000);
		BIntArray a2 = new BIntArray(1000, 1000);
		
		long start = System.currentTimeMillis();
		for(int i = 0; i < count; i++)
			a1.add(i);
		long middle = System.currentTimeMillis();
		for(int i = 0; i < count; i++)
			a2.add(i);
		long stop = System.currentTimeMillis();
		
		System.out.println("PIntArray add time: " + (middle - start));
		System.out.println("BIntArray add time: " + (stop - middle));
	}
}
