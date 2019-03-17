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
    public static LinkedList<JPanel> funcSet = new LinkedList<JPanel>();

    public static void setup() {
        funcSet.add(new FuncPanelBikes());
        funcSet.add(new FuncPanelMsg());
        funcSet.add(new FuncPanelMyAccount());
        funcSet.add(new FuncPanelSetting());
        new MainLayout(funcSet);

    }

    public static void main(String[] args) {
        setup();
    }
}