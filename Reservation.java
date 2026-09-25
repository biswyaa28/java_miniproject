// Reservation.java
// Links one passenger to one train and one seat.
public class Reservation {

    // --- Data (fields) of a reservation ---
    int reservationId;   // unique number of this booking, e.g. 1001
    int passengerId;     // which passenger booked (links to Passenger)
    int trainId;         // which train is booked (links to Train)
    int seatNumber;      // which seat is booked on that train

    // --- Constructor: runs when we create a new Reservation object ---
    public Reservation(int reservationId, int passengerId,
                       int trainId, int seatNumber) {
        this.reservationId = reservationId;
        this.passengerId = passengerId;
        this.trainId = trainId;
        this.seatNumber = seatNumber;
    }

    // --- Display reservation details on the screen ---
    public void displayReservation() {
        System.out.println("Reservation ID : " + reservationId);
        System.out.println("Passenger ID   : " + passengerId);
        System.out.println("Train ID       : " + trainId);
        System.out.println("Seat Number    : " + seatNumber);
    }
}
