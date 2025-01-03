import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AOCFileReader {
    /**
     * Reads lines of integers from the specified file.
     * Each line should contain space-separated integers.
     */
    public static List<List<Integer>> readIntegersFromFile(String filePath) {
        List<List<Integer>> sequences = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.trim().split("\\s+");
                List<Integer> sequence = new ArrayList<>();

                // Parse integers from the line and add to the sequence
                for (String token : tokens) {
                    try {
                        sequence.add(Integer.parseInt(token));
                    } catch (NumberFormatException e) {
                        System.out.println("Skipping invalid number: " + token);
                    }
                }

                if (!sequence.isEmpty()) {
                    sequences.add(sequence);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return sequences;
    }

        /**
         * Reads lines of integers from the specified file.
         * Each line should contain space-separated integers.
         */
        public static List<String> readStringsFromFile(String filePath){
            List<String> strings = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    strings.add(line);
                }
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
            return strings;
        }
}
