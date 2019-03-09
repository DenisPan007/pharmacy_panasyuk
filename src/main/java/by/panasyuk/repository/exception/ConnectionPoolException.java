package by.panasyuk.repository.exception;

/**
 * Connection Pool Exception
 */
public class ConnectionPoolException extends Exception {

    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }
    //provide your code here


    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }

    protected ConnectionPoolException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
