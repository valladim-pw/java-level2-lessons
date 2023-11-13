package Less04_HashTable_List_Deque_Stack._3_BinaryHeap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.stream;

public class TestHeap8 {
	
	public static void main(String[] args) {
		BinaryHeapFull2<Integer> heap = new BinaryHeapFull2<>(BinaryHeapFull2.Type.MIN_HEAP);
		for(int i = 10000; i >= 9990; i--) {
			heap.add(i);
		}
		
		System.out.println("-------------------------------------");
		String str = "БУМАГОПРЯДИЛЬНЫЙ";
		String[] arrStr = str.split("");
		BinaryHeapFull2<String> heapS = new BinaryHeapFull2<>();
		heapS.from(BinaryHeapFull2.Type.MIN_HEAP, arrStr);
		
		System.out.println("-------------------------------------");
		String str2 = "Replaces each substring of this string that matches the given regular expression";
		String[] arrStr2 = str2.split(" ");
		heapS.from(BinaryHeapFull2.Type.MAX_HEAP, arrStr2);
		
		System.out.println("-------------------------------------");
		String str3 = "Общая длина 90-миллиардного мегаинфраструктурного проекта Дели – Мумбай составляет 1483 км";
		String[] arrStr3 = str3.split(" ");
		heapS.from(BinaryHeapFull2.Type.MIN_HEAP, arrStr3);
		
	}
}
