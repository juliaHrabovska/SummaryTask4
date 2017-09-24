package ua.nure.hrabovska.SummaryTask4.web.command.user.admin;

import org.apache.log4j.Logger;
import ua.nure.hrabovska.SummaryTask4.database.bean.EnrolleeBean;
import ua.nure.hrabovska.SummaryTask4.database.dao.EnrolleeDAO;
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

public class ShowUsersCommand extends Command {
    private static final Logger LOG = Logger.getLogger(ShowUsersCommand.class);

    @Override
    public PageData execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException, DBException {
        LOG.debug("Commands starts");

        EnrolleeDAO enrolleeDAO = new EnrolleeDAO();
        List<EnrolleeBean> enrolleeList = enrolleeDAO.getAllEnrollees();
        request.setAttribute(RequestProperty.ENROLLEE_LIST, enrolleeList);
        LOG.trace("Set the request attribute: enrolleeList --> " + enrolleeList);

        LOG.debug("Commands finished");
        return new PageData(Path.ENROLLEE_LIST, true);
    }
}
