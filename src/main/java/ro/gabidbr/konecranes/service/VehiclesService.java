package ro.gabidbr.konecranes.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ro.gabidbr.konecranes.models.Vehicle;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class VehiclesService {

    Logger logger = LoggerFactory.getLogger(VehiclesService.class);

    private final Map<Integer, Vehicle> vehicles = new ConcurrentHashMap<>();
    private final AtomicInteger idCounter = new AtomicInteger();

    public Vehicle addVehicle(int x, int y, Vehicle.Direction direction) {
        int newId = idCounter.incrementAndGet();
        String color = getRandomColor();
        Vehicle vehicle = new Vehicle(newId, x, y, color, direction);
        vehicles.put(newId, vehicle);
        moveVehicle(vehicle);
        return vehicle;
    }

    public Map<Integer, Vehicle> getAllVehicles(){
        return vehicles;
    }

    public void updateDirection(int id, Vehicle.Direction direction){
        Vehicle vehicle = vehicles.get(id);
        if(vehicle != null){
            vehicle.setDirection(direction);
        }
    }

    public void moveVehicle(Vehicle vehicle){
        Thread thread = new Thread(() -> {
            String threadName = Thread.currentThread().getName();
            while (!Thread.currentThread().isInterrupted()){
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }

                switch (vehicle.getDirection()){
                    case LEFT -> {
                        vehicle.setXPosition(vehicle.getXPosition()-1);
                    }
                    case RIGHT -> {
                        vehicle.setXPosition(vehicle.getXPosition()+1);
                    }
                    case UP -> {
                        vehicle.setYPosition(vehicle.getYPosition() - 1);
                    }
                    case DOWN -> {
                        vehicle.setYPosition(vehicle.getYPosition() + 1);
                    }
                }
                System.out.println("Moving Thread: " + threadName + " Vehicle ID: " + vehicle.getId() + " X: "
                        + vehicle.getXPosition() + " Y: " + vehicle.getYPosition());
            }
        });
        thread.start();
    }

    public void stopVehicle(Vehicle vehicle){
        System.out.println("Oprirea vehiculului: " + vehicle.getId());
        vehicle.setDirection(Vehicle.Direction.STOP);
    }

    public void stopMovingVehicles(){
        System.out.println("Oprire toate vehiculele");
        vehicles.forEach((integer, vehicle) -> vehicle.setDirection(Vehicle.Direction.STOP));
    }

    public Vehicle getVehicle(int id) {
        return vehicles.get(id);
    }

    public String getRandomColor() {
        Random random = new Random();
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        return String.format("#%02X%02X%02X", red, green, blue);
    }

}
