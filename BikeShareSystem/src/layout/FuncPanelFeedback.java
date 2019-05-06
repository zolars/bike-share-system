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
 * FuncPanelFeedback
 * 
 * @author Xin Yifei
 * @version 0.9
 */
public class FuncPanelFeedback extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;

    private Image img = new ImageIcon(getClass().getResource("/images/Plain.jpg")).getImage();

    private MsgDao dao = new MsgDaoImpl();
    private List<Msg> otherMsg = new ArrayList<Msg>();
    private String[][] datas = new String[100][2];

    private JScrollPane sPane = new JScrollPane();
    private JTable table;

    public FuncPanelFeedback() {
        setName("Feedback");

        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setSize(size);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);

        grabData();

        setLayout(null);

        table = new JTable(datas, new String[] { "Date", "Feedback" });

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
                sPane.setBounds(getBounds().width / 18, getBounds().height / 46 * 10, getBounds().width * 8 / 9,
                        getBounds().height * 205 / 300);
            }
        });
    }

    public void actionPerformed(ActionEvent e) {

    }

    public void grabData() {
        try {

            otherMsg = dao.findMsgOther("admin");

            for (int i = 0; i < datas.length; i++) {
                datas[i] = new String[2];
            }

            int i = 0;
            for (Msg data : otherMsg) {
                datas[i] = data.toStringList();
                i++;
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