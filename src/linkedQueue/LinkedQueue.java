package linkedQueue;

import Interfaces.QueueADT;
import exceptions.EmptyQueueException;

/**
 * Created by antoniomagalhaes on 31/10/14.
 */
public class LinkedQueue<T> implements QueueADT<T> {
    private No<T> no = null;
    private int size = 0;

    public LinkedQueue() {
    }

    @Override
    public void enqueue(T element) {
        No aux = new No(element);
        if (no == null) {
            no = aux;
            size++;
        } else {
            aux.setProximo(no);
            no = aux;
            size++;
        }
    }

    @Override
    public T dequeue() throws EmptyQueueException {
        if (no == null) {
            return (T) no;
        }
        No aux = null;
        if (no.getProximo() != null) {
            aux = no;
            no = no.getProximo();
        } else {
            no = aux;
            no.setProximo(null);
            size--;
        }
        return (T) aux;
    }

    @Override
    public T first() throws EmptyQueueException {
        if (!isEmpty()) {
            System.out.println("first = " + no);
        }
        return null;
    }

    @Override
    public boolean isEmpty() throws EmptyQueueException {
        if (size > 0) {
            return false;
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        No aux;
        String result=null;
  while (no.getProximo()!= null){
      aux = no;
      result +=" " + no.getElemento();
      no=aux.getProximo();
  }
        return  result+ " " +no.getElemento();
    }
}
