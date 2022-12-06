package Day1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public class Day1 {

  public static String getResult() {
    URL path = Day1.class.getResource("input.txt");
    File f = new File(path.getFile());
    int currentSum = 0;
    List<Integer> listOfSums = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(f))) {
      String line;
      while ((line = br.readLine()) != null) {
        if (line.isEmpty()) {
          listOfSums.add(currentSum);
          currentSum = 0;
        } else {
          Integer calories = Integer.valueOf(line);
          currentSum = currentSum + calories;
        }
      }
      listOfSums.add(currentSum);

    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return evaluateTopThreeWithSort(listOfSums).toString();
  }

  private static Integer evaluateTopValue(List<Integer> listOfSums) {
    Integer highestNumSoFar = 0;
    for (Integer i : listOfSums) {
      highestNumSoFar = Math.max(highestNumSoFar, i);
    }
    return highestNumSoFar;
  }

  private static Integer evaluateTopThreeWithSort(List<Integer> listOfSums) {
    listOfSums.sort(Comparator.reverseOrder());
    return listOfSums.get(0) + listOfSums.get(1) + listOfSums.get(2);
  }

  private static Integer evaluateTopThreeWithoutSorting(List<Integer> listOfSums) {
    // [6, 9, 13, 8, 20, 1, 100]
    // Highest Sum of Three --> 133
    // Highest --> 100
    // Second --> 20
    Integer highestSumOfThree = listOfSums.get(0) + listOfSums.get(1) + listOfSums.get(2);

    Integer highestSeenValue = Math.max(listOfSums.get(0), listOfSums.get(1));
    Integer secondHighestSeenValue = Math.min(listOfSums.get(0), listOfSums.get(1));

    for (int i = 2; i < listOfSums.size(); i++) {
      Integer currentValue = listOfSums.get(i);
      Integer currentHighestValue = highestSeenValue;
      Integer currentSecondHighestValue = secondHighestSeenValue;

      // Checking Sums
      Integer currentSumOfThree = currentHighestValue + currentSecondHighestValue + currentValue;
      highestSumOfThree = Math.max(currentSumOfThree, highestSumOfThree);

      // evaluate highest/second highest
      boolean currentValueIsHighest = false;
      highestSeenValue = Math.max(currentHighestValue, currentValue);
      if (currentValue >= currentHighestValue) {
        currentValueIsHighest = true;
      }
      if (currentValueIsHighest == true) {
        secondHighestSeenValue = Math.max(currentSecondHighestValue, currentHighestValue);
      } else {
        secondHighestSeenValue = Math.max(currentSecondHighestValue, currentValue);
      }
    }
    System.out.println("Highest Sum: " + highestSumOfThree);
    System.out.println("HighestValue: " + highestSeenValue);
    System.out.println("SecondValue: " + secondHighestSeenValue);

    return highestSumOfThree;
  }


}
