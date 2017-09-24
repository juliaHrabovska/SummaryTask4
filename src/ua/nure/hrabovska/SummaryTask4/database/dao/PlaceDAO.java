package ua.nure.hrabovska.SummaryTask4.database.dao;

import org.apache.log4j.Logger;
import ua.nure.hrabovska.SummaryTask4.database.DBManager;
import ua.nure.hrabovska.SummaryTask4.database.Field;
import ua.nure.hrabovska.SummaryTask4.database.entity.Place;
import ua.nure.hrabovska.SummaryTask4.exception.DBException;
import ua.nure.hrabovska.SummaryTask4.exception.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Interacts with database. Operates Place data
 *
 * @author Y. Hrabovska
 */
public class PlaceDAO {

    private static final Logger LOG = Logger.getLogger(PlaceDAO.class);

    private static final String GET_ALL_PLACES = "SELECT * FROM place ORDER BY place.name";

    private Connection connection;

    /**
     * Get places
     *
     * @return List<Place>
     * @throws DBException
     */
    public List<Place> getAllPlaces() throws DBException {
        List<Place> placeList = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBManager.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(GET_ALL_PLACES);
            while (resultSet.next()) {
                placeList.add(extract(resultSet));
            }
        } catch (SQLException e) {
            LOG.error(Message.CANNOT_FIND_PLACE, e);
            throw new DBException(Message.CANNOT_FIND_PLACE, e);
        } finally {
            DBManager.closeStatement(statement);
            DBManager.closeResultSet(resultSet);
            DBManager.closeConnection(connection);
        }
        return placeList;
    }

    /**
     * Extracts a Genre object from result set
     *
     * @param resultSet Result set from which genre will be extract
     * @return Genre object
     * @throws SQLException
     */
    private Place extract(ResultSet resultSet) throws SQLException {
        Place place = new Place();
        place.setId(resultSet.getLong(Field.ID));
        place.setName(resultSet.getString(Field.NAME));
        return place;
    }
}
