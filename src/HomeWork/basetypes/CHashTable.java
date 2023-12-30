package HomeWork.basetypes;

import java.util.*;

public class CHashTable<K, V> implements Iterable<V> {
	class IntKey<K> implements HashValue {
		K key;
		public IntKey(K key) {
			this.key = key;
		}
		public int getHash() {
			return (int)key;
		}
	}
	class StringKey<K> implements HashValue {
		K key;
		public StringKey(K key) {
			this.key = key;
		}
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
				return "basket " + index + ": " + list;
				//return "" + list;
			}
	}
	
	CHashItem<K, V>[] table;
	LinkedList<CHashItem<K, V>> copyList;
	int capacity;
	int size = 0;
	double loadFactor = 0.5;
	int threshold;
	boolean rehash = false;

	CHashTable(int n) {
		capacity = n;
		table = new CHashItem[n];
		threshold = getThreshold(capacity, loadFactor);
		copyList = new LinkedList<>();
	}
	public int getHash(K key) {
		int k = 0;
		if(key instanceof String)
			k = new StringKey(key).getHash();
		else
			k = new IntKey(key).getHash();
		return k % table.length;
	}
	
	public void add(K key, V value) {
		CHashTable<K, V> newCHTable;
		size++;
		boolean test = false;
		int index = getHash(key);
		CHashItem<K, V> li = new CHashItem<>(key, value);
		CHashItem<K, V> head = table[index];
		table[index] = li;
		if (head != null) {
			li.setNext(head);
		}
		if(!rehash)
			copyList.add(li);
		//System.out.println("copyList1: " + copyList);
		System.out.println("-------------------------------");
		System.out.println("hash: " + index + " liKey: " + li.getKey() + " liValue: "+li.getValue());
		System.out.println("-------------------------------");
		if(size == threshold) {
			int newCapacity = nextPrime(capacity * 2);
			//copyTable = new CHashItem[size];
			newCHTable = new CHashTable<>(newCapacity);
			//copyData(copyTable);
			capacity = newCapacity;
			threshold = newCHTable.threshold;
			table = newCHTable.table;
			size = 0;
			createNewHashTable(copyList);
			size = copyList.size();
		}
		
		displayTable();
	}
	
//	void copyData(CHashItem<K, V>[] copyTable) {
//		int i = 0;
//		for(V value : this) {
//			copyTable[i] = (CHashItem<K, V>) value;
//			i++;
//		}
//		//System.out.println("copyTable:" + Arrays.toString(copyTable));
//	}
	void createNewHashTable(LinkedList<CHashItem<K, V>> copyList) {
		rehash = true;
		for(int i = 0; i < copyList.size(); i++) {
			K key = copyList.get(i).getKey();
			V value = copyList.get(i).getValue();
			this.add(key, value);
		}
		rehash = false;
	}
	public static int getThreshold(int capacity, double loadFactor) {
		double c = Integer.valueOf(capacity).doubleValue();
		Double t = Math.ceil(c + (c * loadFactor));
		return t.intValue();
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
	public void displayTable() {
		System.out.println("display********");
		System.out.println("size: " + size());
		System.out.println("thres: " + threshold);
		System.out.println("capacity: " + capacity);
		System.out.println("table length: " + this.table.length);
		//CHashItem<K, V>[] copyTable = new CHashItem[size()];
		CHashItemList<CHashItem<K, V>>[] pTable = new CHashItemList[capacity];
		for(int i = 0; i < pTable.length; i++) {
			pTable[i] = new CHashItemList<>(i);
			pTable[i].add(null);
		}
		
		System.out.println("copyList2:" + copyList);
		System.out.println("==============================");
		CHashItem<K, V> item = null;
		int index = 0;

    for(int i = 0; i < copyList.size(); i++) {
	    item = copyList.get(i);
      index = getHash(item.getKey());
	    for(int j = 0; j < pTable.length; j++) {
        if(pTable[j].getIndex() == index) {
          if(pTable[j].get(0) == null)
            pTable[j].set(0, item);
          else
          	pTable[j].add(0, item);
        }
      }
    }

	  for(CHashItemList<CHashItem<K, V>> value : pTable) {
		  System.out.println(value);
	  }
		//System.out.println("step result: " + Arrays.toString(pTable));
	}
	
	public static void main(String[] args) {
		CHashTable<String, Integer> table = new CHashTable<>(2);
		for(int i = 0; i < 12; i++) {
			table.add("i=" + i, i * 2);
//			System.out.println("--------------------------");
//			System.out.println("size: " + table.size());
//			System.out.println("thres: " + table.threshold);
//			System.out.println("capacity: " + table.capacity);
//			System.out.println("table length: " + table.table.length);
			//table.print(table.table);
		}
		//System.out.println("result++++++++++++++++++++++++++++");
		//table.displayTable(table.table);
		//table.remove(9);
		//System.out.println("table:");
		//table.print(table.table);
		//System.out.println(table.size());
		//table.add(5, 25 * 2);
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
TableIterator<CHashItem<K, V>> iter = null;
		boolean innerNull = false;
		int mark = 0;
		for(int i = 0; i < table.length; i++) {
			if (table[i] == null)
				mark = i;
			else {
				if(i > mark) {
					innerNull = true;
					break;
				}
			}
		}
		CHashItem<K, V> item = null;
		int index = 0;
		
		if(innerNull && size() < capacity) {
	    for(int i = 0; i < table.length; i++) {
	    	if(table[i] != null) {
			    item = table[i];
          index = getHash(item.getKey());
		    }
		    for(int j = 0; j < pTable.length; j++) {
          if(pTable[j].getIndex() == index) {
            if(pTable[j].get(0) == null)
              pTable[j].set(0, item);
          }
        }
	    }
		} else {
			CHashItem<K, V> item2 = null;
			int index2 = 0;
			if(table.length > 0)
	      iter = new TableIterator<>(table);
		  while(iter.hasNext()) {
	      item2 = iter.next();
	      index2 = getHash(item2.getKey());
		    for(int j = 0; j < pTable.length; j++) {
			    if(pTable[j].getIndex() == index2) {
			      if(pTable[j].get(0) == null)
			        pTable[j].set(0, item2);
			      else
			        pTable[j].add(item2);
			    }
		    }
			}
		}
 */




