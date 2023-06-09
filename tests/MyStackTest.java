import uy.edu.um.prog2.adt.Exceptions.EmptyStackException;
import uy.edu.um.prog2.adt.Interfaces.MyStack;
import uy.edu.um.prog2.adt.Entities.MyStackImp;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyStackTest {

    MyStack<Integer> stack = new MyStackImp<>();

    @Test
    public void push() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.size());
    }

    @Test
    public void pop() throws EmptyStackException {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        int poppedElement = stack.pop();
        assertEquals(3, poppedElement);
        assertEquals(2, stack.size());
    }

    @Test
    public void peek() throws EmptyStackException {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        int topElement = stack.peek();
        assertEquals(3, topElement);
        assertEquals(3, stack.size());
    }
}
