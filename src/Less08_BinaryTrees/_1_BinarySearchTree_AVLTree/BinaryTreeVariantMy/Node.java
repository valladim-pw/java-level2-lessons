package Less08_BinaryTrees._1_BinarySearchTree_AVLTree.BinaryTreeVariantMy;

public class Node implements Comparable<Node> {
	private int value; // ключ узла
	private Node leftChild; // ссылка на левый узел потомок
	private Node rightChild; // ссылка на правый узел потомок
	
	public int getValue() {
		return value;
	}
	
	public void setValue(final int value) {
		this.value = value;
	}
	
	public Node getLeftChild() {
		return leftChild;
	}
	
	public void setLeftChild(final Node leftChild) {
		this.leftChild = leftChild;
	}
	
	public Node getRightChild() {
		return rightChild;
	}
	
	public void setRightChild(final Node rightChild) {
		this.rightChild = rightChild;
	}
	
	@Override
	public String toString() {
		return "(" + value + ")";
	}
	
	@Override
	public int compareTo(Node otherNode) {
		return Integer.compare(this.value, otherNode.value);
	}
}
