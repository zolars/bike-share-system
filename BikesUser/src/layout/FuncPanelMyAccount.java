package layout;

import javax.swing.*;

import application.Main;

/**
 * FuncPanelMyAccount
 * 
 * @author Xin Yifei
 * @version 0.1
 */
public class FuncPanelMyAccount extends JPanel {
    private static final long serialVersionUID = 1L;

    public FuncPanelMyAccount() {
        this.setName("MyAccount");
        this.add(new JLabel("MyAccount Page..."));
    }

    public static void main(String[] args) {
        Main.setup();
    }
}