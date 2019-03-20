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
 * FuncPanelLogin
 * 
 * @author Xin Yifei
 * @version 0.8
 */
public class FuncPanelLogin extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;
    private Image img = new ImageIcon(getClass().getResource("/images/Plain.jpg")).getImage();

    private JTextField jtUsername;
    private JPasswordField jtPassword;
    private JButton jbLogin = new JButton("Login !");

    public FuncPanelLogin() {
        if (MainUser.loginStatus != null) {
        } else {
            setName("Login");

            Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
            setSize(size);
            setPreferredSize(size);
            setMinimumSize(size);
            setMaximumSize(size);

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
            JLabel jlLogin = new JLabel("Please Login :");
            jlLogin.setFont(new java.awt.Font("Dialog", 1, 25));
            panel03.add(jlLogin);

            // Username input
            panel04 = new JPanel();
            JLabel jlUsername = new JLabel("Username : ");
            jlUsername.setFont(new java.awt.Font("Dialog", 1, 25));
            panel04.add(jlUsername);
            jtUsername = new JTextField(15);
            panel04.add(jtUsername);

            // Password input
            panel05 = new JPanel();
            JLabel jlPassword = new JLabel("Password : ");
            jlPassword.setFont(new java.awt.Font("Dialog", 1, 25));
            panel05.add(jlPassword);
            jtPassword = new JPasswordField(15);
            panel05.add(jtPassword);

            panel06 = new JPanel(new FlowLayout(FlowLayout.CENTER));
            // add Action Listener
            jbLogin.addActionListener(this);
            panel06.add(jbLogin);

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
    }

    public void actionPerformed(ActionEvent e) {
        AccountDao dao = new AccountDaoImpl();
        try {
            Account account = dao.findAccountByUsername(jtUsername.getText());
            if (account == null) {
                JOptionPane.showMessageDialog(this, "This account doesn't exist. Please try again.", "Sorry",
                        JOptionPane.WARNING_MESSAGE);
                jtUsername.setText("");
                jtPassword.setText("");
            } else if (!account.getPassword().equals(String.valueOf(jtPassword.getPassword()))) {
                JOptionPane.showMessageDialog(this, "The password is invalid. Please try again.", "Sorry",
                        JOptionPane.WARNING_MESSAGE);
                jtUsername.setText("");
                jtPassword.setText("");
            } else {
                MainUser.loginStatus = dao.findAccountByUsername(jtUsername.getText()).getUserID();
                MainUser.restart = true;
                JOptionPane.showMessageDialog(this, "Successfully Login!", "Congratulations",
                        JOptionPane.WARNING_MESSAGE);
            }

        } catch (IOException e1) {
            e1.printStackTrace();
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