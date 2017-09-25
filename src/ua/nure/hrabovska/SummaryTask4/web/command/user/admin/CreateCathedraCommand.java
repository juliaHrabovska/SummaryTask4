package ua.nure.hrabovska.SummaryTask4.web.command.user.admin;

import org.apache.log4j.Logger;
import ua.nure.hrabovska.SummaryTask4.database.dao.CathedraDAO;
import ua.nure.hrabovska.SummaryTask4.exception.DBException;
import ua.nure.hrabovska.SummaryTask4.exception.Message;
import ua.nure.hrabovska.SummaryTask4.web.RequestProperty;
import ua.nure.hrabovska.SummaryTask4.web.command.Command;
import ua.nure.hrabovska.SummaryTask4.web.command.PageData;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;

public class CreateCathedraCommand extends Command {

    private static final Logger LOG = Logger.getLogger(CreateCathedraCommand.class);

    @Override
    public PageData execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException, DBException {
        LOG.debug("Command starts");

        String name = request.getParameter(RequestProperty.NAME);
        int budget = Integer.parseInt(request.getParameter(RequestProperty.BUDGET));
        int contract = Integer.parseInt(request.getParameter(RequestProperty.CONTRACT));
        int type_of_training = Integer.parseInt(request.getParameter(RequestProperty.TYPE_OF_TRAINING));
        int level_of_training = Integer.parseInt(request.getParameter(RequestProperty.LEVEL_OF_TRAINING));
        String[] exams = request.getParameterValues("examID");
        String[] departments = request.getParameterValues("departmentId");

        LOG.trace("Request parameter name: " + name);
        LOG.trace("Request parameter budget: " + budget);
        LOG.trace("Request parameter contract: " + contract);
        LOG.trace("Request parameter type_of_training: " + type_of_training);
        LOG.trace("Request parameter level_of_training: " + level_of_training);
        LOG.trace("Request parameter exams: " + Arrays.toString(exams));
        LOG.trace("Request parameter departments: " + Arrays.toString(departments));

        if (!(budget >= 0 && contract >= 0)) {
            request.setAttribute(RequestProperty.ERROR, Message.NOT_VALID_BUDGET_CONTRACT_FIELD);
            LOG.debug(Message.NOT_VALID_BUDGET_CONTRACT_FIELD);
        } else if (departments.length == 0) {
            request.setAttribute(RequestProperty.ERROR, Message.NOT_CHOSEN_DEPARTMENT);
            LOG.debug(Message.NOT_CHOSEN_DEPARTMENT);
        } else if (!(exams.length >= 2)) {
            request.setAttribute(RequestProperty.ERROR, Message.NOT_ENOUGH_EXAMS);
            LOG.debug(Message.NOT_ENOUGH_EXAMS);
        } else {
            CathedraDAO cathedraDAO = new CathedraDAO();

            if (cathedraDAO.createCathedra(name, budget, contract, type_of_training, level_of_training, exams, departments)) {
                LOG.debug("Commands finished");
                return new PageData("/controller?command=universityPage", false);
            }
        }
        LOG.debug("Command is completed with error");
        request.setAttribute(RequestProperty.ERROR, Message.CANNOT_CREATE_CATHEDRA);
        return new PageData("/controller?command=universityPage", false);

    }
}
