package Less08_BinaryTrees._1_BinarySearchTree_AVLTree.BinaryTree2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Consumer;

class TreeException extends Exception {
	public TreeException(String message) {
		super(message);
	}
}

public class BinaryTree<K extends Comparable<K>, V> {
	private static final String KEYEXIST = "Key already exist";
	private static final String KEYNOTEXIST = "Key not exist";
	
	class TreeLeaf<K extends Comparable<K>, V> {
		K key;
		V value;
		TreeLeaf parent;
		TreeLeaf left;
		TreeLeaf right;
		
		public TreeLeaf(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		private TreeLeaf<K, V> find(K key) {
			int cmp = key.compareTo(this.key);
			if (cmp > 0) {
				if (right != null)
					return right.find(key);
				else
					return this;
			}
			if (cmp < 0) {
				if (left != null)
					return left.find(key);
				else
					return this;
			}
			return this;
		}
		
		void add(TreeLeaf<K, V> leaf) throws TreeException {
			int cmp = leaf.key.compareTo(key);
			if (cmp == 0) {
				return;
				//throw new TreeException(KEYEXIST);
			}
			if (cmp > 0) {
				right = leaf;
				leaf.parent = this;
			} else {
				left = leaf;
				leaf.parent = this;
			}
		}
		
		void delete() throws TreeException {
			if (parent.right == this) {
				parent.right = right;
				
				if (right != null)
					right.parent = parent;
				if (left != null)
					parent.find(left.key).add(left);
			} else {
				parent.left = left;
				if (left != null)
					left.parent = parent;
				if (right != null)
					parent.find(right.key).add(right);
			}
		}
		
		public String toString() {
			return "(" + key + "," + value + ")";
		}
		
		public void process(Consumer<TreeLeaf<K, V>> consumer) {
			if (left != null)
				left.process(consumer);
			consumer.accept(this);
			if (right != null)
				right.process(consumer);
		}
	}
	
	// дерево изначально представляет из себя одну ссылку - корневой лист, это точка входа в дерево
	private TreeLeaf<K, V> root;
	
	// метод поиска дерева, возвращает значение листа по его ключу
	public V find(K key) {
		if (root == null) // если ничего нет возвращаем null
			return null;
		// если корневой лист есть, вызываем метод поиска у корневого листа по ключу-параметру
		TreeLeaf found = root.find(key);
		// делаем дополнительную проверку на равенство ключа текущего листа с ключом-параметром
		// при равенстве возвращаем значение текущего листа, иначе - null
		return found.key.compareTo(key) == 0 ? (V) found.value : null;
	}
	
	public TreeLeaf<K, V> findLeaf(K key) {
		return root.find(key);
	}
	
	// метод вставки самого дерева
	public void add(TreeLeaf<K, V> leaf) throws TreeException {
		if (root == null)// если корневого листа нет объект листа в параметре становится корневым листом
			root = leaf;
		else // если есть, запускаем метод поиска,который либо находит лист с ключом равным ключу листа-параметра
			root.find(leaf.key).add(leaf); // либо запускает вставку листа-параметра
	}
	
	public void add(K key, V value) throws TreeException {
		add(new TreeLeaf<>(key, value));
	}
	
	public void delete(K key) throws TreeException {
		internaldDelete(key);
	}
	
	// метод удаления в дереве по ключу, возвращает удаляемый лист
	public TreeLeaf<K, V> internaldDelete(K key) throws TreeException {
		if (root == null) // если дерева нет выбрасываем исключение
			throw new TreeException(KEYNOTEXIST);
		
		TreeLeaf found = root.find(key); // ищем лист, который будем удалять
		int cmp = found.key.compareTo(key);
		if (cmp != 0) // если лист не нашелся тоже выбрасываем исключение
			throw new TreeException(KEYNOTEXIST);
		if (found.parent == null) { // проверяем не является ли удаляемый лист корневым
			// если удаляем корневой лист, то надо в корень присвоить то, что будет вставляться
			if (found.right != null) { // если есть правый потомок
				root = found.right; // в корень присваиваем его значение
				if (found.left != null) // при наличии еще и левого потомка
					add(found.left); // добавляем его в дерево
			} else if (found.left != null) // если есть левый потомок(а правый равен null, раз дошло до этой части условий)
				root = found.left; // присваиваем в корень значение левого потомка
			else
				root = null; // в случае отсутствия потомков обнуляем корневой лист
		} else
			found.delete(); // если удаляемый лист не корневой, вызываем у него метод удаления листа
		return found;
	}
	
	// метод позволяющий заменить ключ листа на другой ключ с соответствующей перестройкой дерева
	public void change(K oldKey, K newKey) throws TreeException {
		TreeLeaf<K, V> current = internaldDelete(oldKey);
		current.key = newKey;
		add(current);
	}
	
