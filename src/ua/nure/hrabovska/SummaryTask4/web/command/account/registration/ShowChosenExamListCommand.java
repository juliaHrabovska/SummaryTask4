package ua.nure.hrabovska.SummaryTask4.web.command.account.registration;

import org.apache.log4j.Logger;
import ua.nure.hrabovska.SummaryTask4.database.dao.ExamDAO;
import ua.nure.hrabovska.SummaryTask4.database.entity.Exam;
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
import java.util.*;

/**
 * Show chosen exams command
 *
 * @author Y. Hrabovska
 *
 */
public class ShowChosenExamListCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ShowChosenExamListCommand.class);

    @Override
    public PageData execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException, DBException {
        LOG.debug("Commands starts");

//        Map<String, String[]> parameters = request.getParameterMap();
//        for(Map.Entry<String, String[]> pair : parameters.entrySet())
//        {
//            System.out.println(pair.getKey());
//            String[] value = pair.getValue();
//            for (String aValue : value) {
//                System.out.println(aValue);
//            }
//        }
//        System.out.println(parameters);
        String[] exams = request.getParameterValues("examID");
        LOG.trace("Get parameters examID: " + Arrays.toString(exams));

        //////////////////if (exams.length == 4) {
            ExamDAO examDAO = new ExamDAO();
            List<Exam> examList = examDAO.getListByIds(exams);
            request.setAttribute(RequestProperty.EXAM_LIST, examList);
            LOG.trace("Set the request attribute: examList --> " + examList);

            LOG.debug("Commands finished");
            return new PageData(Path.CHOSEN_EXAM_LIST, true);
        /*} else {
            LOG.debug("Command is completed with error");
            request.setAttribute(RequestProperty.ERROR, Message.LENGTH_OF_CHOSEN_EXAMS_MUST_BE_4);
            return new PageData(Path.EXAM_LIST, false);
        }*/
//        HttpSession session = request.getSession();
//        System.out.println();request.getParameterNames();
//        List<String> parameterNames = new ArrayList<String>();
//        Enumeration enumeration = request.getParameterNames();
//        while (enumeration.hasMoreElements()) {
//            String parameterName = (String) enumeration.nextElement();
//            parameterNames.add(parameterName);
//        }
//
//        System.out.println(parameterNames);

//        String[] examId = request.getParameterValues("examID");
//        LOG.trace("Set the request parameter: examID --> " + Arrays.toString(examId));
    }
}
