import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Car extends Vehicle {
    public Car(int nrDoors, double enginePower, Color color, String modelName, Point position, VehicleType vehicleType, BufferedImage img) {
        super(nrDoors, enginePower, color, modelName, position, vehicleType, img);
    }
}
