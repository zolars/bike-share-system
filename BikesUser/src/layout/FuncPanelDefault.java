package layout;

import javax.swing.*;

import application.Main;

/**
 * FuncPanelDefault
 * 
 * @author Xin Yifei
 * @version 0.5
 */
public class FuncPanelDefault extends JPanel {
    private static final long serialVersionUID = 1L;

    public FuncPanelDefault() {
        this.setName("Default");
        this.add(new JLabel("Default Page..."));
    }

    public static void main(String[] args) {
        Main.setup();
    }
}