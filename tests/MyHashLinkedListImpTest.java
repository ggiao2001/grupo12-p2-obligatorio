import uy.edu.um.prog2.adt.Entities.MyHashLinkedListImp;
import uy.edu.um.prog2.adt.Entities.NodeHash;
import uy.edu.um.prog2.adt.Exceptions.OutOfBoundsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyHashLinkedListImpTest {

    private MyHashLinkedListImp<Integer,String> hashLinkedList = new MyHashLinkedListImp<>();
    private NodeHash<Integer, String> nodo1 = new NodeHash<>(1,"a");
    private NodeHash<Integer, String> nodo2 = new NodeHash<>(2,"b");
    private NodeHash<Integer, String> nodo3 = new NodeHash<>(3,"c");

    @Test
    void add() {
        hashLinkedList.add(nodo1);
        assertEquals("a", hashLinkedList.getFirst().getValue());
        hashLinkedList.add(nodo2);
        assertEquals("b",hashLinkedList.getLast().getValue());
        hashLinkedList.add(nodo3);
        assertEquals("c",hashLinkedList.getLast().getValue());
    }

    @Test
    void get() {
        hashLinkedList.add(nodo1);
        hashLinkedList.add(nodo2);
        hashLinkedList.add(nodo3);
        assertEquals(nodo1, hashLinkedList.get(0));
        assertEquals(nodo2, hashLinkedList.get(1));
        assertEquals(nodo3, hashLinkedList.get(2));
    }

    @Test
    void getKey() {
        hashLinkedList.add(nodo1);
        hashLinkedList.add(nodo2);
        hashLinkedList.add(nodo3);
        assertNull(hashLinkedList.getKey(0));
        assertEquals(nodo1, hashLinkedList.getKey(1));
        assertEquals(nodo2, hashLinkedList.getKey(2));
        assertEquals(nodo3, hashLinkedList.getKey(3));
     }
    @Test
    void contains() {
        hashLinkedList.add(nodo1);
        hashLinkedList.add(nodo2);
        assertFalse(hashLinkedList.contains(nodo3));
        assertTrue(hashLinkedList.contains(nodo1));
        assertTrue(hashLinkedList.contains(nodo2));
    }

    @Test
    void remove() throws OutOfBoundsException {
        //caso remove el unico elemento
        hashLinkedList.add(nodo1);
        hashLinkedList.remove(nodo1);
        assertFalse(hashLinkedList.contains(nodo1));

        //caso remove el last
        hashLinkedList.add(nodo1);
        hashLinkedList.add(nodo2);
        hashLinkedList.add(nodo3);
        hashLinkedList.remove(nodo3);
        assertFalse(hashLinkedList.contains(nodo3));

    }

    @Test
    void size() {
        assertEquals(0, hashLinkedList.size());
        hashLinkedList.add(nodo1);
        assertEquals(1, hashLinkedList.size());
        hashLinkedList.add(nodo2);
        assertEquals(2,hashLinkedList.size());
    }
}