import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class DataBase {
    public List<String> getAvailableFlights() {
        // Simulated method to get available flights from the database
        return new ArrayList<>(Arrays.asList(
                "United Airlines Flight 123",
                "Delta Air Lines Flight 456",
                "American Airlines Flight 789",
                "British Airways Flight 101",
                "Lufthansa Flight 202"));
    }

    public List<String> getAvailableAccommodations() {
        // Simulated method to get available accommodations from the database
        return new ArrayList<>(Arrays.asList(
                "Hotel",
                "Apartment",
                "Resort",
                "Hostel",
                "Bed and Breakfast"));

    }

    public List<String> getAvailableTransportationOptions() {
        // Simulated method to get available transportation options from the database
        return new ArrayList<>(Arrays.asList(
                "Airplane",
                "Train",
                "Bus",
                "Car",
                "Cruise Ship"));
    }

    public List<String> getAvailableDestinations() {
        // Simulated method to get available destinations from the database
        return new ArrayList<>(Arrays.asList(
                "Paris, France",
                "Tokyo, Japan",
                "Cairo, Egypt",
                "Sydney, Australia"));
    }
}