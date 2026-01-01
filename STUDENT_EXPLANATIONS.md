# Student-Wise OOPS Concept Explanations

## Railway Reservation System Project

---

# STUDENT 1: ENCAPSULATION

## Definition (In Your Own Words)

Encapsulation means wrapping data and the methods that work on that data together in one unit (a class), and hiding the internal details from outside access. We make the data private and provide public methods (getters and setters) to access or modify it in a controlled way.

Think of it like a capsule medicine - the medicine is protected inside, and you cannot directly access it. You can only use it in the intended way.

---

## Why Encapsulation is Needed in THIS Project

In our Railway Reservation System:

1. **Data Protection**: Ticket information like ticketId, passengerName, and fare are sensitive. We don't want anyone to directly change the ticket ID after it's generated or modify the fare incorrectly.

2. **Controlled Access**: By using getters and setters, we ensure that data is accessed and modified only through proper methods. For example, we can add validation in setters to prevent negative age or empty passenger names.

3. **Security**: Private fields prevent accidental or unauthorized changes to ticket data from other parts of the program.

4. **Flexibility**: If we want to change how data is stored internally (for example, storing age in months instead of years), we can do it without affecting other parts of the code.

---

## Java Code Snippet from Project

**File**: `Ticket.java`

```java
public abstract class Ticket {
    // ENCAPSULATION - All fields are private
    private String ticketId;
    private String passengerName;
    private int age;
    private String trainNumber;
    private double baseFare;

    // Public getter - controlled read access
    public String getTicketId() {
        return ticketId;
    }

    // Public setter - controlled write access
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

    // ... other getters and setters
}
```

---

## Explanation of the Code

**Step 1: Private Fields**

```java
private String ticketId;
private String passengerName;
private int age;
```

- All important data fields are declared as `private`
- `private` keyword means these variables cannot be accessed directly from outside the class
- Only methods inside the Ticket class can directly use these variables

**Step 2: Public Getters**

```java
public String getTicketId() {
    return ticketId;  // Returns the private field value
}
```

- Getter methods are public, so they can be called from anywhere
- They provide "read-only" access to private fields
- Return the value of the private field without allowing direct modification

**Step 3: Public Setters**

```java
public void setTicketId(String ticketId) {
    this.ticketId = ticketId;  // Sets the private field value
}
```

- Setter methods are public and allow controlled modification
- Accept a parameter and assign it to the private field
- We can add validation logic inside setters before setting the value

**Real Example in Code**:

```java
// WRONG - Cannot do this because ticketId is private
ticket.ticketId = "ABC123";  // Compilation ERROR

// CORRECT - Use setter method
ticket.setTicketId("ABC123");  // Works properly

// CORRECT - Use getter method
String id = ticket.getTicketId();  // Read the value
```

---

## Viva Questions You Might Face

**Q1: What is encapsulation?**  
A: Encapsulation is wrapping data and methods together and hiding internal details by making fields private and providing public getters and setters for controlled access.

**Q2: Why not just make all fields public?**  
A: Public fields can be directly modified from anywhere, leading to invalid data. Private fields with getters/setters give us control to validate and protect data.

**Q3: Where is encapsulation used in your project?**  
A: In the Ticket class, all fields (ticketId, passengerName, age, etc.) are private, and we provide public getters and setters for each field.

**Q4: Can you add validation in encapsulation?**  
A: Yes! In setters, we can add validation. For example:

```java
public void setAge(int age) {
    if (age > 0 && age < 120) {
        this.age = age;
    } else {
        System.out.println("Invalid age!");
    }
}
```

**Q5: What are the benefits of encapsulation?**  
A: Data security, controlled access, flexibility to change internal implementation, and data validation.

---

# STUDENT 2: INHERITANCE

## Definition (In Your Own Words)

Inheritance means creating a new class based on an existing class. The new class (called child class or subclass) automatically gets all the properties and methods of the existing class (called parent class or superclass). The child class can add its own new features and can also modify inherited behaviors.

Think of it like how a child inherits traits from parents but also has their own unique features.

---

## Why Inheritance is Needed in THIS Project

In our Railway Reservation System:

1. **Code Reuse**: Both Sleeper and AC tickets have common properties like ticketId, passengerName, age, trainNumber. Instead of writing these in both classes, we write once in parent class and reuse.

