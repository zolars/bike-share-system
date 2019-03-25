package database.dao;

import java.io.*;
import java.util.*;

import database.entity.*;

/**
 * BikesDao
 * 
 * @author Xin Yifei
 * @version 0.9
 */
public interface BikesDao {
    public List<Bikes> findAllBikes() throws IOException;

    public int findBikesNumberByStation(String station) throws IOException;

    public void changeBikesByStation(Bikes bikes) throws IOException;
}