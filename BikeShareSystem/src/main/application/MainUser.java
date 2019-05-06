package application;

import java.util.*;
import javax.swing.*;

import layout.*;

/**
 * MainUser
 * 
 * @author Xin Yifei
 * @version 0.9
 */
public class MainUser {

    public static LinkedList<JPanel> funcSetLogin = new LinkedList<JPanel>();
    public static LinkedList<JPanel> funcSet = new LinkedList<JPanel>();
    public static Boolean restart = false;
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
            funcSet.add(new FuncPanelAccounts());
            new MainLayout(funcSet);
        }

    }

    public static void main(String[] args) {
        setup();
    }
}