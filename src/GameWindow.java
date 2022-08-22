import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.FlatteningPathIterator;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;

/**
 * This class creates the game window and all necessary components of the game,
 * including the bricks, ball, paddle and the JComponents like the JButton
 *
 */
public class GameWindow extends JPanel implements Runnable, KeyListener, ActionListener {

    private Handler handler;
    private boolean gameRunning = false;
    private final static int BOARD_WIDTH = 500;
    private final static int BOARD_HEIGHT = 500;

    public int paddleX = BOARD_WIDTH/2;
    public int paddleY = BOARD_HEIGHT-50;

    private int paddleW = 100;
    private int paddleH = 10;

    public boolean paddleLeft = false;
    public boolean paddleRight = false;
    public boolean message;
    private Timer timer;
    private int delay = 30;
    private static int lives;

    private int limit = 3;
    private int count = 0;

    private JButton pause;
    // default image
    private ClassLoader cl = Thread.currentThread().getContextClassLoader();
    private Image image = Toolkit.getDefaultToolkit().getImage(cl.getResource("space.jpg"));

    public void changeFilepath(String filepath){
        this.image = Toolkit.getDefaultToolkit().getImage(cl.getResource(filepath));
    }

    public GameWindow(){
        lives = 3;
        handler = new Handler();
        handler.addGameObjects(new Ball(20,200,0,0,20,20,ID.BALL,handler));
        handler.addGameObjects(new Paddle(250,450,0,0,100,10,ID.PADDLE, handler));

        pause = new JButton("Pause");
        pause.setFont(new Font("Arial", Font.BOLD, 9));
        pause.setBackground(Color.pink);
        pause.addActionListener(e -> {
            try {
                pauseGame();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        add(pause);

        //addButton();
        addKeyListener(this);
        //addMouseListener(this);
        setFocusable(true);
        //dimension = new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
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

        // pink bricks
        int pinkX = 75;
        int pinkY = 100; //80
        for(int i = 0; i < 4; i++){
            handler.addGameObjects(new ColorChangingBrick(pinkX, pinkY,0,0,50,10,ID.BRICK, handler,ColorID.PINK,2));
            pinkX += 110;
        }

        timer = new Timer(delay, this);
        timer.start();
        setDoubleBuffered(true);
    }


    /**
     * Pauses the game and shows a dialog box in which user can choose to continue game
     * or quit which goes back to main menu
     *
     * @throws InterruptedException
     */
    public void pauseGame() throws InterruptedException {
        timer.stop();
        int number = new JOptionPanePause().displayGUI();
        if(number == 0){
            timer.start();
            setFocusable(true);
           requestFocusInWindow();
        }
        else{
            Thread thread = new Thread(this);
            thread.start();
        }
    }

    public static int getLives(){
        return lives;
    }

    public static void setLives(int totalLives){
        lives = totalLives;
    }

    /**
     * Draws the countdown from 3 to 1 then starts the game.
     *
     * @param g Graphics card
     * @throws InterruptedException
     */
    public void drawNumber(Graphics g) throws InterruptedException {
        paddleLeft = false;
        paddleRight = false;
        Font f = new Font("Comic Sans MS", Font.BOLD, 40);
        g.setFont(f);
        g.drawString(String.format("%s", limit), 300, 300);
        limit--;
        if(limit == -1) {
            gameRunning = true;
            ArrayList<Ball> balls = handler.getBalls();
            for(int i=0; i<balls.size(); i++){
                Ball temp = balls.get(i); // gets each ball in arraylist and starts it
                temp.startBall();
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
        Font f = new Font("Comic Sans MS", Font.BOLD, 10);
        g.setColor(Color.white);
        g.setFont(f);
        g.drawString(String.format("%s", "Lives: " + getLives()), 300,10);
    }

    public void youLoseMessage(Graphics g) throws InterruptedException {
        if(message == true) {
            Font f = new Font("Comic Sans MS", Font.BOLD, 20);
            g.setColor(Color.white);
            g.setFont(f);
            g.drawString(String.format("%s", "YOU HAVE 1 LIFE LEFT!"), 150, 300);
        }
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
                for(int i=0;i<balls.size();i++){
                    Ball temp = balls.get(i);
                    temp.stopBall();
                }
                Thread thread = new Thread(this);
                thread.start(); // calls the run() method which essentially goes back to main menu
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //g.dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        Paddle paddle = handler.getPaddle();
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                paddle.setXSpeed(5);
                repaint();

        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            paddle.setXSpeed(-5);
            repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Paddle paddle = handler.getPaddle();
        paddle.setXSpeed(0);
        repaint();
    }


    public void mainMenu(){
        Window.frame.getContentPane().removeAll();
        Window.frame.getContentPane().add(Window.main);
        Window.frame.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        handler.updateLogic();

        if (handler.getBricks().size() == 0) {
            System.out.println("YOU WIN!");
            Thread thread = new Thread(this);
            thread.start();
        }
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
        Window.frame.repaint();
    }
}