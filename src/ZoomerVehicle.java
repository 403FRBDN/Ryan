public abstract class ZoomerVehicle {
    private String registrationNumber;
    private String make;
    private VehicleCategory category;
    private int totalKilometers;

    public ZoomerVehicle(String registrationNumber, String make, VehicleCategory category) {
        this.registrationNumber = registrationNumber;
        this.make = make;
        this.category = category;
        this.totalKilometers = 0;
    }

    public abstract double calculateCost(int distance, int passengers, boolean peakTime, int largeBags);

    public void addKilometers(int kilometers) {
        totalKilometers += kilometers;
    }

    // Getters and Setters
    public String getRegistrationNumber() { return registrationNumber; }
    public String getMake() { return make; }
    public VehicleCategory getCategory() { return category; }
    public int getTotalKilometers() { return totalKilometers; }

    @Override
    public String toString() {
        return "Vehicle [RegNo: " + registrationNumber + ", Make: " + make + ", Category: " + category + ", Kilometers: " + totalKilometers + "]";
    }
}
