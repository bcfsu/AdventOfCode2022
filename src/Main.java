import Day1.Day1;

public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        String result = Day1.getResult();
        long stopTime = System.currentTimeMillis();
        long duration = stopTime = startTime;

        System.out.println(result);
        System.out.println("-----");
        System.out.println("Duration in MS: " + duration);
    }
}