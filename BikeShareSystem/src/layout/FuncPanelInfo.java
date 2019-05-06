package layout;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.*;

import application.*;

/**
 * FuncPanelInfo
 * 
 * @author Xin Yifei
 * @version 0.9
 */
public class FuncPanelInfo extends FuncPanelDefault {
    private static final long serialVersionUID = 1L;

    public FuncPanelInfo() {
        super();
        setName("Info");
        add(new JLabel("Info Page..."));
    }

    @Override
    public void updateUI() {
        super.updateUI();
        // Data update
    }

    public static void main(String[] args) {
        MainAdmin.setup();
    }
}