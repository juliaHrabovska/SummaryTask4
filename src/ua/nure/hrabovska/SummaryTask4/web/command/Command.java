package ua.nure.hrabovska.SummaryTask4.web.command;

import ua.nure.hrabovska.SummaryTask4.exception.DBException;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Main interface for the Command pattern implementation.
 *
 * @author Y. Hrabovska
 */
public abstract class Command implements Serializable {
    private static final long serialVersionUID = 8879403039606311780L;

    /**
     * Execution method for command.
     *
     * @return Address to go once the command is executed.
     */
    public abstract PageData execute(HttpServletRequest request,
                                     HttpServletResponse response) throws IOException, ServletException,/* DBException, */ParseException, DBException;

    @Override
    public final String toString() {
        return getClass().getSimpleName();
    }
}