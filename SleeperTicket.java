/**
 * Sleeper class ticket - extends Ticket
 * Demonstrates INHERITANCE and POLYMORPHISM
 */
public class SleeperTicket extends Ticket {
    // Additional field specific to Sleeper ticket
    private String coachType;
    private static final double SERVICE_CHARGE = 50.0; // Fixed service charge for sleeper

    // Constructor
    public SleeperTicket(String ticketId, String passengerName, int age, String trainNumber, double baseFare) {
        super(ticketId, passengerName, age, trainNumber, baseFare);
        this.coachType = "Sleeper Class";
    }

    // POLYMORPHISM - Overriding abstract method with Sleeper-specific logic
    @Override
    public double calculateFare() {
        double totalFare = getBaseFare() + SERVICE_CHARGE;
        
        // Apply age-based discount
        if (getAge() < 12) {
            totalFare = totalFare * 0.5; // 50% discount for children
        } else if (getAge() >= 60) {
            totalFare = totalFare * 0.6; // 40% discount for senior citizens
        }
        
        return totalFare;
    }

    // Override displayTicketDetails to add coach type
    @Override
    public void displayTicketDetails() {
        super.displayTicketDetails();
        System.out.println("Coach Type      : " + coachType);
        System.out.println("Service Charge  : ₹" + SERVICE_CHARGE);
        System.out.println("====================================\n");
    }

    // Getter and Setter for coachType
    public String getCoachType() {
        return coachType;
    }

    public void setCoachType(String coachType) {
        this.coachType = coachType;
    }
}
