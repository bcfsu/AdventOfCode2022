package Day5;

import java.io.*;
import java.net.URL;
import java.util.*;

public class Day5 {

    private static HashMap<Integer, Stack<String>> stackDatabase = new HashMap<>();

    public static String getResult() {
        URL path = Day5.class.getResource("input.txt");
        File f = new File(path.getFile());

        initializeInputStacks();

        // Read the Input
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!(line.contains("move"))) {
                    continue;
                }
               carryOutInstruction(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Return a string of the peeked item from each stack
        return getResultFromStackDatabase();
    }

    private static String getResultFromStackDatabase() {
        StringBuilder resultStr = new StringBuilder();
        for (Stack<String> thing : stackDatabase.values()) {
            resultStr.append(thing.peek());
        }
        return resultStr.toString();
    }

    private static void carryOutInstruction(String instruction) {
        String[] parsedInstruction = instruction.split(" "); // ==> ["move", "1", "from", "2", "to", "1"]

        Integer quantity = Integer.valueOf(parsedInstruction[1]);
        Integer originId = Integer.valueOf(parsedInstruction[3]);
        Integer destinationId = Integer.valueOf(parsedInstruction[5]);

        Stack<String> originStack = stackDatabase.get(originId);
        Stack<String> destinationStack = stackDatabase.get(destinationId);

        moveBoxesPartTwo(quantity, originStack, destinationStack);
    }

    // TODO: Broken
    private static void moveBoxesPartTwo(Integer quantity, Stack<String> originStack, Stack<String> destinationStack) {
        if (quantity == 1) {
            String box = originStack.pop();
            destinationStack.push(box);

        } else {
            int startIndex = originStack.size() - quantity;
            int lastIndex = originStack.size();

            List<String> subList = originStack.subList(startIndex, lastIndex);
            destinationStack.addAll(subList);
            originStack.removeAll(subList);
        }
    }

    private static void moveBoxesPartOne(Integer quantity, Stack<String> originStack, Stack<String> destinationStack) {
        int counter = 0;
        while (counter < quantity) {
            String box = originStack.pop();
            destinationStack.push(box);
            counter++;
        }
    }

    private static void initializeSampleStacks() {
        List<String> listOfContainersAsString = Arrays.asList("ZN", "MCD", "P");

        for (int i = 0; i < listOfContainersAsString.size(); i++) {
            String stringContainer = listOfContainersAsString.get(i);
            pushStringToStackDatabase(stringContainer, i + 1);
        }
    }

    private static void initializeInputStacks() {
        List<String> listOfContainersAsString = Arrays.asList("QFMRLWCV", "DQL", "PSRGWCNB", "LCDHBQG", "VGLFZS", "DGNP", "DZPVFCW", "CPDMS", "ZNWTVMPC");
        for (int i = 0; i < listOfContainersAsString.size(); i++) {
            String stringContainer = listOfContainersAsString.get(i);
            pushStringToStackDatabase(stringContainer, i + 1);
        }
    }

    private static void pushStringToStackDatabase(String input, Integer id) {
        Stack<String> stack = new Stack<>();
        String[] inputArr = input.split("");
        for (String s : inputArr) {
            stack.push(s);
        }

        stackDatabase.put(id, stack);
    }

}
