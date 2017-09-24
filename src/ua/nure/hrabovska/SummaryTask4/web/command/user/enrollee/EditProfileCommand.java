package ua.nure.hrabovska.SummaryTask4.web.command.user.enrollee;

import org.apache.log4j.Logger;
import ua.nure.hrabovska.SummaryTask4.database.dao.AccountDAO;
import ua.nure.hrabovska.SummaryTask4.database.dao.EnrolleeDAO;
import ua.nure.hrabovska.SummaryTask4.database.entity.Account;
import ua.nure.hrabovska.SummaryTask4.database.entity.Enrollee;
import ua.nure.hrabovska.SummaryTask4.exception.DBException;
import ua.nure.hrabovska.SummaryTask4.exception.Message;
import ua.nure.hrabovska.SummaryTask4.util.Encryption;
import ua.nure.hrabovska.SummaryTask4.util.ValidatorField;
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

/**
 * Edit profile command
 *
 * @author Y. Hrabovska
 */
public class EditProfileCommand extends Command {

    private static final Logger LOG = Logger.getLogger(EditProfileCommand.class);

    @Override
    public PageData execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, DBException {

        LOG.debug("Command starts");

        HttpSession session = request.getSession();
        Enrollee enrolleeS = (Enrollee) session.getAttribute("enrollee");
        LOG.trace("Get attribute enrolleeS: " + enrolleeS);

        Account accountS = (Account) session.getAttribute("account");
        LOG.trace("Get attribute accountS: " + accountS);

        String email = request.getParameter(RequestProperty.EMAIL);
        String password = request.getParameter(RequestProperty.PASSWORD);
        String password2 = request.getParameter(RequestProperty.PASSWORD2);
        String first_name = request.getParameter(RequestProperty.FIRST_NAME);
        String last_name = request.getParameter(RequestProperty.LAST_NAME);
        String patronymic = request.getParameter(RequestProperty.PATRONYMIC);

        LOG.trace("Request parameter email: " + email);
        LOG.trace("Request parameter password: " + password);
        LOG.trace("Request parameter password2: " + password2);
        LOG.trace("Request parameter first_name: " + first_name);
        LOG.trace("Request parameter last_name: " + last_name);
        LOG.trace("Request parameter patronymic: " + patronymic);

        if (!ValidatorField.isPasswordValid(password)) {
            request.setAttribute(RequestProperty.ERROR, Message.NOT_VALID_PASSWORD);
        } else if (!ValidatorField.isNameFieldValid(first_name) || !ValidatorField.isNameFieldValid(last_name)) {
            request.setAttribute(RequestProperty.ERROR, Message.NOT_VALID_FIRSTNAME_SECONDNAME_PATRONYMIC);
        } else if (!password.equals(password2)) {
            request.setAttribute(RequestProperty.ERROR, Message.PASSWORDS_NOT_MATCH);
        } else if (!ValidatorField.isEmailValid(email)) {
            request.setAttribute(RequestProperty.ERROR, Message.NOT_VALID_EMAIL);
        } else {
            EnrolleeDAO enrolleeDAO = new EnrolleeDAO();
            AccountDAO accountDAO = new AccountDAO();

            Enrollee enrollee = new Enrollee();
            enrollee.setId(enrolleeS.getId());
            enrollee.setFirst_name(first_name);
            enrollee.setLast_name(last_name);
            enrollee.setPatronymic(patronymic);
            enrollee.setAccount_id(enrolleeS.getAccount_id());
            LOG.trace("New parameters in enrollee: enrollee --> " + enrollee);

            Account account = new Account();
            account.setId(accountS.getId());
            account.setEmail(email);
            account.setPassword(Encryption.doCipher(password));

            if (enrolleeDAO.update(enrollee) && accountDAO.update(account)) {
                request.setAttribute(RequestProperty.ERROR, Message.All_OK);

                session.setAttribute("enrollee", enrollee);
                LOG.trace("Set the session attribute: enrollee --> " + enrollee);

                LOG.debug("Command is completed successfully");
                return new PageData(Path.SETTINGS_CLIENT, true);
            }

        }
        LOG.debug("Command is completed with error");
        request.setAttribute(RequestProperty.ERROR, Message.CANNOT_UPDATE_USER);
        return new PageData(Path.SETTINGS_CLIENT, true);
    }
}
