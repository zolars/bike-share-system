package layout;

import application.MainAdmin;
import database.dao.AccountDao;
import database.dao.impl.AccountDaoImpl;
import database.entity.Account;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 * FuncPanelAccounts
 *
 * @author Xin Yifei
 * @version 1.0
 */
public class FuncPanelAccounts extends FuncPanelDefault implements ActionListener {

    private static final long serialVersionUID = 1L;

    private String[][] datas = new String[100][3];

    private JScrollPane sPane = new JScrollPane();
    private JTable table;

    public FuncPanelAccounts() {
        super();

        AccountDao dao = new AccountDaoImpl();

        setName("Accounts");
        grabData();
        setLayout(null);

        table = new JTable(datas, new String[]{"ID", "Name", "Email"});
        JTableHeader head = table.getTableHeader();
        head.setPreferredSize(new Dimension(head.getWidth(), 50));
        head.setFont(new Font("Dialog", 1, 25));
        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.getColumnModel().getColumn(1).setPreferredWidth(10);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        table.setRowHeight(50);
        table.setFont(new Font("Dialog", Font.PLAIN, 15));

        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, r);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String id = table.getValueAt(table.getSelectedRow(), 0).toString();
                String name = table.getValueAt(table.getSelectedRow(), 1).toString();
                String email = table.getValueAt(table.getSelectedRow(), 2).toString();
                Object[] choices = {"Modify Email Addres", /** "Send Message", */
                        "Delete", "Cancel"};
                int choiceNum = (int) JOptionPane.showOptionDialog(null,
                        "ID : " + id + "\nName : " + name + "\nEmail : " + email, id,
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                        choices, choices[0]);

                switch (choiceNum) {
                    case 0: // Modify
                        while (true) {
                            String newString =
                                    JOptionPane.showInputDialog(null, "Input new email : \n",
                                            "Change Email", JOptionPane.PLAIN_MESSAGE);
                            if (newString == null || newString.equals("")) {
                                break;
                            } else if (isEmail(newString)) {
                                try {
                                    Account account = dao.findAccountByUserID(id);
                                    account.setEmail(newString);
                                    dao.modifyAccount(account);
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }

                                JOptionPane.showMessageDialog(null,
                                        "The email address has been modified successfully!",
                                        "Congratulations", JOptionPane.INFORMATION_MESSAGE);
                                break;
                            } else {
                                JOptionPane.showMessageDialog(null,
                                        "The format is wrong. Try again!", "Sorry",
                                        JOptionPane.WARNING_MESSAGE);
                            }
                        }
                        break;
                    /**
                     * case 1: // Send message String msg = JOptionPane.showInputDialog(null,
                     * "Please input your message here...", "Send message",
                     * JOptionPane.PLAIN_MESSAGE); if (msg.equals("") || msg == null) {
                     * JOptionPane.showMessageDialog(null, "The input is empty.", "Sorry",
                     * JOptionPane.WARNING_MESSAGE); } else { try { MsgDao dao = new MsgDaoImpl();
                     * if (dao.addOtherMsg(id, msg)) { JOptionPane.showMessageDialog(null,
                     * "Congratulations! Send Successfully!", "Congratulations!",
                     * JOptionPane.WARNING_MESSAGE); } else { JOptionPane.showMessageDialog(null,
                     * "This account doesn't exist. Please try again.", "Sorry",
                     * JOptionPane.WARNING_MESSAGE); } } catch (IOException e1) {
                     * e1.printStackTrace(); } } break;
                     */
                    case 1: // Delete
                        try {
                            dao.deleteAccount(dao.findAccountByUserID(id));
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        break;
                    case 2: // Cancel
                        break;
                    default:
                        break;
                }
            }
        });

        sPane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(sPane);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                sPane.setBounds(getBounds().width / 18, getBounds().height / 46 * 10,
                        getBounds().width * 8 / 9, getBounds().height * 205 / 300);
            }
        });
    }

    public void actionPerformed(ActionEvent e) {

    }

    public void grabData() {
        AccountDao dao = new AccountDaoImpl();
        List<Account> accounts = new ArrayList<Account>();
        try {
            accounts = dao.findAccountAll();

            for (int i = 0; i < datas.length; i++) {
                datas[i] = new String[3];
            }

            for (int i = 0; i < accounts.size() - 1; i++) {
                datas[i] = accounts.get(i + 1).toString().split(" ");
                datas[i][1] = datas[i][1].replaceAll(";", " ");
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
            // refresh otherPanel
            table.repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static boolean isEmail(String string) {
        if (string == null) {
            return false;
        }
        String regEx1 =
                "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx1);
        m = p.matcher(string);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        MainAdmin.setup();
    }
}
