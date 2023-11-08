package Less04_HashTable_List_Deque_Stack._3_BinaryHeap;

import java.util.Arrays;

public class BArray<T> {
	
	T[] array;
	int size;
	int block;
	
	public BArray(int initSize, int blockSize) {
		size = 0;
		array = (T[])new Object[initSize];
		block = blockSize;
	}
	
	void copyData(T[] src, T[] dst) {
		for(int i = 0; i < src.length; i++) {
			dst[i] = src[i];
		}
	}
	
	public T get(int index)	{
		return array[index];
	}
	
	public int size() {
		return size;
	}
	
	public void add(T item) {
		if(size == array.length) {
			T[] newArray = (T[])new Object[array.length + block];
			copyData(array, newArray);
			newArray[array.length] = item;
			array = newArray;
		}
		array[size++] = item;
		//System.out.println(Arrays.toString(array));
	}
	
	@Override
	public String toString() {
		return Arrays.toString(array);
	}
	
	public static void main(String[] args) {
		BArray<Integer> ba = new BArray(0, 2);
		System.out.println("1-----------------------------------");
		ba.add(9);
		System.out.println("2-----------------------------------");
		ba.add(5);
		System.out.println("3-----------------------------------");
		ba.add(2);
		System.out.println("---------------------------------------------------");
		BArray<String> ba2 = new BArray(0, 2);
		System.out.println("1-----------------------------------");
		ba2.add("Nine");
		System.out.println("2-----------------------------------");
		ba2.add("Five");
		System.out.println("3-----------------------------------");
		ba2.add("Two");
	}
}



