/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.svu.csc326;

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
public class ArrayListTest {
    ArrayList<Integer> testList = new ArrayList<>();
    
    public ArrayListTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        testList = new ArrayList<>();
        testList.append(1).append(2).append(3);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of isEmpty method, of class ArrayList.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        ArrayList instance = new ArrayList();
        boolean expResult = true;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
        instance = testList;
        expResult = false;
        result = instance.isEmpty();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLength method, of class ArrayList.
     */
    @Test
    public void testGetLength() {
        System.out.println("getLength");
        ArrayList instance = new ArrayList();
        int expResult = 0;
        int result = instance.getLength();
        assertEquals(expResult, result);
        instance = testList;
        result = instance.getLength();
        expResult = 3;
        assertEquals(expResult, result);
    }

    /**
     * Test of retrieve method, of class ArrayList.
     */
    @Test
    public void testRetrieve() {
        System.out.println("retrieve");
        int index = 0;
        ArrayList<Integer> instance = new ArrayList();
        Object expResult = null;
        Object result = null;
        try{
            result = instance.retrieve(0);
        fail("Retrieve did not throw exception");
    }catch (IndexOutOfBoundsException ex){
    System.out.println("Retrieving from empty ArrayList caused an exception.");
    
    }
        instance = testList;
        result = instance.retrieve(index);
        expResult = 1;
        assertEquals(expResult, result);
        try{
                index = 3;
                result = instance.retrieve(index);
                fail("Retrieve from beyond the Array did not throw exception");
                
        }catch(IndexOutOfBoundsException ex){
        System.out.println("Retrieving from beyond the ArrayList caused an exception.");
        
        }
        
    }

    /**
     * Test of prepend method, of class ArrayList.
     */
    @Test
    public void testPrepend() {
        System.out.println("prepend");
        Object element = true;
        ArrayList<Boolean> instance = new ArrayList();
        instance.prepend((boolean) element);
        
        int expResult = 1;
        
        int result = instance.getLength();
        assertEquals(expResult, result); 

    }

    /**
     * Test of append method, of class ArrayList.
     */
    @Test
    public void testAppend() {
        System.out.println("append");
        int o = 10;
        ArrayList<Integer> instance = new ArrayList();
        instance.append(o);
        Object result = instance.retrieve(0);
        Object expResult = o;
        assertEquals(expResult, result);
        instance = testList;
        result = testList.retrieve(2);
        expResult = 3;
        assertEquals(expResult, result);
        

    }

    /**
     * Test of insert method, of class ArrayList.
     */
    @Test
    public void testInsert() {
        System.out.println("insert"); 
        int index = 2;
        Boolean o = true;
        Boolean q = false;
        ArrayList<Boolean> instance = new ArrayList();
        try{
            instance.insert(index, q);
            fail("Insert did not cause an Exception.");
        }catch(IndexOutOfBoundsException ex){
            System.out.println("Inserting beyond the length of the Array caused an exception");
        
        }
        try{
            index = -1;
            instance.insert(index, q);
            fail("Insert at a negative index did not cause an Exception.");
        }catch(IndexOutOfBoundsException ex){
            System.out.println("Inserting beyond the length of the Array caused an exception ");
        }
        for(int i = 0; i< 3; ++i){
        instance.append(q);
        }
        index = 2;
        instance.insert(index, o);
        Object result = instance.retrieve(index);
        Object expResult = o;
        assertEquals(expResult, result);

    }

    /**
     * Test of remove method, of class ArrayList.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        int index = 0;
        ArrayList<Integer> instance = new ArrayList();
        try{
        instance.remove(index);
        fail("Removing beyond the array caused an exception");
        }catch(IndexOutOfBoundsException ex){
        System.out.println("Removing nonexistent objects caused an exception.");
        }
        instance = testList;
        try{
            index = -1;
            instance.remove(index);
            fail("Negative index did not cause exception");
        }catch(IndexOutOfBoundsException ex){
            System.out.println("Negative index caused exception");
        }
        index = 2;
        instance.remove(index);
       
        int expResult = -1;
        assertEquals(expResult, instance.contains(3));

    }

    /**
     * Test of replace method, of class ArrayList.
     */
    @Test
    public void testReplace() {
        System.out.println("replace");
        int index = 0;
        ArrayList instance = new ArrayList();
        try{
        instance.replace(index, 7);
        fail("Replacing beyond the bounds did not cause an exception.");
        }catch(IndexOutOfBoundsException ex){
            System.out.println("Replacing beyond the bounds caused an exception.");
        
        }
        try{
            index = -1;
        instance.replace(index, 7);
        fail("Replacing negative index did not cause an exception.");
        
        }catch(IndexOutOfBoundsException ex){
            System.out.println("Negative index caused an exception");
        }
        index = 0;
        Integer element = 5;
        Integer newElement = 8;
        instance.append(element);
        instance.replace(index, newElement);
        assertEquals(instance.contains(newElement), index);
        assertEquals(instance.contains(element), -1);

    }

    /**
     * Test of contains method, of class ArrayList.
     */
    @Test
    public void testContains() {
        System.out.println("contains");
        Boolean element = true;
        ArrayList<Boolean> instance = new ArrayList();
        instance.append(element);
        int expResult = 0;
        int result = instance.contains(element);
        assertEquals(expResult, result);
        result = testList.contains(12);
        expResult = -1;
        assertEquals(expResult, result);
        

    }

    /**
     * Test of iterator method, of class ArrayList.
     */
    @Test
    public void testIterator() {
        System.out.println("iterator");      
        ArrayList<Boolean> instance = new ArrayList();
        for(int i = 0; i < 4; ++i){
            Boolean myBool = false;
            instance.append(myBool);
        }
        int cycler = 0;
        for (Boolean b: instance){
        boolean btest = b;
        
        ++cycler;
        }

        assertEquals(cycler, instance.getLength());

    }

    /**
     * Test of stream method, of class ArrayList.
     */
    @Test
    public void testStream() {
        System.out.println("stream");
        ArrayList instance = new ArrayList();
        Stream result = instance.stream();        
        Boolean expResult = true;
        Boolean checker = false;
        if(result instanceof Stream){
        checker = true;
        }
        
        assertEquals(expResult, checker);
///idk
    }
    
}
