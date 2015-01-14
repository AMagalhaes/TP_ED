package pt.ipp.estgf.facegraph.linkedQueue;

import pt.ipp.estgf.facegraph.Interfaces.QueueADT;
import pt.ipp.estgf.facegraph.exceptions.EmptyCollectionException;
import pt.ipp.estgf.facegraph.exceptions.EmptyQueueException;

/**
 * Work done by:
 * Antonio Magalhaes
 * Pedro Fernandes
 */

public class LinkedQueue<T> implements QueueADT<T> {


    private int count;
    private No<T> front, rear;

    //-----------------------------------------------------------------
    //  Creates an empty queue.
    //-----------------------------------------------------------------
    public LinkedQueue()
    {
        count = 0;
        front = rear = null;
    }

    //-----------------------------------------------------------------
    //  Adds the specified element to the rear of the queue.
    //-----------------------------------------------------------------
    public void enqueue (T element)
    {
        No<T> node = new No<T>(element);

        if (isEmpty())
            front = node;
        else
            rear.setProximo (node);

        rear = node;
        count++;
    }

    //-----------------------------------------------------------------
    //  Removes the element at the front of the queue and returns a
    //  reference to it. Throws an EmptyCollectionException if the
    //  queue is empty.
    //-----------------------------------------------------------------
    public T dequeue() throws EmptyCollectionException
    {
        if (isEmpty())
            throw new EmptyCollectionException ("queue");

        T result = front.getElemento();
        front = front.getProximo();
        count--;

        if (isEmpty())
            rear = null;

        return result;
    }

    //-----------------------------------------------------------------
    //  Returns a reference to the element at the front of the queue.
    //  The element is not removed from the queue.  Throws an
    //  EmptyCollectionException if the queue is empty.
    //-----------------------------------------------------------------
    public T first() throws EmptyCollectionException
    {
        if (isEmpty())
            throw new EmptyCollectionException ("queue");

        return front.getElemento();
    }

    //-----------------------------------------------------------------
    //  Returns true if this queue is empty and false otherwise.
    //-----------------------------------------------------------------
    public boolean isEmpty()
    {
        return (count == 0);
    }

    //-----------------------------------------------------------------
    //  Returns the number of elements currently in this queue.
    //-----------------------------------------------------------------
    public int size()
    {
        return count;
    }

    //-----------------------------------------------------------------
    //  Returns a string representation of this queue.
    //-----------------------------------------------------------------
    public String toString()
    {
        String result = "";
        No<T> current = front;

        while (current != null)
        {
            result = result + (current.getElemento()).toString() + "\n";
            current = current.getProximo();
        }

        return result;

    }
}