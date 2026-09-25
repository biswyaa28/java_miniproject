// Train.java
// Represents one train with seats that can be reserved or cancelled.
public class Train {

    // --- Data (fields) of a train ---
    int trainId;          // unique number of the train, e.g. 101
    String trainName;     // name of the train, e.g. "Rajdhani Express"
    String source;        // starting station, e.g. "Delhi"
    String destination;   // last station, e.g. "Mumbai"
    int totalSeats;       // total number of seats in the train
    int[] seats;          // seat array: 0 = free seat, 1 = booked seat

    // --- Constructor: runs when we create a new Train object ---
    public Train(int trainId, String trainName, String source,
                 String destination, int totalSeats) {
        this.trainId = trainId;
        this.trainName = trainName;
        this.source = source;
        this.destination = destination;
        this.totalSeats = totalSeats;

        // New int array is already filled with 0 (0 = free seat)
        seats = new int[totalSeats];
    }

    // --- Display train details on the screen ---
    public void displayTrain() {
        System.out.println("Train ID   : " + trainId);
        System.out.println("Train Name : " + trainName);
        System.out.println("From       : " + source);
        System.out.println("To         : " + destination);
        System.out.println("Total Seats: " + totalSeats);
    }

    // --- Check whether a SPECIFIC seat is free ---
    public boolean isSeatAvailable(int seatNumber) {
        int index = seatNumber - 1;              // convert 1-based to 0-based
        if (index < 0 || index >= seats.length) {
            return false;                        // wrong seat number
        }
        return seats[index] == 0;                // 0 means free
    }

    // --- Book a SPECIFIC seat ---
    public void reserveSeat(int seatNumber) {
        if (isSeatAvailable(seatNumber)) {       // that seat must be free
            seats[seatNumber - 1] = 1;           // mark it as booked
            System.out.println("Seat " + seatNumber + " reserved.");
        } else {
            System.out.println("Seat " + seatNumber + " not available!");
        }
    }

    // --- Free a booked seat (seatNumber starts from 1) ---
    public void cancelSeat(int seatNumber) {
        int index = seatNumber - 1;        // convert 1-based to 0-based
        if (index < 0 || index >= seats.length) {
            System.out.println("Invalid seat number!");
        } else if (seats[index] == 0) {
            System.out.println("Seat " + seatNumber + " is already free.");
        } else {
            seats[index] = 0;              // mark it as free again
            System.out.println("Booking of seat " + seatNumber + " cancelled.");
        }
    }
}
