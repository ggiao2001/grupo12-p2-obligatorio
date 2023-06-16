package uy.edu.um.prog2.adt.Interfaces;

import uy.edu.um.prog2.adt.Exceptions.EmptyTreeException;
import uy.edu.um.prog2.adt.Exceptions.FullHeapException;

public interface MyHeap<T extends Comparable<T>> {

    void agregar(T elemento) throws FullHeapException;

    T obtenerYEliminar() throws EmptyTreeException;

    int obtenerTamaño();

    void mostrarArbol();

}
