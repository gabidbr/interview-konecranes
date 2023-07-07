# Konecranes Vehicle Tracking Application

This application is a vehicle tracking system that allows users to add vehicles, update their positions, and view them in real-time through a web UI. Additionally, it offers a REST API that you can interact with using Swagger UI.

The application uses multithreading in order to manage the vehicle movement 

## Getting Started

These instructions will guide you on how to get the project up and running on your local machine.

### Prerequisites

- Java 17
- Maven
- A web browser

### How to use the app

1. Clone the repository on your local machine and run the application, either by running it from Intellij, or by running the command mvn spring-boot:run
2. Once the application is running, navigate to http://localhost:8080
3. Access the swagger by going to http://localhost:8080/swagger-ui.html

Through the Web UI

    On the main page, you can view the track and any vehicles that have been added.
    To add a new vehicle, use the Swagger UI.
    After adding a vehicle through the Swagger UI, refresh the main page to see the new vehicle on the track.
Through the Swagger UI

    After navigating to swagger UI, you'll see a list of available API endpoints.
    To add a new vehicle, use the /vehicles POST endpoint:
    Click on the endpoint to expand it.
    Click on "Try it out".
    Enter the x and y coordinates as well as the direction for the new vehicle.
    Click on "Execute".

    To update the direction of a vehicle, use the /vehicles/{id}/direction PUT endpoint:
    Click on the endpoint to expand it.
    Click on "Try it out".
    Enter the id of the vehicle and the new direction.
    Click on "Execute".

    To view all vehicles, use the /vehicles GET endpoint.
    To view a specific vehicle by id, use the /vehicles/{id} GET endpoint.


#### The web UI requires manual refreshing to see the updated state of vehicles.
