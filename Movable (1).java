/**
 * a interface for all shared code with all objects that can move
 */

public interface Movable {

    /**
     * a method that moves the movable object
     */
    void move();

    /**
     * a method that turns the movable objects one step towards the left
     */
    void turnLeft();

    /**
     * a method that turns the movable objects one step towards the right
     */
    void turnRight();

    /**
     * @param amount increases speed of vehicle by factor(0-1)
     */
    void gas(double amount);

    /**
     * @param amount decreases speed of vehicle by factor(0-1)
     */
    void brake(double amount);
}
