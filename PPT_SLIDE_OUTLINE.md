# PPT Slide Outline - Railway Reservation System

## OOPS Mini Project Presentation

---

## SLIDE 1: TITLE SLIDE

**Title**: Railway Reservation System  
**Subtitle**: Object-Oriented Programming Concepts Implementation in Java

**Team Members**:

- Student 1: [Name] - Encapsulation
- Student 2: [Name] - Inheritance
- Student 3: [Name] - Abstraction
- Student 4: [Name] - Polymorphism

**Course**: [Course Name]  
**Professor**: [Professor Name]  
**Date**: [Presentation Date]

---

## SLIDE 2: AGENDA

**What We Will Cover Today**:

- Problem Statement
- Project Objectives
- System Architecture
- OOPS Concepts Implementation
  - Encapsulation (Student 1)
  - Inheritance (Student 2)
  - Abstraction (Student 3)
  - Polymorphism (Student 4)
- Live Demonstration
- Conclusion

---

## SLIDE 3: PROBLEM STATEMENT

**Real-World Challenge**:

- Manual railway ticket booking is time-consuming and error-prone
- Different ticket classes have different pricing structures
- Complex fare calculations based on multiple factors
- Need for organized ticket management

**Our Solution**:

- Computerized railway reservation system
- Automated fare calculation
- Menu-driven terminal interface
- In-memory ticket management using ArrayList

---

## SLIDE 4: PROJECT OBJECTIVES

**Main Goals**:

- ✅ Demonstrate all 4 core OOPS concepts clearly
- ✅ Solve a real-world problem
- ✅ Create user-friendly terminal interface
- ✅ Implement automated fare calculation
- ✅ Manage ticket lifecycle (Book, View, Cancel)

**Target Users**:

- Railway passengers booking tickets
- Admin managing reservations

---

## SLIDE 5: SYSTEM OVERVIEW

**Technologies Used**:

- Language: Java (JDK 8+)
- Interface: Terminal/Console-based
- Storage: In-memory (ArrayList)
- Input: Scanner class

**Key Features**:

- Book tickets (Sleeper/AC class)
- View ticket details by ID
- Cancel tickets with confirmation
- Automatic discount for children and seniors
- Unique ticket ID generation

---

## SLIDE 6: SYSTEM ARCHITECTURE

**Class Structure**:

```
Ticket (Abstract Parent Class)
    ↓
    ├── SleeperTicket (Child Class)
    └── ACTicket (Child Class)

RailwayReservationSystem (Main Class)
```

**Files**:

- `Ticket.java` - Abstract base class
- `SleeperTicket.java` - Sleeper implementation
- `ACTicket.java` - AC implementation
- `RailwayReservationSystem.java` - Main application

---

## SLIDE 7: ENCAPSULATION (STUDENT 1)

**Presented by**: [Student 1 Name]

**Definition**:

- Wrapping data and methods together
- Hiding internal details from outside
- Controlled access through getters/setters

**Implementation**:

- All fields in Ticket class are `private`
- Public getter and setter methods provided
- Data protection from unauthorized access

**Key Code**:

```java
private String ticketId;
public String getTicketId() { return ticketId; }
public void setTicketId(String id) { this.ticketId = id; }
```

**Benefits**:

- Data security
- Controlled modification
- Validation capability

---

## SLIDE 8: ENCAPSULATION - CODE EXAMPLE

**Class**: `Ticket.java`

```java
public abstract class Ticket {
    // Private fields - ENCAPSULATION
    private String ticketId;
    private String passengerName;
    private int age;
    private String trainNumber;
    private double baseFare;

    // Public getter - controlled access
    public String getTicketId() {
        return ticketId;
    }

    // Public setter - controlled modification
    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }
}
```

**Result**: Data is protected and accessed only through methods

---

## SLIDE 9: INHERITANCE (STUDENT 2)

**Presented by**: [Student 2 Name]

**Definition**:

- Creating new class from existing class
- Child inherits parent's properties and methods
- Establishes "IS-A" relationship

**Implementation**:

- `SleeperTicket` extends `Ticket`
- `ACTicket` extends `Ticket`
- Both inherit common ticket properties
- Each adds its own specific features

**Key Code**:

```java
public class SleeperTicket extends Ticket {
    // Inherits all parent fields and methods
    // super() calls parent constructor
}
```

**Benefits**:

- Code reuse
- Avoids duplication
- Logical hierarchy

---

