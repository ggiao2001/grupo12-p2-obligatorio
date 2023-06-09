package uy.edu.um.prog2.adt.Entities;

import uy.edu.um.prog2.adt.Exceptions.OutOfBoundsException;
import uy.edu.um.prog2.adt.Interfaces.MyList;

public class MyHashLinkedListImp<K,V> implements MyList<NodeHash<K,V>> {

    private NodeHash<K,V> first;
    private NodeHash<K,V> last;

    public MyHashLinkedListImp() {
        this.first = null;
        this.last = null;
    }

    public NodeHash<K, V> getFirst() {
        return first;
    }

    public void setFirst(NodeHash<K, V> first) {
        this.first = first;
    }

    public NodeHash<K, V> getLast() {
        return last;
    }

    public void setLast(NodeHash<K, V> last) {
        this.last = last;
    }

    @Override
    public void add(NodeHash<K,V> node) {
        if (node!=null) {
            if (first == null && last == null) {
                first = node;
                last = node;
            } else if (first == last) {
                //caso agregar a lista de un solo valor
                last = node;
                first.setNext(last);
            } else {
                //caso agregar al final
                last.setNext(node);
                last = node;
            }
        }
    }

    @Override
    public NodeHash<K, V> get(int position) {
        NodeHash<K,V> aux = first;
        int search = 0;
        while (search < position && aux.getNext() != null) {
            search++;
            aux = aux.getNext();
        }
        if (search == position) {
            return aux;
        }
        return null;
        //throw new OutOfBoundsException();
    }

    public NodeHash<K,V> getKey(K key){
        NodeHash<K,V> aux = first;
        if (aux != null) {
            while (aux.getKey() != key && aux.getNext() != null) {
                aux = aux.getNext();
            }
            if (aux.getKey() == key) {
                return aux;
            }
        }
        return null;
    }

    @Override
    public boolean contains(NodeHash<K, V> node) {
        if (node != null && first != null) {
            NodeHash<K,V> aux = first;
            while (node.getKey() != aux.getKey() && aux.getNext() != null){
                aux = aux.getNext();
            }
            if (aux.getKey() == node.getKey()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void remove(NodeHash<K, V> node) throws OutOfBoundsException {
        if (node != null && first != null) {
            NodeHash<K,V> aux = first;
            NodeHash<K,V> auxPrev = null;
            while (node.getKey() != aux.getKey() && aux.getNext() != null) {
                auxPrev = aux;
                aux = aux.getNext();
            }
            if (node.getKey() == aux.getKey()) {
                if (aux == first && aux == last) {
                    //caso remove el unico elemento
                    first = null;
                    last = null;
                } else if (aux == first) {
                    //caso saco el first
                    first = first.getNext();
                    aux.setNext(null);
                } else if (aux == last) {
                    //caso remove el last
                    last = auxPrev;
                    last.setNext(null);
                } else {
                    //otros casos
                    auxPrev.setNext(aux.getNext());
                    aux.setNext(null);
                }
            } else {
                //significa que recorri toda la lista pero ninguno coincide con mi key
                throw new OutOfBoundsException();
            }
        }
    }

    @Override
    public int size() {
        if (first == null) {
            return 0;
        } else {
            int size = 1;
            NodeHash<K,V> aux = first;
            while (aux.getNext() != null) {
                size++;
                aux = aux.getNext();
            }
        return size;
        }
    }
}
