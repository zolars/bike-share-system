package layout;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.*;

import application.*;

/**
 * FuncPanelTest
 * 
 * @author Xin Yifei
 * @version 0.9
 */
public class FuncPanelTest extends FuncPanelDefault implements ActionListener {
    private static final long serialVersionUID = 1L;

    JScrollPane sPane = new JScrollPane();

    public FuncPanelTest() {
        super();
        setName("Test");
        setLayout(null);

        sPane.setPreferredSize(new Dimension(getBounds().width, getBounds().height));

        DefaultTableModel model = new DefaultTableModel(new Integer[][] { { 1, 2 }, { 3, 4 } },
                new String[] { "A", "B" });
        JTable table = new JTable(model);
        sPane.getViewport().add(table);

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

    @Override
    public void updateUI() {
        super.updateUI();
        try {
            sPane.setPreferredSize(new Dimension(getBounds().width * 8 / 10, getBounds().height * 8 / 10));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MainUser.setup();
    }
}