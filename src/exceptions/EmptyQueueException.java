package exceptions;

/**
 * Created by antoniomagalhaes on 31/10/14.
 */
public class EmptyQueueException extends Exception {
    public EmptyQueueException(String msg) {
        super(msg);
}
}