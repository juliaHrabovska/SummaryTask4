package ua.nure.hrabovska.SummaryTask4.database.dao;

import org.apache.log4j.Logger;
import ua.nure.hrabovska.SummaryTask4.database.DBManager;
import ua.nure.hrabovska.SummaryTask4.database.Field;
import ua.nure.hrabovska.SummaryTask4.database.entity.Enrollee;
import ua.nure.hrabovska.SummaryTask4.exception.DBException;
import ua.nure.hrabovska.SummaryTask4.exception.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Interacts with database. Operates Result data
 *
 * @author Y. Hrabovska
 */
public class ResultDAO {

    private static final Logger LOG = Logger.getLogger(ResultDAO.class);

    private static final String INSERT_RESULT = "INSERT INTO results (exam_id, enrollee_id, mark) VALUES (?, ?, ?)";
    private static final String GET_COUNT_APPROPRIATE_EXAM = "SELECT COUNT(1) AS count " +
            " FROM results " +
            "  WHERE results.exam_id IN ( " +
            "        SELECT exam.id AS exam  " +
            "  FROM exam " +
            "   INNER JOIN  " +
            "            requirements " +
            "            ON requirements.exam_id = exam.id " +
            "  WHERE  " +
            "   requirements.cathedra_id = ?) " +
            "            AND results.enrollee_id = ?";

    private static final String GET_RESULT_BY_ENROLLEE_ID =
            "SELECT r.mark AS mark " +
            "   FROM results AS r " +
            "       WHERE r.enrollee_id = ?";

    private Connection connection;

    /**
     * Insert enrollee's results in database
     *
     * @param enrollee - enrollee
     * @return true - if operation completed successfully
     * @throws DBException
     */
    public boolean insertResult(Map<Long, Integer> results, Enrollee enrollee) throws DBException {
        boolean result = true;
        PreparedStatement statement = null;
        ResultSet resultSet;
        try {
            connection = DBManager.getConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            for (Map.Entry<Long, Integer> pair : results.entrySet()) {
                statement = connection.prepareStatement(INSERT_RESULT);
                statement.setLong(1, pair.getKey());
                statement.setLong(2, enrollee.getId());
                statement.setInt(3, pair.getValue());
                statement.executeUpdate();
            }
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
     * Get count appropriate exams
     *
     * @param cathedra_id - cathedra's id
     * @param enrollee_id - enrollee's id
     * @return List of results
     * @throws DBException
     */
    public int getCountAppropriateExam(long cathedra_id, Long enrollee_id) throws DBException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            connection = DBManager.getConnection();
            statement = connection.prepareStatement(GET_COUNT_APPROPRIATE_EXAM);
            statement.setLong(1, cathedra_id);
            statement.setLong(2, enrollee_id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt(Field.COUNT);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new DBException(e.getMessage(), e);
        } finally {
            DBManager.closeStatement(statement);
            DBManager.closeConnection(connection);
        }
        return count;
    }

    /**
     * Get results list by enrollee's id
     *
     * @param enrollee_id - enrollee's id
     * @return List of results
     * @throws DBException
     */
    public List<Integer> getListResultsByErollee_id(long enrollee_id) throws DBException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Integer> result = new ArrayList<>();
        try {
            connection = DBManager.getConnection();

            statement = connection.prepareStatement(GET_RESULT_BY_ENROLLEE_ID);
            statement.setLong(1, enrollee_id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                result.add(resultSet.getInt(Field.EXAM_MARK));
            }

        } catch (SQLException e) {
            LOG.error(Message.CANNOT_GET_RESULT_BY_ENROLLE_ID, e);
            throw new DBException(Message.CANNOT_GET_RESULT_BY_ENROLLE_ID, e);
        } finally {
            DBManager.closeStatement(statement);
            DBManager.closeResultSet(resultSet);
            DBManager.closeConnection(connection);
        }
        return result;
    }
}
