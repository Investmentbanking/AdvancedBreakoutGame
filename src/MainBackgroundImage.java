import java.awt.*;
import javax.swing.JPanel;

/**
 * Creates the background image for the main window.
 */
public class MainBackgroundImage extends JPanel{

    private ClassLoader cl = Thread.currentThread().getContextClassLoader();
    private Image image = Toolkit.getDefaultToolkit().getImage(cl.getResource("bricks.jpg"));


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        image.getScaledInstance(500, 500,Image.SCALE_SMOOTH);
        while(!prepareImage(image, this)) {
            prepareImage(image, this);
        }
        g.drawImage(image,0,0,null);
    }
}
