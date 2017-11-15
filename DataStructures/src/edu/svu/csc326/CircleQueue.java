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
public class CircleQueue<E> implements Queue<E>{
    
    private E[] list;
    private int front;
    private int rear;
    private int count;
    
    
    public CircleQueue(){
    this(20);
    
    }
    
    public CircleQueue(int maxSize){
    list = (E[]) new Object[maxSize];
    front = 0;
    rear = 0;
    count = 0;
    
    }
    

    @Override
    public boolean isEmpty() {
    return count == 0;

    }

    @Override
    public boolean isFull() {
        return count ==list.length;    }

    @Override
    public E front() {
        if(isEmpty()){
        throw new IndexOutOfBoundsException("Attempt to access the front of empty queue");
        
        }
        return list[front];    }

    @Override
    public E rear() {
      if(isEmpty()){
        throw new IndexOutOfBoundsException("Attempt to access the rear of empty queue");
        
        }
        return list[rear];  
    }

    @Override
    public Queue add(E element) {
        if(isFull()){
        throw new IndexOutOfBoundsException("Attempt to add to full queue");
        
        }
        rear = (rear+1)%list.length;
        list[rear++] = element;
        return this;
    }

    @Override
    public E remove() {
        count--;
        E element = list[front++];
        front = (front+1)%list.length;
        return element;
    }
    
}
