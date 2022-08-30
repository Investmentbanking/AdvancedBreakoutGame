import javax.swing.*;
import java.awt.*;

/**
 * This class creates the background selector JPanel which allows the player to choose a background
 * of their choice. A choice of 6 backgrounds is given to the player to choose from.
 *
 */
public class BackgroundSelector extends JPanel{

    GameWindow game = new GameWindow();

    private final ClassLoader cl = Thread.currentThread().getContextClassLoader();
    private final Image image = Toolkit.getDefaultToolkit().getImage(cl.getResource("black.jpg"));

    JLabel label;

    // creates the radioButtons which display the backgrounds to choose from
    JRadioButton radioButton;
    JRadioButton radioButton2;
    JRadioButton radioButton3;
    JRadioButton radioButton4;
    JRadioButton radioButton5;
    JRadioButton radioButton6;

    /**
     * Constructor which adds the radio buttons to a button group and displays them on the JPanel using a flow layout.
     *
     */
    public BackgroundSelector(){

        //setBackground(Color.black);
        label = new JLabel("Please select a background", JLabel.CENTER);
        label.setFont(new Font("Verdana", Font.BOLD, 18));
        label.setForeground(Color.white);

        setLayout(new FlowLayout());
        ButtonGroup group = new ButtonGroup();
        group.add(radioButton);
        group.add(radioButton2);
        group.add(radioButton3);
        group.add(radioButton4);
        group.add(radioButton5);
        group.add(radioButton6);

        option1();
        option2();
        option3();
        option4();
        option5();
        option6();
        add(label);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        image.getScaledInstance(500, 500,Image.SCALE_SMOOTH);
        while(!prepareImage(image, this)) {
            prepareImage(image, this);
        }
        g.drawImage(image,0,0,null);
    }

    /**
     * Converts the url to an image which will be used as the icon for the JRadioButton.
     *
     * @param url The url of the image.
     * @return The image with a specific height and width.
     */
    public String URL(String url){
        return "<html><body><img src='" + url + "'width=128 height=128>";
    }

    /**
     * Adds radioButton to the JPanel, when clicked, creates a new game with the space background.
     *
     */
    public void option1() {
        String url = URL("https://www.nasa.gov/sites/default/files/styles/full_width/public/thumbnails/image/main_image_star-forming_region_carina_nircam_final-1280.jpg");
        radioButton = new JRadioButton(url);
        add(radioButton);
        radioButton.addActionListener(e -> {
            Window.frame.getContentPane().removeAll();
            game.changeFilepath("space.jpg");
            Window.frame.getContentPane().add(game);
            Window.frame.addKeyListener(game);
            game.setFocusable(true);
            game.requestFocusInWindow();
            Window.frame.revalidate();
        });
    }

    /**
     * Adds radioButton to the JPanel, when clicked, creates a new game with the bricks background.
     *
     */
    public void option2(){
        String url = URL("https://appgrooves.com/cdn/mc/GAME_ARCADE/7_w1200.jpg");
        radioButton2 = new JRadioButton(url);
        add(radioButton2);
        radioButton2.addActionListener(e -> {
            Window.frame.getContentPane().removeAll();
            game.changeFilepath("bricks.jpg");
            Window.frame.getContentPane().add(game);
            Window.frame.addKeyListener(game);
            game.setFocusable(true);
            game.requestFocusInWindow();
            Window.frame.revalidate();
        });
    }

    /**
     * Adds radioButton to the JPanel, when clicked, creates a new game with the stars background.
     *
     */
    public void option3(){
        String url = URL("https://upload.wikimedia.org/wikipedia/commons/2/26/Oldest_star_in_solar_neighbourhood.jpg");
        radioButton3 = new JRadioButton(url);
        add(radioButton3);
        radioButton3.addActionListener(e -> {
            Window.frame.getContentPane().removeAll();
            game.changeFilepath("stars.jpg");
            Window.frame.getContentPane().add(game);
            Window.frame.addKeyListener(game);
            game.setFocusable(true);
            game.requestFocusInWindow();
            Window.frame.revalidate();
        });
    }

    /**
     * Adds radioButton to the JPanel, when clicked, creates a new game with the bubbles background.
     *
     */
    public void option4(){
        String url = URL("https://cdn.pocket-lint.com/assets/images/131835-phones-news-feature-cropped-best-iphone-wallpapers-image72-7pqcs1gy9h.jpg");
        radioButton4 = new JRadioButton(url);
        add(radioButton4);
        radioButton4.addActionListener(e -> {
            Window.frame.getContentPane().removeAll();
            game.changeFilepath("bubbles.jpg");
            Window.frame.getContentPane().add(game);
            Window.frame.addKeyListener(game);
            game.setFocusable(true);
            game.requestFocusInWindow();
            Window.frame.revalidate();
        });
    }

    /**
     * Adds radioButton to the JPanel, when clicked, creates a new game with the forest background.
     *
     */
    public void option5(){
        String url = URL("https://images.hdqwalls.com/download/blue-forest-minimal-4k-kz-2048x2048.jpg");
        radioButton5 = new JRadioButton(url);
        add(radioButton5);
        radioButton5.addActionListener(e -> {
            Window.frame.getContentPane().removeAll();
            game.changeFilepath("forest.jpg");
            Window.frame.getContentPane().add(game);
            Window.frame.addKeyListener(game);
            game.setFocusable(true);
            game.requestFocusInWindow();
            Window.frame.revalidate();
        });
    }

    /**
     * Adds radioButton to the JPanel, when clicked, creates a new game with the japanese purple background.
     *
     */
    public void option6(){
        String url = URL("https://www.pixelstalk.net/wp-content/uploads/images5/Cool-Japanese-Backgrounds-HD-Free-download.jpg");
        radioButton6 = new JRadioButton(url);
        add(radioButton6);
        radioButton6.addActionListener(e -> {
            Window.frame.getContentPane().removeAll();
            game.changeFilepath("purple.jpg");
            Window.frame.getContentPane().add(game);
            Window.frame.addKeyListener(game);
            game.setFocusable(true);
            game.requestFocusInWindow();
            Window.frame.revalidate();
        });
    }
}

