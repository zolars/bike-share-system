package layout;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.regex.*;

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
    private JTextField jtUsername, jtEmail;
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
        JPanel panel07 = new JPanel();

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

        // Email input
        JLabel jlEmail = new JLabel("Email : ");
        jlEmail.setFont(new java.awt.Font("Dialog", 1, 25));
        panel04.add(jlEmail);
        jtEmail = new JTextField(15);
        panel04.add(jtEmail);

        panel05 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // add Action Listener
        jbRegister.addActionListener(this);
        panel05.add(jbRegister);

        panel01.setOpaque(false);
        panel02.setOpaque(false);
        panel03.setOpaque(false);
        panel04.setOpaque(false);
        panel05.setOpaque(false);

        panel07.setOpaque(false);
        add(panel07);
        add(panel01);
        add(panel02);
        add(panel03);
        add(panel04);
        add(panel05);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbRegister) {
            if (jtUserID.getText().isEmpty() || jtUsername.getText().isEmpty()
                    || isNotEmail(String.valueOf(jtEmail.getText()))) {
                JOptionPane.showMessageDialog(null, "Invalid Input! Please try again.", "Sorry",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                AccountDao dao = new AccountDaoImpl();
                Account account = new Account();
                account.setUserID(jtUserID.getText());
                account.setUsername(jtUsername.getText());
                account.setEmail(jtEmail.getText());
                account.setFine(false);

                try {
                    if (dao.addNewAccount(account)) {
                        JOptionPane.showMessageDialog(null, "Congratulations! Register Successfully!",
                                "Congratulations!", JOptionPane.WARNING_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "The user ID or user name has been used. Please try again.",
                                "Sorry", JOptionPane.WARNING_MESSAGE);
                    }

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            jtUserID.setText("");
            jtUsername.setText("");
            jtEmail.setText("");
        }
    }

    public static boolean isNotEmail(String string) {
        if (string == null)
            return false;
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx1);
        m = p.matcher(string);
        if (m.matches())
            return false;
        else
            return true;
    }

    public static void main(String[] args) {
        MainAdmin.setup();
    }
}