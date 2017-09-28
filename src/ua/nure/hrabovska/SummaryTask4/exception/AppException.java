package ua.nure.hrabovska.SummaryTask4.exception;

/**
 * An exception that provides information on an application error.
 *
 * @author Y. Hrabovska
 */
public class AppException extends Exception {

    private static final long serialVersionUID = 8288779062647218916L;

    AppException() {
        super();
    }

    AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(String message) {
        super(message);
    }

}
