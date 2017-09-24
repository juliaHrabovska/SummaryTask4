package ua.nure.hrabovska.SummaryTask4.database.dao;

import org.apache.log4j.Logger;
import ua.nure.hrabovska.SummaryTask4.database.DBManager;
import ua.nure.hrabovska.SummaryTask4.database.Field;
import ua.nure.hrabovska.SummaryTask4.database.entity.Exam;
import ua.nure.hrabovska.SummaryTask4.exception.DBException;
import ua.nure.hrabovska.SummaryTask4.exception.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExamDAO {

    private static final Logger LOG = Logger.getLogger(ExamDAO.class);

    private static final String GET_EXAM_BY_LEVEL = "SELECT DISTINCT e.name AS exam, e.id AS id " +
            "    FROM requirements AS r " +
            "        LEFT JOIN " +
            "            exam AS e  " +
            "            ON r.exam_id = e.id " +
            "            WHERE r.cathedra_id IN ( " +
            "                SELECT id " +
            "                FROM cathedra " +
            "                    WHERE level_of_training_id=?" +
            "                )";
    private static final String GET_EXAM_BY_ID = "SELECT e.name AS exam, e.id AS id " +
            "FROM exam AS e WHERE id=?";

    private Connection connection;


    /**
     * Get exam list by id
     *
     * @param exams - array of exam's ids
     * @return List of exams
     * @throws DBException
     */
    public List<Exam> getListByIds(String[] exams) throws DBException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Exam> result = new ArrayList<>();
        try {
            connection = DBManager.getConnection();
            for (String exam : exams) {
                statement = connection.prepareStatement(GET_EXAM_BY_ID);
                statement.setLong(1, Long.parseLong(exam));
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    result.add(extract(resultSet));
                }
            }
        } catch (SQLException e) {
            LOG.error(Message.CANNOT_GET_EXAM_BY_LEVEL, e);
            throw new DBException(Message.CANNOT_GET_EXAM_BY_LEVEL, e);
        } finally {
            DBManager.closeStatement(statement);
            DBManager.closeResultSet(resultSet);
            DBManager.closeConnection(connection);
        }
        return result;
    }

    /**
     * Get exam list by level of training
     *
     * @param level_id - level of training id
     * @return List of exams object
     * @throws DBException
     */
    public List<Exam> getByLevel_id(long level_id) throws DBException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Exam> exams = new ArrayList<>();
        try {
            connection = DBManager.getConnection();
            statement = connection.prepareStatement(GET_EXAM_BY_LEVEL);
            statement.setLong(1, level_id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                exams.add(extract(resultSet));
            }
        } catch (SQLException e) {
            LOG.error(Message.CANNOT_GET_EXAM_BY_LEVEL, e);
            throw new DBException(Message.CANNOT_GET_EXAM_BY_LEVEL, e);
        } finally {
            DBManager.closeStatement(statement);
            DBManager.closeResultSet(resultSet);
            DBManager.closeConnection(connection);
        }
        return exams;
    }

    /**
     * Extract a SubmittedAppBean object from the result set
     *
     * @param resultSet - result set from a submittedAppBean will be extract
     * @return submittedAppBean object
     * @throws SQLException
     */
    private Exam extract(ResultSet resultSet) throws SQLException {
        Exam exam = new Exam();
        exam.setId(resultSet.getLong(Field.ID));
        exam.setName(resultSet.getString(Field.EXAM_NAME));
        return exam;
    }
}
