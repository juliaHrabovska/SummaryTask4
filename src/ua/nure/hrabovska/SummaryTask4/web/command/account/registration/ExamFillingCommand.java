package ua.nure.hrabovska.SummaryTask4.web.command.account.registration;

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
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * Exam filling command
 *
 * @author Y. Hrabovska
 *
 */
public class ExamFillingCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ExamFillingCommand.class);

    @Override
    public PageData execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException, DBException {

        Map<String, String[]> parameters = request.getParameterMap();
        Map<Long, Integer> exRes = new HashMap<>();

        for (Map.Entry<String, String[]> pair : parameters.entrySet()) {
            if (pair.getKey().startsWith("exam_")) {
                exRes.put(Long.parseLong(pair.getKey().substring(5)), Integer.parseInt(pair.getValue()[0]));
            }
        }

        ResultDAO resultDAO = new ResultDAO();
        resultDAO.insertResult(exRes, (Enrollee) request.getSession().getAttribute("enrollee"));

        Map<String, Integer> results = new ResultDAO().getListExamResultsByErollee_id(
                ((Enrollee) request.getSession().getAttribute("enrollee")).getId());
        LOG.debug("Get results from DB: " + results);
        request.setAttribute("results", results);
        LOG.debug("Command completed successfully");

        return new PageData(Path.PERSONAL_AREA_CLIENT, true);
    }
}
