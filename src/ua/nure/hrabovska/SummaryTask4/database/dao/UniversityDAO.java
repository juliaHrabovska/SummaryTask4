package ua.nure.hrabovska.SummaryTask4.database.dao;

import org.apache.log4j.Logger;
import ua.nure.hrabovska.SummaryTask4.database.DBManager;
import ua.nure.hrabovska.SummaryTask4.database.Field;
import ua.nure.hrabovska.SummaryTask4.database.entity.University;
import ua.nure.hrabovska.SummaryTask4.exception.DBException;
import ua.nure.hrabovska.SummaryTask4.exception.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UniversityDAO {

    private static final Logger LOG = Logger.getLogger(UniversityDAO.class);

    private static final String GET_BY_PLACE_ID = "SELECT * FROM university WHERE university.place_id=? ORDER BY university.name";

    private Connection connection;

    /**
     * Get university by place_id
     *
     * @param place_id
     * @return List<Place>
     * @throws DBException
     */
    public List<University> getByPlace_id(long place_id) throws DBException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<University> universityList = new ArrayList<>();
        try {
            connection = DBManager.getConnection();
            statement = connection.prepareStatement(GET_BY_PLACE_ID);
            statement.setLong(1, place_id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                universityList.add(extract(resultSet));
            }
        } catch (SQLException e) {
            LOG.error(Message.CANNOT_GET_UNIVERSITY_BY_PLACE_ID, e);
            throw new DBException(Message.CANNOT_GET_UNIVERSITY_BY_PLACE_ID, e);
        } finally {
            DBManager.closeStatement(statement);
            DBManager.closeResultSet(resultSet);
            DBManager.closeConnection(connection);
        }
        return universityList;
    }

    /**
     * Extracts a Genre object from result set
     *
     * @param resultSet Result set from which genre will be extract
     * @return Genre object
     * @throws SQLException
     */
    private University extract(ResultSet resultSet) throws SQLException {
        University university = new University();
        university.setId(resultSet.getLong(Field.ID));
        university.setName(resultSet.getString(Field.NAME));
        university.setPlace_id(resultSet.getLong(Field.PLACE_ID));
        System.out.println(university);
        return university;
    }
}
