package ro.gabidbr.konecranes.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.gabidbr.konecranes.models.Vehicle;
import ro.gabidbr.konecranes.service.VehiclesService;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/vehicles")
@Tag(name = "Vehicles", description = "Manage vehicles on the track")
public class VehiclesController {

    public VehiclesService vehiclesService;

    public VehiclesController(VehiclesService vehiclesService) {
        this.vehiclesService = vehiclesService;
    }

    @GetMapping("/")
    @Operation(summary = "Load the home page with the list of vehicles")
    public String home(Model model) {
        model.addAttribute("vehicles", vehiclesService.getAllVehicles());
        return "index";
    }

    @PostMapping
    @Operation(summary = "Add a new vehicle")
    @ApiResponse(responseCode = "200", description = "Vehicle created successfully")
    public ResponseEntity<Vehicle> addVehicle(@RequestParam int x,
                                              @RequestParam int y,
                                              @RequestParam Vehicle.Direction direction) {
        Vehicle vehicle = vehiclesService.addVehicle(x, y, direction);
        return ResponseEntity.ok(vehicle);
    }

    @GetMapping
    @Operation(summary = "Get the list of vehicles")
    @ApiResponse(responseCode = "200", description = "List of vehicles retrieved successfully")
    public ResponseEntity<Map<Integer, Vehicle>> getAllVehicles() {
        Map<Integer, Vehicle> vehicles = vehiclesService.getAllVehicles();
        return ResponseEntity.ok(vehicles);
    }

    @PutMapping("/{id}/direction")
    @Operation(summary = "Update the direction of a vehicle by id")
    @ApiResponse(responseCode = "200", description = "Direction updated successfully")
    public ResponseEntity<Void> updateDirection(@PathVariable int id, @RequestParam Vehicle.Direction direction) {
        vehiclesService.updateDirection(id, direction);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/stop")
    @Operation(summary = "Stop a specific vehicle from moving")
    @ApiResponse(responseCode = "200", description = "Vehicle stopped successfully")
    @ApiResponse(responseCode = "404", description = "Vehicle not found")
    public ResponseEntity<Void> stopVehicle(@PathVariable int id) {
        Vehicle vehicle = vehiclesService.getAllVehicles().get(id);
        if (vehicle != null) {
            vehiclesService.stopVehicle(vehicle);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/stopAll")
    @Operation(summary = "Stop all moving vehicles")
    @ApiResponse(responseCode = "200", description = "All moving vehicles stopped successfully")
    public ResponseEntity<Void> stopMovingVehicles() {
        vehiclesService.stopMovingVehicles();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a specific vehicle by id")
    @ApiResponse(responseCode = "200", description = "Vehicle retrieved successfully")
    public Vehicle getVehicle(@PathVariable int id) {
        return vehiclesService.getVehicle(id);
    }
}
