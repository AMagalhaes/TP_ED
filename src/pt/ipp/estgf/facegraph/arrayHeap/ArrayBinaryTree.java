package pt.ipp.estgf.facegraph.arrayHeap;

import pt.ipp.estgf.facegraph.ArrayUnorderedList;
import pt.ipp.estgf.facegraph.Interfaces.BinaryTreeADT;
import java.util.Iterator;


/**
 * Work done by:
 * Antonio Magalhaes
 * Pedro Fernandes
 */

public class ArrayBinaryTree<T> implements BinaryTreeADT<T> {
    protected int count;
    public T[] tree = (T[]) new Object[100];
    private int pos = 0;
    private final int CAPACITY = 30;

    public ArrayBinaryTree() {
        this.count = 0;
        T[] tree = (T[]) new Object[CAPACITY];
    }

    public ArrayBinaryTree(T element) {
        this.count = 1;
        T[] tree = (T[]) new Object[CAPACITY];
        tree[0] = element;
    }

    @Override
    public T getRoot() {
        return tree[0];
    }

    @Override
    public boolean isEmpty() {
        if (tree[0] == null) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return getcount();
    }

    @Override
    public boolean countains(T element) {
        for (int pos = 0; pos < tree.length; pos++) {
            if (tree[pos] != null) {
                if (tree[pos].equals(element)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public T find(T targetElement) {
        for (int pos = 0; pos < tree.length; pos++) {
            if (tree[pos] != null) {
                if (tree[pos].equals(targetElement)) {
                    return tree[pos];
                }
            }
        }
        return null;
    }

    @Override
    public String tooString() {
        String result = "";
        for (int pos = 0; pos < tree.length; pos++) {
            if (tree[pos] != null) {
                result += tree[pos];
            }
        }
        return null;
    }

    @Override
    public Iterator<T> iteratorPreOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>(CAPACITY);
        preOrder(tempList);
        return tempList.iterator();

    }

    /**
     * to position in the array n
     * the left child is stored in ((2*n)+1)
     * the right child(2*(n+1))
     */

    protected void preOrder(ArrayUnorderedList<T> tempList) {
        tempList.addRear(tree[getPos()]);
        setPos(2 * getPos() + 1);
        preOrder(tempList);
        setPos(2 * (getPos() + 1));
        preOrder(tempList);
    }


    @Override
    public Iterator<T> iteratorInOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>(CAPACITY);
        inOrder(tempList);
        return tempList.iterator();
    }

    protected void inOrder(ArrayUnorderedList<T> tempList) {
        setPos(2 * getPos() + 1);
        preOrder(tempList);
        tempList.addRear(tree[getPos()]);
        setPos(2 * (getPos() + 1));
        preOrder(tempList);
    }

    @Override
    public Iterator<T> iteratorPostOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>(CAPACITY);
        preOrder(tempList);
        return tempList.iterator();
    }

    protected void postOrder(ArrayUnorderedList<T> tempList) {
        setPos(2 * getPos() + 1);
        preOrder(tempList);
        setPos(2 * (getPos() + 1));
        preOrder(tempList);
        tempList.addRear(tree[getPos()]);
    }

    @Override
    public Iterator<T> iteratorlevelOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>(CAPACITY);
        levelOrder(tempList);
        return tempList.iterator();
    }

    protected void levelOrder(ArrayUnorderedList<T> tempList) {
        setPos(getPos() + 1);
        if (tree[getPos()] != null) {
            tempList.addRear(tree[getPos()]);
        }
    }


    public void expandCapacity() {
        T[] aux = (T[]) new Object[tree.length * 2];
        System.arraycopy(tree, 0, aux, 0, tree.length);
        tree = aux;
    }
//           getters and setters

    public int getcount() {
        return count;
    }

    public void setcount(int count) {
        this.count = count;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
}
