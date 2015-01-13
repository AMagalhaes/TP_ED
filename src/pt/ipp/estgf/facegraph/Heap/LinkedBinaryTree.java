package pt.ipp.estgf.facegraph.Heap;




import pt.ipp.estgf.facegraph.lists.ArrayUnorderedList;
import pt.ipp.estgf.facegraph.Interfaces.BinaryTreeADT;
import pt.ipp.estgf.facegraph.exceptions.ElementNotFoundException;

import java.util.Iterator;

/**
 * Created by antoniomagalhaes on 27/11/14.
 */
public class LinkedBinaryTree<T> implements BinaryTreeADT<T> {
    protected final int DEFAULT_CAPACITY = 10;
    protected int count;
    protected BinaryTreeNode<T> root;
    private BinaryTreeNode<T> head = null;
    private BinaryTreeNode tail = null;

    /**
     *
     */
    public LinkedBinaryTree() {
        this.count = 0;
        this.root = null;
    }

    /**
     * @param element
     */
    public LinkedBinaryTree(T element) {
        this.count = 1;
        this.root = new BinaryTreeNode<T>(element);
    }

    /**
     * @param targetElement
     * @param next
     * @return
     */
    private BinaryTreeNode<T> findAgain(T targetElement, BinaryTreeNode<T> next) {
        if (next == null) {
            return null;
        }
        if (next.getElement().equals(targetElement)) {
            return next;
        }
        BinaryTreeNode<T> temp = findAgain(targetElement, next.getLeft());
        if (temp == null) {
            temp = findAgain(targetElement, next.getRigth());
        }
        return temp;
    }

    /**
     * @return
     */
    @Override
    public T getRoot() {
        return (T) head;
    }

    /**
     * @return
     */
    @Override
    public boolean isEmpty() {
        if (this.count == 0) {
            return true;
        }
        return false;
    }

    /**
     * @return
     */
    @Override
    public int size() {
        return count;
    }

    /**
     * @param element
     * @return
     * @throws ElementNotFoundException
     */
    @Override
    public boolean countains(T element) throws ElementNotFoundException {
        if (find(element) != null) {
            return true;
        }
        return false;
    }

    /**
     * @param targetElement
     * @return
     * @throws ElementNotFoundException
     */
    @Override
    public T find(T targetElement) throws ElementNotFoundException {
        BinaryTreeNode<T> current = findAgain(targetElement, root);
        if (current == null) {
            throw new ElementNotFoundException("arvore binária");
        }
        return current.getElement();
    }

    /**
     * @return
     */
    @Override
    public String tooString() {
        String treePre = "", treeIn = "", treePost = "", treeLevel = "";
        System.out.println("Travessia preOrder");

        return " Travessia PreOrder " + treePre + "\n Travessia PostOrder " +
                treePost + "\n Travessia InOrder " + treeIn + "\n Travessia Level Order" + treeLevel + "\n";
    }

    /**
     * @return
     */
    @Override
    public Iterator<T> iteratorInOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>(DEFAULT_CAPACITY);
        inOrder(root, tempList);
        return tempList.iterator();
    }

    protected void inOrder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
        if (node == null) {
            inOrder(node.getLeft(), tempList);
            tempList.addToRear(node.getElement());
            inOrder(node.getRigth(), tempList);
        }
    }

    /**
     * @return
     */
    @Override
    public Iterator<T> iteratorPreOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>(DEFAULT_CAPACITY);
        preOrder(root, tempList);
        return tempList.iterator();
    }

    /**
     * @param node
     * @param tempList
     */
    protected void preOrder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
        tempList.addToRear(node.getElement());
        preOrder(node.getLeft(), tempList);
        preOrder(node.getRigth(), tempList);
    }

    /**
     * @return
     */
    @Override
    public Iterator<T> iteratorPostOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>(DEFAULT_CAPACITY);
        postOrder(root, tempList);
        return tempList.iterator();
    }

    /**
     * @param node
     * @param tempList
     */
    protected void postOrder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
        preOrder(node.getLeft(), tempList);
        preOrder(node.getRigth(), tempList);
        tempList.addToRear(node.getElement());
    }

    /**
     * @return
     */
    @Override
    public Iterator<T> iteratorlevelOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>(DEFAULT_CAPACITY);
        levelOrder(root, tempList);
        return tempList.iterator();
    }

    /**
     * @param node
     * @param tempList
     */
    protected void levelOrder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
        preOrder(node.getLeft(), tempList);
        tempList.addToRear(node.getElement());
        preOrder(node.getRigth(), tempList);
        tempList.addToRear(node.getElement());
    }

    public void setRoot(BinaryTreeNode<T> root) {
        this.root = root;
    }


}
