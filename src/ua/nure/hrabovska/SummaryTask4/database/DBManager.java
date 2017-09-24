package ua.nure.hrabovska.SummaryTask4.database;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Works with MySQL database, provides pooled connection
 *
 * @author Y. Hrabovska
 */
public final class DBManager {

    private static final Logger LOG = Logger.getLogger(DBManager.class);

    private static DataSource ds;

    /**
     * Returns a database connection from the Pool Connections.
     *
     * @return database connection
     * @throws SQLException
     */
    public static synchronized Connection getConnection() throws SQLException {
        if (ds == null) {
            try {
                InitialContext ctx = new InitialContext();
                Context context = (Context) ctx.lookup("java:comp/env");
                ds = (DataSource) context.lookup("jdbc/selection_committee");
            } catch (NamingException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return ds.getConnection();
    }

    /**
     * Closes connection
     *
     * @param connection connection to close
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }

    /**
     * Closes statement
     *
     * @param statement statement to close
     */
    public static void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }

    /**
     * Closes result set
     *
     * @param resultSet result set to close
     */
    public static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }

    /**
     * Rollbacks a connection
     *
     * @param connection
     */
    public static void rollback(Connection connection) {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }
}