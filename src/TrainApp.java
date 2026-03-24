import java.util.ArrayList;
import java.util.List;

public class TrainApp {

    // Entry point of the application
    public static void main(String[] args) {

        // Print welcome message
        System.out.println("=== Train Consist Management App ===");

        // Initialize train consist using dynamic collection
        List<String> trainConsist = new ArrayList<>();

        // Display initial bogie count
        System.out.println("Initial bogie count: " + trainConsist.size());

        // Program continues...
        System.out.println("Train initialization complete. Ready for further operations.");
    }
}