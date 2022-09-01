import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * This class creates the game window and all necessary components of the game,
 * including the bricks, ball, paddle and the JComponents like the JButton
 *
 */
public class GameWindow extends JPanel implements Runnable, ActionListener {

    private final Handler handler;
    private boolean gameRunning = false;

    public boolean paddleLeft = false;
    public boolean paddleRight = false;
    private Timer timer;
    private int delay = 30;
    private static int lives;

    private int limit = 3;

    // default image
    private final ClassLoader cl = Thread.currentThread().getContextClassLoader();
    private Image image = Toolkit.getDefaultToolkit().getImage(cl.getResource("space.jpg"));


    public GameWindow() {
        lives = 3;
        handler = new Handler();
        handler.addGameObjects(new Ball(20,200,0,0,20,20,ID.BALL,handler));
        handler.addGameObjects(new Paddle(250,450,0,0,100,10,ID.PADDLE, handler));

        JButton pause = new JButton("Pause");
        pause.setFont(new Font("Arial", Font.BOLD, 15));
        pause.setBackground(Color.white);
        pause.setForeground(Color.black);
        pause.addActionListener(e -> {
            try {
                pauseGame();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        add(pause);

        setFocusable(true);
        setBackground(Color.BLACK);

        // normal bricks
        int x = 20;
        int y = 40; //20
        int count = 1;
        for(int i = 0; i < 24; i++){
            if(i%4 == 0){
                handler.addGameObjects(new NormalBrick(x,y,0,0,50,10,ID.BRICK,handler,ColorID.WHITE));
            }
            else if(i%4 == 1){
                handler.addGameObjects(new NormalBrick(x,y,0,0,50,10,ID.BRICK, handler,ColorID.CYAN));
            }
            else if(i%4 == 2){
                handler.addGameObjects(new NormalBrick(x, y, 0, 0, 50, 10, ID.BRICK, handler, ColorID.RED));
            }
            else{
                handler.addGameObjects(new NormalBrick(x,y,0,0,50,10,ID.BRICK, handler,ColorID.ORANGE));
            }
            x += 55;
            if (count%8 == 0){
                x = 20;
                y += 20;
            }
            count++;
        }

        // green bricks (normal bricks)
        int greenX = 20;
        int greenY = 100; // 80
        for(int i = 0; i < 4; i++){
            handler.addGameObjects(new NormalBrick(greenX, greenY,0,0,50,10,ID.BRICK, handler,ColorID.GREEN));
            greenX += 110;
        }

        // pink bricks which change color
        int pinkX = 75;
        int pinkY = 100; //80
        for(int i = 0; i < 4; i++){
            handler.addGameObjects(new ColorChangingBrick(pinkX, pinkY,0,0,50,10,ID.BRICK, handler,ColorID.PINK,2));
            pinkX += 110;
        }

        timer = new Timer(delay, this);
        timer.start();
        setDoubleBuffered(true);

        movePaddle();
    }

    public void movePaddle() {
        String actionId = "left";
        KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false);
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, actionId);
        getActionMap().put(actionId, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Paddle paddle = handler.getPaddle();
                    paddle.setXSpeed(-5);
                    repaint();
            }
        });
        String actionId1 = "right";
        KeyStroke keyStroke1 = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,0,false);
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke1, actionId1);
        getActionMap().put(actionId1, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Paddle paddle = handler.getPaddle();
                paddle.setXSpeed(5);
                repaint();
            }
        });
        String actionId2 = "leftReleased";
        KeyStroke keyStroke2 = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,0,true);
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke2, actionId2);
        getActionMap().put(actionId2, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Paddle paddle = handler.getPaddle();
                paddle.setXSpeed(0);
                repaint();
            }
        });
        String actionId3 = "rightReleased";
        KeyStroke keyStroke3 = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,0,true);
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke3, actionId3);
        getActionMap().put(actionId3, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Paddle paddle = handler.getPaddle();
                paddle.setXSpeed(0);
                repaint();
            }
        });
    }

    /**
     * Changes the file path of the image.
     *
     * @param filepath The filepath to the image.
     */
    public void changeFilepath(String filepath){
        this.image = Toolkit.getDefaultToolkit().getImage(cl.getResource(filepath));
    }

    /**
     * Pauses the game and shows a dialog box in which user can choose to continue game
     * or quit which goes back to main menu.
     *
     * @throws InterruptedException
     */
    public void pauseGame() throws InterruptedException {
        timer.stop();
        int number = new JOptionPanePause().displayGUI();
        if(number == 0) {
            timer.start();
            setFocusable(true);
            requestFocusInWindow();
        }
        else {
            DifficultySelector.easyChecked = false;
            DifficultySelector.mediumChecked = false;
            DifficultySelector.hardChecked = false;
            Thread thread = new Thread(this);
            thread.start();
        }
    }

    /**
     * Gets the current lives the user has in the game
     *
     * @return lives The number of lives.
     */
    public static int getLives(){
        return lives;
    }

    /**
     * Sets the number of lives the player has during game.
     *
     * @param totalLives The number of lives the player has in the game.
     */
    public static void setLives(int totalLives){
        lives = totalLives;
    }

    /**
     * Draws the countdown from 3 to 1 then when timer hits -1 it starts the game.
     *
     * @param g Graphics card.
     * @throws InterruptedException
     */
    public void drawNumber(Graphics g) throws InterruptedException {
        paddleLeft = false;
        paddleRight = false;
        Font f = new Font("Comic Sans MS", Font.BOLD, 40);
        g.setFont(f);
        g.setColor(Color.white);
        g.drawString(String.format("%s", limit), 300, 300);
        limit--;
        if(limit == -1) {
            gameRunning = true;
            // ADDED SPEED HERE // 4 5
            ArrayList<Ball> balls = handler.getBalls();
            // gets each ball in arraylist and starts it
            for (Ball temp : balls) {
                if(DifficultySelector.easyChecked){
                    temp.startBall(4,5);
                }
                else if (DifficultySelector.mediumChecked){
                    temp.startBall(9,10);
                }
                else if (DifficultySelector.hardChecked){
                    temp.startBall(15,16);
                }
            }
        }
        repaint();
        Thread.sleep(1000);
    }


    /**
     * The number of lives the player has. (Lives: ).
     *
     * @param g
     */
    public void lives(Graphics g) {
        Font f = new Font("Comic Sans MS", Font.BOLD, 20); // 10
        g.setColor(Color.white);
        g.setFont(f);
        g.drawString(String.format("%s", "Lives: " + getLives()), 400,25); // 300 10
    }

    public void setDelay(int delay){
        this.delay = delay;
    }
    public int getDelay(){
        return delay;
    }

    /**
     * Paints all the relevant shapes
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        image.getScaledInstance(500, 500,Image.SCALE_SMOOTH);
        while(!prepareImage(image, this)) {
            prepareImage(image, this);
        }
        g.drawImage(image,0,0,this);

        lives(g);

        handler.render(g);

        // paint the countdown
        if (!gameRunning) {
            try {
                drawNumber(g);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(getLives() == 0) {
            try {
                Font f = new Font("Comic Sans MS", Font.BOLD, 20);
                g.setColor(Color.white);
                g.setFont(f);
                g.drawString(String.format("%s", "YOU LOSE"), 150,300);

                ArrayList<Ball> balls = handler.getBalls();
                for (Ball temp : balls) {
                    temp.stopBall();
                }
                Thread thread = new Thread(this);
                thread.start(); // calls the run() method which essentially goes back to main menu
                DifficultySelector.easyChecked = false;
                DifficultySelector.mediumChecked = false;
                DifficultySelector.hardChecked = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(handler.getBricks().size() == 0){
            Font f = new Font("Comic Sans MS", Font.BOLD, 20);
            g.setColor(Color.white);
            g.setFont(f);
            g.drawString(String.format("%s", "CONGRATS YOU WIN!"), 150,300);
            ArrayList<Ball> balls = handler.getBalls();
            for (Ball temp : balls) {
                temp.stopBall();
            }
           Thread thread = new Thread(this);
           thread.start();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        handler.updateLogic();
        //
        repaint();
        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * Called when thread.start() is called, stops the timer and takes
     * user back to the main menu.
     */
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }

        timer.stop();
        Window.mainPanel();
    }
}