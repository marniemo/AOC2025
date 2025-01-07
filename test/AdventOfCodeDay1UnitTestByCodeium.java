import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdventOfCode1Test {

    @Test
    public void testLoadArrays() throws IOException {
        // Create a test file
        File file = new File("test.txt");
        FileWriter writer = new FileWriter(file);
        writer.write("1 2\n3 4\n5 6");
        writer.close();

        // Load the arrays
        List<Integer> array1 = AdventOfCode1.loadArrays("test.txt", 0);
        List<Integer> array2 = AdventOfCode1.loadArrays("test.txt", 1);

        // Check the results
        assertNotNull(array1);
        assertNotNull(array2);
        assertEquals(3, array1.size());
        assertEquals(3, array2.size());
        assertEquals(1, (int) array1.get(0));
        assertEquals(2, (int) array2.get(0));
        assertEquals(3, (int) array1.get(1));
        assertEquals(4, (int) array2.get(1));
        assertEquals(5, (int) array1.get(2));
        assertEquals(6, (int) array2.get(2));

        // Clean up
        file.delete();
    }

    @Test
    public void testLoadArraysInvalidFile() {
        // Load the arrays from a non-existent file
        List<Integer> array = AdventOfCode1.loadArrays("non-existent-file.txt", 0);

        // Check the result
        assertNull(array);
    }

    @Test
    public void testLoadArraysInvalidFormat() throws IOException {
        // Create a test file with invalid format
        File file = new File("test.txt");
        FileWriter writer = new FileWriter(file);
        writer.write("abc def\nghi jkl");
        writer.close();

        // Load the arrays
        List<Integer> array1 = AdventOfCode1.loadArrays("test.txt", 0);
        List<Integer> array2 = AdventOfCode1.loadArrays("test.txt", 1);

        // Check the results
        assertNotNull(array1);
        assertNotNull(array2);
        assertEquals(0, array1.size());
        assertEquals(0, array2.size());

        // Clean up
        file.delete();
    }

    @Test
    public void testGetDifferences() {
        // Create test arrays
        List<Integer> array1 = new ArrayList<>();
        array1.add(1);
        array1.add(3);
        array1.add(5);

        List<Integer> array2 = new ArrayList<>();
        array2.add(2);
        array2.add(4);
        array2.add(6);

        // Get the differences
        int[] differences = AdventOfCode1.getDifferences(array1, array2);

        // Check the results
        assertNotNull(differences);
        assertEquals(3, differences.length);
        assertEquals(1, differences[0]);
        assertEquals(1, differences[1]);
        assertEquals(1, differences[2]);
    }

    @Test
    public void testGetDifferencesInvalidInput() {
        // Create test arrays with different sizes
        List<Integer> array1 = new ArrayList<>();
        array1.add(1);
        array1.add(3);

        List<Integer> array2 = new ArrayList<>();
        array2.add(2);
        array2.add(4);
        array2.add(6);

        // Get the differences
        assertThrows(IllegalArgumentException.class, () -> AdventOfCode1.getDifferences(array1, array2));
    }
}