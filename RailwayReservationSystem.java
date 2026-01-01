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
        clearScreen();
        printWelcomeBanner();

        boolean running = true;

        while (running) {
            try {
                displayMenu();
                int choice = getIntInput("👉 Enter your choice (1-4): ");

                switch (choice) {
                    case 1:
                        clearScreen();
                        bookTicket();
                        pressEnterToContinue();
                        clearScreen();
                        break;
                    case 2:
                        clearScreen();
                        viewTicket();
                        pressEnterToContinue();
                        clearScreen();
                        break;
                    case 3:
                        clearScreen();
                        cancelTicket();
                        pressEnterToContinue();
                        clearScreen();
                        break;
                    case 4:
                        clearScreen();
                        printGoodbyeMessage();
                        running = false;
                        break;
                    default:
                        System.out.println("\n❌ Invalid choice! Please select between 1-4.\n");
                        pressEnterToContinue();
                        clearScreen();
                }
            } catch (Exception e) {
                System.out.println("\n❌ An error occurred: " + e.getMessage());
                System.out.println("Please try again.\n");
                scanner.nextLine(); // Clear buffer
                pressEnterToContinue();
                clearScreen();
            }
        }

        scanner.close();
    }

    /**
     * Clears the screen for better UX
     */
    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Prints welcome banner
     */
    private static void printWelcomeBanner() {
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║                                                  ║");
        System.out.println("║       🚂 RAILWAY RESERVATION SYSTEM 🚂          ║");
        System.out.println("║                                                  ║");
        System.out.println("║           Welcome to Indian Railways!            ║");
        System.out.println("║                                                  ║");
        System.out.println("╚══════════════════════════════════════════════════╝\n");
    }

    /**
     * Prints goodbye message
     */
    private static void printGoodbyeMessage() {
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║                                                  ║");
        System.out.println("║     ✅ Thank you for using our service!          ║");
        System.out.println("║                                                  ║");
        System.out.println("║        🛤️  Have a safe journey! 🛤️              ║");
        System.out.println("║                                                  ║");
        System.out.println("╚══════════════════════════════════════════════════╝\n");
    }

    /**
     * Displays the main menu with enhanced formatting
     */
    private static void displayMenu() {
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║                   MAIN MENU                      ║");
        System.out.println("╠══════════════════════════════════════════════════╣");
        System.out.println("║                                                  ║");
        System.out.println("║  📝 1. Book New Ticket                           ║");
        System.out.println("║  🔍 2. View Ticket Details                       ║");
        System.out.println("║  ❌ 3. Cancel Ticket                             ║");
        System.out.println("║  🚪 4. Exit System                               ║");
        System.out.println("║                                                  ║");
        System.out.println("╚══════════════════════════════════════════════════╝\n");
    }

    /**
     * Books a new ticket
     * Demonstrates POLYMORPHISM - creates Ticket reference but assigns child class object
     */
    private static void bookTicket() {
        try {
            System.out.println("╔══════════════════════════════════════════════════╗");
            System.out.println("║              📝 BOOK NEW TICKET                  ║");
            System.out.println("╚══════════════════════════════════════════════════╝\n");

            // Get passenger details
            scanner.nextLine(); // Clear buffer
            System.out.print("👤 Enter Passenger Name: ");
            String name = scanner.nextLine().trim();

            if (name.isEmpty()) {
                System.out.println("\n❌ Error: Name cannot be empty!");
                return;
            }

            // Get and validate age
            int age = getIntInput("🎂 Enter Age: ");
            if (age <= 0 || age > 120) {
                System.out.println("\n❌ Error: Invalid age! Age must be between 1 and 120.");
                return;
            }

            System.out.print("🚂 Enter Train Number: ");
            String trainNumber = scanner.nextLine().trim();

            if (trainNumber.isEmpty()) {
                System.out.println("\n❌ Error: Train number cannot be empty!");
                return;
            }

            // Get base fare
            double baseFare = getDoubleInput("💰 Enter Base Fare (₹): ");
            if (baseFare <= 0) {
                System.out.println("\n❌ Error: Fare must be greater than 0!");
                return;
            }

            // Select ticket type
            System.out.println("\n" + "─".repeat(50));
            System.out.println("🎫 SELECT TICKET CLASS:");
            System.out.println("─".repeat(50));
            System.out.println("  1️⃣  Sleeper Class (Budget Friendly)");
            System.out.println("  2️⃣  AC Class (Premium Comfort)");
            System.out.println("─".repeat(50));
            int ticketType = getIntInput("👉 Enter your choice (1 or 2): ");

            // Generate unique ticket ID
            String ticketId = "TKT" + (ticketCounter++);

            // POLYMORPHISM - Ticket reference can hold SleeperTicket or ACTicket object
            Ticket ticket = null;

            if (ticketType == 1) {
                // Create Sleeper ticket
                ticket = new SleeperTicket(ticketId, name, age, trainNumber, baseFare);
                System.out.println("\n✅ Sleeper Class ticket selected!");
            } else if (ticketType == 2) {
                // Create AC ticket
                System.out.println("\n" + "─".repeat(50));
                System.out.println("❄️  SELECT AC TIER:");
                System.out.println("─".repeat(50));
                System.out.println("  1️⃣  First AC (1A) - Most Premium");
                System.out.println("  2️⃣  Second AC (2A) - Comfortable");
                System.out.println("  3️⃣  Third AC (3A) - Affordable AC");
                System.out.println("─".repeat(50));
                int tierChoice = getIntInput("👉 Enter your choice (1-3): ");

                String tier;
                switch (tierChoice) {
                    case 1:
                        tier = "1A";
                        System.out.println("\n✅ First AC (1A) selected!");
                        break;
                    case 2:
                        tier = "2A";
                        System.out.println("\n✅ Second AC (2A) selected!");
                        break;
                    case 3:
                        tier = "3A";
                        System.out.println("\n✅ Third AC (3A) selected!");
                        break;
                    default:
                        System.out.println("\n⚠️  Invalid tier! Defaulting to Third AC (3A).");
                        tier = "3A";
                }

                ticket = new ACTicket(ticketId, name, age, trainNumber, baseFare, tier);
            } else {
                System.out.println("\n❌ Error: Invalid ticket type! Please select 1 or 2.");
                return;
            }

            // Add ticket to list
            ticketList.add(ticket);

            // POLYMORPHISM - calculateFare() behaves differently based on actual object type
            double totalFare = ticket.calculateFare();

            // Success message with booking summary
            System.out.println("\n" + "═".repeat(50));
            System.out.println("🎉 BOOKING SUCCESSFUL! 🎉");
            System.out.println("═".repeat(50));
            System.out.println("📋 Booking Summary:");
            System.out.println("   🎫 Ticket ID    : " + ticketId);
            System.out.println("   👤 Passenger    : " + name);
            System.out.println("   🚂 Train No.    : " + trainNumber);
            System.out.println("   💰 Total Fare   : ₹" + String.format("%.2f", totalFare));
            
            // Show discount info if applicable
            if (age < 12) {
                System.out.println("   🎁 Child Discount Applied (50% OFF)");
            } else if (age >= 60) {
                System.out.println("   🎁 Senior Citizen Discount Applied");
            }
            
            System.out.println("═".repeat(50));
            System.out.println("💡 Tip: Save your Ticket ID for future reference!");
            System.out.println("═".repeat(50) + "\n");

        } catch (InputMismatchException e) {
            System.out.println("\n❌ Invalid input! Please enter the correct data type.");
            scanner.nextLine(); // Clear buffer
        } catch (Exception e) {
            System.out.println("\n❌ Error booking ticket: " + e.getMessage());
        }
    }

    /**
     * Views ticket details by ticket ID
     */
    private static void viewTicket() {
        try {
            System.out.println("╔══════════════════════════════════════════════════╗");
            System.out.println("║              🔍 VIEW TICKET DETAILS              ║");
            System.out.println("╚══════════════════════════════════════════════════╝\n");

            if (ticketList.isEmpty()) {
                System.out.println("❌ No tickets found in the system!");
                System.out.println("💡 Please book a ticket first.\n");
                return;
            }

            System.out.println("📊 Total tickets in system: " + ticketList.size() + "\n");
            
            scanner.nextLine(); // Clear buffer
            System.out.print("🎫 Enter Ticket ID (e.g., TKT1000): ");
            String ticketId = scanner.nextLine().trim().toUpperCase();

            if (ticketId.isEmpty()) {
                System.out.println("\n❌ Error: Ticket ID cannot be empty!");
                return;
            }

            // Search for ticket
            Ticket foundTicket = findTicketById(ticketId);

            if (foundTicket != null) {
                System.out.println("\n✅ Ticket found!\n");
                // POLYMORPHISM - displayTicketDetails() behaves based on actual object type
                foundTicket.displayTicketDetails();
            } else {
                System.out.println("\n❌ Ticket not found!");
                System.out.println("🔍 No ticket exists with ID: " + ticketId);
                System.out.println("💡 Please check the ticket ID and try again.\n");
            }

        } catch (Exception e) {
            System.out.println("\n❌ Error viewing ticket: " + e.getMessage());
        }
    }

    /**
     * Cancels a ticket by ticket ID
     */
    private static void cancelTicket() {
        try {
            System.out.println("╔══════════════════════════════════════════════════╗");
            System.out.println("║              ❌ CANCEL TICKET                    ║");
            System.out.println("╚══════════════════════════════════════════════════╝\n");

            if (ticketList.isEmpty()) {
                System.out.println("❌ No tickets found in the system!");
                System.out.println("💡 Please book a ticket first.\n");
                return;
            }

            System.out.println("📊 Total tickets in system: " + ticketList.size() + "\n");
            
            scanner.nextLine(); // Clear buffer
            System.out.print("🎫 Enter Ticket ID to cancel (e.g., TKT1000): ");
            String ticketId = scanner.nextLine().trim().toUpperCase();

            if (ticketId.isEmpty()) {
                System.out.println("\n❌ Error: Ticket ID cannot be empty!");
                return;
            }

            // Search and remove ticket
            Ticket foundTicket = findTicketById(ticketId);

            if (foundTicket != null) {
                // Show ticket details before cancellation
                System.out.println("\n📋 Ticket Details:");
                System.out.println("   Passenger: " + foundTicket.getPassengerName());
                System.out.println("   Train No.: " + foundTicket.getTrainNumber());
                
                // Confirm cancellation
                System.out.print("\n⚠️  Are you sure you want to cancel? (yes/no): ");
                String confirm = scanner.nextLine().trim().toLowerCase();
                
                if (confirm.equals("yes") || confirm.equals("y")) {
                    double refundAmount = foundTicket.calculateFare() * 0.8; // 80% refund
                    ticketList.remove(foundTicket);
                    
                    System.out.println("\n" + "═".repeat(50));
                    System.out.println("✅ TICKET CANCELLED SUCCESSFULLY!");
                    System.out.println("═".repeat(50));
                    System.out.println("   🎫 Ticket ID     : " + ticketId);
                    System.out.println("   💰 Refund Amount : ₹" + String.format("%.2f", refundAmount));
                    System.out.println("   ⏰ Processing Time: 7-10 working days");
                    System.out.println("═".repeat(50) + "\n");
                } else {
                    System.out.println("\n✅ Cancellation aborted. Ticket is still active.\n");
                }
            } else {
                System.out.println("\n❌ Ticket not found!");
                System.out.println("🔍 No ticket exists with ID: " + ticketId);
                System.out.println("💡 Please check the ticket ID and try again.\n");
            }

        } catch (Exception e) {
            System.out.println("\n❌ Error cancelling ticket: " + e.getMessage());
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
                System.out.println("❌ Invalid input! Please enter a valid number.");
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
                System.out.println("❌ Invalid input! Please enter a valid number.");
                scanner.nextLine(); // Clear buffer
            }
        }
    }

    /**
     * Helper method to pause and wait for user
     */
    private static void pressEnterToContinue() {
        System.out.print("\n⏸️  Press ENTER to continue...");
        try {
            System.in.read();
            scanner.nextLine(); // Clear buffer
        } catch (Exception e) {
            // Ignore
        }
    }
}
