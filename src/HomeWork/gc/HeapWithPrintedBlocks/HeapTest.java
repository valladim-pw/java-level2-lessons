package HomeWork.gc.HeapWithPrintedBlocks;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;


public class HeapTest {
	static final int MAX_ITERATIONS = 3;
	static final int maxSize = 1000;//1932735283;//1024*1024*1024 * 1.8 = 1.8GB
	static final int maxSmall = 3;//10;
	static final int maxMedium = 5;//100;
	static final int maxBig = 10;//1000;
	static final int maxHuge = 100;//10000;
	static int allocated = 0;
	static boolean doneFree = false;
	static int isNul = 0;
	
	static class Block {
		public int start;
		public int size;
		
		public Block(int start, int size) {
			this.start = start;
			this.size = size;
		}
		
		@Override
		public String toString() {
			String block = " i=" + start + ", size=" + size + ":\n [ BB ]";
			for (int i = 0; i < size - 1; i++) {
				block += "[ BB ]";
			}
			return block + "\n";
		}
	}
	
	static class TestResult {
		String strategyName;
		long averageTime;
		long averageTime2;
		long allocTime;
		long freeTime;
		
		public TestResult(String strategyName, long averageTime, long averageTime2, long allocTime, long freeTime) {
			this.strategyName = strategyName;
			this.averageTime = averageTime;
			this.averageTime2 = averageTime2;
			this.allocTime = allocTime;
			this.freeTime = freeTime;
		}
	}
	
	static int getRandomSize() {
		int n = Math.abs(ThreadLocalRandom.current().nextInt() % 10);
		int size = Math.abs(ThreadLocalRandom.current().nextInt());
		int res = 0;
		if (n < 6)
			size %= maxSmall;
		else if (n < 8)
			size %= maxMedium;
		else if (n < 9)
			size %= maxBig;
		else
			size %= maxHuge;
		return size > (maxSize - allocated) - 1 ? (maxSize - allocated) / 2 + 1 : size + 1;
	}
	
	// Тестовая оболочка для стратегии
	private static TestResult testStrategy(String name, Heap.Strategy strategy) {
		long totalTime = 0;
		long totalTime2 = 0;
		long fullAllocTime = 0;
		long fullFreeTime = 0;
		
		System.out.println("---------------------------" + name);
		for (int i = 0; i < MAX_ITERATIONS; i++) {
			System.out.println("Test iteration: " + (i + 1));
			long startTime = System.nanoTime();
			long timeParams[] = performMemoryOperations(strategy);
			long endTime = System.nanoTime();
			totalTime += endTime - startTime;
			totalTime2 += timeParams[0];
			fullAllocTime += timeParams[1];
			fullFreeTime += timeParams[2];
			System.out.println("External total time: " + totalTime + " ns");
			System.out.println("Internal total time: " + timeParams[0] + " ns");
			System.out.println("Internal allocate time: " + timeParams[1] + " ns");
			System.out.println("Internal free time: " + timeParams[2] + " ns");
		}
		
		long averageTime = TimeUnit.NANOSECONDS.toMillis(totalTime / MAX_ITERATIONS);
		long averageTime2 = TimeUnit.NANOSECONDS.toMillis(totalTime2 / MAX_ITERATIONS);
		long averageAllocTime = TimeUnit.NANOSECONDS.toMillis(fullAllocTime / MAX_ITERATIONS);
		long averageFreeTime = TimeUnit.NANOSECONDS.toMillis(fullFreeTime / MAX_ITERATIONS);
		return new TestResult(name, averageTime, averageTime2, averageAllocTime, averageFreeTime);
	}
	
	// Стратегия: Сортировка по адресам
	
//
//	// Стратегия: Сортировка по размеру
//	private static void testSortBySize() {
//		Heap heap = new Heap(maxSize);
//		//heap.setPrintHeap(true);
//		heap.sortFreeBlocksBySize(); // Тестируем сортировку по размеру
//		performMemoryOperations(heap);
//	}
	
	static void compactDeque(Collection<Block> blocks) {
		System.out.println("compactDeque");
		int destination = 0; // Текущая позиция для копирования данных
		
		// Сдвигаем все занятые блоки к началу
		for (Block block : blocks) {
			// Копируем данные блока по одному байту
			for (int i = 0; i < block.size; i++) {
				Heap.bytes[destination + i] = Heap.bytes[block.start + i];
			}
			block.start = destination; // Обновляем начало блока
			destination += block.size; // Смещаемся на следующий свободный адрес
		}
		Heap.possibleCompact = true;
	}
	
