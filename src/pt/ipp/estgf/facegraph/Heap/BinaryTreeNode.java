 package pt.ipp.estgf.facegraph.Heap;

/**
 * Created by antoniomagalhaes on 27/11/14.
 */
public class BinaryTreeNode<T> {
    private T Element;
    private BinaryTreeNode rigth;
    private BinaryTreeNode left;

    /**
     * @param element
     */
    public BinaryTreeNode(T element) {
        Element = element;
        this.rigth = null;
        this.left = null;
    }

    /**
     * @return
     */
    public int numChildren() {
        int children = 0;
        if (left != null) {
            children = 1 + left.numChildren();
        }
        if (rigth != null) {
            children = 1 + rigth.numChildren();
        }
        return children;
    }

    /**
     * @return
     */
    public T getElement() {
        return Element;
    }

    /**
     * @param element
     */
    public void setElement(T element) {
        Element = element;
    }

    /**
     * @return
     */
    public BinaryTreeNode getRigth() {
        return rigth;
    }

    /**
     * @param rigth
     */
    public void setRight(BinaryTreeNode rigth) {
        this.rigth = rigth;
    }

    /**
     * @return
     */
    public BinaryTreeNode getLeft() {
        return left;
    }

    /**
     * @param left
     */
    public void setLeft(BinaryTreeNode left) {
        this.left = left;
    }

    @Override
    public String toString() {
        return "BinaryTreeNode{" +
                "Element=" + Element +
                ", rigth=" + rigth +
                ", left=" + left +
                '}';
    }
}
