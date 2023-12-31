package Less04_HashTable_List_Deque_Stack._1_List_Deque_Stack._1ObjectsList;

public class OList<T> {
	public class ListItem<T> {
// Элемент списка
		private T item;
// Указатель (ссылка) на следующий элемент
		private ListItem<T> next;
		
		ListItem(T item) {
			this.item = item;
		}
		T getItem() {
			return  item;
		}
		void setNext(ListItem<T> item) {
			next = item;
		}
		public ListItem<T> getNext() {
			return  next;
		}
	}
	private ListItem<T> head;
	private ListItem<T> tail;
	
	public ListItem<T> getHead() {
		return  head;
	}
	public void add(T item) {
		ListItem<T> li = new ListItem<T>(item);
		if(head == null) {
			head = li;
			tail = li;
		} else {
			tail.setNext(li);
			tail = li;
		}
	}
}
