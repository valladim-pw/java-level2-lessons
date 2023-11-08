package Less00_DinamicArrays._6QueueIntArray;

import java.util.Arrays;

public class QIntArrayDraft {
	int[] array;
	int head;
	int tail;
	int size;
	boolean reset;
	
	public QIntArrayDraft(int size) {
		array = new int[size];
		this.size = size;
		head = tail = 0;
	}
	
	public void offer(int item) {
//		System.out.println("0-offer-tail: " + tail);
//		System.out.println("0-offer-head: " + head);
		System.out.println("tail: " + tail);
		System.out.println("offer-item: " + item);
		
		array[tail++] = item;
		System.out.println("offer-arr: " + Arrays.toString(array));
		if(tail == size) {
			tail = 0;
			reset = true;
		}
//		System.out.println("1-offer-tail: " + tail);
//		System.out.println("1-offer-head: " + head);
		if(reset) {
			
			
			int	arrLength = array.length + 1;
			int[] copyArray = new int[arrLength];
			//System.out.println("0-arrCopy: " + Arrays.toString(copyArray));
			array = Arrays.copyOf(array, copyArray.length);
			//System.out.println("1-arr: " + Arrays.toString(array));
			//System.out.println("1-arrCopy: " + Arrays.toString(copyArray));
			System.arraycopy(array, tail, copyArray, tail + 1, arrLength - tail - 1);
			//System.out.println("2-arr: " + Arrays.toString(array));
			//System.out.println("2-arrCopy: " + Arrays.toString(copyArray));
			System.arraycopy(copyArray, tail, array, tail, copyArray.length - tail);
			System.out.println("expand-arr: " + Arrays.toString(array));
			//System.out.println("3-arrCopy: " + Arrays.toString(copyArray));
			
			//head++;
		}
	}
	
	public int poll() throws Exception {
//		if(head == 0){
//			int[] copyArray = new int[array.length];
//			System.arraycopy(array, head + 1, copyArray, head, array.length - 1);
//			System.arraycopy(copyArray, head, array, head, copyArray.length);
//			array  = Arrays. copyOf(array, copyArray.length - 1);
//		} else {
//			int[] copyArray = new int[array.length - 1];
//			System.arraycopy(array, head, copyArray, head - 1, array.length - head);
//			System.arraycopy(copyArray, head, array, head, copyArray.length - head);
//			array  = Arrays. copyOf(array, copyArray.length);
//		}
		if(head == tail)
			throw new Exception("no data");
//		System.out.println("poll-tail: " + tail);
//		System.out.println("poll-head: " + head);
		if(head == array.length)
			head = 0;
		System.out.println("head: " + head);
		System.out.println("poll-arr: " + Arrays.toString(array));
		System.out.println("poll-item: " + array[head]);
		return array[head++];
	}
}
