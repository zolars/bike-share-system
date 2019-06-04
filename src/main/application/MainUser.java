package application;

import java.util.LinkedList;
import javax.swing.JPanel;
import layout.FuncPanelBikes;
import layout.FuncPanelHistory;
import layout.FuncPanelMsgRec;
import layout.MainLayout;

/**
 * MainUser
 *
 * @author Xin Yifei
 * @version 1.0
 */
public class MainUser {

    public static LinkedList<JPanel> funcSetLogin = new LinkedList<JPanel>();
    public static LinkedList<JPanel> funcSet = new LinkedList<JPanel>();
    public static boolean restart = false;
    public static String loginStatus = "123";

    public static void setup() {

        funcSetLogin = new LinkedList<JPanel>();
        funcSet = new LinkedList<JPanel>();

        if (loginStatus == null) {
            funcSetLogin.add(new FuncPanelBikes());
            new MainLayout(funcSetLogin);
        } else {
            funcSet.add(new FuncPanelBikes());
            funcSet.add(new FuncPanelMsgRec());
            funcSet.add(new FuncPanelHistory());
            // funcSet.add(new FuncPanelAccounts());
            new MainLayout(funcSet);
        }

    }

    public static void main(String[] args) {
        setup();
    }
}
