import java.util.ArrayList;
import java.util.Arrays;

public class main {
    public static void main(String[] args) {
        ParkingSpot spot1 = new ParkingSpot("1", SpotType.MOTORCYCLE);
        ParkingSpot spot2 = new ParkingSpot("2",SpotType.MOTORCYCLE);
        ParkingSpot spot3 = new ParkingSpot("3",SpotType.CAR);
        ParkingFloor floor = new ParkingFloor("1",new ArrayList<>(Arrays.asList(spot1,spot2,spot3)));
        ParkingLot lot = new ParkingLot("Park",new ArrayList<>(Arrays.asList(floor)));
        System.out.println("Welcome to "+lot.getName());

        // Park the vehicle
        Vehicle vehicle1 = new Vehicle("A", VehicleType.MOTORCYCLE,false);
        FeeCalculator cal = new FeeCalculator();
        ParkingService service = new ParkingService(lot,cal);
        Ticket ticket = service.assignVehicle(vehicle1);
        System.out.println(ticket.getSpot().getId()+" "+ticket.getEntryTime());

        double price =service.unOccupySpot(ticket);
        System.out.println(price);
        Ticket ticket2=service.assignVehicle(vehicle1);
         System.out.println(ticket2.getSpot().getId()+" "+ticket2.getEntryTime());
    }
    
}
