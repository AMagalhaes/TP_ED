package Interfaces;

/**
 * Created by antoniomagalhaes on 12/12/14.
 */
public interface HeapADT<T> extends  BinaryTreeADT<T> {
    /**
     * Adiciona o elemento á Heap
     *
     * @param obj elemento a adcionar a esta Heap
     */
    public void addElement(T obj);

    /**
     * Remove o elemento com valor minimo nesta Heap
     * Return o elemento com valor mais baixo aHeap
     */
    public T removeMin();

    /**
     *
     * @return a referencia para o elemento cpm o valor mais baixo
     */
    public T findMin();

}
