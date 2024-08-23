package Less07_TypeOfLinks_GarbageCollector._2_TypeOfLinks;

import java.lang.ref.SoftReference;
import java.util.ArrayList;

public class C_SoftRefsResearch {
	public StringBuffer sb;
	
	public C_SoftRefsResearch(StringBuffer sb) {
		this.sb = sb;
	}
	
	public static void main(String[] args) {
		SoftReference<C_SoftRefsResearch> softRef =
						new SoftReference<>(new C_SoftRefsResearch(new StringBuffer("Объект под мягкой ссылкой")));
		StringBuffer sb = softRef.get().sb;
		
		System.out.println(softRef.get());
		
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < 90_000_000; i++)  list.add(i);
		
		System.out.println(softRef.get());
		System.out.println(sb);
	}
}
