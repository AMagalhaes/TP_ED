package Interfaces;

/**
 * Work done by:
 * Antonio Magalhaes
 * Pedro Fernandes
 */

public interface HeapADT<T> extends BinaryTreeADT<T> {
    /**
     * Adds the element will Heap
     */
    public void addElement(T obj);

    /**
     * Removes the element with minimum value in this Heap
     */
    public T removeMin();


    /**
     * Find the element with minimum value in this Heap
     */
    public T findMin();

}
