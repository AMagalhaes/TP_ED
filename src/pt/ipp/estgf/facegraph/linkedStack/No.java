package pt.ipp.estgf.facegraph.linkedStack;

/**
 * Work done by:
 * Antonio Magalhaes
 * Pedro Fernandes
 */

public class No<T> {
    private No proximo;
    private T elemento;

    /**
     * creation of the node with the element
     *
     * @param elemento
     */
    public No(T elemento) {
        this.elemento = elemento;
        this.proximo = null;
    }

    public No<T> getProximo() {

        return proximo;
    }

    public void setProximo(No<T> proximoNo) {
        this.proximo = proximoNo;
    }

    public T getElemento() {

        return elemento;
    }

    public void setElemento(T elemento) {

        this.elemento = elemento;
    }

    @Override
    public String toString() {

        return "  " + proximo + ", elemento=" + elemento;
    }
}
