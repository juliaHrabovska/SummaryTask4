package ua.nure.hrabovska.SummaryTask4.web.listener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
//import javax.servlet.jsp.jstl.core.Config;

//import ua.nure.hrabovska.SummaryTask4.enums.Language;
//import ua.nure.hrabovska.SummaryTask4.util.MessageLanguge;

/**
 * Context listener
 *
 * @author Y. Hrabovska
 */
@WebListener
public class ContextListener implements ServletContextListener {

    private static final Logger LOG = Logger.getLogger(ContextListener.class);

    public void contextDestroyed(ServletContextEvent event) {
        log("Servlet context destruction starts");
        // no op
        log("Servlet context destruction finished");
    }

    public void contextInitialized(ServletContextEvent event) {
        log("Servlet context initialization starts");

        ServletContext servletContext = event.getServletContext();
        initLog4J(servletContext);
        initCommandContainer();

        //setLocale(servletContext);
        log("Servlet context initialization finished");
    }

    /*
     * Initializes log4j framework.
     *
     * @param servletContext
     */
    private void initLog4J(ServletContext servletContext) {
        PropertyConfigurator.configure(this.getClass().getClassLoader().getResource("configureLogger.properties"));

        log("Log4J initialization started");
        try {
            PropertyConfigurator.configure(
                    servletContext.getRealPath("WEB-INF/log4j.properties"));
            LOG.debug("Log4j has been initialized");
        } catch (Exception ex) {
            log("Cannot configure Log4j");
            ex.printStackTrace();
        }
        log("Log4J initialization finished");
    }

    /**private void setLocale(ServletContext context) {
        Config.set(context, Config.FMT_LOCALE, Language.ENGLISH.getLocale());
        MessageLanguge.setLocale(Language.ENGLISH.getLocale());
        LOG.debug("Defaul locale has been set");
    }
    /**
     * Initializes CommandContainer.
     */
    private void initCommandContainer() {

        try {
            Class.forName("ua.nure.hrabovska.SummaryTask4.web.command.container.CommandContainer");
        } catch (ClassNotFoundException ex) {
            throw new IllegalStateException("Cannot initialize Command Container");
        }
    }

    private void log(String msg) {
        System.out.println("[ContextListener] " + msg);
    }
}