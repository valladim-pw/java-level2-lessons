package Less04_HashTable_List_Deque_Stack._3_BinaryHeap;

public class TestHeap1 {
	
	public static void main(String[] args) {
		BinaryHeapMax2<Integer> heapI = new BinaryHeapMax2<>();
//		for(int i = 0; i < 16; i++) {
//			heapI.add(i);
//		}
		String str = "БУМАГОПРЯДИЛЬНЫЙ";
		BinaryHeapMax2<String> heapS = new BinaryHeapMax2<>();
		for(int i = 0; i < str.length(); i++) {
			heapS.add(Character.toString(str.charAt(i)));
		}
//		long t1 = System.currentTimeMillis();
//		heapI.pause(8_000_000);
//		long t2 = System.currentTimeMillis();
//		System.out.println(t2 - t1);
	}
}
