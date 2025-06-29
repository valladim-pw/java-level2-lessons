package Less08_BinaryTrees._1_BinarySearchTree_AVLTree.BinaryTreeVariantMy;

import java.util.*;

// класс дерева
class Tree {
	private Node rootNode; // корневой узел
	
	public Tree() { // Пустое дерево
		rootNode = null;
	}
	
	// Поиск узла по значению ключа
	public Node findNode(int value) {
		Node currentNode = rootNode; // начинаем поиск с корневого узла
		while (currentNode.getValue() != value) { // поиск пока не будет найден элемент или не будут перебраны все
			if (value < currentNode.getValue()) { // движение влево?
				currentNode = currentNode.getLeftChild();
			} else { // движение вправо
				currentNode = currentNode.getRightChild();
			}
			if (currentNode == null) { // если потомка нет,
				// выводим на консоль сообщение
				System.out.println("Нет узла с значением: " + value);
				return null; // возвращаем null
			}
		}
		// выводим на консоль найденный элемент
		System.out.println("Найденный узел имеет значние: " + currentNode.getValue());
		return currentNode; // возвращаем найденный элемент
	}
	
	// Метод вставки нового элемента
	public void insertNode(int value) {
		Node newNode = new Node(); // создание нового узла
		newNode.setValue(value); // вставка данных
		if (rootNode == null) { // если корневой узел не существует
			rootNode = newNode;// то новый элемент и есть корневой узел
		} else { // если корневой узел в наличие
			Node currentNode = rootNode; // начинаем с корневого узла
			Node parentNode;
			while (true) { // мы имеем внутренний выход из цикла
				parentNode = currentNode;
				if (value == currentNode.getValue()) {  // если такой элемент в дереве уже есть, не сохраняем его
					return;  // просто выходим из метода
				} else if (value < currentNode.getValue()) {  // если аргумент меньше значения текущего узла
					currentNode = currentNode.getLeftChild();  // движение влево
					if (currentNode == null) { // если был достигнут конец цепочки,
						parentNode.setLeftChild(newNode); //  то вставить слева и выйти из метода
						return;
					}
				} else { // если аргумент больше значения текущего узла
					currentNode = currentNode.getRightChild();// движение вправо
					if (currentNode == null) { // если был достигнут конец цепочки,
						parentNode.setRightChild(newNode); //  то вставить справа и выйти из метода
						return;
					}
				}
			}
		}
	}
	
	// Удаление узла с заданным ключом
	public Node deleteNode(int value) {
		Node delNode = rootNode;  // Определяем удаляемый узел как корневой
		Node leftNode = null;
		Node rightNode = null;
		Node newNode = null;
		Node parentNode = null;
		boolean isLeftNode = false;
		boolean isRootNode = false;
		// Ищем удаляемый узел сравнивая значение аргумента со значением текущего узла
		while (delNode != null && delNode.getValue() != value) {
			parentNode = delNode;
			if (value < delNode.getValue()) {
				delNode = delNode.getLeftChild();
				isLeftNode = true; // удаляемый узел - слева
			} else {
				delNode = delNode.getRightChild();
				isLeftNode = false; // удаляемый узел - справа
			}
		}
		if (delNode == null) {
			System.out.println(false);
			return null; // удаляемый yзел не найден
		}
		if (value == rootNode.getValue()) {
			parentNode = delNode;
			isRootNode = true; // удаляемый узел - корневой узел
		}
		// вариант, когда удалемый узел не имеет потомков
		if (delNode.getLeftChild() == null && delNode.getRightChild() == null) {
			// в зависимости от положения удаляемого узла (корневой, левый или правый),
			// устанавливаем его значение null (удаляем)
			if (isRootNode) {
				rootNode = null;
			} else if (isLeftNode)
				parentNode.setLeftChild(null);
			else
				parentNode.setRightChild(null);
			// вариант, когда у удаляемого узла есть один потомок (правый или левый)
		} else if (delNode.getLeftChild() == null || delNode.getRightChild() == null) {
			// в зависимости от положения удаляемого узла (корневой, левый или правый),
			// устанавливаем его значение null (удаляем)
			// а его ссылку на правый или левый узел-потомок передаем родителю удаляемого узла
			if (isRootNode) {
				if (delNode.getLeftChild() == null)
					rootNode = delNode.getRightChild();
				if (delNode.getRightChild() == null)
					rootNode = delNode.getLeftChild();
			} else if (isLeftNode) {
				parentNode.setLeftChild(null);
				parentNode.setLeftChild(delNode.getLeftChild());
			} else {
				parentNode.setRightChild(null);
				parentNode.setRightChild(delNode.getRightChild());
			}
			// вариант, когда у удаляемого узла есть два потомка
		} else {
			// в зависимости от положения удаляемого узла (корневой, левый или правый),
			// определяем используя доп.метод getMinHeir(аргумент - удаляемый узел) новый узел который должен заменить удаляемый,
			// родителю удаляемого узла устанавливаем ссылку на этот новый узел,
			// новому узлу устанавливаем сохраненные ссылки на правого и левого потомков удаляемого узла
			leftNode = delNode.getLeftChild();
			rightNode = delNode.getRightChild();
			
			if (isRootNode) {
				parentNode.setRightChild(getMinHeir(delNode));
				newNode = parentNode.getRightChild();
				rootNode = newNode;
			} else if (isLeftNode) {
				parentNode.setLeftChild(null);
				parentNode.setLeftChild(getMinHeir(delNode));
				newNode = parentNode.getLeftChild();
			} else {
				parentNode.setRightChild(null);
				parentNode.setRightChild(getMinHeir(delNode));
				newNode = parentNode.getRightChild();
			}
			newNode.setLeftChild(leftNode);
			if (newNode.compareTo(rightNode) != 0)
				newNode.setRightChild(rightNode);
		}
		return delNode; // возвращаем удаленный узел
	}
	
