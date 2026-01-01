/**
 * AC class ticket - extends Ticket
 * Demonstrates INHERITANCE and POLYMORPHISM
 */
public class ACTicket extends Ticket {
    // Additional fields specific to AC ticket
    private String acTier;
    private double acCharge;

    // Constructor
    public ACTicket(String ticketId, String passengerName, int age, String trainNumber, double baseFare, String acTier) {
        super(ticketId, passengerName, age, trainNumber, baseFare);
        this.acTier = acTier;
        
        // Set AC charge based on tier
        switch (acTier) {
            case "1A":
                this.acCharge = 500.0;
                break;
            case "2A":
                this.acCharge = 300.0;
                break;
            case "3A":
                this.acCharge = 200.0;
                break;
            default:
                this.acCharge = 200.0;
        }
    }

    // POLYMORPHISM - Overriding abstract method with AC-specific logic
    @Override
    public double calculateFare() {
        double totalFare = getBaseFare() + acCharge;
        
        // Apply age-based discount
        if (getAge() < 12) {
            totalFare = totalFare * 0.5; // 50% discount for children
        } else if (getAge() >= 60) {
            totalFare = totalFare * 0.7; // 30% discount for senior citizens (less discount than sleeper)
        }
        
        return totalFare;
    }

    // Override displayTicketDetails to add AC-specific information
    @Override
    public void displayTicketDetails() {
        super.displayTicketDetails();
        System.out.println("AC Tier         : " + acTier);
        System.out.println("AC Charge       : ₹" + acCharge);
        System.out.println("====================================\n");
    }

    // Getters and Setters
    public String getAcTier() {
        return acTier;
    }

    public void setAcTier(String acTier) {
        this.acTier = acTier;
    }

    public double getAcCharge() {
        return acCharge;
    }

    public void setAcCharge(double acCharge) {
        this.acCharge = acCharge;
    }
}
