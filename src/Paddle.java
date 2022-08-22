import java.awt.*;

public class Paddle extends GameObject{


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
        else if(val >= max){
            return max;
        }
        else {
            return val;
        }
    }

    public Rectangle getBounds(){
        return new Rectangle(xPosition,yPosition,width,height);
    }
}
