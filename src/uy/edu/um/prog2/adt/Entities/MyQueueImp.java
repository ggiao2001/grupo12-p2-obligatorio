package uy.edu.um.prog2.adt.Entities;

import uy.edu.um.prog2.adt.Exceptions.EmptyQueueException;
import uy.edu.um.prog2.adt.Interfaces.MyQueue;

public class MyQueueImp<T> implements MyQueue<T> {

    private Node<T> first;
    private Node<T> last;

    //constructor
    public MyQueueImp() {
        this.first = null;
        this.last = null;
    }

    //getters y setters
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

    //metodos
    @Override
    public void enqueue(T value) {
        Node<T> agregar = new Node<T>(value);
        if(this.first == null){
            this.first = agregar;
            this.last = agregar;
        }else{
            Node<T> aux = this.first;
            while(aux.getNext()!= null){
                aux = aux.getNext();
            }
            aux.setNext(agregar);
            this.last = agregar;
        }
    }

    @Override
    public T dequeue() throws EmptyQueueException {
        if(this.first == null){
            throw new EmptyQueueException();
        }else{
            Node<T> to_return = null;
            if(this.first.getNext() != null){
                to_return = this.first;
                this.first = this.first.getNext();
            }else{
                to_return = this.first;
                this.first=null;
            }
            return to_return.getValue();
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
}
