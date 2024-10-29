package HomeWork.gc.HeapWithPrintedBlocks;

import java.util.*;

// Пользовательское исключение OutOfMemoryException
class OutOfMemoryException extends RuntimeException {
	public OutOfMemoryException(String message) {
		super(message);
	}
}

// Пользовательское исключение InvalidPointerException
class InvalidPointerException extends RuntimeException {
	public InvalidPointerException(String message) {
		super(message);
	}
}

// Класс Heap, имитирующий работу сборщика мусора
public class Heap {
	static byte[] bytes; // Массив, представляющий кучу
	private static List<FreeBlock> freeBlocks; // Список свободных блоков
	private static List<AllocatedBlock> allocatedBlocks; // Список выделенных блоков
	static boolean possibleCompact = true;
	
//	public boolean isPossibleCompact() {
//		return possibleCompact;
//	}
	
	//	private static Boolean printHeap = false;
//	private  static Boolean printBytes = false;
	//public Strategy strategy;
	// Конструктор класса Heap
	public Heap(int maxHeapSize) {
		this.bytes = new byte[maxHeapSize]; // Инициализация кучи заданного размера
		this.freeBlocks = new ArrayList<>();
		this.allocatedBlocks = new ArrayList<>();
		// Вначале вся куча является одним свободным блоком
		this.freeBlocks.add(new FreeBlock(0, maxHeapSize));
		Arrays.fill(this.bytes, (byte)-1);
		//fillBytes();
	}
	
	enum Strategy {
		SORT_BY_ADDRESS,
		SORT_BY_SIZE,
		SORT_BY_TWO_INDEXES,
		PERIODIC_DEFRAG,
		PERIODIC_COMPACT
	}
	
//	public void setPrintHeap(Boolean printHeap) {
//		if (printHeap) {
//			System.out.println("Heap:");
//			printHeap();
//		}
//		this.printHeap = printHeap;
//	}
//
//	public void setPrintBytes(Boolean printBytes) {
//		if (printBytes)
//			printBytes();
//		this.printBytes = printBytes;
//	}
	
	public static List<FreeBlock> getFreeBlocks() {
		return freeBlocks;
	}
	
	public static List<AllocatedBlock> getAllocatedBlocks() {
		return allocatedBlocks;
	}
	
	// Вспомогательный класс для хранения информации о свободном блоке
	private static class FreeBlock {
		int start; // Начало свободного блока
		int size;  // Размер свободного блока
		
		FreeBlock(int start, int size) {
			this.start = start;
			this.size = size;
		}
		
		@Override
		public String toString() {
			String block = " i=" + start + ", size=" + size + ":\n [ FB ]";
			for (int i = 0; i < size - 1; i++) {
				 block += "[ FB ]";
			}
			return block + "\n";
		}
	}
	
	// Вспомогательный класс для хранения информации о выделенном блоке
	static class AllocatedBlock {
		int start; // Начало выделенного блока
		int size;  // Размер выделенного блока
		
		AllocatedBlock(int start, int size) {
			this.start = start;
			this.size = size;
		}
		@Override
		public String toString() {
			String block = " i=" + start + ", size=" + size + ":\n [ AB ]";
			for(int i = 0;i < size - 1; i++) {
				block += "[ AB ]";
			}
			return block + "\n";
		}
	}
	
