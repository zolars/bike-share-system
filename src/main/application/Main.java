package application;

import database.entity.Bikes;

/**
 * Main * @author Xin Yifei
 *
 * @version 1.0
 */
public class Main {

    public static final double version = 1.0;
    public static final String[] fileNames = {"bikes.txt", "account.txt", "record.txt", "msg.txt"};

    public static final int freshInterval = 1000; // in ms

    public static final Bikes[] bikeStationList =
            {new Bikes("A", 5), new Bikes("B", 5), new Bikes("C", 5)};

    public static final int overdueTime_Once = 30; // in minutes
    public static final int overdueTime_All = 120; // in minutes
    public static final int lightInterval = 60; // in s

    public static String getFilepath() {
        String env = System.getProperty("os.name");
        if (env.split(" ")[0].equals("Windows")) {
            return ".\\database\\";
        } else if (env.equals("Linux")) {
            return "./database/";
        } else if (env.split(" ")[0].equals("Mac")) {
            return "./database/";
        } else {
            return ".\\database\\";
        }
    }
}
