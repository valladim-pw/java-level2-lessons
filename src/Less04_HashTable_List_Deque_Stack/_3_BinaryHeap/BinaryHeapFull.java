package Less04_HashTable_List_Deque_Stack._3_BinaryHeap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinaryHeapFull<T extends Comparable<T>> {
	public enum Type {MAX_HEAP, MIN_HEAP};
	
	private List<T> list;
	private Type type;
	
	BinaryHeapFull() {
		list = new ArrayList<>();
		type = Type.MAX_HEAP;
	}
	
	BinaryHeapFull(Type type) {
		this();
		this.type = type;
	}
	
	public int size() {
		return list.size();
	}
	
	public void add(T item) {
		list.add(item);
		shiftUp();
	}
	
	public T poll() {
		T result = list.get(0);
		T item = list.remove(list.size() - 1);
		if (list.size() > 0) {
			list.set(0, item);
			shiftDown();
		}
		return result;
	}
	
	private void swap(int i, int j) {
		T item = list.get(i);
		list.set(i, list.get(j));
		list.set(j, item);
	}
	
	private int compare(T item1, T item2) {
		int result = item1.compareTo(item2);
		if (type == Type.MIN_HEAP)
			result = -result;
		return result;
	}
	@Override
	public String toString() {
		return list.toString();
	}
	
	public List<T> sort() {
		List<T> sorted = new ArrayList<>();
		for (int i=0; size() > 0; i++) {
			sorted.add(poll());
		}
		return sorted;
	}
	
	public void shiftUp() {
		int i = list.size() - 1;
		int parent = (i - 1) / 2;
		
		while (i > 0 && compare(list.get(parent), list.get(i)) < 0) {
			swap(i, parent);
			i = parent;
			parent = (i - 1) / 2;
		}
	}
	
	public void shiftDown() {
		int i = 0;
		boolean loop = true;
		do {
			int left = 2 * i + 1;
			int right = 2 * i + 2;
			int max = i;
			
			if (left < list.size() && compare(list.get(left), list.get(max)) > 0) {
				max = left;
			}
			if (right < list.size() && compare(list.get(right), list.get(max)) > 0) {
				max = right;
			}
			
			if (max == i) {
				loop = false;
			} else {
				swap(i, max);
				i = max;
			}
		} while (loop);
	}
	public static <T extends Comparable<T>> BinaryHeapFull<T> from(Type type, List<T> list) {
		BinaryHeapFull<T> heap = new BinaryHeapFull<>(type);
		for(T item: list) {
			heap.add(item);
		}
		return heap;
	}
	public static <T extends Comparable<T>> BinaryHeapFull<T> from(Type type, T[] array) {
		BinaryHeapFull<T> heap = new BinaryHeapFull<>(type);
		for(T item: array) {
			heap.add(item);
		}
		return heap;
	}
	public static <T extends Comparable<T>> BinaryHeapFull<T> of(Type type, T...array) {
		return from(type, array);
	}
	
	public static <T extends Comparable<T>> void sort(Type type, T[] array) {
			BinaryHeapFull<T> heap = from(type, array);
			for (int i=0; heap.size() > 0; i++) {
				array[i] = heap.poll();
			}
		}
	
	public static void main(String[] args) {
		BinaryHeapFull<Integer> heap = new BinaryHeapFull<>();
		heap.add(4);
		heap.add(2);
		heap.add(8);
		heap.add(6);
		heap.add(10);
		System.out.println("heap: " + heap.toString());
		Integer[] ints = {9, 1, 7, 3};
		System.out.println("arr: " + Arrays.toString(ints));
		sort(Type.MAX_HEAP, ints);
		System.out.println("heap2: " + heap.toString());
		System.out.println("arr2: " + Arrays.toString(ints));
	}
}