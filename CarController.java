import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

/*
 * This class represents the Controller part in the MVC pattern.
 * It's responsibilities is to listen to the View and responds in a appropriate manner by
 * modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with an listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    public static ArrayList<Vehicle> cars = new ArrayList<>();
    //methods:

    public static void main(String[] args) throws IOException {
        // Instance of this class
        CarController cc = new CarController();

        cc.cars.add(new Volvo240());
        cc.cars.add(new Saab95());
        cc.cars.add(new Scania());

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();

        for(Vehicle c :cars){
            DrawPanel.carPoint.add(new Point());
        }

    }

    /* Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < cars.size(); i++) {
                cars.get(i).move();
                int x = (int) Math.round(cars.get(i).getPosition().getX());
                int y = (int) Math.round(cars.get(i).getPosition().getY());
                frame.drawPanel.moveIt(x, y, i);
                // repaint() calls the paintComponent method of the panel
                if((x > 684 && cars.get(i).getDirection() == Dir.RIGHT) || (x <= 0 && cars.get(i).getDirection() == Dir.LEFT)) {
                    cars.get(i).turnRight();
                    cars.get(i).turnRight();
                }
                frame.drawPanel.repaint();

            }
            //System.out.print( cars.get(0).getCurrentSpeed());
        }
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Vehicle car : cars) {
            car.gas(gas);
        }
    }

    void brake(int amount) {
        double gas = ((double) amount) / 100;
        for (Vehicle car : cars) {
            car.brake(gas);
        }
    }

    void startEngine() {
        for (Vehicle car : cars) {
            car.startEngine();
            System.out.print("Engine running");
        }
    }

    void stopEngine() {
        for (Vehicle car : cars) {
            car.stopEngine();
            while(car.getCurrentSpeed()>0){
                car.brake(0.01);
            }
            System.out.print("Engine not running");
        }
    }

    void setTurboOff() {
        for (Vehicle car : cars) {
            if(car.getClass() == Saab95.class) {
                ((Saab95)car).setTurboOff();
            }
        }
    }

    void setTurboOn() {
        for (Vehicle car : cars) {
            if(car.getClass() == Saab95.class) {
                ((Saab95)car).setTurboOn();
            }
        }
    }

    void lowerFleet() {
        for (Vehicle car : cars) {
            if (car.getClass() == Scania.class && car.getCurrentSpeed() == 0) {
                ((Scania)car).changeFleetAngle(70);
                System.out.print("Fleet lowered");
            }
        }
    }

    void raiseFleet() {
        for (Vehicle car : cars) {
            if (car.getClass() == Scania.class) {
                ((Scania)car).changeFleetAngle(0);
                System.out.print("Fleet raised");
            }
            System.out.print(car.getCurrentSpeed());
        }
    }



}