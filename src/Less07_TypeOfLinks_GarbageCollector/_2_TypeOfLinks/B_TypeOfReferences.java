package Less07_TypeOfLinks_GarbageCollector._2_TypeOfLinks;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class B_TypeOfReferences {
	public static void main(String[] args) {
		SoftReference<StringBuffer> softRef = new SoftReference<>(new StringBuffer("Мягкая ссылка"));
		WeakReference<StringBuffer> weakRef = new WeakReference<>(new StringBuffer("Слабая ссылка"));
		
		System.out.println("softRef.get() = " + softRef.get());
		System.out.println("weakRef.get() = " + weakRef.get());
		
		System.gc();
		
		System.out.println("После сборки мусора:");
		System.out.println("softRef.get() = " + softRef.get());
		System.out.println("weakRef.get() = " + weakRef.get());
	}
}
