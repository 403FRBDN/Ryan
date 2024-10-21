import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ZoomerSystem {
    private List<Customer> customers = new ArrayList<>();
    private List<ZoomerVehicle> vehicles = new ArrayList<>();
    private List<Ride> rides = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new ZoomerSystem().run();
    }

    private void run() {
        boolean running = true;

        while (running) {
            System.out.println("\nZoomer System Menu:");
            System.out.println("1. Register a new customer");
            System.out.println("2. Register a new Zoomer vehicle");
            System.out.println("3. Book a new ride");
            System.out.println("4. Display daily ride report");
            System.out.println("5. Display monthly summary report");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1 -> registerNewCustomer();
                case 2 -> registerNewVehicle();
                case 3 -> bookNewRide();
                case 4 -> displayDailyRideReport();
                case 5 -> displayMonthlySummaryReport();
                case 6 -> running = false;
                default -> System.out.println("Invalid choice, try again.");
            }
        }
    }

    private void registerNewCustomer() {
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        String customerId = "C" + (customers.size() + 1);
        Customer customer = new Customer(customerId, name);
        customers.add(customer);
        System.out.println("Customer registered with ID: " + customerId);
    }

    private void registerNewVehicle() {
        System.out.print("Enter vehicle registration number: ");
        String regNo = scanner.nextLine();
        System.out.print("Enter vehicle make: ");
        String make = scanner.nextLine();
        System.out.println("Select vehicle category: 1. Lite 2. Standard 3. Pro");
        int categoryChoice = scanner.nextInt();
        scanner.nextLine(); 

        ZoomerVehicle vehicle = switch (categoryChoice) {
            case 1 -> new ZoomerLite(regNo, make);
            case 2 -> new ZoomerStandard(regNo, make);
            case 3 -> new ZoomerPro(regNo, make);
            default -> null;
        };

        if (vehicle != null) {
            vehicles.add(vehicle);
            System.out.println("Vehicle registered: " + vehicle);
        } else {
            System.out.println("Invalid category.");
        }
    }

    private void bookNewRide() {
        if (customers.isEmpty() || vehicles.isEmpty()) {
            System.out.println("No customers or vehicles available for booking.");
            return;
        }

        System.out.print("Enter customer ID: ");
        String customerId = scanner.nextLine();
        Customer customer = findCustomerById(customerId);

        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        System.out.print("Enter vehicle registration number: ");
        String regNo = scanner.nextLine();
        ZoomerVehicle vehicle = findVehicleByRegNo(regNo);

        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        System.out.print("Enter ride distance (in km): ");
        int distance = scanner.nextInt();
        System.out.print("Enter number of passengers: ");
        int passengers = scanner.nextInt();
        System.out.print("Enter number of large bags: ");
        int largeBags = scanner.nextInt();
        scanner.nextLine(); 

        String rideId = "R" + (rides.size() + 1);
        LocalDateTime rideDateTime = LocalDateTime.now();
        System.out.print("Enter start location: ");
        String startLocation = scanner.nextLine();
        System.out.print("Enter destination: ");
        String destination = scanner.nextLine();

        Ride ride = new Ride(rideId, customer, vehicle, rideDateTime, startLocation, destination, distance, passengers, largeBags);
        rides.add(ride);
        customer.addRide(ride);
        System.out.println("Ride booked successfully: " + ride);
    }

    private void displayDailyRideReport() {
        if (rides.isEmpty()) {
            System.out.println("No rides booked today.");
        } else {
            System.out.println("Today's Ride Report:");
            for (Ride ride : rides) {
                System.out.println(ride);
            }
        }
    }

    private void displayMonthlySummaryReport() {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles registered.");
            return;
        }

        System.out.println("Monthly Summary Report:");
        for (ZoomerVehicle vehicle : vehicles) {
            long totalRides = rides.stream()
                                   .filter(ride -> ride.getVehicle().equals(vehicle))
                                   .count();
            int totalKilometers = vehicle.getTotalKilometers();
            double totalEarnings = rides.stream()
                                        .filter(ride -> ride.getVehicle().equals(vehicle))
                                        .mapToDouble(Ride::getCost)
                                        .sum();

            System.out.println(vehicle + " - Rides: " + totalRides + ", Kilometers: " + totalKilometers + ", Earnings: $" + totalEarnings);
        }
    }

    private Customer findCustomerById(String customerId) {
        return customers.stream()
                        .filter(customer -> customer.getCustomerId().equals(customerId))
                        .findFirst()
                        .orElse(null);
    }

    private ZoomerVehicle findVehicleByRegNo(String regNo) {
        return vehicles.stream()
                       .filter(vehicle -> vehicle.getRegistrationNumber().equals(regNo))
                       .findFirst()
                       .orElse(null);
    }
}
