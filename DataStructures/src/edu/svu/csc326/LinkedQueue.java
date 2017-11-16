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
public class LinkedQueue<T> implements Queue<T>{
    protected LinkedList<T> list;

    public LinkedQueue(){
    list = new LinkedList();
    }
    @Override
    public boolean isEmpty() {
      return list.isEmpty();
    }


    
    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public T front() {
        return list.retrieve(0);
    }

    @Override
    public T rear() {
        return list.retrieve(list.getLength()-1);
    }


    @Override
    public T remove() {
        T object = list.retrieve(0);
        list.remove(0);
        return object;
        
    }

    @Override
    public Queue add(T element) {
    list.append(element);
    return this;
    }

    @Override
    public int size() {
       return list.getLength();
    }
    
    
}
