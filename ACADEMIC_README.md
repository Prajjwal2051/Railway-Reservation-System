# Railway Reservation System

## Project Title

**Railway Ticket Booking and Management System using Java**

---

## Real-World Problem Description

Railway ticket booking is a common daily activity in India. Passengers need to book tickets, view their booking details, and cancel tickets when plans change. Different types of tickets (Sleeper Class, AC Class) have different pricing structures and facilities. Managing this manually is difficult and error-prone.

This project provides a computerized solution where users can:

- Book railway tickets by entering passenger details
- Choose between different ticket classes (Sleeper or AC)
- View complete ticket information using ticket ID
- Cancel bookings when needed

The system automatically calculates fares based on ticket type, applies discounts for children and senior citizens, and maintains all booking records.

---

## Objectives of the Project

1. **Demonstrate Core OOPS Concepts**: Show practical implementation of Encapsulation, Inheritance, Abstraction, and Polymorphism
2. **Solve Real Problem**: Provide a working ticket booking system for railway passengers
3. **Data Management**: Store and manage ticket information efficiently using in-memory data structures
4. **User-Friendly Interface**: Provide clear menu-driven terminal interface for easy interaction
5. **Automated Calculations**: Calculate fares automatically based on ticket type and passenger age

---

## Technologies Used

- **Programming Language**: Java (JDK 8 or higher)
- **Development Approach**: Console/Terminal-based application
- **Data Storage**: In-memory using ArrayList (no database)
- **User Input**: Scanner class for terminal input
- **Design Pattern**: Object-Oriented Programming (OOPS)

---

## Project Structure

The project consists of 4 Java files organized as follows:

```
Railway-Reservation-System/
│
├── Ticket.java                    (Abstract Parent Class)
├── SleeperTicket.java             (Child Class - Sleeper)
├── ACTicket.java                  (Child Class - AC)
└── RailwayReservationSystem.java (Main Application Class)
```

### Class Descriptions

1. **Ticket.java** (Abstract Class)

   - Represents a generic railway ticket
   - Contains common properties: ticketId, passengerName, age, trainNumber, baseFare
   - Declares abstract method `calculateFare()` that child classes must implement
   - Provides concrete method `displayTicketDetails()` for showing ticket information
   - Implements encapsulation with private fields and public getters/setters

2. **SleeperTicket.java** (Concrete Class)

   - Extends the Ticket class
   - Represents Sleeper Class tickets
   - Adds specific fields: coachType, SERVICE_CHARGE
   - Implements `calculateFare()` with Sleeper-specific pricing logic
   - Applies higher discounts for children and senior citizens

3. **ACTicket.java** (Concrete Class)

   - Extends the Ticket class
   - Represents AC Class tickets with different tiers (1A, 2A, 3A)
   - Adds specific fields: acTier, acCharge
   - Implements `calculateFare()` with AC-specific pricing logic
   - Different AC charges based on tier selection

4. **RailwayReservationSystem.java** (Main Class)
   - Entry point of the application
   - Contains main() method
   - Implements menu-driven user interface
   - Manages ticket collection using ArrayList
   - Demonstrates polymorphism by using parent class reference for child objects

---

## OOPS Concepts Used

### 1. Encapsulation

**Definition**: Binding data (variables) and methods together, and restricting direct access to some components.

**Implementation in Project**:

- All data fields in `Ticket.java` are declared as `private`
- Access to private fields is provided through public getter and setter methods
- This protects data from unauthorized or accidental modification

**Class Reference**: `Ticket.java` (Lines 7-11, 39-77)

**Example Code**:

```java
// Private fields - hidden from outside
private String ticketId;
private String passengerName;
private int age;

// Public getter - controlled access
public String getTicketId() {
    return ticketId;
}

// Public setter - controlled modification
public void setTicketId(String ticketId) {
    this.ticketId = ticketId;
}
```

**Why it is important**: Protects sensitive ticket information and ensures data is accessed only through proper methods.

---

### 2. Inheritance

**Definition**: Creating new classes from existing classes, inheriting properties and methods.

**Implementation in Project**:

- `SleeperTicket` class extends `Ticket` class
- `ACTicket` class extends `Ticket` class
- Both child classes inherit all properties and methods from parent `Ticket` class
- Child classes add their own specific fields and override methods as needed

**Class Reference**: `SleeperTicket.java` (Line 5), `ACTicket.java` (Line 5)

**Example Code**:

```java
// Child class inheriting from parent
public class SleeperTicket extends Ticket {
    // Inherits: ticketId, passengerName, age, trainNumber, baseFare
    // Inherits: all getters and setters
    // Inherits: displayTicketDetails() method

    // Adds its own specific field
    private String coachType;

    // Constructor calls parent constructor
    public SleeperTicket(...) {
        super(ticketId, passengerName, age, trainNumber, baseFare);
        this.coachType = "Sleeper Class";
    }
}
```

