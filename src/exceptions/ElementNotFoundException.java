package exceptions;

/**
 * Work done by:
 * Antonio Magalhaes
 * Pedro Fernandes
 */

public class ElementNotFoundException extends Exception {
    /**
     * Creates a new instance of <code>ElementNotFoundException</code> without
     * detail message.
     */
    public ElementNotFoundException() {
    }

    /**
     * Constructs an instance of <code>ElementNotFoundException</code> with the
     * specified detail message.
     *
     * @param message the detail message.
     */
    public ElementNotFoundException(String message) {

        super(message);
    }


}
