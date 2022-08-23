import java.awt.*;
import java.util.ArrayList;

/**
 * This class handles everything to do with the Brick object
 *
 */

public abstract class Brick extends GameObject {

    protected ColorID colorID;
    // the number of hits a brick takes
    protected int hits;

    /**
     *
     * @param xPosition The x position of the brick on the window.
     * @param yPosition The y position of the brick on the window.
     * @param xSpeed The x speed of the brick.
     * @param ySpeed The y speed of the brick.
     * @param width The width of the brick.
     * @param height The height of the brick.
     * @param id The id of the game object (brick, paddle or ball) to make sure its a brick.
     * @param handler An instance of handler class which handles the game objects.
     * @param colorID The color of the brick.
     * @param hits The number of times a brick gets hit.
     */
    public Brick(int xPosition, int yPosition, int xSpeed, int ySpeed, int width, int height, ID id, Handler handler, ColorID colorID, int hits) {
        super(xPosition, yPosition, xSpeed, ySpeed, width, height, id, handler);
        this.colorID = colorID;
        this.hits = hits;
    }

    /**
     * Gets the balls from the general objects Array List in handler class and stores in balls
     * Array List, checks for collision with
     *
     */
    public void updateLogic() {
        ArrayList<Ball> balls = handler.getBalls();
        for(int i = 0; i < balls.size(); i++) {
            Ball ball = balls.get(i);
            checkCollision(ball);
        }
    }

    /**
     *
     * @param ball
     * @return
     */
    public boolean checkCollision(Ball ball){
        if(!checkTopAndBottomCollision(ball)){
            checkRightAndLeftCollision(ball);
        }
        return false;
    }

    public abstract boolean checkTopAndBottomCollision(Ball ball);
    public abstract boolean checkRightAndLeftCollision(Ball ball);

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }


    /**
     * Gets the bounds of the brick.
     *
     * @return The new rectangle with a given x position, y position and size.
     */
    public Rectangle getBounds(){
        return new Rectangle(xPosition,yPosition,width,height);
    }

    /**
     * Gets the top bound of the brick.
     *
     * @return The new rectangle with height of 1 which covers the top bound of the brick.
     */
    public Rectangle getTopBound(){
        return new Rectangle(xPosition,yPosition,width,1);
    }

    /**
     * Gets the bottom bound of the brick.
     *
     * @return The new rectangle with a height of 1.
     */
    public Rectangle getBottomBound(){
        return new Rectangle(xPosition,yPosition+height-1, width, 1);
    }

    /**
     * Gets the right bound of the brick.
     *
     * @return The new rectangle with a width of 1.
     */
    public Rectangle getRightBound(){
        return new Rectangle(xPosition+width-1,yPosition,1,height);
    }

    /**
     * Gets the left bound of the brick.
     *
     * @return The new rectangle with a width of 1.
     */
    public Rectangle getLeftBound(){
        return new Rectangle(xPosition,yPosition,1, height);
    }

}
