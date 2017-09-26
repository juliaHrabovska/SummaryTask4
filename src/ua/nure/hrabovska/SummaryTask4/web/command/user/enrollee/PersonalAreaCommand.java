package ua.nure.hrabovska.SummaryTask4.web.command.user.enrollee;

import org.apache.log4j.Logger;
import ua.nure.hrabovska.SummaryTask4.database.dao.ResultDAO;
import ua.nure.hrabovska.SummaryTask4.database.entity.Enrollee;
import ua.nure.hrabovska.SummaryTask4.exception.DBException;
import ua.nure.hrabovska.SummaryTask4.web.Path;
import ua.nure.hrabovska.SummaryTask4.web.command.Command;
import ua.nure.hrabovska.SummaryTask4.web.command.PageData;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

public class PersonalAreaCommand extends Command {

    private static final Logger LOG = Logger.getLogger(PersonalAreaCommand.class);

    @Override
    public PageData execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException, DBException {
        LOG.debug("Command start");

        HttpSession session = request.getSession();
        Enrollee enrollee = (Enrollee) session.getAttribute("enrollee");
        LOG.debug("Get attribute enrollee from session: " + enrollee);

        Map<String, Integer> results = new ResultDAO().getListExamResultsByErollee_id(enrollee.getId());
        LOG.debug("Get results from DB: " + results);

        request.setAttribute("results", results);
        LOG.debug("Set attribute enrollee to request: " + results);

        LOG.debug("Command completed successfully");
        return new PageData(Path.PERSONAL_AREA_CLIENT, true);
    }
}
