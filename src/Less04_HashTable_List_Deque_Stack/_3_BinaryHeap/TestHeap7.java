package Less04_HashTable_List_Deque_Stack._3_BinaryHeap;

public class TestHeap7 {
	
	public static void main(String[] args) {
		BinaryHeapFull<Integer> heap = new BinaryHeapFull<>(BinaryHeapFull.Type.MIN_HEAP);
		for(int i = 15; i >= 2; i--) {
			heap.add(i);
			System.out.println(heap.toString());
		}
	}
}
