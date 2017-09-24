package ua.nure.hrabovska.SummaryTask4.web.command.link;

import ua.nure.hrabovska.SummaryTask4.exception.DBException;
import ua.nure.hrabovska.SummaryTask4.web.command.Command;
import ua.nure.hrabovska.SummaryTask4.web.command.PageData;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class which refers to pages
 *
 * @author Y. Hrabovska
 */
public class LinkPageCommand extends Command {

    private String path;

    public LinkPageCommand(String path) {
        this.path = path;
    }

    @Override
    public PageData execute(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException, DBException {
        return new PageData(path, true);
    }
}