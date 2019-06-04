package database;

import application.Main;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * BaseDAO
 *
 * @author Xin Yifei
 * @version 1.0
 */
public class BaseDao {

    private static final String filePath = Main.getFilepath();

    private static synchronized boolean getConnection(String fileName) {
        File file = new File(filePath + fileName);
        return file.exists();
    }

    /**
     * Find data from fileName with the keyword in index. When keyword="", return all data.
     *
     * @author Xin Yifei
     * @version 1.0
     */
    public static List<String[]> search(String fileName, String keyword, int index)
            throws IOException {
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

    public static void replace(String fileName, String keyword, int index, String newString)
            throws IOException {
        StringBuffer buf = new StringBuffer();
        if (getConnection(fileName)) {
            File file = new File(filePath + fileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String lineString = new String();
            while ((lineString = reader.readLine()) != null) {
                String data[] = lineString.split(" ");
                if (data[index].equals(keyword) && (!newString.equals(""))) {
                    buf.append(newString);
                    buf.append(System.getProperty("line.separator"));
                } else if (data[index].equals(keyword) && newString.equals("")) {

                } else {
                    buf.append(lineString);
                    buf.append(System.getProperty("line.separator"));
                }
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

    public static int dataAmount(String fileName, String keyword, int index) throws IOException {
        int count = -1;
        if (getConnection(fileName)) {
            count++;
            File file = new File(filePath + fileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String lineString = new String();
            while ((lineString = reader.readLine()) != null) {
                String data[] = lineString.split(" ");
                if (data[index].equals(keyword) || keyword.equals("")) {
                    count++;
                }
            }
            reader.close();
        } else {
            System.out.println("The file \"" + fileName + "\" doesn't exist.");
        }
        return count;
    }

    public static void main(String[] args) {
        try {
            replace("msg.txt", "3", 0, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
