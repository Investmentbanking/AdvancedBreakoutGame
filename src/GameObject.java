import java.awt.*;

/**
 * The game objects in the game. Which include the ball, paddle and bricks.
 * Every game object will be responsible for drawing itself
 * Cannot create an instance of game object, must be inherited
 *
 */
public abstract class GameObject {

    protected int xPosition;
    protected int yPosition;
    protected int xSpeed;
    protected int ySpeed;
    protected int width;
    protected int height;
    protected ID id;
    protected Handler handler;

    public GameObject(int xPosition, int yPosition, int xSpeed, int ySpeed, int width, int height, ID id, Handler handler){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.width = width;
        this.height = height;
        this.id = id;
        this.handler = handler;
    }

    public abstract void render(Graphics g);

    public abstract void updateLogic();

    public ID getID() {
        return id;
    }

    public void setXSpeed(int xSpeed){
        this.xSpeed = xSpeed;
    }

}
