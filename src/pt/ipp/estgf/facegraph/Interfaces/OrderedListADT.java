package pt.ipp.estgf.facegraph.Interfaces;

/**
 * This interface represents an ordered list.
 *
 * @author Gil Mendes (gil0mendes) - <gil00mendes@gmail.com>
 */
public interface OrderedListADT<T> extends ListADT<T>
{
	/**
	 * Adds the specified element to this list at the proper location
	 *
	 * @param element The element to be added to this list
	 */
	public void add(T element);
}
