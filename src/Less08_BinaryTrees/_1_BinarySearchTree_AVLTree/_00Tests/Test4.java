package Less08_BinaryTrees._1_BinarySearchTree_AVLTree._00Tests;

class One1 {
	public static void print() {
		System.out.println(1);
	}
}

class Two1 extends One1 {
	public static void print() {
		System.out.println(2);
	}
}

public class Test4 {
	public static void main(String[] args) {
		One1 one = new Two1();
		one.print();
	}
}
