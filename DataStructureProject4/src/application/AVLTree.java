package application;

import java.util.ArrayList;

public class AVLTree<T extends Comparable<T>> {
	TNode<T> root;
	public int height() {
	    return height(root);
	}
	
	private int height(TNode node) {
		if (node == null)
			return 0;
		return 1 + Math.max(height(node.left), height(node.right));
	}

	int balanceFactor(TNode node) {
		if (node == null)
			return 0;
		return height(node.left) - height(node.right);
	}

	TNode rotateRight(TNode n) {
		TNode x = n.left;
		TNode T2 = x.right;

		x.right = n;
		n.left = T2;

		return x;
	}

	TNode rotateLeft(TNode x) {
		TNode y = x.right;
		TNode T2 = y.left;

		y.left = x;
		x.right = T2;

		return y;
	}

	private TNode insert(TNode<T> node, T val) {
		if (node == null)
			return new TNode(val);

		if (val.compareTo(node.data) < 0)
			node.left = insert(node.left, val);
		else if (val.compareTo(node.data) > 0)
			node.right = insert(node.right, val);
		else
			return node; // duplicates not allowed
		rebalance(node);

		return node;
	}

	private TNode<T> rotateRightLeft(TNode<T> nodeN) {
		TNode nodeC = nodeN.right;
		nodeN.right = rotateRight(nodeC);
		return rotateLeft(nodeN);
	}

	private TNode<T> rotateLeftRight(TNode nodeN) {
		TNode nodeC = nodeN.left;
		nodeN.left = rotateLeft(nodeC);
		return rotateRight(nodeN);

	}

	private TNode rebalance(TNode nodeN) {
		int diff = balanceFactor(nodeN);
		if (diff > 1) { // addition was in node's left subtree
			if (balanceFactor(nodeN.left) > 0)
				nodeN = rotateRight(nodeN);
			else
				nodeN = rotateLeftRight(nodeN);
		} else if (diff < -1) { // addition was in node's right subtree
			if (balanceFactor(nodeN.right) < 0)
				nodeN = rotateLeft(nodeN);
			else
				nodeN = rotateRightLeft(nodeN);
		}
		return nodeN;
	}

	public void insert(T val) {
		root = insert(root, val);
	}

	public boolean delete(T data) {
		TNode temp = deleteBST(data);
		if (temp != null) {
			TNode rootNode = root;
			root = rebalance(rootNode);
			return true;
		}
		return false;
	}

	// In-order traversal
	void inOrder(TNode node) {
		if (node != null) {
			inOrder(node.left);
			System.out.print(node.data + " ");
			inOrder(node.right);
		}
	}

	public void printInOrder() {
		inOrder(root);
		System.out.println();
	}

	public boolean isEmpty() {
		return root.isLeaf();
	}

	public TNode deleteBST(T data) {
		TNode current = root;
		TNode parent = root;
		boolean isLeftChild = false;
		if (isEmpty())
			return null;// tree is empty
		while (current != null && !current.data.equals(data)) {
			parent = current;
			if (data.compareTo((T) current.data) < 0) {
				current = current.left;
				isLeftChild = true;
			} else {
				current = current.right;
				isLeftChild = false;
			}
		}
		if (current == null)
			return null; // 
		//  leaf
		if (!current.hasLeft() && !current.hasRight()) {
			if (current == root) // one node
				root = null;
			else {
				if (isLeftChild)
					parent.left = null;
				else
					parent.right = null;
			}
		} else if (current.hasLeft() && !current.hasRight()) { 
			// left child
			
			if (current == root) {
				root = current.left;
			} else if (isLeftChild) {
				parent.left = current.left;
			} else {
				parent.right = current.left;
			}
		} else if (current.hasRight() && !current.hasLeft()) { // current has
			// right child
			// only
			if (current == root) {
				root = current.right;
			} else if (isLeftChild) {
				parent.left = current.right;
			} else {
				parent.right = current.right;
			}
		} else { // case 3: node to be deleted has 2 children
			TNode successor = getSuccessor(current);
			if (current == root)
				root = successor;
			else if (isLeftChild)
				parent.left = successor;
			else
				parent.right = successor;
			successor.left = current.left;
		}
		return current;
	}

	private TNode getSuccessor(TNode node) {
		TNode parentOfSuccessor = node;
		TNode successor = node;
		TNode current = node.right;
		while (current != null) {
			parentOfSuccessor = successor;
			successor = current;
			current = current.left;
		}
		if (successor != node.right) { // fix successor connections
			parentOfSuccessor.left = successor.right;
			successor.right = node.right;
		}
		return successor;
	}

	public T search(T val) {
		return search(root, val);
	}
	
	private T search(TNode<T> node, T val) {
		if (node == null)
			return null;
		if (node.data.equals(val))
			return node.data; // Return node if found, or null if not found

		if (val.compareTo(node.data) < 0) {
			return search(node.left, val); // Search in the left subtree
		} else {
			return search(node.right, val); // Search in the right subtree
		}
	}
	public ArrayList<T> toList() {
		ArrayList<T> list = new ArrayList<>();
	    inOrder(root, list);
	    return list;
	}

	private void inOrder(TNode<T> node, ArrayList<T> list) {
	    if (node != null) {
	    	inOrder(node.left, list);
	        list.add(node.data);
	        inOrder(node.right, list);
	    }
	}
}