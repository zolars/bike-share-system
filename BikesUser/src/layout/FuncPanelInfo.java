package layout;

import javax.swing.*;

import application.Main;

/**
 * FuncPanelInfo
 * 
 * @author Xin Yifei
 * @version 0.5
 */
public class FuncPanelInfo extends JPanel {
    private static final long serialVersionUID = 1L;

    public FuncPanelInfo() {
        this.setName("Info");
        this.add(new JLabel("Info Page..."));
    }

    public static void main(String[] args) {
        Main.setup();
    }
}