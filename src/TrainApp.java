import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Comparator;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.function.Predicate;

public class TrainApp {

    // Represents a passenger bogie with a class name and seating capacity.
    static class Bogie {
        private final String name;
        private final int capacity;

        Bogie(String name, int capacity) {
            this.name = name;
            this.capacity = capacity;
        }

        public String getName() {
            return name;
        }

        public int getCapacity() {
            return capacity;
        }

        @Override
        public String toString() {
            return "Bogie: " + name + " | Capacity: " + capacity;
        }
    }

    // Represents a goods bogie with bogie type and cargo type.
    static class GoodsBogie {
        private final String type;
        private final String cargo;

        GoodsBogie(String type, String cargo) {
            this.type = type;
            this.cargo = cargo;
        }

        public String getType() {
            return type;
        }

        public String getCargo() {
            return cargo;
        }

        @Override
        public String toString() {
            return "GoodsBogie: " + type + " | Cargo: " + cargo;
        }
    }

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
        formation.add("Engine");
        formation.add("Sleeper");
        formation.add("Cargo");
        formation.add("Guard");
        formation.add("Sleeper"); // duplicate ignored
        System.out.println("Final train formation (in insertion order, no duplicates): " + formation);
        System.out.println("\nTrain consist operations complete. Ready for next use case.\n");

        // UC6: Map Bogie to Capacity using HashMap
        System.out.println("=== UC6: Bogie Capacity Mapping (HashMap) ===");
        HashMap<String, Integer> bogieCapacity = new HashMap<>();

        // Insert bogie-capacity pairs
        bogieCapacity.put("Sleeper", 72);
        bogieCapacity.put("AC Chair", 56);
        bogieCapacity.put("First Class", 24);

        // Iterate and display bogie capacities
        for (Map.Entry<String, Integer> entry : bogieCapacity.entrySet()) {
            System.out.println("Bogie: " + entry.getKey() + " | Capacity: " + entry.getValue());
        }

        System.out.println("\nTrain consist operations complete. Ready for next use case.\n");

        // UC7: Sort Bogies by Capacity using Comparator
        System.out.println("=== UC7: Sort Bogies by Capacity (Comparator) ===");
        List<Bogie> passengerBogieObjects = new ArrayList<>();
        passengerBogieObjects.add(new Bogie("Sleeper", 72));
        passengerBogieObjects.add(new Bogie("AC Chair", 56));
        passengerBogieObjects.add(new Bogie("First Class", 24));

        // Sort from highest capacity to lowest for planning and reporting.
        passengerBogieObjects.sort(Comparator.comparingInt(Bogie::getCapacity).reversed());

        System.out.println("Passenger bogies sorted by capacity:");
        for (Bogie bogie : passengerBogieObjects) {
            System.out.println(bogie);
        }

        System.out.println("\nTrain consist operations complete. Ready for next use case.\n");

        // UC8: Filter Bogies by Capacity using Stream API
        System.out.println("=== UC8: Filter Passenger Bogies by Capacity (Stream API) ===");
        List<Bogie> filteredBogies = passengerBogieObjects.stream()
                .filter(b -> b.getCapacity() > 60)
                .collect(Collectors.toList());

        System.out.println("Passenger bogies with capacity greater than 60:");
        for (Bogie bogie : filteredBogies) {
            System.out.println(bogie);
        }

        System.out.println("\nTrain consist operations complete. Ready for next use case.\n");

        // UC9: Group Bogies by Category using Stream Collectors
        System.out.println("=== UC9: Group Bogies into Capacity Categories (Stream Collectors) ===");
        Map<String, List<Bogie>> groupedBogies = passengerBogieObjects.stream()
                .collect(Collectors.groupingBy(b -> b.getCapacity() > 60 ? "High Capacity" : "Standard Capacity"));

        System.out.println("Grouped bogies:");
        for (Map.Entry<String, List<Bogie>> entry : groupedBogies.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        System.out.println("\nTrain consist operations complete. Ready for next use case.\n");

        // UC10: Aggregate Seating Capacity using Stream Reduction
        System.out.println("=== UC10: Total Seating Capacity (Stream Reduction) ===");
        int totalSeatingCapacity = passengerBogieObjects.stream()
                .map(b -> b.getCapacity())
                .reduce(0, Integer::sum);

        System.out.println("Total seating capacity across passenger bogies: " + totalSeatingCapacity);

        System.out.println("\nTrain consist operations complete. Ready for next use case.\n");

        // UC11: Validate Train ID and Cargo Code using Regular Expressions
        System.out.println("=== UC11: Validate Train ID and Cargo Code (Regex) ===");
        String trainIdInput = "TRN-1234";
        String cargoCodeInput = "PET-AB";

        Pattern trainIdPattern = Pattern.compile("TRN-\\d{4}");
        Pattern cargoCodePattern = Pattern.compile("PET-[A-Z]{2}");

        Matcher trainIdMatcher = trainIdPattern.matcher(trainIdInput);
        Matcher cargoCodeMatcher = cargoCodePattern.matcher(cargoCodeInput);

        if (trainIdMatcher.matches()) {
            System.out.println("Train ID is valid: " + trainIdInput);
        } else {
            System.out.println("Train ID is invalid: " + trainIdInput);
        }

        if (cargoCodeMatcher.matches()) {
            System.out.println("Cargo Code is valid: " + cargoCodeInput);
        } else {
            System.out.println("Cargo Code is invalid: " + cargoCodeInput);
        }

        System.out.println("\nTrain consist operations complete. Ready for next use case.\n");

        // UC12: Enforce Goods Bogie Safety Rules using Functional Interfaces
        System.out.println("=== UC12: Goods Bogie Safety Validation (Functional Interfaces) ===");
        List<GoodsBogie> goodsBogies = new ArrayList<>();
        goodsBogies.add(new GoodsBogie("Rectangular", "Coal"));
        goodsBogies.add(new GoodsBogie("Cylindrical", "Petroleum"));
        goodsBogies.add(new GoodsBogie("Rectangular", "Cement"));

        Predicate<GoodsBogie> hasRequiredFields = g ->
                g.getType() != null && !g.getType().isEmpty()
                        && g.getCargo() != null && !g.getCargo().isEmpty();

        Predicate<GoodsBogie> cylindricalPetroleumOnly = g ->
                !g.getType().equalsIgnoreCase("Cylindrical")
                        || g.getCargo().equalsIgnoreCase("Petroleum");

        boolean isSafetyCompliant = goodsBogies.stream()
                .allMatch(hasRequiredFields.and(cylindricalPetroleumOnly));

        System.out.println("Goods bogies under validation: " + goodsBogies);
        if (isSafetyCompliant) {
            System.out.println("Train safety compliance status: SAFE");
        } else {
            System.out.println("Train safety compliance status: UNSAFE");
        }

        System.out.println("\nAll use cases executed successfully.");
    }
}