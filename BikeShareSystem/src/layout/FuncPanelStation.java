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
 * @version 0.8
 */
public class FuncPanelStation extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;

    private Image img = new ImageIcon(getClass().getResource("/images/Plain.jpg")).getImage();

    private RecordDao recordDao = new RecordDaoImpl();
    private AccountDao accountDao = new AccountDaoImpl();

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
                        getBounds().height / 3 - 30 / 3 + 130, text1.getText().length() * 28, 30);
                btn.setBounds(getBounds().width / 2 - 30, getBounds().height * 3 / 4 - 30 * 3 / 4, 60, 30);
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
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
                        recordDao.addNewReturn(userID);
                        text1.setText("Your bike is returned : )");
                        text2.setText("If your bike needs to repair, please tell us!");
                        MainStation.restart = true;
                        break;
                    } else {
                        recordDao.addNewBorrow(userID, new Date());
                        MainStation.restart = true;
                        text1.setText("Your bike is unlocked : )");
                        text2.setText("Remember giving the bike back at one of parking areas!");
                        btn.setEnabled(false);
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

        RecordDao recordDao = new RecordDaoImpl();

        try {
            List<Record> recordOverdue = new ArrayList<Record>();
            recordOverdue = recordDao.findRecordOverdue("");
            if (recordOverdue != null) {
                for (Record record : recordOverdue)
                    System.out.println(record.toString());
            }
        } catch (IOException e) {
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