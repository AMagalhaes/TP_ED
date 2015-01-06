package Interfaces;


import exceptions.EmptyCollectionException;
import exceptions.EmptyQueueException;

/**
 * Trabalho realizado por:
 * Antonio Magalhaes
 * Pedro Fernandes
 */

/**
 * Interface StackADT contem os comportamentos de uma stack.
 *
 * @param <T> Indica que a interface e do tipo generico.
 */
public interface StackADT<T> {
    /**
     * Adds a element to the top of the stack
     *
     * @param element to be added
     */
    public void push(T element);

    /**
     * Removes a element from the top of the stack
     *
     * @return element removed
     * @throws EmptyCollectionException in case stack is empty
     */
    public T pop() throws EmptyQueueException, EmptyCollectionException;

    /**
     * Returns the top element of the stack
     *
     * @return top element
     * @throws EmptyCollectionException
     */
    public T peek() throws EmptyCollectionException;

    /**
     * Verifies if stack is empty
     *
     * @return true if empty, false if not
     */
    public boolean isEmpty();

    /**
     * Returns the size of the stack
     *
     * @return size of the stack
     */
    public int size();

    /**
     * Returns a string representation of the stack
     *
     * @return elements in a string
     */
    public String toString();

}
