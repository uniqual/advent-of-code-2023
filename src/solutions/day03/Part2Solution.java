package solutions.day03;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Part2Solution {

    public int part2(List<Line> lines) {
        List<Integer> adjacentNumbers = new ArrayList<>();
        collectGearRatioNumbers(lines, adjacentNumbers);
        return adjacentNumbers.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private void collectGearRatioNumbers(List<Line> lines, List<Integer> adjacentNumbers) {
        for (Line line : lines) {
            List<Number> numbers = Stream.of(line.getNumbers(),
                            getNextLineNumbers(lines, line.lineNumber()),
                            getPrevLineNumbers(lines, line.lineNumber()))
                    .flatMap(Collection::stream)
                    .toList();
            for (var symbol : line.getGearRatioSymbols()) {
                adjacentNumbers.add(getGearNumbersProduct(numbers, symbol));
            }
        }
    }

    private List<Number> getPrevLineNumbers(List<Line> lines, int index) {
        if (index > 0) {
            index = index - 1;
            return lines.get(index).getNumbers();
        }
        return Collections.emptyList();
    }

    private List<Number> getNextLineNumbers(List<Line> lines, int index) {
        index = index + 1;
        if (index < lines.size()) {
            return lines.get(index).getNumbers();
        }
        return Collections.emptyList();
    }

    private int getGearNumbersProduct(List<Number> numbers, Symbol symbol) {
        List<Integer> gearNumbers = numbers.stream()
                .filter(number -> (number.xPos() - 1) <= symbol.position() && (number.yPos() + 1) >= symbol.position())
                .map(Number::number)
                .toList();
        if (gearNumbers.size() == 2) {
            return gearNumbers.stream().reduce((i, j) -> i * j).orElse(0);
        }
        return 0;
    }
}
