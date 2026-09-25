// RailwayGUI.java
// Main window with 7 buttons - each button opens its own small form window.
// The GUI only collects input, calls RailwaySystem, and displays the result.
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RailwayGUI {

    // The backend we already built (passed in from Main)
    RailwaySystem system;

    // ================= MAIN WINDOW =================
    public RailwayGUI(RailwaySystem system) {
        this.system = system;

        JFrame frame = new JFrame("Railway Reservation System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(340, 440);

        // 7 buttons, one column (one button per operation)
        JPanel buttonPanel = new JPanel(new GridLayout(7, 1, 8, 8));
        JButton addPassengerButton = new JButton("Add Passenger");
        JButton searchTrainButton = new JButton("Search Train");
        JButton reserveButton = new JButton("Reserve Seat");
        JButton cancelButton = new JButton("Cancel Reservation");
        JButton showPassengersButton = new JButton("Show Passengers");
        JButton showReservationsButton = new JButton("Show Reservations");
        JButton showTrainsButton = new JButton("Show Trains");
        buttonPanel.add(addPassengerButton);
        buttonPanel.add(searchTrainButton);
        buttonPanel.add(reserveButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(showPassengersButton);
        buttonPanel.add(showReservationsButton);
        buttonPanel.add(showTrainsButton);

        frame.setLayout(new BorderLayout(10, 10));
        frame.add(new JLabel("  Choose an operation:"), BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);   // center window on screen
        frame.setVisible(true);

        // Each main button simply opens its own window
        addPassengerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openAddPassengerWindow();
            }
        });
        searchTrainButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openSearchTrainWindow();
            }
        });
        reserveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openReserveSeatWindow();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openCancelWindow();
            }
        });
        showPassengersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openShowPassengersWindow();
            }
        });
        showReservationsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openShowReservationsWindow();
            }
        });
        showTrainsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openShowTrainsWindow();
            }
        });
    }

    // ================= 1. ADD PASSENGER WINDOW =================
    private void openAddPassengerWindow() {
        JPanel form = new JPanel(new GridLayout(0, 2, 6, 6));
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();
        addRow(form, "Passenger ID", idField);
        addRow(form, "Passenger Name", nameField);
        addRow(form, "Age", ageField);

        JTextArea result = new JTextArea(4, 30);
        result.setEditable(false);

        JButton addButton = new JButton("Add Passenger");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id = getNumber(idField);
                int age = getNumber(ageField);
                String name = nameField.getText();
                if (id == -1 || age == -1 || name.equals("")) {
                    result.setText("Please fill Passenger ID, Name and Age.");
                    return;
                }
                system.addPassenger(id, name, age);          // call the backend
                result.setText("Passenger added: " + name);
            }
        });

        showWindow("Add Passenger", form, result, addButton);
    }

    // ================= 2. SEARCH TRAIN WINDOW =================
    private void openSearchTrainWindow() {
        JPanel form = new JPanel(new GridLayout(0, 2, 6, 6));
        JTextField trainField = new JTextField();
        addRow(form, "Train ID", trainField);

        JTextArea result = new JTextArea(6, 30);
        result.setEditable(false);

        JButton searchButton = new JButton("Search Train");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int trainId = getNumber(trainField);
                if (trainId == -1) {
                    result.setText("Please enter a Train ID.");
                    return;
                }
                Train t = system.searchTrain(trainId);       // call the backend
                if (t == null) {
                    result.setText("Train not found!");
                } else {
                    result.setText("Train found:\n"
                            + t.trainName + " : " + t.source + " -> " + t.destination
                            + "\nTotal seats: " + t.totalSeats);
                }
            }
        });

        showWindow("Search Train", form, result, searchButton);
    }

    // ================= 3. RESERVE SEAT WINDOW =================
    private void openReserveSeatWindow() {
        JPanel form = new JPanel(new GridLayout(0, 2, 6, 6));
        JTextField passField = new JTextField();
        JTextField trainField = new JTextField();
        JTextField seatField = new JTextField();
        addRow(form, "Passenger ID", passField);
        addRow(form, "Train ID", trainField);
        addRow(form, "Seat Number", seatField);

        JTextArea result = new JTextArea(6, 30);
        result.setEditable(false);

        JButton reserveButton = new JButton("Reserve Seat");
        reserveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int passId = getNumber(passField);
                int trainId = getNumber(trainField);
                int seat = getNumber(seatField);
                if (passId == -1 || trainId == -1 || seat == -1) {
                    result.setText("Please enter Passenger ID, Train ID and Seat Number.");
                    return;
                }
                // Backend checks everything and returns the message
                result.setText(system.makeReservation(passId, trainId, seat));
            }
        });

        showWindow("Reserve Seat", form, result, reserveButton);
    }

    // ================= 4. CANCEL RESERVATION WINDOW =================
    private void openCancelWindow() {
        JPanel form = new JPanel(new GridLayout(0, 2, 6, 6));
        JTextField resField = new JTextField();
        addRow(form, "Reservation ID", resField);

        JTextArea result = new JTextArea(6, 30);
        result.setEditable(false);

        JButton cancelButton = new JButton("Cancel Reservation");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int resId = getNumber(resField);
                if (resId == -1) {
                    result.setText("Please enter a Reservation ID.");
                    return;
                }
                // Backend searches, frees the seat and returns the message
                result.setText(system.cancelReservation(resId));
            }
        });

        showWindow("Cancel Reservation", form, result, cancelButton);
    }

    // ================= 5. SHOW PASSENGERS WINDOW =================
    private void openShowPassengersWindow() {
        JTextArea area = new JTextArea(14, 46);
        area.setEditable(false);
        area.setText(passengersText());
        makeWindow("Passengers", area, 500, 340);
    }

    // ================= 6. SHOW RESERVATIONS WINDOW =================
    private void openShowReservationsWindow() {
        JTextArea area = new JTextArea(14, 46);
        area.setEditable(false);
        area.setText(reservationsText());
        makeWindow("Reservations", area, 500, 340);
    }

    // ================= 7. SHOW TRAINS WINDOW =================
    private void openShowTrainsWindow() {
        JTextArea area = new JTextArea(14, 46);
        area.setEditable(false);
        area.setText(trainsText());
        makeWindow("Trains (sorted by ID)", area, 560, 340);
    }

    // ================= SMALL HELPERS =================

    // Create and show a new window with the given content inside
    private void makeWindow(String title, Container content, int width, int height) {
        JFrame window = new JFrame(title);
        window.setSize(width, height);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.add(content);
        window.setLocationRelativeTo(null);   // center on screen
        window.setVisible(true);
    }

    // Put a form window together:
    // inputs on top, result in the middle, button at the bottom
    private void showWindow(String title, JPanel form, JTextArea result, JButton button) {
        JPanel buttons = new JPanel(new FlowLayout());
        buttons.add(button);

        JPanel content = new JPanel(new BorderLayout(8, 8));
        content.add(form, BorderLayout.NORTH);
        content.add(result, BorderLayout.CENTER);
        content.add(buttons, BorderLayout.SOUTH);

        makeWindow(title, content, 400, 330);
    }

    // Add one label + text field row to a form
    private void addRow(JPanel form, String label, JTextField field) {
        form.add(new JLabel("  " + label));
        form.add(field);
    }

    // Read a number from a text field. Returns -1 if empty or wrong.
    private int getNumber(JTextField field) {
        try {
            return Integer.parseInt(field.getText());
        } catch (NumberFormatException ex) {
            return -1;
        }
    }

    // Format the passenger list as one block of text
    private String passengersText() {
        String text = "--- Passengers ---\n";
        for (Passenger p : system.passengers) {
            text = text + "ID: " + p.passengerId + " | Name: " + p.name
                    + " | Age: " + p.age + "\n";
        }
        return text;
    }

    // Format the reservation list as one block of text
    private String reservationsText() {
        String text = "--- Reservations ---\n";
        if (system.reservations.size() == 0) {
            text = text + "(no reservations yet)\n";
        }
        for (Reservation r : system.reservations) {
            text = text + "Res ID: " + r.reservationId + " | Passenger: " + r.passengerId
                    + " | Train: " + r.trainId + " | Seat: " + r.seatNumber + "\n";
        }
        return text;
    }

    // Format the trains (sorted by ID) as one block of text
    private String trainsText() {
        String text = "--- Trains (sorted by ID) ---\n";
        for (int id : system.sortedTrains.keySet()) {   // TreeMap keys come sorted
            Train t = system.sortedTrains.get(id);
            text = text + id + " | " + t.trainName + " | " + t.source
                    + " -> " + t.destination + " | Seats: " + t.totalSeats + "\n";
        }
        return text;
    }
}
