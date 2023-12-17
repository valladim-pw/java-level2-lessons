package Less04_HashTable_List_Deque_Stack._2_HashTables._2ChainHashTable;

import java.util.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CHashTable2<K, V>  implements Iterable<V> {
	class CHashItem<K, V>{
		private V value;
		private K key;
		private CHashItem<K, V> next;

		CHashItem(K key, V value) {
			this.key = key;
			this.value = value;
		}

		K getKey() {
			return key;
		}

		V getValue() {
			return value;
		}

		void setNext(CHashItem<K, V> item) {
			next = item;
		}

		CHashItem<K, V> getNext() {
			return next;
		}

		@Override
		public String toString() {
			return "{key: " + key + ", value: " + value + "}";
		}
	}
	class CHashItemList<T> extends LinkedList<T> {
		public int index;

		CHashItemList(int index) {
			super();
			this.index = index;
		}
		int getIndex() {
			return index;
		}
		@Override
			public String toString() {
				LinkedList<T> list = new LinkedList<>(this);
				return "basket " + index + ": " + list + "\n";
			}
	}
//	class K<K> {
//		private K key;
//		int getInt() {
//			return Integer.valueOf(key.toString()).intValue();
//		}
//	}
//	class V<V> {
//		private V value;
//	}
	CHashItem<K, V>[] table;
	LinkedList<CHashItemList<CHashItem<K, V>>> cList;
	int capacity;
	int size = 0;
	double loadFactor = 0.1;
	int threshold;

	CHashTable2(int n) {
		this.capacity = n;
		table = new CHashItem[n];
		threshold = getThreshold(capacity, loadFactor);
		cList = new LinkedList<>();
	}
	public class TableIterator<V> implements Iterator<V> {
		CHashItem<K, V> current;
		//K key = current.getKey();

		public TableIterator(CHashItem<K, V>[] table) {
			int index = getHash(current.getKey());
			current = table[index];
		}
		public boolean hasNext() {
			return current != null;
		}
		public V next() {
			V value = current.getValue();
			current = current.getNext();
			return value;
		}
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	public Iterator<V> iterator() {
		return new TableIterator(table);
	}
	public int getHash(K key) {
		//Integer i = Integer.valueOf(key.toString()).intValue();
		return (int)key % table.length;
	}

	void add(K key, V item) {
		int index = getHash(key);
		cList.add(new CHashItemList<>(index));
		CHashItem<K, V> li = new CHashItem<>(key, item);
		CHashItem<K, V> head = table[index];
		table[index] = li;
		System.out.println("hash: " + index + " itemKey: " + li.getKey() + " itemValue: "+li.getValue());
		if(head != null) {
			li.setNext(head);
		}
		for(int i = 0; i < cList.size(); i++) {
			if(cList.get(i).getIndex() == index) {
				cList.get(i).add(0, li);
			}
		}
		size++;
		//System.out.println("count: " + size);
//		System.out.println("cList: " + cList);
	}

	V get(K key) {
		int index = getHash(key);
		CHashItem<K, V> current = table[index];
		do {
			if(current.key.equals(key)) {
				return  current.getValue();
			}
			current = current.getNext();
		} while(current != null);
		return null;
	}
	public static int getThreshold(int capacity, double loadFactor) {
		double c = Integer.valueOf(capacity).doubleValue();
		Double t = Math.ceil(c + (c * loadFactor));
		return t.intValue();
  }

	void print() {
		for(int i = 0; i < capacity; i++) {
			System.out.println("hash(tableIndex): " + cList.get(i).getIndex());
			System.out.println(cList.get(i));
		}
	}

	public static void main(String[] args) {
//		CHashTable2<Integer, String> table = new CHashTable2<>(10);
//		for(int i = 0; i < 22; i++) {
//			table.add(i, "i=" + i);
//		}
//		table.print();
//		System.out.println(table.size);
//		System.out.println(table.threshold);
	}
}
