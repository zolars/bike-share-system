package layout;

import application.Main;
import application.MainUser;
import database.dao.BikesDao;
import database.dao.impl.BikesDaoImpl;
import database.entity.Bikes;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * FuncPanelBikes
 *
 * @author Lu Siyuan
 * @version 1.0
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
                text.setBounds(20, getBounds().height * 2 / 3 - 70, 200,
                        getBounds().height * 4 / 21 + 70);
            }
        });
    }

    @Override
    public void updateUI() {
        super.updateUI();
        BikesDao dao = new BikesDaoImpl();
        try {
            String newText = "<html><body><p align=\"center\">";
            for (Bikes bikes : Main.bikeStationList) {
                int number = dao.findBikesNumberByStation(bikes.getStation());
                newText += "Station " + bikes.getStation() + ": " + number + "<br>";
            }
            text.setText(newText + "</p></body></html>");
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
