package uy.edu.um.prog2.adt.Interfaces;

import uy.edu.um.prog2.adt.Exceptions.EmptyTreeException;
import uy.edu.um.prog2.adt.TADs.NodeBST;

public interface MyBinarySearchTree<K extends Comparable<K>, T> {

    T find(K key);

    void insert(K key, T data);

    void delete(K key, T data) throws EmptyTreeException;


    NodeBST<K, T> getRoot();

    void setRoot(NodeBST<K, T> root);


}

