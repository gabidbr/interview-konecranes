package ro.gabidbr.konecranes.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Vehicle {
    @Schema(description = "The unique identifier auto incremented for the vehicle")
    private int id;
    @Schema(description = "The x position on X axis for the vehicle")

    private int xPosition;
    @Schema(description = "The y position on Y axis for the vehicle")
    private int yPosition;

    @Schema(description = "The color of the vehicle")
    private String color;

    @Schema(description = "The direction of the vehicle(LEFT, RIGHT, UP, DOWN and STAY)")
    private Direction direction;

    public enum Direction{
        LEFT,RIGHT,UP,DOWN, STOP
    }
}
