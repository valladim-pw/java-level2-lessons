package Less04_HashTable_List_Deque_Stack._3_BinaryHeap;

import java.util.*;

public class BinaryHeapMaxWithDel<T extends Comparable<T>> {
	public enum Type {
		MAX_HEAP,
		MIN_HEAP
	};
	
	private List<T> list;
	private Type type;
	
	BinaryHeapMaxWithDel() {
		list = new ArrayList<>();
		type = Type.MAX_HEAP;
	}
	
	BinaryHeapMaxWithDel(Type type) {
		this();
		this.type = type;
	}
	
	public int size() {
		return list.size();
	}
	
	private int compare(T item1, T item2) {
		int result = item1.compareTo(item2);
		if (type == Type.MIN_HEAP)
			result = -result;
		return result;
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
		while(i > 0 &&
      list.get(parent).compareTo(list.get(i)) < 0) {
			swap(i, parent);
			i = parent;
			parent = (i - 1) / 2;
		}
	}
	
	public T poll() {
		T result = list.get(0);
		T item = list.remove(list.size() - 1);
		if(list.size() > 0) {
			list.set(0, item);
			shiftDown();
		}
		return result;
	}
	
	public void shiftDown() {
		int i = 0;
		boolean loop = true;
		do {
			int left = 2 * i + 1;
			int right = 2 * i + 2;
			int max = i;
			if (left < list.size() &&
		    compare(list.get(left), list.get(max)) > 0)
				max = left;
			if (right < list.size() &&
		    compare(list.get(right), list.get(max)) > 0)
				max = right;
			
			if (max == i) {
				loop = false;
			} else {
				swap(i, max);
				i = max;
			}
		} while(loop);
	}
	
	@Override
	public String toString() {
		return list.toString();
	}
}
