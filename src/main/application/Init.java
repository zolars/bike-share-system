package application;

import java.io.*;

import database.entity.*;

/**
 * Init
 * 
 * @author Xin Yifei
 * @version 0.9
 */
public class Init {
    public static void main(String[] args) {
        String filePath = Main.getFilepath();
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
                    if (fileName.equals("account.txt")) {
                        BufferedWriter writter = new BufferedWriter(new FileWriter(filePath + fileName));
                        String str = "admin admin admin 0";
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