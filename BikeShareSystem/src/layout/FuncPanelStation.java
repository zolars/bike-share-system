package layout;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import application.*;
import database.dao.*;
import database.dao.impl.*;
import database.entity.*;

/**
 * FuncPanelStation
 * 
 * @author Xin Yifei
 * @version 0.9
 */
public class FuncPanelStation extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;

    private Image img = new ImageIcon(getClass().getResource("/images/Plain.jpg")).getImage();

    private JLabel text1;
    private JLabel text2;
    private JButton btn;

    public FuncPanelStation() {

        setName("Station");

        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setSize(size);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);

        setLayout(null);

        if (MainStation.station == null) {
            Object[] obj = Main.bikeStationList;
            while (true) {
                Bikes bike = (Bikes) JOptionPane.showInputDialog(null, "Please choose the station name of this PC:\n",
                        "Recharge", JOptionPane.PLAIN_MESSAGE, new ImageIcon(), obj, "");
                MainStation.station = bike.getStation();
                if (MainStation.station == null)
                    continue;
                else
                    break;
            }
        }

        text1 = new JLabel("", JLabel.CENTER);
        text1.setFont(new java.awt.Font("Dialog", 1, 50));
        text1.setOpaque(false);
        text1.setForeground(Color.BLACK);
        text1.setText("Shared Bikes System");
        add(text1);

        text2 = new JLabel("", JLabel.CENTER);
        text2.setFont(new java.awt.Font("Dialog", 1, 20));
        text2.setOpaque(false);
        text2.setForeground(Color.BLACK);
        text2.setText("Please use your ID card to unlock the shared bikes");
        add(text2);

        btn = new JButton();
        btn.setText("OK");
        btn.addActionListener(this);
        add(btn);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                text1.setBounds(getBounds().width / 2 - text1.getText().length() * 35 / 2,
                        getBounds().height / 3 - 60 / 3, text1.getText().length() * 35, 60);
                text2.setBounds(getBounds().width / 2 - text2.getText().length() * 21 / 4,
                        getBounds().height / 3 - 30 / 3 + 130, text2.getText().length() * 11, 30);
                btn.setBounds(getBounds().width / 2 - 30, getBounds().height * 3 / 4 - 30 * 3 / 4, 60, 30);
            }
        });
    }

    public void actionPerformed(ActionEvent e) {

        RecordDao recordDao = new RecordDaoImpl();
        AccountDao accountDao = new AccountDaoImpl();
        BikesDao bikesDao = new BikesDaoImpl();

        if (e.getSource() == btn) {
            try {
                while (true) {
                    String userID = JOptionPane.showInputDialog(null, "Input your ID number (not null) : \n",
                            "Check ID", JOptionPane.PLAIN_MESSAGE);
                    if (userID.equals("")) {
                        continue;
                    } else if (accountDao.findAccountByUserID(userID) == null) {
                        JOptionPane.showMessageDialog(this, "Invalid ID card. Please contact the administer.", "Sorry",
                                JOptionPane.WARNING_MESSAGE);
                        break;
                    } else if (recordDao.findRecordNotend(userID).size() >= 1) {
                        bikesDao.changeBikesByStation(new Bikes(MainStation.station,
                                bikesDao.findBikesNumberByStation(MainStation.station) + 1));
                        recordDao.addNewReturn(userID);
                        text1.setText("Your bike is returned : )");
                        text2.setText("If your bike needs to repair, please tell us!");
                        btn.setEnabled(false);

                        boolean mark = false;
                        while (true) {
                            String notice;
                            if (mark) {
                                notice = "Thank you for your feedback.\nIs there any other malfunction on this bicycle?";
                            } else {
                                notice = "Is there any malfunction on this bicycle?";
                            }
                            Object[] choices = { "No, thanks", "Yes" };
                            int choiceNum = (int) JOptionPane.showOptionDialog(null, notice, "Feedback",
                                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, choices,
                                    choices[0]);

                            if (choiceNum == 0) {
                                break;
                            } else {
                                Object[] obj = { "1  Lock", "2  Brake", "3  Foot lice", "4  Chain", "5  Handlebar",
                                        "6  Wheels", "7  Other feedback" };
                                String choiceStr = (String) JOptionPane.showInputDialog(null,
                                        "Please choose the place of malfunction :\n", "Feedback",
                                        JOptionPane.QUESTION_MESSAGE, new ImageIcon(), obj, "");
                                mark = true;
                            }
                        }

                        MainStation.restart = true;
                        break;
                    } else {
                        bikesDao.changeBikesByStation(new Bikes(MainStation.station,
                                bikesDao.findBikesNumberByStation(MainStation.station) - 1));
                        recordDao.addNewBorrow(userID, new Date());
                        text1.setText("Your bike is unlocked : )");
                        text2.setText("Remember giving the bike back at one of parking areas!");
                        btn.setEnabled(false);

                        MainStation.restart = true;
                        break;
                    }
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }

    @Override
    public void updateUI() {
        super.updateUI();

        BikesDao bikesDao = new BikesDaoImpl();
        RecordDao recordDao = new RecordDaoImpl();
        MsgDao msgDao = new MsgDaoImpl();

        try {

            if (text1.getText().charAt(0) == 'S')
                text1.setText("Station " + MainStation.station + " - "
                        + bikesDao.findBikesNumberByStation(MainStation.station) + " Bikes Left");

            List<Record> overdueRecords = new ArrayList<Record>();
            overdueRecords = recordDao.findRecordOverdue("");
            if (overdueRecords != null) {
                for (Record overdueRecord : overdueRecords)
                    msgDao.addOverdueMsg(overdueRecord);
            }
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
        MainStation.setup();
    }
}