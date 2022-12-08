import java.util.Arrays;
import java.util.Random;
import java.nio.file.Files;
import java.time.Duration;
import java.nio.charset.StandardCharsets;
import java.util.stream.IntStream;
import java.io.*;

public class SortSearchAssign {
    private static final File file = new File("somanynumbers.txt");
    public static void main(String[] args) {
        // START HERE
        boolean fileExists = Files.exists(file.toPath());
        if(!fileExists) createFile();
        try {
            int[] readIntegers = loadFile();
            AllSorts sorts = new AllSorts();
            AllSearch search = new AllSearch();

            long startTimeMS = System.currentTimeMillis();

            // THE FASTEST SHORT
            // A MERGE SORT, CALLS IN METHOD WHICH WILL CONSIST OF n NUMBER OR LENGTH OF THE ARRAY 
            // IT WILL DIVIDE AND CONQUER, AND TIME COMPLEXITY WILL BE THE SAME.

            int[] sorted = sorts.mergeSort(readIntegers);

            // IT WILL BE FASTER BECAUSE OF BINARY
            // ALSO BECAUSE ITS SORTED IT WILL MAKE IT FASTER AND LINEAR ON THE OTHER HAND WOULD BE NOT AS FAST
            // BECAUSE OF THE EXTRA CHECK REQUIRED DURING PROCESSING.

            int foundNumberIndex = search.jumpSearch(sorted, 44);

            // THE SLOWEST SHORT
            // MOST BASIC SEARCH ALGORITHM,WHICH IS BUBBLE THAT COMPARES EACH ADJ.
            // AND CHECKS IF ELEMENTS ARE IN ORDER, THEN IT SWAPS THEM. 
            // IT THEN TURNS A DOUBLY-SIZED TIME COMPLEXITY OF n^2.
            // ALSO THE SLOWEST SEARCH 
            // A JUMP SEARCH THAT REQUIRES TO CHECK ENTRIES FOR BASE VALUE WHEN SEARCHING
            // EVEN THOUGH IT FILTERS 

            // IE: SLOW.
            /*int[] sorted = sorts.bubbleSort(readIntegers);
            int foundNumberIndex = search.jumpSearch(sorted, 44);*/

            System.out.println("Found number at index: " + foundNumberIndex);
            long endTimeMS = System.currentTimeMillis();
            long totalTimeMS = (endTimeMS - startTimeMS);
            boolean isSeconds = totalTimeMS > 1000;

            System.out.println("Total search time took: " + 
             (isSeconds ? Duration.ofMillis(totalTimeMS).getSeconds():
             totalTimeMS) + (isSeconds ? "seconds." : "ms."));
        } 
        catch(IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
    // Method which generates text file containing 1 million random numbers
    public static void createFile()
    {
        // File to be created in default directory
        Random rng = new Random();
        int r;

        try {
            // Create the file
            PrintWriter output = new PrintWriter(file);

            // Write random numbers into a file
            for (int i = 0; i < 1000000; i++)
            {
                // Genereates a random number in range of (0 - 100)
                r = rng.nextInt(101);
                output.write(r + ", ");
            }
            System.out.println("File created.");
            // Close file
            output.close();
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("Cannot do that.");
        }
    }

    private static int[] loadFile() throws IOException, NumberFormatException {
        byte[] bytes = Files.readAllBytes(file.toPath());
        String value = new String(bytes, StandardCharsets.UTF_8);

        return Arrays.stream(value.split(", "))
        .flatMapToInt(num -> IntStream.of(Integer.parseInt(num))).toArray();
    }
}