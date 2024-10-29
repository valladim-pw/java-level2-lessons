package HomeWork.gc.HeapSimple_HeapTestSource;


import java.util.ArrayList;
import java.util.List;

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
	private byte[] bytes; // Массив, представляющий кучу
	private List<FreeBlock> freeBlocks; // Список свободных блоков
	private List<AllocatedBlock> allocatedBlocks; // Список выделенных блоков
	
	// Конструктор класса Heap
	public Heap(int maxHeapSize) {
		this.bytes = new byte[maxHeapSize]; // Инициализация кучи заданного размера
		this.freeBlocks = new ArrayList<>();
		this.allocatedBlocks = new ArrayList<>();
		// Вначале вся куча является одним свободным блоком
		this.freeBlocks.add(new FreeBlock(0, maxHeapSize));
	}
	
	// Вспомогательный класс для хранения информации о свободном блоке
	private static class FreeBlock {
		int start; // Начало свободного блока
		int size;  // Размер свободного блока
		
		FreeBlock(int start, int size) {
			this.start = start;
			this.size = size;
		}
	
	}
	
	// Вспомогательный класс для хранения информации о выделенном блоке
	private static class AllocatedBlock {
		int start; // Начало выделенного блока
		int size;  // Размер выделенного блока
		
		AllocatedBlock(int start, int size) {
			this.start = start;
			this.size = size;
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
		// Проверка свободных блоков на наличие блока подходящего размера
		// при наличии такого блока отменяем компактизацию
		for (FreeBlock freeBlock : freeBlocks) {
			if (freeBlock.size >= size)
				toCompact = false;
		}
		// При отсутствии блока нужного размера проводим компактизацию
		if (toCompact)
			compact();
		
		for (int i = 0; i < freeBlocks.size(); i++) {
			
			FreeBlock freeBlock = freeBlocks.get(i);
			if (freeBlock.size >= size) {
				// Создаем новый выделенный блок
				AllocatedBlock allocatedBlock = new AllocatedBlock(freeBlock.start, size);
				allocatedBlocks.add(allocatedBlock);
				
				// Обновляем свободный блок: уменьшаем его или удаляем
				if (freeBlock.size == size) {
					freeBlocks.remove(i);
				} else {
					freeBlock.start += size;
					freeBlock.size -= size;
				}
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
		// Ищем выделенный блок по указателю
		for (int i = 0; i < allocatedBlocks.size(); i++) {
			AllocatedBlock allocatedBlock = allocatedBlocks.get(i);
			if (allocatedBlock.start == ptr) {
				// Перемещаем блок в список свободных блоков
				freeBlocks.add(new FreeBlock(allocatedBlock.start, allocatedBlock.size));
				allocatedBlocks.remove(i);
				
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
	}
	/*
	Метод compact сдвигает все занятые блоки к началу кучи и освобождает память после них.
	*/
	public void compact() {
		int destination = 0; // Текущая позиция для копирования данных
		
		// Сортируем выделенные блоки по их началу
		if (allocatedBlocks.size() > 1)
			allocatedBlocks.sort((a, b) -> Integer.compare(a.start, b.start));
		
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
		}
	}
}
