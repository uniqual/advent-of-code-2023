package day1;

import java.util.List;

public class Part1 extends Trebuchet {

    public int calibrate(List<String> inputs) {
        return inputs.stream()
                .map(Trebuchet::removeNonDigit)
                .mapToInt(Part1::extractFirstAndLastDigit)
                .sum();
    }
}
