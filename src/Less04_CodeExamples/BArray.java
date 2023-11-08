package Less04_CodeExamples;

import java.util.Arrays;

public class BArray<T> {
	
	T[] array;
	int size;
	int block;
	
	public BArray(int initSize, int blockSize) {
		size = 0;
		array = (T[]) new Object[initSize];
		block = blockSize;
	}
	
	public T get(int index) {
		return array[index];
	}
	
	public int size() {
		return size;
	}
	
	public void add(T item) {
		if(size == array.length) {
			T[] copyArray = (T[])new Object[size() + block];
			array = Arrays.copyOf(array, copyArray.length);
		}
		array[size++] = item;
	}
	
	@Override
	public String toString() {
		return Arrays.toString(array) ;
	}

	public static void main(String[] args) {
		BArray ba = new BArray(0, 1);
		System.out.println("1-----------------------------------");
		ba.add(9);
		System.out.println(ba.toString());
		System.out.println("2-----------------------------------");
		ba.add(5);
		System.out.println(ba.toString());
		System.out.println("3-----------------------------------");
		ba.add(2);
		System.out.println(ba.toString());
	}
}



