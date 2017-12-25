package edu.svu.csc326;

import java.util.Iterator;

/**
 * Implementation of an AVL Tree
 *
 * @author Heidi Smith
 * @param <T>
 */
public class AVLTree<T extends Comparable> implements Tree<T> {

    private Node root;
    int size;

    /**
     * Default Constructor
     */
    public AVLTree() {
        size = 0;
    }

    /**
     * The building blocks of the AVL Tree. Has parent, and two children.
     *
     * @param <T>
     */
    private class Node<T extends Comparable> {

        private T data;
        private Node parent;
        Node lchild;
        Node rchild;

        /**
         * Constructor takes data and a parent node.
         *
         * @param data
         * @param parent
         */
        public Node(T data, Node parent) {
            this.data = data;
            this.parent = parent;
        }

        public String toString() {
            return "(data: " + data + " lchild: " + lchild + " rchild: " + rchild + ")";
        }

        /**
         * Counts the number of descendents on a line, given that the other line
         * is null. Useful for calculating depth.
         *
         * @return the number of descendents.
         */
        public int numDescendents() {
            return numDescendents(0);
        }

        private int numDescendents(int num) {
            if (rchild != null && lchild == null) {
                return rchild.numDescendents(++num);
            } else if (rchild == null && lchild != null) {
                return lchild.numDescendents(++num);
            }
            return num;
        }
    }

    /**
     * Link helper method.
     *
     * @param parent
     * @param child
     * @param rightChild True if this is the right child, false if left child.
     */
    private void link(Node parent, Node child, boolean rightChild) {
        child.parent = parent;
        if (rightChild) {
            parent.rchild = child;
        } else {
            parent.lchild = child;
        }

    }

    /**
     * Returns size of tree.
     *
     * @return
     */
    @Override
    public int size() {
        return size;
    }

    public int balanceFactor(Node n) {
        int rfactor;
        int lfactor;
        if (n.rchild != null) {
            rfactor = 1 + depth(n.rchild);
        } else {
            rfactor = 0;
        }

        if (n.lchild != null) {
            lfactor = 1 + depth(n.lchild);
        } else {
            lfactor = 0;
        }

        return lfactor - rfactor;
    }

    /**
     * Returns depth of tree
     *
     * @return
     */
    private int depth() {
        return depth(root);
    }

    private int depth(Node n) {
        if (n == null) {
            return 0;
        }
        if (n.lchild != null && n.rchild != null) {
            return 1 + Math.max(depth(n.lchild), depth(n.rchild));
        } else if (n.lchild != null) {
            return 1 + n.lchild.numDescendents();
        } else if (n.rchild != null) {
            return 1 + n.rchild.numDescendents();
        }
        return 0;
    }

    @Override
    public String toString() {
        Iterator it = inIterator();
        StringBuilder s = new StringBuilder();
        while (it.hasNext()) {
            s.append(it.next().toString());

        }
        return s.toString();
    }

    public Comparable getRoot() {
        return root.data;
    }

    /**
     *
     * Checks whether the Tree is balanced, and starts on appropriate action.
     *
     */
    public void balance() {
        int balance = balanceFactor(root);
        if (root != null && balance > 1) {
            rebalance(root, true, balance);
        } else if (root != null && balance < -1) {
            rebalance(root, false, balance);
        }

    }

    /**
     * Checks whether an element is contained in the tree.
     *
     * @param element
     * @return true if it is, false otherwise
     */
    @Override
    public boolean contains(T element) {
        return contains(element, root, false);
    }

    /**
     * Checks whether the Tree contains an element. Ties into the remove
     * function if remove is true.
     *
     * @param element
     * @param n
     * @param remove
     * @return
     */
    private boolean contains(T element, Node n, boolean remove) {
        if (n == null) {
            return false;
        }
        if (n.data.compareTo(element) == 0) {
            if (remove) {

            }
            return true;
        }
        if (n.data.compareTo(element) > 0) {
            return contains(element, n.lchild, remove);

        } else {
            return contains(element, n.rchild, remove);
        }
    }

    /**
     * Adds element to the tree, inserting in order, and rebalancing as
     * necessary. Does not allow duplicates.
     *
     * @param element
     * @return
     */
    public Tree<T> add(T element) {
        if (root == null) {
            root = new Node(element, null);
            size++;
            return this;
        }
        size++;
        return add(element, root);

    }

    /**
     * Recursive adding method.
     *
     * @param element
     * @param n
     * @return
     */
    private Tree<T> add(T element, Node n) {
        if (contains(element)) {
            System.out.print("Add failed. No duplicates allowed on AVL tree");
            return this;
        } else if (n.data.compareTo(element) > 0) {
            if (n.lchild == null) {
                n.lchild = new Node(element, n);
                n.lchild.parent = n;
                balance();
            } else {
                return add(element, n.lchild);
            }

        } else {
            if (n.rchild == null) {
                n.rchild = new Node(element, n);
                n.rchild.parent = n;
                balance();
                return this;
            } else {
                return add(element, n.rchild);
            }
        }
        return this;
    }

    /**
     * Checks whether both numbers are negative or non-negative.
     *
     * @param one
     * @param two
     * @return true if they share same sign, otherwise false.
     */
    private boolean matchSigns(int one, int two) {
        if (one == 0 || two == 0) {
            return true;
        }
        if (one > 0 && two > 0) {
            return true;
        }
        if (one < 0 && two < 0) {
            return true;
        }
        return false;
    }

