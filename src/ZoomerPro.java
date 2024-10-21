public class ZoomerPro extends ZoomerVehicle {
    public ZoomerPro(String registrationNumber, String make) {
        super(registrationNumber, make, VehicleCategory.PRO);
    }

    @Override
    public double calculateCost(int distance, int passengers, boolean peakTime, int largeBags) {
        int costPerKm = peakTime ? 5 : 3;
        double totalCost = distance * costPerKm * passengers;
        totalCost += largeBags * 5; // Surcharge for large bags
        return totalCost;
    }
}
