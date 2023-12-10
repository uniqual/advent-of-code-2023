package solutions.day01;

import java.util.List;
import java.util.Map;

public class Part2 extends Trebuchet {

    private static final Map<String, String> NUMBERS_MAPPER = Map.of(
            "one", "o1e",
            "two", "t2o",
            "three", "t3ree",
            "four", "f4ur",
            "five", "f5ve",
            "six", "s6x",
            "seven", "se7en",
            "eight", "ei8ht",
            "nine", "n9ne"
    );

    public int calibrate(List<String> inputs) {
        return inputs.stream()
                .map(Part2::convertWordToDigit)
                .map(Part2::removeNonDigit)
                .mapToInt(Part2::extractFirstAndLastDigit)
                .sum();
    }

    private static String convertWordToDigit(String input) {
        for (Map.Entry<String, String> entry : NUMBERS_MAPPER.entrySet()) {
            input = input.replace(entry.getKey(), entry.getValue());
        }
        return input;
    }
}
