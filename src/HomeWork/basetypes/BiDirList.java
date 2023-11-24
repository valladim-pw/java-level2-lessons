package HomeWork.basetypes;

import java.util.*;

public class BiDirList<T> implements Iterable<T> {
	
	public class ListItem<T> {
		private T item;
		private ListItem<T> next;
		private ListItem<T> prev;
		
		ListItem(T item) {
			this.item = item;
		}
		T getItem() {
			return item;
		}
		void setNext(ListItem<T> item) {
			next = item;
		}
		public ListItem<T> getNext() {
			return next;
		}
		void setPrev(ListItem<T> item) {
			prev = item;
		}
		public ListItem<T> getPrev() {
			return prev;
		}
		
		@Override
		public String toString() {
			if(this.getItem() == null)
				return "";
			else
				return "" + item;
		}
	}
	public class ListIterator<T> implements Iterator<T> {
		BiDirList<T>.ListItem<T> current;
	
		public ListIterator(BiDirList<T> list) {
			current = list.getStart();
		}
		public boolean hasNext() {
			return current != null;
		}
		public T next() {
			T value = current.getItem();
			current = current.getNext();
			return value;
		}
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	private ListItem<T> start;
	private ListItem<T> end;
	//private BiDirList<T> copyList;
	int size = 0;
	
//	BiDirList() {
//		copyList = new BiDirList<T>();
//	}
	
	public Iterator<T> iterator() {
		return new ListIterator(this);
	}
	
	public ListItem<T> getStart() {
		return start;
	}
	public ListItem<T> getEnd() {
		return end;
	}
	
	public void addLast(T item) {
		ListItem<T> el = new ListItem<T>(item);
		if(start == null) {
			start = el;
			end = el;
		} else {
			end.setNext(el);
			end = el;
		}
		size++;
	}
	public void addFirst(T item) {
		ListItem<T> el = new ListItem<T>(item);
		if(start == null) {
			start = el;
			end = el;
		} else {
			start.setPrev(el);
			el.setNext(start);
			start = el;
		}
		size++;
	}
	public int size() {
		return size;
	}
	public T at(int i) {
		int c = 0;
		for(T value : this) {
			if(c == i)
				return value;
			c++;
		}
		return null;
	}
	public void remove(T item) {
//	  Iterator<T> iterator = this.iterator();
//	  while(iterator.hasNext()) {
//	  	checkAndRemove(item);
//	  	T val = iterator.next();
////	  	if(item.equals(val))
////	  		removeElem(iterator.next());
//		  System.out.println(val);
//	  }
		//int c = 0;
		//int len = size();
		BiDirList<T> list = this;
		//copyList = new BiDirList<T>();
//		BiDirList<T>.ListItem<T> listStart = this.getStart();

		
		BiDirList<T> newList = new BiDirList<T>();
		for(T value : this) {
			//boolean remove = false;
			//ListItem<T> valI = new ListItem<T>(item);
			//ListItem<T> val = new ListItem<T>(value);
			//ListItem<T> valC = new ListItem<T>(at(0));
			//ListItem<T> valS = new ListItem<T>(at(len-1));
			//ListItem<T> valCC = new ListItem<T>(at(c - 1));
			//ListItem<T> valE = this.getEnd();
//			T elN = start.getNext().getItem();
			
			if(/*c < len*2 &&*/ !value.equals(item)) {
				newList.addLast(value);
				start = null;
			}
			
//			System.out.println("len:" + len);
//		  System.out.println("size: " + size());
//			System.out.println("c: " + c);
//			System.out.println("start:" + start.getItem());
////			System.out.println("startNext:" + start.getNext().getItem());
////			System.out.println("startPrev:" + start.getPrev().getItem());
//			System.out.println("end:" + end.getItem());
			//c++;
		}
		for(T value : newList)
			list.addLast(value);
		//print(list);
		//print(newList);
	}
	
	static<T> void print(BiDirList<T> list) {
		LinkedList<T> li = new LinkedList<>();
		BiDirList<T>.ListItem<T> current = list.getStart();
		while(current != null) {
			li.add(current.getItem());
			current = current.getNext();
		}
		System.out.println(li);
	}
	
	public static void main(String[] args) {
		LinkedList<Integer> list1 = new LinkedList<>();
		LinkedList<Integer> list2 = new LinkedList<>();
		list1.add(22);
		list1.add(20);
		list1.add(2);
		list1.add(10);
		list2.add(22);
		list2.add(2);
		list2.add(10);
//		System.out.println(list1);
//		System.out.println(list2);
//		list1 = list2;
//		System.out.println(list1);
//		System.out.println(list2);
		BiDirList<Integer> bdl = new BiDirList<>();
		bdl.addLast(2);
		bdl.addLast(10);
		bdl.addFirst(20);
		bdl.addFirst(22);
//		bdl.addLast(11);
//		bdl.addFirst(23);
//		bdl.addFirst(24);
//		bdl.addFirst(25);
//		bdl.addLast(12);
		bdl.print(bdl);
		bdl.remove(10);
		bdl.print(bdl);
	}
}
//	public void checkAndRemove(T item, T value) {
//		ListItem<T> el = new ListItem<T>(item);
//		ListItem<T> listItem = new ListItem<T>(value);
//		ListItem<T> curr = this.getStart();
//		T val = null;
//
//		//curr = curr.getNext();
//		start = listItem;
//
//		//System.out.println("curr: " + curr.getItem());
//
//		System.out.println("start: " + start.getItem());
//		if(start.getNext() != null)
//			System.out.println("startNext: " + start.getNext().getItem());
//		//System.out.println("startNext: " + start.getNext().getItem());
////		if(size > 1 && start != null) {
////			start.setNext(listItem);
////			start = listItem;
////		}
//		if(item.equals(start.getItem())) {
//			el = null;
//			//val = null;
//			//start = start.getNext();
//			//curr = curr.getNext();
//
//			//curr = start;
//		}
//		//else {
//
//		//}
//	}
//			if(c > 0 ) {
//				start = val;
//				start.setPrev(valCC);
//				start = start.getPrev();
//				start.setNext(val);
//				start = valCC;
//				if (val.getItem().equals(valI.getItem())) {
//
//					val.setNext(start);
//					start = val;
//				}
//			}
//			if(c == len - 1 && c < len*2 - 1) {
//				c = 0;
//				this.addLast(valC.getItem());
//			}
//end = val;
//			if(!valC.getItem().equals(valI.getItem())) {
//				end.setNext(valC);
//				end = valC;
//			}
//				end = val;
//				end.setPrev(valCC);
//				end = end.getPrev();
//				end.setNext(val);
//end = end.getNext();
//end = new ListItem<T>(end.getNext().getItem());
//				end = valI;
//				end.setNext(valCC);
//				end = valCC;
//System.out.println("end2:" + end.getItem());
//				System.out.println("endNext2:" + end.getPrev().getItem());
//System.out.println("valCC:" + valCC.getItem());
//System.out.println("itm: " + itm.item);
//System.out.println("at(c + 1): " + new ListItem<T>(at(c + 1)).getItem());