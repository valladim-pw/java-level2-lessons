package Less08_BinaryTrees._1_BinarySearchTree_AVLTree.BinaryTreeMy.OneFileCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

class TreeTest {
	static final int ITERATIONS = 10;
	
	public static void main(String[] args) throws TreeException {
		BinaryTree<Integer, String> tree1 = new BinaryTree<>();
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			int randomNumber = random.nextInt(900) + 1; // Генерация числа от 1 до 1000
			tree1.add(randomNumber, "==" + randomNumber);
		}
		System.out.println("");
		tree1.printTree();
		BinaryTree<Integer, String> tree2 = new BinaryTree<>();
		tree2.add(7, "*7");
		tree2.add(3, "*3");
		tree2.add(11, "*11");
		tree2.add(2, "*2");
		tree2.add(5, "*5");
		tree2.add(4, "*4");
		tree2.add(9, "*9");
		tree2.add(12, "*12");
		tree2.add(1, "*1");
		tree2.add(6, "*6");
		tree2.add(8, "*8");
		tree2.add(10, "*10");
		tree2.add(13, "*13");
		
		tree2.printTree();
		tree2.delete(7);
		tree2.printTree();
		tree2.delete(8);
		tree2.printTree();
		tree2.find(10);
		tree2.change(14, 9);
		tree2.printTree();
	}
}

class TreeException extends Exception {
	public TreeException(String message) {
		super(message);
	}
}

class BinaryTree<K extends Comparable<K>, V> {
	private static final String KEYEXIST = "Key already exist";
	private static final String KEYNOTEXIST = "Key not exist";
	
	class Node<K extends Comparable<K>, V> {
		K key;
		V value;
		Node parent;
		Node left;
		Node right;
		
		public Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		public Node<K, V> find(K key) {
			int cmp = key.compareTo(this.key);
			if (cmp < 0) {
				if (left != null)
					return left.find(key);
				else
					return this;
			}
			if (cmp > 0) {
				if (right != null)
					return right.find(key);
				else
					return this;
			}
			return this;
		}
		
		public void add(Node<K, V> node) throws TreeException {
			int cmp = node.key.compareTo(this.key);
			if (cmp == 0) {
				//throw new TreeException(KEYEXIST);
				return;
			}
			if (cmp < 0) {
				if (left == null) {
					left = node;
					node.parent = this;
				} else
					left.add(node);
			}
			if (cmp > 0) {
				if (right == null) {
					right = node;
					node.parent = this;
				} else
					right.add(node);
			}
		}
		
		// метод удаления текущего узла, возвращает удаленный узел
		public Node<K, V> delete() throws TreeException {
			Node<K, V> replace = null; // ссылка на удаляемый(перемещаемый) узел
			Node<K, V> left = this.left; // левый потомок текущего узла
			Node<K, V> right = this.right; // правый потомок текущего узла
			Node<K, V> parent = this.parent; // его родитель
			
			if (left != null && right != null) { // случай, когда у текущего узла есть и левый и правый потомки
				if (right.left == null) { // если у правого потомка нет левого потомка
					replace = right; // удаляемый(перемещаемый) узел будет правым потомком
					replace.add(left); // добавляем ему ссылку на левого потомка
				} else { // если у правого потомка есть левы-й(е) потом-ок(ки)
					replace = right.find(left.key); // удаляемый(перемещаемый) узел будет последним в цепочке левых потомков ( у которого уже нет потомков)
					replace.parent.left = null; // убираем ссылку  на удаляемый(пермещаемый) узел
					if (replace.right != null) // если у удаляемого(перемещаемого) узла есть правый потомок
						replace.parent.add(replace.right); // он заменяет удаляемый(перемещаемый) узел
					// удаляем ссылки на правого и левого потомков у удаляемого(перемещаемого) узла
					replace.left = null;
					replace.right = null;
					// добавляем ему ссылки на правого и левого потомков текущего узла узла
					replace.add(left);
					replace.add(right);
				}
			} else if (left != null || right != null) { // случай, когда у текущего узла есть либо левый либо правый потомки
				// если есть только левый - удаляемый узел будет левым потомком
				if (left != null)
					replace = left;
				// только правый - удаляемый узел будет правым потомком
				if (right != null)
					replace = right;
			} else { // случай, когда у текущего узла нет потомков
				// если текущий узел слева по отношению к родителю
				if (this == parent.left) {
					replace = parent.left; //сохраняем ссылку в удаляемый узел
					parent.left = null; // убираем текущий левый узел
				}
				// если текущий узел справа по отношению к родителю, те же действия по аналогии
				if (this == parent.right) {
					replace = parent.right;
					parent.right = null;
				}
			}
			// если текущий узел является корневым ( нет родителя)
			if (parent == null)
				replace.parent = null; // убираем ссылку на родителя в удалямом (перемещаемом) узле
			
			return replace;
		}
		
