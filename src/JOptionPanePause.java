import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

/**
 * This class creates a JPanel then adds it to the JOptionPane which pops up when the pause button
 * is clicked during the game.
 *
 */
public class JOptionPanePause {

    /**
     * Creates the JOptionPane relative to the JFrame with two options and returns either 0
     * or 2 depending on which option is chosen.
     *
     * @return number The number associated with the option chosen.
     */
    public int displayGUI(){
        UIManager.put("OptionPane.cancelButtonText", "Quit"); //2
        UIManager.put("OptionPane.okButtonText", "Return to Game"); //0
        // removes border around options
        UIDefaults defaults = UIManager.getLookAndFeelDefaults();
        defaults.put("Button.focus", new ColorUIResource(new Color(0, 0, 0, 0)));

        // colors the JOptionPane red
        UIManager.put("OptionPane.background",new ColorUIResource(255,0,0));
        UIManager.put("Panel.background",new ColorUIResource(255,0,0));

        int number = JOptionPane.showConfirmDialog(Window.frame,
                getPanel(),
                "You have paused your game",
                JOptionPane.CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        return number;
    }

    /**
     * Creates the JPanel with custom components and returns the panel.
     *
     * @return panel The JPanel.
     */
    public JPanel getPanel(){
        JPanel panel = new JPanel();
        panel.setBackground(Color.MAGENTA);
        JLabel label = new JLabel("Would you like to return to your game or quit?");
        panel.add(label);
        return panel;
    }
}
