package ua.nure.hrabovska.SummaryTask4.web.command.account;

import org.apache.log4j.Logger;
import ua.nure.hrabovska.SummaryTask4.database.dao.ExamDAO;
import ua.nure.hrabovska.SummaryTask4.database.entity.Enrollee;
import ua.nure.hrabovska.SummaryTask4.database.entity.Exam;
import ua.nure.hrabovska.SummaryTask4.exception.DBException;
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
 * Show exams command
 *
 * @author Y. Hrabovska
 *
 */
public class ShowExamListCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ShowExamListCommand.class);

    @Override
    public PageData execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException, DBException {
        LOG.debug("Command starts");

        HttpSession session = request.getSession();

        Enrollee enrollee = (Enrollee) session.getAttribute("enrollee");
        LOG.trace("Get parameter enrollee: " + enrollee);

        ExamDAO examDAO = new ExamDAO();
        List<Exam> examList = examDAO.getByLevel_id(enrollee.getLevel_id());
        request.setAttribute(RequestProperty.EXAM_LIST, examList);
        LOG.trace("Set the request attribute: examList --> " + examList);

        LOG.debug("Commands finished");
        return new PageData(Path.EXAM_LIST, true);
    }
}
