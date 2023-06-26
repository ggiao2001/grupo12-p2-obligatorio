package uy.edu.um.prog2.adt.TADs;

import uy.edu.um.prog2.adt.Exceptions.EmptyQueueException;
import uy.edu.um.prog2.adt.Exceptions.EmptyTreeException;
import uy.edu.um.prog2.adt.Interfaces.MyBinarySearchTree;
import uy.edu.um.prog2.adt.Interfaces.MyQueue;

public class MyBinarySearchTreeImp<K extends Comparable<K>, T> implements MyBinarySearchTree<K, T> {

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

        NodeBST<K, T> nuevo = new NodeBST<>(key, data);
        if (root != null) {
            insertRecursivo(this.root, nuevo);
        } else {
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
                    if (eliminar != null) {
                        if (eliminar.getLeftChild() == null && eliminar.getRightChild() == null) {
                            //Si el que voy a eliminar no tiene hijos, lo elimino nomas
                            if (padre.getRightChild() != null && padre.getRightChild().equals(eliminar)) {
                                padre.setRightChild(null);
                            } else {
                                padre.setLeftChild(null);
                            }
                        } else if (eliminar.getLeftChild() == null && eliminar.getRightChild() != null) {
                            //Caso con solo hijo derecho
                            if (padre.getRightChild() != null && padre.getRightChild().equals(eliminar)) {
                                padre.setRightChild(eliminar.getRightChild());
                            } else {
                                padre.setLeftChild(eliminar.getRightChild());
                            }

                        } else if (eliminar.getLeftChild() != null && eliminar.getRightChild() == null) {
                            //Caso con solo hijo izquierdo
                            if (padre.getRightChild() != null && padre.getRightChild().equals(eliminar)) {
                                padre.setRightChild(eliminar.getRightChild());
                            } else {
                                padre.setLeftChild(eliminar.getRightChild());
                            }

                        } else {
                            //Caso con dos hijos
                            MyQueue<NodeBST> queue = new MyQueueImp<>();
                            queue = inOrder(eliminar, queue);

                            //Borro el que quiero eliminar, setteando el hijo de su padre a null.
                            if (padre.getRightChild() != null && padre.getRightChild().equals(eliminar)) {
                                padre.setRightChild(null);
                            } else {
                                padre.setLeftChild(null);
                            }
                            //Y lo saco de la queue
                            eliminar = queue.dequeue();

                            //Recorro la queue insertando
                            for (int i = 0; i < queue.size(); i++) {
                                NodeBST<K, T> nuevo = new NodeBST<>();
                                nuevo = queue.dequeue();
                                insertRecursivo(padre, nuevo);

                            }
                        }
                    } else {
                        throw new EmptyTreeException();
                    }

                }
            } else {
                throw new EmptyTreeException();
            }
        } catch (EmptyQueueException e) {
            System.out.println("Dequeue Problem: Queue vacia");
        }
    }

    //METHODS AUXILIARES
    private NodeBST<K, T> insertRecursivo(NodeBST<K, T> actual, NodeBST<K, T> nuevo) {
        if (actual == null) {
            return nuevo;
        }

        if (nuevo.getKey().compareTo(actual.getKey()) < 0) {
            actual.setLeftChild(insertRecursivo(actual.getLeftChild(), nuevo));
        } else if (nuevo.getKey().compareTo(actual.getKey()) > 0) {
            actual.setRightChild(insertRecursivo(actual.getRightChild(), nuevo));
        } else {
            // Si tengo dos keys iguales. Mantengo el nodo que ya existía
            // y le agrego los nuevos datos.
            actual.setData(nuevo.getData());
        }

        // Update height and balance factor
        actual.setHeight(1 + Math.max(getHeight(actual.getLeftChild()), getHeight(actual.getRightChild())));
        int balanceFactor = getBalanceFactor(actual);

        // Rebalance the tree if necessary
        if (balanceFactor > 1 && nuevo.getKey().compareTo(actual.getLeftChild().getKey()) < 0) {
            // Left-Left case
            return rotateRight(actual);
        }
        if (balanceFactor < -1 && nuevo.getKey().compareTo(actual.getRightChild().getKey()) > 0) {
            // Right-Right case
            return rotateLeft(actual);
        }
        if (balanceFactor > 1 && nuevo.getKey().compareTo(actual.getLeftChild().getKey()) > 0) {
            // Left-Right case
            actual.setLeftChild(rotateLeft(actual.getLeftChild()));
            return rotateRight(actual);
        }
        if (balanceFactor < -1 && nuevo.getKey().compareTo(actual.getRightChild().getKey()) < 0) {
            // Right-Left case
            actual.setRightChild(rotateRight(actual.getRightChild()));
            return rotateLeft(actual);
        }

        return actual;
    }

    private int getHeight(NodeBST<K, T> node) {
        if (node == null) {
            return 0;
        }
        return node.getHeight();
    }

    private int getBalanceFactor(NodeBST<K, T> node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.getLeftChild()) - getHeight(node.getRightChild());
    }

    private NodeBST<K, T> rotateRight(NodeBST<K, T> node) {
        NodeBST<K, T> newRoot = node.getLeftChild();
        NodeBST<K, T> temp = newRoot.getRightChild();

        // Perform rotation
        newRoot.setRightChild(node);
        node.setLeftChild(temp);

        // Update heights
        node.setHeight(1 + Math.max(getHeight(node.getLeftChild()), getHeight(node.getRightChild())));
        newRoot.setHeight(1 + Math.max(getHeight(newRoot.getLeftChild()), getHeight(newRoot.getRightChild())));

        return newRoot;
    }

    private NodeBST<K, T> rotateLeft(NodeBST<K, T> node) {
        NodeBST<K, T> newRoot = node.getRightChild();
        NodeBST<K, T> temp = newRoot.getLeftChild();

        // Perform rotation
        newRoot.setLeftChild(node);
        node.setRightChild(temp);

        // Update heights
        node.setHeight(1 + Math.max(getHeight(node.getLeftChild()), getHeight(node.getRightChild())));
        newRoot.setHeight(1 + Math.max(getHeight(newRoot.getLeftChild()), getHeight(newRoot.getRightChild())));

        return newRoot;
    }

    private NodeBST<K, T> busquedaRecursiva(NodeBST<K, T> actual, K key) {
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

    private NodeBST<K, T> busquedaRecursivaPadre(NodeBST<K, T> actual, K key, NodeBST<K, T> parent) {
        if (actual == null) {
            return null;
        }

        if (key.compareTo(actual.getKey()) < 0) {
            return busquedaRecursivaPadre(actual.getLeftChild(), key, actual);

        } else if (key.compareTo(actual.getKey()) > 0) {
            return busquedaRecursivaPadre(actual.getRightChild(), key, actual);

        } else {
            // Encontre el nodo entonces devuelve el padre
            // Si el padre es null entonces estoy en la root (Que deberia cortar antes igual)
            //Devuelvo null
            return parent;
        }
    }


    public MyQueue<NodeBST> inOrder(NodeBST<K, T> nodo, MyQueue<NodeBST> queue) {

        if (nodo != null) {
            inOrder(nodo.getLeftChild(), queue);
            queue.enqueue(nodo);
            inOrder(nodo.getRightChild(), queue);
        }
        return queue;
    }

    public MyQueue<NodeBST> preOrder(NodeBST<K, T> nodo, MyQueue<NodeBST> queue) {

        if (nodo != null) {
            queue.enqueue(nodo);
            preOrder(nodo.getLeftChild(), queue);
            preOrder(nodo.getRightChild(), queue);
        }
        return queue;
    }

    public MyQueue<NodeBST> postOrder(NodeBST<K, T> nodo, MyQueue<NodeBST> queue) {
        if (nodo != null) {
            postOrder(nodo.getLeftChild(), queue);
            postOrder(nodo.getRightChild(), queue);
            queue.enqueue(nodo);
        }
        return queue;
    }

    public MyLinkedListImp<T> getRange(K startDate, K endDate) {
        MyLinkedListImp<T> result = new MyLinkedListImp<>();
        getRange(root, startDate, endDate, result);
        return result;
    }

    private void getRange(NodeBST<K, T> node, K startDate, K endDate, MyLinkedListImp<T> result) {
        if (node == null) {
            return;
        }

        int compareStart = startDate.compareTo(node.getKey());
        int compareEnd = endDate.compareTo(node.getKey());

        if (compareStart < 0) {

            getRange(node.getLeftChild(), startDate, endDate, result);
        }

        if (compareStart <= 0 && compareEnd >= 0) {

            result.add(node.getData());
        }

        if (compareEnd > 0) {

            getRange(node.getRightChild(), startDate, endDate, result);
        }
    }

    //GETTER & SETTERS
    public NodeBST<K, T> getRoot() {
        return root;
    }

    public void setRoot(NodeBST<K, T> root) {
        this.root = root;
    }


}

