package Interfaces;

import exceptions.EmptyQueueException;

/**
 * Trabalho realizado por:
 * Antonio Magalhaes
 * Pedro Fernandes
 */

/**
 * Interface QueueADT contem os comportamentos de uma queue.
 *
 * @param <T> Indica que a interface e do tipo generico.
 */
public interface QueueADT<T> {
    /**
     * Adds a element to the rear of the queue
     *
     * @param element
     */
    public void enqueue(T element);

    /**
     * Removes a element from the front of the queue
     *
     * @return element removed
     * @throws exceptions.EmptyQueueException
     */
    public T dequeue() throws EmptyQueueException;

    /**
     * Gets first element of the queue
     *
     * @return first element of the queue
     * @throws exceptions.EmptyQueueException
     */
    public T first() throws EmptyQueueException;

    /**
     * Verifies if the queue is empty
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() throws EmptyQueueException;

    /**
     * Obtains size of the queue
     *
     * @return size of queue
     */
    public int size();

    /**
     * Returns a string representation of the queue
     *
     * @return queue elements in a string
     */
    public String toString();


}
