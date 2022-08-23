import java.awt.*;

public class NormalBrick extends Brick {


    public NormalBrick(int xPosition, int yPosition, int xSpeed, int ySpeed, int width, int height, ID id, Handler handler, ColorID colorID) {
        super(xPosition, yPosition, xSpeed, ySpeed, width, height, id, handler, colorID, 1);
    }

    @Override
    public void render(Graphics g) {
        switch(colorID){
            case RED -> g.setColor(Color.red);
            case CYAN -> g.setColor(Color.cyan);
            case WHITE -> g.setColor(Color.white);
            case ORANGE -> g.setColor(Color.YELLOW);
            case GREEN -> g.setColor(Color.green);
        }
        g.fillRect(xPosition,yPosition,width,height);
    }

    public boolean checkTopAndBottomCollision(Ball ball){
        if(ball.getBounds().intersects(getTopBound()) || ball.getBounds().intersects(getBottomBound())){
            // invert y velocity for ball
            ball.invertY();
            handler.removeGameObjects(this);
            return true;
        }
        return false;
    }

    public boolean checkRightAndLeftCollision(Ball ball){
        if(ball.getBounds().intersects(getLeftBound()) || ball.getBounds().intersects(getRightBound())){
            // invert x velocity for ball
            ball.invertX();
            handler.removeGameObjects(this);
            return true;
        }
        return false;
    }
}
