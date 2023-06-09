package uy.edu.um.prog2.adt.Entities;

public class NodeBST <K extends Comparable<K>, T> {
    K key;
    T data;
    NodeBST <K, T> leftChild;
    NodeBST <K, T> rightChild;

    //Constructor completo
    public NodeBST(K key, T data, NodeBST<K, T> leftChild, NodeBST<K, T> rightChild) {
        this.key = key;
        this.data = data;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    //Contructor vacio
    public NodeBST() {
    }

    //Contructor solo con primeros datos y sin child
    public NodeBST(K key, T data) {
        this.key = key;
        this.data = data;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public NodeBST<K, T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(NodeBST<K, T> leftChild) {
        this.leftChild = leftChild;
    }

    public NodeBST<K, T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(NodeBST<K, T> rightChild) {
        this.rightChild = rightChild;
    }
}



