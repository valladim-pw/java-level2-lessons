package Less07_TypeOfLinks_GarbageCollector._2_TypeOfLinks;

import java.lang.ref.SoftReference;
import java.util.ArrayList;

public class D_SoftRefsFrequency {
	public static void main(String[] args) {
		SoftReference<int[]> srIntArr1 = new SoftReference<>(new int[5_000_000]);
		SoftReference<int[]> srIntArr2 = new SoftReference<>(new int[5_000_000]);
		
		for (int i = 0; i < 5_000_000; i++) srIntArr1.get()[i] = i;
		
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < 105_000_000; i++) {
			if (srIntArr1.get() != null && srIntArr2 == null) {
				System.out.println("Действительно GC пожалел 1");
				break;
			}
			if (srIntArr1.get() == null) {
				System.out.println("Сработал break на i = " + i);
				break;
			}
			list.add(srIntArr1.get()[i % 5_000_000]);
		}
		System.out.println(srIntArr1.get());
		System.out.println(srIntArr2.get());
	}
}
