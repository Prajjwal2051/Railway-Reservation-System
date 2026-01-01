import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Main class for Railway Reservation System
 * Demonstrates POLYMORPHISM through runtime method dispatch
 */
public class RailwayReservationSystem {
    // In-memory storage for tickets
    private static ArrayList<Ticket> ticketList = new ArrayList<>();
    private static int ticketCounter = 1000; // For generating unique ticket IDs
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("\n╔════════════════════════════════════════════╗");
        System.out.println("║   RAILWAY RESERVATION SYSTEM - WELCOME!      ║");
        System.out.println("╚════════════════════════════════════════════╝\n");

        boolean running = true;

        while (running) {
            try {
                displayMenu();
                int choice = getIntInput("Enter your choice: ");

                switch (choice) {
                    case 1:
                        bookTicket();
                        break;
                    case 2:
                        viewTicket();
                        break;
                    case 3:
                        cancelTicket();
                        break;
                    case 4:
                        System.out.println("\n✓ Thank you for using Railway Reservation System!");
                        System.out.println("✓ Have a safe journey!\n");
                        running = false;
                        break;
                    default:
                        System.out.println("\n✗ Invalid choice! Please select between 1-4.\n");
                }
            } catch (Exception e) {
                System.out.println("\n✗ An error occurred: " + e.getMessage());
                System.out.println("✗ Please try again.\n");
                scanner.nextLine(); // Clear buffer
            }
        }

