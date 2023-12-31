package Less04_HashTable_List_Deque_Stack._1_List_Deque_Stack._1ObjectsList;

public class Test_OList {
	public static void main(String[] args) {
		OList<Integer> list = new OList<>();
		list.add(2);
		list.add(3);
		list.add(5);
		OList<Integer>.ListItem<Integer> current = list.getHead();
		String link = current.toString();
		link = link.substring(link.indexOf("$") + 1);
		System.out.println("Ссылка на объект (Integer) в голове списка: " + link);
		while(current != null) {
			String link2 = current.toString();
			link2 = link2.substring(link2.indexOf("$") + 1);
			System.out.println("Ссылка на объект, добавленный в список: " + link2);
			System.out.println("Данные объекта: " + current.getItem());
			current = current.getNext();
		}
		System.out.println("---------------------------------------------");
		OList<String> list2 = new OList<>();
		list2.add("Two");
		list2.add("Three");
		list2.add("Five");
		OList<String>.ListItem<String> current2 = list2.getHead();
		String link3 = current2.toString();
		link3 = link3.substring(link3.indexOf("$") + 1);
		System.out.println("Ссылка на объект (String) в голове списка: " + link3);
		while(current2 != null) {
			String link4 = current2.toString();
			link4 = link4.substring(link4.indexOf("$") + 1);
			System.out.println("Ссылка на объект, добавленный в список: " + link4);
			System.out.println("Данные объекта: " + current2.getItem());
			current2 = current2.getNext();
		}
	}
}
