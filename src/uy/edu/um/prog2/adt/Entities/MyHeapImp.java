package uy.edu.um.prog2.adt.Entities;

import uy.edu.um.prog2.adt.Exceptions.EmptyTreeException;
import uy.edu.um.prog2.adt.Exceptions.FullHeapException;
import uy.edu.um.prog2.adt.Interfaces.MyHeap;

public class MyHeapImp<T extends Comparable<T>> implements MyHeap<T> {

    //VARIABLES
    private static final int CAPACIDAD_INICIAL = 10; //size máximo inicial
    private T[] array;
    private int size; //size actual
    private boolean max; //True is es maximo, False si Minimo

    //CONSTRUCTOR
    public MyHeapImp(boolean max) {
        this.array = (T[]) new Comparable[CAPACIDAD_INICIAL];
        this.size = 0;
        this.max = max;
    }

    //Los hijos de i estan en 2i+1 y 2i+2
    //El padre de i esta en (i-2)/2

    //METHODS

    /**
     * Este metodo agrega un nuevo elemento al Heap
     *
     * @param elemento el elemento a agregar. Acepta Generics
     */
    @Override
    public void agregar(T elemento) throws FullHeapException {
        if (elemento != null) {
            if (this.size < array.length && this.size != 0) {
                // Tengo lugar para agregar
                array[size] = elemento; // Lo agrego al final del arreglo
                int i = size;
                this.size++; // Aumento el size

                if (this.max) {
                    // Es un árbol máximo

                    // Lo ordeno hasta llegar a que el padre sea más grande que el elemento o que sea la raíz.
                    // El padre de i está en (i-1)/2
                    while (i > 0 && array[(i - 1) / 2].compareTo(elemento) < 0) {
                        // Intercambio padre con el elemento
                        array[i] = array[(i - 1) / 2];
                        array[(i - 1) / 2] = elemento;
                        i = (i - 1) / 2;
                    }
                } else {
                    // Es un árbol mínimo

                    // Lo ordeno hasta llegar a que el padre sea más pequeño que el elemento o que sea la raíz.
                    // El padre de i está en (i-1)/2
                    while (i > 0 && array[(i - 1) / 2].compareTo(elemento) > 0) {
                        // Intercambio padre con el elemento
                        array[i] = array[(i - 1) / 2];
                        array[(i - 1) / 2] = elemento;
                        i = (i - 1) / 2;
                    }
                }
            } else if (this.size == 0) {
                // El arreglo está vacío, agrego el elemento en la posición 0
                array[0] = elemento;
                size++;
            } else {
                throw new FullHeapException();
            }
        } else {
            throw new IllegalArgumentException("El elemento no puede ser nulo");
        }
    }

