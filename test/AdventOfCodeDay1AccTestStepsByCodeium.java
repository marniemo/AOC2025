import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AdventOfCode1StepDefs {
    private File file;
    private List<Integer> array1;
    private List<Integer> array2;
    private int[] differences;

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

    @Given("two arrays: {int} and {int}")
    public void two_arrays(String array1Str, String array2Str) {
        array1 = new ArrayList<>();
        array2 = new ArrayList<>();
        String[] tokens1 = array1Str.split(",");
        String[] tokens2 = array2Str.split(",");
        for (String token : tokens1) {
            array1.add(Integer.parseInt(token.trim()));
        }
        for (String token : tokens2) {
            array2.add(Integer.parseInt(token.trim()));
        }
    }

    @When("I load arrays from the file")
    public void i_load_arrays_from_the_file() {
        array1 = AdventOfCode1.loadArrays(file.getAbsolutePath(), 0);
        array2 = AdventOfCode1.loadArrays(file.getAbsolutePath(), 1);
    }
