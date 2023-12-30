package HomeWork.basetypes;

import java.util.*;

public class Test {
	public static String remainder(int key, int size, int num) {
		String f1 = "func1: " + key + " % " + size + " = " + (key % size) + "\n";
		String f2 = "func2: " + num + " - " + key + " % " + num +  " = " + (num - key % num) + "\n";
		return f1 + f2;
	}
	
	public static int nextPrime(int start){
		int nextPrime = 0;
		int limit = 100;
		boolean simple = false;
		int step = start + limit;
		for(int i = start; i < step; i++) {
			int currNum = i;
			for(int j = 2; j < i; j++) {
				if(currNum % j == 0) {
					simple = false;
					break;
				}	else
					simple = true;
			}
			if(simple) {
				nextPrime = currNum;
				break;
			}
			if(i == step - 1)
				step += limit;
			//System.out.println("i: " + i + ", step: " + step);
		}
		//System.out.println(nextPrime);
    return nextPrime;
  }
  public static int getThreshold(int capacity, double loadFactor) {
		double c = Integer.valueOf(capacity).doubleValue();
		Double t = Math.ceil(c + (c * loadFactor));
		return t.intValue();
  }
	public static<T> List<T> arrToList(T...args) {
		return Arrays.asList(args);
	}
	
	
	public static void main(String[] args) {
		Integer[] ints = {9, 10, 2, 6, 3, 4};
		List<Integer> integers = arrToList(ints);
		//System.out.println(integers);
		
		//System.out.println(getThreshold(33, 0.1));
		//System.out.println(nextPrime(getThreshold(33,0.1)));
		TreeSet<Integer> treeSet = new TreeSet<>();
		for(int i = 101; i < 1000; i++) {
			treeSet.add(nextPrime(i));
			//System.out.println();
		}
		
		//System.out.println(nextPrime(15));
		//System.out.println(treeSet);
		List<Integer> list = new ArrayList<>();
		Integer[] table = new Integer[7];
		for(int i = 0; i < 30; i++) {
			list.add(i % table.length);
		}
		//System.out.println(list);
		for(int i = 1; i < 30; i++) {
//			System.out.println("------------");
//			System.out.println(i + " << 1: " + (i << 1));
//			System.out.println(i + " >> 1: " + (i >> 1));
		}
		HashMap < String, Integer> map = new HashMap<>();
		map.put("KING", 100);
		//System.out.println("KING".hashCode());
		int hashCode = "KING".hashCode();
		int hash = hashCode ^ (hashCode >>> 16);
		//System.out.println(hash);
		//System.out.println((16 - 1) & hash);
		int size = 11;
		int num  = 5;
		LinkedList<Integer> li = new LinkedList<>();
		for(int i = 0; i < 20; i++) {
			//System.out.println(remainder(key, size, num));
			Integer key = 1;
			if(i % 2 == 0)
				li.add(key + i);
			else
				li.add(null);
		}
		System.out.println(li);
	}
}
/* Prime numbers
2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101,
 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199,
 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307
 */
