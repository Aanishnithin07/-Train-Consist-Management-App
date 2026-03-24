import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TrainApp {

    // Entry point of the application
    public static void main(String[] args) {

        // UC1: Initialization
        System.out.println("=== Train Consist Management App ===");
        List<String> trainConsist = new ArrayList<>();
        System.out.println("Initial bogie count: " + trainConsist.size());
        System.out.println("Train initialization complete. Ready for further operations.\n");

        // UC2: Passenger Bogie Management
        System.out.println("=== UC2: Passenger Bogie Operations ===");

        // Create ArrayList for passenger bogies
        List<String> passengerBogies = new ArrayList<>();

        // Add bogies
        passengerBogies.add("Sleeper");
        passengerBogies.add("AC Chair");
        passengerBogies.add("First Class");

        // Display bogies after insertion
        System.out.println("Passenger bogies after insertion: " + passengerBogies);

        // Remove one bogie (AC Chair)
        passengerBogies.remove("AC Chair");
        System.out.println("Passenger bogies after removing AC Chair: " + passengerBogies);

        // Check existence of Sleeper
        if (passengerBogies.contains("Sleeper")) {
            System.out.println("Sleeper bogie exists in the train consist.");
        } else {
            System.out.println("Sleeper bogie does not exist in the train consist.");
        }

        // Final list state
        System.out.println("Final passenger bogie list: " + passengerBogies);
        System.out.println("\nTrain consist operations complete. Ready for next use case.\n");

        // UC3: Unique Bogie ID Management
        System.out.println("=== UC3: Unique Bogie ID Operations ===");

        // Create a HashSet for bogie IDs
        Set<String> bogieIDs = new HashSet<>();

        // Add bogie IDs (with duplicates intentionally)
        bogieIDs.add("BG101");
        bogieIDs.add("BG102");
        bogieIDs.add("BG103");
        bogieIDs.add("BG101"); // duplicate
        bogieIDs.add("BG102"); // duplicate

        // Display unique bogie IDs
        System.out.println("Unique bogie IDs in the train consist: " + bogieIDs);

        // Program continues...
        System.out.println("\nTrain consist operations complete. Ready for next use case.");
    }
}