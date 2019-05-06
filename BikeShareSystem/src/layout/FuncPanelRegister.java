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
 * @version 0.9
 */
public class FuncPanelRegister extends FuncPanelDefault implements ActionListener {
    private static final long serialVersionUID = 1L;

    private JTextField jtUserID;
    private JTextField jtUsername;
    private JPasswordField jtPassword;
    private JTextField jtBalance;
    private JButton jbRegister = new JButton("Register !");

    public FuncPanelRegister() {
        super();
        setName("Register");
        setLayout(new GridLayout(8, 1, 0, 0));
        JPanel panel01 = new JPanel();
        JPanel panel02 = new JPanel();
        JPanel panel03 = new JPanel();
        JPanel panel04 = new JPanel();
        JPanel panel05 = new JPanel();
        JPanel panel06 = new JPanel();
        JPanel panel07 = new JPanel();
        // JPanel panel08 = new JPanel();

        // UserID input
        JLabel jlUserID = new JLabel("User ID : ");
        jlUserID.setFont(new java.awt.Font("Dialog", 1, 25));
        panel02.add(jlUserID);
        jtUserID = new JTextField(15);
        panel02.add(jtUserID);

        // Username input
        JLabel jlUsername = new JLabel("Username : ");
        jlUsername.setFont(new java.awt.Font("Dialog", 1, 25));
        panel03.add(jlUsername);
        jtUsername = new JTextField(15);
        panel03.add(jtUsername);

        // Password input
        JLabel jlPassword = new JLabel("Password : ");
        jlPassword.setFont(new java.awt.Font("Dialog", 1, 25));
        panel04.add(jlPassword);
        jtPassword = new JPasswordField(15);
        panel04.add(jtPassword);

        // Balance input
        JLabel jlBalance = new JLabel("Recharge : ");
        jlBalance.setFont(new java.awt.Font("Dialog", 1, 25));
        panel05.add(jlBalance);
        jtBalance = new JTextField(15);
        panel05.add(jtBalance);

        panel06 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // add Action Listener
        jbRegister.addActionListener(this);
        panel06.add(jbRegister);

        panel01.setOpaque(false);
        panel02.setOpaque(false);
        panel03.setOpaque(false);
        panel04.setOpaque(false);
        panel05.setOpaque(false);
        panel06.setOpaque(false);
        panel07.setOpaque(false);
        add(panel07);
        add(panel01);
        add(panel02);
        add(panel03);
        add(panel04);
        add(panel05);
        add(panel06);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbRegister) {
            if (jtUserID.getText().isEmpty() || jtUsername.getText().isEmpty()
                    || String.valueOf(jtPassword.getPassword()).isEmpty() || (!jtBalance.getText().matches("[0-9]+"))) {
                JOptionPane.showMessageDialog(this, "Invalid Input! Please try again.", "Sorry",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                AccountDao dao = new AccountDaoImpl();
                Account account = new Account();
                account.setUserID(jtUserID.getText());
                account.setUsername(jtUsername.getText());
                account.setPassword(String.valueOf(jtPassword.getPassword()));
                account.setBalance(Integer.parseInt(jtBalance.getText()));
                try {
                    if (dao.addNewAccount(account)) {
                        JOptionPane.showMessageDialog(this, "Congratulations! Register Successfully!",
                                "Congratulations!", JOptionPane.WARNING_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "The user ID or user name has been used. Please try again.",
                                "Sorry", JOptionPane.WARNING_MESSAGE);
                    }

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            jtUserID.setText("");
            jtUsername.setText("");
            jtPassword.setText("");
            jtBalance.setText("");
        }
    }

    public static void main(String[] args) {
        MainAdmin.setup();
    }
}