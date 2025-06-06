package application;

import java.util.ArrayList;

public class AVLTree<T extends Comparable<T>> {
    TNode<T> root;

    public int height() {
        return height(root);
    }

    private int height(TNode<T> node) {
        if (node == null)
            return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public int balanceFactor(TNode<T> node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    public TNode<T> rotateRight(TNode<T> n) {
        TNode<T> x = n.left;
        TNode<T> T2 = x.right;

        x.right = n;
        n.left = T2;

        return x;
    }

    private TNode<T> rotateLeft(TNode<T> x) {
        TNode<T> y = x.right;
        TNode<T> T2 = y.left;

        y.left = x;
        x.right = T2;

        return y;
    }

    private TNode<T> insert(TNode<T> node, T val) {
        if (node == null)
            return new TNode<>(val);

        if (val.compareTo(node.data) < 0)
            node.left = insert(node.left, val);
        else if (val.compareTo(node.data) > 0)
            node.right = insert(node.right, val);
        else
            return node; // Duplicates not allowed

        node = rebalance(node); // ✅ FIXED: Must assign the result
        return node;
    }

    private TNode<T> rotateRightLeft(TNode<T> nodeN) {
        TNode<T> nodeC = nodeN.right;
        nodeN.right = rotateRight(nodeC);
        return rotateLeft(nodeN);
    }

    private TNode<T> rotateLeftRight(TNode<T> nodeN) {
        TNode<T> nodeC = nodeN.left;
        nodeN.left = rotateLeft(nodeC);
        return rotateRight(nodeN);
    }

    private TNode<T> rebalance(TNode<T> nodeN) {
        int diff = balanceFactor(nodeN);
        if (diff > 1) {
            if (balanceFactor(nodeN.left) >= 0)
                nodeN = rotateRight(nodeN);
            else
                nodeN = rotateLeftRight(nodeN);
        } else if (diff < -1) {
            if (balanceFactor(nodeN.right) <= 0)
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
        TNode<T> temp = deleteBST(data);
        if (temp != null) {
            root = rebalance(root); // Basic rebalance at root
            return true;
        }
        return false;
    }

    public boolean isEmpty() {
        return root == null; // ✅ FIXED
    }

    public TNode<T> deleteBST(T data) {
        TNode<T> current = root;
        TNode<T> parent = root;
        boolean isLeftChild = false;
        if (isEmpty())
            return null;

        while (current != null && !current.data.equals(data)) {
            parent = current;
            if (data.compareTo(current.data) < 0) {
                current = current.left;
                isLeftChild = true;
            } else {
                current = current.right;
                isLeftChild = false;
            }
        }

        if (current == null)
            return null;

        // Case 1: No child
        if (!current.hasLeft() && !current.hasRight()) {
            if (current == root)
                root = null;
            else if (isLeftChild)
                parent.left = null;
            else
                parent.right = null;
        }

        // Case 2: One child (left)
        else if (current.hasLeft() && !current.hasRight()) {
            if (current == root)
                root = current.left;
            else if (isLeftChild)
                parent.left = current.left;
            else
                parent.right = current.left;
        }

        // Case 3: One child (right)
        else if (current.hasRight() && !current.hasLeft()) {
            if (current == root)
                root = current.right;
            else if (isLeftChild)
                parent.left = current.right;
            else
                parent.right = current.right;
        }

        // Case 4: Two children
        else {
            TNode<T> successor = getSuccessor(current);
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

    private TNode<T> getSuccessor(TNode<T> node) {
        TNode<T> parentOfSuccessor = node;
        TNode<T> successor = node;
        TNode<T> current = node.right;

        while (current != null) {
            parentOfSuccessor = successor;
            successor = current;
            current = current.left;
        }

        if (successor != node.right) {
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
            return node.data;

        if (val.compareTo(node.data) < 0)
            return search(node.left, val);
        else
            return search(node.right, val);
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

    public void printInOrder() {
        inOrder(root);
        System.out.println();
    }

    void inOrder(TNode<T> node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.data + " ");
            inOrder(node.right);
        }
    }
}
