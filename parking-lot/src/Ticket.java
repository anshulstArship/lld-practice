import java.time.LocalDateTime;

public class Ticket {
    private final String id;
    private final LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private final Vehicle vehicle;
    private final ParkingSpot parkingSpot;

    public Ticket(String id,Vehicle vehicle,ParkingSpot parkingSpot){
        this.id = id;
        this.entryTime= LocalDateTime.now();
        this.vehicle=vehicle;
        this.parkingSpot=parkingSpot;
    }

    public ParkingSpot getSpot(){
        return parkingSpot;
    }
    public void setExitTime(){
        exitTime=LocalDateTime.now();
    }
    public LocalDateTime getExitTime(){
        return exitTime;
    }
    public LocalDateTime getEntryTime(){
        return entryTime;
    }
    public boolean hasExited(){
        return exitTime!=null;
    }
    public Vehicle getVehicle(){
        return vehicle;
    }
    public String getId(){
        return id;
    }
}
