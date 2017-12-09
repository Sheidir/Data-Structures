/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.svu.csc326;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * Makes an ArrayList.
 * @author Heidi
 * @param <T> the kind of object it takes.
 */
public class ArrayList<T> implements List<T> {

    protected T[] list;
    protected int length;
/**
 * Default Constructor creates an ArrayList with size 20;
 */
    public ArrayList(ArrayList<T> oldList){
        this.list = Arrays.copyOf(oldList.list, oldList.length);
        this.length = oldList.length;
    }
    public ArrayList() {
        this(20);

    }
/**
 * Creates an array with the size maxSize.
 * @param maxSize is the initial size of the ArrayList
 */
    public ArrayList(int maxSize) {
        list = (T[]) new Object[maxSize];
        length = 0;

    }
/**
 * Returns whether the Array is empty.
 * @return 
 */
    @Override
    public boolean isEmpty() {
        return length == 0;
    }
/**
 * Returns the length of the array.
 * @return length.
 */
    @Override
    public int getLength() {
        return length;
    }
/**
 * Finds and returns the object at the specified index.
 * @param index the index of the requested object.
 * @return the object at the index.
 */
    @Override
    public T retrieve(int index) {
        if(index < 0 || index >= length){
            throw new IndexOutOfBoundsException();
        }
        
        return list[index];
    }
/**
 * Adds an object at the beginning of the list.
 * @param element the object to be added.
 * @return the new List.
 */
    @Override
    public List<T> prepend(T element) {
        return insert(0, element);

    }
/**
 * Inserts an object at end of the Array.
 * @param o the object to be inserted.
 * @return the List with the new object.
 */
    @Override
    public List<T> append(T o) {
        return insert(length, o);
    }
/**
 * Inserts an object at given index.
 * @param index where the object should be placed.
 * @param element the object to be inserted.
 * @return the updated list.
 */
    @Override
    public List<T> insert(int index, T element) {
        if(index < 0 || index > length){
        throw new IndexOutOfBoundsException();
        }
        if (length == list.length) {
            resize();
        }

        for (int i = length; i > index; --i) {
            list[i] = list[i - 1];

        }
        list[index] = element;
        length++;
        return this;
    }
/**
 * Removes object at given index.
 * @param index the index of the object to be removed.
 * @return the updated List.
 */
    @Override
    public List<T> remove(int index) {
        if(index < 0 || index >= length){
        throw new IndexOutOfBoundsException();
        }
        
        for (int i = index; i < length - 1; ++i) {
            list[i] = list[i + 1];

        }
        --length;
        return this;
    }
/**
 * Replaces the object at a given index with a given object.
 * @param index the index of the object to replace.
 * @param element the new object.
 * @return the updated List.
 */
    @Override
    public List<T> replace(int index, T element) {
                if(index < 0 || index >= length){
        throw new IndexOutOfBoundsException();
        }
        list[index] = element;
        return this;
    }
/**
 * Searches to see if the ArrayList contains an object.
 * @param element the object it is looking for.
 * @return the index of the object, or -1 if the object is not found.
 */
    @Override
    public int contains(T element) {
        for (int i = 0; i < length; ++i) {
            if (list[i].equals(element)) {
                return i;
            }

        }
        return -1;
    }
/**
 * Creates a new iterator.
 * @return 
 */
    @Override
    public Iterator<T> iterator() {
        return (new Iterator<T>() {
            private int next = 0;

            @Override
            public boolean hasNext() {
                return next < length;

            }

            @Override
            public T next() {
                return list[next++];

            }

        });
    }
/**
 * Converts the ArrayList to a Stream.
 * @return the created Stream.
 */
    @Override
    public Stream<T> stream() {
        return Arrays.stream(list, 0, length);
    }
/**
 * Resizes the ArrayList with twice the space it is currently using.
 */
    protected void resize() {
        list = Arrays.copyOf(list, length *2);
    }

}
