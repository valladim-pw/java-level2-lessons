package HomeWork.basetypes;

import java.util.Iterator;
import java.util.LinkedList;

public class CHashTable2<K, V> implements Iterable<V> {
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
	//CHashItemList<CHashItem<K, V>>[] pTable;
	int capacity;
	int size = 0;
	double loadFactor = 0.5;
	int threshold;

	CHashTable2(int n) {
		capacity = n;
		table = new CHashItem[n];
		threshold = getThreshold(capacity, loadFactor);
		//pTable = new CHashItemList[n];
		//fillPTable(pTable);
	}
//	public void fillPTable(CHashItemList<CHashItem<K, V>>[] table) {
//		for(int i = 0; i < table.length; i++) {
//			table[i] = new CHashItemList<>(i);
//			table[i].add(null);
//		}
//	}
	public int getHash(K key) {
		int k = 0;
		if(key instanceof String)
			k = new StringKey(key).getHash();
		else
			k = new IntKey(key).getHash();
		return k % table.length;
	}
	public V get(K key) {
		int index = getHash(key);
		CHashItem<K, V> current = table[index];
		do {
			if(current.getKey().equals(key)) {
				return current.getValue();
			}
			current = current.getNext();
		} while(current != null);
		return null;
	}
	
	public void add(K key, V value) {
		CHashTable2<K, V> newCHTable;
		CHashItem<K, V>[] copyTable;
		size++;
		boolean test = false;
		int index = getHash(key);
		CHashItem<K, V> li = new CHashItem<>(key, value);
		CHashItem<K, V> head = table[index];

//		if (size > 1) {
		//			test = testRemove(key, value);
		//		}
		//if(!test) {
			table[index] = li;
			//pTable[index].set(0, li);
			System.out.println("hash: " + index + " liKey: " + li.getKey() + " liValue: "+li.getValue());
			if (head != null) {
				li.setNext(head);
				//pTable[index].add(head);
				//System.out.println("hash: " + index + " headKey: " + head.getKey() + " headValue: "+head.getValue());
			}
		//}
		if(size == threshold) {
			int newCapacity = nextPrime(capacity * 2);
			copyTable = new CHashItem[size];
			newCHTable = new CHashTable2<>(newCapacity);
			copyData(copyTable);
			capacity = newCapacity;
			threshold = newCHTable.threshold;
			table = newCHTable.table;
			size = 0;
			createNewHashTable(newCHTable, copyTable);
			//table = newCHTable.table;
			size = copyTable.length;
		}
		//System.out.println("table length: " + table.length);
//		System.out.println("thres: " + threshold);
//		System.out.println("capacity: " + capacity);
		//System.out.println("print() =============================");
		displayTable(table);
		
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
		System.out.println("copyTable:");
		//print(copyTable);
	}
	void createNewHashTable(CHashTable2<K, V> newHashTable, CHashItem<K, V>[] srcTable) {
		for(int i = 0; i < srcTable.length; i++) {
			K key = srcTable[i].getKey();
			//System.out.println("copyTable key: " + key);
			V value = srcTable[i].getValue();
			//System.out.println("copyTable value: " + value);
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
	public void displayTable(CHashItem<K, V>[] table) {
		System.out.println("Table:");
		CHashItemList<CHashItem<K, V>>[] pTable = new CHashItemList[capacity];
		for(int i = 0; i < pTable.length; i++) {
			pTable[i] = new CHashItemList<>(i);
			pTable[i].add(null);
		}
		//System.out.println("step null: " + Arrays.toString(pTable));
	  TableIterator<CHashItem<K, V>> iter = null;
	  if(table.length > 0)
	    iter = new TableIterator<>(table);
	  while(iter.hasNext()) {
      CHashItem<K, V> item = iter.next();
      int index = getHash(item.getKey());
	    for(int j = 0; j < pTable.length; j++) {
		    if(pTable[j].getIndex() == index) {
		    	if(pTable[j].get(0) == null)
		    		pTable[j].set(0, item);
		    	else
		    		pTable[j].add(item);
		    }
	    }
		  //System.out.println("step add: " + Arrays.toString(pTable));
		}
	  for(CHashItemList<CHashItem<K, V>> value : pTable) {
		  System.out.println(value);
	  }
		//System.out.println("step result: " + Arrays.toString(pTable));
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
		CHashTable2<String, Integer> table = new CHashTable2<>(2);
		for(int i = 0; i < 8; i++) {
			table.add("i=" + i, i * 2);
			System.out.println("--------------------------");
			System.out.println("size: " + table.size());
			System.out.println("thres: " + table.threshold);
			System.out.println("capacity: " + table.capacity);
			System.out.println("table length: " + table.table.length);
			//table.print(table.table);
		}
		System.out.println("result:");
		table.displayTable(table.table);
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
*
	TableIterator<CHashItem<K, V>> iter = new TableIterator<>(table);
	while (iter.hasNext()) {
		CHashItem<K, V> item = iter.next();
		K key = item.getKey();
		V value = item.getValue();
		newTable.add(key, value);
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
	  
	  public void displayTable() {
	  		CHashItem<K, V> current;
	  		int i = 0;
	      System.out.println("Table: ");
	      for (int i = 0; i < table.length; i++) {
	        if (table[i] != null) {
	  //	      if(table[i].getNext() == null)
	  //		      System.out.println(table[i].toString());
	  	      if(table[i].getNext() != null) {
	  		      System.out.print(table[i].toString());
	  		      CHashItem<K, V> next = table[i].getNext();
	  		      //System.out.print(next.toString() + "N1 ");
	  		      if(next.getNext() != null) {
	  			      System.out.print(next.toString() + "N1 ");
	  			      next = next.getNext();
	  			      System.out.print(next.toString() + "N2 ");
	  		      }
	  		      if(next.getNext() == null) {
	  			      System.out.println("N11 ");
	  		      }
	  	      } else {
	  		      System.out.println(table[i].toString());
	  	      }
	            //System.out.print(table[j].getNext().toString() + "N ");
	        } else
	          System.out.println("null");
	      }
	      System.out.println("");
	    }
	    public void displayTable() {
	    		LinkedList<CHashItemList<CHashItem<K, V>>> list = new LinkedList<>();
	    		CHashItemList<CHashItem<K, V>> li;
	    		LinkedList<CHashItem<K, V>> lst = new LinkedList<>();
	    		System.out.println("Table:");
	    		TableIterator<CHashItem<K, V>> iter = null;
	    		int i = 0;
	    		if(table.length > 0)
	    			iter = new TableIterator<>(table);
	    		while(i < size()) {
	    			if(iter.hasNext()) {
	    				CHashItem<K, V> item = iter.next();
	    				lst.add(item);
	    			} else
	    				lst.add(null);
	    			i++;
	    		}
	    			for(int j = 0; j < lst.size(); j++) {
			if(lst.get(j) != null)
				System.out.println(lst.get(j).toString());
			else
				System.out.println("null");
		}
	}
	//		if (size > 1) {
	//			test = testRemove(key, value);
	//		}
* */





