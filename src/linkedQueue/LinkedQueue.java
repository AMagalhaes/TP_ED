package linkedQueue;

import Interfaces.QueueADT;
import exceptions.EmptyQueueException;

/**
 * Work done by:
 * Antonio Magalhaes
 * Pedro Fernandes
 */

public class LinkedQueue<T> implements QueueADT<T> {
    private No<T> no = null;
    private int size = 0;

    public LinkedQueue() {
    }

    /**
     * Adds one element to the rear of this queue.
     *
     * @param element the element to be added to the rear of this queue
     */
    @Override
    public void enqueue(T element) {
        No aux = new No(element);
        if (no == null) {
            no = aux;
            size++;
        } else {
            aux.setProximo(no);
            no = aux;
            size++;
        }
    }

    /**
     * Removes and returns the element at the front of this queue.
     *
     * @return the element at the front of this queue
     * @throws EmptyQueueException Indicates that the queue is empty
     */
    @Override
    public T dequeue() throws EmptyQueueException {
        if (no == null) {
            return (T) no;
        }
        No aux = null;
        if (no.getProximo() != null) {
            aux = no;
            no = no.getProximo();
        } else {
            no = aux;
            no.setProximo(null);
            size--;
        }
        return (T) aux;
    }

    /**
     * Returns without removing the element at the front of this queue.
     *
     * @return the first element in this queue
     */
    @Override
    public T first() throws EmptyQueueException {
        if (!isEmpty()) {
            System.out.println("first = " + no);
        }
        return null;
    }

    /**
     * Returns true if this queue contains no elements.
     *
     * @return true if this queue is empty
     */
    @Override
    public boolean isEmpty() throws EmptyQueueException {
        if (size > 0) {
            return false;
        }
        return true;
    }

    /**
     * Returns the number of elements in this queue.
     *
     * @return the integer representation of the size of this queue
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns a string representation of this queue.
     *
     * @return the string representation of this queue
     */
    @Override
    public String toString() {
        No aux;
        String result = null;
        while (no.getProximo() != null) {
            aux = no;
            result += " " + no.getElemento();
            no = aux.getProximo();
        }
        return result + " " + no.getElemento();
    }
}
