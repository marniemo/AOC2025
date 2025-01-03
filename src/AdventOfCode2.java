import java.io.*;
import java.util.*;

public class AdventOfCode2 {


    public static void main(String[] args) {
        Integer safe = 0;

        // Specify the file path
        String filePath = "src/ACO2sample";

        // Read numbers from the file
        List<List<Integer>> sequences = readFile(filePath);

        // Check each sequence
        for (List<Integer> sequence : sequences) {
            if (sequence.isEmpty()) {
                System.out.println("Empty sequence, skipping.");
            } else {
                System.out.println("Sequence: " + sequence);
                String result = checkSequence(sequence);
                if (!result.equals("unsafe")) {
                    safe++;
                }
                System.out.println("Sequence: " + sequence + " is " + result);
            }
        }
        System.out.println("Safe count is: " + safe);
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

    /**
     * Checks if the given sequence of integers is only increasing,
     * nly decreasing otherwise checks if adjacent numbers
     * differ between 1 and 3. Allows for one integer exception ...
     */
    private static String checkSequence(List<Integer> sequence) {
        boolean isIncreasing = false;
        boolean isDecreasing = false;
        boolean validDifference = false;

        // Loop through the sequence to determine if it's increasing, decreasing,
        // and if the difference between adjacent numbers is between 1 and 3
        Integer max = 0;
        Integer min = 0;
        Integer exceptionCount = 0;
        boolean exceptional = false;

        for (int i = 0; i < sequence.size() - 1; i++) {
            int current = sequence.get(i);
            int next = sequence.get(i + 1);
            int difference = Math.abs(current - next);

            if (i == 0) {
                min = current;
                max = current;
            }
            if ((next > current) && (isIncreasing || i == 0)) {
                if ((next > max) && (difference >= 1 && difference <= 3)) {
                    isIncreasing = true;
                    max = next;
                } else {
                    if (exceptionCount >= 1) {
                        isIncreasing = false;
                    }
                }
            } else {
                if (exceptionCount >= 1) {
                    isIncreasing = false;
                }
            }

            if ((next < current) && (isDecreasing || i == 0)) {
                if ((next < min) && (difference >= 1 && difference <= 3)) {
                    isDecreasing = true;
                    min = next;
                } else {
                    if (exceptionCount >= 1) {
                        isDecreasing = false;
                    }
                }
            } else {
                if (exceptionCount >= 1) {
                    isDecreasing = false;
                }
            }

            if (difference >= 1 && difference <= 3) {
                validDifference = true;
            } else {
                validDifference = false;
                exceptional = true;
            }

            if (exceptional) {
                exceptionCount++;
                if (exceptionCount == 1) {
                    System.out.println("Skipping 1 place as one exception allowed");
                    i++;
                }
            }
            System.out.println("Difference is:" + difference);

        }
if (exceptionCount > 1) {
            return "unsafe";
        }

            // Determine the result based on the flags
            if (isIncreasing) {
                return "increasing";
            } else if (isDecreasing) {
                return "decreasing";
            } else if (validDifference) {
                return "validDifference";
            } else if (exceptionCount == 1) {
                System.out.println("Only one exception");
                return "OnlyOneException";
            } else {
                return "unsafe";
            }
        }

}


