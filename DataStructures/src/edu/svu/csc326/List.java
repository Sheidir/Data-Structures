/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.svu.csc326;

import java.util.Iterator;
import java.util.stream.Stream;

/**
 *
 * @author Heidi
 */
public interface List<E> extends Iterable<E>{

    public List<E> append(E o);

    public boolean isEmpty();

    public int getLength();

    public E retrieve(int index);

    public List<E> prepend(E element);

    public List<E> insert(int index, E element);

    public List<E> remove(int index);

    public List<E> replace(int index, E element);

    public int contains(E element);

    public Iterator<E> iterator();

    public Stream<E> stream();

}
