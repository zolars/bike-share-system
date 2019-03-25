package layout;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.AbstractAction;
import javax.swing.table.*;

import application.*;

/**
 * FuncPanelTest
 * 
 * @author Xin Yifei
 * @version 0.8
 */
public class FuncPanelTest extends JPanel {
    private static final long serialVersionUID = 1L;

    private Image img = new ImageIcon(getClass().getResource("/images/Plain.jpg")).getImage();

    JScrollPane sPane = new JScrollPane();

    public FuncPanelTest() {
        this.setName("Test");

        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setSize(size);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);

        sPane.setPreferredSize(new Dimension(getBounds().width, getBounds().height));
        sPane.setBounds(0, 30, 30, 30);
        DefaultTableModel model = new DefaultTableModel(new Integer[][] { { 1, 2 }, { 3, 4 } },
                new String[] { "A", "B" });
        JTable table = new JTable(model);
        sPane.getViewport().add(table);

        add(sPane);
    }

    @Override
    public void updateUI() {
        super.updateUI();
        try {
            sPane.setPreferredSize(new Dimension(getBounds().width * 8 / 10, getBounds().height * 8 / 10));
        } catch (Exception e) {
            e.printStackTrace();
        }
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