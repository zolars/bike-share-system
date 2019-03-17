package application;

import java.util.*;
import javax.swing.*;

import layout.*;

/**
 * MainAdmin
 * 
 * @author Xin Yifei
 * @version 0.5
 */
public class MainAdmin {

    public static final double version = 0.5;
    public static LinkedList<JPanel> funcSet = new LinkedList<JPanel>();

    public static void setup() {
        funcSet.add(new FuncPanelInfo());
        funcSet.add(new FuncPanelRegister());
        funcSet.add(new FuncPanelMsg());
        new MainLayout(funcSet);
    }

    public static void main(String[] args) {
        setup();
    }
}