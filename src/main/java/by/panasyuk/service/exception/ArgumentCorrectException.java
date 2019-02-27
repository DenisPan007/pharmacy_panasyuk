package by.panasyuk.service.exception;

public class ArgumentCorrectException extends  Exception {
    public ArgumentCorrectException() {
        super();
    }

    public ArgumentCorrectException(String message) {
        super(message);
    }

    public ArgumentCorrectException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArgumentCorrectException(Throwable cause) {
        super(cause);
    }
}
