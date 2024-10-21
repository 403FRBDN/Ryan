public class ZoomerStandard extends ZoomerVehicle {
    public ZoomerStandard(String registrationNumber, String make) {
        super(registrationNumber, make, VehicleCategory.STANDARD);
    }

    @Override
    public double calculateCost(int distance, int passengers, boolean peakTime, int largeBags) {
        int costPerKm = peakTime ? 4 : 2;
        return (distance <= 20) ? distance * costPerKm * passengers : 0; // Max 20 km
    }
}
