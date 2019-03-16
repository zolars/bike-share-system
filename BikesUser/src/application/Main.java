package application;

import java.util.*;
import javax.swing.*;

import layout.*;

/**
 * Main
 * 
 * @author Xin Yifei
 * @version 0.1
 */
public class Main {

    public static final double version = 0.1;
    public static List<JPanel> funcSet = new LinkedList<JPanel>();

    public static void setup() {
        try {
            char isAdmin;
            while (true) {
                System.out.print("Please choose your user type:\nAre you a administer? (Y/N):");
                // isAdmin = (char) System.in.read();
                isAdmin = 'N';
                if (isAdmin == 'Y' || isAdmin == 'y') {
                    funcSet.add(new FuncPanelRegister());
                    funcSet.add(new FuncPanelInfo());
                    funcSet.add(new FuncPanelMsg());
                    break;
                } else if (isAdmin == 'N' || isAdmin == 'n') {
                    funcSet.add(new FuncPanelBikes());
                    funcSet.add(new FuncPanelMsg());
                    funcSet.add(new FuncPanelMyAccount());
                    funcSet.add(new FuncPanelSetting());
                    break;
                } else {
                    System.out.println("Wrong input. Please try again.");
                    continue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        new MainLayout();
    }

    public static void main(String[] args) {
        setup();
    }
}