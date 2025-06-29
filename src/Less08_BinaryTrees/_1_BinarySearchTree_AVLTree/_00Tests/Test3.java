package Less08_BinaryTrees._1_BinarySearchTree_AVLTree._00Tests;

class One {
	int i = 1;
	public int getInt() {
		return i;
	}
}

class Two extends One {
	int i = 2;
	public int getInt() {
		return i;
	}
}

public class Test3 {
	public static void main(String[] args) {
		One one = new One();
		System.out.println(one.getInt());
		try {
			Two two = (Two) one;
			System.out.println(two.getInt());
		} catch (Exception e) {
			System.out.println(e.getClass());
		}
		
		
	}
}
