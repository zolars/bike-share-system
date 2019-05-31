package database.dao;

import java.io.*;
import java.util.*;

import database.entity.*;

/**
 * BikesDao
 * 
 * @author Xin Yifei
 * @version 1.0
 */
public interface BikesDao {
	
	/**
	* <p>Description: Get all bikes stored in database.</p>
	* @return All bikes
	* @throws IOException Input and output exception
	*/
    public List<Bikes> findAllBikes() throws IOException;

    /**
	* <p>Description: Get the numbers of bikes docked in a certain station.</p>
	* @param station A certain station
	* @return The number of bikes
	* @throws IOException Input and output exception
	*/
    public int findBikesNumberByStation(String station) throws IOException;

    /**
   	* <p>Description: Change the number of bikes docked in a certain station </p>
   	* @param bikes An object of Bikes whose station will be changed
   	* @throws IOException Input and output exception
   	*/
    public void changeBikesByStation(Bikes bikes) throws IOException;
}