package Interfaces;

import exceptions.EmptyQueueException;

/**
 * Created by antoniomagalhaes on 31/10/14.
 */
public interface QueueADT<T> {
    public void enqueue(T element);

    public T dequeue() throws EmptyQueueException;

    public T first()throws EmptyQueueException;

    public boolean isEmpty() throws EmptyQueueException;

    public int size();

    public String toString();


}
