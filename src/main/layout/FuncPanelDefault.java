package layout;

import application.MainUser;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * FuncPanelDefault
 *
 * @author Xin Yifei
 * @version 1.0
 */
public class FuncPanelDefault extends JPanel {

    private static final long serialVersionUID = 1L;

    private Image img = new ImageIcon(getClass().getResource("/images/Plain.jpg")).getImage();

    public FuncPanelDefault() {
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setSize(size);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);

    }

    @Override
    public void updateUI() {
        super.updateUI();
        // Data update
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
