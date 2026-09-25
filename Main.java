// Main.java
// Starting point of the Railway Reservation System.
public class Main {
    public static void main(String[] args) {

        // 1. Create the system
        RailwaySystem system = new RailwaySystem();

        // 2 & 3. Add sample trains and passengers
        system.loadSampleData();

        // 4 & 5. Create the GUI and show it
        new RailwayGUI(system);
    }
}
