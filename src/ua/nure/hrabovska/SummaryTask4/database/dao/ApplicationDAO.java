package ua.nure.hrabovska.SummaryTask4.database.dao;

import org.apache.log4j.Logger;
import ua.nure.hrabovska.SummaryTask4.database.DBManager;
import ua.nure.hrabovska.SummaryTask4.database.Field;
import ua.nure.hrabovska.SummaryTask4.database.bean.ApplicationBean;
import ua.nure.hrabovska.SummaryTask4.database.bean.SubmittedAppBean;
import ua.nure.hrabovska.SummaryTask4.database.entity.Application;
import ua.nure.hrabovska.SummaryTask4.exception.DBException;
import ua.nure.hrabovska.SummaryTask4.exception.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Interacts with database. Operates Application data
 *
 * @author Y. Hrabovska
 */
public class ApplicationDAO {

    private static final Logger LOG = Logger.getLogger(ApplicationDAO.class);
    private static final String GET_APPLICATION_BEAN_BY_CATHEDRA_ID =
            "SELECT e.id, e.first_name, e.last_name, e.patronymic, a.competitive_score, s.name AS status " +
                    "FROM enrollee AS e " +
                    "   INNER JOIN " +
                    "        (application AS a " +
                    "           INNER JOIN " +
                    "               status AS s " +
                    "           ON a.status_id = s.id " +
                    "           INNER JOIN " +
                    "               cathedra " +
                    "           ON a.cathedra_id = cathedra.id " +
                    "        ) " +
                    "        ON a.enrollee_id = e.id " +
                    "        WHERE cathedra.id = ? " +
                    "        ORDER BY a.competitive_score DESC";

    private static final String GET_LIST_RESULTS =
            "SELECT DISTINCT ex.name AS exam, res.mark AS mark  " +
                    "FROM results AS res " +
                    "   LEFT JOIN  " +
                    "       exam AS ex " +
                    "   ON ex.id = res.exam_id " +
                    "   WHERE " +
                    "res.enrollee_id = ?  " +
                    "           AND  " +
                    "        ex.name IN ( " +
                    "            SELECT ex.name AS exam " +
                    "               FROM requirements LEFT JOIN exam " +
                    "                ON requirements.exam_id = exam.id " +
                    "               WHERE requirements.cathedra_id = ? " +
                    "               )";
    private static final String GET_SUBMETTED_APP_BEAN_BY_ENROLLEE_ID = "SELECT s.name AS status, u.name AS university,  " +
            "       tt.name AS type_of_training, d.name AS department,  " +
            "       c.name AS cathedra, c.id AS cathedra_id " +
            "    FROM application AS a " +
            "        INNER JOIN  " +
            "            status AS s " +
            "            ON a.status_id = s.id " +
            "        INNER JOIN  " +
            "            ( " +
            "                cathedra AS c " +
            "                    INNER JOIN " +
            "                        ( " +
            "                            department AS d " +
            "                                INNER JOIN " +
            "                                    university AS u " +
            "                                    ON d.university_id = u.id " +
            "                        ) " +
            "                        ON c.department_id = d.id " +
            "                    INNER JOIN " +
            "                        type_of_training AS tt " +
            "                        ON c.type_of_training_id = tt.id " +
            "            ) " +
            "            ON a.cathedra_id = c.id " +
            "            WHERE enrollee_id = ?";

    private static final String DELETE_APP = "DELETE FROM application " +
            "WHERE enrollee_id=? AND cathedra_id=?";

    private static final String CREATE_APPLICATION =
            "INSERT INTO application (enrollee_id, cathedra_id, competitive_score, status_id) " +
                    "VALUES (?, ?, ?, 1)";

    private static final String GET_APPLICATION =
            "SELECT * FROM application " +
                    "       WHERE application.enrollee_id = ? " +
                    "           AND " +
                    "             application.cathedra_id = ?";

    private static final String UPDATE_APPLICATION = "UPDATE application SET status_id=? WHERE enrollee_id=? AND cathedra_id=?";

    private Connection connection;

