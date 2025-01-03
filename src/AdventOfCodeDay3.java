import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

    public class AdventOfCodeDay3 {

        public static void main(String[] args) throws IOException {

            List<String> lines = AOCFileReader.readStringsFromFile("src/AOCDay3Input");

        /*
        Day 3 Part 1
         */

            Pattern pattern
                    = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");

            List<List<Integer>> mulPairs = new ArrayList<>();


            for(String line: lines){
                Matcher matcher
                        = pattern.matcher(line);

                while(matcher.find()){
                    List<Integer> temp = new ArrayList<>();
                    temp.add(Integer.parseInt(matcher.group(1)));
                    temp.add(Integer.parseInt(matcher.group(2)));
                    mulPairs.add(temp);
                }
            }

            System.out.println("Pattern converted is: " + mulPairs);

            int sum = 0;
            for(List<Integer> list : mulPairs){
                sum+= list.get(0) * list.get(1);
            }

            System.out.println("Part 1 Answer:"+ sum);

         /*
        Day 3 Part 2
         */

            Pattern pattern2 = Pattern.compile("do\\(\\)|don't\\(\\)|mul\\((\\d{1,3}),(\\d{1,3})\\)");

            List<List<Integer>> mulPairs2 = new ArrayList<>();

            boolean isEnabled = true;
            for(String line: lines){
                Matcher matcher
                        = pattern2.matcher(line);

                while(matcher.find()){

                    String match = matcher.group();

                    if (match.equals("don't()")) {
                        isEnabled = false;
                    } else if (match.equals("do()")) {
                        isEnabled = true;
                    } else if (isEnabled && matcher.group(1) != null && matcher.group(2) != null) {
                        List<Integer> temp = new ArrayList<>();
                        temp.add(Integer.parseInt(matcher.group(1)));
                        temp.add(Integer.parseInt(matcher.group(2)));
                        mulPairs2.add(temp);
                    }
                }
            }

            int sum2 = 0;
            for(List<Integer> list : mulPairs2){
                sum2+= list.get(0) * list.get(1);
            }

            System.out.println("Part 2 Answer:"+ sum2);


        }


    }
