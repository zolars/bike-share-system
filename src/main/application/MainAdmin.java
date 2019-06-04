package application;

import java.util.LinkedList;
import javax.swing.JPanel;
import layout.FuncPanelAccounts;
import layout.FuncPanelBikes;
import layout.FuncPanelInfo;
import layout.FuncPanelRegister;
import layout.MainLayout;

/**
 * MainAdmin
 *
 * @author Xin Yifei
 * @version 1.0
 */
public class MainAdmin {

    public static LinkedList<JPanel> funcSet = new LinkedList<JPanel>();
    public static boolean restart = false;

    public static void setup() {
        funcSet.add(new FuncPanelBikes());
        funcSet.add(new FuncPanelInfo());
        // funcSet.add(new FuncPanelFeedback());
        funcSet.add(new FuncPanelAccounts());
        funcSet.add(new FuncPanelRegister());

        new MainLayout(funcSet);
    }

    public static void main(String[] args) {
        setup();
    }
}
