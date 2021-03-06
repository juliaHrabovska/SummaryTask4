package ua.nure.hrabovska.SummaryTask4.web.command.filingApplication;

import org.apache.log4j.Logger;
import ua.nure.hrabovska.SummaryTask4.database.dao.PlaceDAO;
import ua.nure.hrabovska.SummaryTask4.database.entity.Place;
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

/**
 * Show places command
 *
 * @author Y. Hrabovska
 */
public class ShowPlaceCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ShowPlaceCommand.class);

    @Override
    public PageData execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException, DBException {
        LOG.debug("Commands starts");

        HttpSession session = request.getSession();
        session.removeAttribute("university_id");
        LOG.trace("Remove attribute from session: university_id");
        session.removeAttribute("cathedraList");
        LOG.trace("Remove attribute from session: cathedraList");

        PlaceDAO placeDAO = new PlaceDAO();
        List<Place> placeList = placeDAO.getAllPlaces();
        request.setAttribute(RequestProperty.PLACE_LIST, placeList);
        LOG.trace("Set the request attribute: placeList --> " + placeList);

        LOG.debug("Commands finished");
        return new PageData(Path.PLACE_LIST, true);
    }
}
