package Less04_HashTable_List_Deque_Stack._1_List_Deque_Stack._4OList_EList_BArray_TimeTest;

import Less04_HashTable_List_Deque_Stack._1_List_Deque_Stack._1ObjectsList.OList;
import Less04_HashTable_List_Deque_Stack._1_List_Deque_Stack._2ElementsList.EList;
import Less04_HashTable_List_Deque_Stack._1_List_Deque_Stack._2ElementsList.Test_EList;
import Less04_HashTable_List_Deque_Stack._1_List_Deque_Stack._3BlockArray.BArray;

public class TimeTest {
	static final int ITERATIONS = 10_000_000;
	
	static void testAdd() {
		long t1 = System.currentTimeMillis();
		BArray<Integer> ba = new BArray<>(5000000, 5000000);
		for(int i = 0; i < ITERATIONS; i++)
			ba.add(i);
		
		long t2 = System.currentTimeMillis();
		OList<Integer> ol = new OList<>();
		for(int i = 0; i < ITERATIONS; i++) {
			ol.add(i);
		}
		
		long t3 = System.currentTimeMillis();
		EList el = new EList();
		for(int i = 0; i < ITERATIONS; i++) {
			el.add(new Test_EList.IntItem(i));
		}
		long t4 = System.currentTimeMillis();
		System.out.println("---- Test Add ----");
		System.out.println("BArray: " + (t2 - t1));
		System.out.println("OList: " + (t3 - t2));
		System.out.println("EList: " + (t4 - t3));
		
		testGet(ba, ol, el);
	}
	
	static void testGet(BArray<Integer> ba, OList<Integer> ol, EList el) {
		long t1 = System.currentTimeMillis();
		for(int i = 0; i < ITERATIONS; i++)
			ba.get(i);
		
		long t2 = System.currentTimeMillis();
		OList<Integer>.ListItem<Integer> current = ol.getHead();
		while(current != null)
			current = current.getNext();
		
		long t3 = System.currentTimeMillis();
		Test_EList.IntItem current2 = (Test_EList.IntItem) el.getHead();
		while(current2 != null)
			current2 = (Test_EList.IntItem) current2.getNext();
		long t4 = System.currentTimeMillis();
		System.out.println("---- Test Get ----");
		System.out.println("BArray: " + (t2 - t1));
		System.out.println("OList: " + (t3 - t2));
		System.out.println("EList: " + (t4 - t3));
	}
	
	public static void main(String[] args) {
		testAdd();
	}
}

/*
---- Test Add ----
BArray: 1317
OList: 5224
EList: 2325
---- Test Get ----
BArray: 9
OList: 280
EList: 188
*/