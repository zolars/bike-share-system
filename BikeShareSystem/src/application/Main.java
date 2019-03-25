package application;

import database.entity.*;

/**
 * Main * @author Xin Yifei
 * 
 * @version 0.8
 */
public class Main {

    public static final double version = 0.8;
    public static final String[] fileNames = { "bikes.txt", "account.txt", "record.txt", "msg.txt" };
    public static final int overdueTime = 120; // in minutes
    public static final Bikes[] bikeStationList = { new Bikes("A", 5), new Bikes("B", 5), new Bikes("C", 5) };

    public static final int freshInterval = 1000;

}