	// Вспомогательный метод для выполнения операций с памятью
	private static long[] performMemoryOperations(Heap.Strategy strategy) {
		long timeParams[] = new long[3];
		System.out.println("Perform Memory Operations");
		String result = "";
		Heap heap = new Heap(maxSize);
		allocated = 0;
		
		ArrayDeque<Block> blocks = new ArrayDeque<>();
		
		int count = 0;
		long allocTime = 0;
		long freeTime = 0;
		//System.out.println("maxSize - allocated: " + (maxSize - allocated) );
		long start = System.nanoTime();
		// alloc and free 30% random
		while ((maxSize - allocated) > 3) { //50000
			long lstart, lstop;
			System.out.println("count: " + (count + 1));
			int size = getRandomSize();
			System.out.println("block size: " + size);
			allocated += size;
			System.out.println("allocated: " + allocated);
			count++;
			
			lstart = System.nanoTime();
			int ptr = heap.getStrategy(size, strategy, doneFree);
			lstop = System.nanoTime();
			System.out.println("possibleCompact1: " + Heap.possibleCompact);
			allocTime += lstop - lstart;
			if (!Heap.possibleCompact)
				compactDeque(blocks);
			System.out.println("possibleCompact2: " + Heap.possibleCompact);
			blocks.offer(new Block(ptr, size));
		 
			System.out.println("Blocks: \n" + blocks);
			System.out.println("allocBloks1:\n " + heap.getAllocatedBlocks() );
			System.out.println("freeBloks1: \n" + heap.getFreeBlocks() );
			heap.printBytes();

			int n = Math.abs(ThreadLocalRandom.current().nextInt() % 25);
			System.out.println("n1 = ThreadLocalRandom.current().nextInt() % 25: " + n);
			if (n == 0) {
				//n = Math.abs(ThreadLocalRandom.current().nextInt()%blocks.size());
				isNul++;
				doneFree = true;
				for (int i = 0; i < 5; i++) {
					System.out.println("-----------------------------------Before Free");
					System.out.println("Bloks2:\n " + blocks);
					System.out.println("allocBloks2:\n " + heap.getAllocatedBlocks() );
					System.out.println("freeBloks2: \n" + heap.getFreeBlocks() );
					heap.printBytes();
					Block block = blocks.poll();
					
					if (block == null)
						break;
					else
						System.out.println("Block ptr: " + block.start);
					lstart = System.nanoTime();
					heap.free(block.start);
					
					lstop = System.nanoTime();
					System.out.println("********************************After Free");
					System.out.println("Bloks2:\n " + blocks);
					System.out.println("allocBloks2:\n " + heap.getAllocatedBlocks() );
					System.out.println("freeBloks2: \n" + heap.getFreeBlocks() );
					heap.printBytes();
					freeTime += lstop - lstart;
					allocated -= block.size;
				}
				//blocks.remove(n);
			}
			n = Math.abs(ThreadLocalRandom.current().nextInt() % 100000);
			//System.out.println("n2 = ThreadLocalRandom.current().nextInt() % 10: " + n);
			if (n == 0)
				System.out.println(maxSize - allocated);
		}
		long stop = System.nanoTime();
		long totalTime = stop - start;
	//	result += "total time: " + (stop - start) + ", count: " + count;
	//	result += " (malloc time: " + allocTime + ", free time: " + freeTime + ")";
//		long fullAllocTime = TimeUnit.NANOSECONDS.toMillis(allocTime);
//		long fullFreeTime = TimeUnit.NANOSECONDS.toMillis(freeTime);
//		long totalTime = TimeUnit.NANOSECONDS.toMillis(stop - start);
		timeParams[0] = totalTime;
		timeParams[1] = allocTime;
		timeParams[2] = freeTime;
		return timeParams;
	}
	
	public static void main(String[] args) throws InvalidPointerException, OutOfMemoryException {
		List<TestResult> results = new ArrayList<>();
		
		// Тестируем каждую стратегию
		results.add(testStrategy("Sort by Address", Heap.Strategy.SORT_BY_ADDRESS));
		results.add(testStrategy("Sort by Size", Heap.Strategy.SORT_BY_SIZE));
		results.add(testStrategy("Sort by Two Indexes", Heap.Strategy.SORT_BY_TWO_INDEXES));
		results.add(testStrategy("Compact Periodically", Heap.Strategy.PERIODIC_COMPACT));
		results.add(testStrategy("Defrag Periodically", Heap.Strategy.PERIODIC_DEFRAG));
		
		// Сортируем результаты по среднему времени выполнения
		results.sort((r1, r2) -> Long.compare(r1.averageTime, r2.averageTime));
		
		// Выводим лидерборд
		System.out.println("Performance Leaderboard:");
		for (int i = 0; i < results.size(); i++) {
			System.out.printf("%d. %s - Average Time: %d ms, Average Time2: %d ms, Allocate Time: %d ms, Free Time: %d  ms%n",
							i + 1,
							results.get(i).strategyName,
							results.get(i).averageTime,
							results.get(i).averageTime2,
							results.get(i).allocTime,
							results.get(i).freeTime
							);
		}
		System.out.println("n = 0: " + isNul);
	}
}