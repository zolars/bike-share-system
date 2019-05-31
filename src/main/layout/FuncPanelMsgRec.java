package layout;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.table.*;

import application.*;
import database.dao.*;
import database.dao.impl.*;
import database.entity.*;

/**
 * FuncPanelMsgRec
 * 
 * @author An Hongda
 * @version 1.0
 */
public class FuncPanelMsgRec extends FuncPanelDefault implements ActionListener {
    private static final long serialVersionUID = 1L;

    private MsgDao dao = new MsgDaoImpl();
    private Msg overdueMsg = new Msg();
    private List<Msg> otherMsg = new ArrayList<Msg>();
    private String[][] datas = new String[100][2];

    private JPanel overduePanel = new JPanel();
    private JLabel jlOverdueMsgLeft;
    private JLabel jlOverdueMsg;
    private JLabel jlOverdueMsgRight;

    private JScrollPane sPane = new JScrollPane();
    private JTable table;

    public FuncPanelMsgRec() {
        super();
        setName("Message");
        grabData();
        setLayout(null);

        jlOverdueMsgLeft = new JLabel();
        jlOverdueMsgLeft.setFont(new Font("Dialog", 1, 25));
        jlOverdueMsgLeft.setForeground(Color.RED);
        overduePanel.add(jlOverdueMsgLeft);

        jlOverdueMsg = new JLabel();
        jlOverdueMsg.setFont(new Font("Dialog", 1, 25));
        jlOverdueMsg.setForeground(Color.RED);
        overduePanel.add(jlOverdueMsg);

        jlOverdueMsgRight = new JLabel();
        jlOverdueMsgRight.setFont(new Font("Dialog", 1, 25));
        jlOverdueMsgRight.setForeground(Color.RED);
        overduePanel.add(jlOverdueMsgRight);

        overduePanel.setOpaque(false);
        add(overduePanel);

        table = new JTable(datas, new String[] { "Date", "Message" });

        JTableHeader head = table.getTableHeader();
        head.setPreferredSize(new Dimension(head.getWidth(), 50));
        head.setFont(new Font("Dialog", 1, 25));
        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.getColumnModel().getColumn(1).setPreferredWidth(500);
        table.setRowHeight(50);
        table.setFont(new Font("Dialog", Font.PLAIN, 15));

        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, r);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String date = table.getValueAt(table.getSelectedRow(), 0).toString();
                String text = table.getValueAt(table.getSelectedRow(), 1).toString();
                Object[] choices = { "OK", "Delete", "Cancel" };
                int choiceNum = (int) JOptionPane.showOptionDialog(null, "Date : " + date + "\nMessage : " + text,
                        "Details", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, choices,
                        choices[0]);

                switch (choiceNum) {
                case 0: // OK
                    break;
                case 1: // Delete
                    try {
                        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                        for (Msg selectedMsg : otherMsg) {
                            if (text.equals(selectedMsg.getText()) && date.equals(sf.format(selectedMsg.getDate()))) {
                                dao.deleteMsg(String.valueOf(selectedMsg.getMsgID()));
                                updateUI();
                            }
                        }
                    } catch (IOException e1) {
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
                overduePanel.setBounds(0, getBounds().height / 60, getBounds().width, 60);
                sPane.setBounds(getBounds().width / 18, getBounds().height / 46 * 10, getBounds().width * 8 / 9,
                        getBounds().height * 205 / 300);
            }
        });
    }

    public void actionPerformed(ActionEvent e) {

    }

    public void grabData() {
        try {
            if (dao.findMsgOverdue(MainUser.loginStatus).size() == 0) {
                overdueMsg = null;
            } else {
                overdueMsg = dao.findMsgOverdue(MainUser.loginStatus).get(0);
            }
            otherMsg = dao.findMsgOther(MainUser.loginStatus);

            for (int i = 0; i < datas.length; i++) {
                datas[i] = new String[2];
            }

            int i = 0;
            for (Msg data : otherMsg) {
                datas[i] = data.toStringList();
                i++;
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

            // refresh overduePanel
            Calendar cal = Calendar.getInstance();
            if (overdueMsg != null) {

                jlOverdueMsg.setText(" A bike overdue! Please return as soon as possible. ");
                if (cal.get(Calendar.SECOND) % 2 == 0) {
                    jlOverdueMsgLeft.setText("!!");
                    jlOverdueMsgRight.setText("!!");
                } else {
                    jlOverdueMsgLeft.setText("");
                    jlOverdueMsgRight.setText("");
                }
            } else {

                jlOverdueMsg.setText("");
                jlOverdueMsgLeft.setText("");
                jlOverdueMsgRight.setText("");
            }

            // refresh otherPanel
            table.repaint();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        MainUser.setup();
    }

}