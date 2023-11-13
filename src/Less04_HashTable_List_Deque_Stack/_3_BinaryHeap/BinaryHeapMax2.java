package Less04_HashTable_List_Deque_Stack._3_BinaryHeap;

import java.util.*;

public class BinaryHeapMax2<T extends Comparable<T>> {
	
	public static int count;
	public static int pow;
	private List<T> list;
	public LinkedList<Integer> plist;
	
	BinaryHeapMax2() {
		list = new ArrayList<>();
		plist = new LinkedList<>();
		count = 0;
		pow = 0;
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
	private void swap(int i, int j) {
		T item = list.get(i);
		list.set(i, list.get(j));
		list.set(j, item);
	}
	public void shiftUp() {
		int i = list.size() - 1;
		int parent = (i - 1) / 2;
		int c = pow - 1;
		while(i > 0 && list.get(parent).compareTo(list.get(i)) < 0) {
			swap(i, parent);
			i = parent;
			parent = (i - 1) / 2;
			System.out.println("shiftUp-> value \""
         + list.get(i) + "\" to Level-" + c  + ": \n" + list);
			print(2);
			c--;
		}
	}
	@Override
	public String toString() {
		return list.toString();
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
