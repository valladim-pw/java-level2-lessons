package Less04_HashTable_List_Deque_Stack._1_List_Deque_Stack._2ElementsList;

import Less04_HashTable_List_Deque_Stack._1_List_Deque_Stack._2ElementsList.EList.ListItem;

public class Test_EList {
	// Расширяем под Integer
	public static class IntItem extends EList.ListItem{
		int num;
		public IntItem(int num) {
			this.num = num;
		}
	}
	// Расширяем под String
	public static class StrItem extends EList.ListItem{
		String str;
		StrItem(String str) {
			this.str = str;
		}
	}
	
	public static void main(String[] args) {
		EList ints = new EList();
		ints.add(new IntItem(2));
		ints.add(new IntItem(5));
		ints.add(new IntItem(3));
		IntItem current = (IntItem) ints.getHead();
		while(current != null) {
			System.out.println(current.num);
			current = (IntItem) current.getNext();
		}
		EList str = new EList();
		str.add(new StrItem("Two"));
		str.add(new StrItem("Five"));
		str.add(new StrItem("Three"));
		StrItem current2 = (StrItem) str.getHead();
		while(current2 != null) {
			System.out.println(current2.str);
			current2 = (StrItem) current2.getNext();
		}
	}
}
