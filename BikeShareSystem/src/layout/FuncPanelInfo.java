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
public class FuncPanelInfo extends JPanel {
    private static final long serialVersionUID = 1L;

    private Image img = new ImageIcon(getClass().getResource("/images/Plain.jpg")).getImage();

    public FuncPanelInfo() {
        setName("Info");

        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setSize(size);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);

        add(new JLabel("Info Page..."));
    }

    @Override
    public void updateUI() {
        super.updateUI();
        // Data update
    }

    @Override
    protected void paintComponent(Graphics g) {
        Dimension size = getParent().getSize();
        g.drawImage(img, 0, 0, size.width, size.height, this);
    }

    public static void main(String[] args) {
        MainAdmin.setup();
    }
}