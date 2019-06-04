package database.dao.impl;

import database.BaseDao;
import database.dao.BikesDao;
import database.entity.Bikes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * BikesDaoImpl
 *
 * @author Xin Yifei
 * @version 1.0
 */
public class BikesDaoImpl implements BikesDao {

    /**
     * <p>Description: Get all bikes stored in database.</p>
     *
     * @return All bikes
     * @throws IOException Input and output exception
     */
    public List<Bikes> findAllBikes() throws IOException {
        List<Bikes> result = new ArrayList<Bikes>();
        for (String[] resultStr : BaseDao.search("bikes.txt", "", 0)) {
            result.add(new Bikes(resultStr[0], Integer.parseInt(resultStr[1])));
        }
        return result;
    }

    /**
     * <p>Description: Get the numbers of bikes docked in a certain station.</p>
     *
     * @param station A certain station
     * @return The number of bikes
     * @throws IOException Input and output exception
     */
    public int findBikesNumberByStation(String station) throws IOException {
        String[] result = BaseDao.search("bikes.txt", station, 0).get(0);
        return Integer.parseInt(result[1]);
    }

    /**
     * <p>Description: Change the number of bikes docked in a certain station </p>
     *
     * @param bikes An object of Bikes whose station will be changed
     * @throws IOException Input and output exception
     */
    public void changeBikesByStation(Bikes bikes) throws IOException {
        BaseDao.replace("bikes.txt", bikes.getStation(), 0, bikes.toString());
    }

    /**
     * <p>Description: A main method.</p>
     *
     * @param args Default
     */
    public static void main(String[] args) {
    }
}
