package layout;

import javax.swing.*;

import application.*;

/**
 * FuncPanelMsg
 * 
 * @author Xin Yifei
 * @version 0.6
 */
public class FuncPanelMsg extends JPanel {
    private static final long serialVersionUID = 1L;

    public FuncPanelMsg() {
        this.setName("Msg");
        this.add(new JLabel("Station Page..."));
    }

    public static void main(String[] args) {
        MainUser.setup();
    }
}