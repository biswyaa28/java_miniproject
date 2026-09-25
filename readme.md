# Railway Reservation System

A simple desktop Railway Reservation System built with **Java** and **Swing**.
Made as a beginner-friendly college mini-project — no databases, no external libraries, no build tools.

## Features

- Add Passenger
- Search Train (by Train ID)
- Reserve Seat (with double-booking protection)
- Cancel Reservation (frees the seat again)
- Show Passengers
- Show Reservations
- Show Trains (in sorted order of Train ID)

Each operation opens its own small window with only the fields it needs.

## Files

| File | What it does |
|------|--------------|
| `Main.java` | Starting point — creates the system, loads sample data, opens the GUI |
| `RailwayGUI.java` | The Swing window (main menu + one window per operation) |
| `RailwaySystem.java` | The manager — holds all data and implements every operation |
| `Train.java` | Train details + seat array (0 = free, 1 = booked) |
| `Passenger.java` | Passenger details (ID, name, age) |
| `Reservation.java` | One booking: reservation ID, passenger ID, train ID, seat number |

## Data structures used

- **Array** (`int[] seats`) — tracks seat status in each train
- **LinkedList** — stores passengers and reservations (grows as needed)
- **HashMap** — stores trains for instant search by Train ID
- **TreeMap** — stores trains sorted by Train ID for display

## How to compile and run

You need a Java JDK installed (Java 8 or newer).

```bash
javac *.java
java Main
```

This opens the **Railway Reservation System** window.

## Sample data

Loaded automatically at startup:

| Train ID | Name | Route | Seats |
|----------|------|-------|-------|
| 1001 | Rajdhani Express | Delhi → Mumbai | 30 |
| 1002 | Shatabdi Express | Delhi → Chandigarh | 20 |
| 1003 | Duronto Express | Mumbai → Pune | 25 |

Passengers: **501 – Anil (25)** and **502 – Sita (30)**