	/*
	Метод malloc ищет свободный блок, который можно выделить.
	Если подходящий блок найден, он помечается как занятый и добавляется в список выделенных блоков.
	Если блоков нужного размера нет, вызывается компактизация.
	*/
	public int malloc(int size) {
		// Флаг, указывающий на необходимость компактизации
		boolean toCompact = true;
		byte[] block = new byte[size];
		// Проверка свободных блоков на наличие блока подходящего размера
		// при наличии такого блока отменяем компактизацию
		for (FreeBlock freeBlock : freeBlocks) {
			if (freeBlock.size >= size) {
				toCompact = false;
				break;
			}
		}
		// При отсутствии блока нужного размера проводим компактизацию
		if (toCompact && possibleCompact) {
			compact();
			possibleCompact = false;
		}
		
		
		
		for (int i = 0; i < freeBlocks.size(); i++) {
			
			FreeBlock freeBlock = freeBlocks.get(i);
			if (freeBlock.size >= size) {
				// Создаем новый выделенный блок
				AllocatedBlock allocatedBlock = new AllocatedBlock(freeBlock.start, size);
				allocatedBlocks.add(allocatedBlock);
				Arrays.fill(block, (byte) freeBlock.start);
				setBytes(freeBlock.start, block);
				// Обновляем свободный блок: уменьшаем его или удаляем
				if (freeBlock.size == size) {
					freeBlocks.remove(i);
				} else {
					freeBlock.start += size;
					freeBlock.size -= size;
				}
				
				//fillBytes();
//				if (printHeap || printBytes)
//					System.out.println("Heap.malloc(" + size + ")");
//				if (printHeap)
//					printHeap();
//				if (printBytes)
//					printBytes();
				
				// Возвращаем индекс начала выделенного блока
				return allocatedBlock.start;
			}
		}
		throw new OutOfMemoryException("Недостаточно памяти для выделения блока размером " + size + " байт.");
	}
	
	/*
	Метод free освобождает блок памяти, проверяя, что указатель является началом ранее выделенного блока.
	*/
	public void free(int ptr) {
		//allocatedBlocks.sort(Comparator.comparingInt(block -> block.start));
		// Ищем выделенный блок по указателю
		//int i = 0;
		for (int i = 0; i < allocatedBlocks.size(); i++) {
			AllocatedBlock allocatedBlock = allocatedBlocks.get(i);
			if (allocatedBlock.start == ptr) {
				// Перемещаем блок в список свободных блоков
				freeBlocks.add(new FreeBlock(allocatedBlock.start, allocatedBlock.size));
				allocatedBlocks.remove(i);
				byte[] block = new byte[allocatedBlock.size];
				Arrays.fill(block, (byte)-1);
				setBytes(ptr, block);
				//fillBytes();
//				if (printHeap || printBytes)
//					System.out.println("Heap.free(" + ptr + ")");
//				if (printHeap)
//					printHeap();
//				if (printBytes)
//					printBytes();
				
				return;
			}
		}
		// Если указатель не найден или некорректен, выбрасываем исключение InvalidPointerException
		throw new InvalidPointerException("Указатель " + ptr + " некорректен.");
	}
	/*
	Метод defrag объединяет смежные свободные блоки.
	*/
	public void defrag() {
		// Сортируем свободные блоки по началу
		freeBlocks.sort((a, b) -> Integer.compare(a.start, b.start));
		
		// Объединяем смежные блоки
		for (int i = 0; i < freeBlocks.size() - 1; i++) {
			FreeBlock current = freeBlocks.get(i);
			FreeBlock next = freeBlocks.get(i + 1);
			
			// Если блоки соприкасаются, объединяем их
			if (current.start + current.size == next.start) {
				current.size += next.size;
				freeBlocks.remove(i + 1);
				i--; // Проверяем текущий блок еще раз с новым "следующим"
			}
		}
		//fillBytes();
//		if (printHeap || printBytes)
//			System.out.println("Heap.defrag()");
//		if (printHeap)
//			printHeap();
//		if (printBytes)
//			printBytes();
	}
	/*
	Метод compact сдвигает все занятые блоки к началу кучи и освобождает память после них.
	*/
	public void compact() {
		System.out.println("heap.compact");
		int destination = 0; // Текущая позиция для копирования данных
		
		// Сортируем выделенные блоки по их началу
		//if (allocatedBlocks.size() > 1)
		//allocatedBlocks.sort((a, b) -> Integer.compare(a.start, b.start));
		
		// Сдвигаем все занятые блоки к началу
		for (AllocatedBlock block : allocatedBlocks) {
			// Копируем данные блока по одному байту
			for (int i = 0; i < block.size; i++) {
				bytes[destination + i] = bytes[block.start + i];
			}
			block.start = destination; // Обновляем начало блока
			destination += block.size; // Смещаемся на следующий свободный адрес
		}
		
		// Обновляем список свободных блоков
		freeBlocks.clear();
		if (destination < bytes.length) {
			freeBlocks.add(new FreeBlock(destination, bytes.length - destination));
			byte[] block = new byte[bytes.length - destination];
			Arrays.fill(block, (byte)-1);
			setBytes(destination, block);
		}
		//fillBytes();
//		if (printHeap || printBytes)
//			System.out.println("Heap.compact()");
//		if (printHeap)
//			printHeap();
//		if (printBytes)
//			printBytes();
	}
	
//	public void fillBytes() {
//		int j = 0;
//		Arrays.fill(bytes, (byte)-1);
//		//freeBlocks.sort((a, b) -> Integer.compare(a.start, b.start));
//		allocatedBlocks.sort((a, b) -> Integer.compare(a.start, b.start));
//
//		for (int i = 0; i < bytes.length; i++) {
//			if (!allocatedBlocks.isEmpty() && j < allocatedBlocks.size()) {
//				AllocatedBlock allocBlock = allocatedBlocks.get(j);
//
//				if (allocBlock.start == i) {
//					while (i < allocBlock.start + allocBlock.size)
//						bytes[i++] = (byte)(allocBlock.start);
//					i = allocBlock.start + allocBlock.size - 1;
//					j++;
//				}
//			}
//		}
//	}
	
//	public void getHeap() {
//		int index = 0;
//		List<Object> heap = new ArrayList<>();
//		while (index < bytes.length) {
//			for (AllocatedBlock allocBlock : allocatedBlocks) {
//				if (allocBlock.start == index) {
//					heap.add(allocBlock);
//					index = allocBlock.start + allocBlock.size;
//				}
//			}
//			for (FreeBlock freeBlock : freeBlocks) {
//				if (freeBlock.start == index) {
//					heap.add(freeBlock);
//					index = freeBlock.start + freeBlock.size;
//				}
//			}
//		}
//		System.out.println("Heap list: " + heap);
//	}
	
