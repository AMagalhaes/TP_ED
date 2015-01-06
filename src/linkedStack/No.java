package linkedStack;

/**
 * Trabalho realizado por:
 * Antonio Magalhaes
 * Pedro Fernandes
 */

public class No<T> {
    private No proximo;
    private T elemento;

    /**
     * criacao do no com o elemento
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
