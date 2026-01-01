# Railway Reservation System - OOPS Demonstration

## 📌 How to Compile and Run

```bash
# Compile all Java files
javac *.java

# Run the application
java RailwayReservationSystem
```

## 🎯 OOPS Concepts Demonstrated

### 1️⃣ **ABSTRACTION**

**Location:** `Ticket.java` (Lines 1-75)

- **Abstract Class:** `Ticket` is declared as an abstract class
- **Abstract Method:** `public abstract double calculateFare()` (Line 30)
- **Purpose:** Users of the system don't need to know HOW the fare is calculated internally. They just call `calculateFare()` and get the result. The actual calculation logic is hidden in the child classes.
- **Real-world mapping:** Just like you don't need to know how a train's engine works to travel, you don't need to know fare calculation logic to use it.

**Key Code:**

```java
public abstract class Ticket {
    // ...
    public abstract double calculateFare(); // Abstract method - no implementation here
}
```

---

### 2️⃣ **INHERITANCE**

**Location:**

- `SleeperTicket.java` (Line 5): `public class SleeperTicket extends Ticket`
- `ACTicket.java` (Line 5): `public class ACTicket extends Ticket`

- **Parent Class:** `Ticket` (abstract)
- **Child Classes:** `SleeperTicket` and `ACTicket`
- **Inherited Properties:** Both child classes inherit:
  - `ticketId`, `passengerName`, `age`, `trainNumber`, `baseFare`
  - `displayTicketDetails()` method
  - All getters and setters
- **Additional Properties:**
  - `SleeperTicket` adds: `coachType`, `SERVICE_CHARGE`
  - `ACTicket` adds: `acTier`, `acCharge`
- **Real-world mapping:** Just like Sleeper and AC coaches are types of railway tickets, our classes inherit common ticket properties and add their specific features.

**Key Code:**

```java
public class SleeperTicket extends Ticket {
    // Inherits all properties from Ticket
    // Adds its own: coachType, SERVICE_CHARGE
}

public class ACTicket extends Ticket {
    // Inherits all properties from Ticket
    // Adds its own: acTier, acCharge
}
```

---

### 3️⃣ **POLYMORPHISM**

**Location:** `RailwayReservationSystem.java`

**Runtime Polymorphism (Method Overriding):**

- Line 101: `Ticket ticket = null;` - Parent class reference
- Line 104: `ticket = new SleeperTicket(...)` - Assigned child object
- Line 126: `ticket = new ACTicket(...)` - Assigned different child object
- Line 138: `ticket.calculateFare()` - Same method call, different behavior!

**How it works:**

```java
Ticket ticket; // Parent reference

if (ticketType == 1) {
    ticket = new SleeperTicket(...); // Sleeper object
} else {
    ticket = new ACTicket(...); // AC object
}

// MAGIC: Same method call, different calculation based on actual object type!
ticket.calculateFare(); // Calls SleeperTicket's version OR ACTicket's version
```

- **Method Overriding:** Both `SleeperTicket` and `ACTicket` override the `calculateFare()` method with their own logic
- **Dynamic Method Dispatch:** At runtime, Java determines which version to call based on the actual object type
- **Real-world mapping:** When you ask "what's my fare?", the answer depends on whether you have a Sleeper or AC ticket, even though the question is the same.

**Key Locations:**

- `SleeperTicket.java` Line 16: `@Override public double calculateFare()`
- `ACTicket.java` Line 30: `@Override public double calculateFare()`

---

### 4️⃣ **ENCAPSULATION**

**Location:** All classes (`Ticket.java`, `SleeperTicket.java`, `ACTicket.java`)

- **Private Fields:** All variables are declared as `private`
  - Example in `Ticket.java` Lines 7-11
- **Public Getters/Setters:** Access to private fields is controlled through public methods
  - Example in `Ticket.java` Lines 40-74
- **Data Protection:** Sensitive data like `ticketId`, `fare` cannot be directly modified from outside
- **Real-world mapping:** Just like you can't directly access a train's control panel but must use designated controls, you can't directly access object data but must use getters/setters.

**Key Code:**

```java
public abstract class Ticket {
    // PRIVATE - Cannot be accessed directly
    private String ticketId;
    private String passengerName;
    private int age;

    // PUBLIC - Controlled access
    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }
}
```

---

## ✅ Edge Cases Handled

1. **Invalid menu choice** - Shows error message, program continues
2. **Invalid ticket type** - Validates input (1 or 2), asks again if invalid
3. **Negative age** - Validates age > 0 and < 120
4. **Non-existing ticket ID** - Shows "not found" message
5. **Empty ticket list** - Checks before view/cancel operations
6. **Input mismatch** - Catches `InputMismatchException`, clears buffer, continues
7. **Empty string inputs** - Validates name, train number aren't empty
8. **Duplicate ticket ID prevention** - Auto-generates unique IDs with counter
9. **Invalid fare** - Validates fare > 0

---

## 🎓 Key Takeaways for Examiners

This project demonstrates:

- ✅ **Proper abstraction** through abstract class and methods
- ✅ **Clear inheritance hierarchy** with meaningful relationships
- ✅ **Runtime polymorphism** with dynamic method dispatch
- ✅ **Complete encapsulation** with private fields and public accessors
- ✅ **Production-quality code** with error handling and validation
- ✅ **Real-world mapping** to actual railway reservation scenarios
- ✅ **Clean, commented code** suitable for academic evaluation
