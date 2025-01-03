import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdventOfCodeDay2P2 {

    public static void main(String[] args) throws IOException {

        // Specify the file path
        String filePath = "src/AOC2input";

        // Read numbers from the file
        List<List<Integer>> sequences = readFile(filePath);

         /*
        Day 2 Part 1
         */


        int problem1Count = 0;
        int problem2Count=0;

        for (List <Integer> level : sequences) {
            if(isIncreasingOrDecreasing(level)) {
                problem1Count++;

            }
            if (IsLevelRemovedSafe(level)) {
                problem2Count++;
            }
        }

        System.out.println("Part 1 Answer: " + problem1Count);

        System.out.println("Part 2 Answer: " + problem2Count);

    }

    // not an optimal solution tho :3
    private static boolean IsLevelRemovedSafe(List<Integer> level) {
        for (int i = 0; i < level.size(); i++) {
            List<Integer> temp = new ArrayList<>(level);
            temp.remove(i);
            if (isIncreasingOrDecreasing(temp)) {
                return true;
            }

        }

        return false;

    }

    private static boolean isIncreasingOrDecreasing(List<Integer> level) {
        boolean isIncreasing = true;
        boolean isDecreasing = true;

        for (int i = 0; i < level.size() - 1; i++) {
            int diff = level.get(i + 1) - level.get(i);

            if ((diff != 1 && diff != 2 && diff != 3)) {
                isIncreasing = false;
            }


            if ((diff != -1 && diff != -2 && diff != -3)) {
                isDecreasing = false;
            }

            if (!isIncreasing && !isDecreasing) {
                return false;
            }
        }

        return true;
    }

    /**
     * Reads lines of integers from the specified file.
     * Each line should contain space-separated integers.
     */
    private static List<List<Integer>> readFile(String filePath) {
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
}
