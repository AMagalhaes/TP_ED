package pt.ipp.estgf.facegraph.Interfaces;

/**
 * Work done by:
 * Antonio Magalhaes
 * Pedro Fernandes
 */

import pt.ipp.estgf.facegraph.exceptions.ElementNotFoundException;

/**
 * Interface UnorderedListADT contains the behavior of a non ordernada list.
 *
 * @param <T> Indicates that the interface and the generic type.
 */

public interface UnorderedListADT<T> extends ListADT<T> {
    /**
     * Adds a element to the front of the list
     *
     * @param element to be added to the list
     */
    public void addFront(T element);

    /**
     * Adds a element to the rear of the list
     *
     * @param element to be added
     */
    public void addRear(T element);

    /**
     * Adds a element after a target element
     *
     * @param element to be added
     * @param target  element that will stay before the element added
     */
    public void addAfter(T element, T target) throws ElementNotFoundException;
}
