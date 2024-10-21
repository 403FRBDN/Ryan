import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String customerId;
    private String name;
    private List<Ride> rideHistory;

    public Customer(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
        this.rideHistory = new ArrayList<>();
    }

    public void addRide(Ride ride) {
        rideHistory.add(ride);
    }

    public String getCustomerId() { return customerId; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return "Customer [ID: " + customerId + ", Name: " + name + ", Rides: " + rideHistory.size() + "]";
    }
}
