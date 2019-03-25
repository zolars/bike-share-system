package layout;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
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
 * FuncPanelMsg
 * 
 * @author Xin Yifei
 * @version 0.8
 * @param <MsgDao>
 */
public class FuncPanelMsg extends JPanel {
    private static final long serialVersionUID = 1L;

    private Image img = new ImageIcon(getClass().getResource("/images/Plain.jpg")).getImage();

    private MsgDao dao = new MsgDaoImpl();
    private Msg overdueMsg = new Msg();
    private List<Msg> otherMsg = new ArrayList<Msg>();

    private JLabel jlOverdueMsgLeft;
    private JLabel jlOverdueMsg;
    private JLabel jlOverdueMsgRight;
    private List<JTable> tables = new ArrayList<JTable>();

    public FuncPanelMsg() {
        this.setName("Msg");

        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setSize(size);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);

        grabData();

        int line = 14;
        setLayout(new GridLayout(line, 1, 0, 0));

        List<JPanel> panelList = new ArrayList<JPanel>();

        for (int i = 0; i < line; i++) {
            JPanel newPanel = new JPanel();
            newPanel.setOpaque(false);
            panelList.add(newPanel);
            add(newPanel);
        }

        // Overdue Msg : line 6
        jlOverdueMsgLeft = new JLabel("");
        jlOverdueMsgLeft.setFont(new java.awt.Font("Dialog", 1, 25));

        jlOverdueMsg = new JLabel("");
        jlOverdueMsg.setFont(new java.awt.Font("Dialog", 1, 25));

        jlOverdueMsgRight = new JLabel("");
        jlOverdueMsgRight.setFont(new java.awt.Font("Dialog", 1, 25));

        panelList.get(3).add(jlOverdueMsgLeft);
        panelList.get(3).add(jlOverdueMsg);
        panelList.get(3).add(jlOverdueMsgRight);

        // Other Msg Headers
        List<JScrollPane> tablePanels = new ArrayList<JScrollPane>();
        for (int i = 0; i < 7; i++) {
            DefaultTableModel model = new DefaultTableModel(new Integer[][] {}, new String[] { "Date", "Message" });

            tables.add(new JTable(model));
            tables.get(i).getTableHeader().setReorderingAllowed(false);
            tables.get(i).setRowHeight(28);

            JTableHeader head = tables.get(i).getTableHeader();
            head.setFont(new Font("Dialog", 1, 25));

            tablePanels.add(new JScrollPane());
            tablePanels.get(i).getViewport().add(tables.get(i));
            panelList.get(i + 5).add(tablePanels.get(i));

            panelList.get(i + 5).addMouseListener(new MouseListener() {

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    JOptionPane.showMessageDialog("Congratulations! Register Successfully!", "Congratulations!",
                            JOptionPane.WARNING_MESSAGE);
                }
            });
        }

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                for (int i = 0; i < 7; i++) {
                    tablePanels.get(i).setPreferredSize(
                            new Dimension(getBounds().width * 79 / 100, getBounds().height * 79 / 100));
                    TableColumnModel cm = tables.get(i).getColumnModel();
                    TableColumn column0 = cm.getColumn(0);
                    column0.setPreferredWidth(getBounds().width * 79 / 100 / 3);
                    column0.setMaxWidth(getBounds().width * 79 / 100 / 3);
                    TableColumn column1 = cm.getColumn(1);
                    column1.setPreferredWidth(getBounds().width * 79 / 100 / 3 * 2);
                    column1.setMaxWidth(getBounds().width * 79 / 100 / 3 * 2);

                }
            }
        });

    }

    public void grabData() {
        try {
            if (dao.findMsgOverdue(MainUser.loginStatus).size() == 0) {
                overdueMsg = null;
            } else {
                overdueMsg = dao.findMsgOverdue(MainUser.loginStatus).get(0);
            }
            otherMsg = dao.findMsgOther(MainUser.loginStatus);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void updateUI() {
        super.updateUI();

        try {
            grabData();
            Calendar cal = Calendar.getInstance();
            if (overdueMsg != null) {
                jlOverdueMsg.setForeground(Color.RED);
                jlOverdueMsgLeft.setForeground(Color.RED);
                jlOverdueMsgRight.setForeground(Color.RED);

                jlOverdueMsg.setText(" There is a bike overdue... Please return as soon as possible! ");
                if (cal.get(Calendar.SECOND) % 2 == 0) {
                    jlOverdueMsgLeft.setText("!!");
                    jlOverdueMsgRight.setText("!!");
                } else {
                    jlOverdueMsgLeft.setText("");
                    jlOverdueMsgRight.setText("");
                }
            } else {
                jlOverdueMsg.setForeground(Color.BLACK);
                jlOverdueMsgLeft.setForeground(Color.BLACK);
                jlOverdueMsgRight.setForeground(Color.BLACK);

                jlOverdueMsg.setText(" Welcome to check your message box. Please click to see more! ");
                jlOverdueMsgLeft.setText("");
                jlOverdueMsgRight.setText("");
            }

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
        MainUser.setup();
    }
}