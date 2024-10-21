public class ZoomerLite extends ZoomerVehicle {
    public ZoomerLite(String registrationNumber, String make) {
        super(registrationNumber, make, VehicleCategory.LITE);
    }

    @Override
    public double calculateCost(int distance, int passengers, boolean peakTime, int largeBags) {
        int costPerKm = peakTime ? 4 : 2;
        return (distance <= 10) ? distance * costPerKm : 0; // Max 10 km
    }
}
