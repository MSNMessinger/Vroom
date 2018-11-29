import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * A specific subclass of the superclass Vehicle that has all the same variables and a final static double "trimFactor"
 * that specifies by how much "more" the car accelerates/decelerates when the car gases/break.
 */

public class Volvo240 extends Vehicle {

    /**
     * a double that represents how much more a Volvo240 will accelerate/decelerate when gasing/breaking
     */

    private final static double trimFactor = 1.25;

    /**
     * a constructor to initialize a volvo240 object that uses the super-constructor in order the construct.
     */
    public Volvo240() throws IOException {
        super(4, 100, Color.black, "Volvo240", new Point(1, 1), VehicleType.CAR, ImageIO.read(new File("src\\pics\\Volvo240.jpg")));
    }

    /**
     * A function in order to calculate the speedFactor of a car that is used when the car gases or breaks in order to
     *  calculate the next currentSpeed.
     *  @return the speedFactor of the car.
     *
     */
    protected double speedFactor(){
        return getEnginePower() * 0.01 * trimFactor;
    }

}