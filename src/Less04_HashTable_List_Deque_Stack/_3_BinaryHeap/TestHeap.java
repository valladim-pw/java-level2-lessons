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

