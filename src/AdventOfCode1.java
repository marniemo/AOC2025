import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class AdventOfCode1 {
    public static void main(String[] args) {

        //Load the 2 arrays of locations
        String output = loadArrays();
        System.out.println(output);
        //Compare the values

        //output the total difference

    }

    private static String loadArrays() {

        String filePath = "src/AOC1input.csv";

        // Lists to store the integers from each column
        List<Integer> ArrayList1 = new ArrayList<>();
        List<Integer> ArrayList2 = new ArrayList<>();

        // Read the file and populate the columns
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.trim().split("\\s+"); // Split by whitespace
                if (tokens.length == 2) {
                    try {
                        ArrayList1.add(Integer.parseInt(tokens[0]));
                        ArrayList2.add(Integer.parseInt(tokens[1]));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid integer format in line: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return "Failed";
        }

        // Convert the lists to arrays
        Integer[] array1 = ArrayList1.toArray(new Integer[0]);
        Integer[] array2 = ArrayList2.toArray(new Integer[0]);

        // Sort the arrays
        Arrays.sort(array1);
        Arrays.sort(array2);

        // Print the sorted arrays
        System.out.println("Sorted Column 1:");
        System.out.println(Arrays.toString(array1));

        System.out.println("Sorted Column 2:");
        System.out.println(Arrays.toString(array2));

        //get the differences and print them
        int[] differences = getDifferences(array1, array2);
        int total = Arrays.stream(differences).sum();
        System.out.println("Differences contents: " + Arrays.toString(differences));

        //Total difference print
        System.out.println("Differences total " + total);

        //The count and print the incidences
        countIncidence(array1, array2);

    return "Success";}

    // Private method to get the difference between integers at the same index in both arrays
    private static int[] getDifferences(Integer[] array1, Integer[] array2) {

        System.out.println("Getting differences");

        // Create an array to store the differences
        int[] differences = new int[array1.length];

        // Calculate the differences
        for (int i = 0; i < array1.length; i++) {
            differences[i] = array1[i] - array2[i];
            if (differences[i] <0)
            { differences[i] = -differences[i];}
        }

        return differences;
    }
     private static Integer countIncidence(Integer[] array1, Integer[] array2){

         // Count occurrences of each element in array2
         Map<Integer, Long> array2CountMap = Arrays.stream(array2)
                 .collect(Collectors.groupingBy(n -> n, Collectors.counting()));

         // Count how many times each element in array1 appears in array2
         Map<Integer, Long> countMap = Arrays.stream(array1)
                 .collect(Collectors.groupingBy(n -> n,
                         Collectors.summingLong(n -> array2CountMap.getOrDefault(n, 0L))));
         // Step 2: Multiply each integer in array2 by its incidence in array1
         Map<Integer, Long> resultMap = Arrays.stream(array1)
                 .collect(Collectors.toMap(
                         n -> n,  // Key is the element from array2
                         n -> n * array2CountMap.getOrDefault(n, 0L)  // Multiply by the count in array1
                 ));

         // Print the result
         resultMap.forEach((key, value) -> System.out.println("Element " + key + " * its incidence: " + value));
         long totalSum = 0;
         for (long value : resultMap.values()) {
             totalSum += value;}
         System.out.println(totalSum);

         return 1;
     }
}
