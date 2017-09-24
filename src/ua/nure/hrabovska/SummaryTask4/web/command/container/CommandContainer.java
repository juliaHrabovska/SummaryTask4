package ua.nure.hrabovska.SummaryTask4.web.command.container;

import org.apache.log4j.Logger;
import ua.nure.hrabovska.SummaryTask4.web.Path;
import ua.nure.hrabovska.SummaryTask4.web.command.Command;
import ua.nure.hrabovska.SummaryTask4.web.command.account.ShowExamListCommand;
import ua.nure.hrabovska.SummaryTask4.web.command.account.registration.ExamFillingCommand;
import ua.nure.hrabovska.SummaryTask4.web.command.account.registration.FileUploadCommand;

import ua.nure.hrabovska.SummaryTask4.web.command.account.LoginCommand;
import ua.nure.hrabovska.SummaryTask4.web.command.account.LogoutCommand;
import ua.nure.hrabovska.SummaryTask4.web.command.account.registration.RegistrationCommand;
import ua.nure.hrabovska.SummaryTask4.web.command.account.registration.ShowChosenExamListCommand;
import ua.nure.hrabovska.SummaryTask4.web.command.filingApplication.*;
import ua.nure.hrabovska.SummaryTask4.web.command.link.LinkPageCommand;
import ua.nure.hrabovska.SummaryTask4.web.command.user.admin.BanUserCommand;
import ua.nure.hrabovska.SummaryTask4.web.command.user.admin.ChangeStatusCommand;
import ua.nure.hrabovska.SummaryTask4.web.command.user.admin.ShowUsersCommand;
import ua.nure.hrabovska.SummaryTask4.web.command.user.admin.UserPageCommand;
import ua.nure.hrabovska.SummaryTask4.web.command.user.enrollee.DeleteAppCommand;
import ua.nure.hrabovska.SummaryTask4.web.command.user.enrollee.EditProfileCommand;
import ua.nure.hrabovska.SummaryTask4.web.command.user.enrollee.SubmittedAppCommand;

import java.util.Map;
import java.util.TreeMap;

/**
 * Holder of all commands
 *
 * @author Y. Hrabovska
 */
public class CommandContainer {

    private static final Logger LOG = Logger.getLogger(CommandContainer.class);

    private static Map<String, Command> commands = new TreeMap<>();

    static {
        // account commands
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());

        // registration commands
        commands.put("registration", new RegistrationCommand());
        commands.put("getRegistrationPage", new LinkPageCommand(Path.REGISTRATION_PAGE));
        commands.put("showExamList", new ShowExamListCommand());
        commands.put("showChosenExamList", new ShowChosenExamListCommand());
        commands.put("fillExRes", new ExamFillingCommand());

        // filling applications commands
        commands.put("showPlace", new ShowPlaceCommand());
        commands.put("showUniversity", new ShowUniversityCommand());
        commands.put("universityPage", new ShowUniversityPageCommand());
        commands.put("contest", new ContestCommand());
        commands.put("apply", new ApplyCommand());

        // enrollee's commands
        commands.put("submitted_applications", new SubmittedAppCommand());
        commands.put("deleteApp", new DeleteAppCommand());
        commands.put("editProfile", new EditProfileCommand());

        // admin's commands
        commands.put("showUser", new ShowUsersCommand());
        commands.put("ban_unbanUser", new BanUserCommand());
        commands.put("changeStatus", new ChangeStatusCommand());
        commands.put("enrolleeInfo", new UserPageCommand());

        // link commands
        commands.put("settings", new LinkPageCommand(Path.SETTINGS_CLIENT));
        commands.put("personal_area_client", new LinkPageCommand(Path.PERSONAL_AREA_CLIENT));

    }

    /**
     * Returns command object with the given name.
     *
     * @param commandName Name of the command.
     * @return Command object.
     */
    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            LOG.trace("Command not found, name --> " + commandName);
            return commands.get("noCommand");
        }

        return commands.get(commandName);
    }
}
