package layout;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.*;

import application.*;
import database.dao.*;
import database.dao.impl.*;
import database.entity.*;

/**
 * FuncPanelHistory
 * 
 * @author Xin Yifei
 * @version 0.9
 */
public class FuncPanelHistory extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;

    private Image img = new ImageIcon(getClass().getResource("/images/Plain.jpg")).getImage();
    private String userID = MainUser.loginStatus;

    private RecordDao recordDao = new RecordDaoImpl();
    private List<Record> records = new ArrayList<Record>();
    private String[][] datas = new String[100][4];

    private JScrollPane sPane = new JScrollPane();
    private JTable table;

    public FuncPanelHistory() {
        setName("History");

        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setSize(size);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);

        grabData();

        setLayout(null);

        table = new JTable(datas, new String[] { "Start Date", "End Date", "Duration", "Bill" });

        JTableHeader head = table.getTableHeader();
        head.setPreferredSize(new Dimension(head.getWidth(), 50));
        head.setFont(new Font("Dialog", 1, 25));
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(50);
        table.getColumnModel().getColumn(2).setPreferredWidth(50);
        table.getColumnModel().getColumn(3).setPreferredWidth(50);
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
                String duration = table.getValueAt(table.getSelectedRow(), 2).toString();
                String bill = table.getValueAt(table.getSelectedRow(), 3).toString();
                Object[] choices = { "OK" };
                JOptionPane.showOptionDialog(null,
                        "Start Date : " + startDate + "\nEnd Date : " + endDate + "\nDuration : " + duration
                                + "\nBill : " + bill,
                        "Details", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, choices,
                        choices[0]);
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
        try {
            records = recordDao.findRecordAll(userID);
            for (int i = 0; i < datas.length; i++) {
                datas[i] = new String[4];
            }
            for (int i = records.size() - 1; i >= 0; i--) {
                datas[records.size() - 1 - i] = records.get(i).toStringList();
            }
        } catch (IOException e) {
            e.printStackTrace();
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

    @Override
    protected void paintComponent(Graphics g) {
        Dimension size = this.getParent().getSize();
        g.drawImage(img, 0, 0, size.width, size.height, this);
    }

    public static void main(String[] args) {
        MainAdmin.setup();
    }

}