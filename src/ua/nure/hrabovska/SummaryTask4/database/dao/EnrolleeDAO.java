package ua.nure.hrabovska.SummaryTask4.database.dao;

import org.apache.log4j.Logger;
import ua.nure.hrabovska.SummaryTask4.database.DBManager;
import ua.nure.hrabovska.SummaryTask4.database.Field;
import ua.nure.hrabovska.SummaryTask4.database.bean.EnrolleeBean;
import ua.nure.hrabovska.SummaryTask4.database.entity.Account;
import ua.nure.hrabovska.SummaryTask4.database.entity.Enrollee;
import ua.nure.hrabovska.SummaryTask4.exception.DBException;
import ua.nure.hrabovska.SummaryTask4.exception.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Interacts with database. Operates Enrollee data
 *
 * @author Y. Hrabovska
 */
public class EnrolleeDAO {

    private static final Logger LOG = Logger.getLogger(EnrolleeDAO.class);

    private static final String CREATE_ENROLLEE =
            "INSERT INTO enrollee (first_name, last_name, patronymic, certificate_score, account_id, level_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String GET_ENROLLEE_BY_ACCOUNT_ID = "SELECT * FROM enrollee WHERE account_id=?";

    private static final String CREATE_ACCOUNT = "INSERT INTO account (email, password, role_id, is_banned) VALUES (?, ?, ?, ?)";

    private static final String GET_ACCOUNT_ID_BY_EMAIL = "SELECT id FROM account WHERE email=?";

    private static final String UPDATE_ENROLLEE = "UPDATE enrollee SET first_name=?, last_name=?, patronymic=? WHERE id=?";

    private static final String INSERT_CERTIFICATE_PATH = "UPDATE enrollee SET certificate_path=? WHERE id=?";

    private static final String GET_ENROLLEE_BY_ID = "SELECT * FROM enrollee WHERE id=?";

    private static final String GET_ALL_ENROLLEE =
            "SELECT e.id, e.first_name, e.last_name, e.patronymic,  " +
                    "    lt.name AS level_of_training, e.certificate_score, e.certificate_path,  " +
                    "    a.is_banned " +
                    "    FROM enrollee AS e " +
                    "        INNER JOIN  " +
                    "         account AS a " +
                    "        ON a.id = e.account_id " +
                    "        INNER JOIN " +
                    "         level_of_training AS lt " +
                    "        ON lt.id = e.level_id";

    private Connection connection;

