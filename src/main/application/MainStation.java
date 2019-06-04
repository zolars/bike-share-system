package application;

import java.util.LinkedList;
import javax.swing.JPanel;
import layout.FuncPanelStation;
import layout.MainLayout;

/**
 * MainStation
 *
 * @author Xin Yifei
 * @version 1.0
 */
public class MainStation {

    public static LinkedList<JPanel> funcSet = new LinkedList<JPanel>();
    public static boolean restart = false;
    public static String station = null;

    public static void setup() {
        funcSet = new LinkedList<JPanel>();
        funcSet.add(new FuncPanelStation());
        new MainLayout(funcSet);
    }

    public static void main(String[] args) {
        setup();
    }
}
