package application;

import java.util.*;
import javax.swing.*;

import layout.*;

/**
 * MainUser
 * 
 * @author Xin Yifei
 * @version 0.5
 */
public class MainUser {

    public static final double version = 0.5;
    public static LinkedList<JPanel> funcSetLogin = new LinkedList<JPanel>();
    public static LinkedList<JPanel> funcSet = new LinkedList<JPanel>();
    public static String loginStatus = "xin";

    public static void setup() {
        if (loginStatus == null) {
            funcSetLogin.add(new FuncPanelBikes());
            funcSetLogin.add(new FuncPanelLogin());
            new MainLayout(funcSetLogin);
        } else {
            funcSet.add(new FuncPanelBikes());
            funcSet.add(new FuncPanelMsg());
            funcSet.add(new FuncPanelMyAccount());
            funcSet.add(new FuncPanelSetting());
            new MainLayout(funcSet);
        }

    }

    public static void main(String[] args) {
        setup();
    }
}