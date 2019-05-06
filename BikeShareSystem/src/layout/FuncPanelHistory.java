package layout;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.*;

import application.*;

/**
 * FuncPanelHistory
 * 
 * @author Xin Yifei
 * @version 0.9
 */
public class FuncPanelHistory extends FuncPanelDefault {
    private static final long serialVersionUID = 1L;

    public FuncPanelHistory() {
        super();
        setName("History");
        this.add(new JLabel("History Page..."));
    }

    @Override
    public void updateUI() {
        super.updateUI();
        // Data update
    }

    public static void main(String[] args) {
        MainUser.setup();
    }
}