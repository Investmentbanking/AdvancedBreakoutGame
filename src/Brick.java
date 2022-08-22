import java.awt.*;
import java.util.ArrayList;

/**
 * This class handles everything to do with the Brick object
 *
 */

public abstract class Brick extends GameObject {

    public void updateLogic() {
        ArrayList<Ball> balls = handler.getBalls();
        for(int i = 0; i < balls.size(); i++) {
            Ball ball = balls.get(i);
            checkCollision(ball);
        }
    }

    protected ColorID colorID;

    // the number of hits a brick takes
    protected int hits;

    public Brick(int xPosition, int yPosition, int xSpeed, int ySpeed, int width, int height, ID id, Handler handler, ColorID colorID, int hits) {
        super(xPosition, yPosition, xSpeed, ySpeed, width, height, id, handler);
        this.colorID = colorID;
        this.hits = hits;
    }


    public boolean checkCollision(Ball ball){
        if(!checkTopAndBottomCollision(ball)){
            checkRightAndLeftCollision(ball);
        }
        return false;
    }

    /**
     *
     * @param ball
     * @return
     */

    public abstract boolean checkTopAndBottomCollision(Ball ball);
    public abstract boolean checkRightAndLeftCollision(Ball ball);

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    /**
     * This paints each individual brick depending on the number of hits
     * @param g Graphics card
     */
    public void paintBrick(Graphics g){
        if(hits == 2) {
            g.setColor(Color.pink);
        }
        else if (hits == 1) {
            g.setColor(Color.white);
        }
        else {
            return;
        }
        g.fillRect(xPosition, yPosition, width, height);
    }

    public Rectangle getBounds(){
        return new Rectangle(xPosition,yPosition,width,height);
    }
    public Rectangle getTopBound(){
        return new Rectangle(xPosition,yPosition,width,1);
    }
    public Rectangle getBottonBound(){
        return new Rectangle(xPosition,yPosition+height-1, width, 1);
    }
    public Rectangle getRightBound(){
        return new Rectangle(xPosition+width-1,yPosition,1,height);
    }
    public Rectangle getLeftBound(){
        return new Rectangle(xPosition,yPosition,1, height);
    }

}
