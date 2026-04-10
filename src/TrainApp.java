import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.function.Predicate;

public class TrainApp {

    // Custom checked exception for invalid passenger bogie capacities.
    static class InvalidCapacityException extends Exception {
        InvalidCapacityException(String message) {
            super(message);
        }
    }

    // Custom runtime exception for unsafe cargo assignment operations.
    static class CargoSafetyException extends RuntimeException {
        CargoSafetyException(String message) {
            super(message);
        }
    }

    // Represents a passenger bogie with a class name and seating capacity.
    static class Bogie {
        private final String name;
        private final int capacity;

        Bogie(String name, int capacity) throws InvalidCapacityException {
            if (capacity <= 0) {
                throw new InvalidCapacityException(
                        "Capacity must be greater than zero for bogie " + name + ": " + capacity);
            }
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
        private String cargo;

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

        public void setCargo(String cargo) {
            this.cargo = cargo;
        }

        @Override
        public String toString() {
            return "GoodsBogie: " + type + " | Cargo: " + cargo;
        }
    }

    static void assignCargoWithSafetyCheck(GoodsBogie goodsBogie, String cargoToAssign) {
        try {
            if (goodsBogie.getType().equalsIgnoreCase("Rectangular")
                    && cargoToAssign.equalsIgnoreCase("Petroleum")) {
                throw new CargoSafetyException("Petroleum cannot be assigned to a Rectangular bogie.");
            }

            goodsBogie.setCargo(cargoToAssign);
            System.out.println("Cargo assignment successful: " + goodsBogie);
        } catch (CargoSafetyException e) {
            System.out.println("Cargo assignment failed: " + e.getMessage());
        } finally {
            System.out.println("Cargo assignment completed for bogie type: " + goodsBogie.getType());
        }
    }

    static boolean searchBogieIdDefensively(List<String> bogieIds, String searchKey) {
        if (bogieIds.isEmpty()) {
            throw new IllegalStateException("Search cannot continue: no bogies available in the train consist.");
        }

        for (String bogieId : bogieIds) {
            if (bogieId.equals(searchKey)) {
                return true;
            }
        }
        return false;
    }

    // Entry point of the application
    public static void main(String[] args) throws InvalidCapacityException {

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

        System.out.println("\nTrain consist operations complete. Ready for next use case.\n");

        // UC13: Compare Loop and Stream Performance using nanoTime
        System.out.println("=== UC13: Loop vs Stream Performance Benchmark ===");
        List<Bogie> benchmarkBogies = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            benchmarkBogies.add(new Bogie("Sleeper", 72));
            benchmarkBogies.add(new Bogie("AC Chair", 56));
            benchmarkBogies.add(new Bogie("First Class", 24));
        }

        long loopStartTime = System.nanoTime();
        List<Bogie> loopFilteredBogies = new ArrayList<>();
        for (Bogie bogie : benchmarkBogies) {
            if (bogie.getCapacity() > 60) {
                loopFilteredBogies.add(bogie);
            }
        }
        long loopEndTime = System.nanoTime();
        long loopElapsedTime = loopEndTime - loopStartTime;

        long streamStartTime = System.nanoTime();
        List<Bogie> streamFilteredBogies = benchmarkBogies.stream()
                .filter(b -> b.getCapacity() > 60)
                .collect(Collectors.toList());
        long streamEndTime = System.nanoTime();
        long streamElapsedTime = streamEndTime - streamStartTime;

        System.out.println("Loop-based filtering time (ns): " + loopElapsedTime);
        System.out.println("Stream-based filtering time (ns): " + streamElapsedTime);
        System.out.println("Loop filtered bogies count: " + loopFilteredBogies.size());
        System.out.println("Stream filtered bogies count: " + streamFilteredBogies.size());

        System.out.println("\nTrain consist operations complete. Ready for next use case.\n");

        // UC14: Prevent invalid passenger bogies using custom exception
        System.out.println("=== UC14: Prevent Invalid Passenger Bogies (Custom Exception) ===");
        List<Bogie> validatedPassengerBogies = new ArrayList<>();

        try {
            Bogie validBogie = new Bogie("Sleeper", 72);
            validatedPassengerBogies.add(validBogie);
            System.out.println("Passenger bogie created successfully: " + validBogie);
        } catch (InvalidCapacityException e) {
            System.out.println("Validation failed: " + e.getMessage());
        }

        try {
            Bogie invalidBogie = new Bogie("Faulty Coach", 0);
            validatedPassengerBogies.add(invalidBogie);
            System.out.println("Passenger bogie created successfully: " + invalidBogie);
        } catch (InvalidCapacityException e) {
            System.out.println("Validation failed: " + e.getMessage());
        }

