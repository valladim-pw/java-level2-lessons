package Less01_JVM_JDK_Decompilation_ByteCode._2_JVMParameters;

import java.util.ArrayList;

public class BigHeapSize {
	public static void main(String[] args) {
		ArrayList<Integer> intList = new ArrayList<>();
		for(int i = 0; i < 125_000_000; i++)
			intList.add(i);
		System.out.println("Программа завершена успешно!");
	}
}
