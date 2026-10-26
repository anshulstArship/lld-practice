
public class Vehicle {
    private final String id;
    private final VehicleType type;
    private final boolean electricalVehicle;

    public Vehicle(String id, VehicleType type, boolean electricalVehicle) {
        if(id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Vehicle ID cannot be null or empty");
        }
        this.id = id;
        this.type = type;
        this.electricalVehicle = electricalVehicle;
    }
    
    public String getId(){
        return this.id;
    }
    public VehicleType getType(){
        return this.type;
    }
    public boolean isElectricalVehicle(){
        return this.electricalVehicle;
    }
}
