package database;

import java.io.*;
import java.util.*;

/**
 * BaseDAO
 * 
 * @author Xin Yifei
 * @version 0.6
 */
public class BaseDao {

    private static final String filePath = System.getProperty("user.home") + "\\Documents\\BikeShareSystemFile\\";

    public static boolean getConnection(String fileName) {
        File file = new File(filePath + fileName);
        return file.exists();
    }

    /**
     * Find data from fileName with the keyword in index. When keyword="", return
     * all data.
     * 
     * @author Xin Yifei
     * @version 0.6
     */
    public static List<String[]> search(String fileName, String keyword, int index) throws IOException {
        List<String[]> dataSet = new ArrayList<String[]>();

        if (getConnection(fileName)) {
            File file = new File(filePath + fileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String lineString = new String();
            while ((lineString = reader.readLine()) != null) {
                String data[] = lineString.split(" ");
                if (data[index].equals(keyword) || keyword.equals("")) {
                    dataSet.add(data);
                }
            }
            reader.close();
        } else {
            System.out.println("The file \"" + fileName + "\" doesn't exist.");
        }
        return dataSet;
    }

    public static void addLine(String fileName, String newString) throws IOException {
        if (getConnection(fileName)) {
            StringBuffer buf = new StringBuffer();
            if (getConnection(fileName)) {
                File file = new File(filePath + fileName);
                BufferedReader reader = new BufferedReader(new FileReader(file));

                String lineString = new String();
                while ((lineString = reader.readLine()) != null) {
                    buf.append(lineString);
                    buf.append(System.getProperty("line.separator"));
                }
                buf.append(newString);
                reader.close();

                BufferedWriter writter = null;

                writter = new BufferedWriter(new FileWriter(filePath + fileName));
                writter.write(buf.toString());

                writter.close();
            } else {
                System.out.println("The file \"" + fileName + "\" doesn't exist.");
            }
        }
    }

    public static void replace(String fileName, String keyword, int index, String newString) throws IOException {
        StringBuffer buf = new StringBuffer();
        if (getConnection(fileName)) {
            File file = new File(filePath + fileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String lineString = new String();
            while ((lineString = reader.readLine()) != null) {
                String data[] = lineString.split(" ");
                if (data[index].equals(keyword)) {
                    buf.append(newString);
                } else {
                    buf.append(lineString);
                }
                buf.append(System.getProperty("line.separator"));
            }
            reader.close();

            BufferedWriter writter = null;

            writter = new BufferedWriter(new FileWriter(filePath + fileName));
            writter.write(buf.toString());

            writter.close();
        } else {
            System.out.println("The file \"" + fileName + "\" doesn't exist.");
        }
    }

    public static void main(String[] args) {
        try {
            BaseDao.replace("test.txt", "3", 0, "8 sdasd");
            BaseDao.addLine("account.txt", "7 gch 123456 123");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}