        scanner.close();
    }

    /**
     * Displays the main menu
     */
    private static void displayMenu() {
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│              MAIN MENU                   │");
        System.out.println("├──────────────────────────────────────────┤");
        System.out.println("│  1. Book Ticket                          │");
        System.out.println("│  2. View Ticket                          │");
        System.out.println("│  3. Cancel Ticket                        │");
        System.out.println("│  4. Exit                                 │");
        System.out.println("└──────────────────────────────────────────┘");
    }

    /**
     * Books a new ticket
     * Demonstrates POLYMORPHISM - creates Ticket reference but assigns child class object
     */
    private static void bookTicket() {
        try {
            System.out.println("\n── BOOK NEW TICKET ──────────────────────────");

            // Get passenger details
            scanner.nextLine(); // Clear buffer
            System.out.print("Enter Passenger Name: ");
            String name = scanner.nextLine().trim();

            if (name.isEmpty()) {
                System.out.println("✗ Name cannot be empty!");
                return;
            }

            // Get and validate age
            int age = getIntInput("Enter Age: ");
            if (age <= 0 || age > 120) {
                System.out.println("✗ Invalid age! Age must be between 1 and 120.");
                return;
            }

            System.out.print("Enter Train Number: ");
            String trainNumber = scanner.nextLine().trim();

            if (trainNumber.isEmpty()) {
                System.out.println("✗ Train number cannot be empty!");
                return;
            }

            // Get base fare
            double baseFare = getDoubleInput("Enter Base Fare (₹): ");
            if (baseFare <= 0) {
                System.out.println("✗ Fare must be greater than 0!");
                return;
            }

            // Select ticket type
            System.out.println("\nSelect Ticket Type:");
            System.out.println("1. Sleeper Class");
            System.out.println("2. AC Class");
            int ticketType = getIntInput("Enter choice (1 or 2): ");

            // Generate unique ticket ID
            String ticketId = "TKT" + (ticketCounter++);

            // POLYMORPHISM - Ticket reference can hold SleeperTicket or ACTicket object
            Ticket ticket = null;

            if (ticketType == 1) {
                // Create Sleeper ticket
                ticket = new SleeperTicket(ticketId, name, age, trainNumber, baseFare);
            } else if (ticketType == 2) {
                // Create AC ticket
                System.out.println("\nSelect AC Tier:");
                System.out.println("1. First AC (1A)");
                System.out.println("2. Second AC (2A)");
                System.out.println("3. Third AC (3A)");
                int tierChoice = getIntInput("Enter choice (1-3): ");

                String tier;
                switch (tierChoice) {
                    case 1:
                        tier = "1A";
                        break;
                    case 2:
                        tier = "2A";
                        break;
                    case 3:
                        tier = "3A";
                        break;
                    default:
                        System.out.println("✗ Invalid tier! Defaulting to 3A.");
                        tier = "3A";
                }

                ticket = new ACTicket(ticketId, name, age, trainNumber, baseFare, tier);
            } else {
                System.out.println("✗ Invalid ticket type! Please select 1 or 2.");
                return;
            }

            // Add ticket to list
            ticketList.add(ticket);

            System.out.println("\n✓ Ticket booked successfully!");
            System.out.println("✓ Your Ticket ID: " + ticketId);
            
            // POLYMORPHISM - calculateFare() behaves differently based on actual object type
            System.out.println("✓ Total Fare: ₹" + ticket.calculateFare());
            System.out.println("────────────────────────────────────────────\n");

        } catch (InputMismatchException e) {
            System.out.println("✗ Invalid input! Please enter correct data type.");
            scanner.nextLine(); // Clear buffer
        } catch (Exception e) {
            System.out.println("✗ Error booking ticket: " + e.getMessage());
        }
    }

    /**
     * Views ticket details by ticket ID
     */
    private static void viewTicket() {
        try {
            if (ticketList.isEmpty()) {
                System.out.println("\n✗ No tickets available to view.");
                System.out.println("✗ Please book a ticket first.\n");
                return;
            }

            System.out.println("\n── VIEW TICKET ──────────────────────────────");
            scanner.nextLine(); // Clear buffer
            System.out.print("Enter Ticket ID: ");
            String ticketId = scanner.nextLine().trim();

            if (ticketId.isEmpty()) {
                System.out.println("✗ Ticket ID cannot be empty!");
                return;
            }

            // Search for ticket
            Ticket foundTicket = findTicketById(ticketId);

            if (foundTicket != null) {
                // POLYMORPHISM - displayTicketDetails() behaves based on actual object type
                foundTicket.displayTicketDetails();
            } else {
                System.out.println("✗ Ticket not found with ID: " + ticketId);
                System.out.println("✗ Please check the ticket ID and try again.\n");
            }

        } catch (Exception e) {
            System.out.println("✗ Error viewing ticket: " + e.getMessage());
        }
    }

    /**
     * Cancels a ticket by ticket ID
     */
    private static void cancelTicket() {
        try {
            if (ticketList.isEmpty()) {
                System.out.println("\n✗ No tickets available to cancel.");
                System.out.println("✗ Please book a ticket first.\n");
                return;
            }

            System.out.println("\n── CANCEL TICKET ────────────────────────────");
            scanner.nextLine(); // Clear buffer
            System.out.print("Enter Ticket ID to cancel: ");
            String ticketId = scanner.nextLine().trim();

            if (ticketId.isEmpty()) {
                System.out.println("✗ Ticket ID cannot be empty!");
                return;
            }

            // Search and remove ticket
            Ticket foundTicket = findTicketById(ticketId);

            if (foundTicket != null) {
                ticketList.remove(foundTicket);
                System.out.println("\n✓ Ticket cancelled successfully!");
                System.out.println("✓ Ticket ID: " + ticketId);
                System.out.println("✓ Refund will be processed within 7-10 working days.");
                System.out.println("────────────────────────────────────────────\n");
            } else {
                System.out.println("✗ Ticket not found with ID: " + ticketId);
                System.out.println("✗ Unable to cancel. Please check the ticket ID.\n");
            }

        } catch (Exception e) {
            System.out.println("✗ Error cancelling ticket: " + e.getMessage());
        }
    }

    /**
     * Helper method to find ticket by ID
     */
    private static Ticket findTicketById(String ticketId) {
        for (Ticket ticket : ticketList) {
            if (ticket.getTicketId().equalsIgnoreCase(ticketId)) {
                return ticket;
            }
        }
        return null;
    }

    /**
     * Helper method to get integer input with validation
     */
    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = scanner.nextInt();
                scanner.nextLine(); // Clear buffer
                return value;
            } catch (InputMismatchException e) {
                System.out.println("✗ Invalid input! Please enter a valid number.");
                scanner.nextLine(); // Clear buffer
            }
        }
    }

    /**
     * Helper method to get double input with validation
     */
    private static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = scanner.nextDouble();
                scanner.nextLine(); // Clear buffer
                return value;
            } catch (InputMismatchException e) {
                System.out.println("✗ Invalid input! Please enter a valid number.");
                scanner.nextLine(); // Clear buffer
            }
        }
    }
}
