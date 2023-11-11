package Less04_HashTable_List_Deque_Stack._3_BinaryHeap;

public class TestHeap6 {
	public static void main(String[] args) {
		BinaryHeapMaxSort2<Integer> heap =	new BinaryHeapMaxSort2<>();
		for(int i = 0; i < 7; i++) {
			heap.add(i);
		}
		heap.sort();
	}
}
