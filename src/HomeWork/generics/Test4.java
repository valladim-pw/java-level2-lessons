package HomeWork.generics;

import java.util.List;

public class Test4<T> {
	public void addAll(List<T> list, T... items) {
		for (T item: items) {
			list.add(item);
		}
	}
}
