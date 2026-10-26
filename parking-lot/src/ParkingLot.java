import java.util.List;

public class ParkingLot {
    private final String name;
    private final List<ParkingFloor> parkingFloors;

    public ParkingLot(String name,List<ParkingFloor> list){
        this.name=name;
        this.parkingFloors=list;
    }

    public ParkingSpot checkParkingSpot(Vehicle vehicle){
        for(ParkingFloor floor:parkingFloors){
            ParkingSpot spot=floor.checkSpot(vehicle);
            if(spot!=null){
                return spot;
            }
        }
        return null;
    }
    public String getName(){
        return this.name;
    }
    
}
