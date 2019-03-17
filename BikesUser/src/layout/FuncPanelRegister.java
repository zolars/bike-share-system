package layout;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

import application.*;
import database.dao.*;
import database.dao.impl.*;
import database.entity.*;

/**
 * FuncPanelRegister
 * 
 * @author Xin Yifei
 * @version 0.5
 */
public class FuncPanelRegister extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JTextField jtUserID;
    private JTextField jtUsername;
    private JPasswordField jtPassword;
    private JTextField jtBalance;
    private JButton jbRegister = new JButton("Register !");

    public FuncPanelRegister() {
        setName("Register");
        setLayout(new GridLayout(8, 1, 0, 0));
        JPanel panel01 = new JPanel();
        JPanel panel02 = new JPanel();
        JPanel panel03 = new JPanel();
        JPanel panel04 = new JPanel();
        JPanel panel05 = new JPanel();
        JPanel panel06 = new JPanel();
        // JPanel panel07 = new JPanel();
        // JPanel panel08 = new JPanel();

        panel02 = new JPanel();
        JLabel jlUserID = new JLabel("User ID : ");
        jlUserID.setFont(new java.awt.Font("Dialog", 1, 25));
        panel02.add(jlUserID);
        jtUserID = new JTextField(15);
        panel02.add(jtUserID);

        panel03 = new JPanel();
        JLabel jlUsername = new JLabel("Username : ");
        jlUsername.setFont(new java.awt.Font("Dialog", 1, 25));
        panel03.add(jlUsername);
        jtUsername = new JTextField(15);
        panel03.add(jtUsername);

        panel04 = new JPanel();
        JLabel jlPassword = new JLabel("Password : ");
        jlPassword.setFont(new java.awt.Font("Dialog", 1, 25));
        panel04.add(jlPassword);
        jtPassword = new JPasswordField(15);
        panel04.add(jtPassword);

        panel05 = new JPanel();
        JLabel jlBalance = new JLabel("Recharge : ");
        jlBalance.setFont(new java.awt.Font("Dialog", 1, 25));
        panel05.add(jlBalance);
        jtBalance = new JTextField(15);
        panel05.add(jtBalance);

        panel06 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // add Action Listener
        jbRegister.addActionListener(this);
        panel06.add(jbRegister);

        add(panel01);
        add(panel02);
        add(panel03);
        add(panel04);
        add(panel05);
        add(panel06);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbRegister) {
            if (jtUserID.getText().isEmpty() || jtUsername.getText().isEmpty() || jtPassword.getText().isEmpty()
                    || (!jtBalance.getText().matches("[0-9]+"))) {
                JOptionPane.showMessageDialog(this, "Invalid Input! Please try again.", "Sorry",
                        JOptionPane.WARNING_MESSAGE);
                jtUserID.setText("");
                jtUsername.setText("");
                jtPassword.setText("");
                jtBalance.setText("");
            } else {
                AccountDao dao = new AccountDaoImpl();
                Account account = new Account();
                account.setUserID(jtUserID.getText());
                account.setUsername(jtUsername.getText());
                account.setPassword(jtPassword.getText());
                account.setBalance(Integer.parseInt(jtBalance.getText()));
                try {
                    if (dao.addNewAccount(account)) {
                        JOptionPane.showMessageDialog(this, "Congratulations! Register Successfully!",
                                "Congratulations!", JOptionPane.WARNING_MESSAGE);
                        jtUserID.setText("");
                        jtUsername.setText("");
                        jtPassword.setText("");
                        jtBalance.setText("");
                    } else {
                        JOptionPane.showMessageDialog(this, "The user ID or user name has been used. Please try again.",
                                "Sorry", JOptionPane.WARNING_MESSAGE);
                        jtUserID.setText("");
                        jtUsername.setText("");
                        jtPassword.setText("");
                        jtBalance.setText("");
                    }

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        MainAdmin.setup();
    }
}