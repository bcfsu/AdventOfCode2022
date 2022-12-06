package Day4;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.stream.IntStream;

// Part 1 correct answer 413
// Part 2 correct answer 806

public class Day4 {

    private static Integer pairsThatContainFullOverlaps = 0;

    private static Integer pairsThatContainAnyOverlap = 0;

    public static String getResult() {
        URL path = Day4.class.getResource("input.txt");
        File f = new File(path.getFile());

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                getPartTwoResults(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return pairsThatContainAnyOverlap.toString();
    }

    private static void getPartTwoResults(String input) {
        String[] listOfRangeStrings = input.split(","); // "2-4,6-8" => ["2-4", "6-8"]

        final Set<Integer> firstRange = rangeStringToSet(listOfRangeStrings[0]);
        final Set<Integer> secondRange = rangeStringToSet(listOfRangeStrings[1]);

        compareSetsAndIncrementPartialOverlapsIfNeeded(firstRange, secondRange);
    }

    private static void compareSetsAndIncrementPartialOverlapsIfNeeded(final Set<Integer> one, final Set<Integer> two) {
        Set<Integer> copyOfOne = new HashSet<>(one);
        Set<Integer> copyOfTwo = new HashSet<>(two);

        if (copyOfOne.removeAll(copyOfTwo)) {
            pairsThatContainAnyOverlap++;
            return;
        }
    }

    private static void getPartOneResults(String input) {
        String[] listOfRangeStrings = input.split(","); // "2-4,6-8" => ["2-4", "6-8"]

        final Set<Integer> firstRange = rangeStringToSet(listOfRangeStrings[0]);
        final Set<Integer> secondRange = rangeStringToSet(listOfRangeStrings[1]);

        compareSetsAndIncrementFullOverlapsIfNeeded(firstRange, secondRange);
    }

    private static void compareSetsAndIncrementFullOverlapsIfNeeded(final Set<Integer> one, final Set<Integer> two) {
        Set<Integer> copyOfOne = new HashSet<>(one);
        Set<Integer> copyOfTwo = new HashSet<>(two);

        copyOfOne.removeAll(copyOfTwo);
        if (copyOfOne.isEmpty()) {
            // full overlap is found
            pairsThatContainFullOverlaps++;
            return;
        }

        copyOfOne = new HashSet<>(one);
        copyOfTwo.removeAll(copyOfOne);
        if (copyOfTwo.isEmpty()) {
            // full overlap is found
            pairsThatContainFullOverlaps++;
            return;
        }
//        System.out.println("No overlap found for inputs:");
//        System.out.println(one);
//        System.out.println(two);
//        System.out.println("-");
    }

    private static Set<Integer> rangeStringToSet(String rangeString) {
        // "2-4" ==> Set[2, 3, 4]
        Set<Integer> result = new HashSet<>();
        String[] array = rangeString.split("-");
        Integer start = Integer.valueOf(array[0]);
        Integer end = Integer.valueOf(array[1]) + 1;

        IntStream.range(start, end).forEach(num -> result.add(num));

        return result;
    }

}
