package application;

import java.util.*;
import javax.swing.*;

import layout.*;

/**
 * MainAdmin
 * 
 * @author Xin Yifei
 * @version 0.9
 */
public class MainAdmin {

    public static LinkedList<JPanel> funcSet = new LinkedList<JPanel>();
    public static Boolean restart = false;

    public static void setup() {

        funcSet.add(new FuncPanelInfo());
        funcSet.add(new FuncPanelRegister());
        funcSet.add(new FuncPanelFeedback());
        funcSet.add(new FuncPanelMsgSend());
        // funcSet.add(new FuncPanelTest());
        new MainLayout(funcSet);
    }

    public static void main(String[] args) {
        setup();
    }
}