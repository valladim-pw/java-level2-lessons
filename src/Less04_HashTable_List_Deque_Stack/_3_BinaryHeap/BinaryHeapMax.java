package Less04_HashTable_List_Deque_Stack._3_BinaryHeap;

import java.util.*;

public class BinaryHeapMax<T extends Comparable<T>> {
	
	private List<T> list;
	
	BinaryHeapMax() {
		list = new ArrayList<>();
	}
	
	public int size() {
		return list.size();
	}
	
	public void add(T item) {
		list.add(item);
		shiftUp();
	}
	
	private void swap(int i, int j) {
		T item = list.get(i);
		list.set(i, list.get(j));
		list.set(j, item);
	}
	
	public void shiftUp() {
		int i = list.size() - 1;
		int parent = (i - 1) / 2;
		while(i > 0 && list.get(parent).compareTo(list.get(i)) < 0) {
			swap(i, parent);
			i = parent;
			parent = (i - 1) / 2;
		}
	}
	
	@Override
	public String toString() {
		return list.toString();
	}
}
