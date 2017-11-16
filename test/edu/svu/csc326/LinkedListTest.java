
package edu.svu.csc326;

import edu.svu.csc326.LinkedList.ReverseIterator;
import java.util.Iterator;
import java.util.stream.Stream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Heidi
 */
public class LinkedListTest {
    
    public LinkedListTest() {
        
    }
    LinkedList<String> testList = new LinkedList<>();
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        testList = new LinkedList<>();
        testList.append("One").append("Two").append("Three");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of append method, of class LinkedList.
     */
    @Test
    public void testAppend() {
        System.out.println("append");
        Object o = "Pirate";
        LinkedList instance = new LinkedList();
        instance.append(o);
        Object expResult = 0;
        Object result = instance.contains(o);
        assertEquals(expResult, result);
        testList.append((String)o);
        result = testList.contains((String)o);
        expResult = 3;
        assertEquals(expResult, result);
    }

    /**
     * Test of isEmpty method, of class LinkedList.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        LinkedList instance = new LinkedList();
        boolean expResult = true;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
        instance = testList;
        result = instance.isEmpty();
        expResult = false;
        assertEquals(expResult, result);

    }

    /**
     * Test of getLength method, of class LinkedList.
     */
    @Test
    public void testGetLength() {
        System.out.println("getLength");
        LinkedList instance = new LinkedList();
        int expResult = 0;
        int result = instance.getLength();
        assertEquals(expResult, result);
        instance = testList;
        expResult = 3;
        result = instance.getLength();
        assertEquals(expResult, result);
    }

    /**
     * Test of retrieve method, of class LinkedList.
     */
    @Test
    public void testRetrieve() {
        System.out.println("retrieve");
        int index = 0;
        LinkedList instance = new LinkedList();
        Object result = null;
        try{
               result = instance.retrieve(index);
               fail("Retrieve from empty List failed to cause an exception");
        }catch(IndexOutOfBoundsException ex){
            System.out.println("Retrieving from empty List caused an exception");
        
        }
                
        instance = testList;
        try{
        instance.retrieve(4);
        }catch(IndexOutOfBoundsException ex){
        System.out.println("Retrieving beyond the Bounds caused an exception");        
        
        }
        try{
        instance.retrieve(-1);
        fail("Negative index failed to cause an exception");
        
        }catch(IndexOutOfBoundsException ex){
        System.out.println("Negative index caused an exception.");
        
        }
        Object expResult = "One";
        result = instance.retrieve(index);
        assertEquals(expResult, result);
        

    }

    /**
     * Test of prepend method, of class LinkedList.
     */
    @Test
    public void testPrepend() {
        System.out.println("prepend");
        Object element = "five";
        LinkedList instance = new LinkedList();
        instance.prepend(element);
        
        Object expResult = element;
        Object result = instance.retrieve(0);
        assertEquals(expResult, result);
        testList.prepend((String)element);
        result = instance.retrieve(0);
        expResult = element;
        assertEquals(expResult, result);

    }

    /**
     * Test of insert method, of class LinkedList.
     */
    @Test
    public void testInsert() {
        System.out.println("insert");
        int index = 0;
        Object element = "Twelve";
        LinkedList instance = new LinkedList();
        try{
        instance.insert(-1, element);
        fail("Negative index did not cause an Exception");
        }catch(IndexOutOfBoundsException ex){
        System.out.println("Negative index caused an Exception");
        
        }
        try{
        instance.insert(3, element);
        fail("Inserting beyond the bounds did not cause an Exception");
        }catch(IndexOutOfBoundsException ex){
        System.out.println("Inserting beyond the bounds caused an Exception");
        
        }
        testList.insert(index, (String)element);
        Object result = testList.retrieve(index);
        Object expResult = "Twelve";
        
        assertEquals(expResult, result);
        index = 1;
        testList.insert(index, (String) element);
        expResult = element;
        result = testList.retrieve(index);
        assertEquals(expResult, result);
        testList.append("Three").append("Eight").append("Twelve");
        index = 5; //Forcing it to iterate backwards.
        testList.insert(index, (String)element);
        result = testList.retrieve(index);
        expResult = element;
        assertEquals(result, expResult);
        
       
    }

