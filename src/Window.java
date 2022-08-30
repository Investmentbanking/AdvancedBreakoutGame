import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Arrays;

/**
 *
 */
public class Window {

    public static JFrame frame;
    GameWindow game;
    BackgroundSelector background;
    static DifficultySelector difficulty;
    public static MainBackgroundImage main; // start window

    public Window() throws IOException {

        frame = new JFrame("Brick Builder Game"); // creates JFrame

        game = new GameWindow(); // creates new GAME window
        main = new MainBackgroundImage(); // creates MAIN screen
        background = new BackgroundSelector(); // creates BACKGROUND SELECTOR window
        difficulty = new DifficultySelector(); // creates DIFFICULTY SELECTOR window

        main.setLayout(null);

        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setVisible(true);
        frame.setResizable(false);

        frame.getContentPane().add(main); // adds MAIN screen to frame


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

        start.addActionListener(e -> difficultyPanel());

        quit.addActionListener(e -> {
            frame.dispose();
        });

        backgroundSelector.addActionListener(e -> {
            loading.setText("Loading...");
            backgroundPanel();
        });

        //backgroundSelector.addActionListener(e -> backgroundPanel());
    }

    public static void mainPanel(){
        frame.getContentPane().removeAll();
        frame.getContentPane().add(main);
        frame.validate();
    }

    public void backgroundPanel(){
        frame.getContentPane().removeAll();
        background = new BackgroundSelector();
        frame.getContentPane().add(background);
        frame.revalidate();
    }

    public void difficultyPanel(){
        frame.getContentPane().removeAll();
        difficulty = new DifficultySelector();
        frame.getContentPane().add(difficulty);
        frame.requestFocusInWindow();
        frame.revalidate();
    }

        public static void main (String[]args) throws IOException {
            new Window();
        }
    }

