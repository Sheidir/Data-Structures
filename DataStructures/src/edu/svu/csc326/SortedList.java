package edu.svu.csc326;

import java.util.Arrays;

/**
 * Sorted List data structure
 * @author Heidi
 * @param <T>
 */
public class SortedList<T extends Comparable> extends ArrayList<T> {

    private boolean sorted = true;
    SORT method = SORT.MERGE;
    public int quickSorts;
/**
 * Creates a new Sorted List with default size of 20.
 */
    public SortedList() {
        this(20);
    }
/**
 * Creates a new Sorted List.
 * @param maxSize How big the SortedList should be. 
 */
    public SortedList(int maxSize) {
        list = (T[]) new Comparable[maxSize];
        length = 0;
    }
    
    /**
     * Sorts using method.
     */
    public void sort(){
    sort(method); 
    }
/**
 * Performs a Bubble sort on the list.
 */
    private void bubbleSort() {
        //       if (!sorted) {
        boolean done = false;
        while (!done) {
            done = true;
            for (int i = 0; i < length - 1; i++) {
                if (list[i].compareTo(list[i + 1]) > 0) {
                    swap(i, i + 1);
                    done = false;

                }

            }

        }

        sorted = true;
        //}
    }
/**
 * performs a heap sort.
 */
    private void heapSort() {
   //    if(!sorted){
        for(int i = length /2 -1;i >= 0; i--){
         swap(0, i);   
        heapify(i);
        sorted = true;
           }
    //   }
    }
/**
 * Recursive heap sorting.
 * @param i 
 */
    private void heapify(int i) {
        quickSorts++;
        int left = 2 * i;
        int right = 2 * i + 1;
        int max = i;
        if (left <= length - 1 && list[left].compareTo(list[i]) > 0) {
            max = left;
        }
        if (right <= length - 1 && list[right].compareTo(list[max]) > 0) {
            max = right;
        }
        if (max != i) {
            swap(i, max);
            heapify(max);

        }

    
    }
/**
 * Performs insertion sort.
 */
    private void insertionSort() {
        //       if (!sorted) {
        for (int i = 1; i < length; i++) {
            int j = i;
            while (j > 0 && list[j].compareTo(list[j - 1]) < 0) {
                swap(j, j - 1);
                --j;
            }

        }
        sorted = true;

        //       }
    }
/**
 * Merge sort helper method.
 */
    private void mergeSort() {
        T[] temp = (T[]) new Comparable[length];
        mergeSortPart(0, length - 1, temp);
        sorted = true;
    }
/**
 * merge Sort recursion method.
 * @param low
 * @param high
 * @param temp 
 */
    private void mergeSortPart(int low, int high, T[] temp) {
        if (low < high) {
            int mid = low + (high - low) / 2;
            mergeSortPart(low, mid, temp);
            mergeSortPart(mid + 1, high, temp);
            merge(low, mid, high, temp);

        }

    }
/**
 * Merge sorting method.
 * @param low
 * @param mid
 * @param high
 * @param temp 
 */
    private void merge(int low, int mid, int high, T[] temp) {
        for (int i = low; i <= high; i++) {
            temp[i] = list[i];
        }
        int i = low;
        int j = mid + 1;
        int k = low;
        while (i <= mid && j <= high) {
            if (temp[i].compareTo(temp[j]) < 0) {
                list[k++] = temp[i++];
            } else {
                list[k++] = temp[j++];
            }

        }
        while (i <= mid) {
            list[k++] = temp[i++];
        }

    }
/**
 * Performs a quick sort. Helper method.
 */
    private void quickSort() {
        // if(!sorted){
        quickSortPart(0, length - 1);
        sorted = true;
        

        //}
    }
/**
 * Quick sort recursive method.
 * @param low
 * @param high 
 */
    private void quickSortPart(int low, int high) {
        T pivot = list[low + (high - low) / 2];
        int i = low;
        int j = high;
       
            while (i <= j) {
            while (list[i].compareTo(pivot) < 0) {
                i++;
            }
            while (list[j].compareTo(pivot) > 0) {
                j--;
            }
           if (i <= j) {
                swap(i, j);
                i++;
                j--;
           }
           }
            if (low < j) {               
                quickSortPart(low, j);
            }
            if (i < high) {
                quickSortPart(i, high);
            }

        

    }
/**
 * Performs selection sort.
 */
    private void selectionSort() {
//        if (!sorted) {
        for (int i = 0; i < length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < length; j++) {
                if (list[j].compareTo(list[index]) < 0) {
                    index = j;
                }
            }
            swap(index, i);

        }
        sorted = true;
        //   }
    }
/**
 * Performs a shellSort
 */
    private void shellSort() {
        //       if (!sorted) {
        for (int g = length / 2; g > 0; g = g / 2) {
            for (int i = g; i < length; ++i) {
                T temp = list[i];
                int j = i;
                while (j >= g && list[j - g].compareTo(temp) > 0) {
                    list[j] = list[j - g];
                    j -= g;

                }
                list[j] = temp;

            }
        }
        sorted = true;
        //}
    }

    public enum SORT {
        BUBBLE, HEAP, INSERTION, MERGE, QUICK, SELECTION, SHELL;

    }
/**
 * Switches two elements in the list.
 * @param i
 * @param j 
 */
    private void swap(int i, int j) {
        T temp = list[i];
        list[i] = list[j];
        list[j] = temp;

    }
/**
 * Determines if an element is in the SortedList and returns the index.
 * @param element
 * @return 
 */
    @Override
    public int contains(T element) {
        if (!sorted) {
            sort(method);
        }
        int low = 0;
        int high = length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (element.compareTo(list[mid]) < 0) {
                high = mid - 1;
            } else if (element.compareTo(list[mid]) > 0) {
                low = mid + 1;

            } else {

                return mid;
            }
        }

        return -1;
    }
/**
 * Sorts given the provided sort.
 * @param method SORT method to use
 * @return Sorted List.
 */
    private List sort(SORT method) {
        switch (method) {
            case BUBBLE:
                bubbleSort();
                return this;
            case HEAP:
                heapSort();
                return this;
            case INSERTION:
                insertionSort();
                return this;
            case SHELL:
                shellSort();
                return this;
            case QUICK:
                quickSort();
                return this;
            case SELECTION:
                selectionSort();
                return this;
            default:
                mergeSort();

                return this;

        }

    }
/**
 * Sets the sort.
 * @param newSort 
 */
    public void setSort(SORT newSort) {
        method = newSort;
    }
/**
 * Inserts at a given index.
 * @param index
 * @param element
 * @return 
 */
    @Override
    public List insert(int index, T element) {
        super.insert(index, element);
        sorted = false;
        return this;
    }
/**
 * Inserts at the appropriate location.
 * @param element
 * @return 
 */
    public List insert(T element) {
        if (sorted) {
            int index;
            int i = 0;
            while(list[i].compareTo(element) > 0){
            ++i;
            }
            index = i;
            insert(index, element);
            sorted = true;
        }
        return this;
    }
/**
 * Reverses a the list.
 * @return 
 */
    public List reverseList() {

        for (int i = 0; i < length / 2; i++) {
            int index = Math.abs(i - length + 1);
            swap(index, i);
            sorted = false;
        }
        return this;

    }
/**
 * Prints out the contents of the list.
 * @return 
 */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("List(").append(length).append(") = [");

        for (int i = 0; i < length - 1; ++i) {
            s.append(list[i]);
            s.append(", ");
        }

        s.append("]");
        return s.toString();
    }
}
