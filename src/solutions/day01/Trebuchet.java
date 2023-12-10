package solutions.day01;

import solutions.InputReader;

import java.util.List;

public class Trebuchet {

    public static void main(String[] args) {
        List<String> input = InputReader.getAllFileLines("day01.txt");
        System.out.println(new Part1().calibrate(input));
        System.out.println(new Part2().calibrate(input));
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
