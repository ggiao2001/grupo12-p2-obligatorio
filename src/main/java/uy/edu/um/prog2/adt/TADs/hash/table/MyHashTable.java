package uy.edu.um.prog2.adt.TADs.hash.table;

import uy.edu.um.prog2.adt.TADs.hash.HashNode;
import uy.edu.um.prog2.adt.TADs.hash.HashTable;
import uy.edu.um.prog2.adt.TADs.hash.linked_list.HashLinkedList;

import java.util.Enumeration;
import java.util.NoSuchElementException;

public class MyHashTable<K, V> implements HashTable<K, V> {

    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private static final int DEFAULT_SIZE = 16;

    HashLinkedList<K, V>[] buckets;
    int size;
    float loadFactor;

    public MyHashTable(int size) {
        this.size = size;
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        buckets = (HashLinkedList<K, V>[]) new HashLinkedList[size];
        for (int i = 0; i < size; i++) {
            buckets[i] = new HashLinkedList<>();
        }
    }

    public MyHashTable(int size, float loadFactor) {
        this.size = size;
        this.loadFactor = loadFactor;
        buckets = (HashLinkedList<K, V>[]) new HashLinkedList[size];
        for (int i = 0; i < size; i++) {
            buckets[i] = new HashLinkedList<>();
        }
    }

    public MyHashTable() {
        this(DEFAULT_SIZE, DEFAULT_LOAD_FACTOR);
    }

    public int hashCode(K key) {
        int hashCode = key.hashCode();
        return Math.abs(hashCode) % size;
    }

    @Override
    public void put(K key, V value) {
        int code = hashCode(key);
        HashLinkedList<K, V> bucket = buckets[code];
        if (contains(key)) {
            HashNode<K, V> existingNode = bucket.getNode(key);
            existingNode.setValue(value);
        } else {
            bucket.add(new HashNode<>(key, value));
            if ((float) size() / size > loadFactor) {
                resize();
            }
        }
    }

    @Override
    public boolean contains(K key) {
        int code = hashCode(key);
        HashLinkedList<K, V> bucket = buckets[code];
        HashNode<K, V> current = bucket.getFirst();
        while (current != null) {
            if (current.getKey().equals(key)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }


    @Override
    public V get(K key) {
        int code = hashCode(key);
        return buckets[code].getValue(key);
    }


    @Override
    public void remove(K key) {
        int code = hashCode(key);
        HashNode<K, V> cleanNode = buckets[code].getNode(key);
        buckets[code].remove(cleanNode);
    }

    @Override
    public int size() {
        int count = 0;
        for (HashLinkedList<K, V> bucket : buckets) {
            count += bucket.size();
        }
        return count;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public V getOrDefault(K key, V defaultValue) {
        if (contains(key)) {
            return get(key);
        } else {
            return defaultValue;
        }
    }

    @Override
    public Enumeration<K> keys() {
        return new Enumeration<>() {
            int currentBucket = 0;
            HashNode<K, V> currentNode = buckets[currentBucket].getFirst();

            @Override
            public boolean hasMoreElements() {
                // Check if there are more elements in the current bucket
                if (currentNode != null) {
                    return true;
                }
                // Check if there are more buckets
                while (currentBucket < size - 1) {
                    currentBucket++;
                    currentNode = buckets[currentBucket].getFirst();
                    if (currentNode != null) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public K nextElement() {
                if (!hasMoreElements()) {
                    throw new NoSuchElementException();
                }
                K key = currentNode.getKey();
                // Move to the next node in the current bucket
                currentNode = currentNode.getNext();
                // Check if we need to move to the next bucket
                if (currentNode == null) {
                    while (currentBucket < size - 1) {
                        currentBucket++;
                        currentNode = buckets[currentBucket].getFirst();
                        if (currentNode != null) {
                            break;
                        }
                    }
                }
                return key;
            }
        };
    }

    private void resize() {
        int newSize = size * 2; // Puedes ajustar el factor de redimensionamiento según tus necesidades
        HashLinkedList<K, V>[] newBuckets = (HashLinkedList<K, V>[]) new HashLinkedList[newSize];
        for (int i = 0; i < newSize; i++) {
            newBuckets[i] = new HashLinkedList<>();
        }

        Enumeration<K> keys = keys();
        while (keys.hasMoreElements()) {
            K key = keys.nextElement();
            V value = get(key);
            int code = key.hashCode();
            int index = Math.abs(code) % newSize;
            newBuckets[index].add(new HashNode<>(key, value));
        }

        size = newSize;
        buckets = newBuckets;
    }

}