	// Метод для получения данных из кучи по указателю
	public void getBytes(int ptr, byte[] bytes) {
		Arrays.fill(bytes, (byte)-1);
		if (ptr < 0 || ptr > bytes.length) {
			throw new InvalidPointerException("Invalid pointer or size.");
		}
		// Копируем данные из кучи в указанный массив
		System.arraycopy(this.bytes, ptr, bytes, 0, bytes.length);
	}
	
	// Метод для записи данных в кучу по указателю
	public void setBytes(int ptr, byte[] bytes) {
		if (ptr < 0 || ptr > this.bytes.length) {
			throw new InvalidPointerException("Invalid pointer or size.");
		}
		// Копируем данные из указанного массива в кучу
		System.arraycopy(bytes, 0, this.bytes, ptr, bytes.length);
	}
	
	// Метод для сортировки свободных блоков по адресам
	public int sortFreeBlocksByAddress(int size) {
		freeBlocks.sort(Comparator.comparingInt(block -> block.start));
		System.out.println("sortFreeBlocksByAddress");
		return malloc(size);
	}
	// Метод для сортировки свободных блоков по размеру
	public int sortFreeBlocksBySize(int size) {
		freeBlocks.sort(Comparator.comparingInt(block -> -block.size));
		System.out.println("sortFreeBlocksBySize");
		return malloc(size);
	}
	// Метод для поиска и выделения памяти с использованием двух индексов
//	public int sortFreeBlocksByTwoIndexes(int size, boolean reverseSortBySizeTwoIndex) {
//		System.out.println("sortFreeBlocksByTwoIndexes");
//		int ptr = -1;
//		byte[] block = new byte[size];
//		FreeBlock freeBlock;
//
//		for (int i = 0; i < freeBlocks.size(); i++) {
//			freeBlocks.sort(Comparator.comparingInt(b -> b.start));
//			System.out.println("Sort by start: " + freeBlocks);
//			freeBlock = freeBlocks.get(i);
//			if (freeBlock.size >= size) {
//				ptr = freeBlock.start;
//			} else {
//				if (reverseSortBySizeTwoIndex)
//					freeBlocks.sort(Comparator.comparingInt(b -> -b.size));
//				else
//					freeBlocks.sort(Comparator.comparingInt(b -> b.size));
//				System.out.println("Sort by size: " + freeBlocks);
//				freeBlock = freeBlocks.get(i);
//				if (freeBlock.size >= size) {
//					ptr = freeBlock.start;
//				} else {
//					if (reverseSortBySizeTwoIndex) {
//						compact();
//						possibleCompact = false;
//						i--;
//					}
//				}
//			}
//			if (ptr >= 0) {
//				AllocatedBlock allocatedBlock = new AllocatedBlock(ptr, size);
//				allocatedBlocks.add(allocatedBlock);
//				Arrays.fill(block, (byte) ptr);
//				setBytes(ptr, block);
//				if (freeBlock.size == size) {
//					freeBlocks.remove(i);
//				} else {
//					freeBlock.start += size;
//					freeBlock.size -= size;
//				}
//				return ptr;
//			}
//		}
//		throw new OutOfMemoryException("Недостаточно памяти для выделения блока размером " + size + " байт.");
//	}
	public int sortFreeBlocksByTwoIndexes(int size, boolean reverseSortBySizeTwoIndex) {
		boolean toCompact = true;
		int ptr = -1;
		//int count = 0;
		byte[] block = new byte[size];
		FreeBlock freeBlock = null;
		List<FreeBlock> blocksSortedBySize = new ArrayList<>(freeBlocks);
		
		freeBlocks.sort(Comparator.comparingInt(b -> b.start));
		System.out.println("Sorted by index: " + freeBlocks);
		int freeBlocksSize = freeBlocks.size();
		for (int i = 0; i < freeBlocksSize; i++) {
			freeBlock = freeBlocks.get(i);
			if (freeBlock.size >= size) {
				ptr = freeBlock.start;
			} else {
				blocksSortedBySize.sort(Comparator.comparingInt(b -> b.size));
				System.out.println("Sorted by size: " + blocksSortedBySize);
				if (blocksSortedBySize.get(freeBlocksSize - 1).size < size) {
					compact();
					System.out.println("After compact: " + freeBlocks);
					freeBlock = freeBlocks.get(0);
					ptr = freeBlock.start;
					possibleCompact = false;
					//i--;
				} else {
					freeBlock = blocksSortedBySize.get(i);
					if (freeBlock.size >= size) {
						freeBlocks = blocksSortedBySize;
						System.out.println("Sorted by index2: " + freeBlocks);
						ptr = freeBlock.start;
					}
				}
			}
		
			if (ptr >= 0) {
				AllocatedBlock allocatedBlock = new AllocatedBlock(ptr, size);
				allocatedBlocks.add(allocatedBlock);
				//Arrays.fill(block, (byte) ptr);
				setBytes(ptr, block);
				if (freeBlock.size == size) {
					freeBlocks.remove(i);
				} else {
					freeBlock.start += size;
					freeBlock.size -= size;
				}
				return ptr;
			}
		}
		throw new OutOfMemoryException("Недостаточно памяти для выделения блока размером " + size + " байт.");
	}
	