    /**
     * Updates Application
     *
     * @param application - application
     * @return true if isUpdated, false - otherwise
     * @throws DBException
     */
    public boolean updateApplication(Application application) throws DBException {
        PreparedStatement statment = null;
        boolean isUpdated = false;
        try {
            connection = DBManager.getConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            statment = connection.prepareStatement(UPDATE_APPLICATION);
            statment.setInt(1, application.getStatus_id());
            statment.setLong(2, application.getEnrollee_id());
            statment.setLong(3, application.getCathedra_id());
            if (statment.executeUpdate() != 0) {
                isUpdated = true;
            }
            connection.commit();
            isUpdated = true;
        } catch (SQLException e) {
            DBManager.rollback(connection);
            LOG.error(Message.CANNOT_UPDATE_APPLICATION, e);
            throw new DBException(Message.CANNOT_UPDATE_APPLICATION, e);
        } finally {
            DBManager.closeStatement(statment);
            DBManager.closeConnection(connection);
        }
        return isUpdated;

    }

    /**
     * Get application by enrollee and cathedra ids
     *
     * @param enrollee_id - enrollee's id
     * @param cathedra_id - cathedra's id
     * @return Application object
     * @throws DBException
     */
    public Application getByEnrolleeCathedraIds(Long enrollee_id, long cathedra_id) throws DBException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Application application = new Application();
        try {
            connection = DBManager.getConnection();
            statement = connection.prepareStatement(GET_APPLICATION);
            statement.setLong(1, enrollee_id);
            statement.setLong(2, cathedra_id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                application = extractApplication(resultSet);
            }
        } catch (SQLException e) {
            LOG.error(Message.CANNOT_GET_APPLICATION, e);
            throw new DBException(Message.CANNOT_GET_APPLICATION, e);
        } finally {
            DBManager.closeStatement(statement);
            DBManager.closeResultSet(resultSet);
            DBManager.closeConnection(connection);
        }
        return application;
    }

    /**
     * Create new application in database
     *
     * @param cathedra_id - cathedra's id
     * @param enrollee_id - enrollee's id
     * @param score       - score of enrollee
     * @return true if operation was successful
     * @throws DBException
     */
    public boolean createApp(long cathedra_id, Long enrollee_id, int score) throws DBException {
        boolean result = true;
        PreparedStatement statement = null;
        try {
            connection = DBManager.getConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            statement = connection.prepareStatement(CREATE_APPLICATION);
            statement.setLong(1, enrollee_id);
            statement.setLong(2, cathedra_id);
            statement.setInt(3, score);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            DBManager.rollback(connection);
            LOG.error(Message.CANNOT_CREATE_APPLICATION, e);
            result = false;
            throw new DBException(Message.CANNOT_CREATE_APPLICATION, e);
        } finally {
            DBManager.closeStatement(statement);
            DBManager.closeConnection(connection);
        }
        return result;
    }

    /**
     * Delete app from database
     *
     * @param cathedra_ids list - id of cathedra
     * @param enrollee_id  - id of enrollee
     * @return true if operation complete successful, false - otherwise
     */
    public boolean deleteApp(Long[] cathedra_ids, long enrollee_id) throws DBException {
        PreparedStatement statement = null;
        boolean isDeleted = false;
        int count = 0;
        try {
            connection = DBManager.getConnection();

            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            statement = connection.prepareStatement(DELETE_APP);
            for (Long cathedra_id : cathedra_ids) {
                statement.setLong(1, enrollee_id);
                statement.setLong(2, cathedra_id);

                if (statement.executeUpdate() != 0) {
                    count++;
                }
            }
            if (count == cathedra_ids.length) {
                isDeleted = true;
            }
        } catch (SQLException e) {
            DBManager.rollback(connection);
            LOG.error(Message.CANNOT_DELETE_APP, e);
            throw new DBException(Message.CANNOT_DELETE_APP, e);
        } finally {
            DBManager.closeStatement(statement);
            DBManager.closeConnection(connection);
        }
        return isDeleted;
    }

    /**
     * Get SubmittedAppBeanList
     *
     * @param enrollee_id - enrollee_id
     * @return SubmitAppBean List
     * @throws DBException
     */
    public List<SubmittedAppBean> getSubmittedAppBeanByEnrollee_id(long enrollee_id) throws DBException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<SubmittedAppBean> submittedAppBeans = new ArrayList<>();
        try {
            connection = DBManager.getConnection();
            statement = connection.prepareStatement(GET_SUBMETTED_APP_BEAN_BY_ENROLLEE_ID);
            statement.setLong(1, enrollee_id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                submittedAppBeans.add(extractSubmittedApplicationBean(resultSet));
            }
        } catch (SQLException ex) {
            LOG.error(Message.CANNOT_OBTAIN_SUBMITTED_APPLICATION_BEANS, ex);
            throw new DBException(Message.CANNOT_OBTAIN_SUBMITTED_APPLICATION_BEANS, ex);
        } finally {
            DBManager.closeStatement(statement);
            DBManager.closeResultSet(resultSet);
            DBManager.closeConnection(connection);
        }
        return submittedAppBeans;
    }

    /**
     * Get ApplicationBeanList
     *
     * @param cathedra_id - cathedra id
     * @return ApplicationBean List
     * @throws DBException
     */
    public List<ApplicationBean> getApplicationBeanByCathedra_id(long cathedra_id) throws DBException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<ApplicationBean> applicationBeanList = new ArrayList<>();
        try {
            connection = DBManager.getConnection();
            statement = connection.prepareStatement(GET_APPLICATION_BEAN_BY_CATHEDRA_ID);
            statement.setLong(1, cathedra_id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                applicationBeanList.add(extractApplicationBean(resultSet));
            }
        } catch (SQLException ex) {
            LOG.error(Message.CANNOT_OBTAIN_APPLICATION_BEANS, ex);
            throw new DBException(Message.CANNOT_OBTAIN_APPLICATION_BEANS, ex);
        } finally {
            DBManager.closeStatement(statement);
            DBManager.closeResultSet(resultSet);
            DBManager.closeConnection(connection);
        }
        return applicationBeanList;
    }

    /**
     * Get list results
     *
     * @param cathedra_id - cathedra id
     * @return Map<String,Integer> - exam name, mark
     * @throws DBException
     */
    public Map<String, Integer> getListResults(long enrollee_id, long cathedra_id) throws DBException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, Integer> results = new HashMap<>();
        try {
            connection = DBManager.getConnection();
            statement = connection.prepareStatement(GET_LIST_RESULTS);
            statement.setLong(1, enrollee_id);
            statement.setLong(2, cathedra_id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                results.put(resultSet.getString(Field.EXAM_NAME), resultSet.getInt(Field.EXAM_MARK));
            }
        } catch (SQLException ex) {
            LOG.error(Message.CANNOT_GET_REQUIREMENTS, ex);
            throw new DBException(Message.CANNOT_GET_REQUIREMENTS, ex);
        } finally {
            DBManager.closeStatement(statement);
            DBManager.closeResultSet(resultSet);
            DBManager.closeConnection(connection);
        }
        return results;
    }

    /**
     * Extract an Application object from the result set
     *
     * @param resultSet - result set from an applicationBean will be extract
     * @return application object
     * @throws SQLException
     */
    private Application extractApplication(ResultSet resultSet)
            throws SQLException {
        Application bean = new Application();
        bean.setEnrollee_id(resultSet.getLong(Field.ENROLLEEID));
        bean.setCathedra_id(resultSet.getLong(Field.CATHEDRA_ID));
        bean.setStatus_id(resultSet.getInt(Field.STATUS_ID));
        return bean;
    }

    /**
     * Extract an ApplicationBean object from the result set
     *
     * @param resultSet - result set from an applicationBean will be extract
     * @return applicationBean object
     * @throws SQLException
     */
    private ApplicationBean extractApplicationBean(ResultSet resultSet)
            throws SQLException {
        ApplicationBean bean = new ApplicationBean();
        bean.setEnrollee_id(resultSet.getInt(Field.ID));
        bean.setEnrollee_first_name(resultSet.getString(Field.FIRST_NAME));
        bean.setEnrollee_last_name(resultSet.getString(Field.LAST_NAME));
        bean.setEnrollee_patronymic(resultSet.getString(Field.PATRONYMIC));
        bean.setCompetitive_score(resultSet.getInt(Field.COMPETITIVE_SCORE));
        bean.setStatus(resultSet.getString(Field.STATUS));
        return bean;
    }

    /**
     * Extract a SubmittedAppBean object from the result set
     *
     * @param resultSet - result set from a submittedAppBean will be extract
     * @return submittedAppBean object
     * @throws SQLException
     */
    private SubmittedAppBean extractSubmittedApplicationBean(ResultSet resultSet) throws SQLException {
        SubmittedAppBean bean = new SubmittedAppBean();
        bean.setStatus(resultSet.getString(Field.STATUS));
        bean.setUniversity(resultSet.getString(Field.UNIVERSITY));
        bean.setType_of_training(resultSet.getString(Field.TYPE));
        bean.setDepartment(resultSet.getString(Field.DEPARTMENT));
        bean.setCathedra(resultSet.getString(Field.CATHEDRA));
        bean.setCathedra_id(resultSet.getLong(Field.CATHEDRA_ID));
        return bean;
    }
}
