package layout;

import javax.swing.*;

import application.*;

/**
 * FuncPanelHistory
 * 
 * @author Xin Yifei
 * @version 0.8
 */
public class FuncPanelHistory extends JPanel {
    private static final long serialVersionUID = 1L;

    public FuncPanelHistory() {
        this.setName("History");
        this.add(new JLabel("History Page..."));

    }

    public static void main(String[] args) {
        MainUser.setup();
    }
}