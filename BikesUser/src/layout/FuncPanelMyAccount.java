package layout;

import javax.swing.*;

import application.*;

/**
 * FuncPanelMyAccount
 * 
 * @author Xin Yifei
 * @version 0.5
 */
public class FuncPanelMyAccount extends JPanel {
    private static final long serialVersionUID = 1L;

    public FuncPanelMyAccount() {
        this.setName("MyAccount");

    }

    public static void main(String[] args) {
        MainUser.setup();
    }
}