package Less00_DinamicArrays._6QueueIntArray;

import java.util.Arrays;

public class QTestDraft {
	public static void main(String[] args) throws Exception {
		QIntArrayDraft q = new QIntArrayDraft(3);
		//System.out.println(Arrays.toString(q.array));
		q.offer(10);
		//System.out.println(Arrays.toString(q.array));
		q.offer(15);
		//System.out.println(Arrays.toString(q.array));
		q.offer(8);
		//System.out.println(Arrays.toString(q.array));
		q.offer(22);
		//System.out.println(Arrays.toString(q.array));
		q.offer(35);
		q.poll();
		q.poll();
		q.poll();
		q.poll();
		q.poll();
		q.offer(45);
		q.offer(55);
		q.poll();

//		System.out.println(Arrays.toString(q.array));
//		System.out.println(q.poll());
//		System.out.println(Arrays.toString(q.array));
//		System.out.println(q.poll());
//		System.out.println(Arrays.toString(q.array));
//		System.out.println(q.poll());
//		System.out.println(Arrays.toString(q.array));
//		System.out.println(q.poll());
//		System.out.println(Arrays.toString(q.array));
//		System.out.println(q.poll());
//		q.offer(45);
//		q.offer(55);
//		System.out.println(Arrays.toString(q.array));
//		System.out.println(q.poll());
//		System.out.println(Arrays.toString(q.array));
		
//		int tail = 0;
//		int[] arr = {1, 2, 3, 4, 5, 6};
//		int[] copy = new int[arr.length + 1];
//		System.out.println("0-arr: " + Arrays.toString(arr));
//		System.out.println("0-copy: " + Arrays.toString(copy));
////		arr = Arrays.copyOf(arr, arr.length + 1);
////		System.out.println("1-arr: " + Arrays.toString(arr));
//		System.arraycopy(arr, 0, copy, tail + 1, arr.length);
//		System.out.println("2-arr: " + Arrays.toString(arr));
//		System.out.println("2-copy: " + Arrays.toString(copy));
//		arr = Arrays.copyOf(copy, arr.length + 1);
//		System.out.println("3-arr: " + Arrays.toString(arr));
//		System.out.println("3-copy: " + Arrays.toString(copy));
	}
}
