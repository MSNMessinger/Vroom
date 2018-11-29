public interface MotherVehicles {
    /**
     * Lowers the fleet of the mother vehicle
     */
    void lowerFleet();

    /**
     * Raises the fleet of the mother vehicle
     */
    void raiseFleet();

    /**
     * @param vehicle, loads mother vehicle with param vehicle
     */
    void loadCar(Vehicle vehicle);

    /**
     * Unloads all the cars from the mother
     */
    void unloadAllCars();

}
