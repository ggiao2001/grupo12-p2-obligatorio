package uy.edu.um.prog2.adt.TADs.hash.linked_list;

import uy.edu.um.prog2.adt.TADs.hash.HashNode;

import java.util.Comparator;

public class HashLinkedList<K, V> implements uy.edu.um.prog2.ad.tads.linked_list.ListaConGenerics<HashNode<K, V>> {
    private HashNode<K, V> first;
    private HashNode<K, V> last;

    public HashLinkedList() {
        this.first = null;
        this.last = null;
    }

    @Override
    public void add(HashNode<K, V> node) {
        if (node != null) {
            if (first == null) {
                first = node;
                last = node;
            } else {
                last.setNext(node);
                last = node;
            }
        }
    }

    @Override
    public void remove(int position) {
        if (position == 0) {
            first = first.getNext();
        } else {
            HashNode<K, V> current = first;
            int size = this.size();
            int posiciones = size - 1;
            for (int i = 1; i <= posiciones; i++) {
                if (i == position) {
                    HashNode<K, V> next = current.getNext();
                    current.setNext(next.getNext());
                    break;
                }
                current = current.getNext();
            }
        }
    }

    @Override
    public HashNode<K, V> get(int position) {
        if (position == 0) {
            return first;
        }
        HashNode<K, V> current = first;
        int size = this.size();
        int posiciones = size - 1;
        for (int i = 1; i <= posiciones; i++) {
            if (i == position) {
                return current.getNext();
            }
            current = current.getNext();
        }
        return null;
    }

    @Override
    public HashNode<K, V> get(HashNode<K, V> value) {
        //no implementado
        return null;
    }

    @Override
    public boolean contains(HashNode<K, V> value) {
        HashNode<K, V> current = first;
        while (current != null) {
            if (current.equals(value)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public V getValue(K key) {
        HashNode<K, V> current = first;
        while (current != null) {
            if (current.getKey().equals(key)) {
                return current.getValue();
            }
            current = current.getNext();
        }
        return null;
    }

    public HashNode<K, V> getNode(K key) {
        HashNode<K, V> current = first;
        while (current != null) {
            if (current.getKey().equals(key)) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    @Override
    public void remove(HashNode<K, V> node) {
        if (node == first) {
            first = first.getNext();
            if (first == null) {
                last = null;
            }
        } else {
            HashNode<K, V> current = first;
            while (current != null && current.getNext() != node) {
                current = current.getNext();
            }
            if (current != null) {
                current.setNext(node.getNext());
                if (current.getNext() == null) {
                    last = current;
                }
            }
        }
    }

    @Override
    public Integer size() {
        if (first == null) {
            return 0;
        } else {
            int size = 1;
            HashNode<K, V> current = first;
            while (current.getNext() != null) {
                size++;
                current = current.getNext();
            }
            return size;
        }
    }

    public HashNode<K, V> getFirst() {
        return first;
    }
}
