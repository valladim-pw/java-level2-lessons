package Less08_BinaryTrees._1_BinarySearchTree_AVLTree.BinaryTree;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;

public class TreeTest {
	static final int ITERATIONS = 10;
	
	public static void main(String[] args) throws TreeException {
		// Для тестирования используется две структуры,
		// потому что для добавления в дерево используются случайные числа
		// и запоминание происходит и в map и в tree, что ниже используется в проверке работы поиска
		TreeMap<Integer, Integer> map = new TreeMap<>();
		BinaryTree<Integer, String> tree = new BinaryTree<>();
		for (int i = 0; i < ITERATIONS; i++) {
			int key = ThreadLocalRandom.current().nextInt();
			if (!map.containsKey(key)) {
				map.put(key, key);
				tree.add(key, "key=" + key);
			}
		}
		System.out.println("add passed OK");
		// в первой части теста элементы просто распечатываются, причем идут по возрастанию
		System.out.println("1. Print tree");
		tree.process(System.out::println);
		// во второй части теста элементы сортируются
		System.out.println("2. Sort tree");
		ArrayList<BinaryTree.TreeLeaf> sorted = new ArrayList<>();
		tree.process(sorted::add);
		for (BinaryTree.TreeLeaf leaf : sorted) {
			System.out.println(leaf.toString());
		}
		// проверка работы поиска и удаления с использованием ключей сохраненных в map
		for (Integer i : map.keySet()) {
			tree.find(i);
			tree.delete(i);
		}
		System.out.println("find&delete passed OK");
	}
}
