package Day6;

import java.io.*;
import java.net.URL;
import java.util.HashMap;

// Wrong answers: 83, 25
public class Day6 {

    public static String getResult() {
        URL path = Day6.class.getResource("input.txt");
        File f = new File(path.getFile());

        Integer result = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
//                System.out.println("Answer for line: " + line);
//                System.out.println(secondIterateThrough(line));
                result = getResultSecondAttempt(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result.toString();
    }

    private static int getResultFirstAttempt(String input) {
        int result = 0;

        String[] inputArray = input.split("");
        HashMap<String, Integer> lettersSeenBefore = new HashMap<>();

        int startOfGroupIndex = 0;
        for (int i = 0; i < inputArray.length; i++) {
            String letter = inputArray[i];

            if (!lettersSeenBefore.containsKey(letter)) {
                lettersSeenBefore.put(letter, i);
            } else {
                // Where
                int lastIndexSeen = lettersSeenBefore.get(letter);
                // less than or equal to...?
                if (i - lastIndexSeen < 4) {
                    startOfGroupIndex = lastIndexSeen + 1;
                    lettersSeenBefore.put(letter, i);
                }
            }

            // The check here is not working on first example
            if (i - startOfGroupIndex == 3) {
                result = i + 1;
            }
        }
        return result;
    }

    private static int getResultSecondAttempt(String input) {
        int result = 0;

        String[] inputArray = input.split("");
        HashMap<String, Integer> lettersSeenBefore = new HashMap<>();
        // zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw
        //    ^
        // .

        int startOfGroupIndex = 0;
        for (int i = 0; i < inputArray.length; i++) {
            String letter = inputArray[i];

            // Check that we have a sequence of 4 letters
            // Check that the current letter is not a dupe
            if (i - startOfGroupIndex == 3 && !lettersSeenBefore.containsKey(letter)) {
                result = i + 1;
                break;
            }

            // Check that we have a sequence of 4 letters
            // If the current letter is a dupe, the dupe occurred BEFORE the current group's beginning
            if (i - startOfGroupIndex == 3 && lettersSeenBefore.get(letter) < startOfGroupIndex) {
                result = i + 1;
                break;
            }

            // If we did not break for result
            // Add the current letter to the hash
            if (!lettersSeenBefore.containsKey(letter)) {
                lettersSeenBefore.put(letter, i);
            } else {
                // Or get the most recent index the letter was seen at
                int lastIndexSeen = lettersSeenBefore.get(letter);

                // If the last index it was seen at is within the current range of 4 letters
                // Shift the start of the 4 letter group forward by 1
                if (lastIndexSeen >= startOfGroupIndex) {
                    startOfGroupIndex = lastIndexSeen + 1;
                    // Record the last index that the letter was seen at
                    lettersSeenBefore.put(letter, i);
                }
            }
        }
        return result;
    }
}
