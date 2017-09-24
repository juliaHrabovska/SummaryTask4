package ua.nure.hrabovska.SummaryTask4.database.dao;


import org.apache.log4j.Logger;
import ua.nure.hrabovska.SummaryTask4.database.DBManager;
import ua.nure.hrabovska.SummaryTask4.database.Field;
import ua.nure.hrabovska.SummaryTask4.database.entity.Account;
import ua.nure.hrabovska.SummaryTask4.enums.Role;
import ua.nure.hrabovska.SummaryTask4.exception.DBException;
import ua.nure.hrabovska.SummaryTask4.exception.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Interacts with database. Operates Account data
 *
 * @author Y. Hrabovska
 */
public class AccountDAO {

    private static final Logger LOG = Logger.getLogger(AccountDAO.class);

    private static final String GET_ACCOUNT_BY_EMAIL = "SELECT * FROM account WHERE email=?";

    private static final String UPDATE_BANNED_ACCOUNT = "UPDATE account SET is_banned=? WHERE id=?";

    private static final String UPDATE_ACCOUNT = "UPDATE account SET email = ?, password = ? WHERE id=?";

    private static final String GET_ACCOUNT_BY_ENROLLEEE_ID =
            "SELECT a.id, a.email, a.password, a.is_banned, a.role_id " +
                    "           FROM account AS a " +
                    "            INNER JOIN " +
                    "               enrollee " +
                    "            ON enrollee.account_id = a.id " +
                    "            WHERE enrollee.id = ?";

    private Connection connection;

    /**
     * Get account id by enrollee id
     *
     * @param enrolleeId
     * @return Account object
     * @throws DBException
     */
    public Account getAccountById(long enrolleeId) throws DBException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Account account = new Account();
        try {
            connection = DBManager.getConnection();
            statement = connection.prepareStatement(GET_ACCOUNT_BY_ENROLLEEE_ID);
            statement.setLong(1, enrolleeId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                account = extract(resultSet);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new DBException(Message.CANNOT_GET_ACCOUNT, e);
        } finally {
            DBManager.closeStatement(statement);
            DBManager.closeConnection(connection);
        }
        return account;
    }

    /**
     * Updates account in the database
     *
     * @param account account to update
     * @return true if account was updated, false otherwise
     * @throws DBException
     */
    public boolean update(Account account) throws DBException {
        PreparedStatement statement = null;
        boolean isUpdated = false;
        try {
            connection = DBManager.getConnection();
            statement = connection.prepareStatement(UPDATE_ACCOUNT);
            statement.setString(1, account.getEmail());
            statement.setString(2, account.getPassword());
            statement.setLong(3, account.getId());

            if (statement.executeUpdate() != 0) {
                isUpdated = true;
            }
        } catch (SQLException e) {
            DBManager.rollback(connection);
            LOG.error(e.getMessage(), e);
            throw new DBException(Message.CANNOT_UPDATE_ACCOUNT, e);
        } finally {
            DBManager.closeStatement(statement);
            DBManager.closeConnection(connection);
        }
        return isUpdated;
    }


    /**
     * Ban user
     *
     * @param account account_id
     * @param isBanned banned_status
     * @return Account object
     * @throws DBException
     */
    public boolean updateBanned(long account, boolean isBanned) throws DBException {
        PreparedStatement statement = null;
        boolean isUpdated = false;
        try {
            connection = DBManager.getConnection();
            statement = connection.prepareStatement(UPDATE_BANNED_ACCOUNT);
            statement.setBoolean(1, isBanned);
            statement.setLong(2, account);
            if (statement.executeUpdate() != 0) {
                isUpdated = true;
            }
        } catch (SQLException e) {
            DBManager.rollback(connection);
            LOG.error(Message.CANNOT_UPDATE_USER, e);
            throw new DBException(Message.CANNOT_UPDATE_USER_BAN, e);
        } finally {
            DBManager.closeStatement(statement);
            DBManager.closeConnection(connection);
        }
        return isUpdated;
    }

    /**
     * Get account by email
     *
     * @param email - account email
     * @return Account object
     * @throws DBException
     */
    public Account getByEmail(String email) throws DBException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Account account = null;
        try {
            connection = DBManager.getConnection();
            statement = connection.prepareStatement(GET_ACCOUNT_BY_EMAIL);
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                account = extract(resultSet);
            }
        } catch (SQLException e) {
            LOG.error(Message.CANNOT_GET_ACCOUNT_BY_EMAIL, e);
            throw new DBException(Message.CANNOT_GET_ACCOUNT_BY_EMAIL, e);
        } finally {
            DBManager.closeStatement(statement);
            DBManager.closeResultSet(resultSet);
            DBManager.closeConnection(connection);
        }
        return account;
    }

    /**
     * Extracts a Account object from the result set
     *
     * @param resultSet Result set from which an account will be extracted.
     * @return Account object
     * @throws SQLException
     * @throws DBException
     */
    private Account extract(ResultSet resultSet) throws SQLException {
        Account account = new Account();
        account.setId(resultSet.getLong(Field.ID));
        account.setEmail(resultSet.getString(Field.EMAIL));
        account.setPassword(resultSet.getString(Field.PASSWORD));
        account.setIs_banned(resultSet.getBoolean(Field.IS_BANNED));
        account.setRole_id(Role.getRole(resultSet.getInt(Field.ROLE_ID)));
        return account;
    }


}