    /**
     * Este metodo obtiene el maximo o minimo del heap
     * y reorganiza el heap
     *
     * @return el máximo o minimo dependiendo si el heap es max o min.
     */
    @Override
    public T obtenerYEliminar() throws EmptyTreeException {
        if (this.size == 0) {
            // Caso de arbol vacio
            throw new EmptyTreeException();
        } else if (this.size == 1) {
            // Caso de uno solo
            T devolver = array[0];
            this.array[0] = null;
            size = 0;
            return devolver;
        } else {
            // Caso común de varios
            T devolver = array[0]; // Guardo en un variable el primero para devolverlo.
            this.array[0] = array[size - 1];// Pongo el último primero
            size--;
            // Reordeno hasta que:
            // Los hijos tienen todas las claves menores a las del nodo
            // o el nodo es una hoja
            if (this.max) {
                // Caso Heap Max
                int indice_actual = 0;
                while (true) {
                    int indice_hijo_izq = 2 * indice_actual + 1;
                    int indice_hijo_der = 2 * indice_actual + 2;
                    int indice_hijo_grande = indice_actual;

                    // Comparo con el hijo izquierdo
                    if (array[indice_hijo_izq] != null) {
                        if (indice_hijo_izq < size && array[indice_hijo_izq].compareTo(array[indice_hijo_grande]) > 0) {
                            indice_hijo_grande = indice_hijo_izq;
                        }
                    }

                    // Comparo con el hijo derecho
                    if (array[indice_hijo_der] != null) {
                        if (indice_hijo_der < size && array[indice_hijo_der].compareTo(array[indice_hijo_grande]) > 0) {
                            indice_hijo_grande = indice_hijo_der;
                        }
                    }

                    if (indice_hijo_grande == indice_actual) {
                        // El nodo actual es el más grande, entonces el heap está ordenado
                        break;
                    }

                    // Cambio el nodo actual con el hijo más grande
                    T temp = array[indice_actual];
                    array[indice_actual] = array[indice_hijo_grande];
                    array[indice_hijo_grande] = temp;

                    // Me muevo un piso más abajo del árbol
                    indice_actual = indice_hijo_grande;
                }
            } else {
                // Caso Heap Min
                int indice_actual = 0;
                while (true) {
                    int indice_hijo_izq = 2 * indice_actual + 1;
                    int indice_hijo_der = 2 * indice_actual + 2;
                    int indice_hijo_chico = indice_actual;

                    // Comparo con el hijo izquierdo
                    if (indice_hijo_izq < size && array[indice_hijo_izq] != null && array[indice_hijo_izq].compareTo(array[indice_hijo_chico]) < 0) {
                        indice_hijo_chico = indice_hijo_izq;
                    }

                    // Comparo con el hijo derecho
                    if (indice_hijo_der < size && array[indice_hijo_der] != null && array[indice_hijo_der].compareTo(array[indice_hijo_chico]) < 0) {
                        indice_hijo_chico = indice_hijo_der;
                    }

                    if (indice_hijo_chico == indice_actual) {
                        // El nodo actual es el menor, entonces ya está ordenado
                        break;
                    }

                    // Cambio el nodo actual con el hijo menor
                    T temp = array[indice_actual];
                    array[indice_actual] = array[indice_hijo_chico];
                    array[indice_hijo_chico] = temp;

                    // Me muevo un piso más abajo del árbol
                    indice_actual = indice_hijo_chico;
                }
            }

            return devolver;
        }
    }


    @Override
    public int obtenerTamaño() {
        //Delego al getter. Tamaño ya esta definido como variable local.
        return this.getTamaño();
    }

    /**
     * Este metodo muestra el arbol en salida estandar
     * llama de manera recursiva a mostrarArbolRecusrivo()
     *
     */
    @Override
    public void mostrarArbol() {
        mostrarArbolRecursivo(0, "");
    }

    /**
     * Este metodo muestra el arbol en salida estandar de manera recursiva
     *
     * @param indice indice desede donde se comienza a imprimir
     * @param prefijo el prefijo que se usa en cada espacio y es pasado nuevamente a la funcion.
     *                en la primer instancia tiene que ser un espacio vacio.
     *
     */
    private void mostrarArbolRecursivo(int indice, String prefijo) {
        if (indice < array.length && array[indice] != null) {
            boolean esUltimo = indice == array.length - 1 || array[indice + 1] == null;
            String espacios = prefijo + (esUltimo ? "   " : "│  ");

            mostrarArbolRecursivo(indice * 2 + 2, espacios); // Recursivo para el hijo derecho
            System.out.println(prefijo + (esUltimo ? "└──" : "├──") + array[indice]);
            mostrarArbolRecursivo(indice * 2 + 1, espacios); // Recursivo para el hijo izquierdo
        }
    }

    //GETTERS & SETTERS
    //setters los pongo privados porque no quiero que de afuera me modifiquen esos valores.
    public T[] getArray() {
        return array;
    }

    private void setArray(T[] array) {
        this.array = array;
    }

    public int getTamaño() {
        return this.size;
    }

    private void setTamaño(int size) {
        this.size = size;
    }
}
