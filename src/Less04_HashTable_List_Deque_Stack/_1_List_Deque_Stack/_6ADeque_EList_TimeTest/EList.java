package Less04_HashTable_List_Deque_Stack._1_List_Deque_Stack._6ADeque_EList_TimeTest;

public class EList {
	static class ListItem {
		
		private ListItem next;
		
		void setNext(ListItem item) {
			next = item;
		}
		public ListItem getNext() {
			return  next;
		}
	}
	private ListItem head;
	private ListItem tail;
	
	public ListItem getHead() {
		return  head;
	}
	public void add(ListItem item) {
		if(head == null) {
			head = item;
			tail = item;
		} else {
			tail.setNext(item);
			tail = item;
		}
	}
	ListItem poll() {
		ListItem li = head;
		head = head.getNext();
		return li;
	}
}
