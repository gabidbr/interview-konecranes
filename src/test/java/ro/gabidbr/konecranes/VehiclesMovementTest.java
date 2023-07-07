package ro.gabidbr.konecranes;

import ro.gabidbr.konecranes.models.Vehicle;
import ro.gabidbr.konecranes.service.VehiclesService;

import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class VehiclesMovementTest {

    public static void main(String[] args) throws InterruptedException {
        VehiclesService vehiclesService = new VehiclesService();

        vehiclesService.addVehicle(0, 0, Vehicle.Direction.UP);
        vehiclesService.addVehicle(20, 20, Vehicle.Direction.LEFT);
        vehiclesService.addVehicle(40, 40, Vehicle.Direction.RIGHT);

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println("\nVehicles positions:");
            Map<Integer, Vehicle> allVehicles = vehiclesService.getAllVehicles();
            allVehicles.forEach((integer, vehicle) -> {
                System.out.println(" Vehicle ID: " + integer + " X: "
                        + vehicle.getXPosition() + " Y: " + vehicle.getYPosition());
            });
        }, 0, 2, TimeUnit.SECONDS);

        Thread.sleep(30000);

        scheduledExecutorService.shutdown();
    }
}
