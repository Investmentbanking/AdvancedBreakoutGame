import java.awt.*;

public class ColorChangingBrick extends Brick {


    public ColorChangingBrick(int xPosition, int yPosition, int xSpeed, int ySpeed, int width, int height, ID id, Handler handler, ColorID colorID, int hits) {
        super(xPosition, yPosition, xSpeed, ySpeed, width, height, id, handler, colorID, hits);
    }

    @Override
    public boolean checkTopAndBottomCollision(Ball ball) {
        if((ball.getBounds().intersects(getTopBound()) || ball.getBounds().intersects(getBottonBound())) && colorID == ColorID.PINK){
            colorID = ColorID.WHITE;
            ball.invertY();
            return true;
        } else if ((ball.getBounds().intersects(getTopBound()) || ball.getBounds().intersects(getBottonBound())) && colorID == ColorID.WHITE) {
            // invert y velocity for ball
            ball.invertY();
            handler.removeGameObjects(this);
            return true;
        }
        return false;
    }

    @Override
    public boolean checkRightAndLeftCollision(Ball ball) {
        if ((ball.getBounds().intersects(getRightBound()) || ball.getBounds().intersects(getLeftBound())) && colorID == ColorID.PINK) {
            colorID = ColorID.WHITE;
            ball.invertX();
            return true;
        }
        if((ball.getBounds().intersects(getLeftBound()) || ball.getBounds().intersects(getRightBound())) && colorID == ColorID.WHITE){
            // invert x velocity for ball
            ball.invertX();
            handler.removeGameObjects(this);
            return true;
        }
        return false;
    }

    @Override
    public void render(Graphics g) {
        switch(colorID){
            case PINK -> g.setColor(Color.PINK);
            case WHITE -> g.setColor(Color.white);
        }
        g.fillRect(xPosition,yPosition,width,height);
    }
}
