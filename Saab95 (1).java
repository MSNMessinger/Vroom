import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * A specific subclass of the superclass Vehicle that has all the same variables and a boolen "turboOn" that when on
 * increases the acceleration of the car.
 */

public class Saab95 extends Car {

    /**
     * when true increases the acceleration of the car
     */

    private boolean turboOn;


    /**
     * a constructor to initialize a saab95 object that uses the super-constructor in order the construct. It also sets
     * turboOn to false, meaning that the turbo isn't on when the car is created.
     */

    public Saab95() throws IOException {
        super(2, 125, Color.red, "Saab95", new Point(1, 300), VehicleType.CARWITHTURBO, ImageIO.read(new File("src\\pics\\Saab95.jpg")));
        turboOn = false;
    }

    public void setTurboOn() {
        turboOn = true;
        System.out.print("Turbo on!");
    }

    public void setTurboOff() {
        turboOn = false;
        System.out.print("Turbo off!");
    }

    /**
     * A function in order to calculate the speedFactor of a car that is used when the car gases or breaks in order to
     * calculate the next currentSpeed.
     *
     * @return the speedFactor of the car.
     */


    protected double speedFactor() {
        double turbo = 1;
        if (turboOn) {
            turbo = 1.3;
        }
        return getEnginePower() * 0.01 * turbo;
    }
}