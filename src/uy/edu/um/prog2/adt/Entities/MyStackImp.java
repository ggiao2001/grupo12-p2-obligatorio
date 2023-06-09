package uy.edu.um.prog2.adt.Entities;

import uy.edu.um.prog2.adt.Exceptions.EmptyStackException;
import uy.edu.um.prog2.adt.Interfaces.MyStack;

public class MyStackImp<T> implements MyStack<T> {

    Node<T> first;
    Node<T> last;

    //constructor
    public MyStackImp() {
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
    public void push(T value) {
        if (this.first == null) {
            this.first = new Node<>(value);
            this.last = new Node<>(value);
        } else {
            Node<T> aux = this.first;
            this.first = new Node<>(value);
            this.first.setNext(aux);
        }
    }

    @Override
    public T pop() throws EmptyStackException {
        if(this.first == null){
            throw new EmptyStackException();
        }else if(this.first.getNext() !=null){
            //Caso en el que hay más de uno
            Node <T> to_return = this.first;
            this.first = this.first.getNext();
            return to_return.getValue();
        }else{
            //Caso en el que hay solo uno
            Node <T> to_return = this.first;
            this.first = null;
            return to_return.getValue();
        }
    }

    @Override
    public T peek() {
        if(this.first == null){
            return null;
        }else {
            return this.first.getValue();
        }
    }

    @Override
    public int size() {
        int count = 0;
        if(this.first != null){
            count++;
            Node<T> aux = this.first;
            while(aux.getNext()!= null){
                count++;
                aux = aux.getNext();
            }
        }
        return count;
    }

    @Override
    public T get(int position) {
        if (this.first != null) {
            if (position == 0){
                return this.first.getValue();
            } else {
                Node<T> aux = this.first;
                int search = 0;
                while (search < position && aux.getNext() != null){
                    aux = aux.getNext();
                    search++;
                }
                if (search == position){
                    return aux.getValue();
                }else{
                    return null;
                }
            }
        } else {
            return null;
        }
    }
}
