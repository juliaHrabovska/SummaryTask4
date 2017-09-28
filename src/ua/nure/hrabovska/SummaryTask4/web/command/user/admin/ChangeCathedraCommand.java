package ua.nure.hrabovska.SummaryTask4.web.command.user.admin;

import org.apache.log4j.Logger;
import ua.nure.hrabovska.SummaryTask4.database.dao.CathedraDAO;
import ua.nure.hrabovska.SummaryTask4.exception.DBException;
import ua.nure.hrabovska.SummaryTask4.exception.Message;
import ua.nure.hrabovska.SummaryTask4.web.RequestProperty;
import ua.nure.hrabovska.SummaryTask4.web.command.Command;
import ua.nure.hrabovska.SummaryTask4.web.command.PageData;
import ua.nure.hrabovska.SummaryTask4.web.command.filingApplication.ShowUniversityPageCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;


/**
 * Change cathedra command
 *
 * @author Y. Hrabovska
 */
public class ChangeCathedraCommand extends Command {
    private static final Logger LOG = Logger.getLogger(ChangeCathedraCommand.class);

    @Override
    public PageData execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, DBException, ParseException {

        LOG.debug("Command starts");

        String cathedraName = request.getParameter("cathedraName");
        int budget = Integer.parseInt(request.getParameter("budget"));
        int contract = Integer.parseInt(request.getParameter("contract"));
        long cathedra_id = Long.parseLong(request.getParameter(RequestProperty.CATHEDRA_ID));

        LOG.trace("Request parameter cathedraName: " + cathedraName);
        LOG.trace("Request parameter budget: " + budget);
        LOG.trace("Request parameter contract: " + contract);
        LOG.trace("Request parameter cathedra_id: " + cathedra_id);

        if (!(budget >= 0) && !(contract >= 0)) {
            request.setAttribute(RequestProperty.ERROR, Message.NOT_VALID_BUDGET_CONTRACT_FIELD);
        } else {
            CathedraDAO cathedraDAO = new CathedraDAO();

            if (cathedraDAO.changeCathedra(cathedraName, budget, contract, cathedra_id)) {
                request.setAttribute(RequestProperty.ERROR, Message.All_OK);

                request.setAttribute(RequestProperty.CATHEDRA_ID, cathedra_id);

                LOG.debug("Command is completed successfully");
                return new PageData("/controller?command=universityPage", false);
            }

        }
        LOG.debug("Command is completed with error");
        request.setAttribute(RequestProperty.ERROR, Message.CANNOT_UPDATE_CATHEDRA);
        return new PageData("/controller?command=universityPage", false);

    }
}
