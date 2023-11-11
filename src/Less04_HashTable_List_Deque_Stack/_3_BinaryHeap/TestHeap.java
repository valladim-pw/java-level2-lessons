package Less04_HashTable_List_Deque_Stack._3_BinaryHeap;

public class TestHeap {
	public static void main(String[] args) {
		BinaryHeapMax<Integer> heap = new BinaryHeapMax<>();
		for (int i = 0; i < 13; i++) {
			heap.add(i);
			System.out.println(heap.toString());
		}
	}
}

//		BinaryHeapMaxWithDel2<Integer> heap2 = new BinaryHeapMaxWithDel2<>();
//		for(int i = 0; i < 16; i++) {
//			heap2.add(i);
//			System.out.println(heap.toString());
//			//heap.print(2, 4);
//		}
//		heap2.print(2, 4);
//		heap2.poll();
//heap.print(2, 4);

//		String str = "БУМАГОПРЯДИЛЬНЫЙ";
//		BinaryHeapMax<String> heapS = new BinaryHeapMax<>();
//		for(int i = 0; i < str.length(); i++) {
//			heapS.add(Character.toString(str.charAt(i)));
//		}