2. **Logical Relationship**: Sleeper ticket IS A type of railway ticket. AC ticket IS A type of railway ticket. This is a natural parent-child relationship.

3. **Avoiding Duplication**: Without inheritance, we would have to copy-paste the same code in SleeperTicket and ACTicket classes. Inheritance avoids this duplication.

4. **Easy Maintenance**: If we need to add a new common property (like booking date), we add it once in parent class and all child classes automatically get it.

5. **Organized Structure**: Creates a clear hierarchy - Ticket (parent) → SleeperTicket and ACTicket (children).

---

## Java Code Snippet from Project

**File**: `Ticket.java` (Parent Class)

```java
public abstract class Ticket {
    // Common properties for ALL tickets
    private String ticketId;
    private String passengerName;
    private int age;
    private String trainNumber;
    private double baseFare;

    // Constructor
    public Ticket(String ticketId, String passengerName, int age,
                  String trainNumber, double baseFare) {
        this.ticketId = ticketId;
        this.passengerName = passengerName;
        this.age = age;
        this.trainNumber = trainNumber;
        this.baseFare = baseFare;
    }

    // Common method for ALL tickets
    public void displayTicketDetails() {
        System.out.println("Ticket ID: " + ticketId);
        System.out.println("Passenger: " + passengerName);
        // ...
    }
}
```

**File**: `SleeperTicket.java` (Child Class)

```java
public class SleeperTicket extends Ticket {
    // Additional property specific to Sleeper
    private String coachType;
    private static final double SERVICE_CHARGE = 50.0;

    // Constructor
    public SleeperTicket(String ticketId, String passengerName, int age,
                         String trainNumber, double baseFare) {
        // Call parent class constructor using super()
        super(ticketId, passengerName, age, trainNumber, baseFare);
        this.coachType = "Sleeper Class";
    }

    // SleeperTicket now has:
    // - All parent class fields (ticketId, passengerName, etc.)
    // - All parent class methods (displayTicketDetails, getters/setters)
    // - Its own field (coachType)
    // - Its own methods (calculateFare)
}
```

**File**: `ACTicket.java` (Child Class)

```java
public class ACTicket extends Ticket {
    // Additional properties specific to AC
    private String acTier;
    private double acCharge;

    // Constructor
    public ACTicket(String ticketId, String passengerName, int age,
                    String trainNumber, double baseFare, String acTier) {
        // Call parent class constructor
        super(ticketId, passengerName, age, trainNumber, baseFare);
        this.acTier = acTier;
        // Set charge based on tier
        switch (acTier) {
            case "1A": this.acCharge = 500.0; break;
            case "2A": this.acCharge = 300.0; break;
            case "3A": this.acCharge = 200.0; break;
        }
    }
}
```

---

## Explanation of the Code

**Step 1: Parent Class Declaration**

```java
public abstract class Ticket {
    // Common fields and methods
}
```

- `Ticket` is the parent class containing all common features
- It's abstract because we never create a plain "Ticket" object, only Sleeper or AC tickets

**Step 2: Child Class Declaration**

```java
public class SleeperTicket extends Ticket {
```

- `extends` keyword establishes inheritance relationship
- `SleeperTicket` is child class, `Ticket` is parent class
- SleeperTicket automatically inherits everything from Ticket

**Step 3: Using super() Keyword**

```java
public SleeperTicket(String ticketId, String passengerName, int age,
                     String trainNumber, double baseFare) {
    super(ticketId, passengerName, age, trainNumber, baseFare);
    this.coachType = "Sleeper Class";
}
```

- `super()` calls the parent class constructor
- First, it initializes parent class fields (ticketId, passengerName, etc.)
- Then, it initializes child class specific fields (coachType)
- `super()` must be the first statement in child class constructor

**Step 4: What Child Class Inherits**

SleeperTicket automatically has:

```java
// Inherited from parent
private String ticketId;         // ✓
private String passengerName;    // ✓
private int age;                 // ✓
private String trainNumber;      // ✓
private double baseFare;         // ✓
public String getTicketId() {}   // ✓
public void setTicketId() {}     // ✓
// ... all getters and setters    // ✓

// Own fields
private String coachType;        // ✓
```

**Real Example in Code**:

```java
SleeperTicket ticket = new SleeperTicket("TKT1000", "Rahul", 25, "12345", 500);

// Can use inherited methods
String name = ticket.getPassengerName();  // Inherited from Ticket
String id = ticket.getTicketId();         // Inherited from Ticket

// Can use own methods
String coach = ticket.getCoachType();     // Own method
```

---

## Viva Questions You Might Face

**Q1: What is inheritance?**  
A: Inheritance is creating a new class from an existing class, where the new class inherits all properties and methods of the existing class.

**Q2: Which classes use inheritance in your project?**  
A: SleeperTicket and ACTicket classes inherit from the Ticket class.

**Q3: What does 'extends' keyword do?**  
A: The 'extends' keyword creates an inheritance relationship. It means the child class inherits from the parent class.

**Q4: What is super() keyword used for?**  
A: super() is used to call the parent class constructor. It initializes the parent class fields before the child class fields.

**Q5: What are the benefits of inheritance?**  
A: Code reuse, avoiding duplication, easy maintenance, logical relationships, and organized structure.

**Q6: Can a child class have its own methods?**  
A: Yes! SleeperTicket has its own coachType field and its own implementation of calculateFare() method.

**Q7: Is Ticket class abstract? Why?**  
A: Yes, because we never create a plain Ticket object. We only create SleeperTicket or ACTicket objects. Abstract class cannot be instantiated.

---

# STUDENT 3: ABSTRACTION

## Definition (In Your Own Words)

Abstraction means hiding the complex implementation details and showing only the necessary features to the user. We show "what" something does, not "how" it does it. In Java, we achieve abstraction using abstract classes and abstract methods.

An abstract method is a method that is declared but has no body (no implementation). Child classes must provide the implementation.

Think of it like driving a car - you know pressing the accelerator makes the car go faster, but you don't need to know how the engine works internally.

---

## Why Abstraction is Needed in THIS Project

In our Railway Reservation System:

1. **Hide Complexity**: Users don't need to know the complex fare calculation formula. They just call `calculateFare()` and get the result.

2. **Different Implementations**: Sleeper and AC tickets calculate fares differently, but the user interface is the same - just call `calculateFare()`.

3. **Force Implementation**: By making `calculateFare()` abstract, we force every ticket type to provide its own fare calculation method.

4. **Consistent Interface**: All ticket types have the same method name `calculateFare()`, even though they calculate differently. This makes the code easier to use.

5. **Future Extension**: If we add a new ticket type (like First Class), it must implement `calculateFare()`. The abstract method ensures this.

---

## Java Code Snippet from Project

**File**: `Ticket.java`

```java
// Abstract class - cannot create object of this class
public abstract class Ticket {

    private String ticketId;
    private String passengerName;
    private int age;
    private String trainNumber;
    private double baseFare;

    // Constructor
    public Ticket(String ticketId, String passengerName, int age,
                  String trainNumber, double baseFare) {
        this.ticketId = ticketId;
        this.passengerName = passengerName;
        this.age = age;
        this.trainNumber = trainNumber;
        this.baseFare = baseFare;
    }

    // ABSTRACTION - Abstract method with no implementation
    // Child classes MUST implement this
    public abstract double calculateFare();

    // Concrete method - has implementation
    // Uses abstract method without knowing how it works
    public void displayTicketDetails() {
        System.out.println("Ticket ID    : " + ticketId);
        System.out.println("Passenger    : " + passengerName);
        System.out.println("Age          : " + age);
        System.out.println("Train Number : " + trainNumber);
        System.out.println("Base Fare    : ₹" + baseFare);

        // Calls abstract method - doesn't know the implementation
        System.out.println("Total Fare   : ₹" + calculateFare());
    }

    // Getters
    public int getAge() { return age; }
    public double getBaseFare() { return baseFare; }
}
```

**File**: `SleeperTicket.java`

```java
public class SleeperTicket extends Ticket {

    private static final double SERVICE_CHARGE = 50.0;

    public SleeperTicket(String ticketId, String passengerName, int age,
                         String trainNumber, double baseFare) {
        super(ticketId, passengerName, age, trainNumber, baseFare);
    }

    // Implementation of abstract method
    @Override
    public double calculateFare() {
        double totalFare = getBaseFare() + SERVICE_CHARGE;

        // Sleeper-specific logic: Higher discounts
        if (getAge() < 12) {
            totalFare = totalFare * 0.5;  // 50% discount for children
        } else if (getAge() >= 60) {
            totalFare = totalFare * 0.6;  // 40% discount for senior
        }

        return totalFare;
    }
}
```