    /**
     * Get all enrollees
     *
     * @return List<Enrollee>
     * @throws DBException
     */
    public List<EnrolleeBean> getAllEnrollees() throws DBException {
        List<EnrolleeBean> enrolleeList = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBManager.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(GET_ALL_ENROLLEE);
            while (resultSet.next()) {
                enrolleeList.add(extractEnrolleeBean(resultSet));
            }
        } catch (SQLException e) {
            LOG.error(Message.CANNOT_FIND_ENROLLEE, e);
            throw new DBException(Message.CANNOT_FIND_ENROLLEE, e);
        } finally {
            DBManager.closeStatement(statement);
            DBManager.closeResultSet(resultSet);
            DBManager.closeConnection(connection);
        }
        return enrolleeList;
    }

    /**
     * Get enrollee by id
     *
     * @param id - enrollee id
     * @return Enrollee object
     * @throws DBException
     */
    public Enrollee getById(long id) throws DBException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Enrollee enrollee = null;
        try {
            connection = DBManager.getConnection();
            statement = connection.prepareStatement(GET_ENROLLEE_BY_ID);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                enrollee = extractEnrollee(resultSet);
            }
        } catch (SQLException e) {
            LOG.error(Message.CANNOT_GET_ENROLLEE_BY_ID, e);
            throw new DBException(Message.CANNOT_GET_ENROLLEE_BY_ID, e);
        } finally {
            DBManager.closeStatement(statement);
            DBManager.closeResultSet(resultSet);
            DBManager.closeConnection(connection);
        }
        return enrollee;
    }

    /**
     * Insert certificate path in database
     *
     * @param path - certificate path to insert
     * @return true if operation was successful
     * @throws DBException
     */
    public boolean insertCertificatePath(String path, long enrollee_id) throws DBException {
        boolean result = true;
        PreparedStatement statement = null;
        try {
            connection = DBManager.getConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            statement = connection.prepareStatement(INSERT_CERTIFICATE_PATH);
            statement.setString(1, path);
            statement.setLong(2, enrollee_id);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            DBManager.rollback(connection);
            LOG.error(Message.CANNOT_INSERT_CERTIFICATE_PATH, e);
            result = false;
            throw new DBException(Message.CANNOT_INSERT_CERTIFICATE_PATH, e);
        } finally {
            DBManager.closeStatement(statement);
            DBManager.closeConnection(connection);
        }
        return result;
    }

    /**
     * Updates enrollee in the database
     *
     * @param enrollee enrollee to update
     * @return true if user was updated, false otherwise
     * @throws DBException
     */
    public boolean update(Enrollee enrollee) throws DBException {
        PreparedStatement statement = null;
        boolean isUpdated = false;
        try {
            connection = DBManager.getConnection();
            statement = connection.prepareStatement(UPDATE_ENROLLEE);
            statement.setString(1, enrollee.getFirst_name());
            statement.setString(2, enrollee.getLast_name());
            statement.setString(3, enrollee.getPatronymic());
            statement.setLong(4, enrollee.getId());

            if (statement.executeUpdate() != 0) {
                isUpdated = true;
            }
        } catch (SQLException e) {
            DBManager.rollback(connection);
            LOG.error(e.getMessage(), e);
            throw new DBException(Message.CANNOT_UPDATE_USER, e);
        } finally {
            DBManager.closeStatement(statement);
            DBManager.closeConnection(connection);
        }
        return isUpdated;
    }

    /**
     * Inserts new enrollee in the database
     *
     * @param enrollee enrollee to insert
     * @throws DBException
     */
    public boolean createUser(Account account, Enrollee enrollee) throws DBException {
        boolean result = true;
        PreparedStatement statement = null;
        ResultSet resultSet;
        try {
            connection = DBManager.getConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            statement = connection.prepareStatement(CREATE_ACCOUNT);
            statement.setString(1, account.getEmail());
            statement.setString(2, account.getPassword());
            statement.setInt(3, account.getRole_id().ordinal());
            statement.setBoolean(4, account.is_banned());
            statement.executeUpdate();

            statement = connection.prepareStatement(GET_ACCOUNT_ID_BY_EMAIL);
            statement.setString(1, account.getEmail());
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                account.setId(resultSet.getLong(Field.ID));
            }

            statement = connection.prepareStatement(CREATE_ENROLLEE);
            statement.setString(1, enrollee.getFirst_name());
            statement.setString(2, enrollee.getLast_name());
            statement.setString(3, enrollee.getPatronymic());
            statement.setInt(4, enrollee.getCertificate_score());
            statement.setLong(5, account.getId());
            statement.setLong(6, enrollee.getLevel_id());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            DBManager.rollback(connection);
            LOG.error(e.getMessage(), e);
            throw new DBException(e.getMessage(), e);
        } finally {
            DBManager.closeStatement(statement);
            DBManager.closeConnection(connection);
        }
        return result;
    }

    /**
     * Get enrolle by account_id
     *
     * @param account_id - account email
     * @return Enrollee object
     * @throws DBException
     */
    public Enrollee getByAccount_id(long account_id) throws DBException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Enrollee enrollee = null;
        try {
            connection = DBManager.getConnection();
            statement = connection.prepareStatement(GET_ENROLLEE_BY_ACCOUNT_ID);
            statement.setLong(1, account_id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                enrollee = extractEnrollee(resultSet);
            }
        } catch (SQLException e) {
            LOG.error(Message.CANNOT_GET_ACCOUNT_BY_EMAIL, e);
            throw new DBException(Message.CANNOT_GET_ACCOUNT_BY_EMAIL, e);
        } finally {
            DBManager.closeStatement(statement);
            DBManager.closeResultSet(resultSet);
            DBManager.closeConnection(connection);
        }
        return enrollee;
    }

    /**
     * Extracts a Enrollee object from the result set
     *
     * @param resultSet Result set from which an enrollee will be extracted.
     * @return Enrollee object
     * @throws SQLException
     */
    private Enrollee extractEnrollee(ResultSet resultSet) throws SQLException {
        Enrollee enrollee = new Enrollee();
        enrollee.setId(resultSet.getLong(Field.ID));
        enrollee.setFirst_name(resultSet.getString(Field.FIRST_NAME));
        enrollee.setLast_name(resultSet.getString(Field.LAST_NAME));
        enrollee.setPatronymic(resultSet.getString(Field.PATRONYMIC));
        enrollee.setCertificate_score(resultSet.getInt(Field.CERTIFICATE_SCORE));
        enrollee.setAccount_id(resultSet.getLong(Field.ACCOUNT_ID));
        enrollee.setLevel_id(resultSet.getLong(Field.LEVEL_ID));
        enrollee.setCertificate_path(resultSet.getString(Field.CERTIFICATE_PATH));
        return enrollee;
    }

    /**
     * Extracts a EnrolleeBean object from the result set
     *
     * @param resultSet Result set from which an enrollee will be extracted.
     * @return EnrolleeBean object
     * @throws SQLException
     */
    private EnrolleeBean extractEnrolleeBean(ResultSet resultSet) throws SQLException {
        EnrolleeBean enrollee = new EnrolleeBean();
        enrollee.setId(resultSet.getLong(Field.ID));
        enrollee.setFirst_name(resultSet.getString(Field.FIRST_NAME));
        enrollee.setLast_name(resultSet.getString(Field.LAST_NAME));
        enrollee.setPatronymic(resultSet.getString(Field.PATRONYMIC));
        enrollee.setCertificate_score(resultSet.getInt(Field.CERTIFICATE_SCORE));
        enrollee.setIs_banned(resultSet.getBoolean(Field.IS_BANNED));
        enrollee.setLevel(resultSet.getString(Field.LEVEL));
        enrollee.setCertificate_path(resultSet.getString(Field.CERTIFICATE_PATH));
        return enrollee;
    }
}
