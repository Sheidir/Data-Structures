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
public interface Tree<T extends Comparable> {
    
    public Tree add(T element);
    boolean contains(T element);
    public Tree remove();
    boolean isEmpty();
    public int size();
    public Iterator<T> preIterator();
    public Iterator<T> postIterator();
    public Iterator<T> inIterator();
    
}

