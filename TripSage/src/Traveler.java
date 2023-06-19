import java.util.*;

interface Traveler {
    void signUp(String username, String email, String password);
    boolean logIn(String username, String password);
    void makeBooking();
    void modifyBooking();
}

class TravelerImpl implements Traveler {
    public Map<String, User> users;
    public Map<String, Booking> bookings;
    public Map<String, List<Booking>> reservations = new HashMap<>();
    DataBase database = new DataBase();
    public String currentUserName;

    public TravelerImpl() {
        users = new HashMap<>();
        bookings = new HashMap<>();
    }

    @Override
    public void signUp(String username, String email, String password) {
        User newUser = new User(username, email, password);
        users.put(email, newUser);
        System.out.println("User " + username + " has signed up successfully.\n");
    }

    @Override
    public boolean logIn(String email, String password) {
        if(users.isEmpty()) {
            System.out.println("User Doesn't exits\nPlease Create an account first");
        }else {
            User user = users.get(email);
            currentUserName = user.getUsername();

            if(users.containsKey(user.getPassword())) {
                if(user.getEmail().equals(password)) {
                    System.out.println("User " + currentUserName + " has logged in successfully.\n");
                    return true;
                }else {
                    System.out.println("Wrong Password ! Try Again...\n");
                }
            }else {
                System.out.println("Invalid credentials. Login failed.\n");
            }
        }
        return false;
    }

    public String selectFlight() {
        List<String> flights = database.getAvailableFlights();
        Random random = new Random();
        int randomIndex = random.nextInt(flights.size());
        return flights.get(randomIndex);
    }

    public String chooseAccommodation() {
        List<String> accommodations = database.getAvailableAccommodations();
        Random random = new Random();
        int randomIndex = random.nextInt(accommodations.size());
        return accommodations.get(randomIndex);
    }

    public String bookTransportation() {
        List<String> transportationOptions = database.getAvailableTransportationOptions();
        Random random = new Random();
        int randomIndex = random.nextInt(transportationOptions.size());
        return transportationOptions.get(randomIndex);
    }

    public String selectDestination() {
        List<String> destinations = database.getAvailableDestinations();
        Random random = new Random();
        int randomIndex = random.nextInt(destinations.size());
        return destinations.get(randomIndex);
    }

    @Override
    public void makeBooking() {
        try {
            // Retrieve the list of traveler reservations or create a new one if it doesn't exist
            List<Booking> travelerReservations = reservations.get(currentUserName);
            if (travelerReservations == null) {
                travelerReservations = new ArrayList<>();
                reservations.put(currentUserName, travelerReservations);
            }

            String selectedDestination = selectDestination();
            String selectedFlight = selectFlight();
            String selectedAccommodation = chooseAccommodation();
            String selectedTransport = bookTransportation();

            // Create a new booking
            Booking newBooking = new Booking(selectedDestination, selectedFlight, selectedAccommodation, selectedTransport);

            // Add the booking to the list of reservations
            travelerReservations.add(newBooking);

            System.out.println("Booking made for traveler: " + currentUserName);
        } catch (Exception e) {
            System.out.println("Error occurred while making a booking: " + e.getMessage());
        }
    }


    public void modifyBooking() {
        try {
            Scanner scanner = new Scanner(System.in);
            List<Booking> travelerReservations = reservations.get(currentUserName);

            if (travelerReservations == null || travelerReservations.isEmpty()) {
                System.out.println("Booking not found!");
                return;
            }

            // Prompt the user for the new details
            System.out.print("Enter the new destination: ");
            String newDestination = scanner.nextLine();
            System.out.print("Enter the new flight: ");
            String newFlight = scanner.nextLine();
            System.out.print("Enter the new accommodation: ");
            String newAccommodation = scanner.nextLine();
            System.out.print("Enter the new transport: ");
            String newTransport = scanner.nextLine();

            // Modify each booking in the list for the current user
            for (Booking booking : travelerReservations) {
                booking.setDestination(newDestination);
                booking.setFlight(newFlight);
                booking.setAccommodation(newAccommodation);
                booking.setTransport(newTransport);
            }

            System.out.println("Booking modified successfully!\n");
        } catch (Exception e) {
            System.out.println("Error occurred while modifying the booking: " + e.getMessage());
        }
    }


    public void cancelBooking(User traveler, Booking booking) {
        try {
            // Retrieve the list of traveler reservations
            List<Booking> travelerReservations = reservations.get(traveler.getUsername());

            if (travelerReservations != null && !travelerReservations.isEmpty()) {
                // Remove the booking from the list of reservations
                if (travelerReservations.remove(booking)) {
                    // Update the reservations map
                    reservations.put(traveler.getUsername(), travelerReservations);

                    System.out.println("Booking canceled for traveler: " + traveler.getUsername());
                } else {
                    System.out.println("Booking not found!");
                }
            } else {
                System.out.println("No bookings found for traveler: " + traveler.getUsername());
            }
        } catch (Exception e) {
            System.out.println("Error occurred while canceling the booking: " + e.getMessage());
        }
    }


    public void printAllBookings() {
        try {
            // Retrieve all map entries
            Set<Map.Entry<String, List<Booking>>> entries = reservations.entrySet();

            // Iterate over the entries
            for (Map.Entry<String, List<Booking>> entry : entries) {
                String traveler = entry.getKey();
                List<Booking> bookings = entry.getValue();

                // Print traveler's name
                System.out.println("Traveler: " + traveler);

                // Iterate over the bookings list for each traveler
                for (Booking booking : bookings) {
                    System.out.println("\nDestination: " + booking.getDestination());
                    System.out.println("Flight: " + booking.getFlight());
                    System.out.println("Accommodation: " + booking.getAccommodation());
                    System.out.println("Transport: " + booking.getTransport());
                    System.out.println("------------------------\n");
                }
            }
        } catch (Exception e) {
            System.out.println("Error occurred while printing bookings: " + e.getMessage());
        }
    }

}