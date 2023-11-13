package Less04_HashTable_List_Deque_Stack._3_BinaryHeap;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BinaryHeapFull2<T extends Comparable<T>> {
	public enum Type {MAX_HEAP, MIN_HEAP};
	
	public static int count;
	public static int pow;
	public LinkedList<Integer> plist;
	private List<T> list;
	private Type type;
	
	BinaryHeapFull2() {
		list = new ArrayList<>();
		type = Type.MAX_HEAP;
		plist = new LinkedList<>();
		count = 0;
		pow = 0;
	}
	
	BinaryHeapFull2(Type type) {
		this();
		this.type = type;
	}
	
	public int size() {
		return list.size();
	}
	
	public void add(T item) {
		count++;
		if(count == pow(2, pow))
			pow++;
		list.add(item);
		plist.add(pow);
		System.out.println("LEVEL " + pow + " >>> add value \""
						                   + item + "\": \n" + list);
		print(2);
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
	
	public void shiftUp() {
		int i = list.size() - 1;
		int parent = (i - 1) / 2;
		int c = pow - 1;
		while(i > 0 && compare(list.get(parent), list.get(i)) < 0) {
			swap(i, parent);
			i = parent;
			parent = (i - 1) / 2;
			System.out.println("shiftUp-> value \""
         + list.get(i) + "\" to Level-" + c  + ": \n" + list);
			print(2);
			c--;
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
	
	@Override
	public String toString() {
		return list.toString();
	}
	
	public List<T> sort() {
		List<T> sorted = new ArrayList<>();
		for (int i = 0; size() > 0; i++) {
			sorted.add(poll());
		}
		return sorted;
	}
	
	public static <T extends Comparable<T>> BinaryHeapFull2<T> from(Type type, List<T> list) {
		BinaryHeapFull2<T> heap = new BinaryHeapFull2<>(type);
		for(T item: list) {
			heap.add(item);
		}
		return heap;
	}
	
	public static <T extends Comparable<T>> BinaryHeapFull2<T> from(Type type, T[] array) {
		BinaryHeapFull2<T> heap = new BinaryHeapFull2<>(type);
		for(T item: array) {
			heap.add(item);
		}
		return heap;
	}
	
	public static <T extends Comparable<T>> BinaryHeapFull2<T> of(Type type, T...array) {
		return from(type, array);
	}
	
	public static <T extends Comparable<T>> void sort(Type type, T[] array) {
		BinaryHeapFull2<T> heap = from(type, array);
		for (int i = 0; heap.size() > 0; i++) {
			array[i] = heap.poll();
		}
	}
	
	/* Дополнительные методы для печати */
	
	public int maxLen() {
		T max = list.stream().reduce(list.get(0),
				(a, x) -> a.toString().length() < x.toString().length() ? x : a);
		return max.toString().length();
	}
	public static int pow(int value, int powValue) {
		return (int) Math.pow(value, powValue);
	}
	static String ws(int n) {
		return ("").concat(" ").repeat(n);
	}
	static String vdash(int maxLen) {
		return "|" + ws(maxLen - 1);
	}
	public String wsValue(int maxLen, int offset, T value) {
		int len = value.toString().length();
		return value + ws(maxLen - len);
	}
	public String dashValue(int maxLen, int offset, T value) {
		String a = ws(1) + ("").concat("_").repeat(offset - 1);
		int len = value.toString().length();
		String b = ("").concat("_").repeat(maxLen - (a.length() + len)) + ws(1);
		return a + value + b + ws(maxLen - 1);
	}
	
	public void print(int offset) {
		int width = maxLen() + (offset * 2);
		int dwidth = 0;
		int ind = 0;
		int num = count;
		int lastpow = plist.getLast();
		String sval = "";
		String svdash = "";
		StringBuilder sb = new StringBuilder();
		LinkedList<String> ls = new LinkedList<>();
		for(int i = size() - 1; i >= 0; i--) {
			int ipow = plist.get(i);
			if(ipow == lastpow && num < pow(2, ipow)) {
				sb.insert(0, wsValue(width, offset, list.get(i)));
				sval = sb.toString();
				svdash += vdash(width);
			}
			if(ipow < lastpow && num <= pow(2, ipow)) {
				sb.insert(0, dashValue(width, offset, list.get(i)));
				sval = ws(ind - offset) + sb.toString();
				svdash += vdash(dwidth - 1);
			}
			if(num == pow(2, ipow - 1)) {
				if(dwidth > 0)
					width = dwidth - 1;
				dwidth = width*2 + 1;
				if(num == 1)
					ls.add(0, sval);
				else {
					ls.add(0, sval);
					ls.add(0, svdash);
				}
				sb.delete(0, sb.length());
				ind += offset;
				svdash = ws(ind);
			}
			num--;
		}
		for(int i = 0; i < ls.size(); i++) {
			System.out.println(ls.get(i));
		}
	}
}
