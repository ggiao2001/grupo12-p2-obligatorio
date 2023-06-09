import uy.edu.um.prog2.adt.Entities.MyBinarySearchTreeImp;
import uy.edu.um.prog2.adt.Exceptions.EmptyTreeException;
import uy.edu.um.prog2.adt.Interfaces.MyBinarySearchTree;
import uy.edu.um.prog2.adt.Entities.NodeBST;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyBSTreeTest {

    @Test
    public void find() {
        // Create a binary search tree with some elements
        MyBinarySearchTree<Integer, String> bst = new MyBinarySearchTreeImp<>();
        bst.insert(5, "Apple");
        bst.insert(3, "Banana");
        bst.insert(7, "Orange");

        // Test finding an existing key
        assertEquals("Apple", bst.find(5));

        // Test finding a non-existing key
        assertNull(bst.find(2));
    }

    @Test
    public void insert(){
        // Create an empty binary search tree
        MyBinarySearchTree<Integer, String> bst = new MyBinarySearchTreeImp<>();

        // Insert elements into the tree
        bst.insert(5, "Apple");
        bst.insert(3, "Banana");
        bst.insert(7, "Orange");

        // Test the root and its children
        NodeBST<Integer, String> root = bst.getRoot();
        assertEquals(Integer.valueOf(5), root.getKey());
        assertEquals("Apple", root.getData());
        assertEquals(Integer.valueOf(3), root.getLeftChild().getKey());
        assertEquals("Banana", root.getLeftChild().getData());
        assertEquals(Integer.valueOf(7), root.getRightChild().getKey());
        assertEquals("Orange", root.getRightChild().getData());
    }

    @Test
    public void delete() throws EmptyTreeException {
        // Create a binary search tree with some elements
        MyBinarySearchTree<Integer, String> bst = new MyBinarySearchTreeImp<>();
        bst.insert(5, "Apple");
        bst.insert(3, "Banana");
        bst.insert(7, "Orange");

        // Test deleting an existing key
        bst.delete(3, "Banana");
        assertNull(bst.find(3));

        // Test deleting a non-existing key
        try {
            bst.delete(2, "Non-existing");
            fail("Expected EmptyTreeException to be thrown.");
        } catch (EmptyTreeException e) {
            // Expected exception
        }
    }


}

