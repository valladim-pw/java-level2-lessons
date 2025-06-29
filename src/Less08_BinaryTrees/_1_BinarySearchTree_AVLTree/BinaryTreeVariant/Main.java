package Less08_BinaryTrees._1_BinarySearchTree_AVLTree.BinaryTreeVariant;

public class Main {
	public static void main(String[] args) {
		Tree tree = new Tree();
		// вставляем узлы в дерево:
		tree.insertNode(7);
		tree.insertNode(3);
		tree.insertNode(11);
		tree.insertNode(2);
		tree.insertNode(5);
		tree.insertNode(9);
		tree.insertNode(12);
		tree.insertNode(1);
		tree.insertNode(4);
		tree.insertNode(6);
		tree.insertNode(8);
		tree.insertNode(10);
		tree.printTree();
		tree.deleteNode(7);
		tree.printTree();
		
		// находим узел по значению и выводим его в консоли
	//	Node foundNode = tree.findNodeByValue(7);
	//	foundNode.printNode();
	}
}
