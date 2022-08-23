import java.awt.*;
import java.util.ArrayList;

/**
 * Handler class handles all the game objects in the window which are the ball
 *
 */
public class Handler {

    // Stores all the game objects
    ArrayList<GameObject> objects = new ArrayList<>();

    /**
     * Adds a game object to the Array List.
     *
     * @param object The object to be added.
     */
    public void addGameObjects(GameObject object){
        objects.add(object);
    }

    /**
     * Removes a game object from the Array List.
     *
     * @param object The object to be removed.
     */
    public void removeGameObjects(GameObject object){
        objects.remove(object);
    }

    /**
     * Iterating through the objects ArrayList, checking if it is a ball
     * if so, add to balls ArrayList.
     *
     * @return balls The Array List of balls.
     */
    public ArrayList<Ball> getBalls(){
        ArrayList<Ball> balls = new ArrayList<>();
        for (GameObject temp : objects) {
            if (temp.getID() == ID.BALL) { // if it is a ball
                balls.add((Ball) temp);
            }
        }
        return balls;
    }

    public void render(Graphics g){
        for (GameObject temp : objects) {
            temp.render(g);
        }
    }

    public Paddle getPaddle(){
        for (GameObject temp : objects) {
            if (temp.getID() == ID.PADDLE) {
                return (Paddle) temp;
            }
        }
        return null;
    }

    /**
     * Updates the logic for all game objects separately
     */
    public void updateLogic(){
        for(int i =0; i< objects.size();i++){
            GameObject temp = objects.get(i);
            temp.updateLogic();
        }
    }

    public ArrayList<Brick> getBricks() {
        ArrayList<Brick> bricks = new ArrayList<>();
        for (GameObject temp : objects) {
            if (temp.getID() == ID.BRICK) {
                bricks.add((Brick) temp);
            }
        }
        return bricks;
    }
}
