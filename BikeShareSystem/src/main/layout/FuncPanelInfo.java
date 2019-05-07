package layout;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.*;

import application.*;
import database.dao.*;
import database.dao.impl.*;
import database.entity.*;

/**
 * FuncPanelInfo
 * 
 * @author Xin Yifei
 * @version 0.9
 */
public class FuncPanelInfo extends FuncPanelDefault implements ActionListener {
    private static final long serialVersionUID = 1L;

    private String[][] datas = new String[200][5];

    private JScrollPane sPane = new JScrollPane();
    private JTable table;

    public FuncPanelInfo() {
        super();
        setName("Info");
        grabData();
        setLayout(null);

        table = new JTable(datas, new String[] { "Start Date", "End Date", "User ID", "Duration", "Overdue" });

        JTableHeader head = table.getTableHeader();
        head.setPreferredSize(new Dimension(head.getWidth(), 50));
        head.setFont(new Font("Dialog", 1, 25));
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(10);
        table.getColumnModel().getColumn(3).setPreferredWidth(10);
        table.getColumnModel().getColumn(4).setPreferredWidth(10);
        table.setRowHeight(50);
        table.setFont(new Font("Dialog", Font.PLAIN, 15));

        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, r);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String startDate = table.getValueAt(table.getSelectedRow(), 0).toString();
                String endDate = table.getValueAt(table.getSelectedRow(), 1).toString();
                String userID = table.getValueAt(table.getSelectedRow(), 2).toString();
                String duration = table.getValueAt(table.getSelectedRow(), 3).toString();
                String overdue = table.getValueAt(table.getSelectedRow(), 4).toString();

                JOptionPane.showMessageDialog(null,
                        "Start Date : " + startDate + "\nEnd Date : " + endDate + "\nUser ID : " + userID
                                + "\nDuration : " + duration + "\nis Overdue : " + overdue,
                        "Details", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        sPane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(sPane);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                sPane.setBounds(getBounds().width / 18, getBounds().height / 46 * 10, getBounds().width * 8 / 9,
                        getBounds().height * 205 / 300);
            }
        });
    }

    public void actionPerformed(ActionEvent e) {

    }

    public void grabData() {
        RecordDao dao = new RecordDaoImpl();
        List<Record> records = new ArrayList<Record>();
        try {
            records = dao.findRecordAll();

            for (int i = 0; i < datas.length; i++) {
                datas[i] = new String[5];
            }

            for (int i = 0; i < records.size(); i++) {
                datas[i] = records.get(i).toDetail().split(";");
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

    public static void main(String[] args) {
        MainAdmin.setup();
    }

}