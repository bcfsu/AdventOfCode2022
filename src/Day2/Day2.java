package Day2;

import java.io.*;
import java.net.URL;
import java.util.HashMap;

public class Day2 {

    private static String DRAW = "Y";
    private static String WIN = "Z";
    private static String LOSS = "X";
    public static String getResult() {
        URL path = Day2.class.getResource("input.txt");
        File f = new File(path.getFile());

        Integer totalScore = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                totalScore = totalScore + calculateScoreOfRoundPart2(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
      return totalScore.toString();
    }


    private static HashMap<String, Integer> getInputValuesMap() {
        HashMap<String, Integer> map = new HashMap();
        map.put("A", 1); // Rock (A) beats Scissors (Z)
        map.put("B", 2); // Paper (B) beats Rock (X)
        map.put("C", 3); // Scissors (C) beats Paper (Y)

        map.put("X", 1);
        map.put("Y", 2);
        map.put("Z", 3);

        return map;
    }

    private static HashMap<String, Boolean> getWinLossCombinationsMap() {
        HashMap<String, Boolean> map = new HashMap();
        map.put("A Z", false); // Rock (A) beats Scissors (Z)
        map.put("B X", false); // Paper (B) beats Rock (X)
        map.put("C Y", false); // Scissors (C) beats Paper (Y)
        map.put("A Y", true);
        map.put("B Z", true);
        map.put("C X", true);
        return map;
    }

    private static HashMap<String, Integer> getWinningValues() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("A", 2); // value of Paper
        map.put("B", 3); // value of Scissors
        map.put("C", 1); // value of Rock
        return map;
    }

    private static HashMap<String, Integer> getLosingValues() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("A", 3); // value of Scissors
        map.put("B", 1); // value of Rock
        map.put("C", 2); // value of Paper
        return map;
    }

    private static Integer calculateScoreOfRoundPart2(String input) {
        Character opponentChoice = input.charAt(0); // i.e. Rock Paper Scissors
        Character yourDecision = input.charAt(2); // i.e. Win Lose Draw

        // If Draw -->
        // Return the same value + 3;
        if (yourDecision.toString().equals(DRAW)) {
            HashMap<String, Integer> inputValues = getInputValuesMap();
            return inputValues.get(opponentChoice.toString()) + 3;
        }

        // If Win -->
        // Find the Opponent's matching (losing) value i.e. Opponent places Rock, you return the VALUE of Paper
        // Add 6
        if (yourDecision.toString().equals(WIN)) {
            HashMap<String, Integer> winningValues = getWinningValues();
            return winningValues.get(opponentChoice.toString()) + 6;
        }

        // If Lose -->
        // Find the opposite value i.e. Opponent places Rock, return the VALUE of scissors
        // Return
        if (yourDecision.toString().equals(LOSS)) {
            HashMap<String, Integer> losingValues = getLosingValues();
            return losingValues.get(opponentChoice.toString());
        }

        System.out.println("Something went wrong?");
        return 0;
    }

    private static Integer calculateScoreOfRoundPart1(String input) {
        HashMap<String, Integer> inputValues = getInputValuesMap();
        HashMap<String, Boolean> didUWin = getWinLossCombinationsMap();

        Character opponentChoice = input.charAt(0);
        Character yourChoice = input.charAt(2);

        Integer opponentValue = inputValues.get(opponentChoice.toString());
        Integer yourValue = inputValues.get(yourChoice.toString());

        if (opponentValue == yourValue) {
            // Draw
            return 3 + yourValue;
        } else if (didUWin.get(input) == true) {
            return 6 + yourValue;
        } else if (didUWin.get(input) == false) {
            return yourValue;
        } else {
            System.out.println("Something is wrong");
            return 0;
        }
    }

}
