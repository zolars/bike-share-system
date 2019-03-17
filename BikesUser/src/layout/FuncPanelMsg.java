package layout;

import javax.swing.*;

import application.Main;

/**
 * FuncPanelMsg
 * 
 * @author Xin Yifei
 * @version 0.5
 */
public class FuncPanelMsg extends JPanel {
    private static final long serialVersionUID = 1L;

    public FuncPanelMsg() {
        this.setName("Msg");
        this.add(new JLabel("Msg Page..."));
    }

    public static void main(String[] args) {
        Main.setup();
    }
}