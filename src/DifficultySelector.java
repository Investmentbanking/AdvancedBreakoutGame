import javax.swing.*;
import java.awt.*;

/**
 *
 */
public class DifficultySelector extends JPanel {

    GameWindow newGame = new GameWindow();

    private final ClassLoader cl = Thread.currentThread().getContextClassLoader();
    private final Image image = Toolkit.getDefaultToolkit().getImage(cl.getResource("black.jpg"));

    GridBagConstraints c = new GridBagConstraints();

    JButton easyLevel;
    JButton mediumLevel;
    JButton hardLevel;
    JButton ultraHard;
    JButton impossible;
    JButton back;

    protected static boolean easyChecked = false;
    protected static boolean mediumChecked = false;
    protected static boolean hardChecked = false;
    protected static boolean ultraHardChecked = false;
    protected static boolean impossibleChecked = false;

    public DifficultySelector() {

        JLabel label = new JLabel("Please select the difficulty", JLabel.CENTER);
        label.setFont(new Font("Verdana", Font.BOLD, 18));
        label.setForeground(Color.white);

        setLayout(new GridBagLayout());

        c.weightx = 0;
        c.weighty = 0.1;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridwidth = GridBagConstraints.REMAINDER;

        add(label,c);
        easy();
        medium();
        hard();
        ultraHard();
        impossible();

        backButton();
    }

    public void backButton(){
        back = new JButton("Go back to Main Menu");
        back.setBackground(Color.gray);
        back.setForeground(Color.white);
        add(back,c);
        back.addActionListener(e -> {
              Window.cardLayout.show(Window.cardPane, "Main Menu");
              setVariablesFalse();
        });
        setVariablesFalse();
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


    private void optionTicked(){
        if(BackgroundSelector.option1) {
            newGame.changeFilepath("space.jpg");
        }
        else if(BackgroundSelector.option2) {
            newGame.changeFilepath("bricks.jpg");
        }
        else if(BackgroundSelector.option3) {
            newGame.changeFilepath("stars.jpg");
        }
        else if(BackgroundSelector.option4) {
            newGame.changeFilepath("bubbles.jpg");
        }
        else if(BackgroundSelector.option5) {
            newGame.changeFilepath("forest.jpg");
        }
        else if(BackgroundSelector.option6) {
            newGame.changeFilepath("purple.jpg");
        }
    }

    private void setVariablesFalse(){
        BackgroundSelector.option1 = false;
        BackgroundSelector.option2 = false;
        BackgroundSelector.option3 = false;
        BackgroundSelector.option4 = false;
        BackgroundSelector.option5 = false;
        BackgroundSelector.option6 = false;
    }

    private void easy(){
        easyLevel = new JButton("EASY");
        easyLevel.setBackground(Color.green);

        add(easyLevel,c);
        easyLevel.addActionListener(e -> {
            easyChecked = true;
            newGame = new GameWindow();
            optionTicked();
            Window.cardPane.add(newGame, "new Game");
            Window.cardLayout.show(Window.cardPane, "new Game");
            setVariablesFalse();
        });
        //Window.cardPane.remove(newGame);
    }

    private void medium(){
        mediumLevel = new JButton("MEDIUM");
        mediumLevel.setBackground(Color.orange);

        add(mediumLevel,c);
        mediumLevel.addActionListener(e -> {
            mediumChecked = true;
            newGame = new GameWindow();
            optionTicked();

            Window.cardPane.add(newGame, "new Game");
            Window.cardLayout.show(Window.cardPane, "new Game");
            setVariablesFalse();
        });
    }

    private void hard(){
        hardLevel = new JButton("HARD");
        hardLevel.setBackground(Color.red);

        add(hardLevel,c);
        hardLevel.addActionListener(e -> {
            hardChecked = true;
            newGame = new GameWindow();
            optionTicked();
            Window.cardPane.add(newGame, "new Game");
            Window.cardLayout.show(Window.cardPane, "new Game");
            setVariablesFalse();
        });
    }

    private void ultraHard(){
        ultraHard = new JButton("ULTRA HARD");
        ultraHard.setBackground(Color.blue);
        ultraHard.setForeground(Color.white);
        add(ultraHard,c);
        ultraHard.addActionListener(e -> {
            ultraHardChecked = true;
            newGame = new GameWindow();
            optionTicked();

            Window.cardPane.add(newGame, "new Game");
            Window.cardLayout.show(Window.cardPane, "new Game");
            setVariablesFalse();
        });
    }

    private void impossible(){
        impossible = new JButton("IMPOSSIBLE");
        impossible.setBackground(Color.black);
        impossible.setForeground(Color.white);
        add(impossible,c);
        impossible.addActionListener(e -> {
            impossibleChecked = true;
            newGame = new GameWindow();
            PanelController.newGame = newGame;
            optionTicked();
            Window.cardPane.add(newGame, "new Game");
            Window.cardLayout.show(Window.cardPane, "new Game");
            setVariablesFalse();
            //impossibleChecked = false;
        });
        //impossibleChecked = false;
    }
}