		public String toString() {
			return "(" + key + "," + value + ")";
		}
	}
	
	Node<K, V> root;
	
	public Node<K, V> find(K key) throws TreeException {
		if (root == null)
			return null;
		Node<K, V> found = root.find(key);
		int cmp = found.key.compareTo(key);
		if (cmp == 0) {
			System.out.println("\nПо ключу " + key + " найден элемент: " + found);
			return found;
		} else
			throw new TreeException(KEYNOTEXIST);
	}
	
	public void add(Node<K, V> node) throws TreeException {
		if (root == null)
			root = node;
		else
			root.add(node);
	}
	
	public void add(K key, V value) throws TreeException {
		add(new Node(key, value));
	}
	
	// метод удаления узла по ключу в дереве
	public void delete(K key) throws TreeException {
		Node<K, V> found = root.find(key);  // находим в дереве узел подлежащий удалению по ключу-аргументу
		Node<K, V> replace = found.delete(); // получаем заменяющий узел из метода удаления в узле
		
		if (root == null || found == null)  // если дерева нет или узел не найден по ключу выбрасываем исключение
			throw new TreeException(KEYNOTEXIST);
		
		if (found.parent != null) { // если у удаляемого узла есть родитель
			// если найденный узел справа от родителя, соответственно ссылка на правого потомка становится замещающим узлом
			if (found.parent.right == found) {
				found.parent.right = replace;
			}
			// аналогично если узел слева по отношению к родителю
			if (found.parent.left == found) {
				found.parent.left = replace;
			}
		} else
			// если у удаляемого узла нет родителей - замещающий узел становится корневым
			root = replace;
	}
	
	public void change(K newKey, K oldKey) throws TreeException {
		Node<K, V> found = root.find(oldKey);
		delete(oldKey);
		root.add(new Node(newKey, found.value));
		System.out.println("\nЗамена в узле: " + found + " ключа " + oldKey + " на ключ " + newKey);
	}
	
	public void printTree() {
		TreePrinter printer = new TreePrinter();
		printer.printTree(root);
	}
	
	private class TreePrinter {
		private List<String> printList;
		private LinkedList<Integer> levelList;
		private int maxValueLength;
		private int maxStringLength;
		
		public void printTree(Node<K, V> root) {
			this.maxValueLength = Integer.MIN_VALUE;
			this.maxStringLength = 0;
			int space = getTreeMaxLevel(root);
			List<String> subList = new ArrayList<>();
			System.out.println();
			System.out.println("---New Tree--------------");
			if (space < 0) {
				System.out.println(" () ");
				return;
			}
			
			while (!printList.isEmpty()) {
				int index = levelList.poll();
				
				subList = printList.subList(0, index);
				printRow(space, subList, subList.size());
				printList = printList.subList(index, printList.size());
				
				space /= 2;
			}
		}
		
