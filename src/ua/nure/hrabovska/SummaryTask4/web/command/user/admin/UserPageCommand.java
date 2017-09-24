package ua.nure.hrabovska.SummaryTask4.web.command.user.admin;

import org.apache.log4j.Logger;
import ua.nure.hrabovska.SummaryTask4.database.bean.SubmittedAppBean;
import ua.nure.hrabovska.SummaryTask4.database.dao.ApplicationDAO;
import ua.nure.hrabovska.SummaryTask4.exception.DBException;
import ua.nure.hrabovska.SummaryTask4.web.Path;
import ua.nure.hrabovska.SummaryTask4.web.RequestProperty;
import ua.nure.hrabovska.SummaryTask4.web.command.Command;
import ua.nure.hrabovska.SummaryTask4.web.command.PageData;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class UserPageCommand extends Command {
    private static final Logger LOG = Logger.getLogger(UserPageCommand.class);

    @Override
    public PageData execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException, DBException {
        LOG.debug("Commands starts");
        long enrolleeId = Long.parseLong(request.getParameter("enrolleeId"));
        LOG.trace("Request parameter enrolleeId: " + enrolleeId);

        ApplicationDAO applicationDAO = new ApplicationDAO();
        List<SubmittedAppBean> submittedAppBeans = applicationDAO.getSubmittedAppBeanByEnrollee_id(enrolleeId);

        LOG.trace("Get applicationBeans from DB: " + submittedAppBeans);

        request.setAttribute(RequestProperty.SUBMITTED_APP, submittedAppBeans);
        LOG.trace("Set the request attribute: applicationBeans --> " + submittedAppBeans);

        request.setAttribute(RequestProperty.ENROLLEE_ID, enrolleeId);
        LOG.trace("Set the request attribute: enrolleeId --> " + enrolleeId);

        LOG.debug("Commands finished");
        return new PageData(Path.USER_PAGE, true);
    }
}
