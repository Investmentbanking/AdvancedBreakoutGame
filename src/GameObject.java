import java.awt.*;

/**
 * The game objects in the game. Which include the ball, paddle and bricks.
 * Every game object will be responsible for drawing itself.
 * Cannot create an instance of game object, must be inherited.
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

    /**
     * A game object in the game which has these components in common.
     *
     * @param xPosition The x position of the game object on the window.
     * @param yPosition The y position of the game object on the window.
     * @param xSpeed The x speed of the game object.
     * @param ySpeed The y speed of the game object.
     * @param width The width of the game object.
     * @param height The height of the game object.
     * @param id The id of the game object (brick, paddle or ball).
     * @param handler An instance of handler class which handles the game objects.
     */
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

    /**
     * Creates/draws the game objects.
     *
     * @param g The graphics card.
     */
    public abstract void render(Graphics g);

    /**
     * Updates the logic in the game.
     */
    public abstract void updateLogic();

    /**
     * Gets the ID of the game object
     *
     * @return id The ID of the game object.
     */
    public ID getID() {
        return id;
    }

    /**
     * Sets the x speed of the game object.
     *
     * @param xSpeed The xSpeed of the game object.
     */
    public void setXSpeed(int xSpeed){
        this.xSpeed = xSpeed;
    }
}
