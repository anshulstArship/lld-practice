public class ParkingSpot {
    private final String id;
    private Vehicle vehicle;
    private final SpotType type;

    public ParkingSpot(String id, SpotType type) {
        this.id = id;
        this.type = type;
    }

    public void occupy(Vehicle vehicle) {
        if (!isAvailable()) {
            throw new IllegalStateException("Spot not available");
        }
        if (!canFit(vehicle)) {
            throw new IllegalStateException("Vehicle can't be assigned");
        }
        this.vehicle=vehicle;

    }

    public void vacate() {
        this.vehicle = null;
    }

    public boolean canFit(Vehicle vehicle) {
        if (type.equals(SpotType.CAR)) {
            return true;
        } else if (vehicle.getType().equals(VehicleType.MOTORCYCLE)) {
            return true;
        }
        return false;
    }

    public boolean isAvailable() {
        return this.vehicle == null;
    }
    public String getId(){
        return id;
    }
}
