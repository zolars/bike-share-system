package layout;

import application.Main;
import application.MainStation;
import database.dao.AccountDao;
import database.dao.BikesDao;
import database.dao.MsgDao;
import database.dao.RecordDao;
import database.dao.impl.AccountDaoImpl;
import database.dao.impl.BikesDaoImpl;
import database.dao.impl.MsgDaoImpl;
import database.dao.impl.RecordDaoImpl;
import database.entity.Account;
import database.entity.Bikes;
import database.entity.Msg;
import database.entity.Record;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * FuncPanelStation
 *
 * @author Xin Yifei
 * @version 1.0
 */
public class FuncPanelStation extends FuncPanelDefault implements ActionListener {

    private static final long serialVersionUID = 1L;

    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private String userID;

    private JLabel text1;
    private JLabel text2;
    private JButton btn;

    private JFrame frameLight;
    private List<JButton> lights = new ArrayList<JButton>();
    private Date lightTime;

    public FuncPanelStation() {
        super();
        setName("Station");
        setLayout(null);

        if (MainStation.station == null) {
            Bikes[] bikes = Main.bikeStationList;
            Object[] obj = new Object[bikes.length];
            for (int i = 0; i < bikes.length; i++) {
                obj[i] = bikes[i].getStation();
            }

            while (true) {
                String station = (String) JOptionPane.showInputDialog(null,
                        "Please choose the station name of this " + "PC:\n",
                        "Bike Share System - Station", JOptionPane.PLAIN_MESSAGE, new ImageIcon(),
                        obj, "");
                MainStation.station = station;
                if (MainStation.station == null) {
                    continue;
                } else {
                    break;
                }
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

        for (int i = 0; i < 8; i++) {
            lights.add(new JButton());
        }
        addFrameLight();

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                text1.setBounds(getBounds().width / 2 - text1.getText().length() * 35 / 2,
                        getBounds().height / 3 - 60 / 3, text1.getText().length() * 35, 60);
                text2.setBounds(getBounds().width / 2 - text2.getText().length() * 21 / 4,
                        getBounds().height / 3 - 30 / 3 + 130, text2.getText().length() * 11, 30);
                btn.setBounds(getBounds().width / 2 - 30, getBounds().height * 3 / 4 - 30 * 3 / 4,
                        60, 30);
            }
        });
    }

    public void actionPerformed(ActionEvent e) {

        RecordDao recordDao = new RecordDaoImpl();
        AccountDao accountDao = new AccountDaoImpl();
        BikesDao bikesDao = new BikesDaoImpl();
        MsgDao msgDao = new MsgDaoImpl();

        if (e.getSource() == btn) {
            try {
                while (true) {
                    userID = JOptionPane.showInputDialog(null,
                            "Input your ID number (not null) : \n", "Check ID",
                            JOptionPane.PLAIN_MESSAGE);
                    Date date = recordDao.isUserForbidden(userID);
                    Account account = accountDao.findAccountByUserID(userID);

                    if (bikesDao.findBikesNumberByStation(MainStation.station) == 0
                            && recordDao.findRecordNotend(userID).size() == 0) {
                        JOptionPane.showMessageDialog(null, "This station has no bike left : (",
                                "Sorry", JOptionPane.WARNING_MESSAGE);
                        break;
                    } else if (userID.equals("")) {
                        continue;
                    } else if (accountDao.findAccountByUserID(userID) == null) {
                        JOptionPane.showMessageDialog(null,
                                "Invalid ID card. Please contact the administer.", "Sorry",
                                JOptionPane.WARNING_MESSAGE);
                        break;
                    } else if (recordDao.findRecordNotend(userID).size() >= 1) {
                        returnBikes(userID);
                        break;
                    } else if (date.getTime() != 0) {
                        text1.setText("Time overrun in 24h : (");
                        text2.setText("You can use the bikes until " + sf.format(date));
                        btn.setEnabled(false);
                        MainStation.restart = true;
                        break;
                    } else if (account.isFine()) {
                        Object[] choices = {"Pay", "Cancel"};
                        int choiceNum = (int) JOptionPane.showOptionDialog(null,
                                "Until you can use the bikes, you "
                                        + "still have 100BCD as fine to pay.",
                                "Payment", JOptionPane.YES_NO_CANCEL_OPTION,
                                JOptionPane.INFORMATION_MESSAGE, null, choices, choices[0]);
                        if (choiceNum == 0) {
                            // Pay for the fine
                            account.setFine(false);
                            accountDao.modifyAccount(account);

                            text1.setText("Your slot is unlocked : )");
                            text2.setText("Remember giving the bike back at one of parking areas!");
                            btn.setEnabled(false);
                            adjustFrameLight(
                                    bikesDao.findBikesNumberByStation(MainStation.station) - 1);
                            break;
                        } else {
                            text1.setText("Please pay for the fine first : (");
                            text2.setText("You can use the bikes until you have paid for the fine");
                            btn.setEnabled(false);
                            MainStation.restart = true;
                            break;
                        }
                    } else {
                        text1.setText("Your slot is unlocked : )");
                        text2.setText("Remember giving the bike back at one of parking areas!");
                        btn.setEnabled(false);
                        adjustFrameLight(
                                bikesDao.findBikesNumberByStation(MainStation.station) - 1);
                        break;
                    }
                }

            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else {
            try {
                if (recordDao.findRecordNotend(userID).size() >= 1) {

                    Msg overdueMsg = null;
                    try {
                        overdueMsg = msgDao.findMsgOverdue(userID).get(0);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }

                    // delete overdue msg & check the bill
                    if (overdueMsg != null) {
                        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                        Record record = recordDao.findRecordOverdue(userID).get(0);

                        String startDateStr = sf.format(record.getStartDate());
                        String endDateStr = sf.format(new Date());

                        msgDao.deleteMsg(
                                String.valueOf(msgDao.findMsgOverdue(userID).get(0).getMsgID()));
                        msgDao.addOtherMsg(userID, "Your ride from " + startDateStr + " to "
                                + endDateStr + " costs " + "100BCD. * "
                                + "Attention : The scooter must be returned within 30 " + "minutes"
                                + " and the "
                                + "total usage must not exceed 2 hours a day, otherwise a fine "
                                + "should be " + "issued" + ".");
                    }

                    // return bikes
                    text1.setText("Thank you for returning : )");
                    bikesDao.changeBikesByStation(new Bikes(MainStation.station,
                            bikesDao.findBikesNumberByStation(MainStation.station) + 1));
                    recordDao.addNewReturn(userID);
                } else {
                    // borrow bikes
                    text1.setText("Thank you for borrowing : )");
                    bikesDao.changeBikesByStation(new Bikes(MainStation.station,
                            bikesDao.findBikesNumberByStation(MainStation.station) - 1));
                    recordDao.addNewBorrow(userID, new Date());
                }

            } catch (IOException e1) {
                e1.printStackTrace();
            }
            lightTime.setTime(0);
            frameLight.dispose();
            text2.setText("If you meet any questions, connect with the administer.");
            MainStation.restart = true;
        }
    }

    private void returnBikes(String userID) {
        BikesDao bikesDao = new BikesDaoImpl();

        try {
            // change viewer
            text1.setText("We need your feedback : )");
            text2.setText("If your bike needs to repair, please tell us!");
            btn.setEnabled(false);

            // feedback(userID);

            adjustFrameLight(bikesDao.findBikesNumberByStation(MainStation.station));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void feedback(String userID) {
        MsgDao msgDao = new MsgDaoImpl();

        boolean mark = false;
        while (true) {
            String notice;
            if (mark) {
                notice = "Thank you for your feedback.\nIs there any other malfunction on this "
                        + "bicycle?";
            } else {
                notice = "Is there any malfunction on this bicycle?";
            }
            Object[] choices = {"No, thanks", "Yes"};
            int choiceNum = (int) JOptionPane.showOptionDialog(null, notice, "Feedback",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, choices,
                    choices[0]);

            if (choiceNum == 0) {
                break;
            } else {
                Object[] obj = {"1  Lock", "2  Brake", "3  Footlice", "4  Chain", "5  Handlebar",
                        "6  Wheels"};
                String choiceStr = (String) JOptionPane.showInputDialog(null,
                        "Please choose the place of " + "malfunction" + " :\n", "Feedback",
                        JOptionPane.QUESTION_MESSAGE, new ImageIcon(), obj, "");

                try {
                    msgDao.addOtherMsg("admin", "Malfunction : " + choiceStr.split("  ")[1]
                            + " from station " + MainStation.station);
                    msgDao.addOtherMsg(userID,
                            "Dear user, your feedback about \"" + choiceStr.split("  ")[1] + "\" "
                                    + "has been " + "received."
                                    + " If you have any other questions, connect the administer.");

                } catch (Exception e) {
                    e.printStackTrace();
                }
                mark = true;
            }
        }
    }

    private void addFrameLight() {
        frameLight = new JFrame();
        frameLight.setLayout(new GridLayout(2, 4, 10, 10));
        frameLight.setBounds(500, 200, 300, 300);
        frameLight.setMinimumSize(new Dimension(500, 350));
        frameLight.setTitle("Light Panel");
        frameLight.setLocationRelativeTo(null);
        frameLight.setVisible(false);
        frameLight.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frameLight.dispose();
                MainStation.restart = true;
            }
        });
        for (int i = 0; i < 8; i++) {
            frameLight.add(lights.get(i));
        }
    }

    private void adjustFrameLight(int bikeNum) {
        lightTime = new Date();
        frameLight.setVisible(true);

        for (int i = 0; i < bikeNum; i++) {
            lights.get(i).setText("existed");
            lights.get(i).setBackground(Color.RED);
        }

        lights.get(bikeNum).setText("unlocked");
        lights.get(bikeNum).addActionListener(this);

        for (int i = bikeNum + 1; i < 8; i++) {
            lights.get(i).setText("empty");
            lights.get(i).setBackground(Color.GREEN);
        }

        frameLight.validate();
    }

    @Override
    public void updateUI() {
        super.updateUI();

        AccountDao accountDao = new AccountDaoImpl();
        BikesDao bikesDao = new BikesDaoImpl();
        RecordDao recordDao = new RecordDaoImpl();
        MsgDao msgDao = new MsgDaoImpl();
        try {
            int bikeNum = bikesDao.findBikesNumberByStation(MainStation.station);
            if (bikeNum == 0) {
                text1.setText("This station has no bike left : (");
                text2.setText("You can go to another station in order to use the bikes.");
            }

            // refresh the bikes' num
            if (text1.getText().charAt(0) == 'S') {
                text1.setText("Station " + MainStation.station + " - " + bikeNum + " Bikes Left");
            }

            // update overdue status
            List<Record> overdueRecords = recordDao.findRecordOverdue();
            if (overdueRecords != null) {
                for (Record overdueRecord : overdueRecords) {
                    msgDao.addOverdueMsg(overdueRecord);
                }
            }

            // add weekMsg
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            date.setTime(Calendar.getInstance().getTime().getTime() - 1000);
            Calendar pastCalendar = Calendar.getInstance();
            pastCalendar.setTime(date);
            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY
                    && pastCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                String weekMsg = new String();
                List<Account> accounts = accountDao.findAccountAll();
                for (Account account : accounts) {
                    List<Record> records = recordDao.findRecordAll(account.getUserID());
                    long allDuration = 0;
                    weekMsg = "";
                    for (Record record : records) {
                        pastCalendar = Calendar.getInstance();
                        date.setTime(
                                Calendar.getInstance().getTime().getTime() - 7 * 24 * 3600 * 1000);
                        pastCalendar.setTime(date);
                        if (record.getStartDate().after(pastCalendar.getTime())) {
                            if (record.getDuration() > 0) {
                                allDuration += record.getDuration() / 1000;
                            }
                            if (record.isFine()) {
                                weekMsg = "There is still fine to pay for you in last week. ";
                            }
                        }
                    }
                    weekMsg += "Your ride time of last week is " + allDuration + ". ";
                    weekMsg = "";
                    msgDao.addOtherMsg(account.getUserID(), weekMsg);
                    weekMsg = "";
                }
            }

            if (lightTime.getTime() != 0
                    && ((new Date()).getTime() - lightTime.getTime()) / 1000 > Main.lightInterval) {
                lightTime.setTime(0);
                frameLight.dispose();
                text1.setText("Your operation is overdue : (");
                text2.setText("Please be hurry! The lock will close after one minute.");
                MainStation.restart = true;
            }

            // Make the light blingbling
            Calendar cal = Calendar.getInstance();
            for (int i = 0; i < 8; i++) {
                if (lights.get(i).getText().equals("unlocked")) {
                    if (cal.get(Calendar.SECOND) % 2 == 0) {
                        lights.get(i).setBackground(Color.RED);
                    } else {
                        lights.get(i).setBackground(Color.GREEN);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();


        }
    }

    public static void main(String[] args) {
        MainStation.setup();
    }
}
