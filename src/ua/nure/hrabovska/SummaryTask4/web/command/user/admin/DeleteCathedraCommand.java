package ua.nure.hrabovska.SummaryTask4.web.command.user.admin;

import org.apache.log4j.Logger;
import ua.nure.hrabovska.SummaryTask4.database.dao.CathedraDAO;
import ua.nure.hrabovska.SummaryTask4.exception.DBException;
import ua.nure.hrabovska.SummaryTask4.web.Path;
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

        String[] cathedraIds = request.getParameterValues("cathedra_id");
        LOG.trace("Set the request parameter: cathedraIds --> " + Arrays.toString(cathedraIds));

        CathedraDAO cathedraDAO = new CathedraDAO();

        if (cathedraDAO.deleteCathdera(cathedraIds)) {
            LOG.debug("Commands finished");
            return new ShowUniversityPageCommand().execute(request, response);
        }
        LOG.debug("Command is completed with error");
        return new ShowUniversityPageCommand().execute(request, response);
    }
}
