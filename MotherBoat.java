import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Stack;

import static java.lang.Math.abs;

/**
 * Boat class that uses parent vehicle to inherit needed methods from vehicle class
 * Includes methods to lower and raise the fleet of the boat
 * Boat is a type of mother vehicle and therefor implements Interface mother vehicle, which gives the boat the ability to hold other vehicles
 */
public class MotherBoat extends Vehicle implements Movable, MotherVehicles {

    private boolean fleetDown;
    private ArrayDeque<Vehicle> vehicles;

    public MotherBoat() throws IOException {
        super(0, 80, Color.WHITE, "MotherFuckerBoat,", new Point(1, 1),  VehicleType.MOTHERBOAT, ImageIO.read(new File("src\\pics\\Saab95.jpg")));
        this.fleetDown = false;
        this.vehicles = new ArrayDeque<>();
    }

    /**
     * @return the current speed of the car
     */
    public double getCurrentSpeed() {
        return getCurrentSpeed();
    }

    /**
     * Lowers the fleet of the mother vehicle
     */
    @Override
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
    @Override
    public void raiseFleet() {
        fleetDown = false;
    }

    /**
     * @param vehicle, loads mother vehicle with param vehicle
     */
    @Override
    public void loadCar(Vehicle vehicle) {
        if (fleetDown) {
            if (abs(getPosition().x - vehicle.getPosition().x) < 10 && abs(getPosition().y - vehicle.getPosition().y) < 10 && vehicle != this) {
                vehicles.add(vehicle);
            }
        }
    }

    /**
     * Unloads all the cars from the mother, first in first out
     */
    @Override
    public void unloadAllCars() {
        if (fleetDown){
            while(!vehicles.isEmpty()) {
                vehicles.pop().getPosition().setLocation(getPosition().x + 10, getPosition().y + 10);
            }
        }
    }

    /**
     * a method that moves the movable object
     */
    @Override
    public void move() {
        if (!fleetDown) {
            move();
        }
    }

    public ArrayDeque getVechicles (){
        return vehicles;
    }

    public Point getPoint(){
        return getPosition();
    }
}
