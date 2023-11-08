package Less04_HashTable_List_Deque_Stack._2_HashTables._2ChainHashTable;

import java.util.ArrayList;
import java.util.Arrays;

public class PageArray<T> {
	
	ArrayList<T[]> array; // Список с массивами (страницами)
	int size; // Заполненность данными последней страницы списка
	int block; // Размер страницы
	T[] page; // Страница массива
	
	public PageArray(int blockSize) {
		array = new ArrayList<>();
		size = 0;
		block = blockSize;
		page = (T[])new Object[block]; // Создаем страницу
		array.add(page); // Добавляем страницу в список
	}
	public T get(int index) {
		int index1 = index / block;
		int index2 = index % block;
		return array.get(index1)[index2];
	}
	public void add(T item) {
		if (size == page.length) {
			page = (T[])new Object[block]; // Создаем страницу
			array.add(page); // Добавляем страницу в список
			size = 0; // Если страница заполнена заполненность приводим к 0
		}
		page[size++] = item; // Добавляем элемент на страницу по инкременту заполненности страницы
	}
	public int size() {
		return ((array.size() - 1) * block) + size; // Возвращаем заполненность (последний индекс) последней странцы
	}
	public void print() {
		ArrayList<String> list = new ArrayList<>();
		for (T[] page : array) {
			list.add(Arrays.toString(page));
		}
		System.out.println(list);
	}
	
	public static void main(String[] args) {
		PageArray pa = new PageArray(3);
		pa.print();
		pa.add(5);
		pa.add(4);
		pa.add(9);
		pa.add(7);
		pa.print();
		System.out.println(pa.get(3));
		System.out.println(pa.size());
		//pa.get(4);
		//pa.size();
	}
}
