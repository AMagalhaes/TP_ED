package pt.ipp.estgf.facegraph.lists;

import pt.ipp.estgf.facegraph.Interfaces.UnorderedList;
import pt.ipp.estgf.facegraph.exceptions.EmptyUnorderListException;

import java.util.Iterator;

/**
 * Work done by:
 * Antonio Magalhaes
 * Pedro Fernandes
 */
public class ArrayUnorderedList<T> implements UnorderedList<T> {
    private Object[] item;

    private int size, modCount;

    public ArrayUnorderedList( int capacity) {
        this.size = 0;
        this.modCount = 0;
        item = new Object[capacity];
    }

    /**
     * Adds a new element in the list beginning
     */
    @Override
    public void addToFront(Object elem) {
        if (this.size == item.length){
            this.expandArray();
        }
        modCount++;
        if (isEmpty()) {
            item[0] = elem;
        } else {
            item[size] = elem;
        }
        size++;
    }

    /**
     * Inserts a new element in the list of the tail
     */
    @Override
    public void addToRear(Object elem) {
        if (this.size == item.length){
            this.expandArray();
        }
        modCount++;
        if (isEmpty()) {
            item[0] = elem;
            size++;
        } else {
            for (int pos = size; pos > 0; pos--) {
                item[pos] = item[pos - 1];
            }
            item[0] = elem;
            size++;
        }
    }

    /**
     * Inserts an element after a given target (element)
     *
     * @param target element that will stay before the element added
     * @param elem
     */
    @Override
    public void addAfter(Object target, Object elem) {
        modCount++;
        if (isEmpty()) {
            item[0] = elem;
            size++;
        } else {
            for (int pos = 0; pos < size; pos++) {
                if (item[pos] == target) {
                    for (int aux = size; aux > pos; aux--) {
                        item[aux] = item[aux - 1];
                    }
                    item[pos] = elem;
                    size++;
                    pos = size;
                }
            }
        }

    }

    @Override
    public T removeFirst() throws EmptyUnorderListException {
        Object obj;
        modCount--;
        if (isEmpty()) {
            return null;
        } else {
            obj = item[0];
            item[0] = null;
            size--;
        }
        for (int pos = 0; pos < size; pos++) {
            item[pos] = item[pos + 1];
        }
        return (T) obj;
    }

    @Override
    public T removeLast() throws EmptyUnorderListException {
        Object obj;
        modCount--;
        if (isEmpty()) {
            return null;
        } else {
            obj = item[size - 1];
            item[size - 1] = null;
            size--;
        }

        return (T) obj;
    }

    @Override
    public T remove(T element) throws EmptyUnorderListException {
        boolean exist = false;
        modCount--;
        if (isEmpty()) {
            System.out.println("Lista vazia");
            return null;
        } else {
            for (int pos = 0; pos < size; pos++) {
                if (item[pos] == element) {
                    Object obj = item[pos];
                    System.out.println("passo");
                    for (int aux = pos; aux < size; aux++) {
                        item[aux] = item[aux + 1];
                    }
                    size--;
                    return (T) obj;
                }

            }
        }
        System.out.println(" Esse elemento não existe;");
        return null;


    }

    @Override
    public T first() throws EmptyUnorderListException {
        return (T) item[0];
    }

    @Override
    public T last() throws EmptyUnorderListException {
        return (T) item[size - 1];
    }

    @Override
    public boolean contains(T element) throws EmptyUnorderListException {
        if (isEmpty()) {
            System.out.println(" Array vazio");
            return false;
        }
        for (int pos = 0; pos < size; pos++) {
            if (item[pos].equals(element)) {
                return true;
            }
        }
        System.out.println("Esse objecto não existe");
        return false;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator iterator() {
        return new myIterator();
    }

    @Override
    public String toString() {
        String result = "";
        for (int pos = size - 1; pos >= 0; pos--) {
            result += " " + pos + " -> " + item[pos];
        }
        return result;
    }

    public class myIterator<T> implements Iterator<T> {
        private int expectedModcount = modCount;
        private int index = 0;
        private boolean okToRemove = false;


        @Override
        public boolean hasNext() {
            if (expectedModcount == modCount) {
                return index < size;
            }
            System.out.println(" A lista foi alterada");
            return false;
        }

        @Override
        public T next() {
            okToRemove = true;
            if (expectedModcount == modCount) {
                return (T) item[index++];
            }
            System.out.println(" A lista foi alterada");
            return null;
        }

        @Override
        public void remove() {
            for (int pos = index; pos < index; pos++) {
                item[pos] = item[pos + 1];
                okToRemove = false;
            }
            if (expectedModcount != modCount) {
                System.out.println(" A lista foi alterada");
            }
            if (okToRemove != true) {
                System.out.println(" Tem de reposicionar o apontador(next())");
            }
        }
    }

    private void expandArray(){
        Object[] aux = item;
        item = new Object[aux.length*2];
        System.arraycopy(aux, 0, item, 0, aux.length);
    }


}
