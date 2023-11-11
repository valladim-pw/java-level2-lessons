package Less04_HashTable_List_Deque_Stack._3_BinaryHeap;

public class TestHeap5 {
	public static void main(String[] args) {
		BinaryHeapMaxSort<Integer> heap =	new BinaryHeapMaxSort<>();
		for(int i = 0; i < 13; i++) {
			heap.add(i);
		}
		System.out.println(heap.sort());
	}
}
