package ua.nure.hrabovska.SummaryTask4.web.command.filingApplication;

import org.apache.log4j.Logger;
import ua.nure.hrabovska.SummaryTask4.database.dao.UniversityDAO;
import ua.nure.hrabovska.SummaryTask4.database.entity.University;
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

/**
 * Show universities command
 *
 * @author Y. Hrabovska
 */
public class ShowUniversityCommand extends Command {
    private static final Logger LOG = Logger.getLogger(ShowUniversityCommand.class);

    @Override
    public PageData execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException, DBException {
        LOG.debug("Commands starts");

        int place_id = Integer.parseInt(request.getParameter("place"));

        LOG.trace("Get parameter place_id: " + place_id);

        UniversityDAO universityDAO = new UniversityDAO();
        List<University> universityList = universityDAO.getByPlace_id(place_id);

        LOG.trace("Get universityList from DB: " + universityList);

        request.setAttribute(RequestProperty.UNIVERSITY_LIST, universityList);
        LOG.trace("Set the request attribute: universityList --> " + universityList);

        LOG.debug("Commands finished");
        return new PageData(Path.UNIVERSITY_LIST, true);
    }
}