	// Дополнительный метод для возврата узла с минимальным ключом
	// в наборе потомков правого потомка удаляемого узла, который находится в аргументе метода
	public Node getMinHeir(Node deleteNode) {
		Node currentNode = deleteNode.getRightChild();
		Node parentNode = currentNode;
		Node minHeir = currentNode;
		
		while (currentNode.getLeftChild() != null) {
			parentNode = currentNode;
			currentNode = currentNode.getLeftChild();
		}
		// если искомый узел не имеет правого потомка, просто убираем ссылку на него у родителя
		// если имеет правого потомка - родителю вместо него устанавливаем ссылку на этого потомка
		if (currentNode.getLeftChild() == null) {
			minHeir = currentNode;
			if (currentNode.getRightChild() != null)
				parentNode.setLeftChild(currentNode.getRightChild());
			else
				parentNode.setLeftChild(null);
		}
		return minHeir;
	}
	
	// Метод инициирующий служебный внутренний класс для вывода на консоль наглядного представления
	// двоичного дерева
	public void printTree() {
		TreePrinter printer = new TreePrinter();
		printer.printTree(rootNode);
	}
	
	// Служебный внутренний класс для вывода на консоль наглядного представления
	// двоичного дерева
	private class TreePrinter {
		private List<String> printList;
		private LinkedList<Integer> levelList;
		private int maxValueLength;
		private int maxStringLength;
		
		public void printTree(Node root) {
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
		
		private int getTreeMaxLevel(Node root) {
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
							if (currentNode.getLeftChild() != null) {
								queue.add(currentNode.getLeftChild());
							} else
								queue.add(null);
							if (currentNode.getRightChild() != null) {
								queue.add(currentNode.getRightChild());
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
			int countLastRow = 1;
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
					if (i < mark) {
						newValue = gapDash.repeat(startSpace) + value + gapDash.repeat(endSpace);
					}
					if (i > mark) {
						if (countLastRow == 1)
							newValue = gapSpace.repeat(2) + value + gapSpace.repeat(endSpace + startSpace);
						else {
							if (countLastRow % 2 == 0)
								newValue = gapSpace.repeat((endSpace + startSpace) >= 2 ? (endSpace + startSpace) - (value.length() < 4 && maxValueLength > 4 ? 2 : 0) : (endSpace + startSpace))
			           + value + gapSpace.repeat(value.length() < 4 && maxValueLength > 4 ? 4 : 2);
							else
								newValue = value + gapSpace.repeat(endSpace + startSpace);
						}
						countLastRow++;
					}
					changeValues.add(newValue);
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

