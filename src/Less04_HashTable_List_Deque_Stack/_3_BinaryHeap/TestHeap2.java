package Less04_HashTable_List_Deque_Stack._3_BinaryHeap;

public class TestHeap2 {
	
	public static void main(String[] args) {
		BinaryHeapMax2<Integer> heapI = new BinaryHeapMax2<>();
		for(int i = 0; i < 8; i++) {
			heapI.add(i);
		}
		System.out.println("-------------------------------------");
		String str = "БУМАГОПРЯДИЛЬНЫЙ";
		BinaryHeapMax2<String> heapS = new BinaryHeapMax2<>();
		for(int i = 0; i < str.length(); i++) {
			heapS.add(Character.toString(str.charAt(i)));
		}
	}
}
