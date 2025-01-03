import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class AdventOfCodeDay3Test {

    @Test
    public void testPatternMatching() {
        String input = "mul(10,20)";
        Pattern pattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");
        Matcher matcher = pattern.matcher(input);
        assertTrue(matcher.find());
        assertEquals("10", matcher.group(1));
        assertEquals("20", matcher.group(2));
    }

    @Test
    public void testMulPairsExtraction() {
        String input = "mul(10,20)\nmul(30,40)";
        List<String> lines = Arrays.asList(input.split("\n"));
        List<List<Integer>> mulPairs = new ArrayList<>();
        Pattern pattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");
        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                List<Integer> temp = new ArrayList<>();
                temp.add(Integer.parseInt(matcher.group(1)));
                temp.add(Integer.parseInt(matcher.group(2)));
                mulPairs.add(temp);
            }
        }
        assertEquals(2, mulPairs.size());
        assertEquals(Arrays.asList(10, 20), mulPairs.get(0));
        assertEquals(Arrays.asList(30, 40), mulPairs.get(1));
    }

    @Test
    public void testSumCalculation() {
        List<List<Integer>> mulPairs = Arrays.asList(
                Arrays.asList(10, 20),
                Arrays.asList(30, 40)
        );
        int sum = 0;
        for (List<Integer> list : mulPairs) {
            sum += list.get(0) * list.get(1);
        }
        assertEquals(2000, sum);
    }

    @Test
    public void testPart1() {
        // Test part 1 logic here
        AdventOfCodeDay3 adventOfCodeDay3 = new AdventOfCodeDay3();
        // Call the part 1 method and assert the result
    }

    @Test
    public void testPart2() {
        // Test part 2 logic here
        AdventOfCodeDay3 adventOfCodeDay3 = new AdventOfCodeDay3();
        // Call the part 2 method and assert the result
    }
}