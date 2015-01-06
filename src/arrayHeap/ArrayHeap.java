package arrayHeap;

import Interfaces.HeapADT;
import arrayHeap.ArrayBinaryTree;

/**
 * Created by antoniomagalhaes on 12/12/14.
 */
public class ArrayHeap<T> extends ArrayBinaryTree<T> implements HeapADT<T> {

    int pos;

    public ArrayHeap() {
    }

    public ArrayHeap(T element) {
        super(element);
    }

    @Override
    public void addElement(T obj) {
        boolean added = false;
        T aux;
        T current = (T) new Object();
        if (isEmpty()) {
            tree[0] = obj;
            return;
        } else {
            pos = 0;
            while (!added) {
                if (tree[2 * pos + 1] == null) {
                    tree[2 * pos + 1] = obj;
                    reordenaHeap();
                    added = true;
                }
                if ((!added) && tree[2 * (pos + 1)] == null) {
                    tree[2 * (pos + 1)] = obj;
                    reordenaHeap();
                    added = true;
                }
                pos++;
            }
        }
    }


    @Override
    public T findMin() {
        return getRoot();
    }

    @Override
    public T removeMin() {
        int pos = 0;
        T aux = tree[0];
        while (tree[pos] != null) {
            pos++;
        }
        tree[0] = tree[pos - 1];
        tree[pos - 1] = null;
        reordenaHeap();
        return aux;
    }

    @Override
    public String toString() {
        String result = "";

        for (int pos = 0; pos < tree.length; pos++) {
            if (tree[pos] != null) {
                result += tree[pos] + "-";
            }
        }
        return result;
    }

    private void reordenaHeap() {
        T aux;
        pos = 0;
        while (tree[pos] != null) {
            boolean troca = false;
            if (tree[2 * pos + 1] != null) {
                if (((Comparable<T>) tree[pos]).compareTo(tree[2 * pos + 1]) > 0) {
                    aux = tree[pos];
                    tree[pos] = tree[2 * pos + 1];
                    tree[2 * pos + 1] = aux;
                    troca = true;
                }
            }
            if (tree[2 * (pos + 1)] != null) {
                if (((Comparable<T>) tree[pos]).compareTo(tree[2 * (pos + 1)]) > 0) {
                    aux = tree[pos];
                    tree[pos] = tree[2 * (pos + 1)];
                    tree[2 * (pos + 1)] = aux;
                    troca = true;
                }
            }
            if (!troca) {
                pos++;
            }
        }
    }
}