**Why it is important**: Avoids code duplication and establishes relationship between ticket types.

---

### 3. Abstraction

**Definition**: Hiding implementation details and showing only essential features to the user.

**Implementation in Project**:

- `Ticket` class is declared as abstract
- Contains abstract method `calculateFare()` without implementation
- Child classes must provide their own implementation
- Users call `calculateFare()` without knowing the internal calculation logic

**Class Reference**: `Ticket.java` (Lines 5, 24)

**Example Code**:

```java
// Abstract class
public abstract class Ticket {

    // Abstract method - no implementation here
    public abstract double calculateFare();

    // Concrete method using abstract method
    public void displayTicketDetails() {
        System.out.println("Total Fare: ₹" + calculateFare());
        // User sees result, doesn't know how it's calculated
    }
}
```

**Why it is important**: Users don't need to understand complex fare calculation logic, they just get the final result.

---

### 4. Polymorphism

**Definition**: Ability of objects to take many forms. Same method behaves differently based on object type.

**Implementation in Project**:

- Method Overriding: `calculateFare()` is overridden in both child classes
- Runtime Polymorphism: Parent class reference holds child class objects
- Same method call produces different results based on actual object type

**Class Reference**: `SleeperTicket.java` (Lines 17-29), `ACTicket.java` (Lines 32-44), `RailwayReservationSystem.java` (Lines 101-138)

**Example Code**:

```java
// Parent reference
Ticket ticket;

// Holds SleeperTicket object
if (ticketType == 1) {
    ticket = new SleeperTicket(...);
}
// Holds ACTicket object
else {
    ticket = new ACTicket(...);
}

// Same method call, different behavior!
double fare = ticket.calculateFare();
// Calls SleeperTicket's version OR ACTicket's version
// based on actual object type at runtime
```

**Why it is important**: Same interface (method) provides different functionality based on ticket type, making code flexible and extensible.

---

## How the Program Works

### Execution Flow

1. **Program Starts**

   - Welcome banner is displayed
   - Main menu with 4 options appears

2. **User Selects Option**

   **Option 1: Book Ticket**

   - User enters passenger name
   - User enters age (validates > 0 and < 120)
   - User enters train number
   - User enters base fare
   - User selects ticket type (Sleeper or AC)
   - If AC, user selects tier (1A, 2A, or 3A)
   - System generates unique ticket ID
   - System calculates total fare using polymorphism
   - System stores ticket in ArrayList
   - Confirmation with ticket ID is displayed

   **Option 2: View Ticket**

   - User enters ticket ID
   - System searches in ArrayList
   - If found, displays complete ticket details
   - If not found, shows error message

   **Option 3: Cancel Ticket**

   - User enters ticket ID
   - System confirms cancellation
   - If confirmed, removes ticket from ArrayList
   - Shows refund information (80% of fare)

   **Option 4: Exit**

   - Displays goodbye message
   - Program terminates

3. **Error Handling**
   - Invalid menu choice: Shows error, returns to menu
   - Invalid age: Shows error, cancels booking
   - Empty inputs: Shows error message
   - Non-numeric input: Catches exception, asks again
   - Ticket not found: Shows appropriate message

---

## Sample Input/Output

### Scenario 1: Booking a Sleeper Ticket

```
╔══════════════════════════════════════════════════╗
║                   MAIN MENU                      ║
╠══════════════════════════════════════════════════╣
║  📝 1. Book New Ticket                           ║
║  🔍 2. View Ticket Details                       ║
║  ❌ 3. Cancel Ticket                             ║
║  🚪 4. Exit System                               ║
╚══════════════════════════════════════════════════╝

👉 Enter your choice (1-4): 1

╔══════════════════════════════════════════════════╗
║              📝 BOOK NEW TICKET                  ║
╚══════════════════════════════════════════════════╝

👤 Enter Passenger Name: Rahul Kumar
🎂 Enter Age: 25
🚂 Enter Train Number: 12345
💰 Enter Base Fare (₹): 500

──────────────────────────────────────────────────
🎫 SELECT TICKET CLASS:
──────────────────────────────────────────────────
  1️⃣  Sleeper Class (Budget Friendly)
  2️⃣  AC Class (Premium Comfort)
──────────────────────────────────────────────────
👉 Enter your choice (1 or 2): 1

✅ Sleeper Class ticket selected!

══════════════════════════════════════════════════
🎉 BOOKING SUCCESSFUL! 🎉
══════════════════════════════════════════════════
📋 Booking Summary:
   🎫 Ticket ID    : TKT1000
   👤 Passenger    : Rahul Kumar
   🚂 Train No.    : 12345
   💰 Total Fare   : ₹550.00
══════════════════════════════════════════════════
💡 Tip: Save your Ticket ID for future reference!
══════════════════════════════════════════════════
```