	public int periodicCompact(int size, boolean checkFree) {
		System.out.println("periodicCompact");
		if (checkFree) {
			compact();
			possibleCompact = false;
		}
		int ptr = malloc(size);
		return ptr;
	}
	
	public int periodicDefrag(int size, boolean checkFree) {
		System.out.println("periodicDefrag");
		if (checkFree) {
			defrag();
		}
		int ptr = malloc(size);
		return ptr;
	}
	
	public int getStrategy(int size, Strategy strategy, boolean checkFree) {
		switch (strategy) {
			case SORT_BY_SIZE:
				System.out.println("SORT_BY_SIZE");
				return sortFreeBlocksBySize(size);
			case SORT_BY_TWO_INDEXES:
				System.out.println("SORT_BY_TWO_INDEXES");
				return sortFreeBlocksByTwoIndexes(size, checkFree);
			case PERIODIC_COMPACT:
				System.out.println("PERIODIC_COMPACT");
				return periodicCompact(size, checkFree);
			case PERIODIC_DEFRAG:
				System.out.println("PERIODIC_DEFRAG");
				return periodicDefrag(size, checkFree);
			default:
				System.out.println("SORT_BY_ADDRESS");
				return sortFreeBlocksByAddress(size);
		}
	}
	
//	public void printHeap() {
//		String result = "";
//		int k = 0;
//		int j = 0;
//		//freeBlocks.sort((a, b) -> Integer.compare(a.start, b.start));
//		//allocatedBlocks.sort((a, b) -> Integer.compare(a.start, b.start));
//
//		for (int i = 0; i < bytes.length; i++) {
//
//			if (k < freeBlocks.size()) {
//				FreeBlock freeBlock = freeBlocks.get(k);
//				if (freeBlock.start == i) {
//					result += freeBlock.toString();
//					i = freeBlock.start + freeBlock.size;
//					k++;
//				}
//			}
//			if (!allocatedBlocks.isEmpty() && j < allocatedBlocks.size()) {
//				AllocatedBlock allocBlock = allocatedBlocks.get(j);
//				if (allocBlock.start == i) {
//					result += allocBlock.toString();
//					i = allocBlock.start + allocBlock.size;
//					j++;
//				}
//			}
//			i--;
//		}
//		System.out.println(result);
//	}
	
