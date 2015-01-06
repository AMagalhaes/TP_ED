package linkedStack;

import Interfaces.StackADT;
import exceptions.EmptyCollectionException;
import linkedStack.No;


/**
 * Created by antoniomagalhaes on 17/10/14.
 */
public class LinkedStack<T> implements StackADT<T> {
    private No<T> no = null;
    private int size = 0;

    public LinkedStack() {
    }


    public void push(T elemento) {
        No aux = new No(elemento);
        if (no == null) {
            no = aux;
        } else {
            aux.setProximo(no);
            no = aux;
            size++;
        }

    }

    @Override
    public T pop() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Lista Vazia");
        }

        No aux = no.getProximo();
        no = aux;

        this.size--;

        return (T) aux.getElemento();
    }


    @Override
    public T peek() throws EmptyCollectionException {
        if (!isEmpty()) {
            System.out.println("o elemento do topo da stack é " + no);

        }
        return null;
    }


    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

}
