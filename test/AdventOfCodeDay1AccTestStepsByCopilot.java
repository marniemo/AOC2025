import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class AdventOfCode1StepDefs {
    private File file;
    private List<Integer> array1;
    private List<Integer> array2;
    private int[] differences;
    private String result;

    @Given("a file with the following contents:")
    public void a_file_with_the_following_contents(String contents) throws IOException {
        file = File.createTempFile("test", ".txt");
        FileWriter writer = new FileWriter(file);
        writer.write(contents);
        writer.close();
    }

    @Given("a non-existent file")
    public void a_non_existent_file() {
        file = new File("non-existent-file.txt");
    }

    @Given("two arrays: {string} and {string}")
    public void two_arrays(String array1Str, String array2Str) {
        array1 = new ArrayList<>();
        array2 = new ArrayList<>();
        String[] tokens1 = array1Str.replace("[", "").replace("]", "").split(",");
        String[] tokens2 = array2Str.replace("[", "").replace("]", "").split(",");
        for (String token : tokens1) {
            array1.add(Integer.parseInt(token.trim()));
        }
        for (String token : tokens2) {
            array2.add(Integer.parseInt(token.trim()));
        }
    }

    @When("I load arrays from the file")
    public void i_load_arrays_from_the_file() {
        result = AdventOfCode1.loadArrays(file.getAbsolutePath());
    }

    @When("I get the differences between the arrays")
    public void i_get_the_differences_between_the_arrays() {
        differences = AdventOfCode1.getDifferences(array1.toArray(new Integer[0]), array2.toArray(new Integer[0]));
    }

    @When("I count incidences")
    public void i_count_incidences() {
        result = String.valueOf(AdventOfCode1.countIncidence(array1.toArray(new Integer[0]), array2.toArray(new Integer[0])));
    }

    @Then("the first array should be {string}")
    public void the_first_array_should_be(String expectedArrayStr) {
        String[] tokens = expectedArrayStr.replace("[", "").replace("]", "").split(",");
        Integer[] expectedArray = new Integer[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            expectedArray[i] = Integer.parseInt(tokens[i].trim());
        }
        assertArrayEquals(expectedArray, array1.toArray(new Integer[0]));
    }

    @Then("the second array should be {string}")
    public void the_second_array_should_be(String expectedArrayStr) {
        String[] tokens = expectedArrayStr.replace("[", "").replace("]", "").split(",");
        Integer[] expectedArray = new Integer[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            expectedArray[i] = Integer.parseInt(tokens[i].trim());
        }
        assertArrayEquals(expectedArray, array2.toArray(new Integer[0]));
    }

    @Then("the result should be {string}")
    public void the_result_should_be(String expectedResult) {
        assertEquals(expectedResult, result);
    }

    @Then("the result should be {int}")
    public void the_result_should_be(int expectedResult) {
        assertEquals(expectedResult, Integer.parseInt(result));
    }

    @Then("the result should be {string}")
    public void the_result_should_be(String expectedArrayStr) {
        String[] tokens = expectedArrayStr.replace("[", "").replace("]", "").split(",");
        int[] expectedArray = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            expectedArray[i] = Integer.parseInt(tokens[i].trim());
        }
        assertArrayEquals(expectedArray, differences);
    }
}