import java.time.LocalDateTime;

public class Ride {
    private String rideId;
    private Customer customer;
    private ZoomerVehicle vehicle;
    private LocalDateTime rideDateTime;
    private String startLocation;
    private String destination;
    private int distance;
    private int passengers;
    private int largeBags;
    private double cost;

    public Ride(String rideId, Customer customer, ZoomerVehicle vehicle, LocalDateTime rideDateTime,
                String startLocation, String destination, int distance, int passengers, int largeBags) {
        this.rideId = rideId;
        this.customer = customer;
        this.vehicle = vehicle;
        this.rideDateTime = rideDateTime;
        this.startLocation = startLocation;
        this.destination = destination;
        this.distance = distance;
        this.passengers = passengers;
        this.largeBags = largeBags;
        this.cost = vehicle.calculateCost(distance, passengers, isPeakTime(rideDateTime), largeBags);
        vehicle.addKilometers(distance);
    }

    private boolean isPeakTime(LocalDateTime dateTime) {
        int hour = dateTime.getHour();
        return (hour >= 7 && hour <= 9) || (hour >= 17 && hour <= 19);
    }

    // Getter for vehicle
    public ZoomerVehicle getVehicle() {
        return vehicle;
    }

    // Getter for cost
    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Ride [ID: " + rideId + ", Customer: " + customer.getName() + ", Vehicle: " + vehicle.getRegistrationNumber() +
               ", Start: " + startLocation + ", Destination: " + destination + ", Distance: " + distance + " km, Cost: $" + cost + "]";
    }
}
