package ua.nure.hrabovska.SummaryTask4.database.dao;

import org.apache.log4j.Logger;
import ua.nure.hrabovska.SummaryTask4.database.DBManager;
import ua.nure.hrabovska.SummaryTask4.database.Field;
import ua.nure.hrabovska.SummaryTask4.database.bean.CathedraBean;
import ua.nure.hrabovska.SummaryTask4.exception.DBException;
import ua.nure.hrabovska.SummaryTask4.exception.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Interacts with database. Operates Cathedra data
 *
 * @author Y. Hrabovska
 */
public class CathedraDAO {

    private static final Logger LOG = Logger.getLogger(CathedraDAO.class);

    private static final String GET_CATHEDRA_BEAN_BY_UNIVER_ID = "SELECT c.id, c.name, lv.budget AS budget," +
            "lv.contract AS contract," +
            "lt.name AS level_of_training," +
            "d.name AS department," +
            "tt.name AS type_of_training " +
            "FROM cathedra AS c " +
            "INNER JOIN licensed_volume AS lv " +
            "ON c.licensed_volume_id = lv.id " +
            "INNER JOIN level_of_training AS lt " +
            "ON c.level_of_training_id = lt.id " +
            "INNER JOIN department AS d " +
            "ON c.department_id = d.id " +
            "INNER JOIN type_of_training AS tt " +
            "ON c.type_of_training_id = tt.id " +
            "WHERE d.university_id=?";

    private static final String GET_LIST_REQUIREMENTS = "SELECT exam.name AS exam " +
            "FROM requirements LEFT JOIN exam " +
            "ON requirements.exam_id = exam.id " +
            "WHERE requirements.cathedra_id = ?";

    private static final String GET_CONTEST = "SELECT COUNT(1) AS contest " +
            "FROM application " +
            "WHERE " +
            "   cathedra_id = ? " +
            "AND" +
            "   status_id = ?";
    private static final String UPDATE_CATHEDRA = "UPDATE cathedra SET name=? WHERE id=?";
    private static final String UPDATE_VOLUME = "UPDATE licensed_volume SET budget=?, contract=? " +
            "WHERE id=(SELECT licensed_volume_id FROM cathedra WHERE id=?)";
    
    private static final String DELETE_CATHEDRA = "DELETE FROM cathedra WHERE id=?";

    private Connection connection;

