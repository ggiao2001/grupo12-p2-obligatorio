import uy.edu.um.prog2.adt.Exceptions.EmptyQueueException;
import uy.edu.um.prog2.adt.Entities.MyQueueImp;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyQueueTest {

    MyQueueImp<String> queue = new MyQueueImp<>();

    @Test
    public void enqueue() {


        // Enqueue elements
        queue.enqueue("Apple");
        queue.enqueue("Banana");
        queue.enqueue("Cherry");

        // Check size after enqueue
        assertEquals(3, queue.size());
    }

    @Test
    public void dequeue() throws EmptyQueueException {

        // Enqueue elements
        queue.enqueue("Apple");
        queue.enqueue("Banana");
        queue.enqueue("Cherry");

        // Dequeue elements
        assertEquals("Apple", queue.dequeue());
        assertEquals("Banana", queue.dequeue());

        // Check size after dequeue
        assertEquals(1, queue.size());

        // Dequeue the last element
        assertEquals("Cherry", queue.dequeue());

        // Check size after dequeueing the last element
        assertEquals(0, queue.size());

        // Try dequeue on an empty queue
        try {
            queue.dequeue();
            fail("Expected EmptyQueueException to be thrown");
        } catch (EmptyQueueException e) {
            // Exception is expected
        }
    }
}