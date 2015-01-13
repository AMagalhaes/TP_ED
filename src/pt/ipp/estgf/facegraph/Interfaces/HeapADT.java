package pt.ipp.estgf.facegraph.Interfaces;

import pt.ipp.estgf.facegraph.exceptions.EmptyCollectionException;

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
    public T removeMin() throws EmptyCollectionException;


    /**
     * Find the element with minimum value in this Heap
     */
    public T findMin() throws EmptyCollectionException;
    /**
     * Remove all the elements of this Heap
     */
    public void removeAll() throws EmptyCollectionException;

}
