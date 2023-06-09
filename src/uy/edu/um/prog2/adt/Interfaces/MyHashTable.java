package uy.edu.um.prog2.adt.Interfaces;

import uy.edu.um.prog2.adt.Exceptions.ElementNotFoundException;
import uy.edu.um.prog2.adt.Exceptions.OutOfBoundsException;

public interface MyHashTable<K,V> {
    void put(K key, V value);
    boolean contains(K key);
    void remove(K key) throws ElementNotFoundException, OutOfBoundsException;
    V get(K key) throws ElementNotFoundException;
}
