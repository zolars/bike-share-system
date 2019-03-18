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
 * @version 0.6
 */
public class BikesDaoImpl extends BaseDao implements BikesDao {
    public List<Bikes> findBikes(String station) throws IOException {
        List<Bikes> result = new ArrayList<Bikes>();

        List<String[]> resultStr = search("bikes.txt", station, 0);
        for (String[] data : resultStr) {
            Bikes bikes = new Bikes();
            bikes.setStation(data[0]);
            bikes.setNumber(Integer.parseInt(data[1]));
            result.add(bikes);
        }

        return result;
    }

    public static void main(String[] args) {
        BikesDao dao = new BikesDaoImpl();
        try {
            System.out.println(dao.findBikes("B").get(0).getNumber());
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}