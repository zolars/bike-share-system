package database.dao;

import java.io.*;
import java.util.*;

import database.entity.*;

/**
 * BikesDao
 * 
 * @author Xin Yifei
 * @version 0.6
 */
public interface BikesDao {
    public List<Bikes> findBikesByStation(String station) throws IOException;
}