**File**: `ACTicket.java`

```java
public class ACTicket extends Ticket {

    private String acTier;
    private double acCharge;

    public ACTicket(String ticketId, String passengerName, int age,
                    String trainNumber, double baseFare, String acTier) {
        super(ticketId, passengerName, age, trainNumber, baseFare);
        this.acTier = acTier;

        // Set charge based on tier
        switch (acTier) {
            case "1A": this.acCharge = 500.0; break;
            case "2A": this.acCharge = 300.0; break;
            case "3A": this.acCharge = 200.0; break;
        }
    }

    // Implementation of abstract method
    @Override
    public double calculateFare() {
        double totalFare = getBaseFare() + acCharge;

        // AC-specific logic: Lower discounts
        if (getAge() < 12) {
            totalFare = totalFare * 0.5;  // 50% discount for children
        } else if (getAge() >= 60) {
            totalFare = totalFare * 0.7;  // 30% discount for senior
        }

        return totalFare;
    }
}
```

---

## Explanation of the Code

**Step 1: Abstract Class Declaration**

```java
public abstract class Ticket {
```

- `abstract` keyword makes this an abstract class
- Cannot create objects: `new Ticket()` will give error
- Can only create objects of child classes: `new SleeperTicket()` works

**Step 2: Abstract Method Declaration**

```java
public abstract double calculateFare();
```

- `abstract` keyword + no method body (no curly braces `{}`)
- Only method signature is defined
- Does not specify HOW to calculate, only that it must be calculated
- Ends with semicolon `;`

**Step 3: Using Abstract Method in Concrete Method**

```java
public void displayTicketDetails() {
    // ...
    System.out.println("Total Fare: ₹" + calculateFare());
    // Calls abstract method without knowing implementation
}
```

- `displayTicketDetails()` is a concrete method (has implementation)
- It calls `calculateFare()` without knowing how fare is calculated
- This is abstraction - using something without knowing its internal details

**Step 4: Child Class Implementation**

```java
@Override
public double calculateFare() {
    double totalFare = getBaseFare() + SERVICE_CHARGE;
    // ... calculation logic ...
    return totalFare;
}
```

- Child class MUST implement the abstract method
- `@Override` annotation shows we're implementing parent's abstract method
- Now the method has actual implementation (method body with logic)
- Different child classes can have different implementations

**Why This is Abstraction:**

User code:

```java
SleeperTicket ticket = new SleeperTicket(...);

// User just calls the method
double fare = ticket.calculateFare();

// User doesn't know:
// - How is SERVICE_CHARGE added?
// - How is age-based discount calculated?
// - What formula is used?
// User only knows: I get the final fare amount
```

---

## Viva Questions You Might Face

**Q1: What is abstraction?**  
A: Abstraction is hiding implementation details and showing only essential features. We show what something does, not how it does it.

**Q2: How is abstraction achieved in Java?**  
A: Using abstract classes and abstract methods. Abstract methods have no implementation in parent class; child classes must provide implementation.

**Q3: Where is abstraction used in your project?**  
A: In the Ticket class, `calculateFare()` is an abstract method. Users don't know how fare is calculated; they just call the method and get the result.

**Q4: Can you create an object of abstract class?**  
A: No. Abstract classes cannot be instantiated. We can only create objects of concrete child classes.

**Q5: What is an abstract method?**  
A: An abstract method is a method declared without implementation (no body). It only has method signature. Child classes must implement it.

**Q6: What if child class doesn't implement abstract method?**  
A: Compilation error. Every child class must implement all abstract methods of parent class, or the child class itself must be declared abstract.

**Q7: Can abstract class have concrete methods?**  
A: Yes! Ticket class has `displayTicketDetails()` which is a concrete method with full implementation.

**Q8: Why is calculateFare() abstract and not concrete?**  
A: Because each ticket type calculates fare differently. Sleeper adds service charge, AC adds AC charge. We cannot write one formula that works for all.

---

