package ua.nure.hrabovska.SummaryTask4.web.command.filingApplication;

import org.apache.log4j.Logger;
import ua.nure.hrabovska.SummaryTask4.database.dao.CathedraDAO;
import ua.nure.hrabovska.SummaryTask4.database.bean.CathedraBean;
import ua.nure.hrabovska.SummaryTask4.database.entity.Account;
import ua.nure.hrabovska.SummaryTask4.enums.Role;
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
 * Show university page command
 *
 * @author Y. Hrabovska
 */
public class ShowUniversityPageCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ShowUniversityPageCommand.class);

    @Override
    public PageData execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException, DBException {
        LOG.debug("Commands starts");

        HttpSession session = request.getSession();

        Object university_id = session.getAttribute("university_id");
        LOG.trace("Get attribute from session university_id: " + university_id);

        if (university_id == null || university_id.equals("")) {
            university_id = Long.parseLong(request.getParameter("university_id"));
            LOG.trace("Get parameter university_id: " + university_id);
            session.setAttribute("university_id", university_id);
            LOG.trace("Set session attribute university_id: university_id --> " + university_id);
        }

        CathedraDAO cathedraDAO = new CathedraDAO();
        List<CathedraBean> cathedraList = cathedraDAO.getCathedraBeanByUniver_id((long) university_id);
        LOG.trace("Get cathedraList from DB: " + cathedraList);

        request.setAttribute(RequestProperty.CATHEDRA_LIST, cathedraList);
        LOG.trace("Set the request attribute: cathedraList --> " + cathedraList);

        session.setAttribute("cathedraList", cathedraList);
        LOG.trace("Set session attribute cathedraList: cathedraList --> " + cathedraList);

        Account account = (Account) session.getAttribute("account");

        if (account.getRole_id() == Role.ADMIN) {
            return new PageData(Path.CATHEDRA_INFO, true);
        }

        LOG.debug("Commands finished");
        return new PageData(Path.UNIVERSITY_PAGE, true);
    }
}
