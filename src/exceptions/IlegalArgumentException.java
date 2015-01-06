package exceptions;

/**
 * Work done by:
 * Antonio Magalhaes
 * Pedro Fernandes
 */

public class IlegalArgumentException extends Exception {
    /**
     * Creates a new instance of <code>IlegalArgumentException</code> without
     * detail message.
     */
    public IlegalArgumentException() {
    }

    /**
     * Constructs an instance of <code>IlegalArgumentException</code> with the
     * specified detail message.
     *
     * @param message the detail message.
     */
    public IlegalArgumentException(String message) {
        super(message);
    }
}
