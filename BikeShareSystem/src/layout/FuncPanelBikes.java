package layout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import application.*;
import database.dao.*;
import database.dao.impl.*;
import database.entity.*;

/**
 * FuncPanelBikes
 * 
 * @author Xin Yifei
 * @version 0.8
 */
public class FuncPanelBikes extends JPanel {
    private static final long serialVersionUID = 1L;

    private Image img = new ImageIcon(getClass().getResource("/images/map.jpg")).getImage();
    private JLabel text;

    public FuncPanelBikes() {
        setName("Bikes");
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setSize(size);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setLayout(null);

        text = new JLabel("", JLabel.CENTER);
        text.setFont(new java.awt.Font("Dialog", 1, 25));
        text.setOpaque(false);
        text.setForeground(Color.RED);
        text.setText("Loading...");
        add(text);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                text.setBounds(20, getBounds().height * 2 / 3, 200, getBounds().height * 4 / 21);
            }
        });
    }

    @Override
    public void updateUI() {
        super.updateUI();
        BikesDao dao = new BikesDaoImpl();
        try {
            Bikes a = dao.findBikesByStation("A").get(0);
            Bikes b = dao.findBikesByStation("B").get(0);
            Bikes c = dao.findBikesByStation("C").get(0);
            text.setText("<html><body><p align=\"center\">Bikes in A :" + a.getNumber() + "<br/>Bikes in B :"
                    + b.getNumber() + "<br/>Bikes in C :" + c.getNumber() + "</p></body></html>");
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