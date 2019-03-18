package application;

import java.util.*;
import javax.swing.*;

import layout.*;

/**
 * MainUser
 * 
 * @author Xin Yifei
 * @version 0.6
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
            funcSetLogin.add(new FuncPanelLogin());
            new MainLayout(funcSetLogin);
        } else {
            funcSet.add(new FuncPanelBikes());
            funcSet.add(new FuncPanelMsg());
            funcSet.add(new FuncPanelHistory());
            funcSet.add(new FuncPanelMyAccount());
            new MainLayout(funcSet);
        }

    }

    public static void main(String[] args) {
        setup();
    }
}