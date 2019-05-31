package application;

import java.util.*;
import javax.swing.*;

import layout.*;

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