    /**
     * Delete cathedra from database
     *
     * @param cathedra_ids list - id of cathedra
     * @return true if operation complete successful, false - otherwise
     */
    public boolean deleteCathdera(String[] cathedra_ids) throws DBException {
        PreparedStatement statement = null;
        boolean isDeleted = false;
        int count = 0;
        try {
            connection = DBManager.getConnection();

            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            statement = connection.prepareStatement(DELETE_CATHEDRA);
            for (String cathedra_id : cathedra_ids) {
                statement.setLong(1, Long.parseLong(cathedra_id));

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
     * Updates cathedra in the database
     *
     * @param cathedra_name cathedra_name
     * @param budget - budget places
     * @param contract - contract places
     * @param cathedra_id - cathdra_id
     * @return true if user was updated, false otherwise
     * @throws DBException
     */
    public boolean changeCathedra(String cathedra_name, int budget, int contract, long cathedra_id) throws DBException {
        PreparedStatement statement = null;
        boolean isUpdated = false;
        int count = 0;
        try {
            connection = DBManager.getConnection();

            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            statement = connection.prepareStatement(UPDATE_CATHEDRA);
            statement.setString(1, cathedra_name);
            statement.setLong(2, cathedra_id);

            if (statement.executeUpdate() != 0) {
                count++;
            }
            statement = connection.prepareStatement(UPDATE_VOLUME);
            statement.setInt(1, budget);
            statement.setInt(2, contract);
            statement.setLong(3, cathedra_id);
            if (statement.executeUpdate() != 0) {
                count++;
            }

            if (count == 2) {
                isUpdated = true;
            }

            connection.commit();
        } catch (SQLException e) {
            DBManager.rollback(connection);
            LOG.error(e.getMessage(), e);
            throw new DBException(Message.CANNOT_UPDATE_CATHEDRA, e);
        } finally {
            DBManager.closeStatement(statement);
            DBManager.closeConnection(connection);
        }
        return isUpdated;

    }

    /**
     * Get CathedraBeanList
     *
     * @param university_id - university id
     * @return CathedraBean List
     * @throws DBException
     */
    public List<CathedraBean> getCathedraBeanByUniver_id(long university_id) throws DBException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<CathedraBean> cathedraBeanList = new ArrayList<>();
        try {
            connection = DBManager.getConnection();
            statement = connection.prepareStatement(GET_CATHEDRA_BEAN_BY_UNIVER_ID);
            statement.setLong(1, university_id);
            resultSet = statement.executeQuery();

            PreparedStatement preparedStatement = null;
            ResultSet resultSet1 = null;
            int i = 0;
            while (resultSet.next()) {

                cathedraBeanList.add(extractCathedraBean(resultSet));

                List<String> requirements = new ArrayList<>();
                preparedStatement = connection.prepareStatement(GET_LIST_REQUIREMENTS);
                preparedStatement.setLong(1, cathedraBeanList.get(i).getId());
                resultSet1 = preparedStatement.executeQuery();
                while (resultSet1.next()) {
                    requirements.add(extractRequirements(resultSet1));
                }
                cathedraBeanList.get(i).setRequirements(requirements);

                int contest = 0;
                preparedStatement = connection.prepareStatement(GET_CONTEST);
                preparedStatement.setLong(1, cathedraBeanList.get(i).getId());
                preparedStatement.setInt(2, 1);
                resultSet1 = preparedStatement.executeQuery();
                if (resultSet1.next()) {
                    contest = resultSet1.getInt(Field.CONTEST);
                }
                cathedraBeanList.get(i).setStatement(contest);

                preparedStatement.setLong(1, cathedraBeanList.get(i).getId());
                preparedStatement.setInt(2, 2);
                resultSet1 = preparedStatement.executeQuery();
                if (resultSet1.next()) {
                    contest = resultSet1.getInt(Field.CONTEST);
                }
                cathedraBeanList.get(i).setRecommended(contest);

                preparedStatement.setLong(1, cathedraBeanList.get(i).getId());
                preparedStatement.setInt(2, 3);
                resultSet1 = preparedStatement.executeQuery();
                if (resultSet1.next()) {
                    contest = resultSet1.getInt(Field.CONTEST);
                }
                cathedraBeanList.get(i).setEnlisted(contest);

                i++;
            }
            DBManager.closeStatement(preparedStatement);
            DBManager.closeResultSet(resultSet1);
        } catch (SQLException ex) {
            LOG.error(Message.CANNOT_OBTAIN_CATHEDRA_BEANS, ex);
            throw new DBException(Message.CANNOT_OBTAIN_CATHEDRA_BEANS, ex);
        } finally {
            DBManager.closeStatement(statement);
            DBManager.closeResultSet(resultSet);
            DBManager.closeConnection(connection);
        }
        return cathedraBeanList;
    }

    private String extractRequirements(ResultSet resultSet) throws SQLException {
        return resultSet.getString(Field.EXAM_NAME);
    }

    /**
     * Extract a CathedraBean object from the result set
     *
     * @param resultSet - result set from a cathedraBean will be extract
     * @return cathedraBean object
     * @throws SQLException
     */
    private CathedraBean extractCathedraBean(ResultSet resultSet)
            throws SQLException {
        CathedraBean bean = new CathedraBean();
        bean.setId(resultSet.getLong(Field.ID));
        bean.setName(resultSet.getString(Field.NAME));
        bean.setLicensed_volume_budget(resultSet.getInt(Field.BUDGET));
        bean.setLicensed_volume_contract(resultSet
                .getInt(Field.CONTRACT));
        bean.setLevel_of_training(resultSet
                .getString(Field.LEVEL));
        bean.setDepartment_name(resultSet
                .getString(Field.DEPARTMENT));
        bean.setType_of_training(resultSet
                .getString(Field.TYPE));
        bean.setLicensed_volume(bean.getLicensed_volume_budget(),
                bean.getLicensed_volume_contract());
        return bean;
    }
}