## SLIDE 10: INHERITANCE - CODE EXAMPLE

**Parent Class**: `Ticket.java`

```java
public abstract class Ticket {
    private String ticketId;
    private String passengerName;
    // ... common fields
}
```

**Child Class**: `SleeperTicket.java`

```java
public class SleeperTicket extends Ticket {
    private String coachType;

    public SleeperTicket(...) {
        super(ticketId, name, age, train, fare);
        this.coachType = "Sleeper Class";
    }
}
```

**Result**: SleeperTicket has all Ticket fields + its own field

---

## SLIDE 11: ABSTRACTION (STUDENT 3)

**Presented by**: [Student 3 Name]

**Definition**:

- Hiding implementation details
- Showing only essential features
- Achieved through abstract classes/methods

**Implementation**:

- `Ticket` is abstract class
- `calculateFare()` is abstract method
- Child classes provide implementation
- User calls method without knowing logic

**Key Code**:

```java
public abstract class Ticket {
    public abstract double calculateFare();
}
```

**Benefits**:

- Hides complexity
- Consistent interface
- Forces implementation in child classes

---

## SLIDE 12: ABSTRACTION - CODE EXAMPLE

**Abstract Class**: `Ticket.java`

```java
public abstract class Ticket {
    // Abstract method - no implementation
    public abstract double calculateFare();

    // Uses abstract method
    public void displayTicketDetails() {
        System.out.println("Fare: " + calculateFare());
        // Doesn't know HOW fare is calculated
    }
}
```

**Child Implementation**: `SleeperTicket.java`

```java
@Override
public double calculateFare() {
    // Sleeper-specific calculation
    return getBaseFare() + SERVICE_CHARGE;
}
```

**Result**: User sees only the result, not the calculation logic

---

## SLIDE 13: POLYMORPHISM (STUDENT 4)

**Presented by**: [Student 4 Name]

**Definition**:

- One thing taking many forms
- Same method, different behavior
- Runtime method selection

**Implementation**:

- Parent reference holds child objects
- `calculateFare()` overridden in child classes
- Runtime decides which method to call
- Dynamic method dispatch

**Key Code**:

```java
Ticket ticket = new SleeperTicket(...);
double fare = ticket.calculateFare();
// Calls SleeperTicket's version
```

**Benefits**:

- Flexibility
- Dynamic behavior
- Easy to extend

---

## SLIDE 14: POLYMORPHISM - CODE EXAMPLE

**Main Class**: `RailwayReservationSystem.java`

```java
// Parent reference
Ticket ticket;

if (ticketType == 1) {
    ticket = new SleeperTicket(...);
} else {
    ticket = new ACTicket(...);
}

// POLYMORPHISM - runtime decision
double fare = ticket.calculateFare();
```

**SleeperTicket**: `fare = base + 50 + discounts`  
**ACTicket**: `fare = base + 200-500 + discounts`

**Same call, different results!**

---

## SLIDE 15: FARE CALCULATION LOGIC

**Sleeper Class**:

- Base Fare + ₹50 Service Charge
- Children (< 12 years): 50% discount
- Senior Citizens (≥ 60 years): 40% discount

**AC Class**:

- Base Fare + AC Charge (tier-based)
  - 1A: +₹500
  - 2A: +₹300
  - 3A: +₹200
- Children (< 12 years): 50% discount
- Senior Citizens (≥ 60 years): 30% discount

**Polymorphism**: Same method, different calculations

---

## SLIDE 16: SYSTEM FEATURES

**Menu Options**:

1. 📝 Book New Ticket

   - Enter passenger details
   - Select ticket class
   - Automatic fare calculation
   - Unique ticket ID generation

2. 🔍 View Ticket Details

   - Search by ticket ID
   - Display complete information

3. ❌ Cancel Ticket

   - Confirmation required
   - 80% refund calculated
   - Ticket removed from system

4. 🚪 Exit System

---

## SLIDE 17: BOOKING PROCESS FLOW

**Step-by-Step**:

1. User enters passenger name
2. User enters age (validated)
3. User enters train number
4. User enters base fare
5. User selects ticket type:
   - Option 1: Sleeper Class
   - Option 2: AC Class (then select tier)
6. System generates unique ticket ID
7. System calculates fare using **POLYMORPHISM**
8. Ticket stored in ArrayList
9. Confirmation displayed

---

## SLIDE 18: INPUT VALIDATION & ERROR HANDLING

**Validations Implemented**:

