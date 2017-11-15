/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.svu.csc326;

/**
 *
 * @author Heidi
 * @param <E>
 */
public interface Stack<E> {
    Stack<E> push(E element);
    Stack<E> pop();
    E top();
    int size();
    boolean isEmpty();
    
}
