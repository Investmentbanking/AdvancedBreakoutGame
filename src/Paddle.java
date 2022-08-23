import java.awt.*;

public class Paddle extends GameObject{


    /**
     *
     * @param xPosition The x position of the paddle on the window.
     * @param yPosition The y position of the paddle on the window.
     * @param xSpeed The x speed of the paddle.
     * @param ySpeed The y speed of the paddle.
     * @param width The width of the paddle.
     * @param height The height of the paddle.
     * @param id The id of the game object (brick, paddle or ball) to make sure its a paddle.
     * @param handler An instance of handler class which handles the game objects.
     */
    public Paddle(int xPosition, int yPosition, int xSpeed, int ySpeed, int width, int height, ID id, Handler handler) {
        super(xPosition, yPosition, xSpeed, ySpeed, width, height, id, handler);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        // for the paddle
        g.fillRect(xPosition, yPosition, width, height);
    }

    @Override
    public void updateLogic() {
        xPosition += xSpeed;
        xPosition = clamp(xPosition, 0, 385);
    }

    public int clamp(int val, int min, int max){
        if(val <= min){
            return min;
        }
        else return Math.min(val, max);
    }

    public Rectangle getBounds(){
        return new Rectangle(xPosition,yPosition,width,height);
    }
}
