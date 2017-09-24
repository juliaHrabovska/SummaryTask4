package ua.nure.hrabovska.SummaryTask4.web.command.user.enrollee;

import org.apache.log4j.Logger;
import ua.nure.hrabovska.SummaryTask4.database.bean.SubmittedAppBean;
import ua.nure.hrabovska.SummaryTask4.database.dao.ApplicationDAO;
import ua.nure.hrabovska.SummaryTask4.database.entity.Enrollee;
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

public class SubmittedAppCommand extends Command {
    private static final Logger LOG = Logger.getLogger(SubmittedAppCommand.class);

    @Override
    public PageData execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException, DBException {
        LOG.debug("Commands starts");

        HttpSession session = request.getSession();

        Enrollee enrollee = (Enrollee) session.getAttribute("enrollee");
        LOG.trace("Get attribute from session enrollee: " + enrollee);

        ApplicationDAO applicationDAO = new ApplicationDAO();
        List<SubmittedAppBean> submittedAppBeans = applicationDAO.getSubmittedAppBeanByEnrollee_id(enrollee.getId());

        LOG.trace("Get applicationBeans from DB: " + submittedAppBeans);

        request.setAttribute(RequestProperty.SUBMITTED_APP, submittedAppBeans);
        LOG.trace("Set the request attribute: applicationBeans --> " + submittedAppBeans);

        request.setAttribute(RequestProperty.ENROLLEE_ID, enrollee.getId());
        LOG.trace("Set the request attribute: enrollee_id --> " + enrollee.getId());

        LOG.debug("Commands finished");
        return new PageData(Path.SUBMITTED_APP, true);
    }
}