    /**
     * Rebalances the tree recursively
     *
     * @param n the node in question
     * @param right whether the node requires a right turn to bring it into
     * balance.
     * @param balanceFactor the depth of the left subtree - the depth of the
     * right subtree.
     */
    private void rebalance(Node n, boolean right, int balanceFactor) {
        Node child;
        if (balanceFactor == 2 || balanceFactor == -2) {
            if (balanceFactor == -2) {
                child = n.rchild;
            } else {
                child = n.lchild;
            }
            int childFactor = balanceFactor(child);
            if (childFactor == balanceFactor) {
                rebalance(child, right, childFactor);
            } else if (!matchSigns(childFactor, balanceFactor)) { //if signs do not match, double rotation required.
                rebalance(child, !right, childFactor);
            }
        }
        balanceFactor = balanceFactor(root); //Check again if root is balanced.
        if (balanceFactor > 1 || balanceFactor < -1) {
            rotate(n, right);
        }
    }

    /**
     * Rotates the node.
     *
     * @param n the node to rotate.
     * @param right true for right rotation, false for left.
     */
    private void rotate(Node n, boolean right) {
        boolean rootFlagged = true;
        Node parent;
        Node tempP = n.parent;
        Node tempC;
        if (right) {
            parent = n.lchild;
            tempC = parent.rchild;
        } else {
            parent = n.rchild;
            tempC = parent.lchild;
        }
        if (n != root) {
            rootFlagged = false;
            link(tempP, parent, n == tempP.rchild);
        }
        link(parent, n, right);
        if (right) {
            n.lchild = tempC;
        } else {
            n.rchild = tempC;
        }

        if (rootFlagged) {
            root = parent;
            root.parent = null;
        }
    }

    /**
     * Finds and removes provided element.
     *
     * @param element
     * @return
     */
    @Override
    public Tree remove(T element) {
        contains(element, root, true);
        return this;

    }

    /**
     * Returns whether it is empty.
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Performs a pre-order iteration.
     *
     * @return
     */
    @Override
    public Iterator<T> preIterator() {

        return new PreOrderIterator();

    }

    /**
     * Performs a post-order iteration.
     *
     * @return
     */
    public Iterator<T> postIterator() {
        return new PostOrderIterator();

    }

    class PreOrderIterator implements Iterator<T> {

        Queue<T> nodes = new LinkedQueue<>();

        public PreOrderIterator() {
            addNode(root);

        }

        void addNode(Node n) {
            if (n != null) {
                nodes.add((T) n.data);
                addNode(n.lchild);
                addNode(n.rchild);

            }
        }

        @Override
        public boolean hasNext() {
            return !nodes.isEmpty();
        }

        @Override
        public T next() {
            return nodes.remove();

        }

    }

    class PostOrderIterator implements Iterator<T> {

        Queue<T> nodes = new LinkedQueue<>();

        public PostOrderIterator() {
            addNode(root);

        }

        void addNode(Node n) {
            if (n != null) {
                addNode(n.lchild);
                addNode(n.rchild);
                nodes.add((T) n.data);

            }
        }

        @Override
        public boolean hasNext() {
            return !nodes.isEmpty();
        }

        @Override
        public T next() {
            return nodes.remove();

        }

    }

    /**
     * Returns an in order iteration.
     *
     * @return
     */
    @Override
    public Iterator<T> inIterator() {
        return new InOrderIterator();
    }

    class InOrderIterator implements Iterator<T> {

        Queue<T> nodes = new LinkedQueue<>();

        public InOrderIterator() {
            addNode(root);

        }

        void addNode(Node n) {
            if (n != null) {
                addNode(n.lchild);
                nodes.add((T) n.data);
                addNode(n.rchild);
            }
        }

        @Override
        public boolean hasNext() {
            return !nodes.isEmpty();
        }

        @Override
        public T next() {
            return nodes.remove();
        }
    }

    public static void main(String[] args) {
        AVLTree<String> t = new AVLTree<>();
        System.out.println("Tree is ");
        if (t.isEmpty()) {
            System.out.println("empty");
        } else {
            System.out.println("not empty");
        }
        t.add("A");
        System.out.println("Added 'A' Root is " + t.root.data);
        t.add("D")
                .add("B");
        System.out.println("Added 'D' and 'B' Root is " + t.root.data);
        t.add("C");
        System.out.println("Added 'C' Root is " + t.root.data);

        t.add("E").add("Z").add("M").add("X");
        System.out.println("Added E, Z, M, X. Root is " + t.root.data);
        System.out.println(t.toString());
        System.out.println("Element in order:");
        Iterator<String> it = t.inIterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println("Adding elements");

        t.add("Q").add("F").add("T");
        System.out.println("The root is " + t.root.data);
        System.out.println("InOrder");
        it = t.inIterator();
        while (it.hasNext()) {
            System.out.print(it.next());
        }
        System.out.println("PreOrder");
        it = t.preIterator();
        while (it.hasNext()) {
            System.out.print(it.next());
        }
        System.out.println("PostOrder");
        it = t.postIterator();
        while (it.hasNext()) {
            System.out.print(it.next());
        }
        System.out.println("The depth of the tree is" + t.depth());
    }
}
