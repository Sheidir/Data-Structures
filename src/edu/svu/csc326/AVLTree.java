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

    @Override
    public int size() {
        return size;
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

    

    public AVLTree(){
       size = 0;
    }
    @Override
    public Tree add(T element) {

        size++;
        return this;
    }



    public Tree rebalance() {
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
    
}

