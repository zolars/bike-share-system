package application;

import java.util.*;
import javax.swing.*;

import layout.*;

/**
 * MainAdmin
 * 
 * @author Xin Yifei
 * @version 0.8
 */
public class MainAdmin {

    public static LinkedList<JPanel> funcSet = new LinkedList<JPanel>();
    public static Boolean restart = false;

    public static void setup() {

        funcSet.add(new FuncPanelInfo());
        funcSet.add(new FuncPanelRegister());
        // funcSet.add(new FuncPanelMsg());
        funcSet.add(new FuncPanelDefault());
        new MainLayout(funcSet);
    }

    public static void main(String[] args) {
        setup();
    }
}