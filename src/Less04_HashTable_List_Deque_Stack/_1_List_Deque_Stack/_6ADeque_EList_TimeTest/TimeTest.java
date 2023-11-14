package Less04_HashTable_List_Deque_Stack._1_List_Deque_Stack._6ADeque_EList_TimeTest;

import Less04_HashTable_List_Deque_Stack._1_List_Deque_Stack._2ElementsList.EList;
import Less04_HashTable_List_Deque_Stack._1_List_Deque_Stack._2ElementsList.Test_EList;

import java.util.ArrayDeque;

public class TimeTest {
	static final int ITERATIONS = 10_000_000;
	
	static void testAdd() {
		long t1 = System.currentTimeMillis();
		ArrayDeque<Integer> ad = new ArrayDeque<>();
		for(int i = 0; i < ITERATIONS; i++)
			ad.offer(i);
		long t2 = System.currentTimeMillis();
		EList el = new EList();
		for(int i = 0; i < ITERATIONS; i++) {
			el.add(new Test_EList.IntItem(i));
		}
		long t3 = System.currentTimeMillis();
		
		System.out.println("---- Test Add ----");
		System.out.println("ArrayDeque: " + (t2 - t1));
		System.out.println("EList: " + (t3 - t2));
		
		testGet(ad, el);
	}
	
	static void testGet(ArrayDeque<Integer> ad, EList el) {
		long t1 = System.currentTimeMillis();
		for(int i = 0; i < ITERATIONS; i++)
			ad.poll();
		long t2 = System.currentTimeMillis();
		Test_EList.IntItem current = (Test_EList.IntItem) el.getHead();
		while(current != null)
			current = (Test_EList.IntItem) current.getNext();
		long t3 = System.currentTimeMillis();
		
		System.out.println("---- Test Get ----");
		System.out.println("ArrayDeque: " + (t2 - t1));
		System.out.println("EList: " + (t3 - t2));
	}
	
	public static void main(String[] args) {
		testAdd();
	}
}

/*

*/