- ✅ Age must be between 1-120
- ✅ Passenger name cannot be empty
- ✅ Train number cannot be empty
- ✅ Base fare must be positive
- ✅ Valid ticket type selection
- ✅ Valid AC tier selection
- ✅ Ticket ID must exist for view/cancel

**Error Handling**:

- ✅ Input mismatch exceptions caught
- ✅ Clear error messages displayed
- ✅ Program never crashes
- ✅ User returned to menu after errors

---

## SLIDE 19: LIVE DEMONSTRATION

**Demo Scenarios**:

1. **Book Sleeper Ticket**

   - Passenger: Rahul, Age: 25
   - Train: 12345, Fare: ₹500
   - Result: TKT1000, Total: ₹550

2. **Book AC Ticket (Senior Citizen)**

   - Passenger: Sunita, Age: 65
   - Train: 54321, Fare: ₹800, Tier: 2A
   - Result: TKT1001, Total: ₹770

3. **View Ticket**

   - Enter: TKT1000
   - Display complete details

4. **Cancel Ticket**
   - Enter: TKT1000
   - Confirm: Yes
   - Refund: ₹440 (80%)

---

## SLIDE 20: SAMPLE OUTPUT - BOOKING

```
╔══════════════════════════════════════════════════╗
║              📝 BOOK NEW TICKET                  ║
╚══════════════════════════════════════════════════╝

👤 Enter Passenger Name: Rahul Kumar
🎂 Enter Age: 25
🚂 Enter Train Number: 12345
💰 Enter Base Fare (₹): 500

🎫 SELECT TICKET CLASS:
  1️⃣  Sleeper Class (Budget Friendly)
  2️⃣  AC Class (Premium Comfort)
👉 Enter your choice: 1

══════════════════════════════════════════════════
🎉 BOOKING SUCCESSFUL! 🎉
══════════════════════════════════════════════════
   🎫 Ticket ID    : TKT1000
   👤 Passenger    : Rahul Kumar
   🚂 Train No.    : 12345
   💰 Total Fare   : ₹550.00
══════════════════════════════════════════════════
```

---

## SLIDE 21: SAMPLE OUTPUT - VIEW & CANCEL

**View Ticket**:

```
🎫 Enter Ticket ID: TKT1000

✅ Ticket found!

========== TICKET DETAILS ==========
Ticket ID       : TKT1000
Passenger Name  : Rahul Kumar
Age             : 25
Train Number    : 12345
Total Fare      : ₹550.0
====================================
```

**Cancel Ticket**:

```
⚠️  Are you sure you want to cancel? (yes/no): yes

✅ TICKET CANCELLED SUCCESSFULLY!
   🎫 Ticket ID     : TKT1000
   💰 Refund Amount : ₹440.00
   ⏰ Processing Time: 7-10 working days
```

---

## SLIDE 22: OOPS CONCEPTS SUMMARY

**All 4 Concepts Demonstrated**:

| Concept           | Class/Method                           | Purpose          |
| ----------------- | -------------------------------------- | ---------------- |
| **Encapsulation** | Ticket.java private fields             | Data protection  |
| **Inheritance**   | SleeperTicket, ACTicket extends Ticket | Code reuse       |
| **Abstraction**   | abstract calculateFare()               | Hide complexity  |
| **Polymorphism**  | ticket.calculateFare()                 | Dynamic behavior |

**Relationships**:

- Encapsulation + Inheritance = Secure, reusable code
- Abstraction + Polymorphism = Flexible, extensible design

---

## SLIDE 23: ADVANTAGES OF OOPS APPROACH

**Why OOPS for this project?**:

✅ **Modularity**: Each class has single responsibility  
✅ **Reusability**: Common code in parent class  
✅ **Maintainability**: Easy to modify and extend  
✅ **Scalability**: Can add new ticket types easily  
✅ **Real-world Mapping**: Classes represent real entities  
✅ **Code Organization**: Clear structure and hierarchy

**Without OOPS**: Code duplication, difficult maintenance, no flexibility

---

## SLIDE 24: CHALLENGES FACED

**Technical Challenges**:

- Designing proper class hierarchy
- Implementing abstract methods correctly
- Managing ArrayList for ticket storage
- Input validation and error handling

**Solutions Applied**:

- Clear parent-child relationship
- Method overriding with @Override annotation
- try-catch blocks for exception handling
- Comprehensive input validation

**Key Learning**: Planning class structure before coding is crucial

