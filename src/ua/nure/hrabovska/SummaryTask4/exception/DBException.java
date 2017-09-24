package ua.nure.hrabovska.SummaryTask4.exception;

/**
 * An exception that provides information on a database access error.
 *
 * @author Y. Hrabovska
 */
public class DBException extends AppException {

    private static final long serialVersionUID = 8486886523003078504L;

    public DBException(){
        super();
    }

    public DBException(String message, Throwable cause) {
        super(message, cause);
    }

}
