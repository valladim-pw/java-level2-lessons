package Less04_HashTable_List_Deque_Stack._2_HashTables._2ChainHashTable;

import java.util.Arrays;

public class CHashTable<T> {
	class CHashItem<T> {
		private T item;
		private int key;
		private CHashItem<T> next;
		
		CHashItem(int key, T item) {
			this.key = key;
			this.item = item;
		}
		
		int getKey() {
			return key;
		}
		
		T getItem() {
			return item;
		}
		
		void setNext(CHashItem<T> item) {
			next = item;
		}
		
		CHashItem<T> getNext() {
			return next;
		}
		
//		@Override
//		public String toString() {
//			return "key: " + key + ", item: " + item;
//		}
	}
	CHashItem<T>[] table;
	
	int number;
	
//	@Override
//	public String toString() {
//		return Arrays.toString(table);
//	}
	
	CHashTable(int n) {
		this.number = n;
		table = new CHashItem[n];
	}
	
	public int getHash(int key) {
		return key % table.length;
	}
	
	void add(int key, T item) {
		int index = getHash(key);
		CHashItem<T> li = new CHashItem<>(key, item);
		CHashItem<T> head = table[index];
		table[index] = li;
		//System.out.println("key: "+index+" item: "+li.getItem());
		if(head != null)
			li.setNext(head);
	}
	
	T get(int key) {
		int index = getHash(key);
		CHashItem<T> current = table[index];
		do {
			if(current.key == key) {
				return  current.getItem();
			}
			current = current.getNext();
		} while(current != null);
		return null;
	}
	public static void main(String[] args) {
			CHashTable<String> table = new CHashTable<>(5);
			for(int i = 0; i < 20; i++) {
				table.add(i, "i=" + i);
			}
		//System.out.println(table.toString());
			for(int i = 0; i < 20; i++)
				System.out.println(table.get(i));
		}
}
