package ua.nure.hrabovska.SummaryTask4.web.command.filingApplication;

import org.apache.log4j.Logger;
import ua.nure.hrabovska.SummaryTask4.database.dao.ApplicationDAO;
import ua.nure.hrabovska.SummaryTask4.database.dao.ResultDAO;
import ua.nure.hrabovska.SummaryTask4.database.entity.Enrollee;
import ua.nure.hrabovska.SummaryTask4.exception.DBException;
import ua.nure.hrabovska.SummaryTask4.exception.Message;
import ua.nure.hrabovska.SummaryTask4.web.Path;
import ua.nure.hrabovska.SummaryTask4.web.RequestProperty;
import ua.nure.hrabovska.SummaryTask4.web.command.Command;
import ua.nure.hrabovska.SummaryTask4.web.command.PageData;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Apply command
 *
 * @author Y. Hrabovska
 */
public class ApplyCommand extends Command {
    private static final Logger LOG = Logger.getLogger(ApplyCommand.class);

    @Override
    public PageData execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException, DBException {
        LOG.debug("Commands starts");

        HttpSession session = request.getSession();
        Enrollee enrollee = (Enrollee) session.getAttribute("enrollee");
        LOG.trace("Get enrollee from session: " + enrollee);

        long cathedra_id = Long.parseLong(request.getParameter("cathedra_id"));
        LOG.trace("Get parameter cathedra_id: " + cathedra_id);

        ApplicationDAO applicationDAO = new ApplicationDAO();
        if (applicationDAO.getByEnrolleeCathedraIds(enrollee.getId(), cathedra_id) == null) {
            request.setAttribute(RequestProperty.ERROR, Message.APPLICATION_SUBMITTED);
        } else {
            ResultDAO resultDAO = new ResultDAO();
            if (enrollee.getLevel_id() == 1) {
                if (resultDAO.getCountAppropriateExam(cathedra_id, enrollee.getId()) >= 3) {
                    if (createApp(enrollee, cathedra_id)) {
                        LOG.debug("Commands finished successfully");
                        return new PageData("/controller?command=submitted_applications", false);
                    }
                } else {
                    request.setAttribute(RequestProperty.ERROR, Message.INSUFFICIENT_NUMBER_RELEVANT_EXAMINATION);
                }
            } else if (enrollee.getLevel_id() == 2) {
                if (resultDAO.getCountAppropriateExam(cathedra_id, enrollee.getId()) >= 2) {
                    if (createApp(enrollee, cathedra_id)) {
                        LOG.debug("Commands finished successfully");
                        return new PageData("/controller?command=submitted_applications", false);
                    }
                } else {
                    request.setAttribute(RequestProperty.ERROR, Message.INSUFFICIENT_NUMBER_RELEVANT_EXAMINATION);
                }
            }
        }
        LOG.debug("Commands finished with error");
        request.setAttribute(RequestProperty.CATHEDRA_ID, cathedra_id);
        return new PageData("/controller?command=contest", false);

    }

    private static boolean createApp(Enrollee enrollee, long cathedra_id) throws DBException {
        List<Integer> results = new ResultDAO().getListResultsByErollee_id(enrollee.getId());
        int score = enrollee.getCertificate_score();
        for (Integer result : results) {
            score += result;
        }
        ApplicationDAO applicationDAO = new ApplicationDAO();
        return applicationDAO.createApp(cathedra_id, enrollee.getId(), score);
    }
}
