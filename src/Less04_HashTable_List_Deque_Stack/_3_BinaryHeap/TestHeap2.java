package Less04_HashTable_List_Deque_Stack._3_BinaryHeap;

public class TestHeap2 {
	
	public static void main(String[] args) {
		/*Вывод бинарной кучи после построения*/
		BinaryHeapMax2<Integer> heapI = new BinaryHeapMax2<>();
		for(int i = 0; i < 13; i++) {
			heapI.add(i);
		}
		heapI.printHeap(3);
		
		String str = "БУМАГОПРЯДИЛЬНЫЙ";
		BinaryHeapMax2<String> heapS = new BinaryHeapMax2<>();
		for(int i = 0; i < str.length(); i++) {
			heapS.add(Character.toString(str.charAt(i)));
		}
		heapI.printHeap(3);
		
		/*Вывод этапов построения бинарной кучи*/
			BinaryHeapMax3<Integer> heapI2 = new BinaryHeapMax3<>();
			for(int i = 0; i < 13; i++) {
				heapI2.add(i);
			}
			
			String str2 = "БУМАГОПРЯДИЛЬНЫЙ";
			BinaryHeapMax3<String> heapS2 = new BinaryHeapMax3<>();
			for(int i = 0; i < str2.length(); i++) {
				heapS2.add(Character.toString(str.charAt(i)));
			}
	}
}
