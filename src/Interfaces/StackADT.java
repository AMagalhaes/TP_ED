package Interfaces;


import exceptions.EmptyCollectionException;
import exceptions.EmptyQueueException;

/**
 * Created by antoniomagalhaes on 16/10/14.
 */
public interface StackADT<T> {
    public void push(T elemento);
    public T pop() throws EmptyQueueException, EmptyCollectionException;
    public T peek()throws EmptyCollectionException;
    public boolean isEmpty();
    public int size();
    public String toString();

}