# STUDENT 4: POLYMORPHISM

## Definition (In Your Own Words)

Polymorphism means "many forms". The same method name behaves differently based on the object that calls it. In Java, polymorphism allows a parent class reference to hold child class objects, and when we call a method, the actual child class method gets executed.

There are two types:

1. **Compile-time polymorphism**: Method overloading (same name, different parameters)
2. **Runtime polymorphism**: Method overriding (child class changes parent's method behavior)

Our project uses runtime polymorphism.

Think of it like a smartphone's "play" button - it plays music, videos, or games depending on which app is open. Same button, different behavior.

---

## Why Polymorphism is Needed in THIS Project

In our Railway Reservation System:

1. **Single Interface, Multiple Behaviors**: We use one `Ticket` reference to handle both SleeperTicket and ACTicket objects. Same method call `calculateFare()` produces different results.

2. **Flexibility**: We can add new ticket types (like FirstClass) without changing the main code. Just create new class and override `calculateFare()`.

3. **Code Simplification**: Instead of writing separate code for Sleeper and AC, we write once using parent reference:

   ```java
   Ticket ticket;  // One reference for all ticket types
   ```

4. **Dynamic Behavior**: Which `calculateFare()` method runs is decided at runtime based on actual object type, not at compile time.

5. **Real-world Mapping**: In real life, "calculate fare" works differently for different ticket types, but the action is the same - polymorphism models this perfectly.

---

## Java Code Snippet from Project

**Scenario**: In `RailwayReservationSystem.java`, we use polymorphism when booking tickets.

```java
// Parent class reference - can hold any child class object
Ticket ticket = null;

// User selects ticket type
int ticketType = getIntInput("Enter choice (1 or 2): ");

// POLYMORPHISM: Parent reference holds child object
if (ticketType == 1) {
    // Parent reference holding SleeperTicket object
    ticket = new SleeperTicket(ticketId, name, age, trainNumber, baseFare);

} else if (ticketType == 2) {
    // Same parent reference holding ACTicket object
    ticket = new ACTicket(ticketId, name, age, trainNumber, baseFare, tier);
}

// Add to list - list stores Ticket references
ticketList.add(ticket);

// RUNTIME POLYMORPHISM - which calculateFare() runs?
// Decided at runtime based on actual object type!
double totalFare = ticket.calculateFare();

System.out.println("Total Fare: ₹" + totalFare);
```

**Method Overriding** in Child Classes:

`SleeperTicket.java`:

```java
@Override
public double calculateFare() {
    double totalFare = getBaseFare() + SERVICE_CHARGE;

    if (getAge() < 12) {
        totalFare = totalFare * 0.5;  // 50% discount
    } else if (getAge() >= 60) {
        totalFare = totalFare * 0.6;  // 40% discount
    }

    return totalFare;  // Sleeper calculation
}
```

`ACTicket.java`:

```java
@Override
public double calculateFare() {
    double totalFare = getBaseFare() + acCharge;

    if (getAge() < 12) {
        totalFare = totalFare * 0.5;  // 50% discount
    } else if (getAge() >= 60) {
        totalFare = totalFare * 0.7;  // 30% discount (less than Sleeper)
    }

    return totalFare;  // AC calculation (different formula)
}
```

---

## Explanation of the Code

**Step 1: Parent Reference Variable**

```java
Ticket ticket = null;
```

- `Ticket` is the parent class (abstract)
- `ticket` is a reference variable of type Ticket
- It can hold objects of any child class (SleeperTicket or ACTicket)
- This is the foundation of polymorphism

**Step 2: Assigning Child Objects to Parent Reference**

```java
if (ticketType == 1) {
    ticket = new SleeperTicket(...);  // Sleeper object
} else {
    ticket = new ACTicket(...);       // AC object
}
```

- **Upcasting**: Child class object assigned to parent class reference
- Java allows this automatically (implicit upcasting)
- `ticket` reference is of type Ticket, but actual object is SleeperTicket or ACTicket

**Step 3: Runtime Polymorphism (Dynamic Method Dispatch)**

```java
double totalFare = ticket.calculateFare();
```

- **At compile time**: Java knows there's a `calculateFare()` method in Ticket class
- **At runtime**: Java checks the actual object type and calls that class's method
- If `ticket` holds SleeperTicket → calls SleeperTicket's `calculateFare()`
- If `ticket` holds ACTicket → calls ACTicket's `calculateFare()`
- This decision happens at runtime, not compile time

**Step 4: Method Overriding**

```java
// In SleeperTicket
@Override
public double calculateFare() {
    // Sleeper-specific calculation
}

// In ACTicket
@Override
public double calculateFare() {
    // AC-specific calculation
}
```

- Both methods have same signature (name, return type, parameters)
- `@Override` annotation tells Java we're intentionally overriding parent method
- Each class provides its own implementation
- This enables polymorphism

**Real Example:**

```java
// Example 1: Sleeper Ticket
Ticket ticket1 = new SleeperTicket("TKT1000", "Rahul", 25, "12345", 500);
double fare1 = ticket1.calculateFare();
// Calls SleeperTicket's calculateFare()
// Result: 500 + 50 = 550

// Example 2: AC Ticket
Ticket ticket2 = new ACTicket("TKT1001", "Priya", 25, "54321", 800, "2A");
double fare2 = ticket2.calculateFare();
// Calls ACTicket's calculateFare()
// Result: 800 + 300 = 1100

// Same method call, different results - POLYMORPHISM!
```

**Another Example - Method Overriding:**

```java
SleeperTicket sleeper = new SleeperTicket(...);
sleeper.displayTicketDetails();
// Calls SleeperTicket's overridden displayTicketDetails()
// Shows: Ticket details + Coach Type + Service Charge
```

---

## Viva Questions You Might Face

**Q1: What is polymorphism?**  
A: Polymorphism means one thing taking many forms. Same method name behaves differently based on the object calling it.

**Q2: What types of polymorphism are there?**  
A: Two types - compile-time (method overloading) and runtime (method overriding). Our project uses runtime polymorphism.

**Q3: Where is polymorphism used in your project?**  
A: When we create tickets, we use parent class reference `Ticket ticket` to hold both SleeperTicket and ACTicket objects. When we call `ticket.calculateFare()`, the actual method called depends on the object type at runtime.

**Q4: What is method overriding?**  
A: When a child class provides a different implementation of a method that is already defined in its parent class. We use `@Override` annotation.

**Q5: How is polymorphism different from inheritance?**  
A: Inheritance is about code reuse (child gets parent's features). Polymorphism is about dynamic behavior (same method, different results based on object type).

**Q6: What is runtime/dynamic polymorphism?**  
A: When the decision of which method to call is made at runtime, not at compile time. Java checks the actual object type and calls that class's method.

**Q7: Show polymorphism code from your project**  
A:

```java
Ticket ticket;
if (type == SLEEPER)
    ticket = new SleeperTicket(...);
else
    ticket = new ACTicket(...);

double fare = ticket.calculateFare();  // Polymorphism here
```

**Q8: Why use parent reference instead of child reference?**  
A: Flexibility! We can write one piece of code that works with all ticket types. We don't need separate variables for Sleeper and AC tickets.

**Q9: What is @Override annotation?**  
A: It tells Java that we're intentionally overriding a parent class method. If we make a mistake in method signature, Java will give a compile error.

**Q10: Can you override private methods?**  
A: No. Only public and protected methods can be overridden. Private methods are not visible to child classes.

---

# General Tips for All Students

## For Presentation

1. **Start with Definition** in simple words
2. **Explain Why it's needed** in your specific project
3. **Show Code** and explain line by line
4. **Give Real Examples** using your project
5. **Be confident** - you built this!

## For Viva

1. **Understand, Don't Memorize** - explain in your own words
2. **Point to Code** - know exactly which file and line number
3. **Give Examples** - use scenarios from your project
4. **Be Honest** - if you don't know, say "I need to check that"
5. **Practice** - explain to friends/family before actual viva

## Common Questions for All

**Q: Why did you choose this project?**  
A: Railway reservation is a real-world system everyone uses. It clearly demonstrates all four OOPS concepts with practical examples.

**Q: Is your code production-ready?**  
A: This is an academic project to demonstrate OOPS concepts. For production, we would add database, authentication, payment gateway, etc.

**Q: Can you add more features?**  
A: Yes - database storage, multiple trains, seat availability, payment integration, email confirmation, etc.

---

## Good Luck! 🎓
