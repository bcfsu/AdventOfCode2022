package Day6;

import java.io.*;
import java.net.URL;
import java.util.HashMap;

public class Day6 {

    public static String getResult() {
        URL path = Day6.class.getResource("sample.txt");
        File f = new File(path.getFile());

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "Sure!!!";
    }

    private static int iterateThrough(String input) {
        HashMap<String, Integer> setOfLetters = new HashMap<>();
        String[] inputArray = input.split("");
        int result = 0;

        for (int i = 0; i < inputArray.length; i++) {
            if (setOfLetters.keySet().size() == 4) {
                System.out.println("Set of Letters contains 4 keys, returning");
                result = i;
                break;
            }

            String letter = inputArray[i];
            // zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw
            // ^
            if (!setOfLetters.containsKey(letter)) {
                setOfLetters.put(letter, i);
                // continue ?
            }

            if (setOfLetters.containsKey(letter)) {

            }

        }
        return result;

    }
}

// Keep track of number of letters iterated through (will become the result) (index of for loop)
// HashMap of size FOUR only
// { letter : lastIndexSeenAt }
// Counter % 4 --> return if hashMap.keySet.size == 4
}