	public void printBytes() {
		System.out.println("Bytes:\n " + Arrays.toString(bytes) + "\n");
	}
	
	public static void main(String[] args) {
		Heap heap = new Heap(20);
		ArrayDeque<HeapTest.Block> blocks = new ArrayDeque<>();
		//heap.setPrintHeap(false);
		//heap.setPrintBytes(true);
		//System.out.println("Bytes: \n" + Arrays.toString(heap.bytes));
		heap.malloc(3);
		heap.malloc(2);
		heap.malloc(4);
		heap.malloc(2);
		heap.malloc(3);
		heap.malloc(3);
		heap.free(0);
		//System.out.println("Bytes: \n" + Arrays.toString(heap.bytes));
		heap.free(5);
		heap.free(14);
		//System.out.println(heap.freeBlocks);
		//heap.getHeap();
		//heap.defrag();
		//heap.malloc(5);
		//heap.compact();
		//heap.malloc(5);
		System.out.println("before: " + heap.allocatedBlocks);
		System.out.println("before: " + heap.freeBlocks);
		heap.sortFreeBlocksByTwoIndexes(13, true);
		//heap.compact();
		System.out.println("after: " + heap.allocatedBlocks);
		System.out.println("after: " + heap.freeBlocks);
		
//		heap.fillBytes();
		//System.out.println("Bytes: \n" + Arrays.toString(heap.bytes));
//		byte[] bytes1 = new byte[20];
//		heap.getBytes(0, bytes1);
//		//System.out.println("Bytes1: \n" + Arrays.toString(bytes1));
//
//		//heap.malloc(3);
//		//heap.compact();
////		System.out.println("Bytes1: \n" + Arrays.toString(bytes1));
//		heap.getBytes(0, bytes1);
		//System.out.println("Bytes: \n" + Arrays.toString(heap.bytes));
		//System.out.println("Bytes1: \n" + Arrays.toString(bytes1));
	}
}