	public void process(Consumer<TreeLeaf<K, V>> consumer) {
		if (root != null)
			root.process(consumer);
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
		
		public void printTree(TreeLeaf<K, V> root) {
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
		
		private int getTreeMaxLevel(TreeLeaf<K, V> root) {
			int maxLevel = 0;
			int levelIndex = 0;
			String maxValueStr = "";
			if (root == null) {
				maxLevel = -1;
				return maxLevel;
			}
			List<String> tempList = new ArrayList<>();
			levelList = new LinkedList<>();
			Queue<TreeLeaf> queue = new LinkedList<>();
			queue.add(root);
			boolean isEmptyRow = true;
			
			while (!queue.isEmpty()) {
				int levelSize = queue.size();
				levelList.add(levelSize);
				for (TreeLeaf node : queue) {
					if (node != null) {
						isEmptyRow = false;
						break;
					}
				}
				if (!isEmptyRow) {
					for (int i = 0; i < levelSize; i++) {
						TreeLeaf current = queue.poll();
						if (current != null) {
							tempList.add(current.toString());
							maxValueStr = current.toString();
							if (maxValueStr.length() >= maxValueLength)
								maxValueLength = maxValueStr.length();
							if (current.left != null) {
								queue.add(current.left);
							} else
								queue.add(null);
							if (current.right != null) {
								queue.add(current.right);
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
			maxStringLength = (maxValueLength + 1) * levelIndex;
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
					if (lengthDiff > 0) {
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
			String gapSpace = " ";
			String gapDash = "_";
			int startValueHalf = (int) Math.floor(((maxValueLength) * 1.0) / 2);
			int endValueHalf = (int) Math.ceil(((maxValueLength) * 1.0) / 2);
			
			int startCount = ((maxStringLength / (index * 2) - space) + space) - startValueHalf;
			int tmpStartSpaceCount = (int) Math.ceil(((startCount) * 1.0) / 2);
			int tmpStartDashCount = (int) Math.floor(((startCount) * 1.0) / 2);
			int startDashCount = tmpStartDashCount > startValueHalf ? tmpStartDashCount - startValueHalf : tmpStartDashCount;
			int startSpaceCount = tmpStartDashCount > startValueHalf ? tmpStartSpaceCount + startValueHalf : tmpStartSpaceCount;
			
			int endCount = ((maxStringLength / (index * 2) - space) + space) - endValueHalf;
			int tmpEndDashCount = (int) Math.ceil(((endCount) * 1.0) / 2);
			int tmpEndSpaceCount = (int) Math.floor(((endCount) * 1.0) / 2);
			int endDashCount = tmpEndDashCount > endValueHalf ? tmpEndDashCount - endValueHalf : tmpEndDashCount;
			int endSpaceCount = tmpEndDashCount > endValueHalf ? tmpEndSpaceCount + endValueHalf : tmpEndSpaceCount;
			
			LinkedList<String> valueList = new LinkedList<>(list);
			String startSpaceHalf = "";
			String startDashHalf = "";
			String endSpaceHalf = "";
			String endDashHalf = "";
			
			while (!valueList.isEmpty()) {
				String value = valueList.poll();
				
				startSpaceHalf = gapSpace.repeat(startSpaceCount);
				endSpaceHalf = gapSpace.repeat(endSpaceCount);
				startDashHalf = gapDash.repeat(startDashCount);
				endDashHalf = gapDash.repeat(endDashCount);
				
				StringBuilder sb = new StringBuilder();
				if (levelList.size() > 1)
					sb.append(startSpaceHalf + startDashHalf + value + endDashHalf + endSpaceHalf);
				else
					sb.append(value);
				System.out.print(sb.toString());
			}
			System.out.println();
		}
	}
}

class TreeTest {
	static final int ITERATIONS = 10;
	
	public static void main(String[] args) throws TreeException {
		BinaryTree<Integer, String> tree = new BinaryTree<>();
		
		tree.add(7, "*7");
		tree.add(3, "*3");
		tree.add(11, "*11");
		tree.add(2, "*2");
		tree.add(5, "*5");
		tree.add(4, "*4");
		tree.add(9, "*9");
		tree.add(12, "*12");
		tree.add(1, "*1");
		tree.add(6, "*6");
		tree.add(8, "*8");
		tree.add(10, "*10");
		tree.add(13, "*13");
		
		tree.printTree();
		tree.delete(7);
		tree.printTree();
		BinaryTree.TreeLeaf leaf = tree.findLeaf(11);
		System.out.println(leaf + " - parent of this leaf: " + leaf.parent);
		System.out.println(leaf + " - left of this leaf: " + leaf.left);
		System.out.println(leaf + " - right of this leaf: " + leaf.right);
	}
}