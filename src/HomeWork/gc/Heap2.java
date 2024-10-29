package HomeWork.gc;

import java.util.ArrayList;
import java.util.List;

class OutOfMemoryException2 extends RuntimeException {
	public OutOfMemoryException2(String message) {
		super(message);
	}
}

class InvalidPointerException2 extends RuntimeException {
	public InvalidPointerException2(String message) {
		super(message);
	}
}

public class Heap2 {
	private byte[] bytes;
	private List<FreeBlock> freeBlocks;
	private List<AllocatedBlock> allocatedBlocks;
	
	public Heap2(int maxHeapSize) {
		this.bytes = new byte[maxHeapSize];
		this.freeBlocks = new ArrayList<>();
		this.allocatedBlocks = new ArrayList<>();
		this.freeBlocks.add(new FreeBlock(0, maxHeapSize));
	}
	
	private static class FreeBlock {
		int start;
		int size;
		
		FreeBlock(int start, int size) {
			this.start = start;
			this.size = size;
		}
	}
	
	private static class AllocatedBlock {
		int start;
		int size;
		
		AllocatedBlock(int start, int size) {
			this.start = start;
			this.size = size;
		}
	}
	
	public int malloc(int size) {
		for (int i = 0; i < freeBlocks.size(); i++) {
			FreeBlock freeBlock = freeBlocks.get(i);
			if (freeBlock.size >= size) {
				AllocatedBlock allocatedBlock = new AllocatedBlock(freeBlock.start, size);
				allocatedBlocks.add(allocatedBlock);
				if (freeBlock.size == size) {
					freeBlocks.remove(i);
				} else {
					freeBlock.start += size;
					freeBlock.size -= size;
				}
				return allocatedBlock.start;
			}
		}
		
		compact();
		
		for (int i = 0; i < freeBlocks.size(); i++) {
			FreeBlock freeBlock = freeBlocks.get(i);
			if (freeBlock.size >= size) {
				AllocatedBlock allocatedBlock = new AllocatedBlock(freeBlock.start, size);
				allocatedBlocks.add(allocatedBlock);
				if (freeBlock.size == size) {
					freeBlocks.remove(i);
				} else {
					freeBlock.start += size;
					freeBlock.size -= size;
				}
				return allocatedBlock.start;
			}
		}
		
		throw new OutOfMemoryException2("Недостаточно памяти для выделения блока размером " + size + " байт.");
	}
	
	public void free(int ptr) {
		for (int i = 0; i < allocatedBlocks.size(); i++) {
			AllocatedBlock allocatedBlock = allocatedBlocks.get(i);
			if (allocatedBlock.start == ptr) {
				freeBlocks.add(new FreeBlock(allocatedBlock.start, allocatedBlock.size));
				allocatedBlocks.remove(i);
				return;
			}
		}
		throw new InvalidPointerException2("Указатель " + ptr + " некорректен.");
	}
	
	public void defrag() {
		freeBlocks.sort((a, b) -> Integer.compare(a.start, b.start));
		
		for (int i = 0; i < freeBlocks.size() - 1; i++) {
			FreeBlock current = freeBlocks.get(i);
			FreeBlock next = freeBlocks.get(i + 1);
			
			if (current.start + current.size == next.start) {
				current.size += next.size;
				freeBlocks.remove(i + 1);
				i--;
			}
		}
	}
	
	public void compact() {
		int destination = 0;
		
		allocatedBlocks.sort((a, b) -> Integer.compare(a.start, b.start));
		
		for (AllocatedBlock block : allocatedBlocks) {
			for (int i = 0; i < block.size; i++) {
				bytes[destination + i] = bytes[block.start + i];
			}
			block.start = destination;
			destination += block.size;
		}
		
		freeBlocks.clear();
		if (destination < bytes.length) {
			freeBlocks.add(new FreeBlock(destination, bytes.length - destination));
		}
	}
}
