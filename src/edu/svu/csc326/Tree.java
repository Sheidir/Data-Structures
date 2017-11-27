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
 */
public interface Tree<T extends Comparable> {
    
    public Tree add(T element);
    public int contains(T element);
    public Tree rebalance();
    public Tree remove();
    public T retrieve();
    public boolean isEmpty();
    public int getDepth();
    public Iterator iterator();
}

