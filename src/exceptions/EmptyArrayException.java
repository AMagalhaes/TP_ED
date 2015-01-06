package exceptions;

/**
 * Work done by:
 * Antonio Magalhaes
 * Pedro Fernandes
 */

public class EmptyArrayException extends Exception {

    /**
     * Creates a new instance of <code>EmptyArrayException</code> without
     * detail message.
     */
    public EmptyArrayException() {
    }

    /**
     * Constructs an instance of <code>EmptyArrayException</code> with the
     * specified detail message.
     *
     * @param message the detail message.
     */
    EmptyArrayException(String message) {
        super(message);
    }
}