    /**
     * Test of remove method, of class LinkedList.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        int index = 0;
        LinkedList instance = new LinkedList();
        Object expResult = null;
        Object result = null;
        try{
        instance.remove(index);
        fail("Removing from empty List failed to cause an exception");
        
        }catch(IndexOutOfBoundsException ex){
        System.out.println("Removing from empty list caused an exception");
        }
        try{
        instance.remove(-1);
        fail("Negative index failed to cause an exception");
        
        }catch(IndexOutOfBoundsException ex){
        System.out.println("Negative index from empty list caused an exception");
        }
        testList.remove(index);
        result = testList.contains("One");
        expResult = -1;
        assertEquals(expResult, result);
        result = testList.getLength();
        expResult = 2;
        assertEquals(expResult, result);
        
    }

    /**
     * Test of replace method, of class LinkedList.
     */
    @Test
    public void testReplace() {
        System.out.println("replace");
        int index = 0;
        Object element = "Five";
        LinkedList instance = new LinkedList();
        try{
            instance.replace(index, element);
            fail("Removing from an empty list failed to cause an exception");
        
        
        }catch(IndexOutOfBoundsException ex){
            System.out.println("Removing from an empty list caused an exception");
        
        }
        try{
            instance.replace(-1, element);
            fail("Negative index failed to cause an exception");  

    }catch(IndexOutOfBoundsException ex){
        System.out.println("Negative index caused an exception");
}   testList.replace(index, (String)element);
    Object result = testList.contains((String)element);
     Object expResult = index;           
        assertEquals(expResult, result);
    result = testList.contains("One");
    expResult = -1;
    assertEquals(expResult, result);
        


}

    /**
     * Test of contains method, of class LinkedList.
     */
    @Test
    public void testContains() {
        System.out.println("contains");
        Object element = "Twelve";
        LinkedList instance = new LinkedList();        
        int expResult = -1;        
        int result = instance.contains(element);
        assertEquals(expResult, result);
        expResult = 2;
        result = testList.contains("Three");
        assertEquals(expResult, result);
    }

    /**
     * Test of iterator method, of class LinkedList.
     */
    @Test
    public void testIterator() {
        System.out.println("iterator");
        int expResult = 0;
        int result = 0;
        assertEquals(expResult, result);
        int index = 0;
        for (String i: testList){
            result = testList.contains(i);
            expResult = index;
            assertEquals(result, expResult);           
            ++index;
        
        }
        
    }

    /**
     * Test of stream method, of class LinkedList.
     */
    @Test
    public void testStream() {
        System.out.println("stream");
        Stream result = testList.stream();

        int length = (int)result.count();
        int expResult = 3;      
        assertEquals(length, expResult);          
        assertTrue(result instanceof Stream);
        LinkedList<Integer> instance = new LinkedList();
        instance.append(1).append(5).append(9);
        int total = instance.stream().mapToInt(i -> i).sum();
        expResult = 15;
        assertEquals(total, expResult);
//        Object[] arrayAgain = result.toArray();
//        for(int i = 0; i<testList.getLength()-1; ++i){
//        assertEquals(arrayAgain[i], testList.retrieve(i));
        
//    }
    }

    /**
     * Test of toString method, of class LinkedList.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        LinkedList instance = new LinkedList();
        String expResult = "List(0) = []";
        String result = instance.toString();
        assertEquals(expResult, result);
        result = testList.toString();
        expResult = "List(3) = [One, Two, Three]";
        assertEquals(expResult, result);
    }

    /**
     * Test of reverseIterator method, of class LinkedList.
     */
    @Test
    public void testReverseIterator() {
        System.out.println("reverseIterator");
        int result = 0;
        Iterator it = testList.reverseIterator();
        
        int i = testList.getLength() -1;
        while (it.hasNext()) {
            
            result = testList.contains((String)it.next());
            assertEquals(result, i);
            i--;
            

        }   
    }
    
}