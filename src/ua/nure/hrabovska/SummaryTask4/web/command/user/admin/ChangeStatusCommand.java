package ua.nure.hrabovska.SummaryTask4.web.command.user.admin;

import org.apache.log4j.Logger;
import ua.nure.hrabovska.SummaryTask4.database.bean.SubmittedAppBean;
import ua.nure.hrabovska.SummaryTask4.database.dao.ApplicationDAO;
import ua.nure.hrabovska.SummaryTask4.database.entity.Application;
import ua.nure.hrabovska.SummaryTask4.exception.DBException;
import ua.nure.hrabovska.SummaryTask4.exception.Message;
import ua.nure.hrabovska.SummaryTask4.util.MailSender;
import ua.nure.hrabovska.SummaryTask4.web.Path;
import ua.nure.hrabovska.SummaryTask4.web.RequestProperty;
import ua.nure.hrabovska.SummaryTask4.web.command.Command;
import ua.nure.hrabovska.SummaryTask4.web.command.PageData;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ChangeStatusCommand extends Command {
    private static final Logger LOG = Logger.getLogger(ChangeStatusCommand.class);

    @Override
    public PageData execute(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException, DBException {
        LOG.debug("Commands starts");

        int newStatus = Integer.parseInt(request.getParameter("status"));
        long enrolleeId = Long.parseLong(request.getParameter("enrolleeId"));
        long cathedraId = Long.parseLong(request.getParameter("cathedraId"));

        LOG.trace("Request parameter newStatus: " + newStatus);
        LOG.trace("Request parameter enrolleeId: " + enrolleeId);
        LOG.trace("Request parameter cathedraId: " + cathedraId);

        ApplicationDAO applicationDAO = new ApplicationDAO();
        Application application = applicationDAO.getByEnrolleeCathedraIds(enrolleeId, cathedraId);
        LOG.trace("Found in DB: application --> " + application);
        application.setStatus_id(newStatus);

        if (!applicationDAO.updateApplication(application)) {
            request.setAttribute(RequestProperty.ERROR, Message.CANNOT_UPDATE_APPLICATION);
        }

        List<SubmittedAppBean> submittedAppBeans = applicationDAO.getSubmittedAppBeanByEnrollee_id(enrolleeId);

        LOG.trace("Get applicationBeans from DB: " + submittedAppBeans);

        request.setAttribute(RequestProperty.SUBMITTED_APP, submittedAppBeans);
        LOG.trace("Set the request attribute: applicationBeans --> " + submittedAppBeans);

        request.setAttribute(RequestProperty.ENROLLEE_ID, enrolleeId);
        LOG.trace("Set the request attribute: enrolleeId --> " + enrolleeId);

        if (newStatus == 3) {
            LOG.trace("Send message to user email: " + "julia070796@i.ua");
            if (!MailSender.sendMessage("julia070796@i.ua")) {
                request.setAttribute(RequestProperty.ERROR, Message.MAIL_SEND);
            } else {
                request.setAttribute(RequestProperty.ERROR, Message.MAIL_NOT_SENT);
            }
        }

        LOG.debug("Commands finished");
        return new PageData(Path.USER_PAGE, true);
    }
}
