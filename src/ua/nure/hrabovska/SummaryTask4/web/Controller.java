package ua.nure.hrabovska.SummaryTask4.web;

import org.apache.log4j.Logger;
import ua.nure.hrabovska.SummaryTask4.exception.Message;
import ua.nure.hrabovska.SummaryTask4.web.command.Command;
import ua.nure.hrabovska.SummaryTask4.web.command.PageData;
import ua.nure.hrabovska.SummaryTask4.web.command.container.CommandContainer;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Main controller servlet
 *
 * @author Y. Hrabovska
 */
@MultipartConfig
public class Controller extends HttpServlet {

    private static final long serialVersionUID = 2423353715955164816L;

    private static final Logger LOG = Logger.getLogger(Controller.class);

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        service(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        service(request, response);
    }

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        LOG.debug("Controller starts");
        String commandName = request.getParameter("command");
        LOG.trace("Get command: " + commandName);
        Command command = CommandContainer.get(commandName);

        PageData pageData = null;
        try {
            pageData = command.execute(request, response);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            request.setAttribute(RequestProperty.ERROR, Message.SERVER_ERROR);
            pageData = new PageData(Path.ERROR_PAGE, true);
        }
        if (pageData.isForward()) {
            LOG.debug("Controller ends, forward: " + pageData.getPath());
            request.getRequestDispatcher(pageData.getPath()).forward(request, response);
        } else {
            LOG.debug("Controller ends, redirect: " + pageData.getPath());
            response.sendRedirect(pageData.getPath());
        }
    }
}
