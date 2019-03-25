package database.dao.impl;

import java.io.*;
import java.util.*;

import database.*;
import database.dao.*;
import database.entity.*;

/**
 * BikesDaoImpl
 * 
 * @author Xin Yifei
 * @version 0.9
 */
public class BikesDaoImpl implements BikesDao {
    public List<Bikes> findAllBikes() throws IOException {
        List<Bikes> result = new ArrayList<Bikes>();
        for (String[] resultStr : BaseDao.search("bikes.txt", "", 0)) {
            result.add(new Bikes(resultStr[0], Integer.parseInt(resultStr[1])));
        }
        return result;
    }

    public int findBikesNumberByStation(String station) throws IOException {
        String[] result = BaseDao.search("bikes.txt", station, 0).get(0);
        return Integer.parseInt(result[1]);
    }

    public void changeBikesByStation(Bikes bikes) throws IOException {
        BaseDao.replace("bikes.txt", bikes.getStation(), 0, bikes.toString());
    }

    public static void main(String[] args) {
    }
}