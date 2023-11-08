package Less02_Lambda_Recursion._1_IntroToLambdaExpressions;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;


public class _4TreeSetWithCustomComparatorLambda {
	public static void main(String[] args) {
		TreeSet<Integer> treeSet = new TreeSet<>((o1, o2) ->  Integer.compare(Math.abs(o1), Math.abs(o2)));
		
		treeSet.addAll(List.of(3, 5, -1, -3, -5, -4, 4, -5, 5, 5, 1, 2, -1));
		System.out.println(treeSet);
	}
}
