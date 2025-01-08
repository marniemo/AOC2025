public class AdventOfCodeDay1RefactorByCodeium {
    private static final String FILE_PATH = "src/AOC1input.csv";

    public static void main(String[] args) {
        List<Integer> array1 = loadArrays(FILE_PATH, 0);
        List<Integer> array2 = loadArrays(FILE_PATH, 1);

        if (array1 != null && array2 != null) {
            int[] differences = getDifferences(array1, array2);
            int total = Arrays.stream(differences).sum();
            System.out.println("Differences contents: " + Arrays.toString(differences));
            System.out.println("Differences total " + total);
        }
    }

    private static List<Integer> loadArrays(String filePath, int columnIndex) {
        List<Integer> array = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.trim().split("\\s+");
                if (tokens.length >= columnIndex + 1) {
                    try {
                        array.add(Integer.parseInt(tokens[columnIndex]));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid integer format in line: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return null;
        }

        return array;
    }

    private static int[] getDifferences(List<Integer> array1, List<Integer> array2) {
        if (array1.size() != array2.size()) {
            throw new IllegalArgumentException("The two arrays must have the same size");
        }

        int[] differences = new int[array1.size()];
        for (int i = 0; i < array1.size(); i++) {
            differences[i] = array1.get(i) - array2.get(i);
            if (differences[i] < 0) {
                differences[i] = -differences[i];
            }
        }

        return differences;
    }
}