### Scenario 2: Booking an AC Ticket with Senior Citizen Discount

```
👤 Enter Passenger Name: Sunita Sharma
🎂 Enter Age: 65
🚂 Enter Train Number: 54321
💰 Enter Base Fare (₹): 800

👉 Enter your choice (1 or 2): 2

──────────────────────────────────────────────────
❄️  SELECT AC TIER:
──────────────────────────────────────────────────
  1️⃣  First AC (1A) - Most Premium
  2️⃣  Second AC (2A) - Comfortable
  3️⃣  Third AC (3A) - Affordable AC
──────────────────────────────────────────────────
👉 Enter your choice (1-3): 2

✅ Second AC (2A) selected!

══════════════════════════════════════════════════
🎉 BOOKING SUCCESSFUL! 🎉
══════════════════════════════════════════════════
📋 Booking Summary:
   🎫 Ticket ID    : TKT1001
   👤 Passenger    : Sunita Sharma
   🚂 Train No.    : 54321
   💰 Total Fare   : ₹770.00
   🎁 Senior Citizen Discount Applied
══════════════════════════════════════════════════
```

### Scenario 3: Viewing Ticket Details

```
👉 Enter your choice (1-4): 2

╔══════════════════════════════════════════════════╗
║              🔍 VIEW TICKET DETAILS              ║
╚══════════════════════════════════════════════════╝

📊 Total tickets in system: 2

🎫 Enter Ticket ID (e.g., TKT1000): TKT1000

✅ Ticket found!

========== TICKET DETAILS ==========
Ticket ID       : TKT1000
Passenger Name  : Rahul Kumar
Age             : 25
Train Number    : 12345
Base Fare       : ₹500.0
Total Fare      : ₹550.0
====================================
```

### Scenario 4: Cancelling Ticket

```
👉 Enter your choice (1-4): 3

╔══════════════════════════════════════════════════╗
║              ❌ CANCEL TICKET                    ║
╚══════════════════════════════════════════════════╝

📊 Total tickets in system: 2

🎫 Enter Ticket ID to cancel (e.g., TKT1000): TKT1000

📋 Ticket Details:
   Passenger: Rahul Kumar
   Train No.: 12345

⚠️  Are you sure you want to cancel? (yes/no): yes

══════════════════════════════════════════════════
✅ TICKET CANCELLED SUCCESSFULLY!
══════════════════════════════════════════════════
   🎫 Ticket ID     : TKT1000
   💰 Refund Amount : ₹440.00
   ⏰ Processing Time: 7-10 working days
══════════════════════════════════════════════════
```

---

## Compilation and Execution

### Step 1: Compile all Java files

```bash
javac *.java
```

### Step 2: Run the application

```bash
java RailwayReservationSystem
```

---

## Key Features

✅ Menu-driven interface for easy navigation  
✅ Automatic unique ticket ID generation  
✅ Different pricing for Sleeper and AC tickets  
✅ Multiple AC tiers with different charges  
✅ Age-based discount system (children and senior citizens)  
✅ Confirmation before ticket cancellation  
✅ Refund calculation (80% of ticket fare)  
✅ Complete input validation and error handling  
✅ Professional terminal UI with emojis and borders

---

## Conclusion

This Railway Reservation System successfully demonstrates all four fundamental OOPS concepts:

1. **Encapsulation** protects ticket data through private fields and controlled access
2. **Inheritance** creates a logical class hierarchy avoiding code duplication
3. **Abstraction** hides complex fare calculation logic from users
4. **Polymorphism** allows flexible behavior based on ticket type at runtime

The system provides a practical solution to real-world railway ticket booking needs while serving as an excellent learning project for Object-Oriented Programming in Java. The code is clean, well-commented, and follows Java naming conventions, making it suitable for academic evaluation and learning purposes.

---

## Project Members

This project was developed by a team of 4 students as part of OOPS Mini-Project:

- **Student 1**: Encapsulation Implementation and Documentation
- **Student 2**: Inheritance Implementation and Documentation
- **Student 3**: Abstraction Implementation and Documentation
- **Student 4**: Polymorphism Implementation and Documentation

---

## Future Enhancements (Optional)

- Add database connectivity for persistent storage
- Implement seat availability checking
- Add payment gateway integration
- Create GUI using Java Swing
- Add email confirmation feature
- Implement train schedule management

---

**Note**: This is an academic project designed to demonstrate OOPS concepts. It uses in-memory storage and is intended for educational purposes.
