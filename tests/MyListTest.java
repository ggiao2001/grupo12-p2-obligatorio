import uy.edu.um.prog2.adt.Exceptions.OutOfBoundsException;
import uy.edu.um.prog2.adt.Entities.MyLinkedListImp;
import org.junit.Test;

import static org.junit.Assert.*;


public class MyListTest {

    @Test
    public void add() {
        // Step 1: Create an instance of MyLinkedListImp
        MyLinkedListImp<Integer> list = new MyLinkedListImp<>();

        // Step 2: Add an element to the list
        list.add(1);

        // Step 3: Check different conditions
        assertEquals(1, list.size());
        assertEquals(Integer.valueOf(1), list.getFirst().getValue());
        assertEquals(Integer.valueOf(1), list.getLast().getValue());
        assertTrue(list.contains(1));
    }

    @Test
    public void get() {
        MyLinkedListImp<Integer> list = new MyLinkedListImp<>();
        list.add(10);
        list.add(20);
        list.add(30);

        assertEquals(Integer.valueOf(10), list.get(0));
        assertEquals(Integer.valueOf(20), list.get(1));
        assertEquals(Integer.valueOf(30), list.get(2));
    }

    @Test
    public void contains() {
        MyLinkedListImp<String> list = new MyLinkedListImp<>();
        list.add("apple");
        list.add("banana");
        list.add("cherry");

        assertTrue(list.contains("apple"));
        assertTrue(list.contains("banana"));
        assertTrue(list.contains("cherry"));
    }

    @Test
    public void remove() throws OutOfBoundsException {
        MyLinkedListImp<Integer> list = new MyLinkedListImp<>();
        list.add(10);
        list.add(20);
        list.add(30);

        try {
            list.remove(20);
        } catch (OutOfBoundsException e) {
            fail();
        }

        assertEquals(2, list.size());
        assertEquals(Integer.valueOf(10), list.get(0));
        assertEquals(Integer.valueOf(30), list.get(1));
    }

    @Test
    public void size() {
        MyLinkedListImp<String> list = new MyLinkedListImp<>();
        assertEquals(0, list.size());

        list.add("apple");
        assertEquals(1, list.size());

        list.add("banana");
        list.add("cherry");
        assertEquals(3, list.size());
    }
}