        System.out.println("Passenger bogies added after validation: " + validatedPassengerBogies);

        System.out.println("\nTrain consist operations complete. Ready for next use case.\n");

        // UC15: Safely handle unsafe cargo assignments using try-catch-finally
        System.out.println("=== UC15: Handle Unsafe Cargo Assignment (try-catch-finally) ===");
        GoodsBogie assignmentBogie = new GoodsBogie("Rectangular", "Coal");
        System.out.println("Initial goods bogie: " + assignmentBogie);

        assignCargoWithSafetyCheck(assignmentBogie, "Petroleum");
        assignCargoWithSafetyCheck(assignmentBogie, "Grain");

        System.out.println("Final goods bogie state: " + assignmentBogie);
        System.out.println("Application continues safely after cargo assignment attempts.");

        System.out.println("\nTrain consist operations complete. Ready for next use case.\n");

        // UC16: Sort passenger capacities using Bubble Sort (no library sort)
        System.out.println("=== UC16: Bubble Sort Passenger Capacities ===");
        int[] passengerCapacities = {72, 56, 24, 68, 40};

        for (int i = 0; i < passengerCapacities.length - 1; i++) {
            for (int j = 0; j < passengerCapacities.length - 1 - i; j++) {
                if (passengerCapacities[j] > passengerCapacities[j + 1]) {
                    int temp = passengerCapacities[j];
                    passengerCapacities[j] = passengerCapacities[j + 1];
                    passengerCapacities[j + 1] = temp;
                }
            }
        }

        System.out.print("Sorted passenger capacities: ");
        for (int capacity : passengerCapacities) {
            System.out.print(capacity + " ");
        }
        System.out.println();

        System.out.println("\nTrain consist operations complete. Ready for next use case.\n");

        // UC17: Sort bogie type names alphabetically using Arrays.sort()
        System.out.println("=== UC17: Sort Bogie Type Names with Arrays.sort() ===");
        String[] bogieTypeNames = {"Sleeper", "First Class", "AC Chair", "Pantry", "Cargo"};

        Arrays.sort(bogieTypeNames);

        System.out.println("Sorted bogie type names: " + Arrays.toString(bogieTypeNames));

        System.out.println("\nTrain consist operations complete. Ready for next use case.\n");

        // UC18: Search bogie ID using Linear Search on unsorted data
        System.out.println("=== UC18: Linear Search for Bogie ID ===");
        String[] bogieIds = {"BG305", "BG101", "BG208", "BG450", "BG102"};
        String searchKey = "BG208";
        boolean bogieFound = false;
        int foundAtIndex = -1;

        for (int i = 0; i < bogieIds.length; i++) {
            if (bogieIds[i].equals(searchKey)) {
                bogieFound = true;
                foundAtIndex = i;
                break;
            }
        }

        if (bogieFound) {
            System.out.println("Bogie ID found: " + searchKey + " at index " + foundAtIndex);
        } else {
            System.out.println("Bogie ID not found: " + searchKey);
        }

        System.out.println("\nTrain consist operations complete. Ready for next use case.\n");

        // UC19: Find bogie ID efficiently using Binary Search on sorted data
        System.out.println("=== UC19: Binary Search for Bogie ID ===");
        String[] sortedBogieIds = {"BG101", "BG102", "BG208", "BG305", "BG450"};
        String binarySearchKey = "BG305";
        int low = 0;
        int high = sortedBogieIds.length - 1;
        int binaryFoundIndex = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparison = binarySearchKey.compareTo(sortedBogieIds[mid]);

            if (comparison == 0) {
                binaryFoundIndex = mid;
                break;
            } else if (comparison < 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        if (binaryFoundIndex != -1) {
            System.out.println("Bogie ID found: " + binarySearchKey + " at index " + binaryFoundIndex);
        } else {
            System.out.println("Bogie ID not found: " + binarySearchKey);
        }

        System.out.println("\nTrain consist operations complete. Ready for next use case.\n");

        // UC20: Fail fast when search is attempted on an empty train consist
        System.out.println("=== UC20: Defensive Search State Validation ===");
        List<String> emptyTrainBogieIds = new ArrayList<>();
        String defensiveSearchKey = "BG101";

        try {
            boolean exists = searchBogieIdDefensively(emptyTrainBogieIds, defensiveSearchKey);
            System.out.println("Bogie ID found: " + defensiveSearchKey + " => " + exists);
        } catch (IllegalStateException e) {
            System.out.println("Search aborted: " + e.getMessage());
        }

        System.out.println("Search operation ended safely after defensive validation.");

        System.out.println("\nAll use cases executed successfully.");
    }
}