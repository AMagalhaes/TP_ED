package pt.ipp.estgf.facegraph.Heap;

/**
 * Created by antoniomagalhaes on 14/12/14.
 */
public class HeapNode<T> extends BinaryTreeNode<T> {
    protected HeapNode<T> parent;

    public HeapNode(T element) {
        super(element);
        this.parent = null;
    }

}
