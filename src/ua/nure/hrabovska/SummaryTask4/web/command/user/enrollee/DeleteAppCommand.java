package ua.nure.hrabovska.SummaryTask4.web.command.user.enrollee;

import org.apache.log4j.Logger;
import ua.nure.hrabovska.SummaryTask4.database.dao.ApplicationDAO;
import ua.nure.hrabovska.SummaryTask4.database.entity.Enrollee;
import ua.nure.hrabovska.SummaryTask4.exception.DBException;
import ua.nure.hrabovska.SummaryTask4.exception.Message;
import ua.nure.hrabovska.SummaryTask4.web.RequestProperty;
import ua.nure.hrabovska.SummaryTask4.web.command.Command;
import ua.nure.hrabovska.SummaryTask4.web.command.PageData;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;

/**
 * Delete application command
 *
 * @author Y. Hrabovska
 */
public class DeleteAppCommand extends Command {

    private static final Logger LOG = Logger.getLogger(DeleteAppCommand.class);

    @Override
    public PageData execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException, DBException {
        LOG.debug("Commands starts");

        HttpSession session = request.getSession();

        Enrollee enrollee = (Enrollee) session.getAttribute("enrollee");

        String[] cathedraIds = request.getParameterValues("cathedraId");
        LOG.trace("Set the request parameter: cathedraIds --> " + Arrays.toString(cathedraIds));

        if (cathedraIds != null) {
            ApplicationDAO applicationDAO = new ApplicationDAO();
            Long[] cathedraIdLong = new Long[cathedraIds.length];
            for (int i = 0; i < cathedraIds.length; i++) {
                cathedraIdLong[i] = Long.parseLong(cathedraIds[i]);
            }

            if (applicationDAO.deleteApp(cathedraIdLong, enrollee.getId())) {
                LOG.trace("All chosen app were deleted");
                LOG.debug("Commands finished");
                return new SubmittedAppCommand().execute(request, response);
            }
        }
        LOG.debug("Commands finished");
        request.setAttribute(RequestProperty.ERROR, Message.NOTHING_TO_DELETE);
        return new SubmittedAppCommand().execute(request, response);
    }
}
