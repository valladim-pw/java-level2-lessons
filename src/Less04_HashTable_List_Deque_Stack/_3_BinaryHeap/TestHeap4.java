package Less04_HashTable_List_Deque_Stack._3_BinaryHeap;

public class TestHeap4 {
	public static void main(String[] args) {
		BinaryHeapMaxWithDel2<Integer> heap =	new BinaryHeapMaxWithDel2<>();
		for(int i = 0; i < 13; i++) {
			heap.add(i);
		}
		heap.poll();
	}
}