---

## SLIDE 25: FUTURE ENHANCEMENTS

**Possible Extensions**:

**Database Integration**:

- Persistent storage instead of in-memory
- MySQL or MongoDB connection

**Additional Features**:

- Multiple train management
- Seat availability checking
- Payment gateway integration
- Email/SMS confirmations
- Booking history
- User authentication

**GUI Development**:

- Java Swing/JavaFX interface
- Web application using Spring Boot

---

## SLIDE 26: CODE STATISTICS

**Project Metrics**:

- Total Files: 4 Java files
- Total Classes: 4 classes
- Lines of Code: ~450 lines
- Abstract Classes: 1 (Ticket)
- Concrete Classes: 3
- Abstract Methods: 1 (calculateFare)
- Overridden Methods: 2 classes
- Private Fields: 8 fields
- Public Methods: 15+ methods

**Code Quality**:

- ✅ Proper comments
- ✅ Java naming conventions
- ✅ Comprehensive error handling
- ✅ No compilation warnings

---

## SLIDE 27: HOW TO RUN THE PROJECT

**Compilation**:

```bash
javac *.java
```

**Execution**:

```bash
java RailwayReservationSystem
```

**Requirements**:

- JDK 8 or higher
- Terminal/Command Prompt
- No external libraries needed

**Simple and straightforward!**

---

## SLIDE 28: KEY TAKEAWAYS

**What We Learned**:

1. **Encapsulation**: How to protect data and provide controlled access
2. **Inheritance**: How to create class hierarchies and reuse code
3. **Abstraction**: How to hide implementation and show only essentials
4. **Polymorphism**: How same method can behave differently

**Why This Project**:

- Real-world application
- Clear demonstration of all OOPS concepts
- Practical problem-solving
- Clean, maintainable code

---

## SLIDE 29: CONCLUSION

**Project Accomplishments**:

- ✅ Successfully implemented all 4 OOPS concepts
- ✅ Created functional railway reservation system
- ✅ User-friendly terminal interface
- ✅ Robust error handling
- ✅ Clean, well-documented code

**Real-World Impact**:

- Automates ticket booking process
- Reduces manual errors
- Faster fare calculation
- Organized ticket management

**Academic Value**:

- Excellent OOPS learning project
- Demonstrates theory with practice

---

## SLIDE 30: THANK YOU

**Questions & Discussion**

**Team Members**:

- Student 1: [Name] - Encapsulation
- Student 2: [Name] - Inheritance
- Student 3: [Name] - Abstraction
- Student 4: [Name] - Polymorphism

**Project Repository**: [GitHub link if available]

**Contact**: [Team email/contact]

---

### We are ready for your questions! 🎓

---

# PRESENTATION TIPS

## For Each Student

**When presenting your OOPS concept**:

1. Start with clear definition (30 seconds)
2. Explain why needed in THIS project (45 seconds)
3. Show code on screen and explain (1.5 minutes)
4. Give example/demo if possible (30 seconds)
5. Conclude with benefits (30 seconds)
6. **Total: ~3 minutes per student**

## Slide Timing Recommendation

- Slides 1-6: Introduction (5 minutes)
- Slides 7-8: Student 1 - Encapsulation (3 minutes)
- Slides 9-10: Student 2 - Inheritance (3 minutes)
- Slides 11-12: Student 3 - Abstraction (3 minutes)
- Slides 13-14: Student 4 - Polymorphism (3 minutes)
- Slides 15-21: Demo & Output (5 minutes)
- Slides 22-30: Summary & Conclusion (3 minutes)
- **Total: ~25 minutes + Q&A**

## General Presentation Tips

✅ **Practice together** as a team  
✅ **Know your part** thoroughly  
✅ **Keep slides simple** - bullets, not paragraphs  
✅ **Use code highlighting** for important parts  
✅ **Maintain eye contact** with audience  
✅ **Speak clearly** and at moderate pace  
✅ **Be ready for questions** - study STUDENT_EXPLANATIONS.md  
✅ **Have backup** - code ready to run if demo fails

## Common Questions to Prepare

1. Why did you choose this project?
2. Which OOPS concept was hardest to implement?
3. Can you add [some feature]?
4. How does polymorphism work in your code?
5. What if you have more ticket types?
6. How do you prevent duplicate ticket IDs?
7. Show me the code for [concept]
8. Explain [specific method] in your code

**Good Luck with Your Presentation!** 🎯
