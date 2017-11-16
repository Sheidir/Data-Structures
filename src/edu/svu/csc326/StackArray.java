/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.svu.csc326;

/**
 * 
 * @author Heidi
 */
public class StackArray<E> implements Stack<E>{
    private ArrayList<E> list;

    public StackArray(){
    list = new ArrayList<>();
    }
    /**
     * Adds element to the top of the stack.
     * @param element
     * @return the modified stack
     */
    @Override
    public Stack<E> push(E element) {
        list.append(element);       
        return this;
    }
/**
 * Removes the top element on the stack.
 * @return the modified stack
 */
    @Override
    public Stack<E> pop() {
        list.remove(list.getLength()-1);
        return this;
    }
/**
 * Returns the element at the top of the stack
 * @return 
 */
    @Override
    public E top() {
        return list.retrieve(list.getLength()-1);
         }
/**
 * Returns the size of the stack.
 * @return 
 */
    @Override
    public int size() {
        return list.getLength();
    }
/**
 * Returns whether the stack is empty.
 * @return 
 */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
        }
    
}
