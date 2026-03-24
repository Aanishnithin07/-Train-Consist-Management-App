import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
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
        List<String> passengerBogies = new ArrayList<>();
        passengerBogies.add("Sleeper");
        passengerBogies.add("AC Chair");
        passengerBogies.add("First Class");
        System.out.println("Passenger bogies after insertion: " + passengerBogies);
        passengerBogies.remove("AC Chair");
        System.out.println("Passenger bogies after removing AC Chair: " + passengerBogies);
        if (passengerBogies.contains("Sleeper")) {
            System.out.println("Sleeper bogie exists in the train consist.");
        }
        System.out.println("Final passenger bogie list: " + passengerBogies);
        System.out.println("\nTrain consist operations complete. Ready for next use case.\n");

        // UC3: Unique Bogie ID Management
        System.out.println("=== UC3: Unique Bogie ID Operations ===");
        Set<String> bogieIDs = new HashSet<>();
        bogieIDs.add("BG101");
        bogieIDs.add("BG102");
        bogieIDs.add("BG103");
        bogieIDs.add("BG101"); // duplicate
        bogieIDs.add("BG102"); // duplicate
        System.out.println("Unique bogie IDs in the train consist: " + bogieIDs);
        System.out.println("\nTrain consist operations complete. Ready for next use case.\n");

        // UC4: Ordered Train Consist using LinkedList
        System.out.println("=== UC4: Ordered Train Consist (LinkedList) ===");
        LinkedList<String> orderedConsist = new LinkedList<>();
        orderedConsist.add("Engine");
        orderedConsist.add("Sleeper");
        orderedConsist.add("AC");
        orderedConsist.add("Cargo");
        orderedConsist.add("Guard");
        System.out.println("Train consist after initial addition: " + orderedConsist);
        orderedConsist.add(2, "Pantry Car");
        System.out.println("Train consist after inserting Pantry Car at position 2: " + orderedConsist);
        orderedConsist.removeFirst();
        orderedConsist.removeLast();
        System.out.println("Train consist after removing first and last bogie: " + orderedConsist);
        System.out.println("Final ordered train consist: " + orderedConsist);
        System.out.println("\nTrain consist operations complete. Ready for next use case.\n");

        // UC5: Preserve Insertion Order with Uniqueness using LinkedHashSet
        System.out.println("=== UC5: Preserve Insertion Order (LinkedHashSet) ===");
        LinkedHashSet<String> formation = new LinkedHashSet<>();

        // Attach bogies
        formation.add("Engine");
        formation.add("Sleeper");
        formation.add("Cargo");
        formation.add("Guard");

        // Attempt to attach duplicate bogie
        formation.add("Sleeper"); // duplicate ignored

        // Display final formation
        System.out.println("Final train formation (in insertion order, no duplicates): " + formation);

        System.out.println("\nTrain consist operations complete. Ready for next use case.");
    }
}