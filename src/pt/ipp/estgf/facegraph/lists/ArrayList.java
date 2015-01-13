package pt.ipp.estgf.facegraph.lists;

import pt.ipp.estgf.facegraph.exceptions.ElementNotFoundException;
import pt.ipp.estgf.facegraph.exceptions.EmptyCollectionException;

import java.util.Iterator;

/**
 * This class implements a list usign an array.
 *
 * @author Gil Mendes (gil0mendes) - <gil00mendes@gmail.com>
 */
public abstract class ArrayList<T> extends AbstractList<T>
{

	protected T[] list;
	protected int rear = 0;
	protected final int DEFAULT = 10;

	//--------------------------------------------------------------------------
	// PUBLIC
	//--------------------------------------------------------------------------
	/**
	 * This method creates an instance of this class, the default list will have
	 * 10 positions
	 */
	public ArrayList()
	{
		super();
		this.list = (T[]) (new Object[this.DEFAULT]);
	}

	/**
	 * This method creates an instance of this class.
	 *
	 * @param initialPositions Number of initial positions that the list will
	 * have.
	 */
	public ArrayList(int initialPositions)
	{
		this.list = (T[]) (new Object[initialPositions]);
	}

	/**
	 * Removes and returns the first element from this list.
	 *
	 * @return the first element from this list
	 * @throws EmptyCollectionException Indicates that the list is empty
	 */
	@Override
	public T removeFirst() throws EmptyCollectionException
	{
		// Checks if the list is empty
		if (this.isEmpty()) {
			throw new EmptyCollectionException("Empty");
		}

		// Saves the element to be removed
		T temp = this.list[0];

		// Drag all the elements, -1
		for (int index = 0; index < this.size(); index++) {
			this.list[index] = this.list[index + 1];
		}

		// Decreases the rear and the count
		this.rear--;
		this.count--;

		// Sets the last position to null
		this.list[this.rear] = null;

		// Returns the element removed
		return temp;
	}
	
	/**
	 * Removes and returns the last element from this list.
	 *
	 * @return the last element from this list
	 * @throws EmptyCollectionException Indicates that the list is empty
	 */
	@Override
	public T removeLast() throws EmptyCollectionException {
		// Check if the list is empty
		if (this.isEmpty()) {
			throw new EmptyCollectionException("Empty");
		}

		// Saves the element to be removed
		T temp = this.list[this.rear - 1];

		// Decreasses the rear and the count
		this.rear--;
		this.count--;

		// Sets the last position to null
		this.list[this.rear] = null;

		// Returns the element remoced
		return temp;
	}
	
	/**
	 * Removes and returns the specified element from this list.
	 *
	 * @param element the element to be removed from the list
	 *
	 * @throws EmptyCollectionException Indicates that the list is empty
	 * @throws ElementNotFoundException Indicates that the element to be removed
	 * is not a part of the list
	 */
	@Override
	public T remove(T element) throws EmptyCollectionException, ElementNotFoundException {
		// Check if the list is empty
		if (this.isEmpty()) {
			throw new EmptyCollectionException("Empty");
		}

		// Scroll through the list until you find the element request
		int scan = 0;
		while (scan < this.rear && !element.equals(this.list[scan])) {
			scan++;
		}

		// If the element is not found
		if (!element.equals(this.list[scan])) {
			throw new ElementNotFoundException("Not Found");
		}

		// Saves the element to be removed
		T temp = this.list[scan];

		// Drag all the elements, -1, from the target
		for (int index = scan; index < this.rear; index++) {
			this.list[index] = this.list[index + 1];
		}

		// Decreasses the rear and the count
		this.rear--;
		this.count--;

		// Sets the last position to null
		this.list[this.rear] = null;

		// Returns the element remoced
		return temp;
	}
	
	/**
	 * Returns a reference to the first element in this list.
	 *
	 * @return a reference to the first element in this list
	 * @throws EmptyCollectionException Indicates that the list is empty
	 */
	@Override
	public T first() throws EmptyCollectionException {
		// Check if the list is empty
		if (this.isEmpty()) {
			throw new EmptyCollectionException("Empty");
		}

		// Returns the frist element of the list
		return this.list[0];
	}
	
	/**
	 * Returns a reference to the last element in this list.
	 *
	 * @return a reference to the last element in this list
	 * @throws EmptyCollectionException Indicates that the list is empty
	 */
	@Override
	public T last() throws EmptyCollectionException {
		// Check if the list is empty
		if (this.isEmpty()) {
			throw new EmptyCollectionException("Empty");
		}

		// Returns the last element of the list
		return this.list[this.rear - 1];
	}
	
	/**
	 * Returns true if this list contains the specified target element.
	 *
	 * @param target the target that is being sought in the list
	 * @return true if the list contains this element
	 * @throws EmptyCollectionException Indicates that the list is empty
	 */
	@Override
	public boolean contains(T target) throws EmptyCollectionException {
		// Check if the list is empty
		if (this.isEmpty()) {
			throw new EmptyCollectionException("Empty");
		}

		for (int index = 0; index < this.rear; index++) {
			if (this.list[index].equals(target)) {
				return true;
			}
		}

		return false;
	}
	
	/**
	 * Returns an iterator for the elements in this list.
	 *
	 * @return an iterator over the elements in this list
	 */
	@Override
	public Iterator<T> iterator() {
		return new ArrayListIterator<T>();
	}

	/**
	 * Removes all of the elements from this list.
	 */
	@Override
	public void clear()
	{
		super.clear();
		this.rear = 0;
		this.list = (T[])(new Object[this.DEFAULT]);
	}
	
	/**
	 * Returns a string representation of this list.
	 *
	 * @return a string representation of this list
	 */
	@Override
	public String toString() {
		String out = "";

		for (int index = 0; index < this.size(); index++) {
			out += this.list[index];

			if (index != (this.size() - 1)) {
				out += ";";
			}
		}

		return out;
	}
	
	//--------------------------------------------------------------------------
	// PROTECTED
	//--------------------------------------------------------------------------
	/**
	 * Expands the ability of the list to double the current size.
	 */
	protected void ExpandeCapacity() {
		// Creates a new list, with double the current positions
		T[] newList = (T[]) (new Object[this.list.length * 2]);

		// Copies data from the old to the new list
		System.arraycopy(this.list, 0, newList, 0, this.list.length);

		// Replaces list
		this.list = newList;
	}
	
	protected class ArrayListIterator<T> implements Iterator<T> {

		protected int pos = 0;

		@Override
		public boolean hasNext() {
			return (ArrayList.this.list[pos] != null) ? true : false;
		}

		@Override
		public T next() {
			return (T) ArrayList.this.list[pos++];
		}

		@Override
		public void remove() {
			try {
				ArrayList.this.remove(ArrayList.this.list[--this.pos]);
			} catch (Exception ex) {
				this.pos++;
			}
		}
	}

	public void removeAll() {
		while (!this.isEmpty()) {
			try {
				this.removeFirst();
			} catch (EmptyCollectionException e) {
			}
		}
	}
}
