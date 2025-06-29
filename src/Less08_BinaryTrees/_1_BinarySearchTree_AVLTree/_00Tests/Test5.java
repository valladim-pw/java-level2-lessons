package Less08_BinaryTrees._1_BinarySearchTree_AVLTree._00Tests;

interface Inter {
}

class One2 {
	void print() {
		System.out.println("one");
	}
	
	void print2() {
		System.out.println("one2");
	}
}

class Two2 extends One2 {
}

class Three2 extends One2 {
}

class Four2 extends Two2 implements Inter {
};

class Five2 extends Four2 {
	void print() {
		System.out.println("five");
	}
}

class Six2 extends Three2 {
}

public class Test5 {
	public static void main(String[] args) {
		//Inter inter = new Six2();
		One2 one = new Five2();
		one.print();
		Five2 five = new Five2();
		five.print2();
		//Three2 three = new Four2();
	}
}
