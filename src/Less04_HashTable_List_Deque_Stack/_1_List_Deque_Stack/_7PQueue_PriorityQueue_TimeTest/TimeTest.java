package Less04_HashTable_List_Deque_Stack._1_List_Deque_Stack._7PQueue_PriorityQueue_TimeTest;

import Less04_HashTable_List_Deque_Stack._1_List_Deque_Stack._6PriorityQueue.PQueue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class TimeTest {
	static final int MILLION = 1000000;
	static final int ITERATIONS = MILLION * 10;
	static final int SHIFT = MILLION * 100;
	static final int PRIORITY = 5;
	
	static class QElement {
		static int number;
		public Integer priority;
		public Integer num;
		
		public QElement(int priority, int num) {
			this.priority = Integer.valueOf(priority * SHIFT + number++);
			this.num = num;
		}
	}
	public static final int randomInterval(int min, int max) {
		return (int)((Math.random() * (max - min)) + min);
	}
	
	public static void main(String[] args) {
		long t1 = System.currentTimeMillis();
		Comparator<QElement> comparator = new Comparator<>() {
			@Override
			public int compare(QElement o1, QElement o2) {
				return o2.priority.compareTo(o1.priority);
			}
		};
		PriorityQueue<QElement> q1 = new PriorityQueue<>(comparator);
		for(int i = 0; i < ITERATIONS; i++) {
			q1.offer(new QElement(randomInterval(0, PRIORITY - 1), randomInterval(0, MILLION)));
		}
		long t2 = System.currentTimeMillis();
		PQueue<Integer> q2 = new PQueue<>(PRIORITY);
		for(int i = 0; i < ITERATIONS; i++) {
			q2.offer(randomInterval(0,PRIORITY), randomInterval(0, MILLION));
		}
		long t3 = System.currentTimeMillis();
		System.out.println("---- Test Offer ----");
		System.out.println("offer q1 = " + (t2 - t1));
		System.out.println("offer q2  = " + (t3 - t2));
		
		t1 = System.currentTimeMillis();
		while(q1.size() > 0)
			q1.poll();
		t2 = System.currentTimeMillis();
		while(q2.size() > 0)
			q2.poll();
		t3 = System.currentTimeMillis();
		System.out.println("---- Test Poll ----");
		System.out.println("poll q1 = " + (t2 - t1));
		System.out.println("poll q2  = " + (t3 - t2));
	}
}
