package application;

public class TNode<T extends Comparable<T>> {
	T data;
	TNode<T> left, right;

	TNode(T val) {
		data = val;
	}

	public boolean hasLeft() {
		return left != null;
	}

	public boolean hasRight() {
		return right != null;
	}

	public boolean isLeaf() {
		return left == null && right == null;
	}

}