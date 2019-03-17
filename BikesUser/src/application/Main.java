package application;

import java.util.*;
import javax.swing.*;

import layout.*;

/**
 * Main
 * 
 * @author Xin Yifei
 * @version 0.5
 */
public class Main {

    public static final double version = 0.5;
    public static LinkedList<JPanel> funcSet = new LinkedList<JPanel>();

    public static void adminSetup() {
        funcSet.add(new FuncPanelRegister());
        funcSet.add(new FuncPanelInfo());
        funcSet.add(new FuncPanelMsg());
        new MainLayout(funcSet);
    }

    public static void userSetup() {
        funcSet.add(new FuncPanelBikes());
        funcSet.add(new FuncPanelMsg());
        funcSet.add(new FuncPanelMyAccount());
        funcSet.add(new FuncPanelSetting());
        new MainLayout(funcSet);
    }

    public static void setup() {
        userSetup();
    }

    public static void main(String[] args) {
        setup();
    }
}