		private int getTreeMaxLevel(Node<K, V> root) {
			int maxLevel = 0;
			int levelIndex = 0;
			String maxValueStr = "";
			if (root == null) {
				maxLevel = -1;
				return maxLevel;
			}
			List<String> tempList = new ArrayList<>();
			levelList = new LinkedList<>();
			Queue<Node> queue = new LinkedList<>();
			queue.add(root);
			boolean isEmptyRow = true;
			
			while (!queue.isEmpty()) {
				int levelSize = queue.size();
				levelList.add(levelSize);
				for (Node node : queue) {
					if (node != null) {
						isEmptyRow = false;
						break;
					}
				}
				if (!isEmptyRow) {
					for (int i = 0; i < levelSize; i++) {
						Node currentNode = queue.poll();
						if (currentNode != null) {
							tempList.add(currentNode.toString());
							maxValueStr = currentNode.toString();
							if (maxValueStr.length() >= maxValueLength)
								maxValueLength = maxValueStr.length();
							if (currentNode.left != null) {
								queue.add(currentNode.left);
							} else
								queue.add(null);
							if (currentNode.right != null) {
								queue.add(currentNode.right);
							} else
								queue.add(null);
						} else {
							tempList.add("()");
							queue.add(null);
							queue.add(null);
						}
					}
					levelIndex = levelSize;
					isEmptyRow = true;
				} else {
					maxLevel = levelSize;
					break;
				}
			}
			int index = tempList.size() - maxLevel / 2;
			tempList.add(index, "flag");
			
			printList = adjustValues(maxValueLength, tempList);
			maxStringLength = maxValueLength * levelIndex;
			
			return maxLevel;
		}
		
		private List<String> adjustValues(int maxValueLength, List<String> values) {
			String gapDash = "_";
			String gapSpace = " ";
			int mark = values.indexOf("flag");
			int startSpace = 0;
			int endSpace = 0;
			int lengthDiff = 0;
			String value = "";
			String newValue = "";
			
			List<String> changeValues = new ArrayList<>();
			for (int i = 0; i < values.size(); i++) {
				value = values.get(i);
				
				lengthDiff = maxValueLength - value.length();
				
				startSpace = (int) Math.floor(((lengthDiff) * 1.0) / 2);
				endSpace = (int) Math.ceil(((lengthDiff) * 1.0) / 2);
				
				if (!value.equals("flag")) {
					if (lengthDiff >= 0) {
						if (i < mark)
							newValue = gapDash.repeat(startSpace) + value + gapDash.repeat(endSpace);
						if (i > mark)
							newValue = gapSpace.repeat(startSpace) + value + gapSpace.repeat(endSpace);
						
						changeValues.add(newValue);
					} else
						changeValues.add(value);
				}
			}
			return changeValues;
		}
		
		private void printRow(int space, List<String> list, int index) {
			int count = 0;
			String gapSpace = " ";
			String gapDash = "_";
			int startValueHalf = 0;
			int endValueHalf = 0;
			int startCount = 0;
			int spaceCount = 0;
			int startDashCount = 0;
			int endDashCount = 0;
			
			startValueHalf = (int) Math.floor(((maxValueLength) * 1.0) / 2);
			endValueHalf = (int) Math.ceil(((maxValueLength) * 1.0) / 2);
			
			startCount = (maxStringLength / (index * 2));
			
			spaceCount = startCount / 2;
			if (spaceCount >= startValueHalf)
				startDashCount = spaceCount - startValueHalf;
			if (spaceCount >= endValueHalf)
				endDashCount = spaceCount - endValueHalf;
			
			LinkedList<String> valueList = new LinkedList<>(list);
			String spaceHalf = "";
			String startDashHalf = "";
			String endDashHalf = "";
			
			while (!valueList.isEmpty()) {
				String value = valueList.poll();
				if (space == 4 && maxValueLength % 2 != 0)
					count++;
				
				if (count % 2 == 0 && count != 0)
					spaceHalf = gapSpace.repeat(spaceCount + 1);
				else
					spaceHalf = gapSpace.repeat(spaceCount);
				
				startDashHalf = gapDash.repeat(startDashCount);
				endDashHalf = gapDash.repeat(endDashCount);
				
				StringBuilder sb = new StringBuilder(space);
				
				if (levelList.size() > 1)
					sb.append(spaceHalf + startDashHalf + value + endDashHalf + spaceHalf);
				else
					sb.append(value);
				System.out.print(sb.toString());
			}
			System.out.println();
		}
	}
}


