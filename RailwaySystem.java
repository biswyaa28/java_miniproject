// RailwaySystem.java
// Connects Train, Passenger and Reservation using
// HashMap (search), TreeMap (sorted), LinkedList (records).
import java.util.LinkedList;
import java.util.HashMap;
import java.util.TreeMap;

public class RailwaySystem {

    // --- Our four containers ---
    LinkedList<Passenger> passengers = new LinkedList<Passenger>();
    LinkedList<Reservation> reservations = new LinkedList<Reservation>();
    HashMap<Integer, Train> trains = new HashMap<Integer, Train>();
    TreeMap<Integer, Train> sortedTrains = new TreeMap<Integer, Train>();

    int nextReservationId = 1001;   // every new booking gets a unique ID

    // ===== 1. Add train =====
    public void addTrain(Train t) {
        trains.put(t.trainId, t);            // for searching by ID
        sortedTrains.put(t.trainId, t);      // for showing in sorted order
        System.out.println("Train added: " + t.trainName);
    }

    // ===== 2. Add passenger =====
    public void addPassenger(int id, String name, int age) {
        Passenger p = new Passenger(id, name, age);
        passengers.add(p);                   // add to LinkedList
        System.out.println("Passenger added: " + name);
    }

    // ===== 3. Search train by Train ID =====
    public Train searchTrain(int trainId) {
        return trains.get(trainId);          // HashMap finds it directly
    }

    // ===== 4. Display trains in sorted order =====
    public void showSortedTrains() {
        System.out.println("--- Trains (sorted by ID) ---");
        for (int id : sortedTrains.keySet()) {   // keys come out sorted
            Train t = sortedTrains.get(id);
            t.displayTrain();
            System.out.println();
        }
    }

    // ===== 5. Make a reservation =====
    // Returns the result message, so the GUI can show it in the text area.
    public String makeReservation(int passengerId, int trainId, int seatNumber) {
        Train train = trains.get(trainId);              // find train (HashMap)
        if (train == null) {
            return report("Train not found!");
        }
        if (!train.isSeatAvailable(seatNumber)) {       // check that seat
            return report("Seat " + seatNumber + " is already booked!");
        }
        train.reserveSeat(seatNumber);                  // book the seat
        int id = nextReservationId;                     // ID for this booking
        nextReservationId = nextReservationId + 1;      // next booking gets +1
        reservations.add(new Reservation(id, passengerId, trainId, seatNumber));
        return report("Booking done! Reservation ID: " + id);
    }

    // ===== 6. Cancel a reservation =====
    public String cancelReservation(int reservationId) {
        for (int i = 0; i < reservations.size(); i++) {
            Reservation r = reservations.get(i);
            if (r.reservationId == reservationId) {     // found the booking
                trains.get(r.trainId).cancelSeat(r.seatNumber);  // free the seat
                reservations.remove(i);                 // remove the booking
                return report("Reservation " + reservationId + " cancelled.");
            }
        }
        return report("Reservation not found!");        // no match in the loop
    }

    // --- Print a message on the console and give it back to the caller ---
    private String report(String text) {
        System.out.println(text);
        return text;
    }

    // ===== 7. Display passengers =====
    public void showPassengers() {
        System.out.println("--- Passengers ---");
        for (Passenger p : passengers) {
            p.displayPassenger();
            System.out.println();
        }
    }

    // ===== 8. Display reservations =====
    public void showReservations() {
        System.out.println("--- Reservations ---");
        for (Reservation r : reservations) {
            r.displayReservation();
            System.out.println();
        }
    }

    // ===== Sample data for demo =====
    public void loadSampleData() {
        // 3 sample trains
        addTrain(new Train(1001, "Rajdhani Express", "Delhi", "Mumbai", 30));
        addTrain(new Train(1002, "Shatabdi Express", "Delhi", "Chandigarh", 20));
        addTrain(new Train(1003, "Duronto Express", "Mumbai", "Pune", 25));
        // 2 sample passengers
        addPassenger(501, "Anil", 25);
        addPassenger(502, "Sita", 30);
    }
}
