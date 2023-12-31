package Less02_Lambda_Recursion._3_Recursion;

import java.util.List;

public class _6FastSearchInSortedList {
	static Integer fastSearch(List<Integer> list, int firstIdx, int lastIdx, int elem2Search) {
		if(lastIdx >= firstIdx) {
			int half = firstIdx + (lastIdx - firstIdx) / 2;
			if(list.get(half) == elem2Search)
				return half;
			if(list.get(half) > elem2Search)
				return fastSearch(list, firstIdx, half - 1, elem2Search);
			return fastSearch(list, half + 1, lastIdx, elem2Search);
		}
		return null;
	}
	
	public static void main(String[] args) {
		List<Integer> list = List.of(1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233);
		System.out.println(fastSearch(list, 0, list.size() - 1, 55));
	}
}
