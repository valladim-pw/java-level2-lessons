package Less00_DinamicArrays._6QueueIntArray;

import java.util.Arrays;

public class QIntArray {
	int[] array;
	int head;
	int tail;
	
	public QIntArray(int size) {
		array = new int[size];
		head = tail = 0;
	}
	
	public void offer(int item) {
		if(tail == array.length)
			tail = 0;
		// Если хвост массива догнал голову, то массив расширяется
		// в данном случае копируется с увеличенной на 1 длиной
		if(tail == head)
			array = Arrays.copyOf(array, array.length + 1);
		array[tail++] = item;
		System.out.println("arr: " + Arrays.toString(array));
	}
	
	public int poll() throws Exception {
		if(head == tail)
			throw new Exception("no data");
		return array[head++];
	}
}
