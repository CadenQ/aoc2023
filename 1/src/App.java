import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    public static void main(String[] args) throws Exception {
        part1();
        part2();
    }

    // Part one of the problem
    public static void part1() throws Exception {
        // Initializing variables
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        int sum = 0;
        char firstNum = '\u0000', lastNum = '\u0000';
        String line = "";

        // Loops through every line in input.txt
        while ((line = br.readLine()) != null) {
            for (int i = 0; i < line.length(); i++) {
                // Checks which characters are digits, and keeps track of them
                if (Character.isDigit(line.charAt(i))) {
                    if (firstNum == '\u0000') {
                        firstNum = line.charAt(i);
                        lastNum = line.charAt(i);
                    } else
                        lastNum = line.charAt(i);
                }
            }

            // Adds the first and last digits together then adds to the sum
            sum += Integer.parseInt("" + firstNum + lastNum);
            // Wipe values clean (likely unnecessary)
            firstNum = '\u0000';
            lastNum = '\u0000';
        }

        br.close();
        System.out.println(sum);
    }

    // Part two of the problem
    public static void part2() throws Exception {
        // Initialize variables
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        List<Integer> allMatches = new ArrayList<>();
        String line = "";
        int sum = 0;

        // Loop through every line in input.txt
        while ((line = br.readLine()) != null) {
            // Match a regex to all possible given options
            Matcher m = Pattern.compile("\\d|(?=(one|two|three|four|five|six|seven|eight|nine))").matcher(line);
            
            // For each match...
            while (m.find()) {
                // A string builder is created just in case what was found wasn't in a numerical format
                StringBuilder s = new StringBuilder();

                // Starts iterating at the start of the given match, until the whole match has been discovered
                for (int i = m.start(); i < line.length(); i++) {
                    s.append(line.charAt(i));

                    switch (s.toString()) {
                        case "1": case "2": case "3": case "4": case "5": case "6": case "7": case "8": case "9": case "0":
                            allMatches.add(Integer.parseInt(s.toString()));
                            break;
                        case "zero": allMatches.add(0); break;
                        case "one": allMatches.add(1); break;
                        case "two": allMatches.add(2); break;
                        case "three": allMatches.add(3); break;
                        case "four": allMatches.add(4); break;
                        case "five": allMatches.add(5); break;
                        case "six": allMatches.add(6); break;
                        case "seven": allMatches.add(7); break;
                        case "eight": allMatches.add(8); break;
                        case "nine": allMatches.add(9); break;
                    }
                }
            }

            // Add first and last found values to the sum and reset allMatches for the next line
            sum += (allMatches.get(0) * 10) + allMatches.get(allMatches.size() - 1);
            allMatches = new ArrayList<>();
        }

        br.close();
        System.out.println(sum);
    }
}
