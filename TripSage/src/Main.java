import java.util.*;

public class Main extends AdministratorImpl {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TravelerImpl traveler = new TravelerImpl();
        DataBase dataBase = new DataBase();
        Map<String, User> user = new HashMap<>();
        AdministratorImpl administrator = new AdministratorImpl();
        List<User> registeredUsers = administrator.getRegisteredUsers();
        String currentUsername = traveler.currentUserName;

        boolean flag = true;
        while(flag) {
            System.out.println("Press\n1 To Login As Admin\n2 To Login As Traveler\n3 To Exit");
            switch(scanner.nextInt()) {
                case 1: {
                    boolean loggedIn = false;

                    while (!loggedIn) {
                        System.out.print("Username: ");
                        String username = scanner.next();

                        System.out.print("Password: ");
                        String password = scanner.next();

                        loggedIn = administrator.login(username, password);

                    }

                    boolean exitAdmin = true;

                    while (exitAdmin) {
                        System.out.println("\nWhat would you like to do?");
                        System.out.println("1. Approve Registration");
                        System.out.println("2. Handle Account Issue");
                        System.out.println("3. Monitor Statistics");
                        System.out.println("4. Manage Access Permissions");
                        System.out.println("5. Moderate Content");
                        System.out.println("6. Update Travel Information");
                        System.out.println("7. Exit\n");

                        System.out.print("Enter your choice: ");

                        switch (scanner.nextInt()) {
                            case 1 -> {
                                // Approve Registration
                                // Prompt for user details and call the approveRegistration() method
                                System.out.println("Enter Username");
                                String username = scanner.nextLine();
                                scanner.nextLine();
                                System.out.println("Enter email");
                                String email = scanner.next();
                                System.out.println("Enter the password");
                                String password = scanner.next();
                                administrator.approveRegistration(new User(username, email, password));
                            }
                            case 2 -> {
                                // Handle Account Issue
                                // Prompt for user details and call the handleAccountIssue() method
                                System.out.println("Enter Email of the user");
                                String em = scanner.next();
                                administrator.handleAccountIssue(getCurrentUser(registeredUsers, currentUsername));
                            }
                            case 3 ->
                                // Monitor Statistics
                                    administrator.monitorStatistics();
                            case 4 ->
                                // Manage Access Permissions
                                    administrator.manageAccessPermissions();
                            case 5 ->
                                // Moderate Content
                                    administrator.moderateContent();
                            case 6 ->
                                // Update Travel Information
                                    administrator.updateTravelInformation();
                            case 7 ->
                                // Exit the program
                                    exitAdmin = false;
                            default -> System.out.println("Invalid choice. Please try again.");
                        }
                    }

                    System.out.println();
                    break;
                }
                case 2: {
                    printMessage();
                    boolean travelerFlag = true;
                    while(travelerFlag) {
                        switch (scanner.nextInt()) {
                            case 1 -> {
                                System.out.println("Enter Your Username");
                                String username = scanner.next();
                                System.out.println("Create A Password");
                                String password = scanner.next();
                                System.out.println("Enter Your Email");
                                String email = scanner.next();
                                if (user.containsKey(email)) {
                                    System.out.println("Account Already Exists With This Email");
                                } else {
                                    traveler.signUp(username, email, password);
                                }
                                printMessage();
                            }
                            case 2 -> {
                                System.out.println("Enter Your Email");
                                String email = scanner.next();
                                System.out.println("Enter Password");
                                String password = scanner.next();
                                traveler.logIn(email, password);
                                printMessage();
                            }
                            case 3 -> {
                                System.out.println("Available Services Are:");
                                printList(dataBase.getAvailableDestinations());
                                printList(dataBase.getAvailableFlights());
                                printList(dataBase.getAvailableAccommodations());
                                printList(dataBase.getAvailableTransportationOptions());
                                System.out.println("Enter 1 To Confirm");
                                if (scanner.nextInt() == 1) {
                                    traveler.makeBooking();
                                }
                                printMessage();
                            }
                            case 4 -> {
                                traveler.modifyBooking();
                                printMessage();
                            }
                            case 5 -> {
                                if (traveler.currentUserName == null) {
                                    System.out.println("Please log in first!");
                                } else {
                                    System.out.print("Enter the destination to cancel the booking: ");
                                    String destination = scanner.nextLine();
                                    List<Booking> travelerReservations = traveler.reservations.get(traveler.currentUserName);
                                    if (travelerReservations != null) {
                                        Booking bookingToRemove = null;
                                        for (Booking booking : travelerReservations) {
                                            if (booking.getDestination().equals(destination)) {
                                                bookingToRemove = booking;
                                                break;
                                            }
                                        }
                                        if (bookingToRemove != null) {
                                            traveler.cancelBooking(traveler.users.get(traveler.currentUserName), bookingToRemove);
                                        } else {
                                            System.out.println("No bookings found for the specified destination.");
                                        }
                                    } else {
                                        System.out.println("No bookings found for traveler: " + traveler.currentUserName);
                                    }
                                }
                                printMessage();
                            }
                            case 6 -> {
                                traveler.printAllBookings();
                                printMessage();
                            }
                            case 7 -> travelerFlag = false;
                            default -> System.out.println("Invalid Option");
                        }
                    }
                }
                case 3 : {
                    System.out.println("Successfully Logged Out");
                    flag = false;
                    break;
                }
                default: {
                    System.out.println("Invalid Option");
                }
            }
        }
    }
    public static void printList(List<String> list) {
        for(String item : list) {
            System.out.println(item);
        }
    }

    public static void printMap(Map<String, User> map) {
        if(!map.isEmpty()) {
            for (Map.Entry<String, User> entry : map.entrySet()) {
                System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
            }
        }else {
            System.out.println("Map is empty");
        }
    }

    public static void printMessage() {
        System.out.println("\nEnter\n1 For New Registration\n2 To Login");
        System.out.println("3 To Book A Tour\n4 To Modify Your Bookings");
        System.out.println("5 To Cancel Any Booking\n6 To View All Bookings");
        System.out.println("7 To Go Back");
    }
    public static User getCurrentUser(List<User> registeredUsers, String currentUsername) {
        for (User user : registeredUsers) {
            if (user.getUsername().equals(currentUsername)) {
                return user;
            }
        }
        return null;  // Return null if the current user is not found in the list
    }

}
