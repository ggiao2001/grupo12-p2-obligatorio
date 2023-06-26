package uy.edu.um.prog2.ad.tads.linked_list;

public interface ListaConGenerics<T> {
    void add(T value);

    void remove(int position);

    void remove(T value);

    T get(int position);

    T get(T value);

    boolean contains(T value);

    Integer size();
}
