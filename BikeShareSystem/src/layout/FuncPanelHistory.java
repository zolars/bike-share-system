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
 * @version 0.8
 */
public class FuncPanelHistory extends JPanel {
    private static final long serialVersionUID = 1L;

    private Image img = new ImageIcon(getClass().getResource("/images/Plain.jpg")).getImage();

    public FuncPanelHistory() {
        this.setName("History");

        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setSize(size);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);

        this.add(new JLabel("History Page..."));
    }

    @Override
    public void updateUI() {
        super.updateUI();
        // Data update
    }

    @Override
    protected void paintComponent(Graphics g) {
        Dimension size = this.getParent().getSize();
        g.drawImage(img, 0, 0, size.width, size.height, this);
    }

    public static void main(String[] args) {
        MainUser.setup();
    }
}