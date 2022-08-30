import javax.swing.*;
import java.awt.*;

public class DifficultySelector extends JPanel {

    GameWindow game = new GameWindow();
    //MainBackgroundImage main = new MainBackgroundImage();

    private final ClassLoader cl = Thread.currentThread().getContextClassLoader();
    private final Image image = Toolkit.getDefaultToolkit().getImage(cl.getResource("black.jpg"));

    GridBagConstraints c = new GridBagConstraints();

    JButton easyLevel;
    JButton mediumLevel;
    JButton hardLevel;
    JButton backButton = new JButton("BACK");

    public static boolean easyChecked = false;
    public static boolean mediumChecked = false;
    public static boolean hardChecked = false;

    public DifficultySelector(){
        //setBackground(Color.BLACK);
        JLabel label = new JLabel("Please select the difficulty", JLabel.CENTER);
        label.setFont(new Font("Verdana", Font.BOLD, 18));
        label.setForeground(Color.white);
        //label.setAlignmentX(Component.CENTER_ALIGNMENT);

        setLayout(new GridBagLayout());

        c.weightx = 0;
        c.weighty = 0.1;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridwidth = GridBagConstraints.REMAINDER;
        //c.fill = GridBagConstraints.BOTH;

        add(label,c);
        easy();
        medium();
        hard();
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


    private void easy(){
        easyLevel = new JButton("EASY");
        easyLevel.setBackground(Color.green);

        add(easyLevel,c);
        easyLevel.addActionListener(e -> {
            easyChecked = true;
            Window.frame.getContentPane().removeAll();
            Window.frame.getContentPane().add(game);
            Window.frame.addKeyListener(game);
            game.setFocusable(true);
            game.requestFocusInWindow();
            Window.frame.revalidate();
        });
    }

    private void medium(){
        mediumLevel = new JButton("MEDIUM");
        mediumLevel.setBackground(Color.orange);

        add(mediumLevel,c);
        mediumLevel.addActionListener(e -> {
            mediumChecked = true;
            Window.frame.getContentPane().removeAll();
            Window.frame.getContentPane().add(game);
            Window.frame.addKeyListener(game);
            game.setFocusable(true);
            game.requestFocusInWindow();
            Window.frame.revalidate();
        });
    }

    private void hard(){
        hardLevel = new JButton("HARD");
        hardLevel.setBackground(Color.red);

        add(hardLevel,c);
        hardLevel.addActionListener(e -> {
            hardChecked = true;
            Window.frame.getContentPane().removeAll();
            Window.frame.getContentPane().add(game);
            Window.frame.addKeyListener(game);
            game.setFocusable(true);
            game.requestFocusInWindow();
            Window.frame.revalidate();
        });

    }

}
