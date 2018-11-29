import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Stack;

import static java.lang.Math.abs;

/**
 * Truck class that uses parent vehicle to inherit needed methods from vehicle class
 * Includes methods to lower and raise the fleet of the truck
 * Truck is a type of mother vehicle and therefor implements Interface mother vehicle, which gives the truck the ability to hold other vehicles
 */
public class MotherTruck extends Vehicle implements Movable, MotherVehicles {

    private boolean fleetDown;
    //private Vehicle parentVehicle;
    private ArrayDeque<Vehicle> vehicles;

    public MotherTruck() throws IOException {
        super(2, 100, Color.BLACK, "MotherTruck,", new Point(1, 1), VehicleType.MOTHERTRUCK, ImageIO.read(new File("src\\pics\\Saab95.jpg")));
        this.fleetDown = false;
        this.vehicles = new ArrayDeque<>();
    }

    /**
     * Lowers the fleet of the mother vehicle
     */
    public void lowerFleet() {
        if(getCurrentSpeed() == 0){
            fleetDown = true;
        } else {
            System.out.print("Action can't be preformed due to vehicle is moving.");
        }
    }

    /**
     * Raises the fleet of the mother vehicle
     */
    public void raiseFleet() {
        fleetDown = false;
    }

    /**
     * @param vehicle, loads mother vehicle with param vehicle
     */
    public void loadCar(Vehicle vehicle) {
        if (fleetDown) {
            if ((getPosition().x - vehicle.getPosition().x) < 10 || getPosition().x - vehicle.getPosition().x > -10 ) {
                if (getPosition().y - vehicle.getPosition().y < 10 || getPosition().y - vehicle.getPosition().y > 10) {

                    vehicles.push(vehicle);
                }
            }
        }
    }

    /**
     * Unloads all the cars from the mother, first in last out
     */
    public void unloadAllCars(){
        if (fleetDown){
                while(!vehicles.isEmpty()) {
                    vehicles.pop().getPosition().setLocation(getPosition().x + 10, getPosition().y + 10);
                }

        }
    }

    /**
     * @return the current speed of the car
     */
    public double getCurrentSpeed() {
        return getCurrentSpeed();
    }

    /**
     * a method that moves the movable object
     */
    @Override
    public void move() {
        if (!fleetDown){
            move();
            for (Vehicle c : vehicles){
              c.getPosition().setLocation(getPosition().x, getPosition().y);
            }
        }
    }

    /**
     * a method that turns the movable objects one step towards the left
     */
    @Override
    public void turnLeft() {
        turnLeft();
    }

    /**
     * a method that turns the movable objects one step towards the right
     */
    @Override
    public void turnRight() {
        turnRight();
    }

    /**
     * @param amount increases speed of vehicle by factor(0-1)
     */
    @Override
    public void gas(double amount) {
        gas(amount);
    }

    /**
     * @param amount decreases speed of vehicle by factor(0-1)
     */
    @Override
    public void brake(double amount) {
        brake(amount);
    }

    public ArrayDeque<Vehicle> getVehicles(){
        return vehicles;
    }

    public Point getPoint(){
        return getPosition();
    }
}
