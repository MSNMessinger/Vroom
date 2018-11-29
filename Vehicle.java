import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


/**
 * A enum class Dir that specifies the 4 direction in which a car can face and therefore move in
 */
enum Dir {
    UP, RIGHT, DOWN, LEFT
}

enum VehicleType {
    CAR, CARWITHTURBO, TRUCK, TRUCKWITHFLEET, MOTHERTRUCK, MOTHERBOAT
}
/**
 * An abstract class to reduce code-redundancy in sub-classes of Vehicle (such as Saab95 and Volvo240). A car consists
 * of a number of different variables such as currentSpeed, direction, enginePower and color (to name a few). It does
 * most of the things an actual car can do in order to abstract the functions of a car.
 */

public class Vehicle implements Movable {
    private int nrDoors; // Number of doors on the car
    private double enginePower; // Engine power of the car
    private double currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    private String modelName; // The car model name
    private Point position;
    private Dir direction;
    private boolean engineRunning;
    private VehicleType vehicleType;
    private BufferedImage vehicleImg;
    private static ArrayList<Dir> dirs = new ArrayList<Dir>(){{
        add(Dir.UP);
        add(Dir.RIGHT);
        add(Dir.DOWN);
        add(Dir.LEFT);
    }};

    /**
     * Sets values of
     * @param nrDoors the number of doors a car have
     * @param enginePower the engine power and max speed of the car
     * @param color the color of the car
     * @param modelName the modelname of the car
     * @param position the current position of the car
     * at initiation.
     */

    public Vehicle(int nrDoors, double enginePower, Color color, String modelName, Point position, VehicleType vehicleType, BufferedImage img){
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        this.position = position;
        this.direction = Dir.RIGHT;
        this.engineRunning = false;
        this.vehicleType = vehicleType;
        this.vehicleImg = img;
        stopEngine();
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public BufferedImage getVehicleImg() {
        return vehicleImg;
    }

    /**
     *
     * @return the model name of the car
     */
    protected String getModelName() {
        return modelName;
    }

    /**
     *
     * @return the number of doors a car has
     */
    private int getNrDoors() {
        return nrDoors;
    }

    /**
     *
     * @return a double describing the enginePower of the car, which defines the Vehicle's top speed.
     */
    protected double getEnginePower() {
        return enginePower;
    }

    /**
     * @return the direction the car is currently facing in
     */
    public Dir getDirection() { return direction; }

    /**
     * @return the current speed of the car
     */
    public double getCurrentSpeed() {
        return currentSpeed;
    }

    /**
     * sets the current speed of the car to 0.1.
     */
    public void startEngine(){
        engineRunning = true;
    }

    /**
     * sets the current speed of the car to 0.0
     */
    public void stopEngine(){
        engineRunning = false;
    }

    /**
     *
     * @return a specific value in the specific sub-classes that is used to calculate the next currentSpeed when
     * the car accelerates or breaks.
     */

    protected double speedFactor() {
        return 1;
    }

    /**
     *
     * @return the current position of the car in a coordinate system.
     */
    protected Point getPosition() {
        return position;
    }


    /**
     *
     * @param amount an amount (ranging from 0-1) that multiplied with the speedFactor specified in the dynamic-type
     *               of the car and increases the currentSpeed with that amount to no less than zero and no more than
     *               enginePower.
     */
    private void incrementSpeed(double amount){
        double tmp = getCurrentSpeed() + speedFactor() * amount;
        if (tmp > enginePower){
            tmp = enginePower;
        } else if (tmp < 0){
            tmp = 0;
        }
        System.out.print("speedFactor:"+ speedFactor());
        currentSpeed = tmp;
    }

    /**
     *
     * @param amount an amount (ranging from 0-1) that multiplied with the speedFactor specified in the dynamic-type
     *      *               of the car and decreases the currentSpeed with that amount to no less than zero and no
     *              more than enginePower.
     */
    private void decrementSpeed(double amount){

        double tmp = getCurrentSpeed() - speedFactor() * amount;
        if (tmp > enginePower){
            tmp = enginePower;
        } else if (tmp < 0){
            tmp = 0;
        }
        currentSpeed = tmp;
    }

    /**
     * sets a new x - or - y coordinate to the cars Point in the coordinate system, and therefore moving the car
     * in that space
     */
    @Override
    public void move(){
        if (direction == Dir.RIGHT){
            position.setLocation(position.x + getCurrentSpeed(), position.y);
        } else if (direction == Dir.UP){
            position.setLocation(position.x, position.y-getCurrentSpeed());
        } else if (direction == Dir.LEFT){
            position.setLocation(position.x - getCurrentSpeed(), position.y);
        }   else {
            position.setLocation(position.x, position.y + getCurrentSpeed());
        }
    }

    /**
     * sets the car's direction to the direction directly left of it in the list and loops the list when needed to
     */
    @Override
    public void turnLeft(){
        int i = dirs.indexOf(direction);
        i--;
        if (i < 0){
            i = 3;
        }
        this.direction = dirs.get(i);
    }

    /**
     * sets the car's direction to the direction directly left of it in the list and loops the list when needed to
     */
    @Override
    public void turnRight(){
        int i = dirs.indexOf(direction);
        i++;
        this.direction = dirs.get(i%dirs.size());
    }

    /**
     * @param amount the amount that the user wants to increase the speed with
     * @return an amount in the span of 0 to 1 in order to have a realistic acceleration of the car. If amount
     * is greater than 1 -> return 1. If amount is lesser than 0 -> return 0. Else, return amount.
     */
    private double confirmAmount (double amount){
        if (amount < 0) {
            amount = 0;
        } else if (amount > 1){
            amount = 1;
        }

        return amount;
    }

    /**
     * @param amount increases speed of vehicle by factor(0-1)
     */
    @Override
    public void gas(double amount){
        if (engineRunning == true) {
            incrementSpeed(confirmAmount(amount));
        }
    }

    /**
     * @param amount decreases speed of vehicle by factor(0-1)
     */
    @Override
    public void brake(double amount){
        decrementSpeed(confirmAmount(amount));
    }

}
