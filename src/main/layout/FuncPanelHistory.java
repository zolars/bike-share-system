package layout;

import application.MainUser;
import javax.swing.JLabel;

/**
 * FuncPanelHistory
 *
 * @author Sun Qinghao & Lu Siyuan
 * @version 1.0
 */
public class FuncPanelHistory extends FuncPanelDefault {

    private static final long serialVersionUID = 1L;

    public FuncPanelHistory() {
        super();
        setName("History");
        this.add(new JLabel("History Page..."));
    }

    @Override
    public void updateUI() {
        super.updateUI();
        // Data update
    }

    public static void main(String[] args) {
        MainUser.setup();
    }
}
