package layout;

import javax.swing.*;

import application.Main;

/**
 * FuncPanelSetting
 * 
 * @author Xin Yifei
 * @version 0.5
 */
public class FuncPanelSetting extends JPanel {
    private static final long serialVersionUID = 1L;

    public FuncPanelSetting() {
        this.setName("Setting");
        this.add(new JLabel("Setting Page..."));
    }

    public static void main(String[] args) {
        Main.setup();
    }
}