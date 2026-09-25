// Passenger.java
// Stores the details of one passenger.
public class Passenger {

    // --- Data (fields) of a passenger ---
    int passengerId;   // unique number for the passenger, e.g. 501
    String name;       // name of the passenger, e.g. "Anil"
    int age;           // age of the passenger, e.g. 25

    // --- Constructor: runs when we create a new Passenger object ---
    public Passenger(int passengerId, String name, int age) {
        this.passengerId = passengerId;   // set this object's passengerId
        this.name = name;                 // set this object's name
        this.age = age;                   // set this object's age
    }

    // --- Display passenger details on the screen ---
    public void displayPassenger() {
        System.out.println("Passenger ID : " + passengerId);
        System.out.println("Name         : " + name);
        System.out.println("Age          : " + age);
    }
}
