import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Creates the background image for the main window.
 */
public class MainBackgroundImage extends JPanel{

    BufferedImage background = ImageIO.read(Objects.requireNonNull(getClass().getResource("brick.jpg")));
    //private final ClassLoader cl = Thread.currentThread().getContextClassLoader();
    //private final Image image = Toolkit.getDefaultToolkit().getImage(cl.getResource("brick.jpg"));

    public MainBackgroundImage() throws IOException {
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        background.getScaledInstance(500, 500,Image.SCALE_SMOOTH);
        while(!prepareImage(background, this)) {
            prepareImage(background, this);
        }
        g.drawImage(background,0,0,null);
    }
}
