package ua.nure.hrabovska.SummaryTask4.web.command.account;

import org.apache.log4j.Logger;
import ua.nure.hrabovska.SummaryTask4.database.dao.AccountDAO;
import ua.nure.hrabovska.SummaryTask4.database.dao.EnrolleeDAO;
import ua.nure.hrabovska.SummaryTask4.database.entity.Account;
import ua.nure.hrabovska.SummaryTask4.database.entity.Enrollee;
import ua.nure.hrabovska.SummaryTask4.enums.Role;
import ua.nure.hrabovska.SummaryTask4.exception.DBException;
import ua.nure.hrabovska.SummaryTask4.exception.Message;
import ua.nure.hrabovska.SummaryTask4.util.Encryption;
import ua.nure.hrabovska.SummaryTask4.web.Path;
import ua.nure.hrabovska.SummaryTask4.web.RequestProperty;
import ua.nure.hrabovska.SummaryTask4.web.command.Command;
import ua.nure.hrabovska.SummaryTask4.web.command.PageData;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Login command
 *
 * @author Y. Hrabovska
 *
 */
public class LoginCommand extends Command {

    private static final Logger LOG = Logger.getLogger(LoginCommand.class);

    @Override
    public PageData execute(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException, DBException {
        LOG.debug("Command start");

        HttpSession session = request.getSession();

        String email = request.getParameter(RequestProperty.EMAIL);
        String password = request.getParameter(RequestProperty.PASSWORD);

        LOG.trace("Request parameter email: " + email);
        LOG.trace("Request parameter password: " + password);

        AccountDAO accountDAO = new AccountDAO();
        Account account = accountDAO.getByEmail(email);
        LOG.trace("Found in DB: account --> " + account);

        if (account == null || !Encryption.doDecipher(account.getPassword()).equals(password)) {
            request.setAttribute(RequestProperty.ERROR, Message.WRONG_EMAIL_PASSWORD);
        } else {
            Role accountRole = account.getRole_id();
            LOG.trace("accountRole --> " + accountRole);

            session.setAttribute("account", account);
            LOG.trace("Set the session attribute: account --> " + account);

            session.setAttribute("accountRole", accountRole);
            LOG.trace("Set the session attribute: accountRole --> " + accountRole);

            EnrolleeDAO enrolleeDAO = new EnrolleeDAO();
            Enrollee enrollee = enrolleeDAO.getByAccount_id(account.getId());
            session.setAttribute("enrollee", enrollee);
            LOG.trace("Set the session attribute: accountRole --> " + accountRole);

            if (accountRole == Role.ADMIN){
                return new PageData("/controller?command=showUser", false);
            }

            if (accountRole == Role.CLIENT){
                if (account.is_banned()){
                    session = request.getSession(false);
                    if (session != null) {
                        session.invalidate();
                    }
                    LOG.debug("Command completed successfully. User is banned");
                    request.setAttribute(RequestProperty.ERROR, Message.BAN_USER);
                    return new PageData(Path.ERROR_PAGE, true);
                }
                LOG.debug("Command completed successfully");
                return new PageData(Path.PERSONAL_AREA_CLIENT, true);
            }
        }

        LOG.debug("Command is completed with error");
        return new PageData(Path.LOGIN_PAGE, true);
    }
}
