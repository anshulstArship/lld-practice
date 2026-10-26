import java.util.List;
public class ParkingFloor {
    private final String number;
    private List<ParkingSpot> spotList;

    public ParkingFloor(String number,List<ParkingSpot> list){
        this.number=number;
        this.spotList=list;
    }

    public ParkingSpot checkSpot(Vehicle vehicle){
        for(ParkingSpot spt:spotList){
            if(spt.canFit(vehicle) && spt.isAvailable()){
                return spt;
            }
        }
        return null;
    }
    public  List<ParkingSpot> getParkingSpot(){
        return spotList;
    }
    
}
