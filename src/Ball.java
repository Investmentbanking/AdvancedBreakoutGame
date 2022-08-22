import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ball extends GameObject{


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
    
    public void startBall(){
        xSpeed = 4;
        ySpeed = 5;
    }
    
    public void stopBall(){
        xSpeed = 0;
        ySpeed = 0;
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
     * Returning the area where you have your circle
     *
     * @return
     */
    public Ellipse2D.Double getBounds(){
        return new Ellipse2D.Double(xPosition,yPosition,width, height);
    }
}
