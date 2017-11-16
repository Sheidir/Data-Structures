package edu.svu.csc326;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * Creates a doubly Linked List.
 *
 * @author Heidi
 */
public class LinkedList<E> implements List<E> {

    private int count;
    private ListNode head;
    private ListNode tail;

    /**
     * Iterates from tail.
     */
    public class ReverseIterator implements Iterator<E> {
        private StackArray stack;
        E result;
        public ReverseIterator() {
           stack = new StackArray<>();
           Iterator<E> it = new ListIterator();
           while(it.hasNext()){
               stack.push(it.next());
           }
        }
        

        @Override
        public boolean hasNext() {//Or in this case, has previous.
            return !stack.isEmpty();

        }
        @Override
        public E next() {
            result = (E)stack.top();
            stack.pop();

            return result;

        }
    }

    /**
     * Default iterator. Iterates from head.
     */
    private class ListIterator implements Iterator<E> {

        private ListNode ptr = head;

        /**
         * Checks if there is a next node in the list.
         *
         * @return
         */
        @Override
        public boolean hasNext() {
            return ptr != null;
        }

        /**
         * Implements the required method to take the ptr one step down the
         * List.
         *
         * @return
         */
        @Override
        public E next() {
            E result = ptr.data;
            ptr = ptr.next;
            return result;
        }

    }

    /**
     * Creates a node containing data of type E, and references to the next and
     * previous nodes in the list.
     */
    private class ListNode {

        private E data;
        private ListNode next;
        private ListNode prev;

        /**
         * Constructor creates a ListNode with data.
         *
         * @param data
         */
        public ListNode(E data) {
            this.data = data;

        }
    }

    /**
     * Adds a given object to the end of the list.
     *
     * @param o the object to be added.
     * @return the modified list.
     */
    @Override
    public List append(E o) {
        return insert(count, o);
    }

    /**
     * Checks if the list is empty.
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Returns the length of the list.
     *
     * @return
     */
    @Override
    public int getLength() {
        return count;
    }

    /**
     * Retrieves the object at a given index.
     *
     * @param index
     * @return
     */
    @Override
    public E retrieve(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            return head.data;
        }
        if (index == count - 1) {
            return tail.data;
        }
        ListNode ptr = null;
        ptr = goToIndex(index);

        return ptr.data;

    }

    /**
     * Inserts an object at the beginning of the list.
     *
     * @param element
     * @return
     */
    @Override
    public List prepend(E element) {
        return insert(0, element);
    }

    /**
     * Inserts an object at a given index.
     *
     * @param index
     * @param element
     * @return
     */
    @Override
    public List insert(int index, E element) {
        if (index < 0 || index > count) {
            throw new IndexOutOfBoundsException();

        }
        ListNode n = new ListNode(element);
        if (isEmpty()) {
            head = n;
            tail = n;
        } else if (index == 0) {
            link(n, head);
            head = n;

        } else if (index == count) {
            link(tail, n);
            tail = n;
            tail.next = null;

        } else {
            ListNode ptr;
            ptr = goToIndex(index);

            link(ptr.prev, n);
            link(n, ptr);

        }
        ++count;
        return this;
    }

    /**
     * removes the element from the given index.
     *
     * @param index
     * @return
     */
    @Override
    public List remove(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException();

        }
        if (index == 0) {
            if (count == 1) {
                head = null;
                tail = null;

            } else {
                head = head.next;
                head.prev = null;

            }

        } else {

            ListNode ptr;
            ptr = goToIndex(index);
            link(ptr.prev, ptr.next);
            if (ptr == tail) {
                tail = ptr.prev;
                tail.next = null;

            }

        }
        --count;
        return this;
    }

    /**
     * Replaces element at the given index with the given element.
     *
     * @param index
     * @param element
     * @return
     */
    @Override
    public List replace(int index, E element) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException();
        }
        ListNode n = new ListNode(element);
        if (index == 0) {
            if(head.next != null){
            link(n, head.next);
            }
            n.next = head.next;
            head = n;
        }
        if (count == 1) {
            tail = n;
        } else {
            ListNode ptr;
            ptr = goToIndex(index);
            link(ptr.prev, n);
            link(n, ptr.next);;
            if (tail == ptr) {
                tail = n;
            }
        }

        return this;
    }

    @Override
    public int contains(E element) {
        int index = 0;
        ListNode ptr = head;
        while (ptr != null) {
            if (ptr.data.equals(element)) {
                return index;
            }
            index++;
            ptr = ptr.next;
        }
        return -1;
    }
    
    public int containsR(E element){
        return containsR(head, element, 0);
        
    
    }
    private int containsR(ListNode ptr, E element, int index){
        if(ptr == null){
        return -1;
        }
        if (ptr.data == element){
        return index;
        }

        return containsR(ptr.next, element, ++index);
    }

    /**
     * Creates an instance of the default iterator.
     *
     * @return
     */
    @Override
    public Iterator<E> iterator() {
        return new ListIterator();
    }

    /**
     * Converts the Linked List into a Stream.
     *
     * @return
     */
    @Override
    public Stream<E> stream() {
        E[] array = (E[]) new Object[count];
        Iterator<E> it = new ListIterator();
        int i = 0;
        while (it.hasNext()) {
            array[i++] = it.next();

        }
        return Arrays.stream(array);
    }

    /**
     * Links two ListNodes in order. Mostly here for readability and simplicity.
     *
     * @param first
     * @param second
     */
    private void link(ListNode first, ListNode second) {
        first.next = second;
        second.prev = first;
    }

    /**
     * Provides a method for printing out all elements in the Linked List.
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("List(").append(count).append(") = [");
        ListNode ptr = head;
        while (ptr != null) {
            s.append(ptr.data);
            if (ptr != tail) {
                s.append(", ");
            }
            ptr = ptr.next;
        }
        s.append("]");
        return s.toString();
    }
    
    public int getCount(){
        int localCount = 0;
        ListNode ptr = head;
        while(ptr != null){
        ++localCount;
        ptr = ptr.next;
        }
        return localCount;
    }
    public int getCountR(){
        return getCountR(head);
    }
    private int getCountR(ListNode ptr){
        if (ptr == null) return 0;
        return 1 + getCountR(ptr.next);
    }

    /**
     * Iterates backwards through the linked list.
     *
     * @return an Iterator.
     */
    public Iterator<E> reverseIterator() {
        return new ReverseIterator();
    }

    /**
     * Traverses to the list to reach an index. Goes backwards or forwards
     * depending on which is more efficient.
     *
     * @param index
     * @return ListNode at index.
     */
    private ListNode goToIndex(int index) {
        ListNode ptr;
        if (index > count / 2) {
            ptr = tail;
            int i = count - 1;
            while (i > index) {
                ptr = ptr.prev;
                --i;

            }

        } else {
            int i = 1;
            ptr = head.next;
            while (i < index) {
                ptr = ptr.next;
                ++i;
            }

        }

        return ptr;

    }
}
