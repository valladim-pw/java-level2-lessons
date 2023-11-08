package Less00_DinamicArrays._6QueueIntArray;

import java.util.Arrays;

public class QTest {
	public static void main(String[] args) throws Exception {
		QIntArray q = new QIntArray(5);
		q.offer(10);
		q.offer(15);
		q.offer(8);
		q.offer(22);
		q.offer(35);
		System.out.println(q.poll());
		System.out.println(q.poll());
		System.out.println(q.poll());
		System.out.println(q.poll());
		System.out.println(q.poll());
		q.offer(45);
		System.out.println(q.poll());
	}
}
