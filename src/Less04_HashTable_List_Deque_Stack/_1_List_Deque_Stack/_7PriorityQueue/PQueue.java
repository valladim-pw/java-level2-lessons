package Less04_HashTable_List_Deque_Stack._1_List_Deque_Stack._7PriorityQueue;

import java.util.ArrayDeque;
import java.util.Arrays;

public class PQueue<E> {
	private ArrayDeque<E>[] queue;
	
	public PQueue(int priority) {
		queue = new ArrayDeque[priority];
		for(int i = 0; i < priority; i++)
			queue[i] = new ArrayDeque<>();
	}
	
	public void offer(int priority, E element) {
		queue[priority].add(element);
	}
	
	public E poll() {
		for(int i = 0; i < queue.length; i++) {
			if(queue[i].size() > 0)
				return queue[i].poll();
		}
		return null;
	}
	
	public int size() {
		int size = 0;
		for(int i = 0; i < queue.length; i++)
			size += queue[i].size();
		return size;
	}
	
	@Override
	public String toString() {
		return Arrays.toString(queue);
	}
}
