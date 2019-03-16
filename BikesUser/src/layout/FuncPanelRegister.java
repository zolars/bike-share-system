package layout;

import javax.swing.*;

import application.Main;

/**
 * FuncPanelRegister
 * 
 * @author Xin Yifei
 * @version 0.1
 */
public class FuncPanelRegister extends JPanel {
    private static final long serialVersionUID = 1L;

    public FuncPanelRegister() {
        this.setName("Register");
        this.add(new JLabel("Register Page..."));
    }

    public static void main(String[] args) {
        Main.setup();
    }
}