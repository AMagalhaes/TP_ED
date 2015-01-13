package pt.ipp.estgf.facegraph.Interfaces;

import pt.ipp.estgf.facegraph.Interfaces.ListADT;
import pt.ipp.estgf.facegraph.exceptions.ElementNotFoundException;

/**
 * This interface repersents an unordered list.
 *
 * @author Gil Mendes (gil0mendes) - <gil00mendes@gmail.com>
 */
public interface UnorderedList<T> extends ListADT<T>
{
	/**
	 * Adds a new element at the beginning of the list.
	 *
	 * @param element The element to be added to this list
	 */
	public void addToFront(T element);

	/**
	 * Adds a new element at the end of the list.
	 *
	 * @param element The element to be added to this list
	 */
	public void addToRear(T element);

	/**
	 * Adds a new element after another already inserted in the list.
	 *
	 * @param element The element to be added to this list
	 * @param target Target element
	 */
	public void addAfter(T element, T target) throws ElementNotFoundException;
}
