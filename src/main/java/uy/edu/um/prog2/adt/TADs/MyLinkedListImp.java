package uy.edu.um.prog2.adt.TADs;

import uy.edu.um.prog2.adt.Exceptions.OutOfBoundsException;
import uy.edu.um.prog2.adt.Interfaces.MyList;

import java.util.Comparator;

public class MyLinkedListImp <T> implements MyList<T> {

    private Node<T> first;
    private Node<T> last;

    public MyLinkedListImp() {
        this.first = null;
        this.last = null;
    }

////////////////////////////////
    //METHODS DE MYLIST
////////////////////////////////
    @Override
    public void add(T value) {
        Node<T> toAdd = new Node<>(value);
        if(this.first!= null){
            Node<T> aux = this.first;
            while(aux.getNext()!= null){
                aux = aux.getNext();
            }
            aux.setNext(toAdd);
            this.last = toAdd;

        }else{
            this.first = toAdd;
            this.last = toAdd;
        }
    }

    public void addIndex(int position, T value) throws OutOfBoundsException {
        Node<T> toAdd = new Node<>(value);
        if (position >= 0 && position < this.size()) {
            if (position == 0 && this.first != null) {
                // quiero insertar al principio.
                toAdd.setNext(first);
                first = toAdd;
            } else if (this.first != null) {
                // busca insertar en otro con position > 0.
                Node<T> aux = first.getNext();
                Node<T> auxPrev = first;
                int i = 1;
                while (i < position) {
                    //recorro la lista hasta llegar a la posicion que busco
                    aux = aux.getNext();
                    auxPrev = auxPrev.getNext();
                    i++;
                }
                auxPrev.setNext(toAdd);
                toAdd.setNext(aux);
            }
        } else {
            throw new OutOfBoundsException();
        }
    }

    @Override
    public T get(int position) {
        if (position == 0 && this.first != null) {
            // Busca el primer valor y no es nulo.
            return this.first.getValue();
        } else if (this.first != null) {
            // No buscan el primero y el primero no es nulo.
            Node<T> aux = this.first;
            for (int i = 0; i < position; i++) {
                if (aux.getNext() != null) {
                    aux = aux.getNext();
                } else {
                    return null;
                }
            }
            return aux.getValue();
        } else {
            // El primero es nulo, la lista es vacía.
            return null;
        }
    }


    @Override
    public boolean contains(T value) {
        if (this.first != null) {
            if (this.first.getValue().equals(value)) {
                return true;
            }

            Node<T> aux = this.first;
            while (aux.getNext() != null) {
                aux = aux.getNext();
                if (aux.getValue().equals(value)) {
                    return true;
                }
            }
        }

        return false;
    }


    @Override
    public void remove(T value) throws OutOfBoundsException {
        if (this.first != null && this.first.getValue().equals(value)) {
            // El primero no es vacío y el valor que queremos sacar es el primero
            this.first = this.first.getNext();
            if (this.first == null) {
                // La lista quedó vacía, actualizamos last
                this.last = null;
            }
        } else if (this.first != null) {
            // El primero no es vacío, y no es el que queremos
            Node<T> prev = this.first;
            Node<T> current = this.first.getNext();
            while (current != null) {
                if (current.getValue().equals(value)) {
                    prev.setNext(current.getNext());
                    if (current.getNext() == null) {
                        // Actualizamos last cuando se elimina el último elemento
                        this.last = prev;
                    }
                    return; // Exit the method after removing the element
                }
                prev = current;
                current = current.getNext();
            }
        } else {
            throw new OutOfBoundsException();
        }
    }


    @Override
    public int size() {
        int count = 0;
        if(this.first != null){
            count+=1;
            Node<T> aux = this.first;
            while(aux.getNext()!= null){
                count+=1;
                aux = aux.getNext();
            }
        }
        return count;
    }

/////////////////////////////////////
    //GETTERS & SETTERS
////////////////////////////////////

    public Node<T> getFirst() {
        return first;
    }

    public void setFirst(Node<T> first) {
        this.first = first;
    }

    public Node<T> getLast() {
        return last;
    }

    public void setLast(Node<T> last) {
        this.last = last;
    }

    public void quickSort(Comparator<T> comparator) {
        quickSortRecursive(first, last, comparator);
    }

    private void quickSortRecursive(Node<T> start, Node<T> end, Comparator<T> comparator) {
        if (start == null || end == null || start == end || start == end.getNext()) {
            return; // Nothing to sort
        }

        Node<T> pivot = partition(start, end, comparator);
        quickSortRecursive(start, pivot, comparator);
        quickSortRecursive(pivot.getNext(), end, comparator);
    }

    private Node<T> partition(Node<T> start, Node<T> end, Comparator<T> comparator) {
        Node<T> pivot = start;
        Node<T> current = start;
        T pivotValue = pivot.getValue();

        while (current != end && current.getNext() != null) {
            if (comparator.compare(current.getNext().getValue(), pivotValue) < 0) {
                swapValues(current.getNext(), pivot.getNext());
                pivot = pivot.getNext();
            }
            current = current.getNext();
        }

        swapValues(start, pivot);
        return pivot;
    }

    private void swapValues(Node<T> node1, Node<T> node2) {
        T temp = node1.getValue();
        node1.setValue(node2.getValue());
        node2.setValue(temp);
    }

}
