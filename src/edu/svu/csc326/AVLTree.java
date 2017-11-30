package edu.svu.csc326;

import java.util.Iterator;

/**
 * Implementation of an AVL Tree
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
 * @param <T> 
 */
    private class Node<T extends Comparable> {

        private T data;
        private Node parent;
        Node lchild;
        Node rchild;

        /**
         * Constructor takes data and a parent node.
         * @param data
         * @param parent 
         */
        public Node(T data, Node parent) {
            this.data = data;
            this.parent = parent;
        }

        public String toString() {
            return "(" + data + " " + lchild + " " + rchild + ")";
        }
/**
 * Counts the number of descendents on a line, given that the other line is null. Useful for calculating depth.
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
     * @param parent
     * @param child
     * @param rightChild True if this is the right child, false if left child.
     */
    private void link(Node parent, Node child, boolean rightChild){
    child.parent = parent;
    if(rightChild ){
        parent.rchild = child;
    }
    else{
        parent.lchild = child;
    }
    
    }
/**
 * Returns size of tree.
 * @return 
 */
    @Override
    public int size() {
        return size;
    }
/**
 * Returns depth of tree
 * @return 
 */
    public int depth() {
        return depth(root);
    }

    private int depth(Node n) {
        if (n == null) {
            return 0;
        }
        if (n.lchild != null && n.rchild != null) {
            return 1 + Math.max(depth(n.lchild), depth(n.rchild));
        }else if(n.lchild != null){
        return 1 + n.lchild.numDescendents();
        }else if (n.rchild != null){
        return 1 + n.rchild.numDescendents();
        }
        return 0;
    }
/**
 * Returns whether the tree is balanced.
 * @return 
 */
    public boolean balanced() {
        if (root == null) {
            return true;
        }
        return balanced(root);

    }

    private boolean balanced(Node n) {
        if (n.rchild != null && n.lchild != null) {
            return Math.abs(depth(n.rchild) - depth(n.lchild)) <= 1;//check this. 
        } else {
            if (n.rchild != null && n.numDescendents() <= 1) {
                return true;
            }
            if (n.lchild != null && n.numDescendents() <= 1) {
                return true;
            }
        }
        return false;
    }
/**
 * Checks whether an element is contained in the tree. 
 * @param element
 * @return true if it is, false otherwise
 */
    @Override
    public boolean contains(T element) {
        return contains(element, root);
    }

    private boolean contains(T element, Node n) {
        if (n == null) {
            return false;
        }
        if (n.data.compareTo(element) == 0) {
            return true;
        }
        if (n.data.compareTo(element) > 0) {
            return contains(element, n.lchild);

        } else {
            return contains(element, n.rchild);
        }
    }
/**
 * Adds element to the tree, inserting in order, and rebalancing as necessary.
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

    private Tree<T> add(T element, Node n) {
        if (contains(element)) {
            System.out.print("Add failed. No duplicates allowed on AVL tree");
            return this;
        } else if (n.data.compareTo(element) > 0) {
            if (n.lchild == null) {
                n.lchild = new Node(element, n);
                n.lchild.parent = n;
                if (n != root) {
                    if (!balanced(n.parent)) {
                        rebalance(n.parent);
                    }
                } else if (!balanced(n)) {
                    rebalance(n);
                    return this;
                }
            } else {
                return add(element, n.lchild);
            }

        } else {
            if (n.rchild == null) {
                n.rchild = new Node(element, n);
                n.rchild.parent = n;
                if (n != root && !balanced(n.parent)) {
                    rebalance(n.parent);
                }
                return this;
            } else {
                return add(element, n.rchild);
            }
        }
        return this;
    }
/**
 * Rebalancing method.
 * @param n
 * @return 
 */
    private Tree rebalance(Node n) {
        if (depth(n.rchild) < depth(n.lchild)) {
            rotateRight(n);
        } else if (depth(n.rchild) > depth(n.lchild)) {
            rotateLeft(n);
        }
        if (!balanced()) {
            rebalance(n.parent);
        }
        return this;
    }

    private Tree rotateRight(Node n) {
        boolean rootFlagged = false;
        Node parent;
        Node temp = n.parent;
        if (n == root) {
            rootFlagged = true;           
        }
            parent = n.lchild;
        link(parent, n, true);
        n.lchild = null;
        parent.parent = temp;
        if (rootFlagged) {
            root = parent;
        }

        return this;
    }

    private Tree rotateLeft(Node n) {
        boolean rootFlagged = false;
        Node parent;
        Node temp = n.parent;
        if (n == root) {
            rootFlagged = true;
        }
        parent = n.rchild;
        link(parent, n, false);
        n.rchild = null;
        parent.parent = temp;
        if (rootFlagged) {
            root = parent;
        }
        return this;
    }
/**
 * Removes element from the tree.
 * @return 
 */
    @Override
    public Tree remove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
/**
 * Returns whether it is empty.
 * @return 
 */
    @Override
    public boolean isEmpty() {
        return root == null;
    }
/**
 * Performs a pre-order iteration.
 * @return 
 */
    @Override
    public Iterator<T> preIterator() {

        return new PreOrderIterator();

    }
/**
 * Performs a post-order iteration.
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
        t.add("D").add("B").add("A").add("C").add("E").add("Z").add("M").add("X");
        System.out.println("Root is " + t.root.data);
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
