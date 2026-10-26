import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ParkingService {
    private final ParkingLot parkingLot;
    private final FeeCalculator feeCalculator;
    private final Map<String, Ticket> activeTickets;

    public ParkingService(ParkingLot lot, FeeCalculator feeCalculator) {
        this.parkingLot = lot;
        this.feeCalculator = feeCalculator;
        this.activeTickets = new HashMap<>();
    }

    // Spot-level synchronization: prevents two vehicles from occupying the same
    // spot.
    // Vehicle-level coordination: prevents the same vehicle from obtaining multiple
    // active tickets.
    // synchronizing ParkingSpot.occupy() is redundant for preventing concurrent
    // assignments through this one service instance, assuming no other code
    // modifies spots concurrently.
    public synchronized Ticket assignVehicle(Vehicle vehicle) {
        if (activeTickets.containsKey(vehicle.getId())) {
            throw new IllegalStateException("Vehicle is already assigned the spot");
        }
        ParkingSpot spot = parkingLot.checkParkingSpot(vehicle);
        if (spot == null) {
            throw new IllegalStateException("Parking spot is not available");
        }

        Ticket ticket = createTicket(vehicle, spot);
        // What if ticket creation succeeds, but spot.occupy() fails because another
        // thread occupied the spot?
        spot.occupy(vehicle);
        try {
            activeTickets.put(vehicle.getId(), ticket);
            return ticket;
        } catch (RuntimeException e) {
            spot.vacate(); // rollback
            throw e;
        }

    }

    private Ticket createTicket(Vehicle vehicle, ParkingSpot spot) {
        String ticketId = UUID.randomUUID().toString();
        Ticket ticket = new Ticket(ticketId, vehicle, spot);
        return ticket;
    }

    public synchronized double unOccupySpot(Ticket ticket) {
        if (ticket == null) {
            throw new IllegalStateException("Ticket can't be null");
        }
        if (ticket.hasExited()) {
            throw new IllegalStateException("Ticker is expired");
        }
        Ticket activeTicket = activeTickets.get(ticket.getVehicle().getId());
        if(activeTicket==null || !activeTicket.getId().equals(ticket.getId())){
            throw new IllegalStateException("Not Active ticket ");
        }
        ParkingSpot spot = ticket.getSpot();
        ticket.setExitTime();
        double price;
        try {
            price = feeCalculator.calculatePrice(ticket);

        } catch (RuntimeException e) {
            throw e;
        }
        spot.vacate();
        activeTickets.remove(ticket.getVehicle().getId());
        return price;
    }

}
