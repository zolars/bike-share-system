package application;

import java.io.*;

import database.entity.Bikes;

/**
 * Init
 * 
 * @author Xin Yifei
 * @version 0.8
 */
public class Init {

    public static void main(String[] args) {
        String filePath = System.getProperty("user.home") + "\\Documents\\BikeShareSystemFile\\";
        String[] fileNames = Main.fileNames;
        for (String fileName : fileNames) {
            File fp = new File(filePath);
            File f = new File(filePath + fileName);
            try {
                if (!fp.exists()) {
                    fp.mkdir();
                }
                if (!f.exists()) {
                    f.createNewFile();
                    if (fileName.equals("bikes.txt")) {
                        BufferedWriter writter = new BufferedWriter(new FileWriter(filePath + fileName));
                        String str = new String();
                        for (Bikes bikes : Main.bikeStationList)
                            str += bikes.toString() + "\n";
                        writter.write(str.toString());
                        writter.close();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}