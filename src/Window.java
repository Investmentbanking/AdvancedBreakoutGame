import javax.swing.*;
import java.awt.*;

/**
 *
 */
public class Window {

    public static JFrame frame;
    GameWindow game;
    BackgroundSelector background;
    public static MainBackgroundImage main; // start window

    public Window() {

        frame = new JFrame("Brick Builder Game"); // creates JFrame

        game = new GameWindow(); // creates new GAME window
        main = new MainBackgroundImage(); // creates MAIN screen
        background = new BackgroundSelector();

        // creates main panel layout
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.add(Box.createRigidArea(new Dimension(0, 20)));

        //frame.getContentPane().setBackground(Color.magenta);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setBounds(10, 10, 500, 500);
        frame.setVisible(true);
        frame.setResizable(false);

        frame.getContentPane().add(main); // adds MAIN screen to frame

        JButton start = new JButton("Start");
        JButton quit = new JButton("Quit");
        JButton backgroundSelector = new JButton("Background Selector");
        JLabel label = new JLabel("Welcome to brick breaker!");

        //start.setBackground(Color.white);
        start.setOpaque(false);
        start.setContentAreaFilled(false);
        //start.setBorderPainted(false);
        start.setForeground(Color.white);

        quit.setOpaque(false);
        quit.setContentAreaFilled(false);
        //quit.setBorderPainted(false);
        quit.setForeground(Color.white);

        backgroundSelector.setOpaque(false);
        backgroundSelector.setContentAreaFilled(false);
        //backgroundSelector.setBorderPainted(false);
        backgroundSelector.setForeground(Color.white);

        label.setForeground(Color.white);

        start.setFocusable(false);
        quit.setFocusable(false);
        backgroundSelector.setFocusable(false);
        start.setAlignmentX(Component.CENTER_ALIGNMENT);
        quit.setAlignmentX(Component.CENTER_ALIGNMENT);
        backgroundSelector.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        main.add(label);
        main.add(start); // adds JButton
        main.add(quit);
        main.add(backgroundSelector);

        main.validate();

        start.addActionListener(e -> gamePanel());
        quit.addActionListener(e -> {
            frame.dispose();
        });
        backgroundSelector.addActionListener(e -> backgroundPanel());
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

    public void gamePanel() {
        frame.getContentPane().removeAll();
        game = new GameWindow();
        frame.getContentPane().add(game);
        game.requestFocusInWindow();
        frame.addKeyListener(game);
        frame.revalidate();
    }

        public static void main (String[]args) {
            new Window();
        }
    }

