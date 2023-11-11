package Less04_HashTable_List_Deque_Stack._3_BinaryHeap;

public class TestHeap3 {
	public static void main(String[] args) {
		BinaryHeapMaxWithDel<Integer> heap = new BinaryHeapMaxWithDel<>();
		for(int i = 0; i < 8; i++) {
			heap.add(i);
		}
		System.out.println(heap.toString());
		heap.poll();
		System.out.println(heap.toString());
		heap.poll();
		System.out.println(heap.toString());
	}
}
