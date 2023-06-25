package uy.edu.um.prog2.adt.TADs.hash;

import java.util.Enumeration;

public interface HashTable<K, V> {
    void put(K key, V value);

    V get(K key);

    boolean contains(K key);

    void remove(K key);

    int size();

    boolean isEmpty();

    V getOrDefault(K key, V defaultValue);

    Enumeration<K> keys();
}
