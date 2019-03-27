package by.panasyuk.service.exception;

/**
 * Service Exception
 */
public class ServiceException extends Exception {
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException( Throwable cause) {
        super(cause);
    }

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }
}
