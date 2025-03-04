import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class AdventOfCodeDay1RefactorByCoPilot {

    private static final String FILE_PATH = "src/AOC1input.csv";

    public static void main(String[] args) {
        String output = loadArrays();
        System.out.println(output);
    }

    private static String loadArrays() {
        List<Integer> arrayList1 = new ArrayList<>();
        List<Integer> arrayList2 = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.trim().split("\\s+");
                if (tokens.length == 2) {
                    try {
                        arrayList1.add(Integer.parseInt(tokens[0]));
                        arrayList2.add(Integer.parseInt(tokens[1]));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid integer format in line: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return "Failed";
        }

        Integer[] array1 = arrayList1.toArray(new Integer[0]);
        Integer[] array2 = arrayList2.toArray(new Integer[0]);

        Arrays.sort(array1);
        Arrays.sort(array2);

        System.out.println("Sorted Column 1:");
        System.out.println(Arrays.toString(array1));

        System.out.println("Sorted Column 2:");
        System.out.println(Arrays.toString(array2));

        int[] differences = getDifferences(array1, array2);
        int total = Arrays.stream(differences).sum();
        System.out.println("Differences contents: " + Arrays.toString(differences));
        System.out.println("Differences total: " + total);

        countIncidence(array1, array2);

        return "Success";
    }

    private static int[] getDifferences(Integer[] array1, Integer[] array2) {
        int[] differences = new int[array1.length];
        for (int i = 0; i < array1.length; i++) {
            differences[i] = Math.abs(array1[i] - array2[i]);
        }
        return differences;
    }

    private static void countIncidence(Integer[] array1, Integer[] array2) {
        Map<Integer, Long> array2CountMap = Arrays.stream(array2)
                .collect(Collectors.groupingBy(n -> n, Collectors.counting()));

        Map<Integer, Long> resultMap = Arrays.stream(array1)
                .collect(Collectors.toMap(
                        n -> n,
                        n -> n * array2CountMap.getOrDefault(n, 0L)
                ));

        resultMap.forEach((key, value) -> System.out.println("Element " + key + " * its incidence: " + value));
        long totalSum = resultMap.values().stream().mapToLong(Long::longValue).sum();
        System.out.println(totalSum);
    }
}
