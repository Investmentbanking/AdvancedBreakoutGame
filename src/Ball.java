import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Ball class which creates the ball and controls what happens when ball collides with the window
 * frame and paddle. Also updates the logic of the ball.
 *
 */
public class Ball extends GameObject{

    /**
     *
     * @param xPosition The x position of the ball on the window.
     * @param yPosition The y position of the ball on the window.
     * @param xSpeed The x speed of the ball.
     * @param ySpeed The y speed of the ball.
     * @param width The width of the ball.
     * @param height The height of the ball.
     * @param id The id of the game object (brick, paddle or ball) to make sure its a ball.
     * @param handler An instance of handler class which handles the game objects.
     */
    public Ball(int xPosition, int yPosition, int xSpeed, int ySpeed, int width, int height, ID id, Handler handler) {
        super(xPosition, yPosition, xSpeed, ySpeed, width, height, id, handler);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        // for the ball
        g.fillOval(xPosition, yPosition, width, height);
    }

    @Override
    public void updateLogic() {
        xPosition += xSpeed;
        yPosition += ySpeed;

        checkCollision();
    }
    
    public void startBall(int speedX, int speedY){
        xSpeed = speedX;
        ySpeed = speedY;
    }

    public void easyLevelStartBall(){
        xSpeed = 10;
        ySpeed = 11;
    }
    
    public void stopBall(){
        xSpeed = 0;
        ySpeed = 0;
    }

    public int getXSpeed(){
        return xSpeed;
    }

    public int getYSpeed(){
        return ySpeed;
    }

    public void invertX() {
        xSpeed *= -1;
    }

    public void invertY() {
        ySpeed *= -1;
    }
    
    public void checkCollision(){
        if(xPosition < 0 || xPosition > 450){
            xSpeed *= -1;
        }
        if(yPosition < 0){
            ySpeed *= -1;

        }
        if(yPosition > 450){
            xPosition = 20;
            yPosition = 200;
            GameWindow.setLives(GameWindow.getLives()-1);
            ySpeed *= -1;

        }

        Paddle paddle = handler.getPaddle();
        if(xPosition == paddle.xPosition && yPosition == paddle.yPosition){
            ySpeed *= -1;
        }

        if(getBounds().intersects(paddle.getBounds())){
            ySpeed *= -1;
        }
    }

    /**
     * Returning the area where you have your ball.
     *
     * @return The bounds of the ball.
     */
    public Ellipse2D.Double getBounds(){
        return new Ellipse2D.Double(xPosition,yPosition,width, height);
    }
}
