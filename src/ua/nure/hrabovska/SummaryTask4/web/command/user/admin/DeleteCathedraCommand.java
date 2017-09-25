package ua.nure.hrabovska.SummaryTask4.web.command.user.admin;

import org.apache.log4j.Logger;
import ua.nure.hrabovska.SummaryTask4.database.dao.CathedraDAO;
import ua.nure.hrabovska.SummaryTask4.exception.DBException;
import ua.nure.hrabovska.SummaryTask4.exception.Message;
import ua.nure.hrabovska.SummaryTask4.web.Path;
import ua.nure.hrabovska.SummaryTask4.web.RequestProperty;
import ua.nure.hrabovska.SummaryTask4.web.command.Command;
import ua.nure.hrabovska.SummaryTask4.web.command.PageData;
import ua.nure.hrabovska.SummaryTask4.web.command.filingApplication.ShowUniversityPageCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;

public class DeleteCathedraCommand extends Command {
    private static final Logger LOG = Logger.getLogger(DeleteCathedraCommand.class);

    @Override
    public PageData execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, DBException, ParseException {
        LOG.debug("Commands starts");

        long cathedraId = Long.parseLong(request.getParameter("cathedraIdDel"));
        LOG.trace("Set the request parameter: cathedraIdDel --> " + cathedraId);

        CathedraDAO cathedraDAO = new CathedraDAO();

        if (cathedraDAO.deleteCathdera(cathedraId)) {
            LOG.debug("Commands finished");
            return new PageData("/controller?command=universityPage", false);
        }
        LOG.debug("Command is completed with error");
        request.setAttribute(RequestProperty.ERROR, Message.CANNOT_DELETE_CATHEDRA);
        return new PageData("/controller?command=universityPage", false);
    }
}
