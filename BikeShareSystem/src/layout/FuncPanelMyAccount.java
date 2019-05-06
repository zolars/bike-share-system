package layout;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.regex.*;

import javax.swing.*;

import application.*;
import database.dao.*;
import database.dao.impl.*;
import database.entity.*;

/**
 * FuncPanelMyAccount
 * 
 * @author Xin Yifei
 * @version 0.9
 */
public class FuncPanelMyAccount extends FuncPanelDefault implements ActionListener {
    private static final long serialVersionUID = 1L;

    private AccountDao dao = new AccountDaoImpl();
    private Account account;

    private JLabel jlUsername;
    private JLabel jlEmail;
    private JLabel jlFine;
    private JButton jbChangeEmail = new JButton("Modify");
    private JButton jbChangeFine = new JButton("Pay");
    private JButton jbLogout = new JButton("Log out");

    public FuncPanelMyAccount() {
        super();
        setName("MyAccount");
        grabData();

        setLayout(new GridLayout(8, 1, 0, 0));
        JPanel panel01 = new JPanel();
        JPanel panel02 = new JPanel();
        JPanel panel03 = new JPanel();
        JPanel panel04 = new JPanel();
        JPanel panel05 = new JPanel();
        JPanel panel06 = new JPanel();
        // JPanel panel07 = new JPanel();
        // JPanel panel08 = new JPanel();

        panel03 = new JPanel();
        jlUsername = new JLabel("Username : " + account.getUsername());
        jlUsername.setFont(new java.awt.Font("Dialog", 1, 25));
        panel03.add(jlUsername);

        panel04 = new JPanel();
        jlEmail = new JLabel("Email : " + account.getEmail());
        jlEmail.setFont(new java.awt.Font("Dialog", 1, 25));
        panel04.add(jlEmail);
        jbChangeEmail.addActionListener(this);
        panel04.add(new JLabel(""));
        panel04.add(new JLabel(""));
        panel04.add(new JLabel(""));
        panel04.add(jbChangeEmail);

        panel05 = new JPanel();
        jlFine = new JLabel("fine : " + account.isFine());
        jlFine.setFont(new java.awt.Font("Dialog", 1, 25));
        panel05.add(jlFine);
        jbChangeFine.addActionListener(this);
        panel05.add(new JLabel(""));
        panel05.add(new JLabel(""));
        panel05.add(new JLabel(""));
        panel05.add(jbChangeFine);

        panel06 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // add Action Listener
        jbLogout.addActionListener(this);
        panel06.add(jbLogout);

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
        if (e.getSource() == jbLogout) { // Logout operation
            MainUser.loginStatus = null;
            MainUser.restart = true;
            JOptionPane.showMessageDialog(this, "You have logged out", "", JOptionPane.WARNING_MESSAGE);
        } else { // Modify operation
            grabData();

            if (e.getSource() == jbChangeEmail) {
                while (true) {
                    String newString = JOptionPane.showInputDialog(null, "Input new email : \n", "Change Email",
                            JOptionPane.PLAIN_MESSAGE);
                    if (isEmail(newString)) {
                        account.setEmail(newString);
                        break;
                    } else {
                        JOptionPane.showMessageDialog(this, "The format is wrong. Try again!", "",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
            if (e.getSource() == jbChangeFine) {
                account.setFine(false);
            }

            try {
                if (dao.modifyAccount(account)) {
                    JOptionPane.showMessageDialog(this, "Operate Successfully", "Congratulations",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this,
                            "There are something wrong? Maybe the database occurs mistakes.", "Sorry",
                            JOptionPane.WARNING_MESSAGE);
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void grabData() {
        try {
            account = dao.findAccountByUserID(MainUser.loginStatus);
            if (account == null) {
                JOptionPane.showMessageDialog(this, "There are something wrong? Maybe the database occurs mistakes.",
                        "Sorry", JOptionPane.WARNING_MESSAGE);
                MainUser.loginStatus = null;
                MainUser.restart = true;
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void updateUI() {
        super.updateUI();
        try {
            grabData();
            jlEmail.setText("Email : " + account.getEmail());
            if (account.isFine()) {
                jlFine.setText("You are still have 100GBP to pay.");
                jbChangeFine.setVisible(true);
            } else {
                jlFine.setText("");
                jbChangeFine.setVisible(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isEmail(String string) {
        if (string == null)
            return false;
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx1);
        m = p.matcher(string);
        if (m.matches())
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
        MainUser.setup();
    }
}