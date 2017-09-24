package ua.nure.hrabovska.SummaryTask4.web.command.user.admin;

import org.apache.log4j.Logger;
import ua.nure.hrabovska.SummaryTask4.database.dao.AccountDAO;
import ua.nure.hrabovska.SummaryTask4.database.entity.Account;
import ua.nure.hrabovska.SummaryTask4.exception.DBException;
import ua.nure.hrabovska.SummaryTask4.web.Path;
import ua.nure.hrabovska.SummaryTask4.web.command.Command;
import ua.nure.hrabovska.SummaryTask4.web.command.PageData;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BanUserCommand extends Command {
    private static final Logger LOG = Logger.getLogger(BanUserCommand.class);
    @Override
    public PageData execute(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException, DBException {

        LOG.debug("Command starts");
        long enrolleeId = Long.parseLong(request.getParameter("enrolleeId"));
        LOG.trace("Request parameter enrolleeId: " + enrolleeId);

        AccountDAO accountDAO = new AccountDAO();
        Account account = accountDAO.getAccountById(enrolleeId);
        LOG.trace("Found in DB account --> " + account);

        account.setIs_banned(!account.is_banned());

        if (accountDAO.updateBanned(account.getId(), account.is_banned())) {
            LOG.debug("Command is finished successfully");
            return new PageData("/controller?command=showUser", false);
        }

        LOG.debug("Command is completed with error");
        return new PageData(Path.ERROR_PAGE, true);
    }
//        LOG.debug("Command starts");
//        String[] enrolleeId = request.getParameterValues("enrolleeId");
//        LOG.trace("Set the request parameter: enrolleeId --> " + Arrays.toString(enrolleeId));
//
//        AccountDAO accountDAO = new AccountDAO();
//        Map<Long, Boolean> accounts = accountDAO.getAccountById(enrolleeId);
//        LOG.trace("Found in DB accounts --> " + accounts);
//
//        for (Map.Entry<Long, Boolean> entry : accounts.entrySet()) {
//            boolean value = entry.getValue();
//            accounts.put(entry.getKey(), !value);
//        }
//
//        if (accountDAO.updateBanned(accounts)) {
//            LOG.debug("Commands finished");
//            return new PageData("/controller?command=showUser",false);
//        }
//
//        LOG.debug("Command is completed with error");
//        return new PageData(Path.ERROR_PAGE, true);
//    }
}
