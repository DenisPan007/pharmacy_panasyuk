package by.panasyuk.repository.exception;

/**
 * Dao Exception
 */
public class RepositoryException extends Exception {

    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepositoryException(String message) {
        super(message);
    }

    public RepositoryException(Throwable cause) {
        super(cause);
    }
    //provide your code here
}
