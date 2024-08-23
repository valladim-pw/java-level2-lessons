package Less07_TypeOfLinks_GarbageCollector._2_TypeOfLinks;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

public class A_CallGC {
	static void printMemInfo(String info) {
		System.out.println(String.format("%1$tI:%1$tM:%1$tS.%1$tN", new Date()));
		System.out.println(info);
		double gb = 1024 * 1024 * 1024;
		Runtime runtime = Runtime.getRuntime();
		DecimalFormat df = new DecimalFormat("###.###");
		System.out.println("всего: " + runtime.totalMemory() +
         "(" + df.format((double)runtime.totalMemory()/gb) + " GB)");
		System.out.println("максимально: " + runtime.maxMemory() +
         "(" + df.format((double)runtime.maxMemory()/gb) + " GB)");
		System.out.println("свободно: " + runtime.freeMemory() +
          "(" + df.format((double)runtime.freeMemory()/gb) + " GB)");
	}
	
	public static void main(String[] args) {
		printMemInfo("Памяти на старте:");
		ArrayList<Integer> list = new ArrayList<>();
		for (int k = 0; k < 3; k++) {
			System.out.println(k + ")-----------------------------");
			for (int i = 0; i < 50_000_000; i++) list.add(k);
			printMemInfo("После создания объектов:");
			
			list.clear();
			System.gc();
			printMemInfo("После сборки мусора:");
		}
	}
}
