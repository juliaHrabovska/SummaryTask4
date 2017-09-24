package ua.nure.hrabovska.SummaryTask4.web.command.user.enrollee;

import org.apache.log4j.Logger;
import ua.nure.hrabovska.SummaryTask4.database.dao.ApplicationDAO;
import ua.nure.hrabovska.SummaryTask4.database.entity.Enrollee;
import ua.nure.hrabovska.SummaryTask4.exception.DBException;
import ua.nure.hrabovska.SummaryTask4.web.command.Command;
import ua.nure.hrabovska.SummaryTask4.web.command.PageData;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;

public class DeleteAppCommand extends Command {

    private static final Logger LOG = Logger.getLogger(DeleteAppCommand.class);

    @Override
    public PageData execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException, DBException {
        LOG.debug("Commands starts");

        HttpSession session = request.getSession();

        Enrollee enrollee = (Enrollee) session.getAttribute("enrollee");

        String[] cathedraIds = request.getParameterValues("cathedraId");
        LOG.trace("Set the request parameter: cathedraIds --> " + Arrays.toString(cathedraIds));

        ApplicationDAO applicationDAO = new ApplicationDAO();

        PageData pd = new SubmittedAppCommand().execute(request, response);
        Long[] cathedra_id_long = new Long[cathedraIds.length];
        for (int i = 0; i < cathedraIds.length; i++) {
            cathedra_id_long[i] = Long.parseLong(cathedraIds[i]);
        }

        if (applicationDAO.deleteApp(cathedra_id_long, enrollee.getId())) {
                LOG.trace("All chosen books were deleted");
                LOG.debug("Commands finished");
                return pd;
            }

            LOG.debug("Command is completed with error");
            return pd;
        }
    }
