package ua.nure.hrabovska.SummaryTask4.web.command.filingApplication;

import org.apache.log4j.Logger;
import ua.nure.hrabovska.SummaryTask4.database.bean.ApplicationBean;
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

public class ContestCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ContestCommand.class);

    @Override
    public PageData execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException, DBException {
        LOG.debug("Commands starts");

        long cathedra_id = Long.parseLong(request.getParameter("cathedra_id"));
        LOG.trace("Get parameter cathedra_id: " + cathedra_id);

        ApplicationDAO applicationDAO = new ApplicationDAO();
        List<ApplicationBean> applicationBeans = applicationDAO.getApplicationBeanByCathedra_id(cathedra_id);

        LOG.trace("Get applicationBeans from DB: " + applicationBeans);

        for (ApplicationBean application: applicationBeans) {
            application.setExam_results(applicationDAO.getListResults(application.getEnrollee_id(), cathedra_id));
        }

        request.setAttribute(RequestProperty.CATHEDRA_ID, cathedra_id);
        LOG.trace("Set the request attribute: cathedra_id --> " + cathedra_id);
        request.setAttribute(RequestProperty.CONTEST, applicationBeans);
        LOG.trace("Set the request attribute: applicationBeans --> " + applicationBeans);

        LOG.debug("Commands finished");
        return new PageData(Path.CONTEST, true);
    }
}
