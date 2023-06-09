package uy.edu.um.prog2.adt.Entities;

import uy.edu.um.prog2.adt.Exceptions.EmptyQueueException;
import uy.edu.um.prog2.adt.Exceptions.EmptyTreeException;
import uy.edu.um.prog2.adt.Interfaces.MyBinarySearchTree;
import uy.edu.um.prog2.adt.Interfaces.MyQueue;

public class MyBinarySearchTreeImp <K extends Comparable<K>, T> implements MyBinarySearchTree<K, T> {

    private NodeBST<K, T> root;


    //Constructor vacio
    public MyBinarySearchTreeImp() {
    }

    //Contrusctor con root
    public MyBinarySearchTreeImp(NodeBST<K, T> root) {
        this.root = root;
    }

    //METHODS
    @Override
    public T find(K key) {
        if (this.root != null) {
            if (this.root.getKey().equals(key)) {
                return this.root.getData();
            } else {
                NodeBST<K, T> node = busquedaRecursiva(this.root, key);
                if (node != null) {
                    return node.getData();
                }
            }
        }
        return null;
    }


    @Override
    public void insert(K key, T data) {

        NodeBST<K,T> nuevo = new NodeBST<>(key,data);
        if(root != null){
            insertRecursivo(this.root,nuevo);
        }else{
            this.root = nuevo;
        }

    }

    @Override
    public void delete(K key, T data) throws EmptyTreeException {
        try {
            if (this.root != null) {
                if ((this.root.getKey()).equals(key)) {
                    this.root = null;
                } else {

                    NodeBST<K, T> eliminar = busquedaRecursiva(this.root, key);
                    NodeBST<K, T> padre = busquedaRecursivaPadre(this.root, key, null);
                    if(eliminar != null){
                        if (eliminar.getLeftChild() == null && eliminar.getRightChild() == null) {
                            //Si el que voy a eliminar no tiene hijos, lo elimino nomas
                            if (padre.getRightChild()!= null && padre.getRightChild().equals(eliminar)) {
                                padre.setRightChild(null);
                            } else {
                                padre.setLeftChild(null);
                            }
                        } else if (eliminar.getLeftChild() == null && eliminar.getRightChild() != null) {
                            //Caso con solo hijo derecho
                            if (padre.getRightChild()!= null && padre.getRightChild().equals(eliminar)) {
                                padre.setRightChild(eliminar.getRightChild());
                            } else {
                                padre.setLeftChild(eliminar.getRightChild());
                            }

                        } else if (eliminar.getLeftChild() != null && eliminar.getRightChild() == null) {
                            //Caso con solo hijo izquierdo
                            if (padre.getRightChild()!= null && padre.getRightChild().equals(eliminar)) {
                                padre.setRightChild(eliminar.getRightChild());
                            } else {
                                padre.setLeftChild(eliminar.getRightChild());
                            }

                        } else {
                            //Caso con dos hijos
                            MyQueue<NodeBST> queue = new MyQueueImp<>();
                            queue = inOrder(eliminar, queue);

                            //Borro el que quiero eliminar, setteando el hijo de su padre a null.
                            if (padre.getRightChild()!=null && padre.getRightChild().equals(eliminar)) {
                                padre.setRightChild(null);
                            } else {
                                padre.setLeftChild(null);
                            }
                            //Y lo saco de la queue
                            eliminar = queue.dequeue();

                            //Recorro la queue insertando
                            for (int i = 0; i < queue.size(); i++) {
                                NodeBST<K,T> nuevo = new NodeBST<>();
                                nuevo = queue.dequeue();
                                insertRecursivo(padre,nuevo);

                            }
                        }
                    }else{
                        throw new EmptyTreeException();
                    }

                }
            } else {
                throw new EmptyTreeException();
            }
        }catch (EmptyQueueException e){
            System.out.println("Dequeue Problem: Queue vacia");
        }
    }

    //METHODS AUXILIARES
    private void insertRecursivo(NodeBST<K, T> actual, NodeBST<K, T> nuevo) {
        if (nuevo.getKey().compareTo(actual.getKey()) < 0) {
            if (actual.getLeftChild() == null) {
                actual.setLeftChild(nuevo);
            } else {
                insertRecursivo(actual.getLeftChild(), nuevo);
            }
        } else if (nuevo.getKey().compareTo(actual.getKey()) > 0) {
            if (actual.getRightChild() == null) {
                actual.setRightChild(nuevo);
            } else {
                insertRecursivo(actual.getRightChild(), nuevo);
            }
        } else {
            // Si tengo dos keys iguales. Mantengo el que nodo que ya existia
            // Le agrego datos nuevos.
            actual.setData(nuevo.getData());
        }
    }

    private NodeBST<K,T> busquedaRecursiva(NodeBST<K, T> actual, K key) {
        if (actual == null) {
            return null;
        }

        if (key.compareTo(actual.getKey()) < 0) {
            return busquedaRecursiva(actual.getLeftChild(), key);

        } else if (key.compareTo(actual.getKey()) > 0) {
            return busquedaRecursiva(actual.getRightChild(), key);

        } else {
            //Si no es mayor ni menor es que estoy en el que busco
            return actual;

        }
    }

    private NodeBST<K,T> busquedaRecursivaPadre(NodeBST<K, T> actual, K key, NodeBST<K, T> parent) {
        if (actual == null) {
            return null;
        }

        if (key.compareTo(actual.getKey()) < 0) {
            return busquedaRecursivaPadre(actual.getLeftChild(), key, actual);

        } else if (key.compareTo(actual.getKey()) > 0) {
            return busquedaRecursivaPadre(actual.getRightChild(), key, actual);

        } else {
            // Encontre el nodo entonces devuelve el padre
            if (parent != null) {
                return parent;
            } else {
                // Si el padre es null entonces estoy en la root (Que deberia cortar antes igual)
                //Devuelvo null
                return null;
            }
        }
    }


    public MyQueue<NodeBST> inOrder(NodeBST<K,T> nodo, MyQueue<NodeBST> queue){

        if (nodo != null) {
            inOrder(nodo.getLeftChild(),queue);
            queue.enqueue(nodo);
            inOrder(nodo.getRightChild(),queue);
        }
        return queue;
    }

    public MyQueue<NodeBST> preOrder(NodeBST<K, T> nodo, MyQueue<NodeBST> queue){

        if (nodo != null) {
            queue.enqueue(nodo);
            preOrder(nodo.getLeftChild(),queue);
            preOrder(nodo.getRightChild(),queue);
        }
        return queue;
    }

    public MyQueue<NodeBST> postOrder(NodeBST<K,T> nodo, MyQueue<NodeBST> queue){
        if (nodo != null) {
            postOrder(nodo.getLeftChild(),queue);
            postOrder(nodo.getRightChild(),queue);
            queue.enqueue(nodo);
        }
        return queue;
    }

    //GETTER & SETTERS
    public NodeBST<K, T> getRoot() {
        return root;
    }

    public void setRoot(NodeBST<K, T> root) {
        this.root = root;
    }


}

