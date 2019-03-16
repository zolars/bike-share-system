package layout;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import application.Main;

public class FuncPanelBikes extends JPanel {
    private static final long serialVersionUID = 1L;

    private Image img = new ImageIcon("./src/images/map.jpg").getImage();

    public FuncPanelBikes() {
        this.setName("Bikes");
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setSize(size);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setLayout(null);

        JLabel numA = new JLabel(
                "<html><body><p align=\"center\">Bikes in A : 438<br/>Bikes in B : 239<br/>Bikes in C : 23</p></body></html>",
                JLabel.CENTER);
        numA.setBounds(20, getBounds().height * 2 / 3, 200, getBounds().height / 3);
        numA.setFont(new java.awt.Font("Dialog", 1, 25));
        numA.setOpaque(false);
        numA.setForeground(Color.RED);
        add(numA);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                numA.setBounds(20, getBounds().height * 2 / 3, 200, getBounds().height * 4 / 21);
            }
        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        Dimension size = this.getParent().getSize();
        g.drawImage(img, 0, 0, size.width, size.height, this);
    }

    public static void main(String[] args) {
        Main.setup();
    }
}