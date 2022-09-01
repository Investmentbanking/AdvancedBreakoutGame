import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Controls all the panels in the JFrame
 */
public class Window {

    public static JFrame frame;
    GameWindow game;
    BackgroundSelector background;
    static DifficultySelector difficulty;
    public static MainBackgroundImage main; // start window
    JProgressBar progressBar = new JProgressBar();

    // for the card layout
    public static CardLayout cardLayout = new CardLayout();
    public static JPanel cardPane = new JPanel(); // holds the other panels

    public Window() throws IOException {

        cardPane.setLayout(cardLayout);

        frame = new JFrame("Brick Builder Game"); // creates JFrame

        game = new GameWindow(); // creates new GAME window
        main = new MainBackgroundImage(); // creates MAIN screen
        background = new BackgroundSelector(); // creates BACKGROUND SELECTOR window
        difficulty = new DifficultySelector(); // creates DIFFICULTY SELECTOR window

        main.setLayout(null);

        cardPane.add(main, "Main Menu");
        cardPane.add(game, "Game Panel");

        frame.add(cardPane);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setVisible(true);
        frame.setResizable(false);

        JButton start = new JButton();
        Image startImg = ImageIO.read(getClass().getResource("start.png"));
        start.setIcon(new ImageIcon(startImg));
        start.setBounds(150,270,200,40);
        start.setOpaque(false);
        start.setBorderPainted(false);

        JButton backgroundSelector = new JButton();
        Image selectorImg = ImageIO.read(getClass().getResource("selector.png"));
        backgroundSelector.setIcon(new ImageIcon(selectorImg));
        backgroundSelector.setBounds(150, 320,200,40);
        backgroundSelector.setOpaque(false);
        backgroundSelector.setBorderPainted(false);

        JButton quit = new JButton();
        Image quitImg = ImageIO.read(getClass().getResource("quit.png"));
        quit.setIcon(new ImageIcon(quitImg));
        quit.setBounds(150,370,200,40);
        quit.setOpaque(false);
        quit.setBorderPainted(false);

        JLabel loading = new JLabel();
        loading.setBounds(150,400,100,20);

        main.repaint();

        start.setBackground(Color.white);
        backgroundSelector.setBackground(Color.white);
        quit.setBackground(Color.white);

        start.setFocusable(false);
        quit.setFocusable(false);
        backgroundSelector.setFocusable(false);
        start.setAlignmentX(Component.CENTER_ALIGNMENT);
        quit.setAlignmentX(Component.CENTER_ALIGNMENT);
        backgroundSelector.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        main.add(start); // adds JButton
        main.add(quit);
        main.add(backgroundSelector);
        main.add(loading);
        main.add(progressBar);

        cardPane.add(difficulty, "Difficulty panel");
        start.addActionListener(e -> {

            cardLayout.show(cardPane, "Difficulty panel");
        });

        quit.addActionListener(e -> {
            frame.dispose();
        });

        cardPane.add(background, "Background Panel");
        backgroundSelector.addActionListener(e -> {
            backgroundPanel();
        });
    }

    public static void mainPanel(){
        cardLayout.show(cardPane, "Main Menu");
    }

    public void backgroundPanel(){
        cardLayout.show(cardPane, "Background Panel");
    }

        public static void main (String[]args) throws IOException {
            new Window();
        }
    }

