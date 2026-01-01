/**
 * Abstract class representing a Railway Ticket
 * Demonstrates ABSTRACTION - hides fare calculation implementation from user
 */
public abstract class Ticket {
    // ENCAPSULATION - All fields are private
    private String ticketId;
    private String passengerName;
    private int age;
    private String trainNumber;
    private double baseFare;

    // Constructor
    public Ticket(String ticketId, String passengerName, int age, String trainNumber, double baseFare) {
        this.ticketId = ticketId;
        this.passengerName = passengerName;
        this.age = age;
        this.trainNumber = trainNumber;
        this.baseFare = baseFare;
    }

    // Abstract method - must be implemented by child classes
    // ABSTRACTION - User doesn't know how fare is calculated
    public abstract double calculateFare();

    // Concrete method - displays ticket details
    public void displayTicketDetails() {
        System.out.println("\n========== TICKET DETAILS ==========");
        System.out.println("Ticket ID       : " + ticketId);
        System.out.println("Passenger Name  : " + passengerName);
        System.out.println("Age             : " + age);
        System.out.println("Train Number    : " + trainNumber);
        System.out.println("Base Fare       : ₹" + baseFare);
        System.out.println("Total Fare      : ₹" + calculateFare());
        System.out.println("====================================\n");
    }

    // ENCAPSULATION - Getters and Setters for private fields
    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public double getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(double baseFare) {
        this.baseFare = baseFare;
    }
}
