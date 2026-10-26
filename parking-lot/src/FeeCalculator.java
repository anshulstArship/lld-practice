import java.time.Duration;

public class FeeCalculator {
   

    public double calculatePrice(Ticket ticket) {
        Duration duration = Duration.between(ticket.getExitTime(), ticket.getEntryTime());
        long min = duration.toMinutes();
        double price = 5 * min;
        return price;

    }

}
