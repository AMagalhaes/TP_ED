package pt.ipp.estgf.facegraph.linkedStack;

import pt.ipp.estgf.facegraph.Interfaces.StackADT;
import pt.ipp.estgf.facegraph.exceptions.EmptyCollectionException;
import pt.ipp.estgf.facegraph.linkedQueue.No;

/**
 * Work done by:
 * Antonio Magalhaes
 * Pedro Fernandes
 */

public class LinkedStack<T> implements StackADT<T> {
    private No<T> no = null;
    private int size = 0;

    public LinkedStack() {
    }

    /**
     * Adds one element to the top of this stack.
     *
     * @param element element to be pushed onto stack.
     */
    public void push(T element) {
        No aux = new No(element);
        if (no == null) {
            no = aux;
        } else {
            aux.setProximo(no);
            no = aux;
        }
        size++;
    }

    @Override
    public T pop() throws EmptyCollectionException {
        // Check if the stack if empty
        if (isEmpty()) {
            throw new EmptyCollectionException("Lista Vazia");
        }

        No aux = no;
        no = aux;

        // Decrements the size
        this.size--;

        return (T) aux.getElemento();
    }

    /**
     * Returns without removing the top element of this stack.
     *
     * @return T element on top of the stack
     */
    @Override
    public T peek() throws EmptyCollectionException {
        if (!isEmpty()) {
            System.out.println("o elemento do topo da stack é " + no);

        }
        return null;
    }

    /**
     * Returns true if this stack contains no elements.
     *
     * @return boolean whether or not this stack is empty.
     */
    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /**
     * Returns the number of elements in this stack.
     *
     * @return int number of elements in this stack.
     */
    @Override
    public int size() {
        return size;
    }

}
