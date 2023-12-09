package day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Trebuchet {

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("inputs/day1-calibrate-trebuchet.txt");
        List<String> inputs = Files.readAllLines(path).stream().toList();
        System.out.println(new Part1().calibrate(inputs));
        System.out.println(new Part2().calibrate(inputs));
    }

    protected static String removeNonDigit(String input) {
        return input.replaceAll("\\D", "");
    }

    protected static Integer extractFirstAndLastDigit(String value) {
        char firstDigit = value.charAt(0);
        char lastDigit = value.charAt(value.length() - 1);
        String concat = String.valueOf(firstDigit) + lastDigit;
        return Integer.valueOf(concat);
    }
}
