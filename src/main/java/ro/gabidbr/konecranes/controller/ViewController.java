package ro.gabidbr.konecranes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import ro.gabidbr.konecranes.service.VehiclesService;

@Controller
@CrossOrigin(origins = "http://localhost:8080")
public class ViewController {

    private final VehiclesService vehiclesService;

    public ViewController(VehiclesService vehiclesService) {
        this.vehiclesService = vehiclesService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("vehicles", vehiclesService.getAllVehicles());
        return "index";
    }
}
