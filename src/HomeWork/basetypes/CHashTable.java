package HomeWork.basetypes;

import java.util.*;

public class CHashTable<K, V> implements Iterable<V> {
	class IntKey<K> implements HashValue {
		K key;
		public int getHash() {
			return (int)key;
		}
	}
	class StringKey<K> implements HashValue {
		K key;
		public int getHash() {
			return stringHashFunction((String)key);
		}
		public int stringHashFunction(String str) {
			int hashCode = 0;
			for (int i = 0; i < str.length(); i++) {
				hashCode += str.charAt(i);
			}
			return hashCode;
		}
	}
	class CHashItem<K, V> {
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
		void setOtherValue(V otherValue) {
			this.value = otherValue;
		}
		void setOtherKey(K otherKey) {
			this.key = otherKey;
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
		CHashItemList() {
			super();
		}
		CHashItemList(int index) {
			this();
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
	
	CHashItem<K, V>[] table;
	int capacity;
	int size = 0;
	double loadFactor = 0.5;
	int threshold;

	CHashTable(int n) {
		capacity = n;
		table = new CHashItem[n];
		threshold = getThreshold(capacity, loadFactor);
	}
	public int getHash(K key) {
		Integer i = Integer.valueOf(key.toString()).intValue();
		return i % table.length;
	}
	public V get(K key) {
		int index = getHash(key);
		CHashItem<K, V> current = table[index];
		do {
			if(current.getKey().equals(key)) {
				return  current.getValue();
			}
			current = current.getNext();
		} while(current != null);
		return null;
	}
	
	public void add(K key, V value) {
		CHashItem<K, V>[] copyTable;
		size++;
		boolean test = false;
		int index = getHash(key);
		CHashItem<K, V> li = new CHashItem<>(key, value);
		CHashItem<K, V> head = table[index];
		if (size > 1) {
			test = testRemove(key, value);
		}
		if(!test) {
			table[index] = li;
			//System.out.println("hash: " + index + " liKey: " + li.getKey() + " liValue: "+li.getValue());
			if (head != null) {
				li.setNext(head);      //System.out.println("hash: " + index + " headKey: " + head.getKey() + " headValue: "+head.getValue());
			}
		}
//		if(size == threshold) {
//			int newCapacity = nextPrime(capacity * 2);
//			copyTable = new CHashItem[size];
//			CHashTable<K, V> newCHTable = new CHashTable<>(newCapacity);
//			copyData(copyTable);
//			capacity = newCapacity;
//			threshold = newCHTable.threshold;
//			table = newCHTable.table;
//			size = 0;
//			createNewHashTable(newCHTable, copyTable);
//			size = copyTable.length;
//		}
		System.out.println("table length: " + table.length);
//		System.out.println("thres: " + threshold);
//		System.out.println("capacity: " + capacity);
		//System.out.println("----------------------");
		//print(table);
	}
	boolean testRemove(K key, V value) {
		int index1 = getHash(key);
		boolean remove = false;
		TableIterator<CHashItem<K, V>> iter = new TableIterator<>(table);
		while(iter.hasNext()) {
			CHashItem<K, V> item = iter.next();
			K k = item.getKey();
			int index2 = getHash(k);
			V v = item.getValue();
			if(index1 == index2 && v.equals((V)"remove")) {
				if(k.equals(key))
					item.setOtherValue(value);
				else {
					item.setOtherValue(value);
					item.setOtherKey(key);
				}
				remove = true;
				break;
			}
		}
		//System.out.println("remove: " + remove);
		return remove;
	}
	
	void copyData(CHashItem<K, V>[] copyTable) {
		int i = 0;
		for(V value : this) {
			copyTable[i] = (CHashItem<K, V>) value;
			i++;
		}
	}
	void createNewHashTable(CHashTable<K, V> newHashTable, CHashItem<K, V>[] srcTable) {
		for(int i = 0; i < srcTable.length; i++) {
			K key = srcTable[i].getKey();
			V value = srcTable[i].getValue();
			newHashTable.add(key, value);
		}
	}
	public static int getThreshold(int capacity, double loadFactor) {
		double c = Integer.valueOf(capacity).doubleValue();
		Double t = Math.ceil(c + (c * loadFactor));
		return t.intValue();
  }
	public void remove(K key) {
		TableIterator<CHashItem<K, V>> iter = new TableIterator<>(table);
		while(iter.hasNext()) {
			CHashItem<K, V> item = iter.next();
			K k = item.getKey();
			if(k.equals(key)) {
				iter.remove();
				size--;
				break;
			}
		}
	}
	public void change(K key1, K key2) {
		TableIterator<CHashItem<K, V>> iter = new TableIterator<>(table);
		while(iter.hasNext()) {
			CHashItem<K, V> item = iter.next();
			K k = item.getKey();
			if(k.equals(key1)) {
				item.setOtherKey(key2);
				break;
			}
		}
	}
	
	public class TableIterator<T> implements Iterator<T> {
		int index = 0;
		CHashItem<K, V> current;
		CHashItem<K, V> remove;
		
		public TableIterator(CHashItem<K, V>[] table) {
			current = table[index];
		}
		public boolean hasNext() {
			if(index < capacity && current != null)
				return true;
			return false;
		}
		public T next() {
			T item = (T)current;
			remove = (CHashItem<K, V>) item;
			if(current.getNext() != null) {
				current = current.getNext();
			} else {
				index++;
				if(index == capacity)
					return item;
				current = table[index];
			}
			return item;
		}
		public void remove() {
			try {
				remove.setOtherValue((V)"remove");
			} catch(UnsupportedOperationException e) {
				throw e;
			}
		}
	}
	public Iterator<V> iterator() {
		return new TableIterator(table);
	}
	public int size() {
//		size = 0;
//		for(V value : this) {
//			size++;
//		}
		return size;
	}
	public static int nextPrime(int start){
		int nextPrime = 0;
		int limit = 100;
		boolean simple = false;
		int step = start + limit;
		for(int i = start; i < step; i++) {
			int currNum = i;
			for(int j = 2; j < i; j++) {
				if(currNum % j == 0) {
					simple = false;
					break;
				}	else
					simple = true;
			}
			if(simple) {
				nextPrime = currNum;
				break;
			}
			if(i == step - 1)
				step += limit;
		}
    return nextPrime;
  }
	void print(CHashItem<K, V>[] table) {
		LinkedList<CHashItemList<CHashItem<K, V>>> cList = new LinkedList<>();
		TableIterator<CHashItem<K, V>> iter = null;
		if(table.length > 0)
			iter = new TableIterator<>(table);
		while (iter.hasNext()) {
			CHashItem<K, V> item = iter.next();
			K key = item.getKey();
			int index = getHash(key);
			boolean equal = false;
			CHashItemList<CHashItem<K, V>> iteml = null;
			if(cList.size() < 1) {
				cList.add(new CHashItemList<>(index));
				cList.get(0).add(0, item);
			} else {
				for(int i = 0; i < cList.size(); i++) {
					if(cList.get(i).getIndex() == index) {
						iteml = cList.get(i);
						equal = true;
						break;
					} else {
						equal = false;
					}
				}
				if(equal) {
					iteml.add(item);
				} else {
					cList.add(new CHashItemList<>(index));
					cList.getLast().add(item);
				}
			}
		}
		System.out.println(cList);
	}
	

	public static void main(String[] args) {
		CHashTable<Integer, Integer> table = new CHashTable<>(4);
		for(int i = 0; i < 12; i++) {
			table.add(i, i * 2);
			System.out.println("--------------------------");
			System.out.println("size: " + table.size());
			System.out.println("thres: " + table.threshold);
			System.out.println("capacity: " + table.capacity);
			table.print(table.table);
		}
//		System.out.println("table size: " + table.size());
		table.remove(9);
		//System.out.println("table:");
		//table.print(table.table);
		//System.out.println(table.size());
		table.add(5, 25 * 2);
		//table.print(table.table);
		//System.out.println(table.size());
		//table.add(20, 20 * 2);
		//table.print(table.table);
		//System.out.println(table.size());
//		for(int i = 0; i < 7; i++) {
//			System.out.println(table.get(i));
//		}
		//System.out.println(table.size());
		//System.out.println(table.threshold);
	}
}
/*
*
	TableIterator<CHashItem<K, V>> iter = new TableIterator<>(table);
	while (iter.hasNext()) {
		CHashItem<K, V> item = iter.next();
		K key = item.getKey();
		V value = item.getValue();
		newTable.add(key, value);
	}
	public void displayTable() {
	    System.out.println("Table: ");
	    for (int i = 0; i < table.length; i++) {
	      if (table[i] != null) {
		      if(table[i].getNext() == null)
		        System.out.println(table[i].toString());
		      if(table[i].getNext() != null) {
			      System.out.print(table[i].toString());
			      System.out.println(table[i].getNext().toString() + "N ");
		      }
	          //System.out.print(table[j].getNext().toString() + "N ");
	      } else
	        System.out.println("null");
	    }
	    System.out.println("");
	  }
* */





