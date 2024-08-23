package Less07_TypeOfLinks_GarbageCollector._1_GarbageCollector;

public class FiboWithRecursion {
	int prev;
	int current;
	public FiboWithRecursion(int prev, int current) {
		this.prev = prev;
		this.current = current;
	}
	
	FiboWithRecursion next() {
		return new FiboWithRecursion(current, prev + current);
	}
	
	@Override
	public String toString() {
		return String.valueOf(current);
	}
	
	public static FiboWithRecursion getFibo(int num) {
		switch (num) {
			case 0: return new FiboWithRecursion(0, 1);
			case 1: return new FiboWithRecursion(1, 1);
		}
		FiboWithRecursion preFibo = getFibo(num - 1);
		return preFibo.next();
	}
	
	public static void main(String[] args) {
		MemoryUtil memoryUtil = new MemoryUtil();
		memoryUtil.startGCMonitor();
		for (int i = 0; i < 10000; i++) {
			FiboWithRecursion fwr = FiboWithRecursion.getFibo(i);
			System.out.println(fwr);
			memoryUtil.printUsage(false);		}
		
		memoryUtil.stopGCMonitor();
	}
}
