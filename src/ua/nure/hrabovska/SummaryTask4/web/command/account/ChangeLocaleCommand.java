package ua.nure.hrabovska.SummaryTask4.web.command.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

import org.apache.log4j.Logger;

import ua.nure.hrabovska.SummaryTask4.database.entity.Account;
import ua.nure.hrabovska.SummaryTask4.enums.Language;
import ua.nure.hrabovska.SummaryTask4.enums.Role;
import ua.nure.hrabovska.SummaryTask4.util.MessageLanguge;
import ua.nure.hrabovska.SummaryTask4.web.RequestProperty;
import ua.nure.hrabovska.SummaryTask4.web.command.Command;
import ua.nure.hrabovska.SummaryTask4.exception.DBException;
import ua.nure.hrabovska.SummaryTask4.web.command.PageData;

/**
 * Change locale command
 *
 * @author Y. Hrabovska
 */
public class ChangeLocaleCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ChangeLocaleCommand.class);

    @Override
    public PageData execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, DBException {

        LOG.debug("Command starts");

        String language = request.getParameter(RequestProperty.LANGUAGE);

        LOG.trace("Request parameter language: " + language);

        HttpSession session = request.getSession();
        switch (language) {
            case "en":
                Config.set(session, Config.FMT_LOCALE, Language.ENGLISH.getLocale());
                MessageLanguge.setLocale(Language.ENGLISH.getLocale());
                break;
            case "ru":
                Config.set(session, Config.FMT_LOCALE, Language.RUSSIAN.getLocale());
                MessageLanguge.setLocale(Language.RUSSIAN.getLocale());
                break;
            default:
                Config.set(session, Config.FMT_LOCALE, Language.RUSSIAN.getLocale());
                MessageLanguge.setLocale(Language.RUSSIAN.getLocale());
                break;
        }

        Account account = (Account) session.getAttribute("account");
        if (account.getRole_id() == Role.ADMIN) {

            LOG.debug("Command is complited successfully");
            return new PageData("/controller?command=showUser", false);
        }
        LOG.debug("Command is complited successfully");
        return new PageData("/controller?command=personal_area_client", false);
    }

}
