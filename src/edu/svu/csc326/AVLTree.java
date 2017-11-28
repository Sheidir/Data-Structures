/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.svu.csc326;

import java.util.Iterator;

/**
 *
 * @author sharo
 * @param <T>
 */
public class AVLTree<T extends Comparable> implements Tree<T>{
    private Node root;
    int size;
    
    public AVLTree(){
       size = 0;
    }

    @Override
    public int size() {
        return size;
    }
    public int depth(){
        return depth(root);
    }
    
    private int depth(Node n){
        if(n==null){
            return 0;
        }
        return 1 + Math.max(depth(n.lchild), depth(n.rchild));
    }
    public boolean balanced(){
    
    return balanced(root);
    
    }
    private boolean balanced(Node n){
    
    return Math.abs(depth(n.rchild) - depth(n.lchild)) >1;//check this. 
    }

    
    
    

 

    @Override
    public boolean contains(T element) {
    return contains(element, root);
    }
    private boolean contains(T element, Node n){
     if (n== null){
         return false;
     }
     if(n.data.compareTo(element) == 0){
         return true;
     }
     if(n.data.compareTo(element) > 0){
     return contains(element, n.lchild);
     
     }else{
     return contains(element, n.rchild);
     }
    }
    
    private class Node<T extends Comparable>{
        private T data;
        private Node parent;
        Node lchild;   
        Node rchild;
        public Node(T data, Node parent){
        this.data = data;
        this.parent = parent;
        }
            public String toString(){
            return "(" + data + " " + lchild + " " + rchild + ")";
            }
        }

 public Tree<T> add(T element) {
        if(root==null){
            root = new Node(element, null);
            return this;
        }
        return add(element, root);
    }

    private Tree<T> add(T element, Node n) {
        if (n.data.compareTo(element) == 0) {
            //duplicate node
            return this;
        } else if (n.data.compareTo(element) > 0) {
            if (n.lchild == null) {
                n.lchild = new Node(element, n);
                if(!balanced(n.parent)){
                rebalance(n.parent);
                }
                return this;
            } else {
                return add(element, n.lchild);
            }
        } else {
            if (n.rchild == null) {
                n.rchild = new Node(element, n);
                 if(!balanced(n.parent)){
                rebalance(n.parent);
                }
                return this;
            } else {
                return add(element, n.rchild);
            }
        }
        //ask if tree is balanced.
    }



    public Tree rebalance(Node n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tree remove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T retrieve() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }
    @Override
    public Iterator<T> preIterator() {
        
      return new PreOrderIterator();   
        

    }
           
    public Iterator<T> postIterator() {
    return new PostOrderIterator();
    
    }
   class PreOrderIterator implements Iterator<T>{
Queue <T> nodes = new LinkedQueue<>();
    public PreOrderIterator(){
    addNode(root);
    
    }
    void addNode(Node n){
    if (n != null){
    addNode(n.lchild);
    addNode(n.rchild);
    nodes.add((T)n.data);
    
    }
    }
    @Override
    public boolean hasNext(){
    return !nodes.isEmpty();
    }
    @Override
    public T next(){
    return nodes.remove();
    
    }

   }
   class PostOrderIterator implements Iterator<T>{
    Queue <T> nodes = new LinkedQueue<>();
    public PostOrderIterator(){
    addNode(root);
    
    }
    void addNode(Node n){
    if (n != null){
    addNode(n.lchild);
    addNode(n.rchild);
    nodes.add((T)n.data);
    
    }
    }
    @Override
    public boolean hasNext(){
    return !nodes.isEmpty();
    }
    @Override
    public T next(){
    return nodes.remove();
    
    }
    
    }
       @Override
    public Iterator<T> inIterator() {
        return new InOrderIterator();
    }
    class InOrderIterator implements Iterator<T>{
        Queue<T> nodes = new LinkedQueue<>();
        
        public InOrderIterator(){
            addNode(root);
            
        }
        
        void addNode(Node n){
        if (n!= null){
        addNode(n.lchild);
        nodes.add((T)n.data);
        addNode(n.rchild);
        }
        }
        @Override
        public boolean hasNext(){
            return !nodes.isEmpty();
        }
        @Override
        public T next(){
            return nodes.remove();
        }
    }
}

