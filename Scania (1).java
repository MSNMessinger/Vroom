import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Truck class that uses parent vehicle to inherit needed methods from  vehicle class
 * Includes methods to lower and raise the fleet of the truck
 */
public class Scania extends Vehicle implements Movable {

    private int fleetAngle;

    public Scania() throws IOException {
        super(2, 300, Color.green, "Scania", new Point(1, 150), VehicleType.TRUCKWITHFLEET, ImageIO.read(new File("src\\pics\\Scania.jpg")));
        this.fleetAngle = 0;
    }

    /**
     *
     * @param desiredAngle changes the angle of the fleet to the desired angle
     */
    public void changeFleetAngle(int desiredAngle){
        this.fleetAngle = confirmAngle(desiredAngle);
    }

    private int getFleetAngle() {
        return fleetAngle;
    }

    private int confirmAngle (int angle){
        if (angle > 70){
            angle = 70;
        } else if (angle < 0){
            angle = 0;
        }
        return angle;
    }

    /**
     * a method that moves the movable object
     */
    @Override
    public void move() {
        if (fleetAngle == 0){
            super.move();
        }
    }
}
