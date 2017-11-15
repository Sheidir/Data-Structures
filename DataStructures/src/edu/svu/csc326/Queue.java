/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.svu.csc326;

/**
 *
 * @author sharo
 */
public interface Queue<T> {
    
    public boolean isEmpty();
    public boolean isFull();
    public T front();
    public T rear();
    public Queue<T> add(T element);
    public T remove();
    public int size();
    
}
