package ua.nure.hrabovska.SummaryTask4.database.dao;

import org.apache.log4j.Logger;
import ua.nure.hrabovska.SummaryTask4.database.DBManager;
import ua.nure.hrabovska.SummaryTask4.database.Field;
import ua.nure.hrabovska.SummaryTask4.database.entity.Department;
import ua.nure.hrabovska.SummaryTask4.exception.DBException;
import ua.nure.hrabovska.SummaryTask4.exception.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Interacts with database. Operates Department data
 *
 * @author Y. Hrabovska
 */
public class DepartmentDAO {

    private static final Logger LOG = Logger.getLogger(CathedraDAO.class);

    private static final String GET_ALL_PLACES = "SELECT * FROM department WHERE university_id=?";

    private Connection connection;

    /**
     * Get departments
     *
     * @return List<Department>
     * @throws DBException
     */
    public List<Department> getAllDepartments(long university_id) throws DBException {
        List<Department> departmentList = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBManager.getConnection();
            statement = connection.prepareStatement(GET_ALL_PLACES);
            statement.setLong(1, university_id);
            resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                departmentList.add(extract(resultSet));
            }
            
        } catch (SQLException e) {
            LOG.error(Message.CANNOT_FIND_DEPARTMENT, e);
            throw new DBException(Message.CANNOT_FIND_DEPARTMENT, e);
        } finally {
            DBManager.closeStatement(statement);
            DBManager.closeResultSet(resultSet);
            DBManager.closeConnection(connection);
        }
        return departmentList;
    }

    /**
     * Extracts a Department object from result set
     *
     * @param resultSet Result set from which department will be extract
     * @return Department object
     * @throws SQLException
     */
    private Department extract(ResultSet resultSet) throws SQLException {
        Department place = new Department();
        place.setId(resultSet.getLong(Field.ID));
        place.setName(resultSet.getString(Field.NAME));
        return place;
    }
}
