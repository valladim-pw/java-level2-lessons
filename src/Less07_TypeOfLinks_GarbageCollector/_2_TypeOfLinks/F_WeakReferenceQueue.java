package Less07_TypeOfLinks_GarbageCollector._2_TypeOfLinks;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class F_WeakReferenceQueue {
	public static void main(String[] args) throws InterruptedException{
		ReferenceQueue<Integer> referenceQueue = new ReferenceQueue<>();
		
		WeakReference<Integer> weakRef1 = new WeakReference<>(1001, referenceQueue);//либо new Integer(1)
		WeakReference<Integer> weakRef2 = new WeakReference<>(1002, referenceQueue);//либо new Integer(2)
		WeakReference<Integer> weakRef3 = new WeakReference<>(1003, referenceQueue);//либо new Integer(3)
		
		System.out.println(weakRef1);
		System.out.println(weakRef2);
		System.out.println(weakRef3);
		
		if (referenceQueue.poll() == null)
			System.out.println("До сборки мусора очередь пуста.");
		
		System.gc();
		
		System.out.println("После сборки мусора:");
		for (Reference ref = referenceQueue.remove(000); ref != null;) {
			
			System.out.println("Ссылка: " + ref);
			System.out.println("Оборачивает: " + ref.get());
			
			ref = referenceQueue.poll();
		}
	}
}
