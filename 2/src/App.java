import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
       part1();
       part2(); 
    }

    // Part one of the problem
    public static void part1() throws Exception {
        // Initialize variables
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        String line = "";
        int sum = 0;
        Map<String, Integer> maxValues = new HashMap<>();
        boolean impossible;

        // Map maximum values given by the problem
        maxValues.put("red", 12);
        maxValues.put("green", 13);
        maxValues.put("blue", 14);

        // Loop through every line in the given input.txt
        while ((line = br.readLine()) != null) {
            // Initalize variables to organize and parse the given data
            String[] gameSetSplit = line.split(":");
            String[] setSplit = gameSetSplit[1].split(";");
            impossible = false;
            
            // Check each set of cubes given in the game to see if an impossible combination was provided.
            // If an impossible combination is provided, the boolean impossible is set to true.
            for (String set : setSplit) {
                for (String entry : set.split(",")) {
                    String[] entryValues = entry.split(" ");
                    if (maxValues.get(entryValues[2]) < Integer.parseInt(entryValues[1])) {
                        impossible = true;
                        break;
                    }
                }
                
                if (impossible)
                    break;
            }

            if (impossible)
                continue;

            // If impossible was false, then we add the game number to our total sum
            sum += Integer.parseInt(gameSetSplit[0].split(" ")[1]);
        }

        br.close();
        System.out.println(sum);
    }

    // Part two of the problem
    public static void part2() throws Exception {
        // Initialize variables
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        String line = "";
        int sum = 0;
        Map<String, Integer> minPossible = new HashMap<>();

        // Set default values of  the minimum required cubes possible
        minPossible.put("red", 0);
        minPossible.put("green", 0);
        minPossible.put("blue", 0);

        // Loop through every line of the given input.txt
        while ((line = br.readLine()) != null) {
            // Initialize variables to organize given data
            String[] setSplit = line.split(":")[1].split(";");
            
            // Loop through every set in the given game to record the minimum required amount of cubes into minPossible
            for (String set : setSplit) {
                for (String entry : set.split(",")) {
                    String[] entryValues = entry.split(" ");
                    int cubeAmount = Integer.parseInt(entryValues[1]);
                    
                    if (minPossible.get(entryValues[2]) < cubeAmount)
                        minPossible.replace(entryValues[2], cubeAmount);
                }
            }
            // Multiply all the values contained within minPossible together to get the desired result
            sum += minPossible.values().stream().reduce(1, (i, j) -> i * j);
            // Reset the minPossible map to the default values of 0
            minPossible.replaceAll((key, value) -> 0);
        }

        br.close();
        System.out.println(sum);
    }

    // Printing function to print out an actual good readable array output (saving for the future)
    public static <E> String arrayToString (E[] array) {
        StringBuilder s = new StringBuilder();

        s.append("[");
        for (int i = 0; i < array.length-1; i++) 
            s.append(array[i] + ", ");
        s.append(array[array.length-1] + "]");

        return s.toString();
    }
}
