package uy.edu.um.prog2.adt.Interfaces;


import uy.edu.um.prog2.adt.Exceptions.EmptyStackException;

public interface MyStack<T> {

    void push(T value);

    T pop() throws EmptyStackException;

    T peek();

    int size();

    T get(int position);

}
