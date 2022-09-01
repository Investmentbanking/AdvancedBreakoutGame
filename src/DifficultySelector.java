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
    JButton back;

    public static boolean easyChecked = false;
    public static boolean mediumChecked = false;
    public static boolean hardChecked = false;

    public DifficultySelector(){

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
        //backButton();
    }

    public void backButton(){
        back = new JButton("Back");
        add(back,c);
        back.addActionListener(e -> {
            Window.cardLayout.previous(Window.cardPane);
        });
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


    private void option1Ticked(){
        if(BackgroundSelector.option1) {
            newGame.changeFilepath("space.jpg");
        }
    }

    private void option2Ticked(){
        if(BackgroundSelector.option2) {
            newGame.changeFilepath("bricks.jpg");
        }
    }

    private void option3Ticked(){
        if(BackgroundSelector.option3) {
            newGame.changeFilepath("stars.jpg");
        }
    }

    private void option4Ticked(){
        if(BackgroundSelector.option4) {
            newGame.changeFilepath("bubbles.jpg");
        }
    }

    private void option5Ticked(){
        if(BackgroundSelector.option5) {
            newGame.changeFilepath("forest.jpg");
        }
    }

    private void option6Ticked(){
        if(BackgroundSelector.option6) {
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

            //newGame.changeFilepath("purple.jpg");
            newGame = new GameWindow();
            option1Ticked();
            option2Ticked();
            option3Ticked();
            option4Ticked();
            option5Ticked();
            option6Ticked();
            Window.cardPane.add(newGame, "new Game");
            Window.cardLayout.show(Window.cardPane, "new Game");

            setVariablesFalse();
        });
        Window.cardPane.remove(newGame);
        //setVariablesFalse();
    }

    private void medium(){
        mediumLevel = new JButton("MEDIUM");
        mediumLevel.setBackground(Color.orange);

        add(mediumLevel,c);
        mediumLevel.addActionListener(e -> {
            mediumChecked = true;
            newGame = new GameWindow();
            option1Ticked();
            option2Ticked();
            option3Ticked();
            option4Ticked();
            option5Ticked();
            option6Ticked();
            option2Ticked();

            Window.cardPane.add(newGame, "new Game");
            Window.cardLayout.show(Window.cardPane, "new Game");
            setVariablesFalse();
        });
        Window.cardPane.remove(newGame);
        //setVariablesFalse();
    }

    private void hard(){
        hardLevel = new JButton("HARD");
        hardLevel.setBackground(Color.red);

        add(hardLevel,c);
        hardLevel.addActionListener(e -> {
            hardChecked = true;
            newGame = new GameWindow();
            option1Ticked();
            option2Ticked();
            option3Ticked();
            option4Ticked();
            option5Ticked();
            option6Ticked();
            option2Ticked();
            Window.cardPane.add(newGame, "new Game");
            Window.cardLayout.show(Window.cardPane, "new Game");
            setVariablesFalse();

        });
        Window.cardPane.remove(newGame);
        //setVariablesFalse();
    }

}
