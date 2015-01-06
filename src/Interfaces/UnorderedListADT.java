package Interfaces;

/**
 * Trabalho realizado por:
 * Antonio Magalhaes
 * Pedro Fernandes
 */

import exceptions.ElementNotFoundException;

/**
 * Interface UnorderedListADT contem os comportamentos de uma lista nao ordernada.
 *
 * @param <T> Indica que a interface e do tipo generico.
 */

public interface UnorderedListADT<T> extends ListADT<T> {
    /**
     * Adds a element to the front of the list
     *
     * @param element to be added to the list
     */
    public void addToFront(T element);

    /**
     * Adds a element to the rear of the list
     *
     * @param element to be added
     */
    public void addToRear(T element);

    /**
     * Adds a element after a target element
     *
     * @param element to be added
     * @param target  element that will stay before the element added
     */
    public void addAfter(T element, T target) throws ElementNotFoundException;
}
