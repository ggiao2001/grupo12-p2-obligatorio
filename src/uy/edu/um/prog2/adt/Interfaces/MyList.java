package uy.edu.um.prog2.adt.Interfaces;

import uy.edu.um.prog2.adt.Exceptions.OutOfBoundsException;

public interface MyList<T> {

    void add(T value);

    T get(int position);

    boolean contains(T value);

    void remove(T value) throws OutOfBoundsException;

    int size();

}
