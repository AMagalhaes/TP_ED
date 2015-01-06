package Interfaces;




import exceptions.ElementNotFoundException;

import java.util.Iterator;

/**
 * Created by antoniomagalhaes on 04/12/14.
 */
public interface BinaryTreeADT<T> {
        public T getRoot();
        public String toString();
        public boolean isEmpty();
        public int size();
        public  boolean countains(T element) throws ElementNotFoundException;
        public  T find(T targetElement) throws ElementNotFoundException;
        public String tooString();
        public Iterator<T> iteratorPreOrder();
        public Iterator<T> iteratorInOrder();
        public Iterator<T> iteratorPostOrder();
        public Iterator<T> iteratorlevelOrder();
    }
