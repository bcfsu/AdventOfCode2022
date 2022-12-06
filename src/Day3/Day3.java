package Day3;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Day3 {

    private static ArrayList<String> misplacedItemTypesPerRucksack = new ArrayList<>();

    private static ArrayList<String> commonLettersBetweenGroups = new ArrayList<>();


    // 8298 is correct for part 1
    // 2708 is correct for part 2
    public static String getResult() {
        URL path = Day3.class.getResource("input.txt");
        File f = new File(path.getFile());

        return getPartTwoResult(f);
    }

    private static String getPartTwoResult(File f) {
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            int counter = 1;

            Set<Character> finalSet = new HashSet<>();
            Set<Character> temporarySet = new HashSet<>();

            while ((line = br.readLine()) != null) {
               if (counter == 1) {
                   addLettersToSet(finalSet, line);
                   counter++;
                   continue;
               }

               if (counter == 2) {
                   // create set2 then combine results
                   addLettersToSet(temporarySet, line);
                   finalSet.retainAll(temporarySet);
                   counter++;
                   continue;
               }

               if (counter == 3) {
                   // iterate through and return / add to result as soon as you find a common letter between 3rd string and retained set
                   findTheSimilarCharacterInThirdLine(finalSet, line);
                   counter = 1;
                   finalSet.clear();
                   temporarySet.clear();
                   continue;
               }
            }

            return getTotalPriorityScore(commonLettersBetweenGroups).toString();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void findTheSimilarCharacterInThirdLine(Set<Character> setToCompare, String letters) {
        for (char l : letters.toCharArray()) {
            if (setToCompare.contains(l)) {
                commonLettersBetweenGroups.add(String.valueOf(l));
                return;
            }
        }
    }

    private static void addLettersToSet(Set<Character> setToAddTo, String letters) {
        for (char l : letters.toCharArray()) {
            setToAddTo.add(l);
        }
    }

    private static String getPartOneResult(File f) {
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
               findMisplacedItemTypeAndAddToResultArray(line);
            }

            return getTotalPriorityScore(misplacedItemTypesPerRucksack).toString();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void findMisplacedItemTypeAndAddToResultArray(String line) {
        String misplacedType = findMisplacedItemType(line);
        misplacedItemTypesPerRucksack.add(misplacedType);
    }

    private static String findMisplacedItemType(String input) {
        // return the misplaced item type
        char[] characters = input.toCharArray();
        int i = 0; // beginning of first compartment
        int j = characters.length - 1; // end of second compartment

        Set<Character> distinctItemTypesInFirstCompartment = new HashSet<>();
        Set<Character> distinctItemTypesInSecondCompartment = new HashSet<>();


        while (i < j) {
            char itemTypeCompartmentOne = characters[i];
            char itemTypeCompartmentTwo = characters[j];

            // Add the compartment types to the set and continue to next pair
            distinctItemTypesInFirstCompartment.add(Character.valueOf(itemTypeCompartmentOne));
            distinctItemTypesInSecondCompartment.add(Character.valueOf(itemTypeCompartmentTwo));

            // Continue to next pair
            i++;
            j--;
        }

        for (Character c : distinctItemTypesInFirstCompartment) {
            if (distinctItemTypesInSecondCompartment.contains(c)) {
                return String.valueOf(c);
            }
        }
        System.out.println("All item types distinct");
        return "";
    }

    private static Integer getTotalPriorityScore(ArrayList<String> input) {
        Integer totalScore = 0;
        for (String letter : input) {
            totalScore = totalScore + convertLetterToPriority(letter);
        }
        return totalScore;
    }


    private static Integer convertLetterToPriority(String lett) {
        // lower case a starts at 97 ==> must equal 1 in this game
        // upper case A starts at 65 ==> must equal 27 in this game

        int asciiValue = lett.charAt(0);
        // int asciiValue = charInput;

        if (asciiValue >= 97) {
            // use lowercase letter scoring diff
            return asciiValue - 96;
        }

        if (asciiValue <= 91) {
            // use uppercase letter scoring diff
            return asciiValue - 38;
        }

        System.out.println("Something went wrong converting letter to priority value");
        return 0;
    }
}
