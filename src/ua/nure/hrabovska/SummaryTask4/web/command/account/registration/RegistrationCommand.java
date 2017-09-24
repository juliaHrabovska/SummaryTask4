package ua.nure.hrabovska.SummaryTask4.web.command.account.registration;

import org.apache.log4j.Logger;
import ua.nure.hrabovska.SummaryTask4.database.dao.AccountDAO;
import ua.nure.hrabovska.SummaryTask4.database.dao.EnrolleeDAO;
import ua.nure.hrabovska.SummaryTask4.database.entity.Account;
import ua.nure.hrabovska.SummaryTask4.database.entity.Enrollee;
import ua.nure.hrabovska.SummaryTask4.enums.Role;
import ua.nure.hrabovska.SummaryTask4.exception.DBException;
import ua.nure.hrabovska.SummaryTask4.exception.Message;
import ua.nure.hrabovska.SummaryTask4.util.CaptchaVerify;
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

import static ua.nure.hrabovska.SummaryTask4.enums.Role.CLIENT;

/**
 * Registration command
 *
 * @author Y. Hrabovska
 *
 */
public class RegistrationCommand extends Command {
    private static final Logger LOG = Logger.getLogger(RegistrationCommand.class);

    @Override
    public PageData execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException, DBException {
        LOG.debug("Command starts");

        HttpSession session = request.getSession();

        String email = request.getParameter(RequestProperty.EMAIL);
        String password = request.getParameter(RequestProperty.PASSWORD);
        String password2 = request.getParameter(RequestProperty.PASSWORD2);
        String firstName = request.getParameter(RequestProperty.FIRST_NAME);
        String lastName = request.getParameter(RequestProperty.LAST_NAME);
        String patronymic = request.getParameter(RequestProperty.PATRONYMIC);
        Integer certificate_score = Integer.parseInt(request.getParameter(RequestProperty.CERTIFICATE_SCORE));
        Integer level_of_training = Integer.parseInt(request.getParameter(RequestProperty.LEVEL_OF_TRAINING));


        boolean isNoError = true;

        LOG.trace("Request parameter email: " + email);
        LOG.trace("Request parameter password: " + password);
        LOG.trace("Request parameter password2: " + password2);
        LOG.trace("Request parameter firstname: " + firstName);
        LOG.trace("Request parameter lastname: " + lastName);
        LOG.trace("Request parameter patronymic: " + patronymic);
        LOG.trace("Request parameter certificate_score: " + certificate_score);
        LOG.trace("Request parameter level_of_training: " + level_of_training);


        if (!ValidatorField.isPasswordValid(password)) {
            request.setAttribute(RequestProperty.ERROR, Message.NOT_VALID_PASSWORD);
            LOG.debug(Message.NOT_VALID_PASSWORD);
        } else if (!ValidatorField.isNameFieldValid(firstName) || !ValidatorField.isNameFieldValid(lastName)) {
            request.setAttribute(RequestProperty.ERROR, Message.NOT_VALID_FIRSTNAME_SECONDNAME_PATRONYMIC);
            LOG.debug(Message.NOT_VALID_FIRSTNAME_SECONDNAME_PATRONYMIC);
        } else if (!password.equals(password2)) {
            request.setAttribute(RequestProperty.ERROR, Message.PASSWORDS_NOT_MATCH);
            LOG.debug(Message.PASSWORDS_NOT_MATCH);
        } else if (!ValidatorField.isEmailValid(email) && email.length() <= 30) {
            request.setAttribute(RequestProperty.ERROR, Message.NOT_VALID_EMAIL);
            LOG.debug(Message.NOT_VALID_EMAIL);
        } else if (!ValidatorField.isCertificate_ScoreValid(certificate_score)) {
            request.setAttribute(RequestProperty.ERROR, Message.NOT_VALID_CERTIFICATE_SCORE);
            LOG.trace(Message.NOT_VALID_CERTIFICATE_SCORE);
        } else {
            if (request.getSession().getAttribute(RequestProperty.SHOW_CAPTCHA) != null) {
                String gRecaptchaResponse = request.getParameter(RequestProperty.CAPTCHA);
                if (!CaptchaVerify.verifyCaptcha(gRecaptchaResponse)) {
                    request.setAttribute(RequestProperty.ERROR, Message.WRONG_CAPTCHA);
                    isNoError = false;
                } else {
                    request.getSession().removeAttribute(RequestProperty.SHOW_CAPTCHA);
                }
            }
            if (isNoError) {
                AccountDAO accountDAO = new AccountDAO();
                if (accountDAO.getByEmail(email) != null) {
                    request.setAttribute(RequestProperty.ERROR, Message.ACCOUNTS_EXISTS);
                } else {
                    EnrolleeDAO enrolleeDAO = new EnrolleeDAO();
                    Account account = new Account();
                    account.setEmail(email);
                    account.setPassword(Encryption.doCipher(password));
                    account.setRole_id(CLIENT);

                    Enrollee enrollee = new Enrollee();
                    enrollee.setFirst_name(firstName);
                    enrollee.setLast_name(lastName);
                    enrollee.setPatronymic(patronymic);
                    enrollee.setCertificate_score(certificate_score);
                    enrollee.setLevel_id(level_of_training);

                    if (enrolleeDAO.createUser(account, enrollee)) {

                        Role accountRole = account.getRole_id();
                        LOG.debug("Command is completed successfully");

                        session.setAttribute("accountRole", accountRole);
                        LOG.trace("Set the session attribute: accountRole --> " + accountRole);

                        account = accountDAO.getByEmail(email);
                        session.setAttribute("account", account);
                        LOG.trace("Set the session attribute: account --> " + account);

                        enrollee = enrolleeDAO.getByAccount_id(account.getId());
                        session.setAttribute("enrollee", enrollee);
                        LOG.trace("Set the session attribute: enrollee --> " + enrollee);

                        return new PageData(Path.UPLOAD_FILE_PAGE, true);
                    }
                }
            }
        }
        LOG.debug("Command is completed with error");
        return new PageData(Path.REGISTRATION_PAGE, true);
    }
}
