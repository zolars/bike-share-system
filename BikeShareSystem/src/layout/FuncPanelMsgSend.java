package layout;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import application.*;
import database.dao.MsgDao;
import database.dao.impl.MsgDaoImpl;

/**
 * FuncPanelMsgSend
 * 
 * @author Xin Yifei
 * @version 0.9
 */
public class FuncPanelMsgSend extends FuncPanelDefault implements ActionListener {
    private static final long serialVersionUID = 1L;

    private JTextField jtUserID;
    private JTextArea jtMsgText;
    private JButton jbSend = new JButton("Send !");

    public FuncPanelMsgSend() {
        super();
        setName("Send Message");
        setLayout(new GridLayout(8, 1, 0, 0));

        JPanel panel01 = new JPanel();
        JPanel panel02 = new JPanel();
        JPanel panel03 = new JPanel();
        JPanel panel04 = new JPanel();
        JPanel panel05 = new JPanel();
        JPanel panel06 = new JPanel();
        // JPanel panel07 = new JPanel();
        // JPanel panel08 = new JPanel();

        JLabel jlSend = new JLabel("Please input the message to send :");
        jlSend.setFont(new java.awt.Font("Dialog", 1, 25));
        panel03.add(jlSend);

        // UserID input
        JLabel jlUserID = new JLabel("UserID : ");
        jlUserID.setFont(new java.awt.Font("Dialog", 1, 25));
        panel04.add(jlUserID);
        jtUserID = new JTextField(15);
        panel04.add(jtUserID);

        jtMsgText = new JTextArea(2, 30);
        jtMsgText.setBorder(BorderFactory.createLineBorder(Color.gray, 3));
        jtMsgText.setText("Please input your message here...");
        panel05.add(jtMsgText);

        // add Action Listener
        jbSend.addActionListener(this);

        panel06.add(jbSend);

        panel01.setOpaque(false);
        panel02.setOpaque(false);
        panel03.setOpaque(false);
        panel04.setOpaque(false);
        panel05.setOpaque(false);
        panel06.setOpaque(false);
        add(panel01);
        add(panel02);
        add(panel03);
        add(panel04);
        add(panel05);
        add(panel06);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbSend) {
            if (jtUserID.getText().isEmpty() || jtMsgText.getText().isEmpty()
                    || jtMsgText.getText().equals("Please input your message here...")) {
                JOptionPane.showMessageDialog(null, "Invalid Input! Please try again.", "Sorry",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                try {
                    MsgDao dao = new MsgDaoImpl();
                    if (dao.addOtherMsg(jtUserID.getText(), jtMsgText.getText())) {
                        JOptionPane.showMessageDialog(null, "Congratulations! Send Successfully!", "Congratulations!",
                                JOptionPane.WARNING_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "This account doesn't exist. Please try again.", "Sorry",
                                JOptionPane.WARNING_MESSAGE);
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            jtUserID.setText("");
            jtMsgText.setText("Please input your message here...");
        }
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